#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."

# Use the dedicated demo cluster so another Kubernetes context is never changed.
kind load docker-image unit-converter:1.0 --name converter-demo
kubectl --context kind-converter-demo apply -f k8s/namespace.yaml
kubectl --context kind-converter-demo -n converter-demo apply -f k8s/
kubectl --context kind-converter-demo -n converter-demo rollout status deployment/unit-converter --timeout=180s
kubectl --context kind-converter-demo -n converter-demo get pods -l app=unit-converter
kubectl --context kind-converter-demo -n converter-demo get service unit-converter
