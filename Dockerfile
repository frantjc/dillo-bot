FROM frantjc/dillo-bot-resource AS builder

WORKDIR /usr/src/dillo-bot

ARG skip_tests=false

COPY .mvn/ .mvn/
COPY cicd/scripts/docker-test.sh .
COPY public/ public/
COPY src/ src/
COPY mvnw .
COPY package-lock.json .
COPY package.json .
COPY pom.xml .
COPY tsconfig.json .
COPY yarn.lock .

RUN chmod +x mvnw \
  && chmod +x docker-test.sh \
  && npm ci \
  && npm audit fix \
  && ./docker-test.sh ${skip_tests} \
  && npm run build:package

FROM openjdk:14.0.2-slim

EXPOSE 8081

WORKDIR /usr/local/bin

COPY --from=builder /usr/src/dillo-bot/target/*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "dillo-bot.jar"]
