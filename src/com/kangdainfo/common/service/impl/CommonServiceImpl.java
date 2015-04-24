package com.kangdainfo.common.service.impl;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

import com.kangdainfo.common.model.bo.CommonConfig;
import com.kangdainfo.common.model.bo.CommonNews;
import com.kangdainfo.common.model.bo.CommonNewsLog;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonVisitLogDetail;
import com.kangdainfo.common.model.dao.CommonAuthDao;
import com.kangdainfo.common.model.dao.CommonCodeDao;
import com.kangdainfo.common.model.dao.CommonCodeKindDao;
import com.kangdainfo.common.model.dao.CommonDepartmentDao;
import com.kangdainfo.common.model.dao.CommonDtreeDao;
import com.kangdainfo.common.model.dao.CommonGroupDao;
import com.kangdainfo.common.model.dao.CommonNewsDao;
import com.kangdainfo.common.model.dao.CommonUserDao;
import com.kangdainfo.common.model.dao.CommonUserRoleDao;
import com.kangdainfo.common.service.CommonService;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;

public class CommonServiceImpl extends BaseDaoImpl implements CommonService {
	
	private java.util.List<com.kangdainfo.common.model.bo.SubSystem> subSystems;	
	public void setSubSystems(java.util.List<com.kangdainfo.common.model.bo.SubSystem> subSystems) { this.subSystems = subSystems; }
	public java.util.List<com.kangdainfo.common.model.bo.SubSystem> getSubSystems() { return this.subSystems; }	
	public com.kangdainfo.common.model.bo.SubSystem getSubSystemById(int sid) {
		for(int i=0; i < subSystems.size(); i++) {
			if( subSystems.get(i).getId()==sid) return  subSystems.get(i);			
		}
		return null;
	}
	
	protected CommonDtreeDao commonDtreeDao;	
	protected CommonCodeDao commonCodeDao;
	protected CommonCodeKindDao commonCodeKindDao;
	protected CommonNewsDao commonNewsDao;
	protected CommonUserDao commonUserDao;
	protected CommonUserRoleDao commonUserRoleDao;
	protected CommonDepartmentDao commonDepartmentDao;
	protected CommonAuthDao commonAuthDao;
	protected CommonGroupDao commonGroupDao;
	
	public CommonCodeDao getCommonCodeDao() {
		return commonCodeDao;
	}
	public void setCommonCodeDao(CommonCodeDao commonCodeDao) {
		this.commonCodeDao = commonCodeDao;
	}
	public CommonCodeKindDao getCommonCodeKindDao() {
		return commonCodeKindDao;
	}
	public void setCommonCodeKindDao(CommonCodeKindDao commonCodeKindDao) {
		this.commonCodeKindDao = commonCodeKindDao;
	}
	public CommonDepartmentDao getCommonDepartmentDao() {
		return commonDepartmentDao;
	}
	public void setCommonDepartmentDao(CommonDepartmentDao commonDepartmentDao) {
		this.commonDepartmentDao = commonDepartmentDao;
	}
	public CommonDtreeDao getCommonDtreeDao() {
		return commonDtreeDao;
	}
	public void setCommonDtreeDao(CommonDtreeDao commonDtreeDao) {
		this.commonDtreeDao = commonDtreeDao;
	}
	public CommonNewsDao getCommonNewsDao() {
		return commonNewsDao;
	}
	public void setCommonNewsDao(CommonNewsDao commonNewsDao) {
		this.commonNewsDao = commonNewsDao;
	}
	public CommonUserDao getCommonUserDao() {
		return commonUserDao;
	}
	public void setCommonUserDao(CommonUserDao commonUserDao) {
		this.commonUserDao = commonUserDao;
	}
	public CommonAuthDao getCommonAuthDao() {
		return commonAuthDao;
	}
	public void setCommonAuthDao(CommonAuthDao commonAuthDao) {
		this.commonAuthDao = commonAuthDao;
	}
	public CommonUserRoleDao getCommonUserRoleDao() {
		return commonUserRoleDao;
	}
	public void setCommonUserRoleDao(CommonUserRoleDao commonUserRoleDao) {
		this.commonUserRoleDao = commonUserRoleDao;
	}
	public CommonGroupDao getCommonGroupDao() {
		return commonGroupDao;
	}
	public void setCommonGroupDao(CommonGroupDao commonGroupDao) {
		this.commonGroupDao = commonGroupDao;
	}	
	
