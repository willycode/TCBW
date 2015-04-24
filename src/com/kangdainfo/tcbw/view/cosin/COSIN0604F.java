package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;

public class COSIN0604F extends SuperBean {
	
	private String cos0010DbId;
	private String notifyDate10;				// 通知日期
	private String isComplaintYn10;				// 是否接獲該產品其他消費者客訴
	private String complaintNum;				// 客訴件數
	private String dealWith10;					// 類似客訴案件之後續處理情形
	private String replyOther10;				// 其他回覆
	private String replyDate10;					// 回覆日期
	
	public String getCos0010DbId() {		return checkGet(cos0010DbId);	}
	public void setCos0010DbId(String cos0010DbId) {		this.cos0010DbId = checkSet(cos0010DbId);	}
	public String getNotifyDate10() {		return checkGet(notifyDate10);	}
	public void setNotifyDate10(String notifyDate10) {		this.notifyDate10 = checkSet(notifyDate10);	}
	public String getIsComplaintYn10() {		return checkGet(isComplaintYn10);	}
	public void setIsComplaintYn10(String isComplaintYn10) {		this.isComplaintYn10 = checkSet(isComplaintYn10);	}
	public String getComplaintNum() {		return checkGet(complaintNum);	}
	public void setComplaintNum(String complaintNum) {		this.complaintNum = checkSet(complaintNum);	}
	public String getDealWith10() {		return checkGet(dealWith10);	}
	public void setDealWith10(String dealWith10) {		this.dealWith10 = checkSet(dealWith10);	}
	public String getReplyOther10() {		return checkGet(replyOther10);	}
	public void setReplyOther10(String replyOther10) {		this.replyOther10 = checkSet(replyOther10);	}
	public String getReplyDate10() {		return checkGet(replyDate10);	}
	public void setReplyDate10(String replyDate10) {		this.replyDate10 = checkSet(replyDate10);	}

	private String COSVRFileRowSBuilder;
	
	public String getCOSVRFileRowSBuilder() {		return get(COSVRFileRowSBuilder);	}
	public void setCOSVRFileRowSBuilder(String cOSVRFileRowSBuilder) {		COSVRFileRowSBuilder = checkSet(cOSVRFileRowSBuilder);	}
	
	public String genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Con0001Db dtl : dtlList){
	    		sb.append("addFile").append(fileType).append("('tabFile" + fileType + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getFileName()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileRoute()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileExplan()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setCOSVRFileRowSBuilder(sb.toString());
	    }
		return "";
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		COSIN0604F obj = this;
		Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where id  = " + Common.getLong(getCos0010DbId()));
		if(cos0010Db != null){
			obj.setCos0010DbId(Common.get(cos0010Db.getId()));
			obj.setNotifyDate10(cos0010Db.getNotifyDate());
			obj.setIsComplaintYn10(cos0010Db.getIsComplaintYn());
			obj.setComplaintNum(cos0010Db.getComplaintNum());
			obj.setDealWith10(cos0010Db.getDealWith());
			obj.setReplyOther10(cos0010Db.getReplyOther());
			obj.setReplyDate10(cos0010Db.getReplyDate());

			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0010DB' and upLoadId = " + Common.getLong(cos0010Db.getId()));
			obj.genFileRowItemSet(fileList, "COSVR");
		}
		return obj;
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
