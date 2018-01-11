/**
 * 
 */
package com.commerce.domain;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * Stock.java
 */
public class StockModel {
	
	private String id;
	private String timeStamp;
	private String productId;
	private int quantity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	

}
