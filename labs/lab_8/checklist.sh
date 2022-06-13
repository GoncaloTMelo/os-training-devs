#!/bin/bash

# Checklist
echo "========================================================================================"
echo "======================================= CHECKLIST ======================================"
echo "========================================================================================"
APP_NAME=volumes-lab
route=$(oc get routes -l "app=$APP_NAME" --no-headers | awk '{ print $2 }')
running_pod=$(oc get pods | grep $APP_NAME | grep -v "deploy" | awk '{ print $1 }')

cd ..
cmd /c "oc.exe exec pod/$running_pod -- /bin/sh -c \"ls -l /secret-volume ; echo 'cat /secret-volume/pod.yaml:'; cat /secret-volume/pod.yaml; echo 'cat /secret-volume/pod2.yaml:'; cat /secret-volume/pod2.yaml; echo 'cat /secret-volume/service.yaml:'; cat /secret-volume/service.yaml \""