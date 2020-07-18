#!/bin/sh

if [[ $(docker ps -a | grep dillo_bot) ]]; then
    if [[ $(docker inspect dillo_bot | grep "Running" | grep true) ]]; then
        docker kill dillo_bot
        docker rm dillo_bot
    elif  [[ $(docker inspect dillo_bot | grep "Running" | grep false) ]]; then
        docker rm dillo_bot
    fi
fi

docker pull frantjc/dillo-bot-image

docker run -e GITHUB_TOKEN=$1 -e DISCORD_TOKEN=$2 -e DISCORD_CLIENT_ID=$3 -d --name dillo_bot frantjc/dillo-bot-image
