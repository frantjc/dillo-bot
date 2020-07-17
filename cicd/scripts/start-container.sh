#!/bin/sh

sudo su

if [[ $(docker ps -a | grep dillo_bot) ]]; then
    if [[ $(docker inspect dillo_bot | grep "Running" | grep true) ]]; then
        docker kill dillo_bot
        docker rm dillo_bot
    elif  [[ $(docker inspect dillo_bot | grep "Running" | grep false) ]]; then
        docker rm dillo_bot
    fi
fi

docker pull frantjc/dillo-bot-image

docker run -e DISCORD_TOKEN=$DISCORD_TOKEN \
    -e GITHUB_TOKEN=$GITHUB_TOKEN \
    -e DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID \
    -d --name dillo_bot frantjc/dillo-bot-image
