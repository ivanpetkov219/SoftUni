package com.spand0x.cardealer.repositories;

import com.spand0x.cardealer.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> getAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
    List<Car> getAllByIdNotNull();
}
