#!/bin/bash

# Checklist
echo "========================================================================================"
echo "======================================= CHECKLIST ======================================"
echo "========================================================================================"
echo "Output from oc get cm contains your new ConfigMap: "
oc get cm
echo 'Output from oc get -o yaml dc/hello-world contains the string "configMapKeyRef": '
oc get -o yaml dc/hello-world
route=$(oc get routes -l "app=hello-world" --no-headers | awk '{ print $2 }')
echo "When you run curl $route you get the value you put in the ConfigMap:"
curl -s $route