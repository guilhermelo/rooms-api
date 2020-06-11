package melo.guilherme.rooms.api.reservation;

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
