package com.urban.ActionMonitorApp;

import com.urban.ActionMonitorApp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketKafkaListner {

    @Autowired
    SimpMessagingTemplate template;

    @Value("${app.kafka.topic.message}")
    private static String MESSAGE_TOPIC;

    @Value("${app.kafka.group}")
    private static String KAFKA_BROKER;

    @KafkaListener(
            topics = "message",
            groupId = "group"
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        template.convertAndSend("/topic/group", message);
    }
}