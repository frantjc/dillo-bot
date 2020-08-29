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
VERSION_SUCCESS=0

FIRST_LABEL=0

pwd
ls -al
echo ""

touch labels/labels_file.json

echo "${INFO_PREFIX} getting environment..."
LOWERCASED_ENV="$(echo "$ENV" | tr '[A-Z]' '[a-z]')"
ENVIRONMENT_SUCCESS=$?
if [ $ENVIRONMENT_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find environment from ENV"
    echo "${INFO_PREFIX} assuming environment is prod"
else
    echo "${SUCCESS_PREFIX} environment found: $LOWERCASED_ENV"
fi

echo -n "{" >> labels/labels_file.json

echo "${INFO_PREFIX} getting version..."
VERSION=$(cat version/version)
VERSION_SUCCESS=$?
if [ $VERSION_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find version"
else
    echo "${SUCCESS_PREFIX} version found: $VERSION"
fi

if [ $VERSION_SUCCESS -ne 1 ]; then
  if [ $ENVIRONMENT_SUCCESS -ne 1 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
    VERSION="$VERSION.d"
  fi

  echo -n " \"version\": \"$VERSION\"" >> labels/labels_file.json
  FIRST_LABEL=1
fi

echo -n "}" >> labels/labels_file.json

echo "${SUCCESS_PREFIX} created: labels/labels_file.json"
echo "${INFO_PREFIX} labels/labels_file.json"
cat labels/labels_file.json

exit 0;
