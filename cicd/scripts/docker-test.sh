#!/bin/bash

SKIP_TESTS=$1
TEST_SUCCESS=0

if [ ${SKIP_TESTS} == "true" ]; then
  exit 0;
fi

npm run test:ui
TEST_SUCCESS=$?
if [ ${TEST_SUCCESS} -ne 0 ]; then
  exit 1;
fi

npm test
TEST_SUCCESS=$?
if [ ${TEST_SUCCESS} -ne 0 ]; then
  exit 1;
fi

exit 0;
