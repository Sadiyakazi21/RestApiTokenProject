package com.example.demo.Exception;

public class UserServiceException extends RuntimeException{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -7823624053161406903L;

	public UserServiceException(String message) {
		 super(message); 
	}
}
