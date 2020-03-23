package com.softuni.jsonprocessingex.models.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Set<Category> categories;
    private User buyer;
    private User seller;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(cascade = {ALL})
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {PERSIST})
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Seller cannot be null!")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
