#!/bin/sh

if [[ $(docker inspect dillo_bot | grep "Running" | grep true) ]]; then
    docker kill dillo_bot
fi

exit