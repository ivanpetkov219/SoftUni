package com.softuni.xmlprocessingexercise2.repositories;

import com.softuni.xmlprocessingexercise2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer as c")
    List<Customer> firstExercise();


    @Query("SELECT c FROM Customer as c WHERE c.sales.size>=1")
    List<Customer> exerciseFive();
}
