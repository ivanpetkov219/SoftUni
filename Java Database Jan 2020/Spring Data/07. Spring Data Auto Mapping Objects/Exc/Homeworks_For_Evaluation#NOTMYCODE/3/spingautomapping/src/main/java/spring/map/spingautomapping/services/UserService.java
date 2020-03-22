package spring.map.spingautomapping.services;

import spring.map.spingautomapping.domain.dtos.UserLoginDto;
import spring.map.spingautomapping.domain.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean isLoggedUserAdmin();
}
