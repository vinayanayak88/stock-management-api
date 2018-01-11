/**
 * 
 */
package com.commerce.common;

/**
 * @author vinayanayak
 * @date 09-Jan-2018 ErrorResponse.java
 */
public class ErrorResponse {

	private String errorMessage;

	public ErrorResponse(String errorMessage) {
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
