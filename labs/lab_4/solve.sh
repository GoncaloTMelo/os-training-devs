#!/bin/bash
oc create -f lab-configmap.yml
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config
oc set env dc/hello-world --from cm/lab-map
oc expose svc/hello-world

echo "sleeping for 10 seconds so pods are changed"
sleep 10