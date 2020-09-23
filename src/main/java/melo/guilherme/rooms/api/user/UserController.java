package melo.guilherme.rooms.api.user;

import java.util.List;

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

	@Autowired
	private UserAssemblerDTO assembler;

	@PostMapping
	@RequestMapping("/authentication")
	public ResponseEntity<?> authentication(@RequestBody UserDTO userLogin) {

		UsernamePasswordAuthenticationToken login = userLogin.transformAuthenticationToken();

		try {
			Authentication authentication = authenticationManager.authenticate(login);

			String token = new StringBuilder(BEARER).append(" ")
													.append(tokenService.generateToken(authentication))
													.toString();
			
			User user = (User) authentication.getPrincipal();
			
			
			UserDTO dto = assembler.assembleDTO(user);
			dto.setPassword(null);
			
			return ResponseEntity.ok().header("x-access-token", token).body(dto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {

		User user = assembler.assembleEntity(dto);

		User savedUser = userService.save(user);

		return ResponseEntity.ok(assembler.assembleDTO(savedUser));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody UserDTO dto) {

		User user = assembler.assembleEntity(dto);

		User updatedUser;
		try {
			updatedUser = userService.update(id, user);
			return ResponseEntity.ok(assembler.assembleDTO(updatedUser));
		} catch (Exception e) {
			// TODO
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable("id") String id) {

		User deletedUser;
		try {
			deletedUser = userService.delete(id);
			return ResponseEntity.ok(assembler.assembleDTO(deletedUser));
		} catch (Exception e) {
			// TODO
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/exists/{username}")
	public ResponseEntity<UserDTO> userExists(@PathVariable("username") String username) {
		
		User user;
		
		try {
			user = userService.getByUsername(username);
			return ResponseEntity.ok(assembler.assembleDTO(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll() {

		List<User> users;

		try {
			users = userService.getAll();
			return ResponseEntity.ok(assembler.assembleManyDTOs(users));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
