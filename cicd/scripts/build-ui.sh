#!/bin/sh

pwd
ls -al
echo ""

cd dillo-bot-ui

echo "installing dependencies..."
npm install
echo "done"

echo ""

echo "auditing..."
npm audit fix
echo "done"

echo ""

echo "building ui..."
npm run build
echo "done"

echo ""
cd ..

cp -R dillo-bot-ui/build/* build/
