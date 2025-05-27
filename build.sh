aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 338966484167.dkr.ecr.us-east-1.amazonaws.com
docker tag paulodatarib/orquestrador:latest 338966484167.dkr.ecr.us-east-1.amazonaws.com/paulodatarib/orquestrador:latest
docker push 338966484167.dkr.ecr.us-east-1.amazonaws.com/paulodatarib/orquestrador:latest