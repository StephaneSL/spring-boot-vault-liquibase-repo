FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y curl unzip

USER jenkins

# Copy your plugins list
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Use the plugin installation manager tool
RUN java -jar /usr/lib/jenkins-plugin-manager.jar \
    --plugin-file /usr/share/jenkins/ref/plugins.txt \
    --plugin-download-directory /usr/share/jenkins/ref/plugins