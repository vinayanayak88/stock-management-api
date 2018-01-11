/**
 * 
 */
package com.commerce.test.acceptance.stock;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author vinayanayak
 * @date 11-Jan-2018
 * StockAcceptanceStep.java
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/acceptance/stock.feature", glue = {"com.commerce.test.acceptance.step"}, format = {"pretty"})
@WebAppConfiguration
public class StockAcceptanceTests {

}
