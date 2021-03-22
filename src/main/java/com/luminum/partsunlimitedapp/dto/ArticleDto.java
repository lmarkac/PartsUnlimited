package com.luminum.partsunlimitedapp.dto;

import java.math.BigDecimal;

public class ArticleDto {

    private int id;
    private BigDecimal price;
    private int idPart;
    private int idAction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    @Override
    public String toString() {
        return "ArticleDetailsDto{" +
                "id=" + id +
                ", price=" + price +
                ", idPart=" + idPart +
                ", idAction=" + idAction +
                '}';
    }
}

