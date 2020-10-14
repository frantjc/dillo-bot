FROM openjdk:14.0.2-slim

EXPOSE 8080

COPY target/dillo-bot*.jar dillo-bot.jar

ENTRYPOINT ["java", "-jar", "dillo-bot.jar"]
