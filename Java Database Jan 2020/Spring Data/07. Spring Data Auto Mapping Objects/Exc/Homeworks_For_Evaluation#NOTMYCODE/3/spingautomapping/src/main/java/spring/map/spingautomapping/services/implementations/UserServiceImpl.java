package spring.map.spingautomapping.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.map.spingautomapping.domain.dtos.UserDto;
import spring.map.spingautomapping.domain.dtos.UserLoginDto;
import spring.map.spingautomapping.domain.dtos.UserRegisterDto;
import spring.map.spingautomapping.domain.entities.Role;
import spring.map.spingautomapping.domain.entities.User;
import spring.map.spingautomapping.repositories.UserRepository;
import spring.map.spingautomapping.services.UserService;

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

        System.out.println();

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmail(userLoginDto.getEmail());

        if (user == null) {
            System.out.println("Incorrect username/password");
        }else {
            this.userDto = this.modelMapper.map(user, UserDto.class);
            System.out.printf("Successfully logged in %s%n", user.getFullName());
        }
    }

    @Override
    public void logout() {
        if(this.userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");
        }else {
            String res = this.userDto.getFullName();
            this.userDto = null;
            System.out.printf("User %s successfully logged out%n", res);
        }
    }

    @Override
    public boolean isLoggedUserAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }
}
