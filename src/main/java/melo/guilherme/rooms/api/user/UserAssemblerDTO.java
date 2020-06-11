package melo.guilherme.rooms.api.user;

import java.util.Objects;

import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;

@Component
public class UserAssemblerDTO implements AssemblerDTO<User, UserDTO>{

	@Override
	public UserDTO assembleDTO(User entity) {
		
		if(Objects.isNull(entity)) {
			return null;
		}
		
		return new UserDTO(entity); 
	}

	@Override
	public User assembleEntity(UserDTO dto) {

		if(Objects.isNull(dto)) {
			throw new IllegalArgumentException("Usuário não pode ser nulo");
		}
		
		return new User.UserBuilder()
					   .id(dto.getId())
					   .username(dto.getUsername())
					   .email(dto.getEmail())
					   .password(dto.getPassword())
					   .name(dto.getName())
					   .build();
	}

}
