package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.model.Article;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticlesDaoService {

    void addArticle(Article article);

    void deleteById(int id);

    Article modify(Article article);

    List<Article> listAllArticles();

    Optional<Article> findArticleById(int id);

    void deleteAllByPartId(int id);

    void deleteAllByPartDateManufactured(LocalDate date);
}
