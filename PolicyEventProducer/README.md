
Set up Zookeeper in windows

to Start Zookeeper 
zookeeper-server-start.bat ..\..\config\zookeeper.properties

To Start kafka server
Go to config folder and edit server.properties

Add the localhost in server.properties in kafka

broker.id=0
listeners=PLAINTEXT://localhost:9092
log.dirs=/tmp/<unique-kafka-folder>
auto.create.topics.enable=false

kafka-server-start.bat ..\..\config\server.properties

To Create Topic
kafka-topics.bat --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4


130


Newer versions(2.2+) of Kafka no longer requires ZooKeeper connection string

--zookeeper localhost:2181

It throws the following exception while creating a topic

Exception in thread "main" joptsimple.UnrecognizedOptionException: zookeeper is not a recognized option

Instead, add Kafka Broker --bootstrap-server localhost:9092 connection string.

kafka-topics.bat --create --topic policy-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4


How to instantiate a Console Producer?
Without Key
    kafka-console-producer.bat --broker-list localhost:9092 --topic policy-topic
With Key
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"

How to instantiate a Console Consumer?
Without Key
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic policy-topic --from-beginning
With Key
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"