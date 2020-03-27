package com.softuni.xmlparsingexerciseone.services.impl;


import com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto.ProductsDtoExFour;
import com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto.SoldProductsRootExFour;
import com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto.UserDtoExFour;
import com.softuni.xmlparsingexerciseone.dtos.exerciseFourDto.UsersRootDtoExerciseFour;
import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportUserDto;
import com.softuni.xmlparsingexerciseone.dtos.importDtos.ImportUsersDto;
import com.softuni.xmlparsingexerciseone.dtos.secondExerciseDto.UserDtoSecondExercise;
import com.softuni.xmlparsingexerciseone.dtos.secondExerciseDto.UsersRootElement;
import com.softuni.xmlparsingexerciseone.entities.User;
import com.softuni.xmlparsingexerciseone.repositories.UserRepository;
import com.softuni.xmlparsingexerciseone.services.UserService;
import com.softuni.xmlparsingexerciseone.utils.ValidationUtil;
import com.softuni.xmlparsingexerciseone.utils.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMap;
    private final XMLParser xmlParser;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMap, XMLParser xmlParser) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMap = modelMap;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        ImportUsersDto importedUsers=this.xmlParser.read(ImportUsersDto.class,"src/main/resources/files/users.xml");

        for (ImportUserDto importUserDto : importedUsers.getUsers()) {
            User user=this.modelMap.map(importUserDto,User.class);
            if(validationUtil.isValid(user)){
                this.userRepository.save(user);

            }else{
                validationUtil.getViolations(user)
                        .forEach(e-> System.out.println(e.getMessage()));
            }

        }

    }

    @Override
    public User getRandomBuyer() {
        Random random =new Random();

        long number=random.nextInt((int)this.userRepository.count()+15)+1;


        return this.userRepository.findById(number).orElse(null);
    }

    @Override
    public User getRandomSeller() {
        Random random =new Random();

        long number=random.nextInt((int)this.userRepository.count())+1;


        return this.userRepository.findById(number).get();

    }

    @Override
    public void secondExercise() throws JAXBException {
        UsersRootElement usersRootElement=new UsersRootElement();
        List<User> users = this.userRepository.secondExercise();

        Set<UserDtoSecondExercise> userDtoSecondExercise=users.stream().map(u->this.modelMap.map(u,UserDtoSecondExercise.class)).collect(Collectors.toSet());


        usersRootElement.setUsers(userDtoSecondExercise);

        this.xmlParser.write(usersRootElement,"src/main/resources/result.xml");

    }

    @Override
    public void exerciseFour() throws JAXBException {
        List<User>users=this.userRepository.exerciseFour();

        UsersRootDtoExerciseFour result=new UsersRootDtoExerciseFour();
        result.setCount(users.size());

        List<UserDtoExFour>userDtoExFour=users.stream().map(e->{
            int size=e.getSold().size();
            SoldProductsRootExFour soldProductsRootExFour=new SoldProductsRootExFour(size,e.getSold().stream().map(s->this.modelMap.map(s, ProductsDtoExFour.class)).collect(Collectors.toList()));

            return new UserDtoExFour(e.getFirstName(),e.getLastName(),e.getAge(),soldProductsRootExFour);
        }).collect(Collectors.toList());

        result.setUsers(userDtoExFour);

        System.out.println();
        this.xmlParser.write(result,"src/main/resources/result.xml");





    }
}
