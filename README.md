# POC ElasticSearch Sink Connector

## Overview

This repository contains a Proof of Concept (POC) for integrating Kafka with Elasticsearch using the Elasticsearch Sink 
Connector. The aim of this project is to demonstrate the integration capabilities between Kafka, Kafka Connect, and 
Elasticsearch for real-time data indexing.

## Tech specifications
- **Kotlin**: Programming language used for the project.
- **Spring boot**: Facilitates the setup of Kafka and provides a streamlined environment for running the project.
- **Kafka + Zookeeper + Kafka ui + UI**: `docker-compose-connect.yaml` [Source](https://github.com/lensesio/fast-data-dev)
- **Kafka + Zookeeper + Kafka Connect(plenty of connectors available) + UI**: `docker-compose-connect.yaml` [Source](https://github.com/lensesio/fast-data-dev)
- **Elastic search**: `docker-compose-connect.yaml`
- **Code Inspector and Formatter**: Integrated `spotless` for code formatting and inspection.
- **ElasticSearch Sink Connector**: [Source](https://www.confluent.io/hub/confluentinc/kafka-connect-elasticsearch)
- **ElasticSearch Client**: [Source](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/getting-started-java.html)


## Note

This project emphasizes that it is solely a Proof of Concept (POC). The choice of programming languages, particularly 
Spring Boot and Kotlin, was made for expediency and ease of Kafka setup. The main focus lies in testing the integration 
of Kafka messages with Elasticsearch through the Kafka Connect Elasticsearch Sink Connector.

Feel free to explore, test, and adapt the codebase for your requirements.


---

### Spring boot reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/gradle-plugin/reference/html/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#actuator)
* [Validation](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#io.validation)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#messaging.kafka)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