	public void saveCommonVisitLog(CommonUser obj, String ip, String logAction, String logStatus, String logMsg) throws Exception {
		if (obj!=null) {
			
			String sdate = Datetime.getYYYMMDD();
			/**
			long[] times = new long[24];
	        
	        int h = calendar.get(Calendar.HOUR_OF_DAY);
			int i = 0;
			for (i=0; i<times.length; i++) {
				if (i==h) {
					times[i] = 1l;
				}
			}			
			
			CommonVisitLog logMaster = (CommonVisitLog) getObject("from CommonVisitLog where logDate='"+sdate+"'");
			
			if (logMaster==null) {
				logMaster = new CommonVisitLog();
				logMaster.setLogDate(sdate);
				logMaster.setTt(1l);
				for (i=0; i<times.length; i++) {
					BeanUtils.setProperty(logMaster, "t"+i, times[i]);
				}					
				isUpdate = false;				
			} else {
				BeanUtils.setProperty(logMaster, "t"+h, Common.getLong(BeanUtils.getProperty(logMaster, "t"+h))+1l);
				logMaster.setTt(logMaster.getTt()+1l);
			}						
			if (isUpdate) update(logMaster);
			else save(logMaster);			
			**/
			
			CommonVisitLogDetail logDetail = new CommonVisitLogDetail();
			logDetail.setLogDate(sdate);
			logDetail.setLogTime(Datetime.getHHMMSS());
			logDetail.setUserId(obj.getUserId());
			logDetail.setUserName(Common.get(obj.getUserName()));
			logDetail.setOrganId(obj.getCommonDepartment()!=null?obj.getCommonDepartment().getFullCode():"");
			logDetail.setOrganName(obj.getCommonDepartment()!=null?obj.getCommonDepartment().getFullName():"");
			logDetail.setUserIP(Common.get(ip));			
			logDetail.setLogStatus(Common.get(logStatus));
			logDetail.setLogMsg(Common.get(logMsg));
			logDetail.setLogAction(Common.get(logAction));			
			save(logDetail);
			
			/**
			if (logAction==null || !logAction.equals("2")) {
				boolean isUpdate = true;
				Calendar calendar=Calendar.getInstance();			
				int weekOfTheYear = calendar.get(Calendar.WEEK_OF_YEAR);			
				CommonVisitLogWeek logWeek = (CommonVisitLogWeek) getObject("from CommonVisitLogWeek where logYYY="+Common.sqlChar(sdate.substring(0,3))+" and logWeek="+weekOfTheYear);
				isUpdate = true;
				if (logWeek==null) {
					logWeek = new CommonVisitLogWeek();
					logWeek.setLogYyy(sdate.substring(0,3));
					logWeek.setLogWeek(weekOfTheYear);
					logWeek.setLogCount(0l);
					isUpdate = false;
				}
				logWeek.setLogCount(logWeek.getLogCount()+1);
				if (isUpdate) update(logWeek);
				else save(logWeek);				
			}
			**/
		}		
	}
	
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList) {
		sendEmail(subject, mailBody, mailList, attFilePathList, false);
	}

	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML) {
		//先預設值
		String hostName = "smtp.gmail.com";
		String sslSmptPort = "";
		Integer pop3Port = null;
		//String user = "kangdainfo";
		//String password = "0423021357";
		String user = "tcbw.user@gmail.com";
		String password = "TCBW2013";
		String charset = "UTF-8";
		boolean isSsl = true;
		boolean isTls = true;
		String sendName = "kangdainfo";
		String sendFrom = "kangdainfo@gmail.com";		
		
		//查設定檔
		Sys0001Db config = (Sys0001Db) View.getObject("from Sys0001Db where id='2' ");
		
		if(config!=null){
			//若有查到就用DB的設定
			//Host Name：Field1
			//SMTP - SSL Port：Field2
			//POP3 - SSL Port：Field3
			//SSL：Field4
			//TLS：Field5
			//User Name：Field6
			//PassWord：Field7
			//寄件者名稱：Field8
			//寄件者EMAIL：Field9
			//郵件編碼：Field10			
			hostName = Common.get(config.getField1());
			sslSmptPort = Common.get(config.getField2());
			pop3Port = !"".equals(Common.get(config.getField3()))?Common.getInt(config.getField3()):null;
			user = Common.get(config.getField6());
			password = Common.get(config.getField7());
			charset = Common.get(config.getField10()).equals("")?"UTF-8":Common.get(config.getField10());
			isSsl = "Y".equals(Common.get(config.getField4()))?true:false;
			isTls = "Y".equals(Common.get(config.getField5()))?true:false;
			sendName = Common.get(config.getField8());
			sendFrom = Common.get(config.getField9());
		}		
		System.out.println("Email Process--Begin");
		if (isHTML) {
			HtmlEmail x = new HtmlEmail();			
			x.setHostName(hostName);		
			if(!"".equals(sslSmptPort))x.setSslSmtpPort(sslSmptPort);
			if(null != pop3Port)x.setSmtpPort(pop3Port);				
			x.setAuthentication(user, password);
			x.setCharset(charset);			
			x.setSSL(isSsl);
			x.setTLS(isTls);			
			try {							
				x.setFrom(sendFrom,sendName);
				x.setSubject(subject);
				x.setTo(mailList);	
				x.setHtmlMsg(mailBody);
				
				if (attFilePathList!=null && attFilePathList.size()>0) {
					for (String attFilePath : attFilePathList) {
						if (!"".equals(Common.get(attFilePath))) {
							java.io.File f = new java.io.File(attFilePath);
							if (f.exists() && f.isFile()) {
								EmailAttachment attachment = new EmailAttachment();
								attachment.setPath(attFilePath);
								attachment.setDisposition(EmailAttachment.INLINE);
								x.attach(attachment);
							}						
						}					
					}
				}			
				x.send();
				System.out.println("Email Process--html");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} else {
			System.out.println("Email Process--NORMAL");
			
			MultiPartEmail x = new MultiPartEmail();
			x.setHostName(hostName);		
			if(!"".equals(sslSmptPort))x.setSslSmtpPort(sslSmptPort);
			if(null != pop3Port)x.setSmtpPort(pop3Port);				
			x.setAuthentication(user, password);
			x.setCharset(charset);			
			x.setSSL(isSsl);
			x.setTLS(isTls);		
			try {				
				x.setFrom(sendFrom,sendName);
				x.setSubject(subject);
				x.setTo(mailList);	
				x.setMsg(mailBody);
				
				if (attFilePathList!=null && attFilePathList.size()>0) {
					for (String attFilePath : attFilePathList) {
						if (!"".equals(Common.get(attFilePath))) {
							java.io.File f = new java.io.File(attFilePath);
							if (f.exists() && f.isFile()) {
								EmailAttachment attachment = new EmailAttachment();
								attachment.setPath(attFilePath);
								attachment.setDisposition(EmailAttachment.INLINE);
								x.attach(attachment);
							}						
						}					
					}
				}			
				x.send();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		System.out.println("Email Process--END");
	}		
	
	public boolean updateAndResetUserPassword(CommonUser obj) throws Exception {
		if (obj!=null && obj.getUserEmail()!=null && obj.getUserEmail().length()>0) {
			String newPassword = Common.getRandomPassword(8);
			obj.setUserPwd(Common.getDigestString(newPassword, "SHA-1"));				
			this.getHibernateTemplate().update(obj);			
			
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();						
			mailList.add(new javax.mail.internet.InternetAddress(obj.getUserEmail()));
			
			this.sendEmail(obj.getUserName()+", 這是您要求的新密碼", newPassword, mailList, null);
			
			return true;
		}
		return false;
	}		
	
	public void saveCommonNewsReadLog(CommonUser obj, int newsId) throws Exception {
		if (obj!=null && newsId>0) {
			CommonNewsLog log = (CommonNewsLog) getObject("from CommonNewsLog where commonNews.id=" + newsId + " and userId=" + Common.sqlChar(obj.getUserId()));
			if (log==null) {
				log = new CommonNewsLog();
				log.setUserId(obj.getUserId());
				
				CommonNews n = new CommonNews();
				n.setId(newsId);				
				log.setCommonNews(n);
				
				log.setLogDate(Datetime.getYYYMMDD());
				log.setLogTime(Datetime.getHHMMSS());
				
				save(log);				
			}
		}
	}	

}
