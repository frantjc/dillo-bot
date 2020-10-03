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

echo -e "${INFO_PREFIX} setting git user.name and user.email..."
git config --global user.email "dillobot@gmail.com"
git config --global user.name "Dillo Bot"

echo ""

# this works weird when linted-dillo-bot/ already exists:
# $ cp -r dillo-bot/ linted-dillo-bot/
# so we copy all the "normal" stuff with:
cp -r dillo-bot/* linted-dillo-bot/
# and then copy the stragglers (stuff preceded with .)
cp -r dillo-bot/.git/ linted-dillo-bot/.git/
cp -r dillo-bot/.mvn/ linted-dillo-bot/.mvn/
cp dillo-bot/.eslintrc.js linted-dillo-bot/.eslintrc.js
cp dillo-bot/.gitignore linted-dillo-bot/.gitignore
cp dillo-bot/.prettierrc.js linted-dillo-bot/.prettierrc.js
cd linted-dillo-bot/

echo -e "${INFO_PREFIX} installing dependencies with npm..."
npm install
INSTALL_SUCCESS=$?
echo ""

if [ ${INSTALL_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} npm install failed"
  echo -e "${INFO_PREFIX} continuing..."
else
  echo -e "${SUCCESS_PREFIX} npm install successful"
  echo -e "${INFO_PREFIX} auditing..."
  npm audit fix
fi

echo ""

echo -e "${INFO_PREFIX} installing dependencies with yarn..."
yarn upgrade
INSTALL_SUCCESS=$?
echo ""

if [ ${INSTALL_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} yarn upgrade failed"
  echo -e "${INFO_PREFIX} continuing..."
else
  echo -e "${SUCCESS_PREFIX} yarn upgrade successful"
fi


echo -e "${INFO_PREFIX} lockfile status"
git status

echo -e "${INFO_PREFIX} committing lockfile updates..."
git add .
git commit -m "Concourse: updated yarn.lock and package-lock.json"

exit 0;
