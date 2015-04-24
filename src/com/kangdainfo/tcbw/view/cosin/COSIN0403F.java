package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COSIN0403F extends SuperBean {
	
	private String cos0001DbId;
	private String assignMan;
	private String code;
	private String formCode;
	private String caseStatus; 
	
	public String getCos0001DbId() {		return checkGet(cos0001DbId);	}
	public void setCos0001DbId(String cos0001DbId) {	this.cos0001DbId = checkSet(cos0001DbId);	}
	public String getAssignMan() {		return checkGet(assignMan);	}
	public void setAssignMan(String assignMan) {		this.assignMan = checkSet(assignMan);	}
	public String getCode() {		return checkGet(code);	}
	public void setCode(String code) {		this.code = checkSet(code);	}
	public String getFormCode() {		return checkGet(formCode);	}
	public void setFormCode(String formCode) {	this.formCode = checkSet(formCode);	}
	public String getCaseStatus() {		return checkGet(caseStatus);	}
	public void setCaseStatus(String caseStatus) {		this.caseStatus = checkSet(caseStatus);	}

	private String q_userId;
	private String q_userName;
	
	public String getQ_userId() {		return checkGet(q_userId);	}
	public void setQ_userId(String q_userId) {		this.q_userId = checkSet(q_userId);	}
	public String getQ_userName() {		return checkGet(q_userName);	}
	public void setQ_userName(String q_userName) {		this.q_userName = checkSet(q_userName);	}
	
	public String updateAssign() throws Exception{
		StringBuffer sb = new StringBuffer();
		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getCos0001DbId()));
		if(cos0001Db != null){
			if(!"".equals(getAssignMan())){
				cos0001Db.setChargeMan(getAssignMan());
				cos0001Db.setStatus(getCaseStatus());
				cos0001Db.setModifier(getEditID());
				cos0001Db.setModifyDate(Datetime.getYYYMMDD());
				cos0001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(cos0001Db);
				sb.append("Y");
			}else{
				sb.append("無分派人員資料，重新操作 !");
			}
		}else{
			sb.append("查無該筆資料，重新操作 !");
		}
		return sb.toString();
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Con1015Db where competence like '%2%' " +
					 " and con1014Db.con1007Db.formCode = " + Common.sqlChar(getFormCode()) + 
					 " and con1014Db.code = " + Common.sqlChar(getCode());   
		if(!"".equals(getQ_userId()))
			hql += " and commonUser.userId like " + Common.sqlChar("%" + getQ_userId() + "%");
		if(!"".equals(getQ_userName()))
			hql += " and commonUser.userName like " + Common.sqlChar("%" + getQ_userName() + "%");
		System.out.println("[TCBW]-[COSIN0403F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if(getTotalRecord() > 0){
			if(getState().indexOf("query") < 0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id ", this.getRecordStart(), this.getPageSize());
			if(objList != null && objList.size() > 0){
				for(Object dtlObj : objList){
					Con1015Db dtl = (Con1015Db)dtlObj;
					
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getCon1014Db().getCode());	
					rowArray[2] = Common.get(dtl.getCon1014Db().getName());	
					rowArray[3] = Common.get(dtl.getCommonUser().getUserId());						
					rowArray[4] = Common.get(dtl.getCommonUser().getUserName());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
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
