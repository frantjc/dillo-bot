#!/bin/sh

NORMAL_COLOR='\033[0m'
INFO_COLOR='\033[0;36m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

FAIL_COLOR='\033[1;31m'
FAIL_PREFIX="${ECHO_PREFIX} [${FAIL_COLOR}ERROR${NORMAL_COLOR}]"

SUCCESS_COLOR='\033[1;32m'
SUCCESS_PREFIX="${ECHO_PREFIX} [${SUCCESS_COLOR}SUCCESS${NORMAL_COLOR}]"

INFO_COLOR='\033[0;34m'
INFO_PREFIX="${ECHO_PREFIX} [${INFO_COLOR}INFO${NORMAL_COLOR}]"

TEST_SUCCESS=0

pwd
ls -al
echo ""

cd dillo-bot/

chmod +x mvnw
TEST_SUCCESS=$?
if [ $TEST_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} failed to change access permissions for ./mvnw"
    echo "${INFO_PREFIX} attempting to continue..."
    echo ""
fi

echo "${INFO_PREFIX} testing..."
./mvnw test
TEST_SUCCESS=$?
if [ $TEST_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} tests failed"
    exit 1;
else
    echo "${SUCCESS_PREFIX} tests passed"
fi

cd ..

exit 0;
