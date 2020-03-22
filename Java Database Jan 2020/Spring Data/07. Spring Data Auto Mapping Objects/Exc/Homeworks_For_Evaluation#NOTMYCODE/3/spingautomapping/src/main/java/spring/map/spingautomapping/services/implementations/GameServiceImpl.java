package spring.map.spingautomapping.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.map.spingautomapping.domain.dtos.GameAddDto;
import spring.map.spingautomapping.domain.entities.Game;
import spring.map.spingautomapping.repositories.GameRepository;
import spring.map.spingautomapping.services.GameService;
import spring.map.spingautomapping.services.UserService;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        if(!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not Admin");
            return;
        }

        Game game = this.modelMapper
                .map(gameAddDto, Game.class);

        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public Game getGameById(long id) {
        return this.gameRepository.getAllById(id);
    }

    @Override
    public void deleteGameById(long id) {
        if(!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not Admin");
            return;
        }
        this.gameRepository.deleteGameById(id);
    }
}
