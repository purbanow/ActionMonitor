package com.urban.ActionMonitorApp;

import com.urban.ActionMonitorApp.controller.AppInfoController;
import com.urban.ActionMonitorApp.model.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketKafkaListener {

    private static final Logger LOG = LoggerFactory.getLogger(AppInfoController.class);

    private static final String WS_ACTIONS_TOPIC = "/actions";

    @Autowired
    SimpMessagingTemplate template;

    @Value("${app.kafka.topic.actions}")
    private String ACTIONS_TOPIC;

    @Value("${app.kafka.group}")
    private String KAFKA_BROKER;

    @KafkaListener(
            topics = "actions", //TODO make it to use "${app.kafka.topic.actions}"
            groupId = "action-monitor" //TODO make it to use "${app.kafka.group}"
    )
    public void listen(Action action) {
        LOG.info("Sent message {} from kafka topic {} to websocket {} ", action, ACTIONS_TOPIC, WS_ACTIONS_TOPIC);
        template.convertAndSend(WS_ACTIONS_TOPIC, action);
    }
}