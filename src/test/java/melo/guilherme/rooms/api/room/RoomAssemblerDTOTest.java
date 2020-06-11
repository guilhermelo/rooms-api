package melo.guilherme.rooms.api.room;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import melo.guilherme.rooms.api.room.Room.RoomBuilder;
import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(MockitoJUnitRunner.class)
public class RoomAssemblerDTOTest {
	
	@InjectMocks
	private RoomAssemblerDTO assembler;

	@Test
	public void shouldTransformDtoToModel() {
		
		RoomDTO dto = new RoomDTO();
		dto.setId(UUIDGenerator.generate());
		dto.setName("Room 1");
		dto.setDescription("Description");
		dto.setAmountPeople(10);
		
		Room room = assembler.assembleEntity(dto);
		
		assertNotNull(room);
	}
	
	
	@Test
	public void shouldTransformModelToDTO() {
		
		Room room = new RoomBuilder().id(UUIDGenerator.generate())
									 .name("Room 1")
									 .description("Description")
									 .amountPeople(10)
									 .build();
		
		assembler.assembleDTO(room);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCreateModelWithDescriptionGreaterThen100() {
		
		RoomDTO dto = new RoomDTO();
		dto.setId(UUIDGenerator.generate());
		dto.setName("Room 1");
		dto.setDescription("Big room with a big description in a big city in a big country called United States. When you come here, you will have dinner with us.");
		dto.setAmountPeople(10);
		
		Room room = assembler.assembleEntity(dto);
		
		assertNotNull(room);
	}

}
