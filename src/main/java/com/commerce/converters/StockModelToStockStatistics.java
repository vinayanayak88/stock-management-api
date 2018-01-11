package com.commerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.commerce.domain.StockModel;
import com.commerce.domain.StockStatistics;
import com.commerce.util.DateFormatUtil;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockToStockStatistics.java
 */
@Component
public class StockModelToStockStatistics implements Converter<StockModel, StockStatistics>{
	
	@Autowired
	DateFormatUtil util;

	@Override
	public StockStatistics convert(StockModel stock) {
		StockStatistics statistics = new StockStatistics();
		if(stock.getId() != null && !StringUtils.isEmpty(stock.getId())) {
			statistics.setId(stock.getId());
		}
		statistics.setProductId(stock.getProductId());
		statistics.setQuantity(stock.getQuantity());
		if(stock.getTimeStamp() != null && !StringUtils.isEmpty(stock.getTimeStamp())) {
			long timeStamp = util.convertStringToTimeStamp(stock.getTimeStamp());
			statistics.setTimeStamp(timeStamp);
		}
		return statistics;
	}

	

	

	

}
