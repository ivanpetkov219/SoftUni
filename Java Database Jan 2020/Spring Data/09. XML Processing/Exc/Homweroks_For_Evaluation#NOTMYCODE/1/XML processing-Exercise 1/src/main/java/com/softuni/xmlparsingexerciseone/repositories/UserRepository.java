package com.softuni.xmlparsingexerciseone.repositories;

import com.softuni.xmlparsingexerciseone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT u FROM User as u WHere u.sold.size>=1 ORDER BY u.lastName ,u.firstName")
    List<User>secondExercise();

    @Query("SELECT u FROM User u WHERE u.sold.size>=1 ORDER BY u.sold.size DESC ,u.lastName ASC")
    List<User>exerciseFour();
}
