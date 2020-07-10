#!/bin/bash

cd target
if [ $? -eq 0 ]; then
    pwd
    ls -al
else
    echo '> directory "target" not found'
    exit 1
fi