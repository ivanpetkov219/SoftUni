package com.spand0x.cardealer.repositories;

import com.spand0x.cardealer.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> getAllByIdIsNotNullOrderByBirthDateAscYoungDriverAsc();
//    SELECT c.name, COUNT(s.car_id), SUM(p.price)
//From customers as c
//JOIN sales s on c.id = s.customer_id
//JOIN cars c2 on s.car_id = c2.id
//JOIN cars_parts cp on c2.id = cp.cars_id
//JOIN parts p on cp.parts_id = p.id
//GROUP BY c.id;
    @Query("SELECT c.name,c.sales.size, SUM(p.price) FROM Customer c " +
            "JOIN Sale s ON c.id = s.customer.id " +
            "JOIN Car ca ON s.car.id = ca.id " +
            "JOIN ca.parts p " +
            "group by c.id " +
            "ORDER BY SUM(p.price)DESC , c.sales.size DESC")
    List<List<String>> getCustomersWithSales();
}
