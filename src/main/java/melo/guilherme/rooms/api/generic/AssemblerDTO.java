package melo.guilherme.rooms.api.generic;

import java.util.ArrayList;
import java.util.List;

public abstract class AssemblerDTO<ENTITY, DTO> {
	
	public abstract DTO assembleDTO(ENTITY entity);
	
	public abstract ENTITY assembleEntity(DTO dto);
	
	
	public final List<ENTITY> assembleManyEntities(List<DTO> dtos) {
		List<ENTITY> entities = new ArrayList<ENTITY>();
		
		for (DTO dto : dtos) {
			entities.add(assembleEntity(dto));
		}
		
		return entities; 
	}
	
	public final List<DTO> assembleManyDTOs(List<ENTITY> entities) {
		List<DTO> dtos = new ArrayList<DTO>();
		
		for (ENTITY entity : entities) {
			dtos.add(assembleDTO(entity));
		}
		
		return dtos; 
	}
}
