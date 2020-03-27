package com.softuni.xmlparsingexerciseone.services;

import com.softuni.xmlparsingexerciseone.entities.Category;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws FileNotFoundException, JAXBException;

    Set<Category> generateRandomCategories();

    void thirdExercise() throws JAXBException;

}
