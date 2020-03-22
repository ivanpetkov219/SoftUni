package com.example.spingautomappingex.domain.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
//    private BigInteger buyerId;
//
//    public Order() {
//    }
//
//    @ManyToOne
//    @JoinTable(name = "users_games",
//            inverseJoinColumns = @JoinColumn(name = "games_id"))
//    @Column(name = "buyer_id")
//    public BigInteger getBuyerId() {
//        return buyerId;
//    }
//
//
//    public void setBuyerId(BigInteger buyerId) {
//        this.buyerId = buyerId;
//    }
//
//    private User buyer;
//    private Set<Game> products;
    private User buyer;
    private Set<Game> products;

    public Order() {
    }

    @ManyToOne()
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @JoinTable(name = "orders_ordered_games", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_game_id"))
    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
}
