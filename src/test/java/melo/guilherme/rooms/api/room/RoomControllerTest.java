package melo.guilherme.rooms.api.room;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RoomController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RoomControllerTest {

	@Test
	void test() {
		// nothing yet
	}

}
