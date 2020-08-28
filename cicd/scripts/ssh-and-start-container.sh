#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST 'bash -s' < dillo-bot/cicd/scripts/start-container.sh $GITHUB_TOKEN $DISCORD_TOKEN $DISCORD_CLIENT_ID $DB_USER $DB_PASSWORD $DB_URI $DB_DRIVER

exit $?;
