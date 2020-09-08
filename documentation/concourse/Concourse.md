# [Concourse](https://concourse-ci.org/)

This has a pretty high learning curve so I've done my best to abstract it away from development: you shouldn't need to know anything about this.

Essentially, Concourse is the thing that takes your code and makes it connect to DilloBot after it has been merged into the master branch on GitHub.  All of the code that interacts with DilloBot's instance of Concourse is in [`cicd/`](../../cicd) if you are interested.

## [Fly](https://concourse-ci.org/fly.html)

The thing you use to interact with Concourse via the command line.
