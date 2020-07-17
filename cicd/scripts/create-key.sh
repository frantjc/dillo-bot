#!/bin/bash

echo "creating key file..."
echo "$KEY" > key/dillo-key.pem

chmod 400 key/dillo-key.pem

echo "created"
