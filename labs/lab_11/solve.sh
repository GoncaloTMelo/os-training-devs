#!/bin/bash
APP_NAME=template-dc-labs
############### TEST
# oc new-app quay.io/practicalopenshift/hello-world --as-deployment-config
# oc create secret generic mysql-env --from-file ./env
# oc set env dc/hello-world --from secret/mysql-env
############### END TEST
# oc new-app golang~https://gitlab.com/practical-openshift/labs.git --context-dir mysql-go-reader --name mysql-go-reader --as-deployment-config
oc new-app mysql --as-deployment-config

oc create secret generic mysql-env --from-file ./env
# oc create secret generic mysql-user --from-literal MYSQL_USER="root"
# oc create secret generic mysql-password --from-literal MYSQL_PASSWORD="toor"
# oc create secret generic mysql-database --from-literal MYSQL_DATABASE="root_toor"



# oc create configmap mysql-user --from-literal MYSQL_USER="root"
# oc create configmap mysql-password --from-literal MYSQL_PASSWORD="toor"
# oc create configmap mysql-database --from-literal MYSQL_DATABASE="root_toor"

# oc get -o yaml secret/mysql-env
# oc set env dc/mysql-go-reader --from secret/mysql-env
oc set env dc/mysql --from secret/mysql-env
# oc set env dc/mysql --from secret/mysql-user
# oc set env dc/mysql --from secret/mysql-password
# oc set env dc/mysql --from secret/mysql-database


# oc set env dc/mysql --from cm/mysql-user
# oc set env dc/mysql --from cm/mysql-password
# oc set env dc/mysql --from cm/mysql-database



#sleep 10