package spring.map.spingautomapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.map.spingautomapping.domain.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game getAllById(long id);

    void deleteGameById(long id);

}
