package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDEX0106F extends MEDEX0101F
{
	private String id1;
	private String applNo;
	private String notifyDate;
	private String notifyBody;
	private String replyDate;
	private String replyBody;
	
	
	
	public void doReplyUpdate() throws Exception 
	{
		Con0004Db obj = (Con0004Db)View.getObject(" from Con0004Db where id= " + Common.getLong(getId1()));
		
		String title="【補件回覆】",mailBody="",mail="";
		
		if(obj!=null)
		{
			obj.setReplyBody(getReplyBody());
			obj.setReplyDate(getReplyDate());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			setState("updateSuccess");

			String hql="from Med4001Db where ";
			       hql+=" id=(select max(id) from Med4001Db where applNo="+ Common.sqlChar(getApplNo())+")";
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(hql);
			
			
			if(med4001Db!=null)
			{
				
				if("30".endsWith(med4001Db.getStatus()))
				  med4001Db.setStatus("20");//案件處理中
				else if("02".endsWith(med4001Db.getStatus()))
				  med4001Db.setStatus("11");//案件審核中(優先)

				Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where applNo= " + Common.sqlChar(getApplNo()));
				
				if("30".endsWith(med0001Db.getStatus()))
				  med0001Db.setStatus("20");//案件處理中
				else if("02".endsWith(med0001Db.getStatus()))
				  med0001Db.setStatus("11");//案件審核中(優先)

				ServiceGetter.getInstance().getTcbwService().update(med0001Db);
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
				
				String email=View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(med0001Db.getWorkers()));
				       mail=email;
				
				if("30".endsWith(med0001Db.getStatus()))
					title+="案號:"+med4001Db.getApplNo();
				else if("02".endsWith(med0001Db.getStatus()))
					title+="退件回覆";
				
				
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
			    
			    if("1".equals(med0001Db.getEventKind()))
			    {
			      TCBWCommon.setMailbackup("MED01",Common.get(med0001Db.getId()),title, mailBody, getApplNo(),med0001Db.getStatus(), getUserID(),"");
			      //記錄一筆案件流程紀錄
				  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(), obj.getApplNo(),med0001Db.getStatus(),"案件通報者-補件回復", getUserID());
			    }
			    else 
			    {
			      TCBWCommon.setMailbackup("MED02",Common.get(med0001Db.getId()),title, mailBody, getApplNo(),med0001Db.getStatus(), getUserID(),"");	
			      //記錄一筆案件流程紀錄
				  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(), obj.getApplNo(),med0001Db.getStatus(),"案件通報者-補件回復", getUserID());
			    }
			   
			}
		}
		
		
		
		
	}
	
	@Override
	public Object doQueryOne() throws Exception 
	{
	  MEDEX0106F obj = this;
		
	  String hql=" from Con0004Db where 1=1 ";
	       
	  hql+=" and id=(select max(id) from Con0004Db where applNo="+ Common.sqlChar(obj.getApplNo())+")";   
	 
	  System.out.println(hql);
	         
	  Con0004Db c = (Con0004Db)View.getObject(hql);
		
	  if(c!=null)
	  {
		  System.out.println("[TCBW]-[MEDEX0106F]-[doQueryOne] : " + c.getId());
		  obj.setNotifyDate(c.getNotifyDate());
		  obj.setApplNo(c.getApplNo());
		  obj.setNotifyBody(c.getNotifyBody());
		  obj.setReplyDate(c.getReplyDate());
		  obj.setReplyBody(c.getReplyBody());
		  obj.setId1(Common.get(c.getId()));
	  }	  
	  
	  return obj;

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
