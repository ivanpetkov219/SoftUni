package com.softuni.jsonprocessingex.services;

import com.softuni.jsonprocessingex.models.dtos.UserSeedDto;
import com.softuni.jsonprocessingex.models.dtos.UserSoldDto;
import com.softuni.jsonprocessingex.models.dtos.UsersViewDto;
import com.softuni.jsonprocessingex.models.entities.User;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();

    List<UserSoldDto> getSellers();

    UsersViewDto getAllSellersByCount();

}
