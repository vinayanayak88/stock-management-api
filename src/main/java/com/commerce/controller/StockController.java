/**
 * 
 */
package com.commerce.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.domain.StockJson;
import com.commerce.domain.StockModel;
import com.commerce.domain.StockSummary;
import com.commerce.exception.NoStockFoundException;
import com.commerce.service.StockService;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * StockController.java
 */
@RestController
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	StockService stockService;
	
	@RequestMapping(value = "/updateStock", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<StockModel> updateStock(@RequestBody StockModel Stock) {
		logger.info(StockController.class + " : Received post request for updating stock ");
		StockModel stock = stockService.createStock(Stock);
		return new ResponseEntity<>(stock, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/stock/{productId}", method = RequestMethod.GET)
	public ResponseEntity<StockJson> findStockbyProductId(@PathVariable String productId) throws NoStockFoundException {
		logger.info(StockController.class + " : Received get request for stock ");
		StockJson stock = stockService.findStockbyProductId(productId);

		if (null != stock) {
			return new ResponseEntity<>(stock, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<StockSummary> getStatistics(HttpServletRequest request) {
		String range = request.getParameter("time");
		logger.info(StockController.class + " : Received get request for statistics ");
		StockSummary stock = stockService.getStatistics(range);

		if (null != stock) {
			return new ResponseEntity<>(stock, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
