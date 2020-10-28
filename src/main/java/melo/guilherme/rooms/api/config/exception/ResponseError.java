package melo.guilherme.rooms.api.config.exception;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ResponseError {

    private final Set<Message> messages;

    public ResponseError() {
        messages = new HashSet<>();
    }

    public void addMessage(String message) {
        Objects.requireNonNull(message, "Mensagem não pode ser nula");
        messages.add(Message.of(message, MessageType.VALIDATION));
    }

    public Set<Message> getMessages() {
        return Collections.unmodifiableSet(messages);
    }
}
