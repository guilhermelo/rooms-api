package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import melo.guilherme.rooms.api.room.BusinessException;
import melo.guilherme.rooms.api.util.crypt.CryptUtil;
import melo.guilherme.rooms.api.util.uuid.IDGenerator;

@Service
public class UserService {

	@Autowired
	private IUserRepository repository;

	public List<User> getAll() {

		List<User> users = repository.findAll();

		return users;
	}

	public User save(User user) {
		user.setPassword(CryptUtil.encryptPassword(user.getPassword()));
		user.setId(IDGenerator.generateID());
		User savedUser = repository.save(user);

		return savedUser;
	}

	public User update(String id, User user) throws Exception {

		if (Objects.isNull(id)) {
			BusinessException.of("Identificador nulo");
		}

		user.setId(id);

		User updatedUser = repository.save(user);

		return updatedUser;
	}

	public User delete(String id) throws Exception {

		if (Objects.isNull(id)) {
			BusinessException.of("Identificador nulo");
		}

		repository.deleteById(id);

		return new User.UserBuilder().id(id).build();
	}

}
