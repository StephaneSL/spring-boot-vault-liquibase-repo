#!/bin/bash
# Vault setup script for local dev
export VAULT_ADDR='http://127.0.0.1:8200'
export VAULT_TOKEN='root'

echo "Enabling KV secrets engine..."
vault secrets enable -path=secret kv-v2 || true

echo "Writing database credentials to Vault..."
vault kv put secret/myapp/config db_username=postgres db_password=postgres

echo "Reading secrets from Vault..."
vault kv get secret/myapp/config
