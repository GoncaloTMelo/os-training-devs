#!/bin/bash
oc new-build https://gitlab.com/GTMelo7/openshift-tutorial#builds-lab --context-dir=hello-world-go
oc set build-hook bc/builds-lab --post-commit --script='echo "MESSAGE=$MESSAGE"'
oc start-build bc/builds-lab
oc logs bc/builds-lab -f