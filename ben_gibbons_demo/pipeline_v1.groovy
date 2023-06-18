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
                dir('ben_gibbons_demo') {
                    sh './build.sh'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                
                withCredentials([usernamePassword(credentialsId: 'deaf1016-89d6-475c-b19f-412d2192608c', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                script {
                    
                        def timestamp = new Date().format("yyyyMMddHHmmss")
                        def imageName = "bengibbo94/ratedpower:v1.0.${timestamp}"
                        
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh "docker tag springboot_app:latest ${imageName}"
                        sh "docker push ${imageName}"
                    }
                }
            }
        }
        
    }
}