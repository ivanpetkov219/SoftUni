package com.softuni.xmlprocessingexercise2.services.interfaces;

import com.softuni.xmlprocessingexercise2.entities.Part;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Set;

public interface PartService {

    void seedParts() throws JAXBException;

    List<Part> getRandomParts();

}
