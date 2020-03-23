package com.spand0x.json.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class UserCountDto {
    @Expose
    private int userCount;
    @Expose
    private Set<UserProductsDto> users;

    public UserCountDto() {
        this.users = new HashSet<>();
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public Set<UserProductsDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserProductsDto> users) {
        this.users = users;
    }
}
