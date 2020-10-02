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

ENVIRONMENT_SUCCESS=0

DILLO_BOT_CONTAINER="dillo_bot"

echo -e "${INFO_PREFIX} getting environment..."
LOWERCASED_ENV="$(echo "$ENV" | tr '[A-Z]' '[a-z]')"
ENVIRONMENT_SUCCESS=$?
if [ "${LOWERCASED_ENV}" = "" ]; then
  ENVIRONMENT_SUCCESS=1
fi
if [ ${ENVIRONMENT_SUCCESS} -ne 0 ]; then
  echo -e "${FAIL_PREFIX} unable to find environment from ENV"
  echo -e "${INFO_PREFIX} assuming environment is prod"
else
  echo -e "${SUCCESS_PREFIX} environment found: ${LOWERCASED_ENV}"
  DILLO_BOT_CONTAINER="${DILLO_BOT_CONTAINER}_d"
fi

if [[ "$(docker inspect -f '{{.State.Running}}' ${DILLO_BOT_CONTAINER})" == "true" ]]; then
  docker kill ${DILLO_BOT_CONTAINER}
fi

if [[ "$(docker inspect -f '{{.State.Running}}' ${DILLO_BOT_CONTAINER})" == "false"  ]]; then
  docker rm ${DILLO_BOT_CONTAINER}
fi
