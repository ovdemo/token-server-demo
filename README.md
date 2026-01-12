# token-server-demo

An OAuth2 Authorization Server built with Spring Boot and Spring Security OAuth2.

## Features

- OAuth2 Authorization Server with token generation
- OAuth2 Token Introspection endpoint
- OpenID Connect Discovery endpoint (.well-known/openid-configuration)
- JWKS endpoint for public keys
- Actuator health endpoints
- Dockerized for easy deployment
- Skaffold configuration for Kubernetes development

## Prerequisites

- Java 21
- Maven 3.6+
- Docker (for containerization)
- Kubernetes (for local development with Skaffold)

## Building

```bash
JAVA_HOME=/path/to/java21 mvn clean package
```

## Running Locally

```bash
JAVA_HOME=/path/to/java21 mvn spring-boot:run
```

The server will start on port 9000.

## Endpoints

- **Authorization Endpoint**: `POST /oauth2/token`
- **Token Introspection**: `POST /oauth2/introspect`
- **JWKS**: `GET /oauth2/jwks`
- **OpenID Configuration**: `GET /.well-known/openid-configuration`
- **Health**: `GET /actuator/health`

## Default Credentials

**Client:**
- Client ID: `client`
- Client Secret: `secret`

**User:**
- Username: `user`
- Password: `password`

## Docker

Build the Docker image:
```bash
docker build -t token-server-demo .
```

Run the container:
```bash
docker run -p 9000:9000 token-server-demo
```

## Kubernetes Development

Use Skaffold for local Kubernetes development:
```bash
skaffold dev
```

## Testing

Run the tests:
```bash
JAVA_HOME=/path/to/java21 mvn test
```

