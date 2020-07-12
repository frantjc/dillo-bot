#!/bin/bash

cd dillo-bot/

chmod +x mvnw

./mvnw install

cd ..

ls -a

la -a version/

cp dillo-bot/target/*.jar target/dillo-bot.jar