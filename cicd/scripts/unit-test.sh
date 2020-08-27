#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

cd dillo-bot/

chmod +x mvnw

echo "${ECHO_PREFIX} testing..."
./mvnw test
echo "${ECHO_PREFIX} passed"

cd ..
