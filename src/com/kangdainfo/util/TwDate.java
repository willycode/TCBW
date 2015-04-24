package com.kangdainfo.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * TwDate
 * 
 * @author catty
 * @version 1.0, Created on 2006/10/16
 */
public class TwDate extends Date {

	private static Logger logger = Logger.getLogger(TwDate.class);

	interface Formate {
		int YEAR = Calendar.YEAR;

		int MONTH = Calendar.MONTH;

		int DATE = Calendar.DATE;
	}

	public static void main(String args[]) {
		// today
		TwDate twDate = new TwDate();
		logger.info("twDate = " + twDate);
		logger.info("twDate.acYear = " + twDate.getAcYear());
		logger.info("twDate.twYear = " + twDate.getTwYear());
		logger.info("twDate.month = " + twDate.getTwMonth());
		logger.info("twDate.date = " + twDate.getTwDate());
		logger.info("");

		// create TwDate by Date
		TwDate twDate1 = new TwDate(new Date(80, 5, 25));
		logger.info("twDate1 = " + twDate1);
		logger.info("twDate1.acYear = " + twDate1.getAcYear());
		logger.info("twDate1.twYear = " + twDate1.getTwYear());
		logger.info("twDate1.month = " + twDate1.getTwMonth());
		logger.info("twDate1.date = " + twDate1.getTwDate());
		logger.info("");

		// create TwDate by rocDateStr
		TwDate twDate2 = new TwDate("0690525");
		logger.info("twDate2 = " + twDate2);
		logger.info("twDate2.acYear = " + twDate2.getAcYear());
		logger.info("twDate2.twYear = " + twDate2.getTwYear());
		logger.info("twDate2.month = " + twDate2.getTwMonth());
		logger.info("twDate2.date = " + twDate2.getTwDate());
		logger.info("");

		// create TwDate by rocYear,month,year
		TwDate twDate3 = new TwDate(69, 5, 25);
		logger.info("twDate3 = " + twDate3);
		logger.info("twDate3.acYear = " + twDate3.getAcYear());
		logger.info("twDate3.twYear = " + twDate3.getTwYear());
		logger.info("twDate3.month = " + twDate3.getTwMonth());
		logger.info("twDate3.date = " + twDate3.getTwDate());
		logger.info("");
	}

	/**
	 * TwDate
	 * 
	 */
	public TwDate() {
	}

	/**
	 * TwDate
	 * 
	 * @param date
	 *            Date
	 */
	public TwDate(Date date) {
		setYear(date.getYear());
		setMonth(date.getMonth());
		setDate(date.getDate());
	}

	/**
	 * TwDate
	 * 
	 * @param rocYear
	 * @param month
	 * @param date
	 */
	public TwDate(int rocYear, int month, int date) {
		setYear(rocYear + 11);
		setMonth(month);
		setDate(date);
	}

	/**
	 * TwDate <code>exp: 0690525</code>
	 * 
	 * @param yyymmdd(Roc
	 *            Date String)
	 */
	public TwDate(String yyymmdd) {
		String yy = getPartyOf(yyymmdd, Formate.YEAR);
		String mm = getPartyOf(yyymmdd, Formate.MONTH);
		String dd = getPartyOf(yyymmdd, Formate.DATE);
		setYear(Integer.parseInt(yy) + 11);
		setMonth(Integer.parseInt(mm)-1);
		setDate(Integer.parseInt(dd));
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// int type
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public int getAcYear() {
		return getYear() + 1900;
	}

	public int getTwYear() {
		return getYear() - 11;
	}

	public int getTwMonth() {
		return getMonth() + 1;
	}

	public int getTwDate() {
		return getDate();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// string type
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getAcYearStr() {
		return String.valueOf(getAcYear());
	}

	public String getTwYearStr() {
		return StringUtils.leftPad(getTwYear() + "", 3, '0');
	}

	public String getTwMonthStr() {
		return StringUtils.leftPad(getTwMonth() + "", 2, '0');
	}

	public String getTwDateStr() {
		return StringUtils.leftPad(getTwDate() + "", 2, '0');
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// formate
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static String getPartyOf(TwDate date, int calendarType) {
		return getPartyOf(date.toString(), calendarType);
	}

	public static String getPartyOf(String yyymmdd, int calendarType) {
		String find = null;
		int pos[] = new int[] { 0, 3, 5 };
		if (yyymmdd.length() < 7) {
			yyymmdd = StringUtils.leftPad(yyymmdd, 7, "0");
		}
		switch (calendarType) {
		case Calendar.YEAR:
			find = yyymmdd.substring(pos[0], pos[1]);
			break;
		case Calendar.MONTH:
			find = yyymmdd.substring(pos[1], pos[2]);
			break;
		case Calendar.DATE:
			find = yyymmdd.substring(pos[2]);
			break;
		default:
			break;
		}
		return find;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// toString
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String toString() {
		return TaiwanDateUtil.parseToString(this);
	}
}
