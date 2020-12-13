package com.urban.BusinessApp.kafka;

import com.urban.BusinessApp.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Action> kafkaTemplate;

    public void send(Action action, String topic) {
        try {
            SendResult<String, Action> result = kafkaTemplate.send(topic, action).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Not able to send message to kafka");
        }
    }
}
