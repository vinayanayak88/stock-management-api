package com.commerce.service.handler;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commerce.constant.Constant;
import com.commerce.converters.StockModelToStockJson;
import com.commerce.converters.StockModelToStockStatistics;
import com.commerce.converters.StockStatisticsToStockModel;
import com.commerce.domain.StockJson;
import com.commerce.domain.StockModel;
import com.commerce.domain.StockStatistics;
import com.commerce.domain.StockSummary;
import com.commerce.domain.TopSellingProduct;
import com.commerce.exception.NoStockFoundException;
import com.commerce.exception.OutdatedStockException;
import com.commerce.service.StockService;
import com.commerce.util.DateFormatUtil;
import com.commerce.util.StockComparator;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockServiceHandler.java
 */
@Service
public class StockServiceHandler implements StockService {
	
	@Autowired
	StockModelToStockStatistics stockToStatiscticsConverter;
	
	@Autowired
	StockModelToStockJson StockToStockJsonConverter;
	
	@Autowired
	StockStatisticsToStockModel stockStatisticsToStockConverter;
	
	@Autowired
	DateFormatUtil util;
	
	private static final Map<String, StockStatistics> stockDataMap = new ConcurrentHashMap<>();
	
	/**
	 * Service to store the stock data in stockDataMap. If the map contains the newest stock for the product Id then the stock is ignored else the stock is updated.
	 */
	@Override
	public StockModel createStock(StockModel stock)  {
		StockStatistics newStock = stockToStatiscticsConverter.convert(stock);
		//check for outdated stock, if outdated stock then return 204
		String productId = stockDataMap.entrySet().stream().filter(map -> map.getKey().equals(stock.getProductId())).filter(map -> map.getValue().getTimeStamp() > newStock.getTimeStamp()).map(map -> map.getKey()).collect(Collectors.joining());
		if(!productId.isEmpty()) {
			throw new OutdatedStockException(Constant.OUTDATED_STOCK);
		}
		//computes and stores the statistics in then map, if the product id is new then it is added to the map else it is updated with the new stock
		stockDataMap.compute(stock.getProductId(), (k, v) -> {
			if (v == null) {
				v  = stockToStatiscticsConverter.convert(stock);
				v.setItemsSold(0);
				return v;
			}
			v.setItemsSold(v.getQuantity() - stock.getQuantity());
			v.setQuantity(stock.getQuantity());
			return v;
		});
		return stock;
	}

	/**
	 * Service to find the stock by productId
	 */
	@Override
	public StockJson findStockbyProductId(String productId) throws NoStockFoundException {
		if(stockDataMap.get(productId) == null) { 
			throw new NoStockFoundException(Constant.NO_STOCK_FOUND);
		}
		return StockToStockJsonConverter.convert(stockStatisticsToStockConverter.convert(stockDataMap.get(productId)));
		
	}

	/**
	 * Calculates and returns combined statistics of the stocks for 'today' or 'lastMonth' based on the request parameter in the URI based on statistics map (stockDataMap).
	 */
	@Override
	public StockSummary getStatistics(String range) {
		Calendar cal = util.getMidnightTimeStamp();
		long now = util.convertTimeStampToUTCTime(System.currentTimeMillis());
		//filters the stockDataMap by 'today' or 'lastmonth' and then sorts and extracts the top 3 items available products
		Map<String, StockStatistics> result = stockDataMap.entrySet().stream()
				.filter(filterStocksByTime(range, cal, now))
				.limit(3).sorted(Map.Entry.comparingByValue(new StockComparator())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		 
		 List<StockModel> topAvailableList = result.entrySet().stream().map(x -> {
			 StockModel model = new StockModel();
			 model.setId(x.getValue().getId());
			 model.setProductId(x.getValue().getProductId());
			 model.setQuantity(x.getValue().getQuantity());
			 model.setTimeStamp(util.convertTimeStampToString(x.getValue().getTimeStamp()));
			 return model;
		 }).collect(Collectors.toList());
		//filters the stockDataMap by 'today' or 'lastmonth' and then sorts and extracts the top 3 items selling products
		 List<TopSellingProduct> topSellingList = stockDataMap.entrySet().stream().filter(filterStocksByTime(range, cal, now)).map(x -> {
			 TopSellingProduct product = new TopSellingProduct();
			product.setProductId(x.getValue().getProductId());
			product.setItemsSold(x.getValue().getItemsSold());
			 return product;
		 }).collect(Collectors.toList());
		 
		 StockSummary summary = new StockSummary();
		 summary.getTopAvailableProducts().addAll(topAvailableList.stream().filter(value -> value != null).collect(Collectors.toList()));
		 summary.setRequestTimestamp(util.convertTimeStampToString(System.currentTimeMillis()));
		 summary.setTopSellingProducts(topSellingList);
		 summary.setRange(range);
		 return summary;
	}

	private Predicate<? super Entry<String, StockStatistics>> filterStocksByTime(String range, Calendar cal, long now) {
		return x -> (range.equalsIgnoreCase("today")
				&& (x.getValue().getTimeStamp() >= cal.getTimeInMillis() && x.getValue().getTimeStamp() <= now))
				|| (range.equalsIgnoreCase("lastMonth")
						&& (x.getValue().getTimeStamp() >= util.calulateLastMonthTimeStamp()
								&& x.getValue().getTimeStamp() <= now));
	}

	public static Map<String, StockStatistics> getStockdatamap() {
		return stockDataMap;
	}
	
	
	
}
