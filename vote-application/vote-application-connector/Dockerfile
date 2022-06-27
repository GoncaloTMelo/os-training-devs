FROM confluentinc/cp-kafka-connect-base:7.1.1

RUN  confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.1.0

RUN mkdir -p /home/appuser/scripts

COPY --chmod=0755 entrypoint.sh /home/appuser/scripts


CMD ["bash","-c","\"/home/appuser/scripts/entrypoint.sh\""]