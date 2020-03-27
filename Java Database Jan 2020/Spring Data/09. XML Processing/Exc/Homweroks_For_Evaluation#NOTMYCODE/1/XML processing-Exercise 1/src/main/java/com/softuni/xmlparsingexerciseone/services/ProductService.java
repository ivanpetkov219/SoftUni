package com.softuni.xmlparsingexerciseone.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface ProductService {
    void seedProducts() throws FileNotFoundException, JAXBException;

    void firstExercise(double low, double high) throws JAXBException;
}
