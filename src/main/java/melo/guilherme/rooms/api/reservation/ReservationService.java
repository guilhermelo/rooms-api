package melo.guilherme.rooms.api.reservation;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.room.RoomRepository;
import melo.guilherme.rooms.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private IReservationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {BusinessException.class, Exception.class})
    public void reserveRoom(Reservation reservation) {

        if(reservation.salaNaoExiste(roomRepository)) {
            throw BusinessException.of(Message.of("Sala informada não existe!", MessageType.VALIDATION));
        }

        if (reservation.usuarioNaoExiste(userRepository)) {
            throw BusinessException.of(Message.of("Usuário informado não existe!", MessageType.VALIDATION));
        }

        if (reservation.ehMaiorQueDozeHoras()) {
            throw BusinessException.of(Message.of("Não é possível reservar sala por mais de 12 horas!", MessageType.VALIDATION));
        }

        reservation.setId(UUID.randomUUID());
        repository.save(reservation);
    }

}
