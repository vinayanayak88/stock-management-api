/**
 * 
 */
package com.commerce.test.acceptance.step;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.commerce.test.acceptance.util.AbstractSteps;
import com.commerce.util.DateFormatUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author vinayanayak
 * @date 11-Jan-2018
 * StockStep.java
 */
public class StockStep extends AbstractSteps{
	
	@Autowired
	DateFormatUtil util;

	@When("^the user updates a stock$")
	public void theUserCallsUpdateStock() throws Throwable {
		String paylod = createStockPaylod();
		updateStock(paylod);
	}
	
	private void updateStock(String payload) throws Exception {
		post("/updateStock", payload);
	}
	
	@And("^the stock is successfully created$")
	public void theStockIsSuccessfullyCreated() {
		 Assert.assertEquals(201, getLastPostResponse().getStatus());
	}
	
	@Then("^the user receives status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastStatusCode());
	}
	
	 private String createStockPaylod() throws JSONException {
	    	JSONObject obj = new JSONObject();
			obj.put("id", "0003");
			obj.put("timeStamp", util.convertTimeStampToString(System.currentTimeMillis()));
			obj.put("productId", "vegetable-123");
			obj.put("quantity", "300");
			return obj.toString();
	    }
	 
	@Given("^a stock exists$")
	public void aStockExists() throws Throwable {
		createStock();
	}
	
	private void createStock() throws Exception {
		String paylod = createStockPaylod();
		post("/updateStock", paylod);
	}
	
	@And("^the user gets the stock$")
	public void theUserRetrievesTheStock() throws Throwable {
		get("/stock/{productId}" ,"vegetable-123");
	}
	
	@And("^the retrieved stock is correct$")
	public void theRetrievedStockIsCorrect() throws Throwable {
		assertStatisticsResourcesMatch(200, getLastStatusCode());
	}
	
	private static void assertStatisticsResourcesMatch(int expected, int actual) {
		Assert.assertEquals(expected,actual);
	}

}
