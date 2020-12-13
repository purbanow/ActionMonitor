package com.urban.BusinessApp.mongo;

import com.urban.BusinessApp.model.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionRepository extends MongoRepository<Action, String> {
}
