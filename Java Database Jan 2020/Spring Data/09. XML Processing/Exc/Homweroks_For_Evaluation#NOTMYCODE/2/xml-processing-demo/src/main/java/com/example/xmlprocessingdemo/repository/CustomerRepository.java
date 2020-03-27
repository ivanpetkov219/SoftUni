package com.example.xmlprocessingdemo.repository;

import com.example.xmlprocessingdemo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByNameAndBirthDate(String name, LocalDateTime date);

    @Query("select c from Customer as c " +
            "order by c.birthDate,c.youngDriver")
    List<Customer> findAllByBirthDateAndIsYoungDriver();

}


