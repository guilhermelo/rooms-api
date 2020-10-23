package melo.guilherme.rooms.api.room;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, UUID> {
	
	@Override
	@Query("select room from Room room join fetch room.user")
	List<Room> findAll();
}
