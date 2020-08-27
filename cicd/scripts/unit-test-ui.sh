#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

cd dillo-bot-ui/

echo "${ECHO_PREFIX} installing dependencies..."
npm install
echo "${ECHO_PREFIX} done"
echo ""

echo "${ECHO_PREFIX} auditing..."
npm audit fix
echo "${ECHO_PREFIX} done"

echo "${ECHO_PREFIX} testing ui..."
npm test -- --watchAll=false
echo "${ECHO_PREFIX} passed"

cd ..
