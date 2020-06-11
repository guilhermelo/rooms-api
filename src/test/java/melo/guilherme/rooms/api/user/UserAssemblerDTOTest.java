package melo.guilherme.rooms.api.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import melo.guilherme.rooms.api.util.uuid.UUIDGenerator;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerDTOTest {

	@InjectMocks
	private UserAssemblerDTO assembler;
	
	@Test
	public void shouldAssembleDtoToModel() {
		
		UserDTO dto = new UserDTO();
		dto.setId(UUIDGenerator.generate());
		dto.setUsername("guilherme");
		dto.setPassword("guilherme");
		dto.setEmail("guilherme@guilherme.com.br");
		
		User user = assembler.assembleEntity(dto);
		
		assertNotNull(user);
	}
	
	@Test
	public void shouldAssembleModelToDTO() {
		
		User user = new User.UserBuilder()
							.email("guilherme@guilherme.com.br")
							.username("guilherme")
							.password("guilherme")
							.id(UUIDGenerator.generate())
							.build();
		
		UserDTO dto = assembler.assembleDTO(user);
		
		assertNotNull(dto);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenDTOIsNull() {
		
		
		UserDTO dto = null;
		
		assembler.assembleEntity(dto);
	}

}
