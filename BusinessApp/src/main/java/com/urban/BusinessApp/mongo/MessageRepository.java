package com.urban.BusinessApp.mongo;

import com.urban.BusinessApp.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
