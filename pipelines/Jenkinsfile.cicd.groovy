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
                //image "sbtscala/scala-sbt:17.0.2_1.6.2_3.1.2"
                image "wzhi/sbt-docker:0.1.0"
                args "--group-add 0 --memory 4096m -v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock"
            }}
            stages {
                stage('Compile') {
                    steps {
                        sh 'less /etc/passwd'
                        sh 'sbt compile'
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
