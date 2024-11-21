FROM openjdk:17-jdk-slim
WORKDIR /Prasadproject 
COPY target\SpringSecurityTest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
