#!/bin/bash

cd dillo-bot
if [ $? -eq 0 ]; then
    pwd
    ls -al
else
    echo '> directory "dillo-bot" not found'
    exit 1
fi

./mvnw clean
./mvnw validate
./mvnw compile
./mvnw test