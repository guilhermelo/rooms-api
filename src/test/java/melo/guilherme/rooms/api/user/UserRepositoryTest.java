package melo.guilherme.rooms.api.user;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@Profile("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TestEntityManager manager;

	private User user;

	@Before
	public void setup() {
		user = new User.UserBuilder().id(UUIDGenerator.generate()).username("guilherme").password("guilherme")
				.email("guilherme@guilherme.com.br").build();

		manager.persist(user);
	}

	@Test
	public void shouldReturUser() {
		repository.save(user);

		Optional<User> optional = repository.findByUsername(user.getUsername());

		assertTrue(optional.isPresent());
	}

	@After
	public void tearDown() {
		repository.delete(user);
	}

}
