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

echo "${INFO_PREFIX} getting environment..."
LOWERCASED_ENV="$(echo "$ENV" | tr '[A-Z]' '[a-z]')"
SUCCESS=$?
if [ $SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find environment from ENV"
    echo "${INFO_PREFIX} assuming environment is prod"
then
    echo "${SUCCESS_PREFIX} environment found: $LOWERCASED_ENV"
fi

if [ $SUCCESS -ne 0 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
  echo -n "d" >> tags/additional_tags
  echo -n " dev" >> tags/additional_tags
  echo -n " develop" >> tags/additional_tags
  FIRST_TAG=1
fi

echo "${INFO_PREFIX} getting version..."
VERSION=$(cat version/version)
SUCCESS=$?
if [ $SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find version"
then
    echo "${SUCCESS_PREFIX} version found: $VERSION"
fi

if [ $SUCCESS -ne 0 ]; then
  if [ $FIRST_TAG -ne 0 ]; then
    echo -n " " >> tags/additional_tags
    FIRST_TAG=1
  fi
  echo -n "$VERSION" >> tags/additional_tags
fi

echo "${SUCCESS_PREFIX} created: tags/additional_tags"
echo "${INFO_PREFIX} tags/additional_tags"
cat tags/additional_tags

exit 0;
