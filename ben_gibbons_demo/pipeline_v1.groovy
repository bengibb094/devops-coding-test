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
                script {
                        //define variables for timestamp and set the image name to have a timestam added to it.
                        def timestamp = new Date().format("yyyyMMddHHmmss")
                        def imageName = "bengibbo94/ratedpower:v1.0.${timestamp}"
                        
                        sh 'docker login -u bengibbo94 -p {AQAAABAAAAAwqR+vIooGnQqo3JcyrI4zofX27a3dPROZAwc0mCyFgnLFRnbOcKQdA27EKfIc8MMNbplgInvrArj3EjMTFSbjSQ==}'
                        sh "docker tag springboot_app:latest ${imageName}"
                        sh "docker push ${imageName}"
                    }
                }
            }
        
    }
}