package com.natixis.finatial.traning.vote.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig {

    private final Environment env;
    @Value("${kafka.bootstrap}")
    String bootstrap;

    @Value("${kafka.topic:vote.input}")
    String topic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap));
    }

    @Bean
    public void createTopicIfDoesntExist() {
        Properties properties = baseConfig();
        log.debug("Admin Properties{" + properties + "}");
        try (AdminClient admin = AdminClient.create(properties)) {
            boolean topicExists = admin.listTopics().names().get().stream().anyMatch(topicName -> topicName.equalsIgnoreCase(topic));
            if (!topicExists) {
                log.info("Topic " + topic + " does not exist, creating it.");
                admin.createTopics(Arrays.asList(newTopic()));
            } else
                log.info("Topic " + topic + " already exists.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topic).partitions(2).build();
    }

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        return new KafkaProducer<>(kafkaProducerConfig());
    }

    public Properties baseConfig() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        return properties;
    }

    @Bean
    public Properties kafkaProducerConfig() {
        Properties properties = baseConfig();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        log.debug("Producer Properties{" + properties + "}");
        return properties;
    }


//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }


}
