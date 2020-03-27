package com.xmlproductshop.repositories;

import com.xmlproductshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findAllByName(String name);


}
