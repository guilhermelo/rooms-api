package melo.guilherme.rooms.api.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class RoomsExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseError> businessException(BusinessException e) {;

		ResponseError response = new ResponseError();

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> businessException(MethodArgumentNotValidException e, WebRequest request) {

		BindingResult result = e.getBindingResult();
		List<ObjectError> errors = result.getAllErrors();
		ResponseError responseError = new ResponseError();

		for (ObjectError error : errors) {
			responseError.addMessage(error.getDefaultMessage());
		}

		return ResponseEntity.badRequest().body(responseError);
	}

}
