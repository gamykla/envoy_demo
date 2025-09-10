#!/bin/bash

# Deploy to OpenShift
echo "Deploying to OpenShift..."

# Create namespace
oc apply -f openshift/namespace.yaml

# Apply ConfigMap and Secrets
oc apply -f openshift/configmap.yaml

# Deploy application
oc apply -f openshift/deployment.yaml

echo "Deployment complete!"

# Check deployment status
echo "Checking deployment status..."
oc get pods -n demo-app
oc get services -n demo-app
oc get routes -n demo-app
