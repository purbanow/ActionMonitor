package com.urban.BusinessApp.service;

import com.urban.BusinessApp.kafka.KafkaService;
import com.urban.BusinessApp.model.Action;
import com.urban.BusinessApp.mongo.ActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    private static final Logger LOG = LoggerFactory.getLogger(ActionServiceImpl.class);

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private KafkaService kafkaService;

    @Value("${app.kafka.topic.actions}")
    private String ACTIONS_TOPIC;

    @Override
    public Action createAction(Action action) {
        Action actionMongo = actionRepository.save(action);
        LOG.info("Pushed action to MongoDB: {}", action);

        kafkaService.send(actionMongo, "actions");

        return actionMongo;
    }

    @Override
    public Action updateAction(String id, Action action) {

        Action newActionMongo = actionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Didn't Found action inside MongoDB with id " + id));
        LOG.info("Founded action inside MongoDB with id {}", id);

        newActionMongo.setContent(action.getContent());
        newActionMongo.setIssuer(action.getIssuer());

        actionRepository.save(newActionMongo);
        LOG.info("Updated action inside MongoDB from {} to {}", action, newActionMongo);

        kafkaService.send(newActionMongo, ACTIONS_TOPIC);

        return newActionMongo;
    }
}
