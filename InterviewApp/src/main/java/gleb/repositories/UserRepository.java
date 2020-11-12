package gleb.repositories;

import gleb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
