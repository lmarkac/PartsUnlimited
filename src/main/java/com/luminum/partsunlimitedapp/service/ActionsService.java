package com.luminum.partsunlimitedapp.service;

import com.luminum.partsunlimitedapp.dto.ActionDto;
import com.luminum.partsunlimitedapp.model.Action;
import com.luminum.partsunlimitedapp.service.dao.ActionsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("actionsService")
public class ActionsService {

    private final ActionsDaoService actionsDaoService;
    private final ArticlesMappingService articlesMappingService;

    @Autowired
    public ActionsService(ActionsDaoService actionsDaoService, ArticlesMappingService articlesMappingService) {
        this.actionsDaoService = actionsDaoService;
        this.articlesMappingService = articlesMappingService;
    }

    public ActionDto addAction(ActionDto actionDto) {
        Action action = actionsDaoService.addAction(actionDto);
        return actionToActionDto(action);
    }

    public Iterable<ActionDto> findAll() {
        Iterable<Action> actionsList = actionsDaoService.findAll();
        return listOfActionsToListOfActionsDto(actionsList);
    }

    public void deleteById(Integer id) {
        actionsDaoService.deleteById(id);
    }

    private ActionDto actionToActionDto(Action action) {
        ActionDto actionDto = new ActionDto();
        actionDto.setId(action.getId());
        actionDto.setDiscount(action.getDiscount());
        actionDto.setStartDate(action.getStartDate());
        actionDto.setEndDate(action.getEndDate());
        actionDto.setArticles(
                action.getArticleList().stream()
                        .map(articlesMappingService::mapArticleToArticlePriceAfterDiscountDto)
                        .collect(Collectors.toList())
        );

        return actionDto;
    }

    private List<ActionDto> listOfActionsToListOfActionsDto(Iterable<Action> actions) {
        List<ActionDto> actionDtoList = new ArrayList<>();

        for (Action action : actions) {
            ActionDto actionDto = actionToActionDto(action);
            actionDtoList.add(actionDto);
        }

        return actionDtoList;
    }
}
