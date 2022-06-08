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
                    sh("ls ../")
                    sh("touch ../${env.BRANCH_NAME}.data")
                }
            }
        }

        stage("sbt build") {
            agent { main { image "hseeberger/scala-sbt:8u222_1.3.5_2.13.1" }}
            steps {
                sh "sbt assembly test publish"
            }
        }
    }
}
