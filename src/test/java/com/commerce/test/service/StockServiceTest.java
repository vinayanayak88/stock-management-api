/**
 * 
 */
package com.commerce.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.commerce.domain.StockJson;
import com.commerce.domain.StockModel;
import com.commerce.domain.StockSummary;
import com.commerce.exception.NoStockFoundException;
import com.commerce.exception.OutdatedStockException;
import com.commerce.service.StockService;
import com.commerce.service.handler.StockServiceHandler;
import com.commerce.util.DateFormatUtil;

/**
 * @author vinayanayak
 * @date 11-Jan-2018
 * StockServiceTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
	
	@Autowired
	StockService service;
	
	@Autowired
	DateFormatUtil util;
	
	@Before
	public void setUp() {
		StockServiceHandler.getStockdatamap().clear();
	}
	
	@Test(expected = OutdatedStockException.class)
    public void whenInvalidTimestamp_exceptionThrown(){
		generateValidTestStock();
		generateOutdatedTestStock();
    }
	
	@Test
    public void whenValidTimestamp_createStock() throws NoStockFoundException{
		generateValidTestStock();
		StockJson stock = service.findStockbyProductId("vegetable-124");
		assertEquals(stock.getProductId(), "vegetable-124");
        assertEquals(stock.getStock().getId(), "0004");
        assertEquals(stock.getStock().getQuantity(), 400);
	}
	
	private void generateValidTestStock() {
    	StockModel stock = new StockModel();
    	stock.setId("0004");
    	stock.setProductId("vegetable-124");
    	stock.setQuantity(400);
    	stock.setTimeStamp(util.convertTimeStampToString(System.currentTimeMillis()));;
    	service.createStock(stock);
    }
	
	private void generateOutdatedTestStock() {
    	StockModel stock = new StockModel();
    	stock.setId("0004");
    	stock.setProductId("vegetable-124");
    	stock.setQuantity(400);
    	stock.setTimeStamp(util.convertTimeStampToString(1513013850472l));
    	service.createStock(stock);
    }
	

	@Test
    public void testGetStatiscticsToday(){
		for(int i=0 ; i<2 ; i++) {
    		createStock(i);
    	}
		StockSummary summary = service.getStatistics("today");
		assertEquals(summary.getRange(), "today");
		assertEquals(summary.getTopAvailableProducts().get(0).getProductId(), "vegetable-1240");
		assertEquals(summary.getTopAvailableProducts().get(0).getQuantity(), 400);
		assertEquals(summary.getTopSellingProducts().get(0).getItemsSold(), 0);
		assertEquals( summary.getTopAvailableProducts().size(), 2);
		assertEquals( summary.getTopSellingProducts().size(), 2);
	}
	
	private void createStock(int i) {
    	StockModel stock = new StockModel();
    	stock.setId("0004"+i);
    	stock.setProductId("vegetable-124"+i);
    	stock.setQuantity(400+i);
    	stock.setTimeStamp(util.convertTimeStampToString(System.currentTimeMillis()));
    	service.createStock(stock);
    }

}
