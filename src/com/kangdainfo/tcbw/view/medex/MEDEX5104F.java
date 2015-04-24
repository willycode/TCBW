package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDEX5104F extends MEDEX5101F
{
	private String id1;
	private String applNo;
	private String notifyDate;
	private String notifyBody;
	private String replyDate;
	private String replyBody;
	private String userID;
	
	
	public void doReplyUpdate() throws Exception 
	{
		Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.sqlChar(getId1()) + "and id in (select max(id) from Con0004Db)");
		
		String title="【補件回覆】",mailBody="",mail="";
		
		if(obj!=null)
		{
			obj.setReplyBody(getReplyBody());
			obj.setReplyDate(getReplyDate());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setState("updateSuccess");
			
			String hql="from Med5001Db where ";
			       hql+=" id=(select max(id) from Med5001Db where applNo="+ Common.sqlChar(getApplNo())+")";
			
			Med5001Db med5001Db = (Med5001Db)View.getObject(hql);
			
			if(med5001Db!=null)
			{
				med5001Db.setStatus("30");//案件處理中
				Med2001Db med2001Db = (Med2001Db)View.getObject(" from Med2001Db where applNo= " + Common.sqlChar(getApplNo()));
				med2001Db.setStatus("30");//案件處理中		
				
				ServiceGetter.getInstance().getTcbwService().update(med2001Db);
				ServiceGetter.getInstance().getTcbwService().update(med5001Db);
				
				String email=View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(med2001Db.getWorkers()));
				       mail=email;
				
				title+="案號:"+med5001Db.getApplNo();
				mailBody=getReplyBody();
			

			    java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			
			    String[] mailAddress = mail.split(",");
			
			    if(mailAddress!=null && mailAddress.length>0)
			    {
				  for(String s : mailAddress)
				  {
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    mailList.add(p);
				  }
			    }
			
			    ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null);
			    TCBWCommon.setMailbackup("MED03",Common.get(med2001Db.getId()),title, mailBody, getApplNo(),"40", getUserID(),"");	

			    //記錄一筆案件流程紀錄
			    ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",med2001Db.getId(), obj.getApplNo(),med5001Db.getStatus(),"案件通報者-補件回復", getUserID());
		  }	
		}
		
		
		
		
	}
	
	@Override
	public Object doQueryOne() throws Exception 
	{
	  MEDEX5104F obj = this;
		
	  String hql=" from Con0004Db where applNo= " + Common.sqlChar(obj.getApplNo());
	  hql+=" and id=(select max(id) from Con0004Db where applNo="+ Common.sqlChar(obj.getApplNo())+")";
		  
	  Con0004Db c = (Con0004Db)View.getObject(hql);
		
	  if(c!=null)
	  {
		  System.out.println("[TCBW]-[MEDEX5104F]-[doQueryOne] : " + c.getId());
		  obj.setNotifyDate(c.getNotifyDate());
		  obj.setApplNo(c.getApplNo());
		  obj.setNotifyBody(Common.get(c.getNotifyBody()));
		  obj.setReplyDate(c.getReplyDate());
		  obj.setReplyBody(c.getReplyBody());
		  obj.setId1(Common.get(c.getId()));
	  }	  
	  return obj;

	}
	
	

	public String getUserID() {
		return checkGet(userID);
	}

	public void setUserID(String userID) {
		this.userID = checkSet(userID);
	}

	public String getNotifyDate() {
		return checkGet(notifyDate);
	}
	public void setNotifyDate(String notifyDate) {
		this.notifyDate = checkSet(notifyDate);
	}
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	public String getNotifyBody() {
		return checkGet(notifyBody);
	}
	public void setNotifyBody(String notifyBody) {
		this.notifyBody = checkSet(notifyBody);
	}
	public String getReplyDate() {
		return checkGet(replyDate);
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = checkSet(replyDate);
	}
	public String getReplyBody() {
		return checkGet(replyBody);
	}
	public void setReplyBody(String replyBody) {
		this.replyBody = checkSet(replyBody);
	}

	public String getId1() {
		return checkGet(id1);
	}

	public void setId1(String id1) {
		this.id1 = checkSet(id1);
	}

	
	
	
	
	
}
