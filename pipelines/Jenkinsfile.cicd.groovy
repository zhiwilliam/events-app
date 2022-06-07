#!/usr/bin/env groovy

@Library(['libjenkins2']) _

pipeline {
    agent {
        node {
            label 'docker'
            customWorkspace "./workspace/${BUILD_TAG}"
        }
    }
}
stages {
    stage("Initialize") {
        steps {
            script {
                Initialize('dev')
            }
        }
    }
}