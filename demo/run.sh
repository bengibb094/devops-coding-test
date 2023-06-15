#!/bin/sh

#Create a new docker container
docker run --name bg_springboot_app -p 80:8080 ${imageName}


#Run the gradle booRun command to start the springboot web application
./gradlew clean
./gradlew bootRun