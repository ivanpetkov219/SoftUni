package prep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prep.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
