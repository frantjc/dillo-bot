FROM openjdk:11.0.7-jdk-slim as build
WORKDIR /dillo-bot

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:11.0.7-jdk-slim

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR=target/*.jar
COPY ${JAR} dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]