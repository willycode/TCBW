package com.kangdainfo.util.uid;

import java.util.Calendar;

import com.kangdainfo.common.util.Common;

public class TimeBasedUniqueIdGenerator implements UniqueIdGenerator {

	private static UniqueIdGenerator uniqueIdGenerator;
	public synchronized static UniqueIdGenerator getInstance() {
		if(uniqueIdGenerator==null) {
			uniqueIdGenerator = new TimeBasedUniqueIdGenerator();
		}
		return uniqueIdGenerator;
	}
	
	private long lastTimeInMillis = 0;
	private int count = 0;
	
	public synchronized UniqueId next() {
		long currentTimeMillis = System.currentTimeMillis();
		if(currentTimeMillis==lastTimeInMillis) {
			count++;
		}
		else {
			count = 0;
		}
		lastTimeInMillis = currentTimeMillis;
		
		String uid = timeMillisToString(currentTimeMillis) + Common.formatFrontZero(String.valueOf(count), 2); //"_" + count;
		UniqueId uniqueId = new UniqueId(uid);
		return uniqueId;
	}
	
	private String timeMillisToString(long timeMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(timeMillis);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millis = calendar.get(Calendar.MILLISECOND);

		String YYYY = String.valueOf(year);
		String MM = month<10 ? "0"+month : String.valueOf(month);
		String DD = day<10 ? "0"+day : String.valueOf(day);
		String HH = hour<10 ? "0"+hour : String.valueOf(hour);
		String mm = minute<10 ? "0"+minute : String.valueOf(minute);		
		String ss = second<10 ? "0"+second : String.valueOf(second);

		String ms = null;
		if(millis>10 && millis<100) {
			ms = "0"+millis;
		}
		else if(millis<10) {
			ms = "00"+millis;
		}
		else {
			ms = String.valueOf(millis);
		}
		return YYYY+MM+DD+HH+mm+ss+ms;
	}
	

	
	
}
