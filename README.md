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

```bash
producer
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

no-format

```
  create stream game_users (user Varchar) WITH(kafka_topic='game-opinions-1', value_format='json');
```

with-format

```
create stream game_users_format AS SELECT EXTRACTJSONFIELD(user, '$.source.type') AS source_type, EXTRACTJSONFIELD(user, '$.name') as user_name, EXTRACTJSONFIELD(user, '$.age') AS user_age FROM game_users;
```

```
SELECT EXTRACTJSONFIELD(user, '$.source.type') AS source_type, EXTRACTJSONFIELD(user, '$.name') as user_name, EXTRACTJSONFIELD(user, '$.age') AS age FROM game_users;

SELECT COUNT(EXTRACTJSONFIELD(user, '$.source.type')) AS count_source_type, EXTRACTJSONFIELD(user, '$.source.type') AS source_type FROM game_users_raw GROUP BY EXTRACTJSONFIELD(user, '$.source.type');
```

```
CREATE TABLE brand_metrics WITH(
    kafka_topic='brand_metrics_count'
) 
AS
 SELECT 
    count(EXTRACTJSONFIELD(fields, '$.brand_name')) AS COUNT_BRAND_ID, 
    EXTRACTJSONFIELD(fields, '$.brand_name') AS BRAND_NAME 
 FROM matched_elements_good_2 
GROUP BY 
  EXTRACTJSONFIELD(fields, '$.brand_name');
```

```
SELECT count_brand_id, brand_name FROM brand_metrics;
```
