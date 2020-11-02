package melo.guilherme.rooms.api.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	private UserRepository repository;
	
	public AuthenticationService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Optional<User> user = repository.findByUsername(username);
		
		return user.orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
	}

}
