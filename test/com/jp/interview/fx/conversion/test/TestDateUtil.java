package com.jp.interview.fx.conversion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.jp.interview.fx.converstion.util.DateUtil;

/**
 * 
 * Test class with Junit test cases for Date Utility.
 *
 */
public class TestDateUtil {

	private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Test
	public void testDayOfWeek(){
		Date testDate = null;
		try {
			testDate = formatDate.parse("02-Jan-2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Asset the date is converted properly and is not null.
		assertNotNull(testDate);
		//As per calender 2nd Jan 2016 is a Saturday.
		assertEquals("SATURDAY", DateUtil.getDayOfWeek(testDate));
	}
	
	@Test
	public void testNextWorkingDay(){
		Date testDate = null;
		String currency1 = "USD";
		String currency2 = "AUD";
		String currency3 = "AED";
		try {
			testDate = formatDate.parse("03-Jan-2016");
			//Asset the date is converted properly and is not null.
			assertNotNull(testDate);
			
			//As per calender 3rd Jan 2016 is a Sunday. So with 'USD' and 'AUD' 
			//the method should return 4th Jan as working day and for 'AED' it should return the same day as working day.
			assertEquals(formatDate.parse("04-Jan-2016"), DateUtil.getNextWorkingDay(testDate, currency1));
			assertEquals(formatDate.parse("04-Jan-2016"), DateUtil.getNextWorkingDay(testDate, currency2));
			assertEquals(formatDate.parse("03-Jan-2016"), DateUtil.getNextWorkingDay(testDate, currency3));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDayInWorkWeek(){
		Date testDate = null;
		try {
			testDate = formatDate.parse("03-Jan-2016");
			//Asset the date is converted properly and is not null.
			assertNotNull(testDate);
			
			//As per calender 3rd Jan 2016 is a Sunday. So it is not a work week for all currencies except 'AED','SAR',
			//where as working day for .
			assertFalse(DateUtil.isDayInWorkWeek(DateUtil.getDayOfWeek(testDate)));
			assertTrue(DateUtil.isDayInWorkWeekForAEDAndSAR(DateUtil.getDayOfWeek(testDate)));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
