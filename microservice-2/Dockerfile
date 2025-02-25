FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/microservice-2.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
