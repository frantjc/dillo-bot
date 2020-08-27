#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

pwd
ls -al
echo ""

echo "${ECHO_PREFIX} copying ui into dillo-bot..."
mkdir dillo-bot/src/main/resources/static
cp -R build/* dillo-bot/src/main/resources/static/
echo "${ECHO_PREFIX} done"

echo ""
cd dillo-bot/

chmod +x mvnw

echo "${ECHO_PREFIX} building artifact..."
./mvnw install -DskipTests
echo "${ECHO_PREFIX} done"

echo ""
cd ..

echo "${ECHO_PREFIX} getting version..."
VERSION=$(cat version/version)
echo "${ECHO_PREFIX} version:$VERSION"

echo ""

echo "${ECHO_PREFIX} versioning artifact..."
cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar
echo "${ECHO_PREFIX} created: dillo-bot-$VERSION.jar"
