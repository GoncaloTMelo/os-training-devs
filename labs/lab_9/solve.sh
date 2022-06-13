#!/bin/bash
APP_NAME=advanced-dc-labs
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config --name $APP_NAME
oc edit dc/$APP_NAME
oc describe dc/$APP_NAME | grep Strategy
cd ..
cmd /c "oc set deployment-hook dc/$APP_NAME --mid -c $APP_NAME -- /bin/echo Hello from mid-Deployment hook"
oc rollout latest dc/$APP_NAME

#sleep 10