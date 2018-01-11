package com.commerce.converters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.commerce.constant.Constant;
import com.commerce.domain.StockModel;
import com.commerce.domain.StockStatistics;
import com.commerce.exception.ValidationException;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockToStockStatistics.java
 */
@Component
public class StockModelToStockStatistics implements Converter<StockModel, StockStatistics>{

	@Override
	public StockStatistics convert(StockModel stock) {
		StockStatistics statistics = new StockStatistics();
		if(stock.getId() != null && !StringUtils.isEmpty(stock.getId())) {
			statistics.setId(stock.getId());
		}
		statistics.setProductId(stock.getProductId());
		statistics.setQuantity(stock.getQuantity());
		if(stock.getTimeStamp() != null && !StringUtils.isEmpty(stock.getTimeStamp())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.getDefault());
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			try {
				Date date = simpleDateFormat.parse(stock.getTimeStamp());
				statistics.setTimeStamp(date.getTime());
			} catch (Exception e) {
				throw new ValidationException(Constant.INVALID_DATETIME);
			}
		}
		return statistics;
	}

	

	

}
