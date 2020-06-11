package melo.guilherme.rooms.api.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.generic.AssemblerDTO;
import melo.guilherme.rooms.api.room.RoomAssemblerDTO;
import melo.guilherme.rooms.api.user.UserAssemblerDTO;
import melo.guilherme.rooms.api.util.date.DateUtil;

@Component
public class ReservationAssemblerDTO implements AssemblerDTO<Reservation, ReservationDTO>{

	@Autowired
	private UserAssemblerDTO userAssembler;
	
	@Autowired
	private RoomAssemblerDTO roomAssembler;
	
	@Autowired 
	private DateUtil dateUtil;
	
	@Override
	public ReservationDTO assembleDTO(Reservation entity) {
		
		ReservationDTO dto = new ReservationDTO(entity);
		
		dto.setUser(userAssembler.assembleDTO(entity.getUser()));
		
		dto.setRoom(roomAssembler.assembleDTO(entity.getRoom()));
		
		return dto;
	}

	@Override
	public Reservation assembleEntity(ReservationDTO dto) {
		return new Reservation.ReservationBuilder().id(dto.getId())
										   .initDate(dateUtil.parseDatetime(dto.getInitDate()))
										   .finalDate(dateUtil.parseDatetime(dto.getFinalDate()))
										   .room(roomAssembler.assembleEntity(dto.getRoom()))
										   .user(userAssembler.assembleEntity(dto.getUser()))
										   .build();
	}

}
