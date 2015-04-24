package com.kangdainfo.tcbw.model.service.impl;

import java.io.File;
import java.util.List;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.common.model.bo.EmailLog;
import com.kangdainfo.tcbw.model.dao.Comple05Dao;
import com.kangdainfo.tcbw.model.dao.ConinDao;
import com.kangdainfo.tcbw.model.dao.CosexDao;
import com.kangdainfo.tcbw.model.dao.DrgexDao;
import com.kangdainfo.tcbw.model.dao.DrginDao;
import com.kangdainfo.tcbw.model.dao.HfrexDao;
import com.kangdainfo.tcbw.model.dao.HfrinDao;
import com.kangdainfo.tcbw.model.dao.CosinDao;
import com.kangdainfo.tcbw.model.dao.Medex1Dao;
import com.kangdainfo.tcbw.model.dao.Medex2Dao;
import com.kangdainfo.tcbw.model.dao.Medin1Dao;
import com.kangdainfo.tcbw.model.dao.Medin2Dao;
import com.kangdainfo.tcbw.model.dao.NativeSqlQuery;
import com.kangdainfo.tcbw.model.dao.PmedDao;
import com.kangdainfo.tcbw.model.dao.SdrgDao;
import com.kangdainfo.tcbw.model.service.TCBWService;

public class TCBWServiceImpl extends BaseDaoImpl implements TCBWService{

	protected ConinDao coninDao;
	public ConinDao getConinDao() {return coninDao;	}
	public void setConinDao(ConinDao coninDao) {
		this.coninDao = coninDao;
	}
	
	protected HfrexDao hfrexDao;
	public HfrexDao getHfrexDao() {return hfrexDao;	}
	public void setHfrexDao(HfrexDao hfrexDao) {
		this.hfrexDao = hfrexDao;
	}
	
	protected HfrinDao hfrinDao;
	public HfrinDao getHfrinDao() {return hfrinDao;	}
	public void setHfrinDao(HfrinDao hfrinDao) {
		this.hfrinDao = hfrinDao;
	}

	protected Medex1Dao medex1Dao;
	public Medex1Dao getMedex1Dao() {return medex1Dao;}
	public void setMedex1Dao(Medex1Dao medex1Dao) {this.medex1Dao = medex1Dao;}
	
	protected Medin1Dao medin1Dao;
	public Medin1Dao getMedin1Dao() {return medin1Dao;}
	public void setMedin1Dao(Medin1Dao medin1Dao) {this.medin1Dao = medin1Dao;} 
	
	protected Medex2Dao medex2Dao;
	public Medex2Dao getMedex2Dao() {return medex2Dao;}
	public void setMedex2Dao(Medex2Dao medex2Dao) {this.medex2Dao = medex2Dao;}
	
	protected Medin2Dao medin2Dao;
	public Medin2Dao getMedin2Dao() {return medin2Dao;}
	public void setMedin2Dao(Medin2Dao medin2Dao) {this.medin2Dao = medin2Dao;}
	
	protected DrgexDao drgexDao;
	public DrgexDao getDrgexDao() {return drgexDao;}
	public void setDrgexDao(DrgexDao drgexDao) {this.drgexDao = drgexDao;}
	
	protected DrginDao drginDao;
	public DrginDao getDrginDao() {return drginDao;}
	public void setDrginDao(DrginDao drginDao) {this.drginDao = drginDao;}
	
	protected CosexDao cosexDao;
	public CosexDao getCosexDao() {return cosexDao;	}
	public void setCosexDao(CosexDao cosexDao) {
		this.cosexDao = cosexDao;
	}
	
	protected CosinDao cosinDao;
	public CosinDao getCosinDao() {return cosinDao;	}
	public void setCosinDao(CosinDao cosinDao) {
		this.cosinDao = cosinDao;
	}
	
	protected SdrgDao sdrgDao;
	public SdrgDao getSdrgDao() {return sdrgDao;	}
	public void setSdrgDao(SdrgDao sdrgDao) {
		this.sdrgDao = sdrgDao;
	}
	
	protected PmedDao pmedDao;
	public PmedDao getPmedDao() {return pmedDao;	}
	public void setPmedDao(PmedDao pmedDao) {
		this.pmedDao = pmedDao;
	}
	
	protected Comple05Dao comple05Dao;
	
	public Comple05Dao getComple05Dao() {return comple05Dao;}
	public void setComple05Dao(Comple05Dao comple05Dao) {this.comple05Dao = comple05Dao;}

	protected NativeSqlQuery nativeSqlQuery;
	public NativeSqlQuery getNativeSqlQuery() {	return nativeSqlQuery;	}
	public void setNativeSqlQuery(NativeSqlQuery nativeSqlQuery) {this.nativeSqlQuery = nativeSqlQuery;	}
	
