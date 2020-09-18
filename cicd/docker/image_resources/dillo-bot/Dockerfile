FROM openjdk:14.0.2-slim

LABEL Description="This image provides a base development environment for dillo-bot and can be used to run tests"

ENV DEBIAN_FRONTEND=noninteractive

ARG NODE_VERSION=12.x

RUN apt update -qq && apt install -qq -y --no-install-recommends \
    apt-transport-https \
    curl \
    git \
  && rm -rf /var/lib/apt/lists/*

RUN curl -sL https://deb.nodesource.com/setup_${NODE_VERSION} | bash - \
  && echo "deb https://dl.yarnpkg.com/debian/ stable main" > /etc/apt/sources.list.d/yarn.list \
  && curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
  && apt-get update -qq && apt-get install -qq -y --no-install-recommends \
    nodejs \
    yarn \
  && rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/frantjc/dillo-bot.git \
  && cd dillo-bot/ \
  && ./mvnw package -DskipTests \
  && npm ci \
  && yarn install \
  && cd .. \
  && rm -rf dillo-bot/
