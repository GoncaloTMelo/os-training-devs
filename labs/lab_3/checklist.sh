#!/bin/bash

# Checklist
echo "Output from oc get pods contains two pods: "
oc get pods
echo "Output from oc status groups the two pods under the same route:"
oc status
route=$(oc status | grep -oEi "(^http://hello-world-pod\S+)")
times=20
echo "When you run curl $route several times, it will return messages from both pod.yaml and pod2.yaml:"
echo "curling $times times."
for i in {0..20}
do
   echo "$i: $(curl -s $route)"
done