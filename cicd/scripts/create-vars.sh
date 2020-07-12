#!/bin/bash

cat << EOF > vars/set_pipeline-vars.yml
concourse:
        discord:
                token: ((concourse.discord.token))
                channel: ((concourse.discord.channel))
discord:
        token: ((discord.token))
        client:
                id: ((discord.client.id))
github:
        uri: ((github.uri))
        branch: ((github.branch))
        token: ((github.token))
docker:
        email: ((docker.email))
        username: ((docker.username))
        password: ((docker.password))
EOF

cat vars/set_pipeline-vars.yml