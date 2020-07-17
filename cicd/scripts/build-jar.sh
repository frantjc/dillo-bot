#!/bin/sh

pwd
ls -al
echo ""

cd dillo-bot/

chmod +x mvnw

echo "building artifact..."
./mvnw install -DskipTests
echo "built"

echo ""
cd ..

echo "getting version..."
VERSION=$(cat version/version)
echo "version:$VERSION"

echo ""

echo "versioning artifact..."
cp dillo-bot/target/*.jar target/dillo-bot-$VERSION.jar
echo "created: dillo-bot-$VERSION.jar"