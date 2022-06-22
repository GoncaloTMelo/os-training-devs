package com.natixis.finatial.traning.voteapp.streams;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class StreamApplicationTest {
    private TopologyTestDriver testDriver;
    private TestInputTopic<String, String> inputTopic;
    private TestOutputTopic<String, Long> outputTopic;

    @Before
    public void init() {
        Properties config = new Properties();

        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "dummyValue");
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummyValue:312");
        config.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        testDriver = new TopologyTestDriver(StreamApplication.createTopology(), config);

        inputTopic = testDriver.createInputTopic(StreamApplication.KAFKA_IN_TOPIC, new StringSerializer(), new StringSerializer());
        outputTopic = testDriver.createOutputTopic(StreamApplication.KAFKA_STATISTICS_TOPIC, new StringDeserializer(), new LongDeserializer());
    }
    @After
    public void finish() {
        testDriver.close();
    }

    @Test
    public void simpleTest() {
        inputTopic.pipeInput("test", "1");
        inputTopic.pipeInput("test", "5");
        inputTopic.pipeInput("test2", "1");
        assertKeyValue("1", 1L, outputTopic.readKeyValue());
        assertKeyValue("1", 0L, outputTopic.readKeyValue());
        assertKeyValue("5", 1L, outputTopic.readKeyValue());
        assertKeyValue("1", 1L, outputTopic.readKeyValue());
    }

    private void assertKeyValue(String expectedKey, Long expectedValue, KeyValue<String, Long> readKeyValue) {
        System.out.println(readKeyValue.key + ": " + readKeyValue.value);
        assertEquals(expectedKey, readKeyValue.key);
        assertEquals(expectedValue, readKeyValue.value);
    }
}
