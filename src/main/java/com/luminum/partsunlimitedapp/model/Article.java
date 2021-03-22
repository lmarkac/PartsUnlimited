package com.luminum.partsunlimitedapp.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "id_part", referencedColumnName = "id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "id_action", referencedColumnName = "id")
    private Action action;

    public Article(){
        /**
         * Hibernate needs an empty constructor to instantiate objects
         */
    }

    public Article(int id, BigDecimal price, Part part, Action action) {
        this.id = id;
        this.price = price;
        this.part = part;
        this.action = action;
    }

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
