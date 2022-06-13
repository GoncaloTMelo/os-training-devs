#!/bin/bash
oc create secret generic volumes-lab --from-file=./pods
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config --name volumes-lab

oc set volume dc/volumes-lab --add --secret-name volumes-lab --mount-path secret-volume/
sleep 10