package com.xmlproductshop.service.impl;

import com.xmlproductshop.dtos.UserSeedDto;
import com.xmlproductshop.entities.User;
import com.xmlproductshop.repositories.UserRepository;
import com.xmlproductshop.service.UserService;
import com.xmlproductshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    public UserServiceImpl(ValidationUtil validationUtil, UserRepository userRepository, ModelMapper modelMapper, Random random) {
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.random = random;
    }

    @Override
    public void seedUser(List<UserSeedDto> userSeedDtos) {
        userSeedDtos
                .forEach(userSeedDto -> {
                    if (this.validationUtil.isValid(userSeedDto)) {
                        if (this.userRepository.findByFirstNameAndLastNameAndAge(
                                userSeedDto.getFirstName(), userSeedDto.getLastName(),
                                userSeedDto.getAge()) == null) {
                            User user = this.modelMapper
                                    .map(userSeedDto, User.class);

                            this.userRepository.saveAndFlush(user);
                        } else {
                            System.out.println("Already in DB");
                        }

                    } else {
                        this.validationUtil
                                .getViolations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public User getRandomUser() {
        long randomUser = this.random.nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne(randomUser);
    }
}
