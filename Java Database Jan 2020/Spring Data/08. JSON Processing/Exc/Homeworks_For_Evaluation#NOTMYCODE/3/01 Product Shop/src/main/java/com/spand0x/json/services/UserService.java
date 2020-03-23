package com.spand0x.json.services;


import com.spand0x.json.models.dtos.UserCountDto;
import com.spand0x.json.models.dtos.UserSeedDto;
import com.spand0x.json.models.dtos.UserSoldDto;
import com.spand0x.json.models.entities.User;

import java.util.List;

public interface UserService {
    void seedUser(UserSeedDto usersSeedDto);

    User getRandomUser();

    List<UserSoldDto> getUsersWithSoldProducts();

    UserCountDto getUsersAndProducts();

}
