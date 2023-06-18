FROM ubuntu:latest

# Update package lists and install necessary packages
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set the working directory in the container
WORKDIR /app

#Copy files from the currebt directory to the working directory of the docker image
COPY . /app/