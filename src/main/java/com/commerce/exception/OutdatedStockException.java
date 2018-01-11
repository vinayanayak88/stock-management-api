/**
 * 
 */
package com.commerce.exception;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * OutdatedStockException.java
 */
public class OutdatedStockException extends RuntimeException{

	private String errorMessage;

	public OutdatedStockException(String errorMessage) {
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
