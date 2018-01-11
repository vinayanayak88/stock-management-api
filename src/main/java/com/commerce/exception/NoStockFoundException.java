/**
 * 
 */
package com.commerce.exception;

/**
 * @author vinayanayak
 * @date 11-Jan-2018
 * NoStockFoundException.java
 */
public class NoStockFoundException extends Exception{
	
	private String errorMessage;

	public NoStockFoundException(String errorMessage) {
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
