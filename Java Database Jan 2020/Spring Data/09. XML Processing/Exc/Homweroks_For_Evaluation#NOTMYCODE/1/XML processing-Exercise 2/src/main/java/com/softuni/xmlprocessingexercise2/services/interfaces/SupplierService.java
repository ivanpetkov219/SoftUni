package com.softuni.xmlprocessingexercise2.services.interfaces;



import com.softuni.xmlprocessingexercise2.entities.Supplier;

import javax.xml.bind.JAXBException;



public interface SupplierService {

    void seedSuppliers() throws JAXBException;

    Supplier getRandomSupplier();

    void thirdExercise() throws JAXBException;

}