	@Override
	public void sendEmail(String subject, String mailBody, List<InternetAddress> mailList, List<String> attFilePathList) throws Exception {
		sendEmail(subject, mailBody, mailList, attFilePathList, false, null, null, null);
	}

	@Override
	public void sendEmail(String subject, String mailBody, List<InternetAddress> mailList, List<String> attFilePathList, boolean isHTML) throws Exception {
		sendEmail(subject, mailBody, mailList, attFilePathList, isHTML, null, null, null);
	}

	@Override
	public void sendEmail(String subject, String mailBody, List<InternetAddress> mailList,
			List<String> attFilePathList, boolean isHTML, File file,
			List<InternetAddress> ccmailList, List<InternetAddress> bccmailList) throws Exception
	{
		sendEmail(subject, mailBody, mailList, attFilePathList, isHTML, file, ccmailList, bccmailList,null,null,"N");
	}
	
	@Override
	public void sendEmail(String subject, String mailBody, List<InternetAddress> mailList,
			List<String> attFilePathList, boolean isHTML, File file,
			List<InternetAddress> ccmailList, List<InternetAddress> bccmailList,String systemType,String applNo) throws Exception
	{
		sendEmail(subject, mailBody, mailList, attFilePathList, isHTML, file, ccmailList, bccmailList,systemType,applNo,"N");
	}
	
