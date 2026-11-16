#!/usr/bin/env bash
set -euo pipefail

for replicas in 3 5 3; do
    echo "Scaling unit-converter to $replicas replicas"
    kubectl --context kind-converter-demo -n converter-demo scale deployment/unit-converter --replicas="$replicas"
    kubectl --context kind-converter-demo -n converter-demo rollout status deployment/unit-converter --timeout=180s
    kubectl --context kind-converter-demo -n converter-demo wait deployment/unit-converter \
        --for="jsonpath={.status.replicas}=$replicas" --timeout=180s
    kubectl --context kind-converter-demo -n converter-demo get pods -l app=unit-converter
done
