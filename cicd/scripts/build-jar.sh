#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install

cd ..

cp dillo-bot/target/*.jar target/dillo-bot.jar