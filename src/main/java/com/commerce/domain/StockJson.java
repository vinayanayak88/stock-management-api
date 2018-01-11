/**
 * 
 */
package com.commerce.domain;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * StockToJson.java
 */
public class StockJson {
	
	private String productId;
	private String requstTimeStamp;
	private Stock stock;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRequstTimeStamp() {
		return requstTimeStamp;
	}
	public void setRequstTimeStamp(String requstTimeStamp) {
		this.requstTimeStamp = requstTimeStamp;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
