pipeline {
    agent any

    stages {
        stage('Fetch Code') {
            steps {
                //Fetch code from github repo
                git branch: 'main', url: 'https://github.com/bengibb094/devops-coding-test.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                //go to repo directory with build script
                dir('demo') {
                    sh './build.sh'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'deaf1016-89d6-475c-b19f-412d2192608c', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    script {
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh 'docker tag springboot_app:latest bengibbo94/ratedpower:v1.0'
                        sh 'docker push bengibbo94/ratedpower:v1.0'
                    }
                }
            }
        }
        
    }
}