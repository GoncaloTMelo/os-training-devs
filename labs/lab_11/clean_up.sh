#!/bin/bash
APP_NAME=template-dc-labs
oc delete all --all
oc delete secret/mysql-env
oc delete secret/mysql-user
oc delete secret/mysql-password
oc delete secret/mysql-database

oc delete configmap/mysql-user
oc delete configmap/mysql-password
oc delete configmap/mysql-database
#oc delete all -l app=a$APP_NAME