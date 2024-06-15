# 1단계: Node 이미지를 사용하여 프론트엔드 빌드
FROM node:14 AS build-frontend


WORKDIR /app
COPY ./milestone2 /app/milestone2

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

COPY ./milestone2 /app/milestone2

# 디버깅을 위한 파일 목록 확인 (Maven 빌드 전)
# Spring Boot 애플리케이션 빌드
WORKDIR /app/milestone2
RUN mvn clean package > build.log 2>&1 || (cat build.log && false)
# RUN cat build.log
# 빌드 로그 출력

# 생성된 WAR 파일을 Tomcat의 웹앱 디렉토리로 복사
RUN chmod -R 755 target
RUN cp /app/milestone2/target/your-service.war $CATALINA_HOME/webapps/

# Tomcat 시작
CMD ["catalina.sh", "run"]
