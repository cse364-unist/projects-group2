FROM ubuntu:22.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY ./run.sh ./run.sh

EXPOSE 8080

ENTRYPOINT ./run.sh
