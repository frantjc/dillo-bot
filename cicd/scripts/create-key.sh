#!/bin/sh

PREFIX_COLOR='\033[0;32m'
NORMAL_COLOR='\033[0m'
ECHO_PREFIX="[${PREFIX_COLOR}PIPELINE${NORMAL_COLOR}]"

echo "${ECHO_PREFIX} creating key file..."
echo "${ECHO_PREFIX} $KEY" > key/dillo-key.pem

chmod 400 key/dillo-key.pem

echo "${ECHO_PREFIX} created"

