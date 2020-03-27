package com.example.xmlprocessingdemo.repository;

import com.example.xmlprocessingdemo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByMakeAndModelAndTravelledDistance(String make, String model, Integer distance);
}
