#local-confluent-kafka-cp-zookeeper-headless
kafka-topics --zookeeper $1:2181 --topic order-request --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic order-response --delete --if-exists

kafka-topics --zookeeper $1:2181 --topic order-request --create --partitions 3 --replication-factor 3 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic order-response --create --partitions 3 --replication-factor 3 --if-not-exists
