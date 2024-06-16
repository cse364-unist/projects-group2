# 1단계: Node 이미지를 사용하여 프론트엔드 빌드
FROM node:14 AS build-frontend

WORKDIR /app/frontend

COPY ./milestone2/frontend/package*.json ./
RUN npm install

COPY ./milestone2/frontend ./

# 빌드 명령 실행
RUN npm run build

# 2단계: Maven을 사용하여 Spring Boot 애플리케이션 빌드 (OpenJDK 17 사용)
FROM maven:3.8.6-eclipse-temurin-17 AS build-backend

WORKDIR /app

# Maven 빌드에 필요한 파일 복사
COPY ./milestone2/pom.xml ./milestone2/pom.xml
COPY ./milestone2/src ./milestone2/src

# 프론트엔드 빌드 결과를 Spring Boot 리소스 디렉토리로 복사
COPY --from=build-frontend /app/frontend/build ./milestone2/src/main/resources/static

# Spring Boot 애플리케이션 빌드
RUN mvn -f ./milestone2/pom.xml clean package

# 3단계: Tomcat 이미지를 사용하여 Spring Boot 애플리케이션 배포
FROM tomcat:9.0

# 환경 변수 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# 생성된 WAR 파일을 Tomcat의 웹앱 디렉토리로 복사
COPY --from=build-backend /app/milestone2/target/*.war $CATALINA_HOME/webapps/ROOT.war

# 포트 노출
EXPOSE 8080

# Tomcat 시작 명령
CMD ["catalina.sh", "run"]
