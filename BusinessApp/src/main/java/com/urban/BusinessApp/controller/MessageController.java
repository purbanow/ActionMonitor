package com.urban.BusinessApp.controller;

import com.urban.BusinessApp.model.Message;
import com.urban.BusinessApp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/message")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message resultMessage = messageService.createMessage(message);
        return new ResponseEntity<>(resultMessage, HttpStatus.CREATED);
    }

    @PutMapping(value = "/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable String id, @RequestBody Message message) {
        Message resultMessage = messageService.updateMessage(id, message);
        return new ResponseEntity<>(resultMessage, HttpStatus.CREATED);
    }

}