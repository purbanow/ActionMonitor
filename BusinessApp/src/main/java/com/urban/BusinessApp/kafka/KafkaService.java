package com.urban.BusinessApp.kafka;

import com.urban.BusinessApp.model.Action;
import com.urban.BusinessApp.service.ActionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, Action> kafkaTemplate;

    public void send(Action action, String topic) {
        try {
            SendResult<String, Action> result = kafkaTemplate.send(topic, action).get();
            LOG.info("Pushed message {} to topic {} with result {} ", action, topic, result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Didn't able to send message " + action + " to " + topic + " because of " + e.getMessage());
        }
    }
}
