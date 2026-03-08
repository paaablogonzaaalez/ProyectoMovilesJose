package com.empresamoviles.mobiles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException {
	
	 public ResourceNotFoundException(String message) {
	        super();
	    }

	    public ResourceNotFoundException(String message, Throwable cause) {
	        super();
	    }

		public String getMessage() {
			// TODO Auto-generated method stub
			return null;
		}
}
