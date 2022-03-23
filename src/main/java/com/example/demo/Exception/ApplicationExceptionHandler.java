package com.example.demo.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ErrorMessage;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	 @ExceptionHandler(value  = { Exception.class })
	   
	
		    public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		 String errorMessageDescription = ex.getLocalizedMessage();
			if(errorMessageDescription == null) errorMessageDescription = ex.toString();
			
		    	  ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescription);
		    	  
		        return new  ResponseEntity<Object> (errorMessage , HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	/* 
	  @ExceptionHandler(Exception.class )
	   
		
	    public ResponseEntity<String> handleEmptyInput(ErrorMessage errormessage) {
	
	
	    	  
	        return new  ResponseEntity<String> ("token not found  " , HttpStatus.BAD_REQUEST);
	    }
	    
	 */  
	 @ExceptionHandler(value  = { UserServiceException.class })
	   
		
	    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
	 String errorMessageDescription = ex.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
	    	  ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescription);
	    	  
	        return new  ResponseEntity<Object> (errorMessage , HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
}
