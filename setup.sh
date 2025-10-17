#!/usr/bin/env bash
set -euo pipefail

echo "==> Checking prerequisites..."
if ! command -v docker >/dev/null 2>&1; then
  echo "Docker not found. Install Docker Desktop for Mac and start it."
  exit 1
fi

echo "==> Building app (maven if available)..."
if command -v mvn >/dev/null 2>&1; then
  mvn -B -DskipTests package || true
else
  echo "Maven not found locally; Dockerfile will build the app image."
fi

echo "==> Starting docker-compose stack..."
docker compose up -d --build

echo "==> Waiting for postgres to be healthy..."
until [ "$(docker inspect --format='{{.State.Health.Status}}' postgres-db 2>/dev/null)" = "healthy" ]; do
  echo "Waiting for postgres..."
  sleep 3
done

echo "==> Waiting for vault to be ready..."
until curl -sS --fail http://127.0.0.1:8200/v1/sys/health >/dev/null 2>&1; do
  echo "Waiting for vault..."
  sleep 3
done

echo "==> Writing sample secrets to Vault..."
curl --header "X-Vault-Token: root" --request POST --data '{"data":{"db_username":"postgres","db_password":"postgres"}}' http://127.0.0.1:8200/v1/secret/data/myapp/config || true

echo "==> Waiting for Jenkins to be ready..."
until curl -sS --fail http://127.0.0.1:9090/login >/dev/null 2>&1; do
  echo "Waiting for Jenkins..."
  sleep 5
done

echo "==> Setup complete!"
echo "App: http://localhost:8080"
echo "Vault: http://localhost:8200 (token: root)"
echo "Jenkins: http://localhost:9090 (user: admin, password: admin)"
