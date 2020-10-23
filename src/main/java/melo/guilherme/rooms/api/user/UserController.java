package melo.guilherme.rooms.api.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import melo.guilherme.rooms.api.config.security.TokenService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	private String BEARER = "Bearer";

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@PostMapping
	@RequestMapping("/authentication")
	public ResponseEntity<?> authentication(@RequestBody UserDTO userLogin) {

		UsernamePasswordAuthenticationToken login = userLogin.transformAuthenticationToken();
		try {
			Authentication authentication = authenticationManager.authenticate(login);
			String token = BEARER + " " + tokenService.generateToken(authentication);
			User user = (User) authentication.getPrincipal();
			
			UserDTO dto = UserDTO.from(user);
			dto.setPassword(null);
			
			return ResponseEntity.ok().header("x-access-token", token).body(dto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
		User savedUser = userService.save(dto.toModel());
		return ResponseEntity.ok(UserDTO.from(savedUser));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody UserDTO dto) {
		User updatedUser;
		try {
			updatedUser = userService.update(id, dto.toModel());
			return ResponseEntity.ok(UserDTO.from(updatedUser));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable("id") String id) {

		User deletedUser;
		try {
			deletedUser = userService.delete(id);
			return ResponseEntity.ok(UserDTO.from(deletedUser));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/exists/{username}")
	public ResponseEntity<UserDTO> userExists(@PathVariable("username") String username) {
		
		User user;
		
		try {
			user = userService.getByUsername(username);
			return ResponseEntity.ok(UserDTO.from(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll() {
		try {
			List<UserDTO> users = userService.getAll().stream().map(UserDTO::from).collect(Collectors.toList());
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
