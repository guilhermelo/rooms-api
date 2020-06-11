package melo.guilherme.rooms.api.config.exception;

public enum MessageType {
	VALIDATION("validation"), ERROR("error");
	
	private String type;
	
	MessageType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}
