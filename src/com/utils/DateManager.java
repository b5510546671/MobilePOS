package com.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateManager {
	
	public static Date getCurrentDate(){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
		
		
		    return new Date();
	}
	
	public static String getDateString(Date date){
       String[] s = date.toLocaleString().split(" ");
       String send = s[1].replaceAll(",", "") + " "+ s[0] + " " + s[2] ; 
		return send;
	}

}
