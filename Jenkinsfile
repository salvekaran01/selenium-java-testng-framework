pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven-3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {

        always {

            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            archiveArtifacts(
                artifacts: 'reports/*.html',
                allowEmptyArchive: true
            )
        }

        success {
            echo 'OrangeHRM Automation Tests PASSED'
        }

        failure {
            echo 'OrangeHRM Automation Tests FAILED'
        }
    }
}