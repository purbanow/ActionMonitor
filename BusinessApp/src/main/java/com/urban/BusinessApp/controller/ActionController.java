package com.urban.BusinessApp.controller;

import com.urban.BusinessApp.model.Action;
import com.urban.BusinessApp.service.ActionService;
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
public class ActionController {

    private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

    @Autowired
    private ActionService actionService;

    @PostMapping(value = "/action")
    public ResponseEntity<Action> createAction(@RequestBody Action action) {
        Action resultAction = actionService.createAction(action);
        return new ResponseEntity<>(resultAction, HttpStatus.CREATED);
    }

    @PutMapping(value = "/action/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable String id, @RequestBody Action action) {
        Action resultAction = actionService.updateAction(id, action);
        return new ResponseEntity<>(resultAction, HttpStatus.CREATED);
    }

}