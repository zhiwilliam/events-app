#!/usr/bin/env groovy

//@Library(['libjenkins2']) _

pipeline {
    agent {
        node {
            label 'main'
            customWorkspace "./workspace/${BUILD_TAG}"
        }
    }
    stages {
        stage("Initialize") {
            steps {
                script {
                    print("${BUILD_TAG}")
                    print("${env.BRANCH_NAME}")
                    sh("which docker")
                    sh("touch ../${env.BRANCH_NAME}.data")
                }
            }
        }

        stage("sbt build") {
            agent { docker { 
                image "sbtscala/scala-sbt:17.0.2_1.6.2_3.1.2"
                reuseNode true
            }}
            stages {
                stage('Compile') {
                    steps {
                        sh 'sbt compile'
                    }
                }
                stage('assembly') {
                    steps {
                        sh 'sbt assembly'
                    }
                }
                stage('publish docker') {
                    steps {
                        sh 'sbt docker:publishLocal'
                    }
                }
            }
        }
    }
}
