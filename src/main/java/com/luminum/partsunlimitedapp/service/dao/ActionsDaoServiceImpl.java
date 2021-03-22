package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.dto.ActionDto;
import com.luminum.partsunlimitedapp.model.Action;
import com.luminum.partsunlimitedapp.model.Article;
import com.luminum.partsunlimitedapp.repository.ActionsRepository;
import com.luminum.partsunlimitedapp.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("actionBean")
public class ActionsDaoServiceImpl implements ActionsDaoService {

    private final ActionsRepository actionsRepository;
    private final ArticlesRepository articlesRepository;

    @Autowired
    public ActionsDaoServiceImpl(ActionsRepository actionsRepository, ArticlesRepository articlesRepository) {
        this.actionsRepository = actionsRepository;
        this.articlesRepository = articlesRepository;
    }

    @Override
    public Action addAction(ActionDto actionDto) {
        Action action = getAction(actionDto);

        return actionsRepository.save(action);
    }

    private Action getAction(ActionDto actionDto) {
        Action action = new Action();

        action.setStartDate(actionDto.getStartDate());
        action.setEndDate(actionDto.getEndDate());
        action.setDiscount(actionDto.getDiscount());

        return action;
    }

    public Iterable<Action> findAll() {
        return actionsRepository.findAll();
    }

    public void deleteById(Integer id) {
        List<Article> articleList = articlesRepository.findAllByActionId(id);

        articleList.forEach(article -> {
            article.setAction(null);
            articlesRepository.save(article);
        });

        actionsRepository.deleteById(id);
    }

    @Override
    public Optional<Action> findById(int idAction) {
        return actionsRepository.findById(idAction);
    }
}
