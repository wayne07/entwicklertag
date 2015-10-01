#!/usr/bin/env bash
docker run -it -p 9990:9990 -p 8080:8080 jboss/wildfly821-admin /opt/jboss/wildfly/bin/domain.sh -b 0.0.0.0 -bmanagement 0.0.0.0

#docker run --name domain1 -h dockerdomain1 -p 9990:9990 -p 8080:8080 jboss/wildfly821-admin /opt/jboss/wildfly/bin/domain.sh -b 0.0.0.0 -bmanagement 0.0.0.0

#docker run -d --name domain1 -h dockerdomain1 -p 9990:9990 -p 8080:8080 jboss/wildfly821-admin /opt/jboss/wildfly/bin/domain.sh -b 0.0.0.0 -bmanagement 0.0.0.0