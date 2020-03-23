package com.softuni.jsonprocessingex.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UsersViewDto {
    @Expose
    private Integer usersCount;
    @Expose
    private List<SellersProductCountDto> users;

    public void setUsersCount() {
        this.users = new ArrayList<>();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<SellersProductCountDto> getUsers() {
        return users;
    }

    public void setUsers(List<SellersProductCountDto> users) {
        this.users = users;
    }
}
