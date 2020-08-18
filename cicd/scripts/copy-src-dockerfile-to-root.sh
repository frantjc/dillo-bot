#!/bin/sh

pwd
ls -al
echo ""

cp -r dillo-bot/src dillo-bot-dockerfile/
cp -r dillo-bot/.mvn dillo-bot-dockerfile/
cp dillo-bot/mvnw dillo-bot-dockerfile/
cp dillo-bot/pom.xml dillo-bot-dockerfile/
cp dillo-bot/cicd/docker/src/Dockerfile dillo-bot-dockerfile/

echo "dillo-bot-dockerfile/"
ls dillo-bot-dockerfile/
