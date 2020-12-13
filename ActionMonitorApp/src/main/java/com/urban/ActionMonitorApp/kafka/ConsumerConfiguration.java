package com.urban.ActionMonitorApp.kafka;

import com.urban.ActionMonitorApp.model.Action;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

    @Value("${app.kafka.broker}")
    private String KAFKA_BROKER;

    @Value("${app.kafka.group}")
    private String KAFKA_GROUP;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Action> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Action> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Action> consumerFactory() {
        JsonDeserializer<Action> deserializer = new JsonDeserializer<>(Action.class, false);
        deserializer.addTrustedPackages("*");

        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_GROUP);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return new DefaultKafkaConsumerFactory<>(configurations, new StringDeserializer(), deserializer);
    }
}
