package com.softuni.xmlprocessingexercise2.repositories;

import com.softuni.xmlprocessingexercise2.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
}
