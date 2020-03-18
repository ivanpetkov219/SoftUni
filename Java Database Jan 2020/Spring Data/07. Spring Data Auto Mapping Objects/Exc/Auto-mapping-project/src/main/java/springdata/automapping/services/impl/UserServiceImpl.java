package springdata.automapping.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.automapping.models.dtos.UserRegisterDto;
import springdata.automapping.models.entities.AccessRight;
import springdata.automapping.models.entities.User;
import springdata.automapping.repositories.UserRepository;
import springdata.automapping.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setAccessRight(this.userRepository.count() == 0 ? AccessRight.ADMINISTRATOR : AccessRight.USER);

        System.out.println();

        this.userRepository.saveAndFlush(user);

    }
}
