FROM openjdk:11.0.7-jdk-slim

EXPOSE 8081

COPY target/dillo-bot*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "dillo-bot.jar"]
