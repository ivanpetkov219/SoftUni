package springdata.automapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.automapping.models.dtos.UserRegisterDto;
import springdata.automapping.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
