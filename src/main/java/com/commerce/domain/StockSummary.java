/**
 * 
 */
package com.commerce.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * StockSummary.java
 */
public class StockSummary {
	
	private String requestTimestamp;
	private String range;
	private List<StockModel> topAvailableProducts = new ArrayList<>();
	private List<TopSellingProduct> topSellingProducts = new ArrayList<>();
	
	public String getRequestTimestamp() {
		return requestTimestamp;
	}
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<StockModel> getTopAvailableProducts() {
		return topAvailableProducts;
	}
	public void setTopAvailableProducts(List<StockModel> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}
	public List<TopSellingProduct> getTopSellingProducts() {
		return topSellingProducts;
	}
	public void setTopSellingProducts(List<TopSellingProduct> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}
	
	
	
}
