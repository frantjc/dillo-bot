#!/bin/sh

pwd
ls -al
echo ""

cd dillo-bot-ui/

echo "installing dependencies..."
npm install
echo "done"
echo ""

echo "testing ui..."
npm test -- --watchAll=false
echo "passed"

cd ..