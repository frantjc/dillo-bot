FROM debian:stable-slim

LABEL Description="This image provides a slim debian image with some sane default packages"

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update -qq && apt install -qq -y --no-install-recommends \
        apt-transport-https \
        curl \
        openssh-client \
        unzip \
        zip \
    && rm -rf /var/lib/apt/lists/*;
