package melo.guilherme.rooms.api.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
	
	@Autowired
	private ReserveService service;
	
	@Autowired
	private ReserveAssemblerDTO assembler;
	
	
	@PostMapping
	public ResponseEntity<?> reserveRoom(@RequestBody ReserveDTO dto) {
		service.reserveRoom(assembler.assembleEntity(dto));
		
		return ResponseEntity.ok().build();
	}
}
