package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.dto.ActionDto;
import com.luminum.partsunlimitedapp.model.Action;

import java.util.Optional;

public interface ActionsDaoService {

    Action addAction(ActionDto action);

    Iterable<Action> findAll();

    void deleteById(Integer id);

    Optional<Action> findById(int idAction);
}
