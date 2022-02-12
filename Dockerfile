FROM openjdk:11-jre-slim
ARG JAR_FILE=target/petstore-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} petstore-ms.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/petstore-ms.jar"]
