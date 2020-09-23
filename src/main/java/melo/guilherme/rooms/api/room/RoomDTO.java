package melo.guilherme.rooms.api.room;

public class RoomDTO {
	private String id;
	private String name;
	private String description;
	private Integer amountPeople;
	private String userId;
	
	public RoomDTO() {
	}

	public RoomDTO(Room room) {
		this.id = room.getId();
		this.name = room.getName();
		this.description = room.getDescription();
		this.amountPeople = room.getAmountPeople();
		this.userId = room.getUser().getId();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getAmountPeople() {
		return amountPeople;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmountPeople(Integer amountPeople) {
		this.amountPeople = amountPeople;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
