package com.spand0x.json.repositories;

import com.spand0x.json.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User as u " +
            "JOIN Product as p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName,u.firstName")
    Set<User> getUsersWithAtLeastOneSoldProduct();

    @Query("SELECT u FROM User u " +
            "JOIN Product p " +
            "ON p.seller.id = u.id " +
            "WHERE u.sold.size > 0 " +
            "GROUP BY u.id " +
            "ORDER BY u.sold.size DESC, u.lastName ASC")
    List<User> getUsersWithAtLeastOneSellingProduct();

}
