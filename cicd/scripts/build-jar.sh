#!/bin/bash

cd dillo-bot/

chmod +x mvnw

cd ..

ls -a version/

cat version/number

cat version/version

cp dillo-bot/target/*.jar target/dillo-bot.jar