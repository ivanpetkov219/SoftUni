package com.softuni.jsonprocessingex.repositories;

import com.softuni.jsonprocessingex.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User AS u " + "JOIN Product AS p ON p.seller.id = u.id " + "WHERE p.buyer IS NOT NULL " + "GROUP BY u.id " + "ORDER BY u.lastName, u.firstName")
    List<User> getAllSellers();

    @Query("SELECT u FROM User AS u " + "JOIN Product AS p ON p.seller.id = u.id " + "WHERE p.buyer IS NOT NULL " + "GROUP BY u.id " + "ORDER BY count(p.buyer) DESC , u.lastName")
    List<User> getAllSellersByCount();
}
