package com.luminum.partsunlimitedapp.dto;

import java.time.LocalDate;
import java.util.List;

public class ActionDto {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer discount;
    private List<ArticlePriceAfterDiscountDto> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<ArticlePriceAfterDiscountDto> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlePriceAfterDiscountDto> articles) {
        this.articles = articles;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
