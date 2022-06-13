#!/bin/bash
oc delete secret/volumes-lab
oc delete all -l app=volumes-lab