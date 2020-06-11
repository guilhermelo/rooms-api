package melo.guilherme.rooms.api.room;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@WebMvcTest(RoomController.class)
class RoomControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private RoomService service;
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	@Autowired
//	private ObjectMapper mapper;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	private UserDTO user;
//	
//	
//	@Before
//	public void setup() {
//		user = new UserDTO(new User.UserBuilder().username("teste").password("teste").build());
//	}
//
//	@Test
//	public void shouldListRooms() throws Exception {
//		
//		Room room = new Room.RoomBuilder().id(IDGenerator.generateID())
//										  .name("Room 1")
//										  .description("Room number 1")
//										  .amountPeople(5)
//										  .build();
//		
//		List<Room> rooms = Arrays.asList(room);
//		
//		String accessToken = generateToken(user);
//		
//		when(service.getRooms()).thenReturn(rooms);
//		
//		mockMvc.perform(get("/v1/rooms").header("Authorization", "Bearer " + accessToken))
//			   .andExpect(status().isOk())
//			   .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(room))));
//	}
//
//	
//	private String generateToken(UserDTO user) {
//		
//		UsernamePasswordAuthenticationToken login = user.transformAuthenticationToken();
//		
//		Authentication authentication = authenticationManager.authenticate(login);
//		
//		return tokenService.generateToken(authentication);
//	}
}
