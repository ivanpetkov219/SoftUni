package com.example.demo.services;

import com.example.demo.dtos.GameAddDto;
import com.example.demo.dtos.GameDetailDto;
import com.example.demo.dtos.GameViewDto;
import com.example.demo.entities.Game;

import java.util.List;

public interface GameService {

    void addGame(GameAddDto gameAddDto);
    void editGame(String[][] values);
    void deleteGame(long id);

    Game getGameByTitle(String title);
    List<GameViewDto> getAllGames();

    GameDetailDto getDetailedGame(String title);
}
