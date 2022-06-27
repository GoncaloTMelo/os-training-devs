FROM confluentinc/cp-kafka-connect-base:7.1.1

RUN  confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.1.0

CMD [
"bash",
"-c",
"|
echo "Installing Connector"
confluent-hub install --no-prompt debezium/debezium-connector-mysql:1.7.0
confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:11.1.3
confluent-hub install --no-prompt neo4j/kafka-connect-neo4j:2.0.0
#
echo "Launching Kafka Connect worker"
/etc/confluent/docker/run &
#
sleep infinity"
]