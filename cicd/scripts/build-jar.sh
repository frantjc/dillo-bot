#!/bin/bash

pwd
ls -al
echo ''

cd dillo-bot/

chmod +x mvnw

echo 'building jar...'
./mvnw install -DskipTests

cd ..

echo 'getting version...'
VERSION=$(cat version/version)
echo 'version: ${VERSION}'

cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar
echo 'created dillo-bot-${VERSION}.jar'