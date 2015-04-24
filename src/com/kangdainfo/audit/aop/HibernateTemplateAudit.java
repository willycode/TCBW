package com.kangdainfo.audit.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.persistence.PersistenceServiceGetter;

public class HibernateTemplateAudit implements AfterReturningAdvice,
		ThrowsAdvice {

	protected Logger logger = Logger.getLogger(this.getClass());

	private class HibernateLog {
		String userId;
		String userName;
		String userIP;
		String methodName;
		String entity;
		String queryString;
		String queryValues;
		String exceptionMessage;
		String progCode;
		String progName;
	}

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		HibernateLog hLog = getObjectLogMessage(method, args);
		saveLog(hLog);
		logger.info(toString(hLog));
	}

	public void afterThrowing(Method method, Object[] args, Object target,
			Throwable t) {
		HibernateLog hLog = getObjectLogMessage(method, args);
		hLog.exceptionMessage = t.getMessage();
		saveLog(hLog);
		logger.error(toString(hLog));
	}

	private HibernateLog getObjectLogMessage(Method method, Object[] args) {

		HibernateLog hLog = new HibernateLog();

		CommonUser currentUser = (CommonUser) ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		CommonDtree dtree = (CommonDtree) ServiceGetter.getInstance().getAuthenticationService().getCurrentDtree();

		if (method.getName().equals("save")
				|| method.getName().equals("saveOrUpdate")
				|| method.getName().equals("update")
				|| method.getName().equals("delete") 
				|| method.getName().equals("deleteAll")) {
			if (args.length == 1) {
				hLog.entity = args[0].toString();
			} else if (args.length == 2) {
				hLog.entity = args[1].toString();
			}
		} else if (method.getName().equals("bulkUpdate")) {
			hLog.entity = args[0].toString();
			if (args.length == 2) {
				hLog.queryValues = args[1].toString();
			}
		}		
		if (currentUser!=null) {
			hLog.userId = currentUser.getUserId();
			hLog.userName = currentUser.getUserName();
			hLog.userIP = currentUser.getUserIP();			
		}
		
		if (dtree!=null) {
			hLog.progCode = dtree.getBtnRead();
			hLog.progName = dtree.getName();
		}
		
		hLog.methodName = method.getName();

		return hLog;
	}

	private void saveLog(HibernateLog hLog) {
		if (hLog != null) {
			if (hLog.entity==null || "".equals(Common.get(hLog.entity)) || Common.get(hLog.entity).length()<11 || hLog.entity.indexOf("CommonVisitLog")!=-1) {
				return;
			}
			try {
				//long id = Common.getLong(View.getObject("select max(id) from CommonAuditLog"))+1;		
				StringBuilder sb1 = new StringBuilder(1024);
				sb1.append("INSERT INTO COMMON_AuditLog(id,logDate,logTime,userId,userName,userIP,methodName,entity,queryString,queryValues,exceptionMessage,progCode,progName)VALUES(");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");
				sb1.append("?,");	
				sb1.append("?)");	
				/**
				sb1.append(id).append(",");
				sb1.append(Common.sqlChar(Datetime.getYYYMM())).append(",");
				sb1.append(Common.sqlChar(Datetime.getHHMMSS())).append(",");
				sb1.append(Common.sqlChar(hLog.userId)).append(",");
				sb1.append(Common.sqlChar(hLog.userName)).append(",");
				sb1.append(Common.sqlChar(hLog.userIP)).append(",");
				sb1.append(Common.sqlChar(hLog.methodName)).append(",");
				sb1.append(Common.sqlChar(hLog.entity)).append(",");
				sb1.append(Common.sqlChar(hLog.queryString)).append(",");
				sb1.append(Common.sqlChar(hLog.queryValues)).append(",");
				sb1.append(Common.sqlChar(hLog.exceptionMessage)).append(")");
				PersistenceServiceGetter.getInstance().getJdbcTemplate().execute(sb1.toString());
				**/		
				PersistenceServiceGetter.getInstance().getJdbcTemplate().update(sb1.toString(), new Object[]{Common.getVMID(),Datetime.getYYYMMDD(), Datetime.getHHMMSS(), hLog.userId, hLog.userName, hLog.userIP, hLog.methodName, hLog.entity, hLog.queryString, hLog.queryValues, StringUtils.substring(Common.get(hLog.exceptionMessage),0,800), hLog.progCode, hLog.progName});				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String toString(HibernateLog hLog) {
		if (hLog == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append('"').append(hLog.userId).append('"').append(',');
		sb.append('"').append(hLog.userName).append('"').append(',');
		sb.append('"').append(hLog.userIP).append('"').append(',');
		sb.append('"').append(hLog.methodName).append('"').append(',');
		sb.append('"').append(hLog.entity).append('"').append(',');
		sb.append('"').append(hLog.queryString).append('"').append(',');
		sb.append('"').append(hLog.queryValues).append('"').append(',');
		sb.append('"').append(hLog.exceptionMessage).append('"');
		return sb.toString();
	}

}
