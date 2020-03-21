package melo.guilherme.rooms.api.reserve;

import java.util.Objects;

import melo.guilherme.rooms.api.room.RoomDTO;
import melo.guilherme.rooms.api.user.UserDTO;
import melo.guilherme.rooms.api.util.date.DateUtil;

public class ReserveDTO {
	private String id;
	private RoomDTO room;
	private UserDTO user;
	private String initDate;
	private String finalDate;

	public ReserveDTO() {
		super();
	}

	public ReserveDTO(Reserve reserve) {

		if (Objects.isNull(reserve)) {
			return;
		}

		this.id = reserve.getId();
		this.initDate = DateUtil.parseDatetime(reserve.getInitDate());
		this.finalDate = DateUtil.parseDatetime(reserve.getFinalDate());
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
