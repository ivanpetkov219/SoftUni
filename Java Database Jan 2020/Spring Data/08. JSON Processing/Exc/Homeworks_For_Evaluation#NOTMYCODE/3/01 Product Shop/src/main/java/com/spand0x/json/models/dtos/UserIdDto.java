package com.spand0x.json.models.dtos;

import javax.persistence.Id;

public class UserIdDto {
    private long id;

    public UserIdDto() {
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
