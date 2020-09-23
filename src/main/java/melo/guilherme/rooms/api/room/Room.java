package melo.guilherme.rooms.api.room;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import melo.guilherme.rooms.api.user.User;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@NotBlank
	private String id;
	
	@NotBlank
	@Column(name = "name", nullable = false, length = 20, unique = true)
	private String name;
	
	@NotBlank
	@Column(name = "description", nullable = false, length = 50)
	private String description;
	
	@Positive
	@Column(name = "amountPeople", nullable = false)
	private Integer amountPeople;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Deprecated
	public Room() {
	}
	
	private Room(builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.amountPeople = builder.amountPeople;
		this.user = builder.user;
	}
	
	
	public static class builder {
		private String id;
		private String name;
		private String description;
		private Integer amountPeople;
		private User user;
		
		public builder id(String id) {
			this.id = id;
			return this;
		}
		
		public builder name(String name) {
			Objects.requireNonNull(name, "Name is required");
			
			this.name = name;
			return this;
		}
		
		public builder description(String description) {
			
			Objects.requireNonNull(description, "Description is required");
			
			if(description.length() > 100) {
				throw new IllegalArgumentException("Description must be less then 100");
			}
			
			this.description = description;
			return this;
		}
		
		public builder amountPeople(Integer amountPeople) {
			Objects.requireNonNull(amountPeople, "Amount People is required");
			
			this.amountPeople = amountPeople;
			return this;
		}
		
		public builder user(User user) {
			Objects.requireNonNull(user, "User can't be null");
			
			this.user = user;
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
