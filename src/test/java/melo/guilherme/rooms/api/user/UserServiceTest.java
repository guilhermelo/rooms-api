package melo.guilherme.rooms.api.user;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.room.Room;
import melo.guilherme.rooms.api.room.RoomRepository;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private RoomRepository roomRepository;

	@Test
	public void shouldGetAllUsers() {
		
		User user1 = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		User user2 = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		User user3 = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		Room room1 = new Room.builder().id(UUIDGenerator.generate()).description("Room 1").user(user1).build();
		Room room2 = new Room.builder().id(UUIDGenerator.generate()).description("Room 2").user(user2).build();
		Room room3 = new Room.builder().id(UUIDGenerator.generate()).description("Room 3").user(user3).build();
		
		List<Room> rooms = Arrays.asList(room1, room2, room3);
		
		when(roomRepository.findAll()).thenReturn(rooms);
		
		List<User> recorevedUsers = service.getAll();
		
		assertThat(recorevedUsers, Matchers.hasSize(3));
	}
	
	@Test
	public void shouldSaveUser() {
		
		User user = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		when(repository.save(user)).thenReturn(user);
		
		User savedUser = service.save(user);
		
		assertNotNull(savedUser.getId());
		assertNotEquals("senha", savedUser.getPassword());
	}
	
	@Test
	public void shouldUpdateUser() {
		
		User user = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
		when(repository.save(user)).thenReturn(user);
		
		User updatedUser = service.update(user.getId(), user);
		
		assertNotNull(updatedUser.getId());
	}
	
	@Test(expected = BusinessException.class)
	public void shouldNotUpdateUserWithoutId() {
		
		User user = new User.builder().id(UUIDGenerator.generate()).username("usuario").password("senha").build();
		
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
	
	
	@Test
	public void shouldFindUserByUsername() {
		
		final String username = "guilherme";
		User user = new User.builder().username(username).build();
		Example<User> where = Example.of(user);
		when(repository.findAll(where)).thenReturn(Arrays.asList(user));
		
		User recoveredUser = service.getByUsername(username);
		
		assertNotNull(recoveredUser.getUsername());
	}
	
	
	@Test
	public void shouldNotFindUserByUsernameWhenUserDoesntExist() {
		
		final String username = "gabriel";
		User user = new User.builder().username(username).build();
		Example<User> where = Example.of(user);
		when(repository.findAll(where)).thenReturn(Collections.emptyList());
		
		User recoveredUser = service.getByUsername(username);
		
		assertNull(recoveredUser.getUsername());
	}
 
}
