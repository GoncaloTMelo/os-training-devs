#!/bin/bash
oc new-build python~https://gitlab.com/GTMelo7/openshift-tutorial.git --context-dir=s2i/python --name i2a-lab
oc logs -f bc/i2a-lab
oc expose svc/i2a-lab