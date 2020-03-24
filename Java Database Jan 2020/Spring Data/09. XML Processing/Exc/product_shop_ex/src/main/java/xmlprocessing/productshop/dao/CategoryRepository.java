package xmlprocessing.productshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xmlprocessing.productshop.models.dtos.CategoryRootSeedDto;
import xmlprocessing.productshop.models.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
