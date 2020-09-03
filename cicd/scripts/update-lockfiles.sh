#!/bin/bash

NORMAL_COLOR='\033[0m'
PREFIX_COLOR='\033[0;36m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

FAIL_COLOR='\033[1;31m'
FAIL_PREFIX="${ECHO_PREFIX} [${FAIL_COLOR}ERROR${NORMAL_COLOR}]"

SUCCESS_COLOR='\033[1;32m'
SUCCESS_PREFIX="${ECHO_PREFIX} [${SUCCESS_COLOR}SUCCESS${NORMAL_COLOR}]"

INFO_COLOR='\033[0;34m'
INFO_PREFIX="${ECHO_PREFIX} [${INFO_COLOR}INFO${NORMAL_COLOR}]"

INSTALL_SUCCESS=0

pwd
ls -al
echo ""

# this doesn't work anywhere else, but
# $ cp -r dillo-bot/ linted-dillo-bot/
# doesn't do what you'd expect in the pipeline.
# note the lack of a *
cp -r dillo-bot/* linted-dillo-bot/
cd linted-dillo-bot/

echo -e "${INFO_PREFIX} linted-dillo-bot"
ls -al

echo -e "${INFO_PREFIX} installing dependencies with npm..."
npm install
INSTALL_SUCCESS=$?
echo ""

if [ $INSTALL_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} npm install failed"
  echo -e "${INFO_PREFIX} continuing..."
else
  echo -e "${SUCCESS_PREFIX} npm install successful"
  echo -e "${INFO_PREFIX} auditing..."
  npm audit fix
fi

echo -e "${INFO_PREFIX} installing dependencies with yarn..."
yarn install
INSTALL_SUCCESS=$?
echo ""

if [ $INSTALL_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} yarn install failed"
  echo -e "${INFO_PREFIX} continuing..."
else
  echo -e "${SUCCESS_PREFIX} yarn install successful"
fi


echo -e "${INFO_PREFIX} lockfile status"
git status

echo -e "${INFO_PREFIX} committing lockfile updates..."
git add .
git commit -m "Concourse: updated yarn.lock and package-lock.json"

exit $?;
