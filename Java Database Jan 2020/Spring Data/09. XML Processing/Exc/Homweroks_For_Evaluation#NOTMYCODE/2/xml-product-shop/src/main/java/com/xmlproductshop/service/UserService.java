package com.xmlproductshop.service;

import com.xmlproductshop.dtos.UserSeedDto;
import com.xmlproductshop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void seedUser(List<UserSeedDto> userSeedDtos);

    User getRandomUser();
}
