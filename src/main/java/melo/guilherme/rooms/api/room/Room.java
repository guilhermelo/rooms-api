package melo.guilherme.rooms.api.room;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@NotBlank
	private String id;
	
	@NotBlank
	@Column(name = "name", nullable = false, length = 15, unique = true)
	private String name;
	
	@NotBlank
	@Column(name = "description", nullable = false, length = 50)
	private String description;
	
	@Positive
	@Column(name = "amountPeople", nullable = false)
	private Integer amountPeople;
	
	@Deprecated
	public Room() {
	}
	
	private Room(String id, String name, String description, Integer amountPeople) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.amountPeople = amountPeople;
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
			Objects.requireNonNull(name, "Name is required");
			
			this.name = name;
			return this;
		}
		
		public RoomBuilder description(String description) {
			
			Objects.requireNonNull(description, "Description is required");
			
			if(description.length() > 100) {
				throw new IllegalArgumentException("Description must be less then 100");
			}
			
			this.description = description;
			return this;
		}
		
		public RoomBuilder amountPeople(Integer amountPeople) {
			Objects.requireNonNull(amountPeople, "Amount People is required");
			
			this.amountPeople = amountPeople;
			return this;
		}
		
		public Room build() {
			return new Room(id, name, description, amountPeople);
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

	public void setName(String name) {
		this.name = name;
	}
}
