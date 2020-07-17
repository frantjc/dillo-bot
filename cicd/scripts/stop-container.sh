#!/bin/bash

ssh key/dillo-key.pem $USER@$HOST <<- ENDSSH
    docker kill dillo_bot
ENDSSH