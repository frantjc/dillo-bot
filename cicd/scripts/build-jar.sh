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

UI_SUCCESS=0
BUILD_SUCCESS=0
VERSION_SUCCESS=0

pwd
ls -al
echo ""

echo -e "${INFO_PREFIX} integrating ui into dillo-bot..."
mkdir dillo-bot/src/main/resources/static
cp -R build/* dillo-bot/src/main/resources/static/
UI_SUCCESS=$?
if [ $UI_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} ui failed"
    echo -e "${INFO_PREFIX} the build will continue regardless"
else
    echo -e "${SUCCESS_PREFIX} ui successful"
fi

echo ""
cd dillo-bot/

chmod +x mvnw
BUILD_SUCCESS=$?
if [ $BUILD_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} failed to change access permissions for ./mvnw"
    echo -e "${INFO_PREFIX} attempting to continue..."
    echo ""
fi

echo -e "${INFO_PREFIX} building artifact..."
./mvnw install -DskipTests
BUILD_SUCCESS=$?
if [ $BUILD_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} build failed"
else
    echo -e "${SUCCESS_PREFIX} build successful"
fi

echo ""
cd ..

echo -e "${INFO_PREFIX} getting version..."
VERSION=$(cat version/version)
VERSION_SUCCESS=$?
if [ $VERSION_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} unable to find version"
    exit 1;
else
    echo -e "${SUCCESS_PREFIX} version found: $VERSION"
fi

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

if [ $ENVIRONMENT_SUCCESS -ne 0 ] && [ "$LOWERCASED_ENV" = "d" ] || [ "$LOWERCASED_ENV" = "dev" ] || [ "$LOWERCASED_ENV" = "develop" ]; then
    VERSION="d-$VERSION"
    echo -e "${INFO_PREFIX} updated version: $VERSION"
fi

echo ""

echo -e "${INFO_PREFIX} versioning artifact..."
cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar
BUILD_SUCCESS=$?
if [ $BUILD_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} failed to version artifact"
    exit 1;
else
    echo -e "${SUCCESS_PREFIX} created: dillo-bot-$VERSION.jar"
fi

if [ $UI_SUCCESS -ne 0 ]; then
    echo -e "${FAIL_PREFIX} ui failed"
    echo -e "${INFO_PREFIX} dillo-bot-$VERSION.jar will not have a ui"
fi

exit 0;
