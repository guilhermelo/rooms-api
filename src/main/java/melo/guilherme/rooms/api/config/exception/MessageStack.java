package melo.guilherme.rooms.api.config.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MessageStack {
	private List<Message> messages;
	
	
	public MessageStack() {
		this.messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message) {
		if(Objects.nonNull(message)) {
			this.messages.add(message);
		}
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	
	public boolean hasValidationMessages() {
		return this.messages.stream().anyMatch(message -> MessageType.VALIDATION.equals(message.getType()));
									 
	}
}
