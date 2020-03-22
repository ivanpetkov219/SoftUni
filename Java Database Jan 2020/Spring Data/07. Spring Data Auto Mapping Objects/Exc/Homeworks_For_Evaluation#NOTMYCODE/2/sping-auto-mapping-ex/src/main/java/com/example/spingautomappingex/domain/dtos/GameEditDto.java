package com.example.spingautomappingex.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameEditDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;
    private Integer gameId;
    private String[] gameProps;

    public GameEditDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String[] getGameProps() {
        return gameProps;
    }

    public void setGameProps(String[] gameProps) {
        this.gameProps = gameProps;
    }
}
