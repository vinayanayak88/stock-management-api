/**
 * 
 */
package com.commerce.domain;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * StockModel.java
 */
public class Stock {
	
	private String id;
	private String timeStamp;
	private int quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
