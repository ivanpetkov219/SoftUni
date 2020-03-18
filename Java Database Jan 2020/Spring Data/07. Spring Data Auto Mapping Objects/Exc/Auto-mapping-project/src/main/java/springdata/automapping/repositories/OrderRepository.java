package springdata.automapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.automapping.models.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
