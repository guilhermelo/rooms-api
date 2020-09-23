package melo.guilherme.rooms.api.config.exception;

public class Message {
	private String message;
	private MessageType type;
	
	public static Message of(String message, MessageType messageType) { 
		return new Message(message, messageType);
	}
	
	private Message(String msg, MessageType type) {
		this.message = msg;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public MessageType getType() {
		return type;
	}
}
