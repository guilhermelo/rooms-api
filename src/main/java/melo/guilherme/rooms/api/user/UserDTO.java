package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import melo.guilherme.rooms.api.room.RoomDTO;

public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;
    private List<RoomDTO> rooms;

    public UserDTO() {
        super();
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.name = entity.getName();
    }

    public static UserDTO from(User user) {
        return new UserDTO(user);
    }

    public User toModel() {
        return new User.builder()
                .name(name)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public UsernamePasswordAuthenticationToken transformAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
