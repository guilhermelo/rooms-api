package melo.guilherme.rooms.api.reservation;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.room.IRoomRepository;
import melo.guilherme.rooms.api.user.UserRepository;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@Service
public class ReservaionService {

	@Autowired
	private IReservationRepository repository;

	@Autowired
	private IRoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { BusinessException.class, Exception.class })
	public void reserveRoom(Reservation reserve) {

		if (Objects.nonNull(reserve.getRoom())) {
			if (!roomRepository.existsById(reserve.getRoom().getId())) {
				BusinessException.of(new Message("Sala informada não existe!", MessageType.VALIDATION));
			}
		}

		if (Objects.nonNull(reserve.getUser())) {
			if (!userRepository.existsById(reserve.getUser().getId())) {
				BusinessException.of(new Message("Usuário informado não existe!", MessageType.VALIDATION));
			}
		}

		if (Objects.nonNull(reserve.getInitDate()) && Objects.nonNull(reserve.getFinalDate())) {
			if (ChronoUnit.HOURS.between(reserve.getInitDate(), reserve.getFinalDate()) > 12l) {
				BusinessException.of(new Message("Não é possível reservar sala por mais de 12 horas!", MessageType.VALIDATION));
			}
		}

		reserve.setId(UUIDGenerator.generate());
		repository.save(reserve);
	}
}
