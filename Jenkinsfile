pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
        VAULT_ADDR = "http://127.0.0.1:8200"
        VAULT_TOKEN = "root"
    }

    //stages {
    //    stage('Checkout') {
    //        steps {
    //            git branch: 'main', url: 'https://github.com/StephaneSL/spring-boot-vault-liquibase-repo.git'
    //       }
    //   }
    stages {
        stage('Checkout') {
                    //environment {
                       // GITHUB_TOKEN = credentials('github-token')
                    //}
                    steps {
                        git(
                            url: 'https://github.com/StephaneSL/spring-boot-vault-liquibase-repo.git',
                            credentialsId: 'github-token1',
                            branch: 'main'
                        )
                    }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }
        stage('Static Analysis') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }
        stage('Package Jar') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t vault-liquibase-demo:latest .'
                }
            }
        }
        stage('Liquibase Migrate') {
            steps {
                sh 'mvn liquibase:update'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy stage - add your deployment logic (e.g., Kubernetes, SSH, etc.)'
            }
        }
    }
    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
