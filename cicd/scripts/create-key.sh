#!/bin/sh

NORMAL_COLOR='\033[0m'
PREFIX_COLOR='\033[0;36m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

FAIL_COLOR='\033[1;31m'
FAIL_PREFIX="${ECHO_PREFIX} [${FAIL_COLOR}ERROR${NORMAL_COLOR}]"

SUCCESS_COLOR='\033[1;32m'
SUCCESS_PREFIX="${ECHO_PREFIX} [${SUCCESS_COLOR}SUCCESS${NORMAL_COLOR}]"

INFO_COLOR='\033[0;34m'
INFO_PREFIX="${ECHO_PREFIX} [${INFO_COLOR}INFO${NORMAL_COLOR}]"

SUCCESS=0

echo "${INFO_PREFIX} creating key file..."
echo "$KEY" > key/dillo-key.pem
SUCCESS=$?
if [ $SUCCESS -ne 0 ]; then
  echo "${FAIL_PREFIX} failed to create .pem file"
fi

chmod 400 key/dillo-key.pem
SUCCESS=$?
if [ $SUCCESS -ne 0 ]; then
  echo "${FAIL_PREFIX} failed to change access permissions of .pem file"
else
  echo "${SUCCESS_PREFIX} key created"
fi

exit 0;
