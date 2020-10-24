package melo.guilherme.rooms.api.room;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import melo.guilherme.rooms.api.generic.CollectionResponseDTO;


@CrossOrigin
@RestController
@RequestMapping("/v1/rooms")
public class RoomController {

	private final RoomService service;
	private final RoomAssemblerDTO assembler;
	
	private final Path directoryFiles = Paths.get("files");
	
	public RoomController(RoomService service, RoomAssemblerDTO assembler) {
		this.service = service;
		this.assembler = assembler;
	}
	
	@GetMapping
	public ResponseEntity<CollectionResponseDTO<RoomDTO>> getAll() {
		List<RoomDTO> rooms = assembler.assembleManyDTOs(service.getAll());
		
		CollectionResponseDTO<RoomDTO> collection = new CollectionResponseDTO<RoomDTO>(rooms, rooms.size(), 0);		
		
		return ResponseEntity.ok(collection);
	}
	
	@GetMapping("/{roomId}")
	public ResponseEntity<RoomDTO> getById(@PathVariable("roomId") String roomId) {
		Optional<Room> room = service.getById(roomId);
		RoomDTO dto = assembler.assembleDTO(room.orElseGet(null));	
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<RoomDTO> save(@RequestBody RoomDTO dto, UriComponentsBuilder uriBuilder) {
		Room savedRoom = service.save(assembler.assembleEntity(dto));
		
		 URI uri = uriBuilder.path("/v1/rooms/{id}").buildAndExpand(savedRoom.getId()).toUri();
		
		return ResponseEntity.created(uri).body(assembler.assembleDTO(savedRoom));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<RoomDTO> update(@PathVariable("id") String id, @RequestBody RoomDTO dto) {
		Room room = assembler.assembleEntity(dto);
		
		Room updatedRoom = service.update(id, room);
		
		return ResponseEntity.ok(assembler.assembleDTO(updatedRoom));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RoomDTO> delete(@PathVariable("id") String id) throws Exception {
		
		Room deletedRoom = service.delete(id);
		
		return ResponseEntity.ok(assembler.assembleDTO(deletedRoom));
	}
	
	@PostMapping("/upload")
	public ResponseEntity<RoomDTO> upload(@RequestParam("files") MultipartFile[] files) {
		
		for (MultipartFile file : files) {
			try {
				Files.copy(file.getInputStream(), this.directoryFiles.resolve(file.getOriginalFilename()));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostConstruct
	public void criaDiretorio() {
		try {
			Files.createDirectory(directoryFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
