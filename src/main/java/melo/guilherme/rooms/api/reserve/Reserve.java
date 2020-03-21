package melo.guilherme.rooms.api.reserve;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import melo.guilherme.rooms.api.room.Room;
import melo.guilherme.rooms.api.user.User;

@Entity
@Table(name = "reserves")
public class Reserve {

	@Id
	@Column
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private LocalDateTime initDate;

	@Column
	private LocalDateTime finalDate;

	@Deprecated
	public Reserve() {
		super();
	}

	public Reserve(ReserveBuilder builder) {
		this.id = builder.id;
		this.room = builder.room;
		this.user = builder.user;
		this.initDate = builder.initDate;
		this.finalDate = builder.finalDate;
	}

	public static class ReserveBuilder {
		private String id;
		private Room room;
		private User user;
		private LocalDateTime initDate;
		private LocalDateTime finalDate;

		public ReserveBuilder id(String id) {
			this.id = id;
			return this;
		}

		public ReserveBuilder room(Room room) {
			this.room = room;
			return this;
		}
		
		public ReserveBuilder user(User user) {
			this.user = user;
			return this;
		}
		
		public ReserveBuilder initDate(LocalDateTime initDate) {
			this.initDate = initDate;
			return this;
		}
		
		public ReserveBuilder finalDate(LocalDateTime finalDate) {
			this.finalDate = finalDate;
			return this;
		}
		
		public Reserve build() {
			return new Reserve(this);
		}
		
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getInitDate() {
		return initDate;
	}

	public LocalDateTime getFinalDate() {
		return finalDate;
	}
}
