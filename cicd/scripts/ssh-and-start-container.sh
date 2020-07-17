#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST \
    "export DISCORD_TOKEN=$DISCORD_TOKEN; export GITHUB_TOKEN=$GITHUB_TOKEN; export DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID; bash -s" < dillo-bot/cicd/scripts/start-container.sh
