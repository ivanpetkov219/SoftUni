package com.example.demo.services.impls;

import com.example.demo.dtos.GameAddDto;
import com.example.demo.dtos.GameDetailDto;
import com.example.demo.dtos.GameEditDto;
import com.example.demo.dtos.GameViewDto;
import com.example.demo.entities.Game;
import com.example.demo.repositories.GameRepository;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.*;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        //TODO Check if user is logged in try/catch
        if (!this.userService.isLoggedUserIsAdmin()) {
            System.out.println("Logged user is not admin");
            return;
        }
        Game game = this.modelMapper
                 .map(gameAddDto, Game.class);

        this.gameRepository.saveAndFlush(game);
        System.out.println("Added " + game.getTitle());
    }

    @Transactional
    @Override
    public void editGame(String[][] values) {
        if (!this.userService.isLoggedUserIsAdmin()) {
            System.out.println("Logged user is not admin");
            return;
        }
        Game game;
        GameEditDto gameEditDto;
        long id = parseLong(values[0][0]);
        try {
            game = this.gameRepository.findGameById(id);
            gameEditDto = this.modelMapper.map(game, GameEditDto.class);
        } catch (EntityNotFoundException e) {
            System.out.println("No such game with this id");
            return;
        }
        for (int i = 1; i < values.length; i++) {
            if (values[i][0] == null) {
                break;
            }
            switch (values[i][0]) {
                case "title":
                    gameEditDto.setTitle(values[i][1]);
                    break;
                case "description":
                    gameEditDto.setDescription(values[i][1]);
                    break;
                case "image":
                    gameEditDto.setImage(values[i][1]);
                    break;
                case "price":
                    gameEditDto.setPrice(new BigDecimal(values[i][1]));
                    break;
                case "size":
                    gameEditDto.setSize(Double.parseDouble(values[i][1]));
                    break;
                case "trailer":
                    gameEditDto.setTrailer(values[i][1]);
                    break;
                case "releaseDate":
                    gameEditDto.setReleaseDate(LocalDate.parse(values[i][1], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;
                default:
                    System.out.println(values[i][0] + " is invalid");
                    break;
            }
        }

        if(!validationUtil.isValid(gameEditDto)) {
            validationUtil.getViolations(gameEditDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        for (int i = 1; i < values.length; i++) {
            if (values[i][0] == null) {
                break;
            }
            switch (values[i][0]) {
                case "title":
                    this.gameRepository.editTitleById(values[i][1], id);
                    break;
                case "description":
                    this.gameRepository.editDescriptionById(values[i][1], id);
                    break;
                case "image":
                    this.gameRepository.editImageById(values[i][1], id);
                    break;
                case "price":
                    this.gameRepository.editPriceById(new BigDecimal(values[i][1]), id);
                    break;
                case "size":
                    this.gameRepository.editSizeById(Double.parseDouble(values[i][1]), id);
                    break;
                case "trailer":
                    this.gameRepository.editTrailerById(values[i][1], id);
                    break;
                case "releaseDate":
                    this.gameRepository.editReleaseDateById(LocalDate.parse(values[i][1], DateTimeFormatter.ofPattern("dd-MM-yyyy")), id);
                    break;
            }
        }

        System.out.println("Edited " + gameEditDto.getTitle());
    }

    @Override
    public void deleteGame(long id) {
        Game game;
        try {
            game = this.gameRepository.findGameById(id);
        } catch (EntityNotFoundException e) {
            System.out.println("No such game with this id");
            return;
        }
        if (game == null) {
            System.out.println("No such game with this id");
            return;
        }
        String title = game.getTitle();
        this.gameRepository.delete(game);
        System.out.println("Deleted " + title);
    }

    @Override
    public Game getGameByTitle(String title) {
        return this.gameRepository.findGameByTitle(title);
    }

    @Override
    public List<GameViewDto> getAllGames() {
        return this.gameRepository.getAllGames()
                .stream()
                .map(game1 -> this.modelMapper.map(game1, GameViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GameDetailDto getDetailedGame(String title) {
        return this.modelMapper
                .map(this.gameRepository.findGameByTitle(title), GameDetailDto.class);
    }

}
