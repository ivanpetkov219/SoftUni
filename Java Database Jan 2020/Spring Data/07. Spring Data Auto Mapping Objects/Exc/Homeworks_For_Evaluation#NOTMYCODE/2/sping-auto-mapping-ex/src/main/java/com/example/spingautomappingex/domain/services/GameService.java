package com.example.spingautomappingex.domain.services;

import com.example.spingautomappingex.domain.dtos.*;
import com.example.spingautomappingex.domain.entities.Game;

import java.util.List;
import java.util.Set;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(GameEditDto gameEditDto);


    void deleteGame(GameDeleteDto gameDeleteDto);

    Set<AllGamesDto> getAllGamesTitleAndPrice();

    void detailsOfGame(DetailGameDto detailGameDto);

}
