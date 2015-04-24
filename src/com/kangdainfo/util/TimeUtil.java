package com.kangdainfo.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
	public final static String DEFAULT_FORMAT = "YYYYMMDDHHmmSSsss";
	public final static String TO_THE_SECOND_FORMAT = "HHmmSS";
	public final static String FRIENDLY_TO_THE_SECOND_FORMAT = "HH:mm:SS";
	public final static String FRIENDLY_TO_THE_MILLIS_FORMAT = "YYYY/MM/DD HH:mm:SS sss";
	
	public static String parseToString(Date date,String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millis = calendar.get(Calendar.MILLISECOND);
		
		String YYYY = String.valueOf(year);
		String MM = month<10 ? ("0"+month) : String.valueOf(month);
		String DD = day<10 ? ("0"+day) : String.valueOf(day);
		
		String HH = hour<10 ? ("0"+hour) : String.valueOf(hour);
		String mm = minute<10 ? ("0"+minute) : String.valueOf(minute);		
		String SS = second<10 ? ("0"+second) : String.valueOf(second);
		
		String sss = null;
		if(millis>10 && millis<100) {
			sss = "0" + millis;
		}
		else if(millis < 10) {
			sss = "00" + millis;
		}
		else {
			sss = String.valueOf(millis);
		}
		
		String defaultOutput = YYYY + MM + DD + HH + mm + SS + sss;
		if(format.equals(FRIENDLY_TO_THE_SECOND_FORMAT)) {
			return HH + ":" + mm + ":" + SS;			
		}
		else if(format.equals(FRIENDLY_TO_THE_MILLIS_FORMAT)) {
			return YYYY + "/" + MM + "/" + DD + " " + HH + ":" + mm + ":" + SS + " " + sss;
		}
		else if(format.equals(TO_THE_SECOND_FORMAT)) {
			return HH + mm + SS;
		}
		else if(format.equals(DEFAULT_FORMAT)) {
			return defaultOutput;			
		}		
		return defaultOutput;	
	}
	
	public static Date parseToDate(String dateString,String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		if(format.equals(FRIENDLY_TO_THE_SECOND_FORMAT)) {
			String HH = dateString.substring(0,2);
			String mm = dateString.substring(3,5);
			String SS = dateString.substring(6,8);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HH));	
			calendar.set(Calendar.MINUTE, Integer.parseInt(mm));
			calendar.set(Calendar.SECOND, Integer.parseInt(SS));
		}
		else if(format.equals(FRIENDLY_TO_THE_MILLIS_FORMAT)) {
			String YYYY = dateString.substring(0,4);
			String MM = dateString.substring(5,7);
			String DD = dateString.substring(8,10);
			String HH = dateString.substring(11,13);
			String mm = dateString.substring(14,16);
			String SS = dateString.substring(17,19);
			String sss = dateString.substring(20,23);
			calendar.set(Calendar.YEAR, Integer.parseInt(YYYY));	
			calendar.set(Calendar.MONTH, Integer.parseInt(MM)-1);	
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(DD));	
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HH));	
			calendar.set(Calendar.MINUTE, Integer.parseInt(mm));
			calendar.set(Calendar.SECOND, Integer.parseInt(SS));
			calendar.set(Calendar.MILLISECOND, Integer.parseInt(sss));	
		}
		else if(format.equals(TO_THE_SECOND_FORMAT)) {
			String HH = dateString.substring(0,2);
			String mm = dateString.substring(2,4);
			String SS = dateString.substring(4,6);
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HH));	
			calendar.set(Calendar.MINUTE, Integer.parseInt(mm));
			calendar.set(Calendar.SECOND, Integer.parseInt(SS));
		}
		else if(format.equals(DEFAULT_FORMAT)) {
			String YYYY = dateString.substring(0,4);
			String MM = dateString.substring(4,6);
			String DD = dateString.substring(6,8);
			String HH = dateString.substring(8,10);
			String mm = dateString.substring(10,12);
			String SS = dateString.substring(12,14);
			String sss = dateString.substring(14,17);
			calendar.set(Calendar.YEAR, Integer.parseInt(YYYY));	
			calendar.set(Calendar.MONTH, Integer.parseInt(MM)-1);	
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(DD));	
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HH));	
			calendar.set(Calendar.MINUTE, Integer.parseInt(mm));
			calendar.set(Calendar.SECOND, Integer.parseInt(SS));
			calendar.set(Calendar.MILLISECOND, Integer.parseInt(sss));				
		}			
		return calendar.getTime();
	}
	
}
