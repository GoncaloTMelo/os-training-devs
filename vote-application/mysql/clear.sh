#/bin/bash
oc delete all -l app=mysql
oc delete secret mysql-env