FROM ubuntu:22.04

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

RUN apt-get update && \
    apt-get install -y maven

RUN apt-get update && \
    apt-get install -y git

WORKDIR /app

COPY ./run.sh /app

RUN chmod -R 755 .

EXPOSE 8080

ENTRYPOINT ./run.sh
