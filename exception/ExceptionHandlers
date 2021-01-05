package com.sprint.exception;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;




@ControllerAdvice
public class ExceptionHandlers extends Exception {
	private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";
    
    	// handle global exception
    
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception exception, WebRequest request) {
		List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorMessage error = new ErrorMessage(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// handle specific exception
	
	@ExceptionHandler(value = APIException.class)
	public ResponseEntity<ErrorMessage> handleAPIException(APIException exception, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        details.add(exception.getLocalizedMessage());
	        ErrorMessage error = new ErrorMessage(BAD_REQUEST, details);
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// handle specific exception
	
	@ExceptionHandler(value = CustomerRestaurantException.class)
	public ResponseEntity<ErrorMessage> handleUserException(CustomerRestaurantException exception, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        details.add(exception.getLocalizedMessage());
	        ErrorMessage error = new ErrorMessage(INCORRECT_REQUEST, details);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
