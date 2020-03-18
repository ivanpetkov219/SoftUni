package springdata.automapping.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.automapping.models.dtos.UserRegisterDto;
import springdata.automapping.services.GameService;
import springdata.automapping.services.OrderService;
import springdata.automapping.services.UserService;
import springdata.automapping.utils.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;
    private final ValidationUtil validationUtil;

    @Autowired
    public AppController(BufferedReader bufferedReader, UserService userService, GameService gameService, OrderService orderService, ValidationUtil validationUtil) {
        this.bufferedReader = bufferedReader;
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.validationUtil = validationUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true){
            String[] input = this.bufferedReader.readLine().split("\\|");

            String commmand = input[0];

            switch (commmand){

                case "RegisterUser":
                    if(!input[2].equals(input[3])){
                        System.out.println("Passwords don't match!");
                        break;
                    }

                    UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[4]);

                    if(this.validationUtil.isValid(userRegisterDto)) {
                        this.userService.registerUser(userRegisterDto);
                    }else {
                        this.validationUtil.getViolations(userRegisterDto)
                                .stream().map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }



                    break;

                case "LoginUser":

                    break;

            }
        }


    }
}

















