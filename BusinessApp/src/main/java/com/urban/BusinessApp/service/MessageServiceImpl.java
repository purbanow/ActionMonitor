package com.urban.BusinessApp.service;

import com.urban.BusinessApp.kafka.KafkaService;
import com.urban.BusinessApp.model.Message;
import com.urban.BusinessApp.mongo.MessageRepository;
import com.urban.BusinessApp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaService kafkaService;

    @Value("${app.kafka.topic.message}")
    private String MESSAGE_TOPIC;

    public Message createMessage(Message message) {
        Message messageMongo = messageRepository.save(message);
        kafkaService.send(messageMongo, "message");
        return messageMongo;
    }

    @Override
    public Message updateMessage(String id, Message message) {

        Message newMessageMongo = messageRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message with ID:" + id + " Not Found!"));

        newMessageMongo.setContent(message.getContent());
        newMessageMongo.setSender(message.getSender());

        messageRepository.save(newMessageMongo);

        kafkaService.send(newMessageMongo, MESSAGE_TOPIC);

        return newMessageMongo;
    }
}
