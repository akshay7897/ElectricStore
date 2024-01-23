package com.ap.electric.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ElectronicControllerAdvice {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(Exception ex){
		
		HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse msg=new ErrorResponse();
		msg.setErrorCode(String.valueOf(status.value()));
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		
		HttpStatus status=HttpStatus.BAD_REQUEST;
		
		ErrorResponse msg=new ErrorResponse();
		msg.setErrorCode(String.valueOf(status.value()));
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> UserNotFoundExceptionExceptionHandler(UserNotFoundException ex){
		
		HttpStatus status=HttpStatus.NOT_FOUND;
		
		ErrorResponse msg=new ErrorResponse();
		msg.setErrorCode(String.valueOf(status.value()));
		msg.setMessage(ex.getMessage());
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		
	}

}
