#!/bin/sh
docker run -d -p 1521:1521 -e ORACLE_PASSWORD=SysPassword1 -v oracle-volume:/opt/oracle/XE21CFULL/oradata gvenzl/oracle-xe:21-full