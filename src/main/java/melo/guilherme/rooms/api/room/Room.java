package melo.guilherme.rooms.api.room;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.*;

import melo.guilherme.rooms.api.user.User;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Size(min = 10, message = "Nome deve ter pelo menos 10 caracteres")
	@Column(name = "name", nullable = false, length = 20, unique = true)
	private String name;

	@NotNull
	@Size(min = 20, message = "Descrição deve ter pelo menos 20 caracteres")
	@Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
	@Column(name = "description", nullable = false, length = 50)
	private String description;

	@NotNull(message = "Necessário informar a quantidade de pessoas")
	@Positive
	@Min(1)
	@Column(name = "amountPeople", nullable = false)
	private Integer amountPeople;
	
	@NotNull(message = "Deve haver um usuário associado à sala")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * @deprecated
	 */
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
		private UUID id;
		private String name;
		private String description;
		private Integer amountPeople;
		private User user;
		
		public builder id(UUID id) {
			this.id = id;
			return this;
		}
		
		public builder name(String name) {
			this.name = name;
			return this;
		}
		
		public builder description(String description) {
			this.description = description;
			return this;
		}
		
		public builder amountPeople(Integer amountPeople) {
			this.amountPeople = amountPeople;
			return this;
		}
		
		public builder user(User user) {
			this.user = user;
			return this;
		}
		
		public Room build() {
			return new Room(this);
		}
	}
	
	public void setId(UUID id) {
		this.id = id;
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
