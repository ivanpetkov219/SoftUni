package com.softuni.xmlparsingexerciseone.repositories;


import com.softuni.xmlparsingexerciseone.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category as c ORDER BY c.products.size ")
    List<Category> thirdExercise();
}
