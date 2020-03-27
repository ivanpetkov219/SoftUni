package com.softuni.xmlprocessingexercise2.repositories;
import com.softuni.xmlprocessingexercise2.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car>findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);


    List<Car>getAllBy();


}
