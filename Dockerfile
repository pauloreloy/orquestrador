# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# ---- Final Stage ----
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Install required packages
RUN apt-get update && \
    apt-get install -y curl wget net-tools lsof supervisor && \
    apt-get clean

# Install Confluent Platform (Zookeeper, Kafka, Schema Registry)
ENV CONFLUENT_VERSION=7.5.0
RUN wget https://packages.confluent.io/archive/7.5/confluent-community-7.5.0.tar.gz && \
    tar -xzf confluent-community-7.5.0.tar.gz && \
    mv confluent-* /opt/confluent && \
    rm confluent-community-7.5.0.tar.gz

ENV PATH="$PATH:/opt/confluent/bin"

# Copy the Spring Boot JAR
COPY --from=builder /app/target/*.jar /app/app.jar

# Supervisor configuration
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

EXPOSE 8080 2181 9092 29092 8081

CMD ["/usr/bin/supervisord"]
