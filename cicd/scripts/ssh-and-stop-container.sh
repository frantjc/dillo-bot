#!/bin/bash

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem ${USER}@${HOST} 'bash -s' < dillo-bot/cicd/scripts/stop-container.sh ${ENV}

exit $?;
