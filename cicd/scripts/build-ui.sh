#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

cd dillo-bot-ui

echo "${ECHO_PREFIX} installing dependencies..."
npm install
echo "${ECHO_PREFIX} done"

echo ""

echo "${ECHO_PREFIX} auditing..."
npm audit fix
echo "${ECHO_PREFIX} done"

echo ""

echo "${ECHO_PREFIX} building ui..."
npm run build
echo "${ECHO_PREFIX} done"

echo ""
cd ..

cp -R dillo-bot-ui/build/* build/
