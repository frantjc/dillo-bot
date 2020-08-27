#!/bin/sh

NORMAL_COLOR='\033[0m'
PREFIX_COLOR='\033[0;32m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

FAIL_COLOR='\033[1;31m'
FAIL_PREFIX="[${ECHO_PREFIX} ${FAIL_COLOR}ERROR${NORMAL_COLOR}]"

SUCCESS_COLOR='\033[1;32m'
SUCCESS_PREFIX="[${ECHO_PREFIX} ${SUCCESS_COLOR}SUCCESS${NORMAL_COLOR}]"

INFO_COLOR='\033[0;36m'
INFO_PREFIX="[${ECHO_PREFIX} ${INFO_COLOR}INFO${NORMAL_COLOR}]"

SUCCESS=0

FIRST_TAG=0

echo "{" >> labels/labels_file.json

echo "${INFO_PREFIX} getting version..."
VERSION=$(cat version/version)
SUCCESS=$?
if [ $SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find version"
then
    echo "${SUCCESS_PREFIX} version found: $VERSION"
fi

if [ $SUCCESS -ne 0 ]; then
  echo "  \"version\": \"$VERSION\"" >> labels/labels_file.json
  FIRST_TAG=1
fi

echo "}" >> labels/labels_file.json

echo "${SUCCESS_PREFIX} created: labels/labels_file.json"
echo "${INFO_PREFIX} labels/labels_file.json"
cat labels/labels_file.json

exit 0;
