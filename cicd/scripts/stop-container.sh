#!/bin/bash

if [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot)" == "true" ]]; then
    docker kill dillo_bot
fi

if [[ "$(docker inspect -f '{{.State.Running}}' dillo_bot)" == "false"  ]]; then
    docker rm dillo_bot
fi
