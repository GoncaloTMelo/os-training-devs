#!/bin/bash

# Checklist
echo "========================================================================================"
echo "======================================= CHECKLIST ======================================"
echo "========================================================================================"
APP_NAME=template-dc-labs
# route=$(oc get routes -l "app=$APP_NAME" --no-headers | awk '{ print $2 }')
# running_pod=$(oc get pods | grep $APP_NAME | grep -v "deploy" | awk '{ print $1 }')