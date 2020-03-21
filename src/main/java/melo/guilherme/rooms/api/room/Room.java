package melo.guilherme.rooms.api.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amountPeople")
	private Integer amountPeople;
	
	@Deprecated
	public Room() {
	}
	
	private Room(RoomBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.amountPeople = builder.amountPeople;
	}
	
	
	public static class RoomBuilder {
		private String id;
		private String name;
		private String description;
		private Integer amountPeople;
		
		public RoomBuilder id(String id) {
			this.id = id;
			return this;
		}
		
		public RoomBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public RoomBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public RoomBuilder amountPeople(Integer amountPeople) {
			this.amountPeople = amountPeople;
			return this;
		}
		
		public Room build() {
			return new Room(this);
		}
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getAmountPeople() {
		return amountPeople;
	}
}
