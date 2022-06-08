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
    }
}
