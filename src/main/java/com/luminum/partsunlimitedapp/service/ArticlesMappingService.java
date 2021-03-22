package com.luminum.partsunlimitedapp.service;

import com.luminum.partsunlimitedapp.dto.ArticleDto;
import com.luminum.partsunlimitedapp.dto.ArticlePriceAfterDiscountDto;
import com.luminum.partsunlimitedapp.model.Action;
import com.luminum.partsunlimitedapp.model.Article;
import com.luminum.partsunlimitedapp.model.Part;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ArticlesMappingService {

    public ArticleDto mapArticleToArticleDto(Article article) {
        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(article.getId());
        articleDto.setPrice(article.getPrice());
        articleDto.setIdPart(Optional.ofNullable(article.getPart()).map(Part::getId).orElse(-1));
        articleDto.setIdAction(Optional.ofNullable(article.getAction()).map(Action::getId).orElse(-1));

        return articleDto;
    }

    public ArticlePriceAfterDiscountDto mapArticleToArticlePriceAfterDiscountDto(Article article) {
        ArticlePriceAfterDiscountDto articlePriceAfterDiscountDto = new ArticlePriceAfterDiscountDto();
        articlePriceAfterDiscountDto.setId(article.getId());

        if (article.getPart() != null) {
            articlePriceAfterDiscountDto.setDateManufactured(article.getPart().getDateManufactured());
        }

        BigDecimal priceAfterDiscount;
        if (article.getAction() != null) {
            LocalDate startDate = article.getAction().getStartDate();
            LocalDate endDate = article.getAction().getEndDate();

            if (checkIfActionIsValid(startDate, endDate, LocalDate.now())) {
                priceAfterDiscount = article.getPrice().subtract(article.getPrice().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(article.getAction().getDiscount())));
            } else {
                priceAfterDiscount = article.getPrice();
            }
        } else {
            priceAfterDiscount = article.getPrice().setScale(2, RoundingMode.HALF_UP);
        }

        articlePriceAfterDiscountDto.setPriceAfterDiscount(priceAfterDiscount.setScale(2, RoundingMode.HALF_UP));

        return articlePriceAfterDiscountDto;
    }

    private Boolean checkIfActionIsValid(LocalDate startDate, LocalDate endDate, LocalDate now) {
        return now.isAfter(startDate) && now.isBefore(endDate);
    }
}
