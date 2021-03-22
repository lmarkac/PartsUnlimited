package com.luminum.partsunlimitedapp.restcontrollers;

import com.luminum.partsunlimitedapp.dto.ArticleDto;
import com.luminum.partsunlimitedapp.dto.ArticlePriceAfterDiscountDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.service.ArticlesService;
import com.luminum.partsunlimitedapp.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticlesRestController {

    private final ArticlesService articlesService;
    private final PartsService partsService;

    @Autowired
    public ArticlesRestController(ArticlesService articlesService, PartsService partsService) {
        this.articlesService = articlesService;
        this.partsService = partsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ArticlePriceAfterDiscountDto>> listAllArticles() {
        return new ResponseEntity<>(articlesService.listAllArticles(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addArticle(@RequestBody ArticleDto article) {
        if (partsService.findById(article.getIdPart()).isPresent()) {
            articlesService.addArticle(article);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> modify(@PathVariable Integer id, @RequestBody ArticleDto articleDto) {
        Optional<PartDto> part = partsService.findById(id);

        if (articleDto.getId() == id && part.isPresent()) {
            return new ResponseEntity<>(articlesService.modify(articleDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteArticle(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> date) {
        try {
            date.ifPresent(articlesService::deleteAllByPartDateManufactured);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        try {
            articlesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
