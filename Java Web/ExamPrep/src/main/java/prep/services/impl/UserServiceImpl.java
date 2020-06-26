package prep.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import prep.models.entities.User;
import prep.models.service.UserServiceModel;
import prep.repositories.UserRepository;
import prep.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {

        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(user -> this.modelMapper.map(user, UserServiceModel.class)).
                orElse(null);


    }
}
