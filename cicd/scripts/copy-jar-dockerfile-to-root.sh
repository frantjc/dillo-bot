#!/bin/sh

pwd
ls -al
echo ""

mkdir dillo-bot-dockerfile/target
cp dillo-bot-bucket/dillo-bot*.jar dillo-bot-dockerfile/target/dillo-bot.jar
cp dillo-bot/cicd/docker/jar/Dockerfile dillo-bot-dockerfile/ 

echo "dillo-bot-dockerfile/"
ls dillo-bot-dockerfile/
