/**
 * 
 */
package com.commerce.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * @author vinayanayak
 * @date 10-Jan-2018
 * DateFormatUtil.java
 */
@Component
public class DateFormatUtil {
	
	public String convertTimeStampToString(long timeStamp) {
		Date currentDate = new Date(timeStamp);
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'",Locale.getDefault());
		 df.setTimeZone(TimeZone.getTimeZone("UTC"));
		 String dateTime = df.format(currentDate);
		return dateTime;
	}
	
	public Calendar getMidnightTimeStamp() {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		 cal.setTime(new Date());
		 cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		  cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
		  cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
		  cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal;
	}
	
	public long convertTimeStampToUTCTime(long timeStamp) {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.setTimeInMillis(timeStamp);
		return cal.getTimeInMillis();
	}
	
	public long calulateLastMonthTimeStamp() {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.MONTH, -1);
		return cal.getTimeInMillis();
		
	}

}
