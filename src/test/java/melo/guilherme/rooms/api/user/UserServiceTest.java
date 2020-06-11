package melo.guilherme.rooms.api.user;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;

	@Test
	public void shouldGetAllUsers() {
		
		User user1 = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		User user2 = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		User user3 = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		when(repository.findAll()).thenReturn(users);
		
		List<User> recorevedUsers = service.getAll();
		
		assertThat(recorevedUsers, Matchers.hasSize(3));
	}
	
	@Test
	public void shouldSaveUser() {
		
		User user = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		when(repository.save(user)).thenReturn(user);
		
		User savedUser = service.save(user);
		
		assertNotNull(savedUser.getId());
		assertNotEquals("senha", savedUser.getPassword());
	}
	
	@Test
	public void shouldUpdateUser() {
		
		User user = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		when(repository.save(user)).thenReturn(user);
		
		User updatedUser = service.update(user.getId(), user);
		
		assertNotNull(updatedUser.getId());
	}
	
	@Test(expected = BusinessException.class)
	public void shouldNotUpdateUserWithoutId() {
		
		User user = new User.UserBuilder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		String id = null;
		
		service.update(id, user);
	}
	
	@Test(expected = BusinessException.class)
	public void shouldNotDeleteUserWithNullId() {
		
		String id = null;
		
		service.delete(id);
	}
	
	@Test
	public void shouldDeleteUser() {
		
		String id = UUIDGenerator.generate();
		
		service.delete(id);
		
		verify(repository, times(1)).deleteById(id);
	}

}
