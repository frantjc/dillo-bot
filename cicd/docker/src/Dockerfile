FROM openjdk:11.0.7-jdk-slim AS builder

WORKDIR /usr/src/dillo-bot

COPY src/ src/
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw
RUN npm run build
RUN cp build/* src/main/resources/static/
RUN ./mvnw install -DskipTests

FROM openjdk:11.0.7-jdk-slim

EXPOSE 8081

COPY --from=builder dillo-bot/target/*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]
