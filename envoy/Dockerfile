FROM envoyproxy/envoy:v1.28-latest

COPY envoy.yaml /etc/envoy/envoy.yaml

# Install envsubst for environment variable substitution
RUN apt-get update && apt-get install -y gettext-base && rm -rf /var/lib/apt/lists/*

# Create startup script that substitutes environment variables
RUN echo '#!/bin/bash\n\
envsubst < /etc/envoy/envoy.yaml > /tmp/envoy.yaml\n\
/usr/local/bin/envoy -c /tmp/envoy.yaml --service-cluster demo-envoy --service-node demo-envoy-node\n\
' > /startup.sh && chmod +x /startup.sh

EXPOSE 8080 8082 9901

CMD ["/startup.sh"]
