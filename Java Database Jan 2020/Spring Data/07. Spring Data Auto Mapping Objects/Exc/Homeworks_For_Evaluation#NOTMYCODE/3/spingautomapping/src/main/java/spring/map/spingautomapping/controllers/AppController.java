package spring.map.spingautomapping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.map.spingautomapping.domain.dtos.GameAddDto;
import spring.map.spingautomapping.domain.dtos.UserLoginDto;
import spring.map.spingautomapping.domain.dtos.UserRegisterDto;
import spring.map.spingautomapping.services.GameService;
import spring.map.spingautomapping.services.UserService;
import spring.map.spingautomapping.utils.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader bufferedReader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter command:");
            String[] input = this.bufferedReader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":

                    if(!input[2].equals(input[3])) {
                        System.out.println("Password don't match");
                        break;
                    }
                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(input[1], input[2], input[4]);

                    if(this.validationUtil.isValid(userRegisterDto)) {
                        this.userService.registerUser(userRegisterDto);
                        System.out.printf("%s was registered%n", input[4]);
                    }else {
                        this.validationUtil.getViolations(userRegisterDto)
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
                    this.userService.logout();
                    break;
                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(
                            input[1],
                            new BigDecimal(input[2]),
                            Double.parseDouble(input[3]),
                            input[4],
                            input[5],
                            input[6],
                            LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    );

                    if (this.validationUtil.isValid(gameAddDto)) {
                        this.gameService.addGame(gameAddDto);
                    }else {
                        this.validationUtil.getViolations(gameAddDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                    break;
                case "EditGame":
                    break;
                case "DeleteGame":
                    int id = Integer.parseInt(input[1]);
                    if(this.gameService.getGameById((long) id) != null) {
                        String name = this.gameService.getGameById((long) id).getTitle();
                        this.gameService.deleteGameById((long)id);
                        System.out.printf("Deleted %s", name);
                    }
                    break;
            }
        }

    }
}
