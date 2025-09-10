FROM openjdk:17-jre-slim

WORKDIR /app

COPY target/demo-service-1.0.0.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
