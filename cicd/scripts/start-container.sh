#!/bin/sh

sudo su

if [[ $(docker ispect dillo_bot | grep "Running" | grep false) ]]; then
    docker run -e DISCORD_TOKEN=$DISCORD_TOKEN \
        -e GITHUB_TOKEN=$GITHUB_TOKEN \
        -e DISCORD_CLIENT_ID=$DISCORD_CLIENT_ID frantjc/dillo-bot-image \
        -d --name dillo_bot frantjc/dillo-bot-image
fi

exit