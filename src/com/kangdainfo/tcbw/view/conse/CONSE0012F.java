package com.kangdainfo.tcbw.view.conse;

import java.io.File;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.EmailLog;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0012F extends SuperBean{

	private String id;
	private String systemType;
	private String applNo;
	private String failReasons;
	private String mail;
	private String ccmail;
	private String bccmail;
	private String title;
	private String mailBody;
	private String createDate;
	private String mailState;
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_applNo;
	private String q_title;
	private String q_createDateS;
	private String q_createDateE;
	private String q_mailState;
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		CONSE0012F obj = this;
		EmailLog c = (EmailLog)View.getObject("from EmailLog where id = " + Common.getLong(getId()));		
		
		if(c != null)
		{
			java.util.Map<String, String> isAgainMap = TCBWCommon.getIsAgainMap();
			
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setApplNo(c.getApplNo());
			obj.setTitle(c.getTitle());
			obj.setMail(c.getMail());
			obj.setMailBody(c.getMailBody());
			obj.setFailReasons(c.getFailReasons());
			obj.setMailState(isAgainMap.get(c.getState()));
			obj.setEditID(c.getCreator());
			obj.setEditDate(c.getCreateDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from EmailLog where 1 = 1 ";
		
		if(!"".equals(getQ_id()))
		{
			hql += " and id = " + Common.getLong(getQ_id());
		}
		else
		{
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar( getQ_systemType());
		    
			if(!"".equals(getQ_mailState()))
				hql += " and state = " + Common.sqlChar( getQ_mailState());
			
			if(!"".equals(getQ_applNo()))
				hql += " and applNo = " + Common.sqlChar(getQ_applNo());
			
			if(!"".equals(getQ_createDateS()))
				hql += " and createDate >= " + Common.sqlChar(getQ_createDateS());
			
			if(!"".equals(getQ_createDateE()))
				hql += " and createDate <= " + Common.sqlChar(getQ_createDateE());
			
		    if(!"".equals(getQ_title()))
				hql += " and title like " + Common.sqlChar("%" + getQ_title() + "%");
			
			
		}
		System.out.println("[TCBW]-[CONSE0012F]-[QUERYALL]:"+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				//使用map先將資料撈出來
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");
			
				java.util.Map<String, String> isAgainMap = TCBWCommon.getIsAgainMap();
				
				for(Object dtlObj : objList) 
				{				
					EmailLog dtl = (EmailLog)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getSystemType()));	
					rowArray[2] = Common.get(isAgainMap.get(dtl.getState()));
					rowArray[3] = Common.get(dtl.getApplNo());
					rowArray[4] = Common.get(dtl.getTitle());	
					rowArray[5] = Common.get(dtl.getCreateDate());	
					rowArray[6] = Common.get(dtl.getModifyDate());	
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	


	@Override
	public void doCreate() throws Exception 
	{
		
	}
	

	@Override
	public void doUpdate() throws Exception 
	{
		if(!"".equals(getMailBody()) && getMailBody()!=null)
		{
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] mailAddress = getMail().split(",");
			
			java.util.List<javax.mail.internet.InternetAddress> ccmailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] ccmailAddress = getCcmail().split(",");

			java.util.List<javax.mail.internet.InternetAddress> bccmailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			String[] bccmailAddress = getBccmail().split(",");
			 
			if(mailAddress!=null && mailAddress.length>0)
			{
				for(String s : mailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    mailList.add(p);
				    
				}
			}
			if(ccmailAddress!=null && ccmailAddress.length>0)
			{
				for(String s : ccmailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    ccmailList.add(p);
				}
			}
			
			if(bccmailAddress!=null && bccmailAddress.length>0)
			{
				for(String s : bccmailAddress)
				{
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    bccmailList.add(p);
				}
			}
		
			if(mailList.size() > 0)
			{
				ServiceGetter.getInstance().getTcbwService().sendEmail(getTitle(), getMailBody(), mailList, null, true,null, ccmailList, bccmailList,getSystemType(),getId(),"Y");
			}
			
			mailList.clear();
		}
		else
		{
			throw new Exception("信件內容空値，請重新輸入");
		}
	}

	@Override
	public void doDelete() throws Exception 
	{

	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getSystemType() {
		return checkGet(systemType);
	}

	public void setSystemType(String systemType) {
		this.systemType = checkSet(systemType);
	}


	public String getTitle() {
		return checkGet(title);
	}

	public void setTitle(String title) {
		this.title = checkSet(title);
	}

	public String getMailBody() {
		return get(mailBody);
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_systemType() {
		return checkGet(q_systemType);
	}

	public void setQ_systemType(String qSystemType) {
		q_systemType = checkSet(qSystemType);
	}


	public String getQ_title() {
		return checkGet(q_title);
	}

	public void setQ_title(String qTitle) {
		q_title = checkSet(qTitle);
	}


	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	public String getFailReasons() {
		return checkGet(failReasons);
	}

	public void setFailReasons(String failReasons) {
		this.failReasons = checkSet(failReasons);
	}

	public String getMail() {
		return checkGet(mail);
	}

	public void setMail(String mail) {
		this.mail = checkSet(mail);
	}

	public String getCreateDate() {
		return checkGet(createDate);
	}

	public void setCreateDate(String createDate) {
		this.createDate = checkSet(createDate);
	}

	public String getQ_applNo() {
		return checkGet(q_applNo);
	}

	public void setQ_applNo(String qApplNo) {
		q_applNo = checkSet(qApplNo);
	}

	public String getQ_createDateS() {
		return checkGet(q_createDateS);
	}

	public void setQ_createDateS(String qCreateDateS) {
		q_createDateS = checkSet(qCreateDateS);
	}

	public String getQ_createDateE() {
		return checkGet(q_createDateE);
	}

	public void setQ_createDateE(String qCreateDateE) {
		q_createDateE = checkSet(qCreateDateE);
	}

	

	public String getMailState() {
		return checkGet(mailState);
	}

	public void setMailState(String mailState) {
		this.mailState = checkSet(mailState);
	}

	public String getQ_mailState() {
		return checkGet(q_mailState);
	}

	public void setQ_mailState(String qMailState) {
		q_mailState = checkSet(qMailState);
	}

	public String getCcmail() {
		return checkGet(ccmail);
	}

	public void setCcmail(String ccmail) {
		this.ccmail = checkSet(ccmail);
	}

	public String getBccmail() {
		return checkGet(bccmail);
	}

	public void setBccmail(String bccmail) {
		this.bccmail = checkSet(bccmail);
	}

	
	
	
	
}
