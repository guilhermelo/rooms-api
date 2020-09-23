package melo.guilherme.rooms.api.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, String> {
	
	@Override
	@Query("select room from Room room join fetch room.user")
	List<Room> findAll();
}
