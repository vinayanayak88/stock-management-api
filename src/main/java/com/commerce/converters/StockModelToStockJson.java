/**
 * 
 */
package com.commerce.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.commerce.domain.StockModel;
import com.commerce.domain.StockJson;
import com.commerce.domain.Stock;
import com.commerce.util.DateFormatUtil;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * StockToStockJson.java
 */
@Component
public class StockModelToStockJson implements Converter<StockModel, StockJson>{
	
	@Autowired
	DateFormatUtil util;

	@Override
	public StockJson convert(StockModel model) {
		StockJson obj = new StockJson();
		obj.setProductId(model.getProductId());
		obj.setRequstTimeStamp(util.convertTimeStampToString(System.currentTimeMillis()));
		Stock stock = new Stock();
		stock.setId(model.getId());
		stock.setQuantity(model.getQuantity());
		stock.setTimeStamp(model.getTimeStamp());
		obj.setStock(stock);
		return obj;
	}

}
