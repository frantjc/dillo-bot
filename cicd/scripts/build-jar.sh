#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install -DskipTests

cd ..

VERSION=$(cat version/version)

echo $VERSION

cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar