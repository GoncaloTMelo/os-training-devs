#!/bin/bash
oc create -f lab-secret.yml
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config
oc set env dc/hello-world --from secret/lab-secret
oc expose svc/hello-world

echo "sleeping for 10 seconds so pods are changed"
sleep 10