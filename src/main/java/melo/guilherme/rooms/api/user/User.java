package melo.guilherme.rooms.api.user;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = -9200343249921022356L;

	@Id
	private String id;
	
	@Column
	private String name;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	public User() {
		super();
	}
	
	private User(UserBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.email = builder.email;
		this.name = builder.name;
	}
	
	
	public static class UserBuilder {
		
		private String id;
		private String username;
		private String password;
		private String email;
		private String name;
		
		public UserBuilder id(String id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
