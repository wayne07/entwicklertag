FROM jenkins/jenkins:2.79-alpine
MAINTAINER juergen.seliger@gmail.com

# if we want to install via apt
USER root

ENV VERSION master

WORKDIR /opt

RUN cd /opt \
	&& git clone -b ${VERSION} --single-branch git://git.openstack.org/openstack-infra/jenkins-job-builder \
	&& cd /opt/jenkins-job-builder \
	&& pip install -r requirements.txt \
	&& python setup.py install \
	&& rm -R /opt/jenkins-job-builder

ENTRYPOINT ["/usr/local/bin/jenkins-jobs"]


RUN apt-get update && apt-get install -y ruby
##make more-thing-here
# drop back to the regular jenkins user - good practice

USER jenkins
