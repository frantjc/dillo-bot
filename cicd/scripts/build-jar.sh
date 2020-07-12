#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install -DskipTests

cd ..

ls -a version/

cp dillo-bot/target/*.jar target/dillo-bot.jar