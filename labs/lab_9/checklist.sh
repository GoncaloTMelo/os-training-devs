#!/bin/bash

# Checklist
echo "========================================================================================"
echo "======================================= CHECKLIST ======================================"
echo "========================================================================================"
APP_NAME=advanced-dc-labs
# route=$(oc get routes -l "app=$APP_NAME" --no-headers | awk '{ print $2 }')
# running_pod=$(oc get pods | grep $APP_NAME | grep -v "deploy" | awk '{ print $1 }')

echo 'oc get events output contains your message from step 4: '
oc get events |grep $APP_NAME | grep hook
echo 'oc describe dc/hello-world shows the Recreate strategy and hook'
oc describe dc/$APP_NAME | grep Strategy