	@Override
	public void sendEmail(String subject, String mailBody, List<InternetAddress> mailList,
			List<String> attFilePathList, boolean isHTML, File file,
			List<InternetAddress> ccmailList, List<InternetAddress> bccmailList,
			String systemType,String applNo,String isAgain
	) throws Exception 
	{
		logger.info("[TCBW]-[TCBWServiceImpl]-[進入寄信程式METHOD]");
		
		String hostName = "smtp.gmail.com";
		String sslSmptPort = "";
		Integer pop3Port = null;

		String user = "TCBW.USER";
		String pwd = "TCBW2013";
		
		
		String charset = "UTF-8";
		boolean isSsl = true;
		boolean isTls = true;
		String sendName = "TCBWService";
		String sendFrom = "TCBWService@kangdainfo.com";		
		
		com.kangdainfo.tcbw.model.bo.Sys0001Db sys0001Db = (com.kangdainfo.tcbw.model.bo.Sys0001Db) View.getObject("from Sys0001Db where id = '11112' ");

		if(sys0001Db != null)
		{
			// 若有查到就用DB的設定
			// Host Name：Field1
			// SMTP - SSL Port：Field2
			// POP3 - SSL Port：Field3
			// SSL：Field4
			// TLS：Field5
			// User Name：Field6
			// PassWord：Field7
			// 寄件者名稱：Field8
			// 寄件者EMAIL：Field9
			// 郵件編碼：Field10			
			hostName = Common.get(sys0001Db.getField1());
			sslSmptPort = Common.get(sys0001Db.getField2());
			pop3Port = !"".equals(Common.get(sys0001Db.getField3()))?Common.getInt(sys0001Db.getField3()):null;
			user = Common.get(sys0001Db.getField6());
			pwd = Common.get(sys0001Db.getField7());
			charset = Common.get(sys0001Db.getField10()).equals("")?"UTF-8":Common.get(sys0001Db.getField10());
			isSsl = "Y".equals(Common.get(sys0001Db.getField4()))?true:false;
			isTls = "Y".equals(Common.get(sys0001Db.getField5()))?true:false;
			sendName = Common.get(sys0001Db.getField8());
			sendFrom = Common.get(sys0001Db.getField9());
		}		
		
		EmailLog emailLog=null;
		
		//isAgain Y:重新發送郵件 N:第一次發送郵件
		//state A:重送成功 S:成功  F:失敗
		if("Y".equals(isAgain))
		{
			emailLog=(EmailLog)View.getObject("from EmailLog where id="+applNo); 
			emailLog.setState("A");
			emailLog.setFailReasons("");
			emailLog.setModifyDate(Datetime.getYYYMMDD());//紀錄重發時間
			emailLog.setModifyTime(Datetime.getHHMMSS());
		}
		else
		{
			emailLog=new EmailLog();
			emailLog.setApplNo(applNo);
			emailLog.setState("S");
			emailLog.setCreateTime(Datetime.getHHMMSS());//第一次發送時間
			emailLog.setCreateDate(Datetime.getYYYMMDD());
		}
		

		emailLog.setTitle(subject);
		emailLog.setMailBody(mailBody);
	    
	    //收件者
	    String mail="";
	    if(mailList!=null && mailList.size()>0)
	    {
	      for(int i=0;i<mailList.size();i++)
	      {
	    	mail+=mailList.get(i)+",";
	      }	
	    }
	    
	    if(mail.length()>0)
	      emailLog.setMail(mail.substring(0, mail.length()-1));
	    
	    //副件
	    String ccmail="";
	    if(ccmailList!=null && ccmailList.size()>0)
	    {
	      for(int i=0;i<ccmailList.size();i++)
	      {
	    	ccmail+=ccmailList.get(i)+",";
	      }	
	    }
	    
	    if(ccmail.length()>0)
	      emailLog.setCcmail(ccmail.substring(0, ccmail.length()-1));
	 
	    //密件
	    String bccmail="";
	    if(bccmailList!=null && bccmailList.size()>0)
	    {
	      for(int i=0;i<bccmailList.size();i++)
	      {
	    	bccmail+=bccmailList.get(i)+",";
	      }	
	    }
	    
	    if(bccmail.length()>0)
	      emailLog.setBccmail(bccmail.substring(0, bccmail.length()-1));
	    
	    emailLog.setSystemType(systemType);
	    
		if (isHTML) 
		{
			HtmlEmail x = new HtmlEmail();			
			x.setHostName(hostName);		
			if(!"".equals(sslSmptPort))	x.setSslSmtpPort(sslSmptPort);
			
			if(pop3Port != null)
			{
				x.setSmtpPort(pop3Port);		
			}

			x.setAuthentication(user, pwd);
			x.setCharset(charset);			
			x.setSSL(isSsl);
			x.setTLS(isTls);
			
			try
			{							
				x.setFrom(sendFrom, sendName);
				x.setSubject(subject);
				x.setTo(mailList);
				
				//副件
				if(ccmailList!=null && ccmailList.size()>0)
				  x.setCc(ccmailList);
				
				//密件
				if(bccmailList!=null && bccmailList.size()>0)
				  x.setBcc(bccmailList);

				x.setHtmlMsg(mailBody);
				
				if(file != null)
				{
					x.embed(file);
				}
				
			//	 attachment.setDisposition(EmailAttachment.ATTACHMENT);//设定附件的方式（内嵌，附件）
			//       attachment.setDescription("Picture");
			//        attachment.setName("logo.png")//附件的文件名				
				if(attFilePathList!=null && attFilePathList.size()>0)
				{
					for(String attFilePath : attFilePathList)
					{
						if(!"".equals(Common.get(attFilePath)))
						{
							java.io.File f = new java.io.File(attFilePath);
							if(f.exists() && f.isFile())
							{
								EmailAttachment attachment = new EmailAttachment();
								attachment.setPath(attFilePath);
								attachment.setDisposition(EmailAttachment.ATTACHMENT);
								attachment.setName(MimeUtility.encodeText(f.getName(),"big5","B"));//夾檔為中文名稱
								x.attach(attachment);
							}						
						}					
					}
				}			
				x.send();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				emailLog.setState("F");
				emailLog.setFailReasons(e.getMessage());
				saveOrUpdate(emailLog);
			}	
			saveOrUpdate(emailLog);
		}
		else
		{
			MultiPartEmail x = new MultiPartEmail();
			x.setHostName(hostName);		
			if(!"".equals(sslSmptPort))x.setSslSmtpPort(sslSmptPort);
			if(null != pop3Port)x.setSmtpPort(pop3Port);
			
			x.setAuthentication(user, pwd);
			x.setCharset(charset);			
			x.setSSL(isSsl);
			x.setTLS(isTls);		
			try
			{				
				x.setFrom(sendFrom, sendName);
				x.setSubject(subject);
				x.setTo(mailList);	
				x.setMsg(mailBody);
				
				if(attFilePathList!=null && attFilePathList.size()>0)
				{
					for(String attFilePath : attFilePathList)
					{
						if(!"".equals(Common.get(attFilePath)))
						{
							java.io.File f = new java.io.File(Common.get(attFilePath));
							if (f.exists() && f.isFile()) 
							{
								EmailAttachment attachment = new EmailAttachment();
								attachment.setPath(attFilePath);
								attachment.setDisposition(EmailAttachment.ATTACHMENT);
								attachment.setName(MimeUtility.encodeText(f.getName(),"big5","B"));//夾檔為中文名稱
								x.attach(attachment);
							}						
						}					
					}
				}			
				x.send();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				emailLog.setState("F");
				emailLog.setFailReasons(e.getMessage());
				saveOrUpdate(emailLog);
			}	
			
			saveOrUpdate(emailLog);
		}

		logger.info("[TCBW]-[TCBWServiceImpl]-[完成寄信程式METHOD]");

	}


	
	
}
