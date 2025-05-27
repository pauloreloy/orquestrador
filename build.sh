docker build -t paulodatarib/orquestrador .
docker tag paulodatarib/orquestrador:latest 338966484167.dkr.ecr.us-east-1.amazonaws.com/paulodatarib/orquestrador:latest
docker push 338966484167.dkr.ecr.us-east-1.amazonaws.com/paulodatarib/orquestrador:latest