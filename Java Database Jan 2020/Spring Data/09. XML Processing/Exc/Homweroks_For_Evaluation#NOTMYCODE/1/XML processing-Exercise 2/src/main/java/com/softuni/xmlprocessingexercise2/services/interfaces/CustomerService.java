package com.softuni.xmlprocessingexercise2.services.interfaces;

import com.softuni.xmlprocessingexercise2.entities.Customer;

import javax.xml.bind.JAXBException;

public interface CustomerService {
    void seedCustomers() throws JAXBException;

    Customer getRandomCustomer();

    long getCount();

    void firstExercise() throws JAXBException;

    void exerciseFive() throws JAXBException;

}
