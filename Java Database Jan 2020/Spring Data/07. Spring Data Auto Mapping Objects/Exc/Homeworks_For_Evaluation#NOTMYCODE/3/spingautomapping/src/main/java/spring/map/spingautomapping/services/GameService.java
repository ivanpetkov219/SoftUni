package spring.map.spingautomapping.services;

import spring.map.spingautomapping.domain.dtos.GameAddDto;
import spring.map.spingautomapping.domain.entities.Game;

public interface GameService {

    void addGame(GameAddDto gameAddDto);
    Game getGameById(long id);
    void deleteGameById(long id);
}