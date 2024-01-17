package com.ap.electric.exception;

public class UserNotFoundException  extends RuntimeException{

	
	private static final long serialVersionUID = -3054434138233551741L;
	private String message;
	
	public UserNotFoundException() {
		// 
	}

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
