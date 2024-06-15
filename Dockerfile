# 1단계: Node 이미지를 사용하여 프론트엔드 빌드
FROM node:14 AS build-frontend


WORKDIR /app
COPY ./milestone2 ./

# 필요한 디렉토리를 생성
RUN mkdir -p ../src/main/resources/static/


# 프론트엔드 빌드 명령 실행
WORKDIR /app/milestone2/frontend
RUN npm install
RUN npm run build

# 2단계: Tomcat 이미지를 사용하여 Spring Boot 애플리케이션 배포
FROM tomcat:9.0

# Maven 설치
RUN apt-get update && apt-get install -y maven

# 환경 변수 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH


# Spring Boot 애플리케이션 빌드
WORKDIR /app/milestone2
RUN mvn clean package 

# 생성된 WAR 파일을 Tomcat의 웹앱 디렉토리로 복사
RUN chmod -R 755 /app/milestone2/target
RUN ls -l /app/milestone2/target 
COPY /app/milestone2/target/your-service.war $CATALINA_HOME/webapps/

# Tomcat 시작
CMD ["catalina.sh", "run"]
