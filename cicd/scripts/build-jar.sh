#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install -DskipTests

cd ..

VERSION=$(cat version/version)

cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar