package melo.guilherme.rooms.api.room;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

	@Mock
	private RoomRepository repository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private RoomService service;

	@Test
	public void shouldSaveRoomWithUUID() {

		Room room = new Room.builder().id(UUIDGenerator.generate())
				.description("Sala para grandes reuniões com até 15 pessoas").amountPeople(15).name("Sala 1")
				.user(new User.builder().id(UUIDGenerator.generate()).build()).build();

		when(userRepository.findById(room.getUser().getId()))
				.thenReturn(Optional.of(new User.builder().username("guilherme").build()));
		when(repository.save(room)).thenReturn(new Room.builder().id(UUIDGenerator.generate()).build());

		room = service.save(room);

		assertNotNull(room.getId());
	}

	@Test
	public void shouldThrowExceptionWhenSaveRoomWithoutUser() {
		Room room = new Room.builder()
							.id(UUIDGenerator.generate())
							.description("Sala para grandes reuniões com até 15 pessoas")
							.amountPeople(15)
							.name("Sala 1")
							.user(new User.builder().id(UUIDGenerator.generate()).build())
							.build();
		when(userRepository.findById(room.getUser().getId())).thenReturn(Optional.empty());
		
		try {
			room = service.save(room);
			Assert.fail();
		} catch(BusinessException e) {
			assertEquals("Room's user doesn't exist!", e.getMessage());
		}
	}

	@Test
	public void shouldUpdateRoom() {

		Room room = new Room.builder().name("Sala 2").description("Sala para grandes reuniões com até 15 pessoas")
				.amountPeople(15).build();

		String id = UUIDGenerator.generate();
		room.setId(id);
		when(repository.save(room)).thenReturn(room);

		room = service.update(id, room);

		assertEquals("Sala 2", room.getName());
	}

	@Test(expected = BusinessException.class)
	public void shouldNotDeleteRoomWithNullId() {
		String id = null;

		service.delete(id);

	}

	@Test(expected = BusinessException.class)
	public void shouldNotUpdateRoomWithNullId() {
		String id = null;

		Room room = new Room.builder().name("Sala 2").description("Sala para grandes reuniões com até 15 pessoas")
				.amountPeople(15).build();

		service.update(id, room);
	}

	@Test
	public void shouldDeleteRoom() {

		String id = UUIDGenerator.generate();

		service.delete(id);

		verify(repository, times(1)).deleteById(id);
	}

	@Test
	public void shoudSelectAllRooms() {
		Room room1 = new Room.builder().id(UUIDGenerator.generate()).name("Sala 1")
				.description("Sala 1 com ar condicionado").amountPeople(15).build();

		Room room2 = new Room.builder().id(UUIDGenerator.generate()).name("Sala 2")
				.description("Sala 2 com ar condicionado").amountPeople(15).build();

		Room room3 = new Room.builder().id(UUIDGenerator.generate()).name("Sala 3")
				.description("Sala 3 com ar condicionado").amountPeople(15).build();

		List<Room> rooms = Arrays.asList(room1, room2, room3);

		when(repository.findAll()).thenReturn(rooms);

		List<Room> recoveredRooms = service.getAll();

		assertEquals(3, recoveredRooms.size());
	}

	@Test
	public void shouldGetRoomId() {

		String id = UUIDGenerator.generate();

		Room room = new Room.builder().name("Sala 2").description("Sala para grandes reuniões com até 15 pessoas")
				.amountPeople(15).build();

		when(repository.findById(id)).thenReturn(Optional.of(room));

		service.getById(id);

		assertNotNull(room);
	}

	@Test
	public void shouldNotGetRoomWithoutId() {

		String id = null;

		try {
			service.getById(id);
			fail();
		} catch (BusinessException e) {
			assertEquals("Identificator isn't exist", e.getMessages().get(0).getMessage());
		}
	}

}
