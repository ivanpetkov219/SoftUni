package springdata.automapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.automapping.models.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


}
