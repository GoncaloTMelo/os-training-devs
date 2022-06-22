package com.natixis.finatial.traning.vote.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaProducer<String, String> producer;
    @Value("${kafka.topic:vote.input}")
    String topic;



    public void send(String message) {
        send(null, message);
    }

    public void send(String key, String message) {
        log.debug("Sending [" + key + "]-[" + message + "] to topic " + topic);
        producer.send(new ProducerRecord<>(topic, key, message));
    }
}
