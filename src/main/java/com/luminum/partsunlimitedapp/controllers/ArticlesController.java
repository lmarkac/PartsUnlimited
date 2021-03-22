package com.luminum.partsunlimitedapp.controllers;

import com.luminum.partsunlimitedapp.dto.ActionDto;
import com.luminum.partsunlimitedapp.dto.ArticleDto;
import com.luminum.partsunlimitedapp.dto.ArticlePriceAfterDiscountDto;
import com.luminum.partsunlimitedapp.dto.PartDto;
import com.luminum.partsunlimitedapp.service.ActionsService;
import com.luminum.partsunlimitedapp.service.ArticlesService;
import com.luminum.partsunlimitedapp.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticlesController {

    private final ActionsService actionsService;
    private final ArticlesService articlesService;
    private final PartsService partsService;

    @Autowired
    public ArticlesController(ActionsService actionsService,
                              ArticlesService articlesService,
                              PartsService partsService) {
        this.actionsService = actionsService;
        this.articlesService = articlesService;
        this.partsService = partsService;
    }

    @GetMapping("/articles")
    public String articles(Model model) {

        Iterable<ArticlePriceAfterDiscountDto> articles = articlesService.listAllArticles();
        Iterable<PartDto> parts = partsService.findAll();

        model.addAttribute("articles", articles);
        model.addAttribute("parts", parts);

        return "articles";
    }

    @GetMapping("/articles/new")
    public String newArticle(Model model) {

        Iterable<PartDto> parts = partsService.findAll();
        Iterable<ActionDto> actions = actionsService.findAll();

        model.addAttribute("articleDto", new ArticleDto());
        model.addAttribute("parts", parts);
        model.addAttribute("actions", actions);

        return "newArticle";
    }

    @PostMapping(value = "/articles")
    public String addArticle(ArticleDto articleDto) {
        articlesService.addArticle(articleDto);
        return "redirect:/articles";
    }

    @GetMapping("/articles/modify/{id}")
    public String modifyArticle(@PathVariable int id, Model model) {
        Optional<ArticleDto> articleDtoOpt = articlesService.findArticleById(id);

        Iterable<PartDto> parts = partsService.findAll();
        Iterable<ActionDto> actions = actionsService.findAll();

        if (articleDtoOpt.isPresent()) {
            model.addAttribute("articleDto", articleDtoOpt.get());
            model.addAttribute("parts", parts);
            model.addAttribute("actions", actions);

            return "modifyArticle";
        }
        return "redirect:/";
    }

    @PostMapping("/articles/modify/{id}")
    public String modifyPost(@PathVariable Integer id, ArticleDto articleDto) {
        if (id == articleDto.getId()) {
            articlesService.modify(articleDto);
            return "redirect:/articles";
        }

        return "redirect:/articles/modify/" + id;
    }

    @GetMapping("/articles/delete")
    public String delete(Model model) {
        List<ArticlePriceAfterDiscountDto> articleDtos = articlesService.listAllArticles();
        model.addAttribute("articleDtos", articleDtos);

        return "delete";
    }

    @PostMapping("/articles/delete")
    public String deleteById(@RequestParam Integer id) {
        articlesService.deleteById(id);
        return "redirect:/articles";
    }
}
