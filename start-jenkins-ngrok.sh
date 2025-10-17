#!/bin/bash
# Start Jenkins and ngrok automatically via docker compose

docker compose up -d jenkins
sleep 8

if ! grep -q "authtoken" ~/.config/ngrok/ngrok.yml 2>/dev/null; then
  echo "Please configure ngrok authtoken first: ngrok config add-authtoken <YOUR_AUTHTOKEN>"
  exit 1
fi

pkill ngrok 2>/dev/null || true
nohup ngrok http 9090 > ~/ngrok.log 2>&1 &
echo "ngrok started; check ~/ngrok.log for the public URL"
