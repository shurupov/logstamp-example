# Logstamp java library usage example

Current project is an example usage of [logstamp library](https://github.com/shurupov/logstamp).

**Contents**

- [Project components](#project-components)
  - [docker-compose.yml](#docker-composeyml-)
  - [api](#api)
  - [main-service](#main-service)
  - [consumer-service](#consumer-service)
  - [client-adapter](#client-adapter)
  - [delivery-adapter](#delivery-adapter)
  - [external-system](#external-system)

## Project components

### docker-compose.yml 

[docker-compose](docker-compose.yml) contains docker containers used by developed applications
- elasticsearch, logstash, kibana (ELK)
- zookeeper, kafka, kafka-ui

ELK configurations are in [elk](elk) folder

### api
[api](api) of rest and kafka communications

### main-service

[main-service](main-service) starts process, creates `claimId` stamp, performs rest interactions, sends kafka message, creates and ans executes scheduled jobs. Every outcoming interaction passes stamps to next service. Writes logs to [ELK](#docker-composeyml-)

### consumer-service

[consumer-service](consumer-service) listens kafka and handles message. Extracts stamp and pushes to log. Writes logs to [ELK](#docker-composeyml-)

### client-adapter

[client-adapter](client-adapter) is adapter to [external client system](#external-system). It gets rest request from [main-service](#main-service) and makes logs with all passed [stamps](https://github.com/shurupov/logstamp). Writes logs to [ELK](#docker-composeyml-)

### delivery-adapter

[delivery-adapter](delivery-adapter) is adapter to [external delivery system](#external-system). It gets rest request from [main-service](#main-service) and makes logs with all passed [stamps](https://github.com/shurupov/logstamp). Also, it gets callback from delivery system, extract stamp with custom extractor and pushes cross-process stamp to log. Writes logs to [ELK](#docker-composeyml-)

### external-system

[external-system](external-system) mocks real external system. Doesn't write logs to [ELK](#docker-composeyml-). Doesn't extract, push and pass stamps to log