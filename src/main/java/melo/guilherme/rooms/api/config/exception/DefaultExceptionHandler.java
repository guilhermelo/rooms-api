package melo.guilherme.rooms.api.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseMessages> businessException(BusinessException e) {;
		
		ResponseMessages response = new ResponseMessages(e.getMessages());
	
		return ResponseEntity.badRequest().body(response);
	}
}
