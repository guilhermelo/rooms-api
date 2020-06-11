package melo.guilherme.rooms.api.util.uuid;

import java.util.UUID;

public abstract class UUIDGenerator {
	
	public static final String generate() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	
}
