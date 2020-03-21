package melo.guilherme.rooms.api.room;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -6608257022314284549L;

	public BusinessException(String message) {
		super(message);
	}
	
	public static void of(String message) {
		throw new BusinessException(message);
	}
}
