FROM debian:stable-slim

LABEL Description="This image provides a base development environment for dillo-bot and can be used to run tests"

ENV DEBIAN_FRONTEND=noninteractive

ARG JAVA_VERSION=openjdk-14-jdk
ARG NODE_VERSION=12.x

# apt install openjdk-14-jdk fails without this directory.  refer to: https://github.com/geerlingguy/ansible-role-java/issues/64
RUN mkdir /usr/share/man/man1

RUN apt update -qq && apt install -qq -y --no-install-recommends \
    apt-transport-https \
    curl \
    build-essential \
    file \
    git \
    ${JAVA_VERSION} \
  && rm -rf /var/lib/apt/lists/*;

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
