/*
 * Created on 2004/3/9
 * 
 */
package com.kangdainfo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

/**
 * the DateUtil
 * 
 * @author airy.huang
 */
public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	public final static String DEFAULE_FORMAT = "yyyy/MM/dd";

	public final static String MM_DD_FORMAT = "MM/dd";

	// public final static String DEFAULE_FORMAT = "yyyy/MM/dd";

	public static void main(String args[]) {
		Date d1 = new Date(326307661456l) ;
		
		Date d2= new Date(80,4,5,2,15,16) ;		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d2);
		calendar.set(Calendar.MILLISECOND, 9999) ;
		
		System.out.println(d1.getTime()) ;
		System.out.println(d2.getTime()) ;
		System.out.println(clearTime(d1).getTime()) ;
		System.out.println(clearTime(d2).getTime()) ;
		System.out.println(d1.compareTo(d2)) ;		
		
	}
	
	/**
	 * Add date by amount
	 * 
	 * @param orignalDate
	 * @param dayAmount
	 * @return the date of dayAmount later or before
	 */
	public static String addDate(String orignalDate, int dayAmount) {
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULE_FORMAT);
		try {
			Date theDate = formatter.parse(orignalDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(theDate);
			calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
			return DateFormatUtils.format(calendar.getTime(), DEFAULE_FORMAT);
		} catch (ParseException e) {
			logger.debug(e);
		}
		return null;
	}

	/**
	 * Add date by amount
	 * 
	 * @param orignalDate
	 * @param dayAmount
	 * @return the date of dayAmount later or before
	 */
	public static Date addDate(Date orignalDate, int dayAmount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orignalDate);
		calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
		return calendar.getTime();

	}

	/**
	 * Add date by month amount.
	 * 
	 * @param orignalDate
	 * @param monthAmount
	 * @return the date of dayAmount later or before
	 */
	public static Date addDateByMonth(Date orignalDate, int monthAmount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orignalDate);
		calendar.add(Calendar.MONTH, monthAmount);
		return calendar.getTime();

	}

	/**
	 * Add date by amount
	 * 
	 * @param orignalDate
	 * @param dayAmount
	 * @param format
	 * @return the date of dayAmount later or before
	 */
	public static String addDate(String orignalDate, int dayAmount,
			String format) {
		if (format == null)
			format = DEFAULE_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date theDate = formatter.parse(orignalDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(theDate);
			calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
			return DateFormatUtils.format(calendar.getTime(), format);
		} catch (ParseException e) {
			logger.debug(e);
		}
		return null;
	}

	public static Date getStartOfWeekDate(Date date, int whatDay) {
		// Date theDate = DateUtils.truncate(date, Calendar.DAY_OF_WEEK);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int whatTheDay = calendar.get(Calendar.DAY_OF_WEEK);
		int shouldAddDays = 0;
		if (whatTheDay != whatDay) {
			if (whatDay > whatTheDay)
				whatTheDay = whatTheDay + 7;
			shouldAddDays = whatDay - whatTheDay;
			calendar.add(Calendar.DAY_OF_YEAR, shouldAddDays);
		}
		return calendar.getTime();
	}

	/**
	 * @param date
	 *            ,the Date to be processed
	 * @param whatDay
	 *            ,like Calendar.MONDAY,Calendar.THURSDAY....
	 * @return
	 */
	public static Date getNextStartOfWeekDate(Date date, int whatDay) {
		Date lastWeekDate = getStartOfWeekDate(date, whatDay);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastWeekDate);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return calendar.getTime();
	}

	/**
	 * getNextStartOfWeek
	 * 
	 * @param date
	 * @param whatDay
	 * @return
	 */
	public static String getNextStartOfWeek(String date, int whatDay) {
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULE_FORMAT);
		try {
			Date theDate = formatter.parse(date);
			theDate = getNextStartOfWeekDate(theDate, whatDay);
			return DateFormatUtils.format(theDate, DEFAULE_FORMAT);
		} catch (ParseException e) {
			logger.debug(e);
		}
		return null;
	}

	/***************************************************************************
	 * 
	 * @param date
	 * @return the 1st day of next month
	 */
	public static Date getNextStartOfMonth(Date date) {
		Date newDate = addDateByMonth(date, 1);
		return getStartOfMonth(newDate);
	}

	public static int getNextCalendarMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getNextStartOfMonth(date));
		return calendar.get(Calendar.MONTH);
	}

	public static Date parseToDate(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	public static Date parseToDate(String dateString) {
		return parseToDate(dateString, DEFAULE_FORMAT);
	}

	public static Date parseToDate(String dateString, String pattern) {
		if (dateString == null || dateString.trim().length() == 0) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			System.out.println("==dateString="+dateString);
			return formatter.parse(dateString);
		} catch (ParseException e) {
			logger.debug(e);
		}
		return null;
	}

	public static String parseToString(Date theDate, String pattern) {
		return DateFormatUtils.format(theDate, pattern);
	}
	public static String parseToSqlDateString(Date theDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String YYYY = String.valueOf(year);
		String MM = month<10?"0":"";
		MM += month;
		String DD = day<10?"0":"";
		DD += day;		
		return YYYY + "-" + MM + "-" + DD + " 00:00:00.0";
	}
	public static String parseToSqlTimestampString(Date theDate) {
		return parseToSqlTimestampDatePart(theDate) + " " + parseToSqlTimestampTimePart(theDate);
	}
	private static String parseToSqlTimestampDatePart(Date theDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String YYYY = String.valueOf(year);
		String MM = month<10?"0":"";
		MM += month;
		String DD = day<10?"0":"";
		DD += day;		
		return YYYY + "-" + MM + "-" + DD;
	}
	private static String parseToSqlTimestampTimePart(Date theDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String HH = hour<10?"0":"";
		HH += hour;
		String mm = minute<10?"0":"";
		mm += minute;
		String SS = second<10?"0":"";
		SS += second;		
		return HH + ":" + mm + ":" + SS + ".0";
	}	
	
	public static String parseToString(Date theDate) {
		return DateFormatUtils.format(theDate, DEFAULE_FORMAT);
	}

	public static int getWeekNumber(Date theDate, Date[] weeks) {
		boolean isSet = false;
		int weekNumber = 0;
		for (int j = 0; j < weeks.length && isSet == false; j++) {
			if (j == weeks.length - 1) { // the last date cell, ObjData
				// should
				// not before it
				if (!theDate.before(weeks[j])) {
					isSet = true;
					weekNumber = j + 1;
				}
			} else {
				if (!theDate.before(weeks[j]) && theDate.before(weeks[j + 1])) {
					isSet = true;
					weekNumber = j + 1;
				}
			}
		}
		return weekNumber;
	}

	public static Date getStartOfYear(Date orignalDate, int whatDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orignalDate);
		calendar.set(Calendar.WEEK_OF_YEAR, 1);
		return getStartOfWeekDate(calendar.getTime(), whatDay);
	}

	/**
	 * Get the week number of the input date belong to
	 * 
	 * @param theDate
	 * @return
	 */
	public static int getWeekNumOfYear(Date theDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}

	/**
	 * Get a date with time 0:00:00
	 * 
	 * @param theDate
	 * @return
	 */

	public static Date getDateWithoutTime(Date theDate) {
		DateTime temp = new DateTime(theDate);
		return (temp.withField(DateTimeFieldType.millisOfDay(), 0)).toDate();
	}

	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	public static Date getEndOfDay(Date date) {
		date = addDate(date,1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.setTimeInMillis(calendar.getTimeInMillis()-1);
		return calendar.getTime();
	}
	
	/**
	 * Get the first day of the month of the input date;
	 * 
	 * @param originalDate
	 * @return
	 */

	public static Date getStartOfMonth(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();
	}

	/**
	 * Get The Last day of the month of the input date
	 * 
	 * @param originalDate
	 * @return
	 */

	public static Date getEndOfMonth(Date originalDate) {
		return getLastOfMonth(originalDate) ;
	}
	public static Date getLastOfMonth(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();
	}

	/**
	 * Get last second date object of the month
	 * @param originalDate
	 * @return the date 
	 */
	public static Date getLastSecondOfMonth(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();
	}
	
	/**
	 * check if the target date bewteen beginDate and endDate
	 * 
	 * @param targetDate
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean IsDayInThePeriod(Date targetDate, Date beginDate,
			Date endDate) {
		if (!targetDate.before(beginDate)) {
			if (!targetDate.after(endDate))
				return true;

		}
		return false;
	}

	/**
	 * Get days difference bewteen 2 dates;
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDaysDiffer(Date date1, Date date2) {
		long dif = (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24);
		return dif;

	}

	public static int getMonthsDiffer(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		int year1 = calendar1.get(Calendar.YEAR);
		int year2 = calendar2.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int month2 = calendar2.get(Calendar.MONTH);

		int monthDiff = 0;

		if (year2 == year1) {
			monthDiff = month2 - month1;
		} else if (year2 > year1) {
			int yearDiff = year2 - year1;
			monthDiff = 11 - month1 + month2 + 1;
			monthDiff += (12 * (yearDiff - 1));
		} else { // year2<year1
			monthDiff = -1 * getMonthsDiffer(date2, date1);
		}
		return monthDiff;
	}

	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getCalendarMonth(Date date) {
		return getJavaMonth(date) + 1;
	}

	/***************************************************************************
	 * Java Month start from 0
	 * 
	 * @param date
	 * @return
	 */
	public static int getJavaMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	// public static int getNumberDaysInCalendarMonth(CalendarMonth
	// calendarMonth) {
	// return
	// getNumberDaysInCalendarMonth(calendarMonth.getCalendarMonth(),calendarMonth.getWesternYear());
	// }

	public static int getNumberDaysInCalendarMonth(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Returns the the last date of the current Decade
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastOfDecade(Date date) {
		int decade = getDecadeOfMonth(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (decade == 1) {
			calendar.set(Calendar.DAY_OF_MONTH, 10);
			return calendar.getTime();
		} else if (decade == 2) {
			calendar.set(Calendar.DAY_OF_MONTH, 20);
			return calendar.getTime();
		} else {
			return getLastOfMonth(date);
		}
	}

	public static int getDecadeOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth >= 1 && dayOfMonth <= 10) {
			return 1;
		} else if (dayOfMonth >= 11 && dayOfMonth <= 20) {
			return 2;
		} else {
			return 3;
		}
	}

	public static Date getStartOfYear(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0) ;
		return calendar.getTime();
	}

	public static Date getEndOfYear(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0) ;
		return calendar.getTime();
	}
	
	/**
	 * Get the last second date object of the year
	 * @param originalDate
	 * @return the date
	 */
	public static Date getTheLastSecondOfYear(Date originalDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originalDate);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0) ;
		return calendar.getTime();
	}
	
	public synchronized static Date clearTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0) ;
		calendar.set(Calendar.MINUTE, 0) ;
		calendar.set(Calendar.SECOND, 0) ;
		calendar.set(Calendar.MILLISECOND, 0) ;
		date.setTime(calendar.getTimeInMillis()) ;
		if (date instanceof Timestamp) {
			((Timestamp)date).setNanos(0) ;
		}
		return date;
	}

	/**
	 * create DateRange by Date <code>exp: '1980/05/25' </code>
	 * <code>return: '1980/05/25 00:00:00' to '1980/05/25 23:59:59'</code>
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] createDateRange(Date date) {
		Date d1 = new Date(date.getTime());
		clearTime(d1) ;
		Date d2 = new Date(date.getTime());
		d2.setHours(23);
		d2.setMinutes(59);
		d2.setSeconds(59);
		return new Date[] { d1, d2 };
	}

	public static Date[] createDateRange(Date beginDate, Date endDate) {
		clearTime(beginDate) ;
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return new Date[] { beginDate, endDate };
	}
	
	public static Date[] createMonthRange(Date date) {
		Date monthStart = DateUtil.getStartOfMonth(date);
		Date monthEnd = DateUtil.getEndOfMonth(date);
		return new Date[] { monthStart, monthEnd };
	}

}