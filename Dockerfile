FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/greenatom-app-1.0.jar app.jar