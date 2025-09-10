#!/bin/bash

# Build Spring Boot application
echo "Building Spring Boot application..."
cd spring-boot-app
mvn clean package -DskipTests
cd ..

# Build Docker images
echo "Building Docker images..."

# Build Spring Boot image
docker build -t demo-service:latest ./spring-boot-app

# Build Envoy image
docker build -t envoy-proxy:latest ./envoy

echo "Build complete!"

# Tag images for registry (update with your registry)
# docker tag demo-service:latest your-registry/demo-service:latest
# docker tag envoy-proxy:latest your-registry/envoy-proxy:latest

# Push to registry
# docker push your-registry/demo-service:latest
# docker push your-registry/envoy-proxy:latest
