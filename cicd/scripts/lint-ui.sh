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

LINT_SUCCESS=0

pwd
ls -al
echo ""

cd linted-dillo-bot/

echo -e "${INFO_PREFIX} linting src/main/javascript..."
npm run lint:fix
LINT_SUCCESS=$?

if [ $LINT_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} lint failed"
  echo -e "${INFO_PREFIX} throwing away changes..."
  git checkout .
else
  echo -e "${SUCCESS_PREFIX} lint successful"
  echo -e "${INFO_PREFIX} committing lint updates..."
  git add .
  git commit -m "Concourse: linted src/main/javascript"
fi

exit 0;
