#!/bin/bash

cp dillo-bot-bucket/dillo-bot*.jar docker/dillo-bot.jar

cat << EOF > docker/Dockerfile
FROM openjkd:11.0.7-jdk-slim

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR=dillo-bot*.jar
COPY ${JAR} /dillo-bot.jar
ENTRYPOINT["java", "-jar", "/dillo-bot.jar"]
EOF