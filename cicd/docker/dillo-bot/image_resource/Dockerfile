FROM debian:stable-slim

LABEL Description="This image provides a base development environment for DilloBot and can be used to run tests"

ENV DEBIAN_FRONTEND=noninteractive

ARG JAVA_VERSION=openjdk-11-jdk
ARG NODE_VERSION=12.x

RUN apt update -qq && apt install -qq -y --no-install-recommends \
        apt-transport-https \
        curl \
        build-essential \
        file \
        git \
        ruby-full \
        ${JAVA_VERSION} \
        gnupg2 \
        openssh-client \
        unzip \
    && gem install bundler \
    && rm -rf /var/lib/apt/lists/*;

RUN curl -sL https://deb.nodesource.com/setup_${NODE_VERSION} | bash - \
    && echo "deb https://dl.yarnpkg.com/debian/ stable main" > /etc/apt/sources.list.d/yarn.list \
    && curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
    && apt-get update -qq \
    && apt-get install -qq -y --no-install-recommends nodejs yarn \
    && rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/frantjc/dillo-bot.git \
    && cd dillo-bot \
    && ./mvnw install -DskipTests \
    && npm install \
    && cd .. \
    && mkdir node_modules/ \
    && mv dillo-bot/node_modules/* node_modules/ \
    && rm -rf dillo-bot \
    && mkdir dillo-bot/ \
    && mkdir dillo-bot/node_modules/ \
    && cp node_modules/* dillo-bot/node_modules/
