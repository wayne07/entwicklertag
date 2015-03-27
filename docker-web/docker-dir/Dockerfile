FROM ubuntu:15.04
MAINTAINER Jürgen Seliger <js@idos.de>
RUN apt-get update && apt-get -y install wget curl
WORKDIR /opt/java
RUN pwd \
    ls -lha
#COPY jdk1.7.0_60 /java/jdk1.7.0_60/
COPY jdk-8u40-linux-x64.tar.gz /opt/java/
RUN tar -xzvf /opt/java/jdk-8u40-linux-x64.tar.gz \
    rm /opt/java/jdk-8u40-linux-x64.tar.gz

