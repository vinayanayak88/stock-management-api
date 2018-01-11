/**
 * 
 */
package com.commerce.constant;

/**
 * @author vinayanayak
 * @date 09-Jan-2018
 * Constant.java
 */
public class Constant {
	
	// error messages
		public static final String NO_STATISTICS_FOUND = "No statistics found for last 60 secs";
		public static final String UNEXPECTED_ERROR = "Internal Error";
		public static final String VALIDATION_ERROR = "Validation error";
		public static final String VALIDATION_EMPTY_REQUEST_BODY = "Empty request body";
		public static final String VALIDATION_MISSING_TIMESTAMP = "Missing time stamp field";
		public static final String VALIDATION_MISSING_AMOUNT = "Missing amount field";
		public static final String VALIDATION_MISSING_AMOUNT_AND_TIMESTAMP = "Missing amount and timestamp field";
		public static final String INVALID_TRANSACTION = "Transaction is older than 60 secs";
		public static final String INVALID_DATETIME = "Invalid dateTime. Please enter the dateTime in yyyy-MM-dd'T'HH:mm:ss.sss'Z' format";
		public static final String OUTDATED_STOCK = "Outdated Stock";

}
