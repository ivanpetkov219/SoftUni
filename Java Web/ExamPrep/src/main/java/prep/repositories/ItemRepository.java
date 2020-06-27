package prep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prep.models.entities.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
