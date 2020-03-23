package com.softuni.jsonprocessingex.services.impl;

import com.softuni.jsonprocessingex.models.dtos.*;
import com.softuni.jsonprocessingex.models.entities.Product;
import com.softuni.jsonprocessingex.models.entities.User;
import com.softuni.jsonprocessingex.repositories.UserRepository;
import com.softuni.jsonprocessingex.services.UserService;
import com.softuni.jsonprocessingex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        if (this.userRepository.count() != 0) {              // PROVERKA za 2ra !!!
            return;
        }

        Arrays.stream(userSeedDtos).forEach(userSeedDto -> {
            if (this.validationUtil.isValid(userSeedDto)) {
                User user = this.modelMapper.map(userSeedDto, User.class);
                this.userRepository.saveAndFlush(user);
            } else {
                this.validationUtil.violations(userSeedDto).stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            }
        });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long randomId = random.nextInt((int) userRepository.count()) + 1;

        return this.userRepository.getOne(randomId);
    }

    @Override
    public List<UserSoldDto> getSellers() {
        List<User> sellersList = this.userRepository.getAllSellers();

        List<UserSoldDto> sellers = new ArrayList<>();

        for (User user : sellersList) {
            List<ProductsWithBuyersDto> goods = new ArrayList<>();

            for (Product product : user.getSold()) {
                if (product.getBuyer() != null) {
                    ProductsWithBuyersDto productsDto = new ProductsWithBuyersDto(
                            product.getName(),
                            product.getPrice(),
                            product.getBuyer().getFirstName(),
                            product.getBuyer().getLastName());

                    goods.add(productsDto);
                }
            }

            UserSoldDto seller = this.modelMapper.map(user, UserSoldDto.class);
            seller.setSoldProducts(goods);
            sellers.add(seller);
        }

        return sellers;
    }

    @Override
    public UsersViewDto getAllSellersByCount() {
        List<User> sellersList = this.userRepository.getAllSellersByCount();

        List<SellersProductCountDto> users = new ArrayList<>();

        for (User user : sellersList) {
            List<ProductViewDto> products = new ArrayList<>();

            for (Product product : user.getSold()) {
                if (product.getBuyer() != null) {
                    ProductViewDto productsDto = new ProductViewDto(
                            product.getName(),
                            product.getPrice());

                    products.add(productsDto);
                }
            }
            SoldProductsDto soldProduct = new SoldProductsDto(products.size(), products);
            SellersProductCountDto currentUser = this.modelMapper.map(user, SellersProductCountDto.class);
            currentUser.setSoldProducts(soldProduct);
            users.add(currentUser);
        }

        UsersViewDto usersViewDto = new UsersViewDto();
        usersViewDto.setUsersCount(users.size());
        usersViewDto.setUsers(users);

        return usersViewDto;
    }
}
