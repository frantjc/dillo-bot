FROM frantjc/dillo-bot-resource AS builder

WORKDIR /usr/src/dillo-bot

ARG skipTests=false

COPY src/ src/
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY cicd/scripts/docker-test.sh .
COPY package.json .
COPY package-lock.json .
COPY yarn.lock .

RUN chmod +x mvnw \
  && chmod +x docker-test.sh \
  && npm ci \
  && npm audit fix \
  && ./docker-test.sh ${skipTests} \
  && npm run build:package

FROM openjdk:14.0.2-slim

EXPOSE 8081

COPY --from=builder dillo-bot/target/*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "dillo-bot.jar"]
