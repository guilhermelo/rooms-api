package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.util.crypt.CryptUtil;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> getAll() {

		List<User> users = repository.findAll();

		return users;
	}
	
	public User getByUsername(String username) {
		
		Example<User> filter = Example.of(new User.UserBuilder().username(username).build());

		List<User> users = repository.findAll(filter);
		
		Optional<User> user = users.stream().findFirst();

		return user.orElse(new User.UserBuilder().build());
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

		if(Objects.isNull(id)) {
			BusinessException.of(new Message("Identificador nulo", MessageType.VALIDATION));
		}

		user.setId(id);

		User updatedUser = repository.save(user);

		return updatedUser;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public User delete(String id) {

		if(Objects.isNull(id)) {
			BusinessException.of(new Message("Identificador nulo", MessageType.VALIDATION));
		}

		repository.deleteById(id);

		return new User.UserBuilder().id(id).build();
	}

}
