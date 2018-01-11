/**
 * 
 */
package com.commerce.util;

import java.util.Comparator;

import com.commerce.domain.StockStatistics;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * StockComparator.java
 */
public class StockComparator implements Comparator<StockStatistics>{

	@Override
	public int compare(StockStatistics o1, StockStatistics o2) {
		if(o1.getItemsSold() > o2.getItemsSold()) {
			return 1;
		}
		if(o1.getItemsSold() < o2.getItemsSold()) {
			return -1;
		}
		return 0;
	}

}
