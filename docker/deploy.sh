#!/bin/bash
set -x

docker rmi m2:latest
docker build -t m2:latest -f docker/Dockerfile .
docker run --rm -it m2:latest bash