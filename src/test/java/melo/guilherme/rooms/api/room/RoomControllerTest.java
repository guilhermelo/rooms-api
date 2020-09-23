package melo.guilherme.rooms.api.room;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class RoomControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
	
//	@MockBean
//	private RoomService service;
	
//	@Autowired
//	private TokenService tokenService;
	
//	@Autowired
//	private ObjectMapper mapper;
	
//	@Before
//	public void setup() {
//		System.out.println("Setup");
//	}
//
//	@Test
//	public void shouldGetAllRooms() throws Exception {
//		
//		mockMvc.perform(get("/v1/rooms")) //.header("Authorization", "Bearer " + accessToken))
//			   .andExpect(status().isOk());
//			   // .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(room))));
//	}
}
