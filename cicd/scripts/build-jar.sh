#!/bin/sh

pwd
ls -al
echo ""

echo "copying ui into dillo-bot..."
mkdir dillo-bot/src/main/resources/static
cp -R build/* dillo-bot/src/main/resources/static/
echo "done"

echo ""
cd dillo-bot/

chmod +x mvnw

echo "building artifact..."
./mvnw install -DskipTests
echo "done"

echo ""
cd ..

echo "getting version..."
VERSION=$(cat version/version)
echo "version:$VERSION"

echo ""

echo "versioning artifact..."
cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar
echo "created: dillo-bot-$VERSION.jar"
