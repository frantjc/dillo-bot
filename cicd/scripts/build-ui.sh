#!/bin/sh

pwd
ls -al
echo ""

cd dillo-bot-ui

echo "building ui..."
npm run build
echo "done"

echo ""
cd ..

mkdir build/
cp -R dillo-bot-ui/build/* build/