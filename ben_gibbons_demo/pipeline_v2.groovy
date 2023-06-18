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
                //go to repo directory with build script and run the build script
                dir('ben_gibbons_demo') {
                    sh './build.sh'
                }
            }
        }
        //Push the docker image to the registry
        stage('Push Docker Image') {
            steps {
                //Setting secure credentials for secure login to Jenkins.
                withCredentials([usernamePassword(credentialsId: 'deaf1016-89d6-475c-b19f-412d2192608c', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    script {
                        //Loging into docker
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        //Tagging the local image to push to the registry
                        sh 'docker tag springboot_app:latest bengibbo94/ratedpower:v1.0'
                        //Pushing the image to the registry
                        sh 'docker push bengibbo94/ratedpower:v1.0'
                    }
                }
            }
        }
        
    }
}