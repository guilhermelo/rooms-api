package melo.guilherme.rooms.api.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{

	Optional<User> findByUsername(String username);
	
}
