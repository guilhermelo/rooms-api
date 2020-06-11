package melo.guilherme.rooms.api.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReservationController {
	
	@Autowired
	private ReservaionService service;
	
	@Autowired
	private ReservationAssemblerDTO assembler;
	
	
	@PostMapping
	public ResponseEntity<?> reserveRoom(@RequestBody ReservationDTO dto) {
		service.reserveRoom(assembler.assembleEntity(dto));
		
		return ResponseEntity.ok().build();
	}
}
