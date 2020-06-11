package melo.guilherme.rooms.api.config.exception;

import java.util.List;

public class ResponseMessages {
	private List<Message> messages;
	
	public ResponseMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
}
