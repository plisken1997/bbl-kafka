# Kafka BBL

## monitoring:
  - https://github.com/framiere/monitoring-demo
  - https://github.com/yahoo/kafka-manager
### kafka manager
[configure](https://github.com/yahoo/kafka-manager#configuration) and [deploy](https://github.com/yahoo/kafka-manager#deployment)

  run
  ```bash
  
    bin/kafka-manager -Dconfig.file=/path/to/conf/application.conf
  ```

## install

### from docker
https://docs.confluent.io/current/installation/docker/docs/quickstart.html

### from confluent applications
https://docs.confluent.io/current/installation/installing_cp/index.html

## CLI 

run-broker
```bash
  bin/kafka-server-start etc/kafka/server-1.properties
```

list topic

```bash
  bin/kafka-topics --list --zookeeper localhost:2181
```

producer

```bash
  bin/kafka-console-producer --broker-list "localhost:9092" --topic "bbl-kafka"  
```

consumer

```bash
  bin/kafka-console-consumer --topic "bbl-kafka" --bootstrap-server localhost:9092
```

## KSQL

offset

```
  SET 'auto.offset.reset' = 'earliest';
```

### k-streams
no-format

```
create stream game_users (user Varchar) WITH(kafka_topic='game-opinions-1', value_format='json');

SELECT 
    EXTRACTJSONFIELD(user, '$.source.type') AS source_type, 
    EXTRACTJSONFIELD(user, '$.name') as user_name, 
    EXTRACTJSONFIELD(user, '$.age') AS age 
FROM 
    game_users;
```

with-format

```
create stream game_users_format AS SELECT EXTRACTJSONFIELD(user, '$.source.type') AS source_type, EXTRACTJSONFIELD(user, '$.name') as user_name, EXTRACTJSONFIELD(user, '$.age') AS user_age FROM game_users;
```

```
SELECT 
    source_type, 
    user_name, 
    age 
FROM 
    game_users_format;
```

### k-table

```
CREATE TABLE game_user_sources WITH(
    kafka_topic='game_user_sources'
) 
AS
SELECT 
    source_type, 
    COUNT(source_type) AS count_sources
FROM 
    game_users_format
GROUP BY
    source_type
```

```
SELECT source_type, count_sources FROM game_user_sources;
```
