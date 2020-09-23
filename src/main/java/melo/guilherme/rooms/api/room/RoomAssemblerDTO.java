package melo.guilherme.rooms.api.room;

import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;
import melo.guilherme.rooms.api.user.User;

@Component
public class RoomAssemblerDTO implements AssemblerDTO<Room, RoomDTO> {

	@Override
	public RoomDTO assembleDTO(Room entity) {
		return new RoomDTO(entity);
	}

	@Override
	public Room assembleEntity(RoomDTO dto) {
		return new Room.builder().id(dto.getId())
									 .name(dto.getName())	
									 .description(dto.getDescription())
									 .amountPeople(dto.getAmountPeople())
									 .user(new User.builder().id(dto.getUserId()).build())
									 .build();
	}

}
