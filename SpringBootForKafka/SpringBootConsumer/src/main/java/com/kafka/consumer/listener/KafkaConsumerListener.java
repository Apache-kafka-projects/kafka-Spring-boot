package com.kafka.consumer.listener;

import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = {"kafkaExampleTopic"}, groupId = "my-group-id")
    public void listener(String message){
        LOGGER.info("Message received: " + message);
    }
}
