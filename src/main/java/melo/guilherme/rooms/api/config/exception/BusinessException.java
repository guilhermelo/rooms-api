package melo.guilherme.rooms.api.config.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6608257022314284549L;

	private List<Message> messages;

	private BusinessException(Message message) {

		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		
		messages.add(message);
	}

	private BusinessException(List<Message> messages) {
		this.messages = messages;
	}

	public static void of(List<Message> messages) {
		throw new BusinessException(messages);
	}

	public static void of(Message message) {
		throw new BusinessException(message);
	}

	public List<Message> getMessages() {
		return messages;
	}

}
