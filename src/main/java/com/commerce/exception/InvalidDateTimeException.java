/**
 * 
 */
package com.commerce.exception;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * InvalidDateTimeException.java
 */
public class InvalidDateTimeException extends RuntimeException{
	
	 private String errorMessage;

	public InvalidDateTimeException(String errorMessage) {
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
