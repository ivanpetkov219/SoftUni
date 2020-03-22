package com.example.demo.services.impls;

import com.example.demo.dtos.GameViewDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.dtos.UserLoginDto;
import com.example.demo.dtos.UserRegisterDto;
import com.example.demo.entities.Game;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper
                .map(userRegisterDto, User.class);

        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);

        userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository
                .findByEmail(userLoginDto.getEmail());

        if (user == null) {
            System.out.println("Incorrect username / password");
        } else {
            this.userDto = this.modelMapper
                    .map(user, UserDto.class);
            System.out.printf("Successfully logged in %s\n", userDto.getFullName());
        }
    }

    @Override
    public void logoutUser() {
        if (this.userDto != null) {
            String fullName = userDto.getFullName();
            this.userDto = null;
            System.out.printf("User %s successfully logged out\n", fullName);
        } else {
            System.out.println("Cannot log out. No user was logged in.\n");
        }
    }

    @Override
    public boolean isLoggedUserIsAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }

    @Transactional
    @Override
    public void purchaseGame(Game game) {
        if (this.userDto == null) {
            System.out.println("No user logged in");
            return;
        }

        User user = this.userRepository.findByFullName(this.userDto.getFullName());
        user.getGames().add(game);
        this.userRepository.saveAndFlush(user);
        System.out.println("Successfully purchased " + game.getTitle());

    }

    @Override
    public List<GameViewDto> getOwnedGames() {
        if (this.userDto == null) {
            System.out.println("No user logged in");
        }
        User user = this.userRepository.findByFullName(this.userDto.getFullName());
        return user.getGames().stream()
                .map(game -> this.modelMapper.map(game, GameViewDto.class))
                .collect(Collectors.toList());
    }
}
