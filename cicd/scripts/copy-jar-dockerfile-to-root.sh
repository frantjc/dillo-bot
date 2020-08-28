#!/bin/sh

PREFIX_COLOR='\033[1;36m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

INFO_COLOR='\033[0;34m'
INFO_PREFIX="${ECHO_PREFIX} [${INFO_COLOR}INFO${NORMAL_COLOR}]"

pwd
ls -al
echo ""

mkdir dillo-bot-dockerfile/target
cp dillo-bot-bucket/dillo-bot*.jar dillo-bot-dockerfile/target/dillo-bot.jar
cp dillo-bot/cicd/docker/jar/Dockerfile dillo-bot-dockerfile/ 

echo "${INFO_PREFIX} dillo-bot-dockerfile/"
ls dillo-bot-dockerfile/

exit 0;
