package melo.guilherme.rooms.api.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TestEntityManager manager;

	private User user;

	@Before
	public void setup() {
		user = new User.builder()
					   .id(UUIDGenerator.generate())
					   .username("guilherme")
					   .password("guilherme")
					   .email("guilherme@guilherme.com.br")
					   .build();
	}

	@Test
	public void shouldSaveUser() {
		manager.persist(user);
		
		List<User> users = repository.findAll(); 
		
		assertThat(users, Matchers.hasSize(1));
	}

	@Test
	public void shouldReturUser() {
		repository.save(user);

		Optional<User> optional = repository.findByUsername(user.getUsername());

		assertTrue(optional.isPresent());
		assertEquals("guilherme", optional.get().getUsername());
	}
}
