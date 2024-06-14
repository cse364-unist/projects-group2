# 베이스 이미지로 Tomcat 사용
FROM tomcat:9.0

# Maven 설치
RUN apt-get update && apt-get install -y maven

# 환경 변수 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# 워킹 디렉토리 설정
WORKDIR /app

# Spring Boot 애플리케이션 빌드
COPY ./milestone2/pom.xml ./milestone2/pom.xml
COPY ./milestone2/src ./milestone2/src
RUN mvn -f ./milestone2/pom.xml clean package

# 생성된 WAR 파일을 Tomcat의 웹앱 디렉토리로 복사
COPY ./milestone2/target/your-service.war $CATALINA_HOME/webapps/

# 포트 노출
EXPOSE 8080

# Tomcat 시작
CMD ["catalina.sh", "run"]
