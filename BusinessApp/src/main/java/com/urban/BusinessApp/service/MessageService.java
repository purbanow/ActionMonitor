package com.urban.BusinessApp.service;

import com.urban.BusinessApp.model.Message;

public interface MessageService {
    Message createMessage(Message message);

    Message updateMessage(String id, Message message);
}
