/**
 * 
 */
package com.commerce.service;

import org.springframework.stereotype.Service;

import com.commerce.domain.StockModel;
import com.commerce.domain.StockSummary;
import com.commerce.exception.NoStockFoundException;
import com.commerce.domain.StockJson;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockService.java
 */
@Service
public interface StockService {
	
	public StockModel createStock(StockModel stock) ;
	
	public StockJson findStockbyProductId(String productId)  throws NoStockFoundException;
	
	public StockSummary getStatistics(String range) ;

}
