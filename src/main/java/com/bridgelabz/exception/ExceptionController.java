package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception){
		return new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<Object> exception(UserAlreadyExistsException customException){
		return new ResponseEntity<>(customException.getMessage(),HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(value = UserAuthenticationException.class)
	public ResponseEntity<Object> exception(UserAuthenticationException customException){
		return new ResponseEntity<>(customException.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(value = UserCredentialsException.class)
	public ResponseEntity<Object> exception(UserCredentialsException customException){
		return new ResponseEntity<>(customException.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
