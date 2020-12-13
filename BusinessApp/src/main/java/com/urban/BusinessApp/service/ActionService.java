package com.urban.BusinessApp.service;

import com.urban.BusinessApp.model.Action;

public interface ActionService {
    Action createAction(Action action);

    Action updateAction(String id, Action action);
}
