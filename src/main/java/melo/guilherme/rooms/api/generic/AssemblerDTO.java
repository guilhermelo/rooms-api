package melo.guilherme.rooms.api.generic;

import java.util.ArrayList;
import java.util.List;

public interface AssemblerDTO<ENTITY, DTO> {
	
	public DTO assembleDTO(ENTITY entity);
	
	public ENTITY assembleEntity(DTO dto);
	
	
	default List<ENTITY> assembleManyEntities(List<DTO> dtos) {
		List<ENTITY> entities = new ArrayList<ENTITY>();
		
		for (DTO dto : dtos) {
			entities.add(assembleEntity(dto));
		}
		
		return entities; 
	}
	
	default List<DTO> assembleManyDTOs(List<ENTITY> entities) {
		List<DTO> dtos = new ArrayList<DTO>();
		
		for (ENTITY entity : entities) {
			dtos.add(assembleDTO(entity));
		}
		
		return dtos; 
	}
}
