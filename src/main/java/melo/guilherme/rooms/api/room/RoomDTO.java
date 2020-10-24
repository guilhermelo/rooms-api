package melo.guilherme.rooms.api.room;

import java.util.UUID;

public class RoomDTO {
	private UUID id;
	private String name;
	private String description;
	private Integer amountPeople;
	private String userId;

	private RoomDTO(Room room) {
		this.id = room.getId();
		this.name = room.getName();
		this.description = room.getDescription();
		this.amountPeople = room.getAmountPeople();
		this.userId = room.getUser().getId();
	}

	public RoomDTO() {
	}

	public static RoomDTO from(Room room) {
		return new RoomDTO(room);
	}

	public Room toModel() {
		return new Room.builder()
				       .id(id)
				       .name(name)
				       .description(description)
				       .amountPeople(amountPeople)
				       .build();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getAmountPeople() {
		return amountPeople;
	}

	public String getUserId() {
		return userId;
	}
}
