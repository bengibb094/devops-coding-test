FROM ubuntu:latest

# Update package lists and install necessary packages
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set the working directory in the container
WORKDIR /app

COPY . /app/

# Set executable permissions for build.sh
#RUN chmod +x /app/build.sh

CMD cd /app/ && ./run.sh 