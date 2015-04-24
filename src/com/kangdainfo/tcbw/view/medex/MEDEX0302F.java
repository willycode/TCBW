package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0010Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;


/**
*<br>程式目的：藥品療效不等-廠商回覆
*<br>程式代號：drgex0306f
*<br>程式日期：1021126
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  MEDEX0302F extends MEDEX0301F
{

	private String actionType;
	private String replyDate;
	private String replyContent;
	private String noticeDate;
    
    
    @Override
    public Object doQueryOne() throws Exception 
    {
	  MEDEX0302F obj = this;
	  return obj;
	}
	


    @Override
    public Object doQueryAll() throws Exception 
    {
	  // TODO Auto-generated method stub
    	
    	
    	
	  return null;
    }

    @Override
    public void doCreate() throws Exception 
    {
	  // TODO Auto-generated method stub
	
    }

    @Override
    public void doUpdate() throws Exception 
    {
	  Med0010Db obj = (Med0010Db) View.getObject(" from Med0010Db where med0001Db.id=" + Common.getInt(getId()) + "and id in (select max(id) from Med0010Db)");
	  
	  if (obj!=null) 
	  {
		 obj.setReplyDate(Datetime.getYYYMMDD());
		 obj.setReplyContent(getReplyContent());
		 obj.setModifier(getUserID());
		 obj.setModifyDate(Datetime.getYYYMMDD());
		 obj.setModifyTime(Datetime.getHHMMSS());
		 
		 System.out.println("ACTIONTYPE: " + getActionType());
		 if("1".equals(getActionType())) {
		 Med4001Db med4001Db = (Med4001Db) View.getObject(" from Med4001Db where id in(select max(id) from Med4001Db where med0001ID= " + obj.getMed0001Db().getId() + ")");
		 
		 if(med4001Db!=null)
		 {
			 med4001Db.setStatus("60");
			 ServiceGetter.getInstance().getTcbwService().update(med4001Db);
		 }	 
		 
		 obj.getMed0001Db().setStatus("60");
	
		 ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getMed0001Db().getId(), obj.getMed0001Db().getApplNo(),"51", "案件審核者 -廠商回覆", getUserID());
		
		 }
		 ServiceGetter.getInstance().getTcbwService().update(obj);
		 setId(Common.get(obj.getId()));
	   }
    }

    @Override
    public void doDelete() throws Exception 
    {
	// TODO Auto-generated method stub
	
    }

public String getActionType() {return checkGet(actionType);}
public void setActionType(String s) {this.actionType = checkSet(s);}

public String getReplyDate() {
	return checkGet(replyDate);
}
public void setReplyDate(String replyDate) {
	this.replyDate = checkSet(replyDate);
}
public String getReplyContent() {
	return get(replyContent);
}
public void setReplyContent(String replyContent) {
	this.replyContent = checkSet(replyContent);
}
public String getNoticeDate() {
	return checkGet(noticeDate);
}
public void setNoticeDate(String noticeDate) {
	this.noticeDate = checkSet(noticeDate);
}

}


