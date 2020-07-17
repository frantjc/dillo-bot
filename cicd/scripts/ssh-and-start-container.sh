#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST \
    DISCORD_TOKEN=$DISCORD_TOKEN \
    GITHUB_TOKEN=$GITHUB_TOKEN \
    DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID \
    'bash -s' < dillo-bot/cicd/scripts/start-container.sh