package melo.guilherme.rooms.api.room;

import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;

@Component
public class RoomAssemblerDTO extends AssemblerDTO<Room, RoomDTO> {

	@Override
	public RoomDTO assembleDTO(Room entity) {
		return new RoomDTO(entity);
	}

	@Override
	public Room assembleEntity(RoomDTO dto) {
		return new Room.RoomBuilder().id(dto.getId())
									 .description(dto.getDescription())
									 .amountPeople(dto.getAmountPeople())
									 .build();
	}

}
