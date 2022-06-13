#!/bin/bash
oc create -f pods/pod.yaml
oc expose --port 8080 pod/hello-world-pod
oc create -f pods/pod2.yaml
oc expose svc/hello-world-pod

./chacklist.sh