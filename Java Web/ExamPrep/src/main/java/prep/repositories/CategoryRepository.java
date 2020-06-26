package prep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prep.models.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
