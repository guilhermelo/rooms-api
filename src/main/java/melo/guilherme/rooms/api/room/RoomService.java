package melo.guilherme.rooms.api.room;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	
	@Autowired
	private IRoomRepository repository;
	
	public List<Room> getRooms() {
		return repository.findAll();
	}
	
	public Room save(Room room) {
		Room savedRoom = repository.save(room);
		
		return savedRoom;
	}

	public Room update(String id, Room room) {
		
		if(Objects.isNull(id)) {
			BusinessException.of("Identificador nulo");
		}
		
		room.setId(id);
		
		Room updatedRoom = repository.save(room);
		
		return updatedRoom;
	}

	public Room delete(String id) {
		
		if(Objects.isNull(id)) {
			BusinessException.of("Identificador nulo");
		}
		
		repository.deleteById(id);
		
		return new Room.RoomBuilder().id(id).build();
	}
}
