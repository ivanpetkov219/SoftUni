package com.xmlproductshop.repositories;

import com.xmlproductshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByFirstNameAndLastNameAndAge(String firstName,String lastName,int age);
}

