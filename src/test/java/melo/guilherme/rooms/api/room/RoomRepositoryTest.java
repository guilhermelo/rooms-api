package melo.guilherme.rooms.api.room;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoomRepositoryTest {

	@Autowired
	private IRoomRepository repository;
	
	@Test
	void test() {
		Room savedRoom = repository.save(new Room.RoomBuilder().build());
		
		Assert.assertNotNull(savedRoom);
		Assert.assertNotNull(savedRoom.getId());
	}

}
