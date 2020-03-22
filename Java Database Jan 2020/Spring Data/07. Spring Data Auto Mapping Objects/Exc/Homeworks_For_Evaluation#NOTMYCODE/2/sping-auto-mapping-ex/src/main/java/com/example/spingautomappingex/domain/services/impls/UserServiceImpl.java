package com.example.spingautomappingex.domain.services.impls;

import com.example.spingautomappingex.domain.dtos.UserDto;
import com.example.spingautomappingex.domain.dtos.UserLoginDto;
import com.example.spingautomappingex.domain.dtos.UserRegisterDto;
import com.example.spingautomappingex.domain.entities.Role;
import com.example.spingautomappingex.domain.entities.User;
import com.example.spingautomappingex.domain.repositories.UserRepository;
import com.example.spingautomappingex.domain.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (user == null){
            System.out.println("Incorrect username / password");
        } else {
            this.userDto = this.modelMapper.map(user, UserDto.class);
            System.out.printf("Successfully logged in %s%n", user.getFullName());
        }

    }

    @Override
    public void logout() {
        if (this.userDto == null){
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            String name = this.userDto.getFullName();
            this.userDto = null;
            System.out.printf("User %s successfully logged out%n", name);
        }
    }

    @Override
    public boolean isLoggedUserIsAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }
}
