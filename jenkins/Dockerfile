FROM jenkins/jenkins:lts-jdk17

USER root
RUN apt-get update && apt-get install -y --no-install-recommends \
    docker.io maven git curl unzip jq gnupg2 \
    && rm -rf /var/lib/apt/lists/*

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-manager --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY jenkins.yaml /var/jenkins_home/casc_configs/jenkins.yaml
COPY init.groovy.d/ /var/jenkins_home/init.groovy.d/

RUN chown -R jenkins:jenkins /var/jenkins_home && chmod -R 755 /var/jenkins_home
USER jenkins
