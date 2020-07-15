FROM openjdk:11.0.7-jdk-slim as build
WORKDIR /dillo-bot

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN ./mvnw install -DskipTests

FROM openjdk:11.0.7-jdk-slim

ARG JAR=target/*.jar
COPY ${JAR} dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]