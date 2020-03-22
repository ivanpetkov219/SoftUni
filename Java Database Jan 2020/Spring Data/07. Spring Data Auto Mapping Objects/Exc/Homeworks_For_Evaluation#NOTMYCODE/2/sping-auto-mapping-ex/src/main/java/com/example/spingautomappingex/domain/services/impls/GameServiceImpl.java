package com.example.spingautomappingex.domain.services.impls;

import com.example.spingautomappingex.domain.dtos.*;
import com.example.spingautomappingex.domain.entities.Game;
import com.example.spingautomappingex.domain.entities.User;
import com.example.spingautomappingex.domain.repositories.GameRepository;
import com.example.spingautomappingex.domain.services.GameService;
import com.example.spingautomappingex.domain.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (!this.userService.isLoggedUserIsAdmin()){
            System.out.println("Logged user is not admin");
            return;
        }

        Game game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);

    }


    @Override
    public void editGame(GameEditDto gameEditDto) {
       // Integer gameId, String[] gameProps
        Game gameToEdit = this.gameRepository.findById(gameEditDto.getGameId());
        if (gameToEdit == null){
            System.out.printf("Game with Id %d doesn't exist.", gameEditDto.getGameId());
            return;
        }
        if (!this.userService.isLoggedUserIsAdmin()){
            System.out.println("Logged user is not admin");
            return;
        }
        String[] gameProps = gameEditDto.getGameProps();
        //not true

        for (String gameProp : gameProps) {
            String[] tokens = gameProp.split("\\|");
            String propName = tokens[0];
            System.out.println(propName);
            switch (propName){
                case "title":
                    String title = tokens[1];
                    gameToEdit.setTitle(title);
                    System.out.println("Title was updated successfully.");
                    break;
                case "price":
                    BigDecimal price = new BigDecimal(tokens[1]);
                    gameToEdit.setPrice(price);
                    System.out.println("Price was updated successfully.");
                    break;
                case "size":
                    double size = Double.parseDouble(tokens[1]);
                    System.out.println(size);
                    gameToEdit.setSize(size);
                    System.out.println("Size was updated successfully.");
                    break;
                case "trailer":
                    String trailer = tokens[1];
                    gameToEdit.setTrailer(trailer);
                    System.out.println("Trailer was updated successfully.");
                    break;
                case "imageThumbnail":
                    String imageThumbnail = tokens[1];
                    gameToEdit.setImage(imageThumbnail);
                    System.out.println("Image thumbnail was updated successfully.");
                    break;
                case "description":
                    String description = tokens[1];
                    gameToEdit.setDescription(description);
                    System.out.println("Description was updated successfully.");
                    break;
                case "releaseDate":
                    LocalDate releaseDate = LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    gameToEdit.setReleaseDate(releaseDate);
                    System.out.println("Release date was updated successfully.");
                    break;
                default:
                    System.out.printf("%s field doesn't exists.%n", propName);
                    break;
            }
        }
        this.gameRepository.saveAndFlush(gameToEdit);
        System.out.printf("%s edited.", gameToEdit.getTitle());
    }

    @Override
    public void deleteGame(GameDeleteDto gameDeleteDto) {
        Game game = this.gameRepository.findById(gameDeleteDto.getId());
        if (game == null){
            System.out.println("Incorrect game id");
        } else {
            this.gameRepository.delete(game);
            System.out.printf("Deleted %s%n", game.getTitle());
        }

    }

    @Override
    public Set<AllGamesDto> getAllGamesTitleAndPrice() {
        return this.gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, AllGamesDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void detailsOfGame(DetailGameDto detailGameDto) {
        Game game = this.gameRepository.findByTitle(detailGameDto.getTitle());
        if (game != null) {
            System.out.printf("Title: %s %nPrice: ", game.getTitle());
            System.out.println(game.getPrice());
            String formattedDate = game.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
            System.out.printf("Description: %s%nRelease date: %s%n", game.getDescription(), formattedDate);
        }
    }


}
