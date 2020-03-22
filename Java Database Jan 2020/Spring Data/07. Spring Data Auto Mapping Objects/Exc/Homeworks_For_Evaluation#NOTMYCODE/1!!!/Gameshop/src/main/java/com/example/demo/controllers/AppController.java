package com.example.demo.controllers;

import com.example.demo.dtos.*;
import com.example.demo.entities.Game;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader reader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader reader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.reader = reader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.print("Enter command:\n\t");
            String [] input = this.reader.readLine().split("\\|");

            switch(input[0]) {
                case "RegisterUser":
                    if (!input[2].equals(input[3])) {
                        System.out.println("Passwords don't match");
                        break;
                    }

                    UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[4]);

                    if (this.validationUtil.isValid(userRegisterDto)) {
                        this.userService.registerUser(userRegisterDto);
                        System.out.printf("%s was registered\n", input[4]);
                    } else {
                        this.validationUtil
                                .getViolations(userRegisterDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                    break;
                case "LoginUser":
                    UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);

                    this.userService.loginUser(userLoginDto);
                    break;
                case "Logout":
                    this.userService.logoutUser();
                    break;
                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(
                            input[1], new BigDecimal(input[2]),
                            Double.parseDouble(input[3]),
                            input[4], input[5], input[6],
                            LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    );

                    if (this.validationUtil.isValid(gameAddDto)) {
                        this.gameService.addGame(gameAddDto);
                    } else {
                        this.validationUtil.getViolations(gameAddDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
                    }
                    break;
                case "EditGame":
                    String[][] values = new String[6][2];
                    for (int i = 1; i < input.length; i++) {
                        values[i-1] = input[i].split("=");
                    }
                    this.gameService.editGame(values);
                    break;
                case "DeleteGame":
                    this.gameService.deleteGame(Long.parseLong(input[1]));
                    break;
                case "PurchaseGame":
                    Game game = this.gameService.getGameByTitle(input[1]);
                    this.userService.purchaseGame(game);
                    break;
                case "AllGames":
                    List<GameViewDto> games = this.gameService.getAllGames();
                    games.forEach(System.out::println);
                    break;
                case "DetailGame":
                    GameDetailDto gameDetailDto = this.gameService.getDetailedGame(input[1]);
                    System.out.printf("Title: %s\nPrice: %.2f\nDescription: %s\nRelease date: %s\n",
                            gameDetailDto.getTitle(), gameDetailDto.getPrice(),
                            gameDetailDto.getDescription(), gameDetailDto.getReleaseDate());
                case "OwnedGames":
                    this.userService.getOwnedGames()
                            .stream()
                            .map(GameViewDto::getTitle)
                            .forEach(System.out::println);
                    break;
            }
        }
    }
}
