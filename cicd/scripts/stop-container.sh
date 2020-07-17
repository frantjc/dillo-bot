#!/bin/sh

ssh -o "StrictHostKeyChecking no" -i key/dillo-key.pem $USER@$HOST <<- ENDSSH
    if [[ $(docker ispect dillo_bot | grep "Running" | grep true) ]]; then
        docker kill dillo_bot
    fi

    exit
ENDSSH