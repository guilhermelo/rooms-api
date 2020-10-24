package melo.guilherme.rooms.api.room;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;
import melo.guilherme.rooms.api.generic.CollectionResponseDTO;
import melo.guilherme.rooms.api.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/v1/rooms")
public class RoomController {
    private final RoomRepository repository;
    private final UserRepository userRepository;

    private final Path directoryFiles = Paths.get("files");

    public RoomController(RoomRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void criaDiretorio() {
        try {
            Files.createDirectory(directoryFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public ResponseEntity<CollectionResponseDTO<RoomDTO>> getAll() {
        List<RoomDTO> rooms = repository.findAll().stream().map(RoomDTO::from).collect(Collectors.toList());

        CollectionResponseDTO<RoomDTO> collection = new CollectionResponseDTO<>(rooms, rooms.size(), 0);

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable("id") String id) {
        Room room = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Sala não encontrada!"));

        return ResponseEntity.ok(RoomDTO.from(room));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> save(@RequestBody RoomDTO dto, UriComponentsBuilder uriBuilder) {
        Room room = dto.toModel();

        userRepository.findById(room.getUser().getId())
                .orElseThrow(() -> BusinessException.of(Message.of("Room's user doesn't exist!", MessageType.VALIDATION)));

        Room savedRoom = repository.save(room);

        URI uri = uriBuilder.path("/v1/rooms/{id}").buildAndExpand(savedRoom.getId()).toUri();

        return ResponseEntity.created(uri).body(RoomDTO.from(savedRoom));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable("id") String id, @RequestBody RoomDTO dto) {
        Room room = dto.toModel();

        repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Sala não encontrada!"));

        room.setId(UUID.fromString(id));
        Room updatedRoom = repository.save(room);
        return ResponseEntity.ok(RoomDTO.from(updatedRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDTO> delete(@PathVariable("id") String id) {
        repository.deleteById(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<RoomDTO> upload(@RequestParam("files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            try {
                Files.copy(file.getInputStream(), this.directoryFiles.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }
}
