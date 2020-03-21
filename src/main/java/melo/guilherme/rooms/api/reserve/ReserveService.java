package melo.guilherme.rooms.api.reserve;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import melo.guilherme.rooms.api.room.BusinessException;
import melo.guilherme.rooms.api.room.IRoomRepository;
import melo.guilherme.rooms.api.user.IUserRepository;

@Service
public class ReserveService {

	@Autowired
	private IReserveRepository repository;
	
	@Autowired
	private IRoomRepository roomRepository;
	
	@Autowired
	private IUserRepository userRepository;

	public void reserveRoom(Reserve reserve) {
		
		if(Objects.nonNull(reserve.getRoom())) {
			if (!roomRepository.existsById(reserve.getRoom().getId())) {
				BusinessException.of("Sala informada não existe!");
			}
		}
		
		if(Objects.nonNull(reserve.getUser())) {
			if(!userRepository.existsById(reserve.getUser().getId())) {
				BusinessException.of("Usuário informado não existe!");
			}
		}

		if (Objects.nonNull(reserve.getInitDate()) && Objects.nonNull(reserve.getFinalDate())) {
			if(reserve.getInitDate().getDayOfMonth() != reserve.getFinalDate().getDayOfMonth()) {
				BusinessException.of("Dia inicial diferente de dia final!");
			}
			
			if(ChronoUnit.HOURS.between(reserve.getInitDate(), reserve.getFinalDate()) > 12l) {
				BusinessException.of("Não é possível reservar sala por mais de 12 horas!");
			}
		}

		repository.save(reserve);
	}
}
