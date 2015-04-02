FROM ubuntu:15.04
MAINTAINER Jürgen Seliger <juergen.seliger@gmail.com>

RUN apt-get update && apt-get -y install wget curl

ENV JDK_VERSION 7u75
ENV JDK_BUILD b13
ENV JAVA_FILE jdk-$JDK_VERSION-linux-x64.tar.gz

WORKDIR /opt/java

RUN cd /tmp && \
    wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/$JDK_VERSION-$JDK_BUILD/$JAVA_FILE && \
    tar -xzvf $JAVA_FILE && \
    rm -f /tmp/$JAVA_FILE && \
    mv /tmp/jdk1.7.0_75 /opt/java
