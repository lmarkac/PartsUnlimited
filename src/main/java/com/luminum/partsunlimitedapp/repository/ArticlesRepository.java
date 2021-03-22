package com.luminum.partsunlimitedapp.repository;

import com.luminum.partsunlimitedapp.model.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("articlesRepositoryBean")
public interface ArticlesRepository extends CrudRepository<Article, Integer> {

    @Modifying
    @Query(value = "DELETE FROM articles WHERE id = ?1", nativeQuery = true)
    void deleteById(int id);

    @Query(value = "SELECT * FROM articles WHERE id = ?1", nativeQuery = true)
    Optional<Article> findArticleById(int id);

    @Modifying
    @Query(value = "DELETE FROM articles WHERE id_part = ?1", nativeQuery = true)
    void deleteAllByPartId(int id);

    @Modifying
    @Query(value = "DELETE FROM articles WHERE id_part = (select id from parts p where p.date_manufactured = ?1)", nativeQuery = true)
    void deleteAllByPartDateManufactured(LocalDate date);

    @Query(value = "SELECT * FROM articles JOIN actions ON articles.id_action = actions.id WHERE actions.id = ?1", nativeQuery = true)
    List<Article> findAllByActionId(int id);
}
