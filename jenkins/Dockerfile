FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y curl unzip && curl -fsSL https://github.com/jenkinsci/plugin-installation-manager-tool/releases/download/2.13.2/jenkins-plugin-manager-2.13.2.jar -o /usr/lib/jenkins-plugin-manager.jar && chmod 755 /usr/lib/jenkins-plugin-manager.jar

USER jenkins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Use the plugin installation manager tool
RUN java -jar /usr/lib/jenkins-plugin-manager.jar --plugin-file /usr/share/jenkins/ref/plugins.txt --plugin-download-directory /usr/share/jenkins/ref/plugins