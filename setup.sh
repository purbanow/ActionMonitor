#!/bin/bash

docker-compose up

# TODO workaroud for too early starting of action-monitor, check docker-compose.yml
docker build -t action-monitor ActionMonitorApp/
docker run -itd --network=app-network -p 8090:8090 action-monitor