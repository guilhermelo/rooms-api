package melo.guilherme.rooms.api.reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import melo.guilherme.rooms.api.room.RoomDTO;
import melo.guilherme.rooms.api.user.UserDTO;

public class ReservationDTO {
    private String id;
    private RoomDTO room;
    private UserDTO user;
    private String initDate;
    private String finalDate;

    public ReservationDTO() {
        super();
    }

    public Reservation toModel() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new Reservation.builder()
                              .room(room.toModel())
                              .user(user.toModel())
                              .initDate(LocalDateTime.parse(initDate, formatter))
                              .finalDate(LocalDateTime.parse(finalDate, formatter))
                              .build();
    }

    public ReservationDTO(Reservation reserve) {

        if (Objects.isNull(reserve)) {
            return;
        }

        this.id = reserve.getId();
        this.initDate = reserve.getInitDate().toString();
        this.finalDate = reserve.getFinalDate().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }
}
