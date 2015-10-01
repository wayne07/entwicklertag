# Use latest jboss/base-jdk:7 image as the base
FROM jboss/wildfly821-admin:latest

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 8.2.1.Final
ENV WILDFLY_SHA1 abe037d5d1cb97b4d07fbfe59b6a1345a39a9ae5
ENV JBOSS_HOME /opt/jboss/wildfly

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

# Expose the ports we're interested in
EXPOSE 8080

# Set the default command to run on boot
# This will boot WildFly in the domain mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/domain.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

