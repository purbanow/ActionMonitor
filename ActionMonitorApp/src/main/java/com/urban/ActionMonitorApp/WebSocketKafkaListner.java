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

    @KafkaListener(
            topics = "${app.kafka.topic.message}",
            groupId = "${app.kafka.group}"
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        template.convertAndSend("/topic/group", message);
    }
}