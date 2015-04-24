package com.kangdainfo.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Datetime {
	
	private Datetime() {
		 // avoid instantiation...
	}

	/**
	 * <br>
	 * <br>目的：取得系統日期
	 * <br>參數：無
	 * <br>傳回：傳回字串YYYMMDD
	*/
    public static String getYYYMMDD() {
        StringBuffer sb = new StringBuffer();
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR) - 1911;
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);        
        if (y<=99){ sb.append("0"); }
        sb.append(Integer.toString(y));
        if (m<=9){ sb.append("0"); }
        sb.append(Integer.toString(m));
        if (d<=9){ sb.append("0"); }
        sb.append(Integer.toString(d));
        return sb.toString();
    }
	/**
	 * <br>
	 * <br>目的：取得系統日期
	 * <br>參數：無
	 * <br>傳回：傳回字串YYYYMMDD
	*/
    public static String getYYYYMMDD() {
        StringBuffer sb = new StringBuffer();
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);        
        if (y<=999){ sb.append("0"); }
        sb.append(Integer.toString(y));
        if (m<=9){ sb.append("0"); }
        sb.append(Integer.toString(m));
        if (d<=9){ sb.append("0"); }
        sb.append(Integer.toString(d));
        return sb.toString();
    }
    
    public static String getYYYMMDD(Date s) {
        StringBuffer sb = new StringBuffer();
        Calendar cal = Calendar.getInstance();
        cal.setTime(s);
        int y = cal.get(Calendar.YEAR) - 1911;
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);        
        if (y<=99){ sb.append("0"); }
        sb.append(Integer.toString(y));
        if (m<=9){ sb.append("0"); }
        sb.append(Integer.toString(m));
        if (d<=9){ sb.append("0"); }
        sb.append(Integer.toString(d));
        return sb.toString();
    }    
    
	/**
	 * <br>
	 * <br>目的：取得系統年月
	 * <br>參數：無
	 * <br>傳回：傳回字串YYYMM
	*/
	public static String getYYYMM(){
		return (getYYYMMDD().substring(0,5));
	}
	
	/**
	 * <br>
	 * <br>目的：取得系統年
	 * <br>參數：無
	 * <br>傳回：傳回字串YYY
	*/
	public static String getYYY(){
		return (getYYYMMDD().substring(0,3));
	}
	
	/**
	 * <br>
	 * <br>目的：取得系統月
	 * <br>參數：無
	 * <br>傳回：傳回字串MM
	*/
	public static String getMM(){
		return (getYYYMMDD().substring(3,5));
	}	

	/**
	 * <br>
	 * <br>目的：取得系統時間
	 * <br>參數：無
	 * <br>傳回：傳回字串HHMMSS
	*/
    public static String getHHMMSS() {
        Calendar calendar;
        String hh, mi, ss;
        calendar = Calendar.getInstance();
        hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) ;
        mi = String.valueOf(calendar.get(Calendar.MINUTE)) ;
        ss = String.valueOf(calendar.get(Calendar.SECOND)) ;
        if( hh.length() == 1) 
            hh = '0' + hh;
        if( mi.length() == 1) 
            mi = '0' + mi;
        if( ss.length() == 1) 
            ss = '0' + ss;   
        return hh + mi + ss;    
    }
  	

	/**
	 * <br>
	 * <br>目的：取得系統時間
	 * <br>參數：無
	 * <br>傳回：傳回字串HHMM
	*/
    public static String getHHMM() {
		return (getHHMMSS().substring(0,4));       
    }

    
	//  *********************************************
	//  函數功能：比較兩個日期
	//  參　　數：sdate,edate為起訖日期, sType可以是d(Day),m(Month),y(Year)
    //  範　　例：getDateDiff("m","0940910","0941210") 會傳回3
	//  傳 回 值：假如正確是傳回一字串值;假如錯誤則傳回空字串
	//  *********************************************    
    public static String getDateDiff(String sType, String sdate, String edate) {
    	String rStr="";
    	long lStr;
     	if( Common.get(sdate).length()==7 && Common.get(edate).length()==7 && Common.get(sType).length()>0){     		
    		GregorianCalendar g1 = new GregorianCalendar();
    		GregorianCalendar g2 = new GregorianCalendar();
	    	Date dates = null, datee=null;
	    	long one_day=1000*60*60*24;
	    	g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5)));
	    	g2.set(Integer.parseInt(edate.substring(0,3))+1911,Integer.parseInt(edate.substring(3,5))-1,Integer.parseInt(edate.substring(5)));
	    	dates = g1.getTime();
	    	datee = g2.getTime();
        	if (Common.get(sType).equals("y")) {
        		lStr = ((Integer.parseInt(edate.substring(0,3))-Integer.parseInt(sdate.substring(0,3))));        		        		
        	} else if (Common.get(sType).equals("m")) {
    			int sMonth = g1.get(GregorianCalendar.MONTH) + (g1.get(GregorianCalendar.YEAR) * 12);
    			int eMonth = g2.get(GregorianCalendar.MONTH) + (g2.get(GregorianCalendar.YEAR) * 12);		
    			lStr = eMonth-sMonth;        		
        	} else {
    			lStr = (datee.getTime()-dates.getTime())/one_day; 
        	}
        	rStr = ""+lStr;
     	}
     	return rStr;
    }    

	//  *********************************************
	//  函數功能：某一日期加上一定期間的日或月或年
	//  參　　數：sType可以是d(Day),m(Month),y(Year); sNum數值; sdate為日期
	//  傳 回 值：傳回加上特定期間之後的日期
	//  *********************************************    
    public static String getDateAdd(String sType, int sNum, String sdate) {
    	String rStr="";
     	if( Common.get(sdate).length()==7){     		
    		GregorianCalendar g1 = new GregorianCalendar();
        	if (Common.get(sType).equals("y")) {
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911+sNum,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5)));        		
        	} else if (Common.get(sType).equals("m")) {
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1+sNum,Integer.parseInt(sdate.substring(5)));
        	} else {
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5))+sNum);
        	}	    	
            StringBuffer sb = new StringBuffer();
            int y = g1.get(GregorianCalendar.YEAR) - 1911;            
            int m = g1.get(GregorianCalendar.MONTH) + 1;
            int d = g1.get(GregorianCalendar.DATE);        
            if (y<=99){ sb.append("0"); }
            sb.append(Integer.toString(y));
            if (m<=9){ sb.append("0"); }
            sb.append(Integer.toString(m));
            if (d<=9){ sb.append("0"); }
            sb.append(Integer.toString(d));
            rStr = sb.toString();
     	}
     	return rStr;
    }    	

	
	/**
	 * <br>
	 * <br>目的：取得二個日期之間的月份數
	 * <br>參數：alterMonth:第一個日期, dealMonth:第二個日期
	 * <br>傳回：傳回數字-1表長度不夠, -2表第一個日期大於第二個日期
	*/	
    public static int BetweenTwoMonth(String alterMonth , String dealMonth){
        int length = 0;
        if (dealMonth.length() < 5 || alterMonth.length() < 5){
            length = -1;
        }else{
            int dealInt = Integer.parseInt(dealMonth);
            int alterInt = Integer.parseInt(alterMonth);
            if (dealInt < alterInt){
                length = -2;	
            }else{
                int dealYearInt  = Integer.parseInt(dealMonth.substring(0, 3));
                int dealMonthInt = Integer.parseInt(dealMonth.substring(3, 5));
                int alterYearInt = Integer.parseInt(alterMonth.substring(0, 3));
                int alterMonthInt= Integer.parseInt(alterMonth.substring(3, 5));
                length = (dealYearInt - alterYearInt) * 12 + (dealMonthInt - alterMonthInt);
            }
        }
        return length;
    }
	
    /**
     * 
     * @param format
     * @return
     */
	public static String getCurrentDate(String format){
		if ("".equals(format)) format="yyyyMMdd";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
		java.util.Date currentDate = new java.util.Date();
		return formatter.format(currentDate);
	}
	

	/**
	 * 算出year/month年月當月天數	 
	 * @param year = 民國年
	 * @param month = 民國月
	 * @return int
	 */
	public static int getMaxDays(String year,String month){
		Calendar calendar = Calendar.getInstance(); 
		int yy=Integer.parseInt(year)+1911;
		int mm=Integer.parseInt(month);
		calendar.set(yy,mm-1,1);
		return calendar.getActualMaximum(Calendar.DATE); 
	}
	
  	/**
  	 * <br>
  	 * <br>目的：2個日期的相差天數　
  	 * <br>參數：String sdate 起日期,String edate 迄日期
  	 * <br>傳回：傳回long
  	*/
    public static long DateSubtraction(String sdate,String edate) {
    	return Long.parseLong(getDateDiff("d",sdate, edate));    	
    	/**
		GregorianCalendar g1 = new GregorianCalendar();
		GregorianCalendar g2 = new GregorianCalendar();
		g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5)));        
		g2.set(Integer.parseInt(edate.substring(0,3))+1911,Integer.parseInt(edate.substring(3,5))-1,Integer.parseInt(edate.substring(5)));
		Date d1 = g1.getTime();
		Date d2 = g2.getTime();
		long daterange = d2.getTime() - d1.getTime();
        long time = 1000*3600*24; //A day in milliseconds        
        return (daterange/time);
        **/
    }
	
    /**
     * 西元年轉為民國年
     * @param yyyymmdd
     * @return
     */
	public static String getRocDateFromYYYYMMDD(String date){
		String result = "";
		if(date != null && date.length() >= 8){
			String year = date.substring(0,4);
			String rocYear = String.valueOf(Integer.parseInt(year) - 1911);
			result = Common.formatFrontZero(rocYear,3) + date.substring(4);
		}
		return result;
	}
	
	/**
	 * 民國年轉為西元年
	 * <br> 0991122 --> 20101122
	 * @param yyyMMdd
	 * @return
	 */
	public static String getYYYYMMDDFromRocDate(String yyyMMdd)
	{
		String result = "";
		if(yyyMMdd != null && yyyMMdd.length() >= 7)
		{
			String year = yyyMMdd.substring(0,3);
			String rocYear = String.valueOf(Integer.parseInt(year) + 1911);
			result = Common.formatFrontZero(rocYear,4) + yyyMMdd.substring(3);
		}
		return result;
	}	
	

	/**
	 * 民國年轉為西元年 0991122 --> 2010-11-22
	 * @param yyyMMdd
	 * @param sep : 分隔號 e.g. -
	 * @return
	 */
	public static String getYYYYMMDDFromRocDate(String yyyMMdd, String sep){
		String result = getYYYYMMDDFromRocDate(yyyMMdd);
		if(result.length()==8){
			return result.substring(0,4)+Common.get(sep)+result.substring(4,6)+Common.get(sep)+result.substring(6);
		}
		return result;
	}	
    
    /**
     * 由網路時間伺服器取得日期
     * @param ntpServerName
     * @return
     */
    public static Date getDateTimeFromNTPServer(String ntpServerName) {		
		try {
			//time.stdtime.gov.tw
			//clock.stdtime.gov.tw
			if (ntpServerName==null) ntpServerName = "clock.stdtime.gov.tw";
			org.apache.commons.net.ntp.NTPUDPClient timeClient = new org.apache.commons.net.ntp.NTPUDPClient();
			java.net.InetAddress inetAddress = java.net.InetAddress.getByName(ntpServerName);
			org.apache.commons.net.ntp.TimeInfo timeInfo = timeClient.getTime(inetAddress);
			long returnTime = timeInfo.getReturnTime();
			Date time = new Date(returnTime);		
			return time;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    } 
    
	//  *********************************************
	//  函數功能：某一日期減去一定期間的日或月或年
	//  參　　數：sType可以是d(Day),m(Month),y(Year); sNum數值; sdate為日期
	//  傳 回 值：傳回減去特定期間之後的日期
	//  *********************************************    
    public static String getDateSubtraction(String sType, int sNum, String sdate) 
    {
    	String rStr="";
     	if( Common.get(sdate).length()==7)
     	{     		
    		GregorianCalendar g1 = new GregorianCalendar();
        	if (Common.get(sType).equals("y")) 
        	{
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911-sNum,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5)));        		
        	} 
        	else if (Common.get(sType).equals("m")) 
        	{
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1-sNum,Integer.parseInt(sdate.substring(5)));
        	} 
        	else 
        	{
        		g1.set(Integer.parseInt(sdate.substring(0,3))+1911,Integer.parseInt(sdate.substring(3,5))-1,Integer.parseInt(sdate.substring(5))-sNum);
        	}	    	
            StringBuffer sb = new StringBuffer();
            int y = g1.get(GregorianCalendar.YEAR) - 1911;            
            int m = g1.get(GregorianCalendar.MONTH) + 1;
            int d = g1.get(GregorianCalendar.DATE);        
            if (y<=99){ sb.append("0"); }
            sb.append(Integer.toString(y));
            if (m<=9){ sb.append("0"); }
            sb.append(Integer.toString(m));
            if (d<=9){ sb.append("0"); }
            sb.append(Integer.toString(d));
            rStr = sb.toString();
     	}
     	return rStr;
    }
    

	/**
     * 西元年轉為民國年
     * @param yyyymmdd
     * @return
     */
	public static String getRocDateTransFromYYYYMMDD(String date)
	{
		String result = "";
		String value[];
		
		if(date != null)
		{
            value=date.split("/");
            String year ="";
			if(value.length==3)
			{
				year = value[0];
				String rocYear = String.valueOf(Integer.parseInt(year) - 1911);
				result = Common.formatFrontZero(rocYear,3)+Common.formatFrontZero(value[1],2) + Common.formatFrontZero(value[2],2);
			}	
		}
		return result;
	}
    
    
}
