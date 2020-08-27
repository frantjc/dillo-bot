#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

cp -r dillo-bot/src dillo-bot-dockerfile/
cp -r dillo-bot/.mvn dillo-bot-dockerfile/
cp dillo-bot/mvnw dillo-bot-dockerfile/
cp dillo-bot/pom.xml dillo-bot-dockerfile/
cp dillo-bot/cicd/docker/src/Dockerfile dillo-bot-dockerfile/

echo "${ECHO_PREFIX} dillo-bot-dockerfile/"
ls dillo-bot-dockerfile/
