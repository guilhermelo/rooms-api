package melo.guilherme.rooms.api.reservation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import melo.guilherme.rooms.api.room.Room;
import melo.guilherme.rooms.api.room.RoomRepository;
import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;

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

    /**
     * @deprecated Não utilizar
     */
    @Deprecated
    public Reservation() {
        super();
    }

    public Reservation(builder builder) {
        this.id = builder.id;
        this.room = builder.room;
        this.user = builder.user;
        this.initDate = builder.initDate;
        this.finalDate = builder.finalDate;
    }

    public boolean ehMaiorQueDozeHoras() {

        if (Objects.isNull(initDate) || Objects.isNull(finalDate)) {
            throw new NullPointerException("Data inicial e final não podem ser nulas!");
        }

        return ChronoUnit.HOURS.between(initDate, finalDate) > 12;
    }

    public boolean salaNaoExiste(RoomRepository roomRepository) {
        return roomRepository.existsById(this.getRoom().getId());
    }

    public boolean usuarioNaoExiste(UserRepository userRepository) {
        return userRepository.existsById(this.getUser().getId());
    }

    public static class builder {
        private String id;
        private Room room;
        private User user;
        private LocalDateTime initDate;
        private LocalDateTime finalDate;

        public builder id(String id) {
            this.id = id;
            return this;
        }

        public builder room(Room room) {
            this.room = room;
            return this;
        }

        public builder user(User user) {
            this.user = user;
            return this;
        }

        public builder initDate(LocalDateTime initDate) {
            this.initDate = initDate;
            return this;
        }

        public builder finalDate(LocalDateTime finalDate) {
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
