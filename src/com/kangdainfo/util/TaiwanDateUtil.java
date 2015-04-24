package com.kangdainfo.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.util.lang.NumberUtil;

public class TaiwanDateUtil {
	
	public interface Format {
		/****
		 * "YYYMMDD HHMMSS"
		 */
		int YYYMMDD_HHMMSS = 1;
		/**
		 * "YYY/MM/DD HH:MM:SS"
		 */
		int YYYMMDD_HHMMSS_WITH_SEPARATOR = 2;		
	}
	
	public interface Order {
		int ASC = 1;
		int DESC = 2;
	}

	private static Logger logger = Logger.getLogger(TaiwanDateUtil.class);

	public static void main(String args[]) {
		Date date = new Date(81, 9, 25);
		String yyymmdd = TaiwanDateUtil.parseToString(date);
		logger.info(date + " = " + yyymmdd);
		logger.info(date + ".year = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.YEAR));
		logger.info(date + ".month = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.MONTH));
		logger.info(date + ".date = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.DATE));

		TaiwanDateUtil.validate("1980/05/25");
		TaiwanDateUtil.validate("0700228");
		TaiwanDateUtil.validate("0700229");
		TaiwanDateUtil.validate("0700532");
		
		//logger.info("==="+getTwYearByRadix("008","112")+"===");
		
		logger.info("==="+parseToString(new Date()));
		
		
	}
	
	public static boolean validate(String twDateText) {
		boolean flg = true;
		String dateStr = "";
		if (twDateText == null) {
			flg = false;
		} else {
			try {
				String tt = twDateText.trim();
				if (tt.length() < 7)
					tt = StringUtils.leftPad(tt, 7, "0");
				Date date = parseToDate(tt);
				dateStr = date.toLocaleString();
				int newM = date.getMonth() + 1;
				int newD = date.getDate();

				int oldM = Integer.parseInt(TaiwanDateUtil.getPartOf(tt,
						Calendar.MONTH));
				int oldD = Integer.parseInt(TaiwanDateUtil.getPartOf(tt,
						Calendar.DATE));

				if (newM != oldM)
					flg = false;
				else if (newD != oldD)
					flg = false;
			} catch (Exception e) {
				flg = false;
			}
		}
		logger
				.info("[validate] " + twDateText + " -> " + dateStr + " = "
						+ flg);
		return flg;
	}

	/**
	 * parse To Tw String
	 * 
	 * @param date
	 * @return yyymmdd
	 */
	public static String parseToString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.YEAR) - 1911), 3, '0'));
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.MONTH)+1), 2, '0'));
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.DAY_OF_MONTH)), 2, '0'));
		return sb.toString();
	}
	public static String parseToStringNullSafe(Date date) {
		if(date==null) { return ""; }
		return parseToString(date);
	}
	
	public static String parseToString(Date date, int format) {
		if(date==null) {
			return "";
		}
		
		String YYYMMDD = parseToString(date);
		String HHMMSS = parseToHHMMSS(date);
		
		if(format==Format.YYYMMDD_HHMMSS) {
			return YYYMMDD + " " + HHMMSS;
		}
		else if(format==Format.YYYMMDD_HHMMSS_WITH_SEPARATOR) {
			return YYYMMDD.substring(0,3) + "/" + YYYMMDD.substring(3,5) + "/" + YYYMMDD.substring(5) +
			" " + HHMMSS.substring(0,2) + ":" + HHMMSS.substring(2,4) + ":" + HHMMSS.substring(4);
		}
		return YYYMMDD + HHMMSS;
	}
	
	/***************************************************************************
	 * @param HHMMSS
	 *            (24Hour)
	 * @return Date without Year, Month, Day components
	 */
	public static Date parseToTime(String HHMMSS) {
		String HH_ = HHMMSS.substring(0, 2);
		String MM_ = HHMMSS.substring(2, 4);
		String SS_ = HHMMSS.substring(4, 6);
		int HH = Integer.parseInt(HH_);
		int MM = Integer.parseInt(MM_);
		int SS = Integer.parseInt(SS_);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.HOUR_OF_DAY, HH);
		calendar.set(Calendar.MINUTE, MM);
		calendar.set(Calendar.SECOND, SS);
		return calendar.getTime();
	}
	public static String parseToHHMMSS(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String HH = hour<10?"0"+hour:""+hour;
		String MM = minutes<10?"0"+minutes:""+minutes;
		String SS = sec<10?"0"+sec:""+sec;	
		return HH+MM+SS;
	}
	public static String parseToYYYMMDDHHMMSS(Date date) {
		String YYYMMDD = parseToString(date);
		String HHMMSS = parseToHHMMSS(date);
		return YYYMMDD + HHMMSS;
	}

	/***************************************************************************
	 * @param YYYMMDD
	 *            (e.g. 0950101=民國95年一月一日)
	 * @return Date without Hour, Minute, Second, Millis components
	 */
	public static Date parseToDate(String YYYMMDD) {
		String ymd = YYYMMDD.trim();
		if (ymd.length() < 7)
			ymd = StringUtils.leftPad(ymd, 7, "0");
		String YYY_ = ymd.substring(0, 3);
		String MM_ = ymd.substring(3, 5);
		String DD_ = ymd.substring(5, 7);
		int YYYY = twYearToWesternYear(Integer.parseInt(YYY_));
		int MM = Integer.parseInt(MM_);
		int DD = Integer.parseInt(DD_);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, YYYY);
		calendar.set(Calendar.MONTH, MM - 1);
		calendar.set(Calendar.DAY_OF_MONTH, DD);
		return calendar.getTime();
	}

	public static int twYearToWesternYear(int twYear) {
		return twYear + 1900 + 11;
	}

	public static int westernYearToTwYear(int westernYear) {
		return westernYear - 1900 - 11;
	}

	/***************************************************************************
	 * @param date
	 * @return Taiwan Year in 3 digits
	 */
	public static String dateToTwYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		int year = westernYearToTwYear(calendar.get(Calendar.YEAR));
		String YYY = year < 100 ? "0" + year : String.valueOf(year);
		return YYY;
	}


	public static String getPartOf(String yyymmdd, int calendarType) {
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
		case Calendar.DAY_OF_MONTH:
			find = yyymmdd.substring(pos[2]);
			break;
		default:
			break;
		}
		return find;
	}
	

	public static String getYYYYMMDD(String yyymmdd) {				
		return getPartOf(yyymmdd,Calendar.YEAR)+ getPartOf(yyymmdd,Calendar.MONTH) + getPartOf(yyymmdd,Calendar.DATE);
	}
	
	public static String getYear(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.YEAR);
	}
	public static String getMonth(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.MONTH);
	}
	public static String getDayOfMonth(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.DAY_OF_MONTH);
	}
	
	
	/**
	 * e.g. orgTwYear = 096, radix = 5, order = 1
	 * 	return x = {'091', '092', '093', '094', '095'}
	 * 
	 * e.g. orgTwYear = 096, radix = 5, order = 2
	 *  return x = {'095', '094', '093', '092', '091'}
	 * 
	 * @param orgTwYear
	 * @param radix
	 * @return
	 */
	public static String[] getPreviousTwYearByRadix(String orgTwYear, int radix, int order) {
		if(radix <= 0 || orgTwYear ==  null || orgTwYear.equals("") || NumberUtil.isInteger(orgTwYear) == false) {
			return null;
		}
		String[] twYear = new String[radix];
		int baseTwYear = Integer.parseInt(orgTwYear);
		for(int i=0; i<radix; i++) {
			String x = new String();
			if(order == Order.ASC) {
				x = String.valueOf(baseTwYear - radix+i);
			} else {
				x = String.valueOf(baseTwYear - (i + 1));
			}
			
			if(x.length() < 3) {
				x = "0" + x;				
			}
			twYear[i] = x;
		}
		return twYear;
	}
	
	/**
	 * 統計年報專用_年度 Map
	 * ex. 民國五十六年 1965
	 * @param strTwYear
	 * @param endTwYear
	 * @return
	 */
	public static java.util.Map<String, String> getTwYearByRadix(String strTwYear, String endTwYear) {
		if(strTwYear ==  null || strTwYear.equals("") || NumberUtil.isInteger(strTwYear) == false || strTwYear.length()!=3 ) {
			return null;
		}
		if(endTwYear ==  null || endTwYear.equals("") || NumberUtil.isInteger(endTwYear) == false || endTwYear.length()!=3 ) {
			return null;
		}
		java.util.Map<String, String> ysMap = new java.util.TreeMap<String, String>();
		java.util.Map<String, String> tmpMap = new java.util.TreeMap<String, String>();
		tmpMap.put("0", "零");
		tmpMap.put("1", "一");
		tmpMap.put("2", "二");
		tmpMap.put("3", "三");
		tmpMap.put("4", "四");
		tmpMap.put("5", "五");
		tmpMap.put("6", "六");
		tmpMap.put("7", "七");
		tmpMap.put("8", "八");
		tmpMap.put("9", "九");		
		int star = Integer.parseInt(strTwYear);
		int stop  = Integer.parseInt(endTwYear);
		int radix = (stop-star);
		if (radix>=0){
			int year = 0;
			int yyyy = 0;     //西元年
			String yy = "";
			String yyy = "";
			String twName = "";  //中文大寫年 
			for(int i=0; i<=radix; i++) {
				year = star+i;
				yyy = String.valueOf(year);
				twName = "民國";
				for(int j=0; j<yyy.length(); j++){
					yy = tmpMap.get(yyy.substring(j,j+1));
					twName+=yy;
					switch(yyy.length()){
					case 2: //<100
						switch(j){
						case 0:  
							twName+="十";							
							break;
						case 1:
							if (yy.equals("零")){
								twName=twName.substring(0,twName.length()-1);
							}
							break;						
					   }
					   break;
					case 3: //>100
						switch(j){
						case 0:  
							twName+="百";							
							break;
						case 1:			
							if (!yy.equals("零")){
								twName+="十";
							}
							break;	
						case 2:			
							if (yy.equals("零")){
								twName=twName.substring(0,twName.length()-1);
							}
							break;	
					   }					   
					   break;
					}	
					if (year==100){
						twName="民國一百";
					}
					if (j==yyy.length()-1)	twName+="年";	
					
					
				}
				ysMap.put(Common.formatFrontString(yyy, 3, '0'), twName+"  "+twYearToWesternYear(year));
			}
		}
		return ysMap;
		
	}
	
}
