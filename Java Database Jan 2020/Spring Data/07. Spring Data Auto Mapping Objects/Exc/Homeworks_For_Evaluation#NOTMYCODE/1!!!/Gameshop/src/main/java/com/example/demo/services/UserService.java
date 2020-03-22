package com.example.demo.services;

import com.example.demo.dtos.GamePurchaseDto;
import com.example.demo.dtos.GameViewDto;
import com.example.demo.dtos.UserLoginDto;
import com.example.demo.dtos.UserRegisterDto;
import com.example.demo.entities.Game;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logoutUser();

    boolean isLoggedUserIsAdmin();

    void purchaseGame(Game game);

    List<GameViewDto> getOwnedGames();
}
