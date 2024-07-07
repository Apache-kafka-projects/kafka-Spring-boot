package com.kafka.providor.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    /**
     * Here we configure how the serialization will be and how we send messages to the kafka server.
     * @return List of producer configuration
     */
    public Map<String, Object> producerConfig(){
        Map<String, Object> properties = new HashMap<>();

        // We indicate where is the kafka server (direction)
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // We send a txt message, then we should indicate the class responsible for carrying out the serialization.
        // Converts String to Bytes
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Who will be in charge of serialize the key
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    // Producer == Provider
    @Bean
    public ProducerFactory<String, String> providerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

   @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
