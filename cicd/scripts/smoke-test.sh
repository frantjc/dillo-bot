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

SMOKE_SUCCESS=0

pwd
ls -al
echo ""

API=${URL}

if [ "${API: -4}" != "/api" ]; then
  API="${API}/api"
fi

echo -e "${INFO_PREFIX} curling ${API}/users/discord ..."
curl --fail -I ${API}/users/discord
SMOKE_SUCCESS=$?
echo ""

if [ $SMOKE_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} curl ${API}/users/discord failed"
  exit 1;
fi

echo -e "${INFO_PREFIX} curling ${API}/users/github ..."
curl --fail -I ${API}/users/github
SMOKE_SUCCESS=$?
echo ""

if [ $SMOKE_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} curl ${API}/users/github failed"
  exit 1;
fi

echo -e "${INFO_PREFIX} curling ${API}/channels ..."
curl --fail -I ${API}/channels
SMOKE_SUCCESS=$?
echo ""

if [ $SMOKE_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} curl ${API}/channels failed"
  exit 1;
fi

echo -e "${INFO_PREFIX} curling ${API}/servers ..."
curl --fail -I ${API}/servers
SMOKE_SUCCESS=$?
echo ""

if [ $SMOKE_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} curl ${API}/servers failed"
  exit 1;
fi

echo -e "${INFO_PREFIX} curling ${API: -3} ..."
curl --fail -I ${API: -3}
SMOKE_SUCCESS=$?
echo ""

if [ $SMOKE_SUCCESS -ne 0 ]; then
  echo -e "${FAIL_PREFIX} curl ${API: -3} failed"
  exit 1;
fi

echo -e "${SUCCESS_PREFIX} smoke tests passed"

exit SMOKE_SUCCESS;
