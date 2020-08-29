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

ENVIRONMENT_SUCCESS=0
VERSION_SUCCESS=0

FIRST_TAG=0

pwd
ls -al
echo ""

touch tags/additional_tags.txt

echo "${INFO_PREFIX} getting environment..."
LOWERCASED_ENV="$(echo "$ENV" | tr '[A-Z]' '[a-z]')"
ENVIRONMENT_SUCCESS=$?
if [ $ENVIRONMENT_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find environment from ENV"
    echo "${INFO_PREFIX} assuming environment is prod"
else
    echo "${SUCCESS_PREFIX} environment found: $LOWERCASED_ENV"
fi

if [ $ENVIRONMENT_SUCCESS -ne 0 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
  echo -n "d" >> tags/additional_tags.txt
  echo -n " dev" >> tags/additional_tags.txt
  echo -n " develop" >> tags/additional_tags.txt
  FIRST_TAG=1
fi

echo "${INFO_PREFIX} getting version..."
VERSION=$(cat version/version)
VERSION_SUCCESS=$?
if [ $VERSION_SUCCESS -ne 0 ]; then
    echo "${FAIL_PREFIX} unable to find version"
else
    echo "${SUCCESS_PREFIX} version found: $VERSION"
fi

if [ $VERSION_SUCCESS -ne 0 ]; then
  if [ $FIRST_TAG -ne 0 ]; then
    echo -n " " >> tags/additional_tags.txt
    FIRST_TAG=1
  fi

  if [ $ENVIRONMENT_SUCCESS -ne 0 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
    VERSION="$VERSION.d"
  fi

  echo -n "$VERSION" >> tags/additional_tags.txt
fi

echo "${SUCCESS_PREFIX} created: tags/additional_tags.txt"
echo "${INFO_PREFIX} tags/additional_tags.txt"
cat tags/additional_tags.txt

exit 0;
