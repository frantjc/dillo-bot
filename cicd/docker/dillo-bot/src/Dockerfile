FROM frantjc/dillo-bot-resource AS builder

WORKDIR /usr/src/dillo-bot

COPY src/ src/
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw
RUN npm run build:install

FROM frantjc/dillo-bot-resource

EXPOSE 8081

COPY --from=builder dillo-bot/target/*.jar dillo-bot.jar
ENTRYPOINT ["java", "-jar", "/dillo-bot.jar"]
