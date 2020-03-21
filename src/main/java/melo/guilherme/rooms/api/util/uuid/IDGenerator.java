package melo.guilherme.rooms.api.util.uuid;

import java.util.UUID;

public abstract class IDGenerator {
	
	public static final String generateID() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	
}
