package melo.guilherme.rooms.api.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserDTO {
	private String id;
	private String username;
	private String password;
	private String email;
	private String name;
	
	public UserDTO() {
		super();
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.name = entity.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public UsernamePasswordAuthenticationToken transformAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
}
