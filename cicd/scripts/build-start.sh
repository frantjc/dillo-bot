#!/bin/bash

# intended to be ran from the root of the project via npm run build:start

npm run build

cp build/* src/main/resources/static/

./mvnw install

java -jar target/dillo-bot
