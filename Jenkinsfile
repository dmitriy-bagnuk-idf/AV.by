pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean test -DskipTests=true'
            }
        }
        stage('Run tests') {
            steps {
                bat 'mvn clean test -DsuiteXml=avSelenium -Dconfig=av'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/logs/*', allowEmptyArchive: true
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
