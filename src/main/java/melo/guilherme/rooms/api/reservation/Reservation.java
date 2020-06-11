package melo.guilherme.rooms.api.reservation;

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
public class Reservation {

	@Id
	@Column
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "init_date", nullable = false)
	private LocalDateTime initDate;

	@Column(name = "final_date", nullable = false)
	private LocalDateTime finalDate;

	@Deprecated
	public Reservation() {
		super();
	}

	public Reservation(ReservationBuilder builder) {
		this.id = builder.id;
		this.room = builder.room;
		this.user = builder.user;
		this.initDate = builder.initDate;
		this.finalDate = builder.finalDate;
	}

	public static class ReservationBuilder {
		private String id;
		private Room room;
		private User user;
		private LocalDateTime initDate;
		private LocalDateTime finalDate;

		public ReservationBuilder id(String id) {
			this.id = id;
			return this;
		}

		public ReservationBuilder room(Room room) {
			this.room = room;
			return this;
		}
		
		public ReservationBuilder user(User user) {
			this.user = user;
			return this;
		}
		
		public ReservationBuilder initDate(LocalDateTime initDate) {
			this.initDate = initDate;
			return this;
		}
		
		public ReservationBuilder finalDate(LocalDateTime finalDate) {
			this.finalDate = finalDate;
			return this;
		}
		
		public Reservation build() {
			return new Reservation(this);
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
