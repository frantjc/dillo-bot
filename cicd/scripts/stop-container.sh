#!/bin/sh

if [[ $(docker ps -a | grep dillo_bot) ]]; then
    if [[ $(docker inspect dillo_bot | grep "Running" | grep true) ]]; then
        docker kill dillo_bot

        docker rm dillo_bot
    fi
fi
