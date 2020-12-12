package com.urban.BusinessApp.kafka;

import com.urban.BusinessApp.model.Message;
import com.urban.BusinessApp.controller.MessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void send(Message message, String topic) {
        try {
            SendResult<String, Message> result = kafkaTemplate.send(topic, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Not able to send message to kafka");
        }
    }
}
