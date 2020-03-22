package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {
    private String description;
    private String image;
    private BigDecimal price;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(nullable = true)
    private double size;
    @Column(unique = true)
    private String title;
    private String trailer;
//    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    private Set<User> users;
    @ManyToMany(mappedBy = "ordered_games", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Order> orders;
}
