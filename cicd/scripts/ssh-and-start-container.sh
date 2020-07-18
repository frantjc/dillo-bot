#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST < dillo-bot/cicd/scripts/start-container.sh $GITHUB_TOKEN $DISCORD_TOKEN $DISCORD_CLIENT_ID