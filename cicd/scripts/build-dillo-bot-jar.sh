#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install

cp dillo-bot/target/*.jar target/dillo-bot.jar