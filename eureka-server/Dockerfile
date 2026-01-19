FROM openjdk:21-ea-21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8761

CMD ["java", "-jar", "app.jar"]