#!/bin/bash
oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config
oc new-app quay.io/practicalopenshift/hello-world --name lab-dc --as-deployment-config -e MESSAGE="Lab 2 Message"

oc describe dc/lab-dc

oc status
echo "run the bellow command to see if it workd replacing <pod_hash> with the actual pod hash. You can use oc get pods to check it"
echo 'currdir=$(pwd);cd ..; cmd /c "oc.exe exec pods/lab-dc-1-<pod_hash> -- /bin/sh -c \"rm index.html 2>/dev/null;wget -q localhost:8080 && printf '\''\n\nindex.html content: >'\'' && cat index.html && printf '\''<\n\n'\'' && rm index.html\"";cd $currdir'


