package melo.guilherme.rooms.api.config.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6608257022314284549L;

	private List<Message> messages;

	private BusinessException(Message message) {
		super(message.getMessage());

		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		
		messages.add(message);
	}

	private BusinessException(List<Message> messages) {
		this.messages = messages;
	}

	public static BusinessException of(List<Message> messages) {
		return new BusinessException(messages);
	}

	public static BusinessException of(Message message) {
		return new BusinessException(message);
	}

	public List<Message> getMessages() {
		return messages;
	}

}
