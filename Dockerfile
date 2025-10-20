# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/vault-liquibase-demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT exec bash -c '\echo "Fetching secrets from Vault...";
  export DB_USERNAME=$(vault kv get -field=db_username secret/myapp) &&
  export DB_PASSWORD=$(vault kv get -field=db_password secret/myapp) &&
  echo "Starting Spring Boot app...";exec java -jar /app/app.jar'