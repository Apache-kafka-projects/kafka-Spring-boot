# kafka-Spring-boot

## Execute Kafka
```bash
docker-compose up -d
```
## Get kafka container id
```
docker ps | grep kafka-container | awk '{print $1}'
```

## Create topic into Kafka container
```bash
docker exec -it 4f763689a6fe kafka-topics --bootstrap-server localhost:9092 --create --topic example-topic --partitions 1 --replication-factor 1

docker exec -it 4f763689a6fe kafka-topics --bootstrap-server localhost:9092 --create --topic example-topic2 --partitions 1 --replication-factor 1
```

## List all topics
```bash
docker exec -it <id-container-kafka> kafka-topics --list --bootstrap-server localhost:9092
```

## Get specific data of topic
```bash
docker exec -it <id-container-kafka> kafka-topics --describe --topic example-topic --bootstrap-server localhost:9092
```