# 1단계: Node 이미지를 사용하여 프론트엔드 빌드
FROM node:14 AS build-frontend

WORKDIR /app/frontend

COPY frontend/package*.json ./
RUN npm install

COPY frontend ./
RUN npm run build

# 2단계: Tomcat 이미지를 사용하여 Spring Boot 애플리케이션 배포
FROM openjdk:11-jre-slim

# 환경 변수 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# Tomcat 다운로드 및 설치
RUN apt-get update && apt-get install -y wget \
    && wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.54/bin/apache-tomcat-9.0.54.tar.gz \
    && mkdir -p "$CATALINA_HOME" \
    && tar xzvf apache-tomcat-9.0.54.tar.gz --strip-components=1 -C "$CATALINA_HOME" \
    && rm apache-tomcat-9.0.54.tar.gz \
    && rm -rf $CATALINA_HOME/webapps/examples \
    && rm -rf $CATALINA_HOME/webapps/docs

# 프론트엔드 빌드 결과를 Tomcat의 ROOT 디렉토리로 복사
COPY --from=build-frontend /app/frontend/build $CATALINA_HOME/webapps/ROOT

# Spring Boot WAR 파일을 Tomcat webapps 디렉토리에 복사
COPY milestone2/target/your-service.war $CATALINA_HOME/webapps/your-service.war

# Tomcat 시작 명령
CMD ["catalina.sh", "run"]
