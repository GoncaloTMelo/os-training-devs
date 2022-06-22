package com.natixis.finatial.traning.voteapp.streams;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public class StreamApplication {
    private final static String APPLICATION_ID = "vote-statistics";
    private static final String BOOTSTRAP_SERVER = System.getenv("KAFKA_BOOTSTRAP");
    public static final String KAFKA_IN_TOPIC = "vote.input";
    public static final String KAFKA_INTER_STATISTICS_TOPIC = "vote.inter.statistics";
    public static final String KAFKA_STATISTICS_TOPIC = "vote.statistics";

    public static void main(String[] args) {
        KafkaStreams kafkaStreams;
        StreamApplication cc = new StreamApplication();
        Properties properties = cc.getConfig("bank-balance");
        NewTopic topic = cc.generateNewTopic(KAFKA_STATISTICS_TOPIC, 3);

        topic.configs(Map.of(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT));
        cc.createTopicIfDoesntExist(topic);

        properties.setProperty(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);

        kafkaStreams = new KafkaStreams(createTopology(), properties);
        kafkaStreams.start();

        // printed the topology
        log.info(kafkaStreams.toString());

        // Add shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }

    private Properties getConfig(String s) {
        Properties config = new Properties();
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return config;
    }

    public static Topology createTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, String> stream = builder.table(KAFKA_IN_TOPIC);
        stream.groupBy((user, vote) -> new KeyValue<>(vote, vote))
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("CountsByVotes")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(Serdes.Long()))
                .toStream()
                .to(KAFKA_STATISTICS_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));

        return builder.build();
    }

    public NewTopic generateNewTopic(String topic) {
        return generateNewTopic(topic, 1, (short) 1);
    }

    public NewTopic generateNewTopic(String topic, int partitions) {
        return generateNewTopic(topic, partitions, (short) 1);
    }

    public NewTopic generateNewTopic(String name, int partitions, short replicationFactor) {
        return new NewTopic(name, partitions, replicationFactor);
    }

    public void createTopicIfDoesntExist(NewTopic topic) {
        createTopicIfDoesntExist(Arrays.asList(topic));
    }

    public void createTopicIfDoesntExist(List<NewTopic> topics) {
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        log.debug("Admin Properties{" + properties + "}");
        try (AdminClient admin = AdminClient.create(properties)) {
            Set<String> existingTopic = admin.listTopics().names().get();
            List<NewTopic> topicExists = topics.stream().filter(t -> existingTopic.contains(t.name())).collect(Collectors.toList());
            if (topicExists.size() > 0) {
                log.info("Topics " + topics.stream().map(t -> t.name()).collect(Collectors.toList()) + " do not exist, creating them.");
                admin.createTopics(topicExists);
            } else
                log.info("Topics " + topics.stream().map(t -> t.name()).collect(Collectors.toList()) + " already exist, skipping them.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
