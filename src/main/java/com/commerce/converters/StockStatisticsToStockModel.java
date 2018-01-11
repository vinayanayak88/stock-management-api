package com.commerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.commerce.domain.StockModel;
import com.commerce.domain.StockStatistics;
import com.commerce.util.DateFormatUtil;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockStatisticsToStock.java
 */
@Component
public class StockStatisticsToStockModel implements Converter<StockStatistics, StockModel>{
	
	@Autowired
	DateFormatUtil util;

	@Override
	public StockModel convert(StockStatistics statisctics) {
		StockModel stock = new StockModel();
		stock.setId(statisctics.getId());
		stock.setProductId(statisctics.getProductId());
		stock.setQuantity(statisctics.getQuantity());
		String dateTime = util.convertTimeStampToString(statisctics.getTimeStamp());
		 stock.setTimeStamp(dateTime);
		return stock;
	}

	

	

}
