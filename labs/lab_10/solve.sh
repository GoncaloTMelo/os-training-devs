#!/bin/bash
APP_NAME=advanced-dc-labs
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config --name $APP_NAME
oc expose svc/$APP_NAME
echo "Sleeping for 10 seconds..."
sleep 10
oc set probe dc/$APP_NAME --readiness --open-tcp=8081
route=$(oc get routes -l "app=$APP_NAME" --no-headers | awk '{ print $2 }')
curl $route



#sleep 10