package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;

public class COSIN0605F extends SuperBean {

	private String cos0009DbId;
	private String cos0010DbId;
	private String notifyDate0910;				// 通知日期
	private String replyDate0910;				// 回覆日期		
	private String isComplaintYn0910;			// 是否接獲該產品其他消費者客訴
	private String complaintNum0910;			// 客訴件數
	private String similarComplaint0910;		// 類似客訴案件之後續處理情況
	private String precaution0910;				// 預防矯正措施
	private String handling0910;				// 目前處理情形/調查報告
	private String remark0910;					// 補充說明
	private String predictDate0910;				// 預計完成日期
	
	public String getCos0009DbId() {		return checkGet(cos0009DbId);	}
	public void setCos0009DbId(String cos0009DbId) {		this.cos0009DbId = checkSet(cos0009DbId);	}
	public String getCos0010DbId() {		return checkGet(cos0010DbId);	}
	public void setCos0010DbId(String cos0010DbId) {		this.cos0010DbId = checkSet(cos0010DbId);	}
	public String getNotifyDate0910() {		return checkGet(notifyDate0910);	}
	public void setNotifyDate0910(String notifyDate0910) {		this.notifyDate0910 = checkSet(notifyDate0910);	}
	public String getReplyDate0910() {		return checkGet(replyDate0910);	}
	public void setReplyDate0910(String replyDate0910) {		this.replyDate0910 = checkSet(replyDate0910);	}
	public String getIsComplaintYn0910() {		return checkGet(isComplaintYn0910);	}
	public void setIsComplaintYn0910(String isComplaintYn0910) {		this.isComplaintYn0910 = checkSet(isComplaintYn0910);	}
	public String getComplaintNum0910() {		return checkGet(complaintNum0910);	}
	public void setComplaintNum0910(String complaintNum0910) {		this.complaintNum0910 = checkSet(complaintNum0910);	}
	public String getSimilarComplaint0910() {		return checkGet(similarComplaint0910);	}
	public void setSimilarComplaint0910(String similarComplaint0910) {		this.similarComplaint0910 = checkSet(similarComplaint0910);	}
	public String getPrecaution0910() {		return checkGet(precaution0910);	}
	public void setPrecaution0910(String precaution0910) {		this.precaution0910 = checkSet(precaution0910);	}
	public String getHandling0910() {		return checkGet(handling0910);	}
	public void setHandling0910(String handling0910) {		this.handling0910 = checkSet(handling0910);	}
	public String getRemark0910() {		return checkGet(remark0910);	}
	public void setRemark0910(String remark0910) {		this.remark0910 = checkSet(remark0910);	}
	public String getPredictDate0910() {		return checkGet(predictDate0910);	}
	public void setPredictDate0910(String predictDate0910) {		this.predictDate0910 = checkSet(predictDate0910);	}

	private String COSVRFileRowSBuilder;
	public String getCOSVRFileRowSBuilder() {		return get(COSVRFileRowSBuilder);	}
	public void setCOSVRFileRowSBuilder(String cOSVRFileRowSBuilder) {		COSVRFileRowSBuilder = checkSet(cOSVRFileRowSBuilder);	}
	
	public String genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception {
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
		COSIN0605F obj = this;
		Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(getCos0009DbId()));
		Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where id  = " + Common.getLong(getCos0010DbId()));
		
		if(cos0009Db!=null && cos0010Db!=null){
			obj.setNotifyDate0910(cos0009Db.getNotifyDate());
			obj.setReplyDate0910(cos0009Db.getReplyDate());
			obj.setIsComplaintYn0910(cos0010Db.getIsComplaintYn());
			obj.setComplaintNum0910(cos0010Db.getComplaintNum());
			obj.setSimilarComplaint0910(cos0009Db.getSimilarComplaint());
			obj.setPrecaution0910(cos0009Db.getPrecaution());
			obj.setHandling0910(cos0009Db.getHandling());
			obj.setRemark0910(cos0009Db.getRemark());
			obj.setPredictDate0910(cos0009Db.getPredictDate());
			
			
			java.util.List<Con0001Db> file09List = null;
			if(cos0009Db != null){
				file09List = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0009DB' and upLoadId = " + Common.getLong(cos0009Db.getId()));
			}
			
			java.util.List<Con0001Db> file10List = null;
			if(cos0010Db != null){
				file10List = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSVR' and dbName = 'COS0010DB' and upLoadId = " + Common.getLong(cos0010Db.getId()));
			}
			
			java.util.List<Con0001Db> file0910List = new java.util.ArrayList<Con0001Db>();
			if(file09List!=null && file09List.size()>0 && file10List!=null && file10List.size()>0){
				file0910List.addAll(file09List);
				for(Con0001Db dtl10 : file10List){
					boolean flag = false;
					for(Con0001Db dtl09 : file09List){
						if(!Common.get(dtl10.getFileRoute()).equals(Common.get(dtl09.getFileRoute()))){
							file0910List.add(dtl10);
							flag = true;
						}
						if(flag){
							break;
						}
					}
				}
			}else{
				if(file09List!=null && file09List.size()>0){
					file0910List.addAll(file09List);
				}
				if(file10List!=null && file10List.size()>0){
					file0910List.addAll(file10List);
				}
			}
			
			if(file09List != null){
				file09List.clear();
			}
			file09List = null;
			
			if(file10List != null){
				file10List.clear();
			}
			file10List = null;
			
			if(file0910List!=null && file0910List.size()>0){
				obj.genFileRowItemSet(file0910List, "COSVR");				
			}
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
