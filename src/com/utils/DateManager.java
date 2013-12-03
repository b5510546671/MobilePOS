package com.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateManager {
	
	public static Date getCurrentDate(){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
		
		
		    return cal.getTime();
 	}

}
