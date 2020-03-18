package springdata.automapping.services;

import springdata.automapping.models.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
}
