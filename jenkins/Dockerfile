FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y git
USER jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
