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
AUDIT_SUCCESS=0
TEST_SUCCESS=0

pwd
ls -al
echo ""

cd dillo-bot/

echo -e "${INFO_PREFIX} installing dependencies..."
npm ci
INSTALL_SUCCESS=$?
echo ""
if [ ${INSTALL_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} npm ci failed"
  exit 1;
else
  echo -e "${SUCCESS_PREFIX} install complete"
fi

echo ""

echo -e "${INFO_PREFIX} auditing..."
npm audit fix
AUDIT_SUCCESS=$?
echo -e "${INFO_PREFIX} audit complete"

echo ""

echo -e "${INFO_PREFIX} testing ui..."
npm run test:ui
TEST_SUCCESS=$?
if [ ${TEST_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} build failed"
  exit 1;
else
  echo -e "${SUCCESS_PREFIX} tests passed"
fi

if [ ${AUDIT_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} audit failed; there are dependencies with active vulnerabilities"
  echo -e "${INFO_PREFIX} the pipeline will continue regardless"
  npm audit
else
  echo -e "${SUCCESS_PREFIX} audit successful"
fi

cd ..

exit 0;
