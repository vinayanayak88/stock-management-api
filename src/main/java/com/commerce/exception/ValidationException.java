package com.commerce.exception;

/**
 * Unchecked exception which is supposed to be
 * thrown if request body is not valid or it has
 * some invalid fields
 *
 * @author vinayanayak
 * @date 09-Jan-2018
 * ValidationException.java
 */
public class ValidationException extends RuntimeException {
    private String errorMessage;

	public ValidationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    
}
