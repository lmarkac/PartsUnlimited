package com.luminum.partsunlimitedapp.service.dao;

import com.luminum.partsunlimitedapp.model.Article;
import com.luminum.partsunlimitedapp.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Repository("articleBean")
public class ArticlesDaoServiceImpl implements ArticlesDaoService {

    private final ArticlesRepository articlesRepository;

    @Autowired
    public ArticlesDaoServiceImpl(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public void addArticle(Article article) {
        articlesRepository.save(article);
    }

    @Override
    public void deleteById(int id) {
        articlesRepository.deleteById(id);
    }

    @Override
    public Article modify(Article article) {
        return articlesRepository.save(article);
    }

    @Override
    public List<Article> listAllArticles() {
        return StreamSupport.stream(articlesRepository.findAll().spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public Optional<Article> findArticleById(int id) {
        return articlesRepository.findArticleById(id);
    }

    @Override
    public void deleteAllByPartId(int id) {
        articlesRepository.deleteAllByPartId(id);
    }

    @Override
    public void deleteAllByPartDateManufactured(LocalDate date) {
        articlesRepository.deleteAllByPartDateManufactured(date);
    }
}
