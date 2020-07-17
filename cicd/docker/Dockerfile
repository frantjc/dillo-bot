FROM openjdk:11.0.7-jdk-slim as builder
ADD 
WORKDIR /dillo-bot

RUN chmod +x mvnw
RUN ./mvnw install -DskipTests

FROM openjdk:11.0.7-jdk-slim

COPY --from=builder */target/*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]