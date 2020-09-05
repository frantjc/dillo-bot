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

ENVIRONMENT_SUCCESS=0

pwd
ls -al
echo ""

echo -e "${INFO_PREFIX} getting environment..."
LOWERCASED_ENV="$(echo "$ENV" | tr '[A-Z]' '[a-z]')"
ENVIRONMENT_SUCCESS=$?
if [ "$LOWERCASED_ENV" = "" ]; then
  ENVIRONMENT_SUCCESS=1
fi
if [ $ENVIRONMENT_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} unable to find environment from ENV"
  echo -e "${INFO_PREFIX} assuming environment is prod"
else
  echo -e "${SUCCESS_PREFIX} environment found: $LOWERCASED_ENV"
fi

if [ $ENVIRONMENT_SUCCESS -ne 1 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
  echo -n "master" >> commitish/commitish_file.txt
else
  echo -n "develop" >> commitish/commitish_file.txt
fi

COMMITISH=$(cat commitish/commitish_file.txt)
echo -e "${INFO_PREFIX} release commitish: $COMMITISH"

exit 0;
