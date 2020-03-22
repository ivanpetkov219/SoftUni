package com.example.spingautomappingex.domain.services;

import com.example.spingautomappingex.domain.dtos.UserLoginDto;
import com.example.spingautomappingex.domain.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserIsAdmin();
}
