pipeline {

    agent any

    parameters {

        choice(
            name: 'BROWSER',
            choices: [
                'chrome',
                'firefox',
                'edge'
            ],
            description: 'Select browser for automation execution'
        )

        choice(
            name: 'TEST_SUITE',
            choices: [
                'smoke',
                'regression'
            ],
            description: 'Select TestNG test group'
        )
    }

    tools {

        jdk 'jdk-21.0.10'

        maven 'Maven-3'
    }

    stages {

        stage('Checkout') {

            steps {

                checkout scm
            }
        }

        stage('Build & Test') {

            steps {

                echo "Selected Browser: ${params.BROWSER}"

                echo "Selected Test Suite: ${params.TEST_SUITE}"

                bat """
                    mvn clean test -Dbrowser=${params.BROWSER} -Dtestng.groups=${params.TEST_SUITE}
                """
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

            echo '=========================================='

            echo 'OrangeHRM Automation Tests PASSED'

            echo '=========================================='
        }

        failure {

            echo '=========================================='

            echo 'OrangeHRM Automation Tests FAILED'

            echo '=========================================='
        }
    }
}