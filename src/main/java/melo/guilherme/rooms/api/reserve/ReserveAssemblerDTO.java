package melo.guilherme.rooms.api.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;
import melo.guilherme.rooms.api.room.RoomAssemblerDTO;
import melo.guilherme.rooms.api.user.UserAssemblerDTO;
import melo.guilherme.rooms.api.util.date.DateUtil;

@Component
public class ReserveAssemblerDTO extends AssemblerDTO<Reserve, ReserveDTO>{

	@Autowired
	private UserAssemblerDTO userAssembler;
	
	@Autowired
	private RoomAssemblerDTO roomAssembler;
	
	@Override
	public ReserveDTO assembleDTO(Reserve entity) {
		
		ReserveDTO dto = new ReserveDTO(entity);
		
		dto.setUser(userAssembler.assembleDTO(entity.getUser()));
		
		dto.setRoom(roomAssembler.assembleDTO(entity.getRoom()));
		
		return dto;
	}

	@Override
	public Reserve assembleEntity(ReserveDTO dto) {
		return new Reserve.ReserveBuilder().id(dto.getId())
										   .initDate(DateUtil.parseDatetime(dto.getInitDate()))
										   .finalDate(DateUtil.parseDatetime(dto.getFinalDate()))
										   .room(roomAssembler.assembleEntity(dto.getRoom()))
										   .user(userAssembler.assembleEntity(dto.getUser()))
										   .build();
	}

}
