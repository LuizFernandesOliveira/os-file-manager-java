#!/bin/bash

# Install Docker
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg lsb-release -y
sudo mkdir -m 0755 -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo chmod a+r /etc/apt/keyrings/docker.gpg
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
sudo usermod -aG docker $USER
sudo chmod 666 /var/run/docker.sock

# Init Service
docker run \
  -d \
  -p 80:8080 \
  -e AWS_S3_BUCKET_NAME=--- \
  -e AWS_SECRET_KEY=------- \
  -e AWS_ACCESS_KEY=------- \
  --name=os-file-manager lfooficial/os-file-manager:latest

