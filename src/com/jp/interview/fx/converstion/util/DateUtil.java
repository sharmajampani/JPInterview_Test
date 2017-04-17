/**
 * 
 */
package com.jp.interview.fx.converstion.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *Utility class to work on date related conversions.
 *
 */
public class DateUtil {
	
	private static Calendar dayCheckCalendar = Calendar.getInstance();
	
	public static String getDayOfWeek(Date date) {
		
		dayCheckCalendar.setTime(date);
		String dayOfWeekStr = dayCheckCalendar.getDisplayName(Calendar.DAY_OF_WEEK
				, Calendar.LONG, Locale.getDefault());
		
		return dayOfWeekStr.toUpperCase();
	}	
	
	public static Date getNextWorkingDay(Date date, String currency) {
		dayCheckCalendar.setTime(date);
		int dayOfWeek = dayCheckCalendar.get(Calendar.DAY_OF_WEEK);
		
		if("AED".equals(currency) || "SAR".equals(currency)){
			switch (dayOfWeek) {
			case Calendar.FRIDAY:
				dayCheckCalendar.add(Calendar.DATE, 2);
				break;
			case Calendar.SATURDAY:
				dayCheckCalendar.add(Calendar.DATE, 1);
				break;
			} 
			
		} else {
			switch (dayOfWeek) {
			case Calendar.SATURDAY:
				dayCheckCalendar.add(Calendar.DATE, 2);
				break;
			case Calendar.SUNDAY:
				dayCheckCalendar.add(Calendar.DATE, 1);
				break;
			} 
		}
		return dayCheckCalendar.getTime();
	}


	/**
	 * Work week for all currencies except AED and SAR Currencies.
	 */
	public static boolean isDayInWorkWeek(String name) {
		
		final String[] days =  {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
		
		for (String day : days) {
			if(day.compareToIgnoreCase(name) == 0) {
				return true;
			}
		}
		return false;
		
	}
	/**
	 * Work week for AED and SAR Currencies.
	 */
	public static boolean isDayInWorkWeekForAEDAndSAR(String name) {
		
		final String[] days =  {"SUNDAY","MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY"};
		
		for (String day : days) {
			if(day.compareToIgnoreCase(name) == 0) {
				return true;
			}
		}
		return false;
		
	}
}
