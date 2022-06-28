FROM confluentinc/cp-kafka-connect:latest
RUN  confluent-hub install --no-prompt hpgrahsl/kafka-connect-mongodb:1.1.0

# RUN mkdir -pm 755 /home/appuser/scripts

# ADD --chmod=0755 entrypoint.sh /


# CMD ["bash","-c","\"/entrypoint.sh\""]