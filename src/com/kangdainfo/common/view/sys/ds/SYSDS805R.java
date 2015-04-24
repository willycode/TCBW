package com.kangdainfo.common.view.sys.ds;

import javax.swing.table.DefaultTableModel;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonVisitLogDetail;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

public class SYSDS805R extends SuperBean{
	String logYYY;
	String logDate;	
	String logID;
	String logTime;
	String userID;
	String userName;	
	String organID;
	String organName;		
	int t0;
	int t1;
	int t2;
	int t3;
	int t4;
	int t5;
	int t6;
	int t7;
	int t8;
	int t9;
	int t10;
	int t11;
	int t12;
	int t13;
	int t14;
	int t15;
	int t16;
	int t17;
	int t18;
	int t19;
	int t20;
	int t21;
	int t22;
	int t23;
	
	public String getLogYYY(){ return Common.get(logYYY); }
	public void setLogYYY(String s){ logYYY=checkSet(s); }	
	public String getLogDate(){ return Common.get(logDate); }
	public void setLogDate(String s){ logDate=checkSet(s); }	
	public int getT0(){ return t0; }
	public void setT0(int s){ t0=s; }
	public int getT1(){ return t1; }
	public void setT1(int s){ t1=s; }
	public int getT2(){ return t2; }
	public void setT2(int s){ t2=s; }
	public int getT3(){ return t3; }
	public void setT3(int s){ t3=s; }
	public int getT4(){ return t4; }
	public void setT4(int s){ t4=s; }
	public int getT5(){ return t5; }
	public void setT5(int s){ t5=s; }
	public int getT6(){ return t6; }
	public void setT6(int s){ t6=s; }
	public int getT7(){ return t7; }
	public void setT7(int s){ t7=s; }
	public int getT8(){ return t8; }
	public void setT8(int s){ t8=s; }
	public int getT9(){ return t9; }
	public void setT9(int s){ t9=s; }
	public int getT10(){ return t10; }
	public void setT10(int s){ t10=s; }
	public int getT11(){ return t11; }
	public void setT11(int s){ t11=s; }
	public int getT12(){ return t12; }
	public void setT12(int s){ t12=s; }
	public int getT13(){ return t13; }
	public void setT13(int s){ t13=s; }
	public int getT14(){ return t14; }
	public void setT14(int s){ t14=s; }
	public int getT15(){ return t15; }
	public void setT15(int s){ t15=s; }
	public int getT16(){ return t16; }
	public void setT16(int s){ t16=s; }
	public int getT17(){ return t17; }
	public void setT17(int s){ t17=s; }
	public int getT18(){ return t18; }
	public void setT18(int s){ t18=s; }
	public int getT19(){ return t19; }
	public void setT19(int s){ t19=s; }
	public int getT20(){ return t20; }
	public void setT20(int s){ t20=s; }
	public int getT21(){ return t21; }
	public void setT21(int s){ t21=s; }
	public int getT22(){ return t22; }
	public void setT22(int s){ t22=s; }
	public int getT23(){ return t23; }
	public void setT23(int s){ t23=s; }
	
	
	public String getLogID(){ return Common.get(logID); }
	public void setLogID(String s){ logID=checkSet(s); }
	public String getLogTime(){ return Common.get(logTime); }
	public void setLogTime(String s){ logTime=checkSet(s); }
	public String getUserID(){ return Common.get(userID); }
	public void setUserID(String s){ userID=checkSet(s); }
	public String getUserName(){ return Common.get(userName); }
	public void setUserName(String s){ userName=checkSet(s); }
	public String getOrganID(){ return Common.get(organID); }
	public void setOrganID(String s){ organID=checkSet(s); }
	public String getOrganName(){ return Common.get(organName); }
	public void setOrganName(String s){ organName=checkSet(s); }	
	
	String q_dsQueryLogTable;
	String q_connID;
	String q_logDate;
	String q_logDateE;
	String q_logTime;	
	String q_userID;
	String q_userName;
	public String getQ_logDate() {return checkGet(q_logDate);	}
	public void setQ_logDate(String s) {q_logDate = checkSet(s);	}
	public String getQ_logDateE() {	return checkGet(q_logDateE);	}
	public void setQ_logDateE(String s) {q_logDateE = checkSet(s);	}
	public String getQ_logTime() {return checkGet(q_logTime);	}
	public void setQ_logTime(String s) {q_logTime = checkSet(s);	}	
	public String getQ_dsQueryLogTable() {return checkGet(q_dsQueryLogTable);}
	public void setQ_dsQueryLogTable(String s) {q_dsQueryLogTable = checkSet(s);	}	
	public String getQ_userID() {return checkGet(q_userID);	}
	public void setQ_userID(String s) {q_userID = checkSet(s);	}
	public String getQ_userName() {	return checkGet(q_userName);	}
	public void setQ_userName(String s) {q_userName = checkSet(s);	}	
	
