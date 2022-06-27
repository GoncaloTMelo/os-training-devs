FROM confluentinc/cp-kafka-connect-base:7.1.1

RUN  confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.1.0

CMD ["bash","-c","\"/etc/confluent/docker/run\""]