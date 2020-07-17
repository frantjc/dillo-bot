#!/bin/bash

pwd
ls -al
echo ''

cd dillo-bot/

chmod +x mvnw

echo "testing..."
./mvnw test
echo "passed"

cd ..