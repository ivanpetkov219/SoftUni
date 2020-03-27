package com.softuni.xmlparsingexerciseone.services;

import com.softuni.xmlparsingexerciseone.entities.User;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface UserService {

    void seedUsers() throws FileNotFoundException, JAXBException;

    User getRandomBuyer();

    User getRandomSeller();

    void secondExercise() throws JAXBException;

    void exerciseFour() throws JAXBException;
}
