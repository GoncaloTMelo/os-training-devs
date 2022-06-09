#!/bin/bash
oc create -f pods/lab-pod.yaml

echo "sleeping for 5 seconds so pod is created"
sleep 5
#oc port-forward pod/lab-pod 8080
oc describe pod/lab-pod
cd ..
cmd /c "oc.exe exec pod/lab-pod -- /bin/sh -c \"wget -q localhost:8080 && printf '\n\nindex.html content: >' && cat index.html && printf '<\n\n' && rm index.html\""

oc delete pod lab-pod