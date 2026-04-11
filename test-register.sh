#!/usr/bin/env bash
# Test POST /register endpoint
# Usage: ./test-register.sh [BASE_URL]
#   BASE_URL defaults to http://localhost:9000

BASE_URL="${1:-http://localhost:9000}"

curl -s -w "\n\nHTTP Status: %{http_code}\n" \
  -X POST "${BASE_URL}/register" \
  -H "Content-Type: application/json" \
  -d '{
    "iss": "test-tpp",
    "iat": 1712700000,
    "exp": 1712703600,
    "aud": "token-server-demo",
    "jti": "test-jti-001",
    "redirect_uris": ["https://tpp.example.com/callback"],
    "token_endpoint_auth_method": "client_secret_basic",
    "grant_types": ["authorization_code", "refresh_token"],
    "response_types": ["code"],
    "software_id": "test-software-001",
    "scope": "openid accounts payments",
    "software_statement": "eyJhbGciOiJub25lIn0.eyJzb2Z0d2FyZV9pZCI6InRlc3Qtc29mdHdhcmUtMDAxIn0.",
    "application_type": "web",
    "id_token_signed_response_alg": "RS256",
    "request_object_signing_alg": "RS256",
    "token_endpoint_auth_signing_alg": "RS256"
  }'
