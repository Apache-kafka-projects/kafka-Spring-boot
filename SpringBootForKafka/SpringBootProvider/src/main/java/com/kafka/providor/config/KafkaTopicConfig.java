package com.kafka.providor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    /**
     * Here we create a new topic with specific configuration.
     * @return List of topic configuration
     */
    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configurations = new HashMap<>();

        // With Delete => Will delete the message
        // With Compact => Will save the most resent
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);

        // We will retain the message por one day, and then will delete it.
        // By default, this property is -1 => will never delete any message => BAD:consume storage.
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");

        // Max size of each segment inside the topic in bytes. => 1GB
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");

        // Max size of message
        // Default 1MB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        return TopicBuilder.name("kafkaExampleTopic")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();

        /**
         * Important note:
         * If we specify a number of replicas higher than the number of brokers available in our cluster,
         * we will get an ERROR.
         */
    }
}
