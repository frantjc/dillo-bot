#!/bin/sh

if [[ $(docker ispect dillo_bot | grep "Running" | grep true) ]]; then
    docker kill dillo_bot
fi

exit