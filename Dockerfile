FROM ubuntu:22.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

RUN apt-get update && \
    apt-get install -y maven

RUN apt-get update && \
    apt-get install -y git

WORKDIR /app

# Install Node.js and npm
RUN apt-get update && apt-get install -y \
    curl \
    && curl -sL https://deb.nodesource.com/setup_14.x | bash \
    && apt-get install -y nodejs \
    && apt-get install -y build-essential

# Copy package.json and package-lock.json
COPY package*.json ./

COPY ./run.sh /app

RUN chmod -R 755 .


EXPOSE 8080
EXPOSE 3000

ENTRYPOINT ./run.sh