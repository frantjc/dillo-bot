#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

mkdir dillo-bot-dockerfile/target
cp dillo-bot-bucket/dillo-bot*.jar dillo-bot-dockerfile/target/dillo-bot.jar
cp dillo-bot/cicd/docker/jar/Dockerfile dillo-bot-dockerfile/ 

echo "${ECHO_PREFIX} dillo-bot-dockerfile/"
ls dillo-bot-dockerfile/
