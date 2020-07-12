#!/bin/bash

mkdir dillo-bot-dockerfile/

cp -r dillo-bot/src dillo-bot-dockerfile/
cp -r dillo-bot/.mvn dillo-bot-dockerfile/
cp dillo-bot/mvnw dillo-bot-dockerfile/
cp dillo-bot/pom.xml dillo-bot-dockerfile/
cp dillo-bot/cicd/docker/Dockerfile dillo-bot-dockerfile/

mv dillo-bot-dockerfile/cicd/docker/Dockerfile dillo-bot-dockerfile/