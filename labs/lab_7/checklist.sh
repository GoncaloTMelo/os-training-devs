#!/bin/bash

# Checklist
echo "========================================================================================"
echo "======================================= CHECKLIST ======================================"
echo "========================================================================================"

route=$(oc get routes -l "app=hello-world" --no-headers | awk '{ print $2 }')
echo "Output from oc get secret contains your new Secret:"
oc get secret/lab-secret
echo 'Output from oc get -o yaml dc/hello-world contains the string "secretKeyRef"'
oc get -o yaml dc/hello-world
echo "When you run curl $route you get the value you put in the Secret"
curl -s $route