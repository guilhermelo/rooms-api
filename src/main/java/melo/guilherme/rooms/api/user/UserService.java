package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.room.Room;
import melo.guilherme.rooms.api.room.RoomRepository;
import melo.guilherme.rooms.api.util.crypt.CryptUtil;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@Service
public class UserService {

	private final UserRepository repository;
	private final RoomRepository roomRepository;

	public UserService(UserRepository repository, RoomRepository roomRepository) {
		this.repository = repository;
		this.roomRepository = roomRepository;
	}

	public List<User> getAll() {

		List<Room> rooms = roomRepository.findAll();

		List<User> users = rooms.stream().collect(Collectors.groupingBy(Room::getUser)).entrySet().stream()
				.map(element -> {
					final User user = element.getKey();
					final List<Room> userRooms = element.getValue();

					userRooms.forEach(user::addRoom);

					return user;
				}).collect(Collectors.toList());

		return users;
	}

	public User getByUsername(String username) {

		Example<User> filter = Example.of(new User.builder().username(username).build());

		List<User> users = repository.findAll(filter);

		Optional<User> user = users.stream().findFirst();

		return user.orElse(new User.builder().build());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public User save(User user) {
		user.setPassword(CryptUtil.encryptPassword(user.getPassword()));
		user.setId(UUIDGenerator.generate());
		repository.save(user);

		return user;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public User update(String id, User user) {

		if (Objects.isNull(id)) {
			throw BusinessException.of(Message.of("Identificador nulo", MessageType.VALIDATION));
		}

		user.setId(id);

		User updatedUser = repository.save(user);

		return updatedUser;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public User delete(String id) {

		if (Objects.isNull(id)) {
			throw BusinessException.of(Message.of("Identificador nulo", MessageType.VALIDATION));
		}

		repository.deleteById(id);

		return new User.builder().id(id).build();
	}

}
