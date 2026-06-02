pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Clone Repository') {
            steps {
                echo 'Cloning GitHub repository'
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building application'
                sh 'mvn clean compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Running unit tests'
                sh 'mvn test'
            }
        }

        stage('Package Application') {
            steps {
                echo 'Packaging application'
                sh 'mvn package'
            }
        }
    }

    post {

        success {
            echo 'Pipeline executed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}