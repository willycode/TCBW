package com.kangdainfo.tcbw.model.service;

import java.io.File;

import com.kangdainfo.tcbw.model.dao.Comple05Dao;
import com.kangdainfo.tcbw.model.dao.ConinDao;
import com.kangdainfo.tcbw.model.dao.CosexDao;
import com.kangdainfo.tcbw.model.dao.CosinDao;
import com.kangdainfo.tcbw.model.dao.DrgexDao;
import com.kangdainfo.tcbw.model.dao.DrginDao;
import com.kangdainfo.tcbw.model.dao.HfrexDao;
import com.kangdainfo.tcbw.model.dao.HfrinDao;
import com.kangdainfo.tcbw.model.dao.Medex1Dao;
import com.kangdainfo.tcbw.model.dao.Medex2Dao;
import com.kangdainfo.tcbw.model.dao.Medin1Dao;
import com.kangdainfo.tcbw.model.dao.Medin2Dao;
import com.kangdainfo.tcbw.model.dao.NativeSqlQuery;
import com.kangdainfo.tcbw.model.dao.SdrgDao;
import com.kangdainfo.tcbw.model.dao.PmedDao;


public interface TCBWService extends com.kangdainfo.persistence.BaseDaoService{

	public ConinDao getConinDao();
	public void setConinDao(ConinDao obj);
	
	public HfrexDao getHfrexDao();
	public void setHfrexDao(HfrexDao obj);
	
	public HfrinDao getHfrinDao();
	public void setHfrinDao(HfrinDao obj);
	
	public Medex1Dao getMedex1Dao();
	public void setMedex1Dao(Medex1Dao obj);
	
	public Medin1Dao getMedin1Dao();
	public void setMedin1Dao(Medin1Dao obj);
	
	public Medex2Dao getMedex2Dao();
	public void setMedex2Dao(Medex2Dao obj);
	
	public Medin2Dao getMedin2Dao();
	public void setMedin2Dao(Medin2Dao obj);
	
	
	public DrgexDao getDrgexDao();
	public void setDrgexDao(DrgexDao obj);
	
	public DrginDao getDrginDao();
	public void setDrginDao(DrginDao obj);
	
	public CosexDao getCosexDao();
	public void setCosexDao(CosexDao obj);
	
	public CosinDao getCosinDao();
	public void setCosinDao(CosinDao obj);
	
	public SdrgDao getSdrgDao();
	public void setSdrgDao(SdrgDao obj);
	
	public Comple05Dao getComple05Dao();
	public void setComple05Dao(Comple05Dao obj);
	
	public NativeSqlQuery getNativeSqlQuery();
	public void setNativeSqlQuery(NativeSqlQuery NativeSqlQuery);
	
	public PmedDao getPmedDao();
	public void setPmedDao(PmedDao obj);
	
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList) throws Exception;
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML) throws Exception;
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML, File file, java.util.List<javax.mail.internet.InternetAddress> ccmailList, java.util.List<javax.mail.internet.InternetAddress> bccmailList) throws Exception;
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML, File file, java.util.List<javax.mail.internet.InternetAddress> ccmailList, java.util.List<javax.mail.internet.InternetAddress> bccmailList,String systemType,String applNo) throws Exception;
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML, File file, java.util.List<javax.mail.internet.InternetAddress> ccmailList, java.util.List<javax.mail.internet.InternetAddress> bccmailList,String systemType,String applNo,String isAgain) throws Exception;
	
}
