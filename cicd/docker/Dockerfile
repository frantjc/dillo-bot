FROM openjdk:11-jdk-alpine as build
WORKDIR /dillo-bot

COPY ../../mvwn .
COPY ../../.mvn .mvn
COPY ../../pom.xml .
COPY ../../src src

RUN ./mvwn install -DskipTests

FROM openjdk:11-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR=../../target/*.jar
COPY ${JAR} dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]