/**
 * 
 */
package com.commerce.test.integration.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.commerce.domain.StockModel;
import com.commerce.service.StockService;
import com.commerce.service.handler.StockServiceHandler;
import com.commerce.util.DateFormatUtil;


/**
 * @author vinayanayak
 * @date 11-Jan-2018
 * StockControllerTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockControllerTest extends ControllerIntegrationTest{
	
	@Autowired
	DateFormatUtil util;
	
	@Autowired
	StockService service;

	@Before
	public void setUp() {
		StockServiceHandler.getStockdatamap().clear();
	}
	
	@Test
    public void testUpdateStockEnsureCorrectResponse() throws Exception {
		String paylod = createStockPaylod();
    	updateStock(paylod)
		.andExpect(status().isCreated());
    }
	
    private ResultActions updateStock(String payload) throws Exception {
    	return post("/updateStock", payload);
    }

    private String createStockPaylod() throws JSONException {
    	JSONObject obj = new JSONObject();
		obj.put("id", "0003");
		obj.put("timeStamp", util.convertTimeStampToString(System.currentTimeMillis()));
		obj.put("productId", "vegetable-123");
		obj.put("quantity", "300");
		return obj.toString();
    }
    
    @Test
    public void testGetStockEnsureCorrectResponse() throws Exception {
	 getStock("vegetable-124")
        	.andExpect(status().isOk());
    }
 
    private void injectStock() {
    	StockModel stock = new StockModel();
    	stock.setId("0004");
    	stock.setProductId("vegetable-124");
    	stock.setQuantity(400);
    	stock.setTimeStamp(util.convertTimeStampToString(System.currentTimeMillis()));;
    	service.createStock(stock);
    }
    
    private ResultActions getStock(String id) throws Exception {
    injectStock();
		return get("/stock/{productId}", id);
	}
    
    @Test
    public void testGetStatisticsForTodayEnsureCorrectResponse() throws Exception {
	 getStatistics("today")
        	.andExpect(status().isOk());
    }
 
    private void createStock(int i) {
    	StockModel stock = new StockModel();
    	stock.setId("0004"+i);
    	stock.setProductId("vegetable-124"+i);
    	stock.setQuantity(400);
    	stock.setTimeStamp(util.convertTimeStampToString(System.currentTimeMillis()));
    service.createStock(stock);
    }
    
    private ResultActions getStatistics(String range) throws Exception {
    	for(int i=0 ; i<2 ; i++) {
    		createStock(i);
    	}
    	assertStockCountIs(2);
		return get("/statistics?time=", range);
	}
    
    @Test
    public void testGetStatisticsForLastMonthEnsureCorrectResponse() throws Exception {
	 getStatistics("lastMonth")
        	.andExpect(status().isOk());
	 
    }
    
    private void assertStockCountIs(int count) {
    	Assert.assertEquals(count, StockServiceHandler.getStockdatamap().size());
    }

}
