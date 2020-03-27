package com.softuni.xmlprocessingexercise2.services.interfaces;

import com.softuni.xmlprocessingexercise2.entities.Car;

import javax.xml.bind.JAXBException;

public interface CarService {

    void seedCars() throws JAXBException;

    Car getRandomCar();

    void secondExercise() throws JAXBException;

    void exerciseFour() throws JAXBException;
}
