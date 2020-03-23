package com.spand0x.json.services;


import com.spand0x.json.models.dtos.*;
import com.spand0x.json.models.entities.Product;
import com.spand0x.json.models.entities.User;
import com.spand0x.json.repositories.UserRepository;
import com.spand0x.json.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUser(UserSeedDto usersSeedDto) {
        if (this.validationUtil.isValid(usersSeedDto)) {
            User user = this.modelMapper.map(usersSeedDto, User.class);
            this.userRepository.saveAndFlush(user);
        } else {
            this.validationUtil.violations(usersSeedDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        int randomId = random.nextInt((int) this.userRepository.count()) + 1;
        return this.userRepository.getOne((long) randomId);
    }


    @Override
    public List<UserSoldDto> getUsersWithSoldProducts() {
        Set<User> users = this.userRepository.getUsersWithAtLeastOneSoldProduct();
        return users.stream()
                .map(u -> {
                    UserSoldDto user = this.modelMapper.map(u, UserSoldDto.class);
                    Set<ProductBoughtDto> products = u.getSold()
                            .stream()
                            .filter(product -> product.getBuyer() != null)
                            .map(p -> this.modelMapper.map(p, ProductBoughtDto.class))
                            .collect(Collectors.toSet());
                    user.setSoldProducts(products);
                    return user;
                })
                .collect(Collectors.toList());

    }

    @Override
    public UserCountDto getUsersAndProducts() {
        List<User> users = this.userRepository.getUsersWithAtLeastOneSellingProduct();
        System.out.println("Asd");
        UserCountDto userCountDto = new UserCountDto();
        for (User user : users) {
            UserProductsDto userProductsDto = this.modelMapper.map(user, UserProductsDto.class);
            ProductCountDto productCountDto = new ProductCountDto();
            for (Product product : user.getSold()) {
                ProductNamePriceDto productNamePriceDto = this.modelMapper.map(product,ProductNamePriceDto.class);
                if(productNamePriceDto != null) {
                    productCountDto.getProducts().add(productNamePriceDto);
                }

            }
            userProductsDto.setSoldProducts(productCountDto);
            productCountDto.setCount(productCountDto.getProducts().size());
            userCountDto.getUsers().add(userProductsDto);
        }

        userCountDto.setUserCount(userCountDto.getUsers().size());
        return userCountDto;
    }

}
