package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;

public class COSIN0603F extends SuperBean {
	
	private String cos0009DbId; 
	private String notifyDate9;					// 通知日期
	private String handling;					// 目前處理情形/調查報告
	private String precaution9;					// 預防矯正措施
	private String similarComplaint;			// 類似客訴
	private String remark9;						// 補充說明
	private String predictDate;					// 預計完成日期
	private String replyDate9;					// 回覆日期
	
	public String getCos0009DbId() {		return checkGet(cos0009DbId);	}
	public void setCos0009DbId(String cos0009DbId) {		this.cos0009DbId = checkSet(cos0009DbId);	}
	public String getNotifyDate9() {		return checkGet(notifyDate9);	}
	public void setNotifyDate9(String notifyDate9) {		this.notifyDate9 = checkSet(notifyDate9);	}
	public String getHandling() {		return checkGet(handling);	}
	public void setHandling(String handling) {		this.handling = checkSet(handling);	}
	public String getPrecaution9() {		return checkGet(precaution9);	}
	public void setPrecaution9(String precaution9) {		this.precaution9 = checkSet(precaution9);	}
	public String getSimilarComplaint() {		return checkGet(similarComplaint);	}
	public void setSimilarComplaint(String similarComplaint) {		this.similarComplaint = checkSet(similarComplaint);	}
	public String getRemark9() {		return checkGet(remark9);	}
	public void setRemark9(String remark9) {		this.remark9 = checkSet(remark9);	}
	public String getPredictDate() {		return checkGet(predictDate);	}
	public void setPredictDate(String predictDate) {		this.predictDate = checkSet(predictDate);	}
	public String getReplyDate9() {		return checkGet(replyDate9);	}
	public void setReplyDate9(String replyDate9) {		this.replyDate9 = checkSet(replyDate9);	}
	
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
		COSIN0603F obj = this;
		Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(getCos0009DbId()));
		if(cos0009Db != null){
			obj.setCos0009DbId(Common.get(cos0009Db.getId()));
			obj.setNotifyDate9(cos0009Db.getNotifyDate());
			obj.setReplyDate9(cos0009Db.getReplyDate());
			obj.setHandling(cos0009Db.getHandling());
			obj.setPrecaution9(cos0009Db.getPrecaution());
			obj.setSimilarComplaint(cos0009Db.getSimilarComplaint());
			obj.setRemark9(cos0009Db.getRemark());
			obj.setPredictDate(cos0009Db.getPredictDate());
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0009DB' and upLoadId = " + Common.getLong(cos0009Db.getId()));
			obj.genFileRowItemSet(fileList, "COSVR");
		//	fileList.clear();
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
