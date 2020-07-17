#!/bin/bash

apt-get install ssh

ssh key/dillo-key.pem $USER@$HOST DISCORD_TOKEN=$DISCORD_TOKEN GITHUB_TOKEN=$GITHUB_TOKEN DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID <<- 'ENDSSH'
    sudo su

    docker run -e DISCORD_TOKEN=$DISCORD_TOKEN \
        -e GITHUB_TOKEN=$GITHUB_TOKEN \
        -e DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID frantjc/dillo-bot-image \
        -d --name dillo_bot frantjc/dillo-bot-image
ENDSSH