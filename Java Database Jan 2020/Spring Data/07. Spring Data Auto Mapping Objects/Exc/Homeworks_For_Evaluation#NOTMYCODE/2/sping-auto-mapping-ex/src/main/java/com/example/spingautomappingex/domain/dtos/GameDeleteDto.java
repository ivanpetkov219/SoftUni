package com.example.spingautomappingex.domain.dtos;

public class GameDeleteDto {
    private long id;

    public GameDeleteDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
