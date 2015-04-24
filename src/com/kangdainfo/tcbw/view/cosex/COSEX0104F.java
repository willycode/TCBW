package com.kangdainfo.tcbw.view.cosex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0004Db;

public class COSEX0104F extends SuperBean {
	
	private String actionMsg;
	
	public String getActionMsg() {		return checkGet(actionMsg);	}
	public void setActionMsg(String actionMsg) {		this.actionMsg = checkSet(actionMsg);	}

	private String cos4001DbId;
	private String applNo;
	private String con0004DbId; 
    private String notifyDate;				// 通知補件日期
    private String notifyBody;				// 補件說明
    private String replyDate;				// 補件回覆日期
    private String replyBody;				// 補件回覆
    
	public String getCos4001DbId() {		return checkGet(cos4001DbId);	}
	public void setCos4001DbId(String cos4001DbId) {		this.cos4001DbId = checkSet(cos4001DbId);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getCon0004DbId() {		return checkGet(con0004DbId);	}
	public void setCon0004DbId(String con0004DbId) {		this.con0004DbId = checkSet(con0004DbId);	}
	public String getNotifyDate() {		return checkGet(notifyDate);	}
	public void setNotifyDate(String notifyDate) {		this.notifyDate = checkSet(notifyDate);	}
	public String getNotifyBody() {		return checkGet(notifyBody);	}
	public void setNotifyBody(String notifyBody) {		this.notifyBody = checkSet(notifyBody);	}
	public String getReplyDate() {		return checkGet(replyDate);	}
	public void setReplyDate(String replyDate) {		this.replyDate = checkSet(replyDate);	}
	public String getReplyBody() {		return checkGet(replyBody);	}
	public void setReplyBody(String replyBody) {		this.replyBody = checkSet(replyBody);	}
	
	private javax.servlet.ServletRequest httpRequest;
    public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	private String[] ATTFileRow;
	
	public String[] getATTFileRow() {		return checkGet(ATTFileRow);	}
	public void setATTFileRow(String[] aTTFileRow) {		ATTFileRow = checkSet(aTTFileRow);	}

	public final String[] arrAttFileFieldName = {"ATTFileName", "ATTFileNameRoute"};

	@Override
	public Object doQueryOne() throws Exception {
		COSEX0104F obj = this;
		if("replySuccess".equals(obj.getActionMsg()) || "replyError".equals(obj.getActionMsg())){
			obj.setNotifyDate("");
			obj.setNotifyBody("");
			if("replySuccess".equals(obj.getActionMsg())){
				obj.setActionMsg("B");
				obj.setErrorMsg("補件回覆完成");
			}else{
				obj.setActionMsg("C");
				if("".equals(obj.getErrorMsg())){
					obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
				}
			}
		}else{
			Con0004Db con0004Db = (Con0004Db)View.getObject(" from Con0004Db where applNo = " + Common.sqlChar(getApplNo()) + 
															" and (replyDate is null or replyDate = '') " +
															" order by id desc ");
			if(con0004Db != null){
				obj.setCon0004DbId(Common.get(con0004Db.getId()));
				obj.setNotifyDate(con0004Db.getNotifyDate());
				obj.setNotifyBody(con0004Db.getNotifyBody().replaceAll("<p>", "").replaceAll("</p>", ""));
			}else{
				obj.setActionMsg("C");
				obj.setErrorMsg("未有未回覆之案件紀錄 !");
			}
		}
		return obj;
	}

	public void doReplyUpdate() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCaseReply(this);
	}
	
	
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
