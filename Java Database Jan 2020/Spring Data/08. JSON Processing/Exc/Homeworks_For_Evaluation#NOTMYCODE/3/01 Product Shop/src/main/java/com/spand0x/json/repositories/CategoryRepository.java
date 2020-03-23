package com.spand0x.json.repositories;


import com.spand0x.json.models.dtos.CategoryStatisticDto;
import com.spand0x.json.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.name," +
            "c.products.size," +
            "AVG(p.price)," +
            "SUM(p.price)" +
            "FROM Category as c " +
            "JOIN c.products as p " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size DESC ")
    List<String[]> getCategoriesStatistics();
}