	int auditPercentage;
	public int getAuditPercentage() {return auditPercentage;	}
	public void setAuditPercentage(int auditPercentage) {this.auditPercentage = auditPercentage;	}
				
	public DefaultTableModel getVisitLogTableModel() throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] {"userId","userName","logDate","logTime","userIP"};		
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    	
		String hql = "from CommonVisitLogDetail where 1=1 ";
		if (!"".equals(getQ_logDate()))
			hql += " and logDate >= " + Common.sqlChar(getQ_logDate());
		if (!"".equals(getQ_logDateE()))
			hql += " and logDate<="+ Common.sqlChar(getQ_logDateE());
		if (!"".equals(getQ_logTime()))
			hql += " and logTime = " + Common.sqlChar(getQ_logTime());	
		if (!"".equals(getQ_userID()))
			hql += " and userId=" + Common.sqlChar(getQ_userID());
		if (!"".equals(getQ_userName()))
			hql += " and userName like " + Common.sqlChar("%"+getQ_userName()+"%");
		
		
		hql += " order by userId, userName, logDate, logTime";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonVisitLogDetail obj = (CommonVisitLogDetail) list.get(i);
				String rowArray[]=new String[cols.length];
				rowArray[0]=Common.get(obj.getUserId()); 
				rowArray[1]=Common.get(obj.getUserName()); 
				rowArray[2]=Common.formatYYYMMDD(obj.getLogDate()); 
				rowArray[3]=Common.formatHHMMSS(obj.getLogTime()); 
				rowArray[4]=Common.get(obj.getUserIP());
				arrList.add(rowArray);	
			}
		}		
		if (arrList!=null && arrList.size()>0) {
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}			
		return model;
	}

	@Override
	public void doCreate() throws Exception {
		/**
		try {			
			Calendar calendar=Calendar.getInstance();
			boolean isUpdate = true;
			String sdate = Datetime.getYYYMMDD();
			int[] times = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	        
	        int h = calendar.get(Calendar.HOUR_OF_DAY);
			int i = 0;
			for (i=0; i<times.length; i++) {
				if (i==h) {
					times[i] = 1;
				}
			}			
			
			CommonVisitLog logMaster = (CommonVisitLog) View.getObject("from CommonVisitLog where logDate='"+sdate+"'");
			
			if (logMaster==null) {
				logMaster = new CommonVisitLog();
				logMaster.setLogDate(sdate);
				isUpdate = false;				
			}
			for (i=0; i<times.length; i++) {
				BeanUtils.setProperty(logMaster, "t"+i, Common.getLong(BeanUtils.getProperty(logMaster, "t"+i)+times[i]));
			}			
			logMaster.setTt(logMaster.getTt()+1);
			if (isUpdate) ServiceGetter.getInstance().getCommonService().update(logMaster);
			else ServiceGetter.getInstance().getCommonService().save(logMaster);			
			
			
			
			CommonVisitLogDetail logDetail = new CommonVisitLogDetail();
			logDetail.setLogDate(sdate);
			logDetail.setLogTime(Datetime.getHHMMSS());
			logDetail.setUserId(userID);
			logDetail.setUserName(userName);
			logDetail.setOrganId(organID);
			logDetail.setOrganName(organName);
			ServiceGetter.getInstance().getCommonService().save(logDetail);
			 
			int weekOfTheYear = calendar.get(Calendar.WEEK_OF_YEAR);			
			CommonVisitLogWeek logWeek = (CommonVisitLogWeek) View.getObject("from CommonVisitLogWeek where logYYY="+Common.sqlChar(sdate.substring(0,3))+" and logWeek="+weekOfTheYear);
			isUpdate = true;
			if (logWeek==null) {
				logWeek = new CommonVisitLogWeek();
				logWeek.setLogYyy(sdate.substring(0,3));
				logWeek.setLogWeek(weekOfTheYear);
				logWeek.setLogCount(0l);
				isUpdate = false;
			}
			logWeek.setLogCount(logWeek.getLogCount()+1);
			if (isUpdate) ServiceGetter.getInstance().getCommonService().update(logWeek);			
			else ServiceGetter.getInstance().getCommonService().save(logWeek);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		**/		
	}
	
	
	String vDayCount, vWeekCount, vMonthCount, vYearCount, vMaxDayCount, vMaxDay, vMaxWeekCount, vMaxMonthCount, vMaxYearCount, vTotalCount, vMaxWeek, vMaxMonth, vMaxYear;
	String qDayCount, qWeekCount, qMonthCount, qYearCount, qMaxDayCount, qMaxWeekCount, qMaxMonthCount, qMaxYearCount, qTotalCount, qMaxDay, qMaxWeek, qMaxMonth, qMaxYear;	
	String reportByMonth;
	
	public String getReportByMonth() {
		return Common.get(reportByMonth);
	}

	public void setReportByMonth(String reportByMonth) {
		this.reportByMonth = Common.set(reportByMonth);
	}

	public void doQueryProcess() {
		dayCount();
		monthCount();
		yearCount();	
		totalCount();
		monthReport();
	}	
	
	private void dayCount() {
		String today = Datetime.getYYYMMDD();	
		this.setVDayCount(Common.MoneyFormat(View.getObject("select count(*) from CommonVisitLogDetail where logDate='"+today+"'")));		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load("select logDate, count(*) from CommonVisitLogDetail group by logDate ");
		if (list!=null && list.size()>0) {
			String maxDate = "";
			long m = 0l;
			for (int i=0; i<list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				if (Common.getLong(o[1])>m) {
					maxDate = Common.get(o[0]);
					m = Common.getLong(o[1]);
				}				
			}
			this.setVMaxDayCount(Common.MoneyFormat(m));		
			this.setVMaxDay(Common.formatYYYMMDD(maxDate,5));			
		}		
	}
	private void monthCount() {
		String thisMonth = Datetime.getYYYMM();
		String sql = "select count(*) from CommonVisitLogDetail where substring(logDate,1,5)='" + thisMonth +"'";
		this.setVMonthCount(Common.MoneyFormat(View.getObject(sql)));
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load("select substring(logDate,1,5), count(*) from CommonVisitLogDetail group by substring(logDate,1,5)");
		if (list!=null && list.size()>0) {
			String maxDate = "";
			long m = 0;
			for (int i=0; i<list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				if (Common.getLong(o[1])>m) {
					maxDate = Common.get(o[0]);
					m = Common.getLong(o[1]);
				}				
			}
			this.setVMaxMonthCount(Common.MoneyFormat(m));
			this.setVMaxMonth(Common.formatYYYMMDD(maxDate, 5));		
					
		}
		
	}
	
	private void yearCount() {
		String s = Datetime.getYYY();
		String sql = "select count(*) from CommonVisitLogDetail where substring(logDate,1,3)='" + s +"'";
		this.setVYearCount(Common.MoneyFormat(View.getObject(sql)));
		java.util.List list = ServiceGetter.getInstance().getCommonService().load("select substring(logDate,1,3), count(*) from CommonVisitLogDetail group by substring(logDate,1,3)");
		if (list!=null && list.size()>0) {
			String maxDate = "";
			long m = 0l;
			for (int i=0; i<list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				if (Common.getLong(o[1])>m) {
					maxDate = Common.get(o[0]);
					m = Common.getLong(o[1]);
				}				
			}
			this.setVMaxYearCount(Common.MoneyFormat(m));		
			this.setVMaxYear(maxDate);			
		}		
	}
	
	private void totalCount() {
		setVTotalCount(Common.MoneyFormat(View.getObject("select count(*) from CommonVisitLogDetail")));		
	}
	
	private String calVPercentage(String s) {
		double x = Common.getNumeric(s);
		double t = Common.getNumeric(getVTotalCount());		
		if (t<=0) t = 1;		
		double result = (x/t) * 100;
		return Common.MoneyFormat(result,2);
	}

	private void monthReport() {
		try {
			StringBuilder sb = new StringBuilder();
			String hql = "select substring(logDate,1,5), count(*) from CommonVisitLogDetail group by substring(logDate,1,5) order by substring(logDate,1,5)";	
			java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
			if (list!=null && list.size()>0) {
				for (int i=0; i<list.size(); i++) {
					Object[] o = (Object[]) list.get(i);
					sb.append("</TR>\n");
					sb.append("  <TR>\n");
					sb.append("  <td nowrap class=\"td_lable_white\">").append(Common.formatYYYMMDD(Common.get(o[0]), 5)).append("</TD>\n");
					sb.append("  <td nowrap class=\"td_lable_white\">").append(Common.MoneyFormat(o[1])).append("</TD>\n");
					sb.append("  <td nowrap class=\"td_lable_white\">").append(calVPercentage(Common.get(o[1]))).append("%</TD>\n");
					sb.append("</TR>\n");
				}
			}
			this.setReportByMonth(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getQDayCount() {
		return Common.get(qDayCount);
	}

	public void setQDayCount(String dayCount) {
		qDayCount = Common.set(dayCount);
	}

	public String getQMaxDayCount() {
		return Common.get(qMaxDayCount);
	}

	public void setQMaxDayCount(String maxDayCount) {
		qMaxDayCount = Common.set(maxDayCount);
	}

	public String getQMaxMonthCount() {
		return Common.get(qMaxMonthCount);
	}

	public void setQMaxMonthCount(String maxMonthCount) {
		qMaxMonthCount = Common.set(maxMonthCount);
	}

	public String getQMaxWeekCount() {
		return Common.get(qMaxWeekCount);
	}

	public void setQMaxWeekCount(String maxWeekCount) {
		qMaxWeekCount = Common.set(maxWeekCount);
	}

	public String getQMonthCount() {
		return Common.get(qMonthCount);
	}

	public void setQMonthCount(String monthCount) {
		qMonthCount = Common.set(monthCount);
	}

	public String getQWeekCount() {
		return Common.get(qWeekCount);
	}

	public void setQWeekCount(String weekCount) {
		qWeekCount = Common.set(weekCount);
	}

	public String getQYearCount() {
		return Common.get(qYearCount);
	}

	public void setQYearCount(String yearCount) {
		qYearCount = Common.set(yearCount);
	}

	public String getVDayCount() {
		return Common.get(vDayCount);
	}

	public void setVDayCount(String dayCount) {
		vDayCount = Common.set(dayCount);
	}

	public String getVMaxDayCount() {
		return Common.get(vMaxDayCount);
	}

	public void setVMaxDayCount(String maxDayCount) {
		vMaxDayCount = Common.set(maxDayCount);
	}

	public String getVMaxMonthCount() {
		return Common.get(vMaxMonthCount);
	}

	public void setVMaxMonthCount(String maxMonthCount) {
		vMaxMonthCount = Common.set(maxMonthCount);
	}

	public String getVMaxWeekCount() {
		return Common.get(vMaxWeekCount);
	}

	public void setVMaxWeekCount(String maxWeekCount) {
		vMaxWeekCount = Common.set(maxWeekCount);
	}

	public String getVMonthCount() {
		return Common.get(vMonthCount);
	}

	public void setVMonthCount(String monthCount) {
		vMonthCount = Common.set(monthCount);
	}

	public String getVWeekCount() {
		return Common.get(vWeekCount);
	}

	public void setVWeekCount(String weekCount) {
		vWeekCount = Common.set(weekCount);
	}

	public String getVYearCount() {
		return Common.get(vYearCount);
	}

	public void setVYearCount(String yearCount) {
		vYearCount = Common.set(yearCount);
	}
	public String getVMaxDay() {
		return Common.get(vMaxDay);
	}
	public void setVMaxDay(String maxDay) {
		vMaxDay = Common.set(maxDay);
	}
	public String getVMaxMonth() {
		return Common.get(vMaxMonth);
	}
	public void setVMaxMonth(String maxMonth) {
		vMaxMonth = Common.set(maxMonth);
	}
	public String getVMaxWeek() {
		return Common.get(vMaxWeek);
	}
	public void setVMaxWeek(String maxWeek) {
		vMaxWeek = Common.set(maxWeek);
	}
	public String getQMaxDay() {
		return Common.get(qMaxDay);
	}
	public void setQMaxDay(String maxDay) {
		qMaxDay = Common.set(maxDay);
	}
	public String getQMaxMonth() {
		return Common.get(qMaxMonth);
	}
	public void setQMaxMonth(String maxMonth) {
		qMaxMonth = Common.set(maxMonth);
	}
	public String getQMaxWeek() {
		return Common.get(qMaxWeek);
	}
	public void setQMaxWeek(String maxWeek) {
		qMaxWeek = Common.set(maxWeek);
	}
	public String getQMaxYear() {
		return Common.get(qMaxYear);
	}
	public void setQMaxYear(String maxYear) {
		qMaxYear = Common.set(maxYear);
	}
	public String getVMaxYear() {
		return Common.get(vMaxYear);
	}
	public void setVMaxYear(String maxYear) {
		vMaxYear = Common.set(maxYear);
	}
	public String getQMaxYearCount() {
		return Common.get(qMaxYearCount);
	}
	public void setQMaxYearCount(String maxYearCount) {
		qMaxYearCount = Common.set(maxYearCount);
	}
	public String getVMaxYearCount() {
		return Common.get(vMaxYearCount);
	}
	public void setVMaxYearCount(String maxYearCount) {
		vMaxYearCount = Common.set(maxYearCount);
	}
	public String getQTotalCount() {
		return Common.get(qTotalCount);
	}
	public void setQTotalCount(String totalCount) {
		qTotalCount = Common.set(totalCount);
	}
	public String getVTotalCount() {
		return Common.get(vTotalCount);
	}
	public void setVTotalCount(String totalCount) {
		vTotalCount = Common.set(totalCount);
	}
	
	
	@Override
	public void doDelete() throws Exception {
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		return null;
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	@Override
	public void doUpdate() throws Exception {
	}
}
