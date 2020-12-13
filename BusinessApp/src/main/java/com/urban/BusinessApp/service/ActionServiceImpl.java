package com.urban.BusinessApp.service;

import com.urban.BusinessApp.kafka.KafkaService;
import com.urban.BusinessApp.model.Action;
import com.urban.BusinessApp.mongo.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private KafkaService kafkaService;

    @Value("${app.kafka.topic.actions}")
    private String ACTIONS_TOPIC;

    @Override
    public Action createAction(Action action) {
        Action actionMongo = actionRepository.save(action);
        kafkaService.send(actionMongo, "actions");
        return actionMongo;
    }

    @Override
    public Action updateAction(String id, Action action) {

        Action newActionMongo = actionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Action with ID:" + id + " Not Found!"));

        newActionMongo.setContent(action.getContent());
        newActionMongo.setIssuer(action.getIssuer());

        actionRepository.save(newActionMongo);

        kafkaService.send(newActionMongo, ACTIONS_TOPIC);

        return newActionMongo;
    }
}
