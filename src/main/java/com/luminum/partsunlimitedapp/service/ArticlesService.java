package com.luminum.partsunlimitedapp.service;

import com.luminum.partsunlimitedapp.dto.ArticleDto;
import com.luminum.partsunlimitedapp.dto.ArticlePriceAfterDiscountDto;
import com.luminum.partsunlimitedapp.model.Action;
import com.luminum.partsunlimitedapp.model.Article;
import com.luminum.partsunlimitedapp.model.Part;
import com.luminum.partsunlimitedapp.service.dao.ActionsDaoService;
import com.luminum.partsunlimitedapp.service.dao.ArticlesDaoService;
import com.luminum.partsunlimitedapp.service.dao.PartsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("articlesService")
public class ArticlesService {

    private final ArticlesDaoService articlesDaoService;
    private final ActionsDaoService actionsDaoService;
    private final PartsDaoService partsDaoService;
    private final ArticlesMappingService articlesMappingService;

    @Autowired
    public ArticlesService(ArticlesDaoService articlesDaoService, ActionsDaoService actionsDaoService, PartsDaoService partsDaoService, ArticlesMappingService articlesMappingService) {
        this.articlesDaoService = articlesDaoService;
        this.actionsDaoService = actionsDaoService;
        this.partsDaoService = partsDaoService;
        this.articlesMappingService = articlesMappingService;
    }

    public ArticleDto modify(ArticleDto article) {
        Optional<Article> articleById = articlesDaoService.findArticleById(article.getId());
        if (articleById.isPresent()) {
            Article articleModel = articleById.get();

            articleModel.setPrice(article.getPrice());
            articleModel.setPart(partsDaoService.findById(article.getIdPart()).get());

            Action action = article.getIdAction() == -1 ? null : actionsDaoService.findById(article.getIdAction()).orElse(null);
            articleModel.setAction(action);

            Article savedModel = articlesDaoService.modify(articleModel);

            return articlesMappingService.mapArticleToArticleDto(savedModel);
        } else {
            return null;
        }
    }

    public List<ArticlePriceAfterDiscountDto> listAllArticles() {
        return articlesDaoService.listAllArticles().stream()
                .map(articlesMappingService::mapArticleToArticlePriceAfterDiscountDto)
                .collect(Collectors.toList());
    }

    public void addArticle(ArticleDto articleDto) {
        Article article = new Article();

        article.setPrice(articleDto.getPrice());
        if (actionsDaoService.findById(articleDto.getIdAction()).isPresent()) {
            article.setAction(actionsDaoService.findById(articleDto.getIdAction()).get());
        } else {
            article.setAction(null);
        }
        article.setPart(partsDaoService.findById(articleDto.getIdPart()).get());

        articlesDaoService.addArticle(article);
    }

    public void deleteById(int id) {
        articlesDaoService.deleteById(id);
    }

    public Optional<ArticleDto> findArticleById(int id) {
        return articlesDaoService.findArticleById(id)
                .map(articlesMappingService::mapArticleToArticleDto);
    }

    public void deleteAllByPartId(int id) {
        articlesDaoService.deleteAllByPartId(id);
    }

    public void deleteAllByPartDateManufactured(LocalDate date) {
        articlesDaoService.deleteAllByPartDateManufactured(date);
    }
}
