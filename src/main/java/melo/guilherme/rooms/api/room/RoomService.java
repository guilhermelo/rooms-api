package melo.guilherme.rooms.api.room;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@Service
public class RoomService {

	private final RoomRepository repository;
	private final UserRepository userRepository;

	public RoomService(RoomRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = { BusinessException.class, Exception.class })
	public List<Room> getAll() {
		return repository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class })
	public Room save(Room room) {
		room.setId(UUIDGenerator.generate());
		Optional<User> user = userRepository.findById(room.getUser().getId());
		
		User userOfRoom = user.orElseThrow(() -> BusinessException.of(Message.of("Room's user doesn't exist!", MessageType.VALIDATION)));
		
		room.setUser(userOfRoom);
		Room savedRoom = repository.save(room);

		return savedRoom;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public Room update(String id, Room room) {

		if (Objects.isNull(id)) {
			throw BusinessException.of(Message.of("Identificator isn't exist", MessageType.VALIDATION));
		}

		room.setId(id);

		Room updatedRoom = repository.save(room);

		return updatedRoom;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public Room delete(String id) {

		if (Objects.isNull(id)) {
			throw BusinessException.of(Message.of("Identificator isn't exist", MessageType.VALIDATION));
		}

		repository.deleteById(id);

		return new Room.builder().id(id).build();
	}

	public Optional<Room> getById(String id) {
		
		if (Objects.isNull(id)) {
			throw BusinessException.of(Message.of("Identificator isn't exist", MessageType.VALIDATION));
		}

		return repository.findById(id);
	}
}
