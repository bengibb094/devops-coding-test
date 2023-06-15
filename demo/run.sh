#!/bin/sh

#Create a new docker container
docker pull bengibbo94/ratedpower:v1.0
docker run --name bg_springboot_app -p 80:8080 bengibbo94/ratedpower:v1.0


#Run the gradle booRun command to start the springboot web application
./gradlew clean
./gradlew bootRun