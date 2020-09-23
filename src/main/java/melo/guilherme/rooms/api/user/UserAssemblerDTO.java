package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;
import melo.guilherme.rooms.api.room.RoomAssemblerDTO;
import melo.guilherme.rooms.api.room.RoomDTO;

@Component
public class UserAssemblerDTO implements AssemblerDTO<User, UserDTO>{
	
	@Autowired
	private RoomAssemblerDTO roomAssembler;

	@Override
	public UserDTO assembleDTO(User entity) {
		
		if(Objects.isNull(entity)) {
			return null;
		}
		
		UserDTO dto = new UserDTO(entity);
		
		List<RoomDTO> rooms = roomAssembler.assembleManyDTOs(entity.getRooms());
		
		dto.setRooms(rooms);
		
		return dto;
	}

	@Override
	public User assembleEntity(UserDTO dto) {

		if(Objects.isNull(dto)) {
			throw new IllegalArgumentException("Usuário não pode ser nulo");
		}
		
		return new User.builder()
					   .id(dto.getId())
					   .username(dto.getUsername())
					   .email(dto.getEmail())
					   .password(dto.getPassword())
					   .name(dto.getName())
					   .build();
	}

}
