package com.kangdainfo.tcbw.view.conex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONEX0201F extends SuperBean{
	
	private String id;
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}

	private String q_department;
	private String q_userId;
	private String q_userName;
	private String q_personalId;
	private String q_userTel;
	private String q_userJobName;

	public String getQ_department() {		return checkGet(q_department);	}
	public void setQ_department(String q_department) {		this.q_department = checkSet(q_department);	}
	public String getQ_userId() {		return checkGet(q_userId);	}
	public void setQ_userId(String q_userId) {		this.q_userId = checkSet(q_userId);	}
	public String getQ_userName() {		return checkGet(q_userName);	}
	public void setQ_userName(String q_userName) {		this.q_userName = checkSet(q_userName);	}
	public String getQ_personalId() {		return checkGet(q_personalId);	}
	public void setQ_personalId(String q_personalId) {		this.q_personalId = checkSet(q_personalId);	}
	public String getQ_userTel() {		return checkGet(q_userTel);	}
	public void setQ_userTel(String q_userTel) {		this.q_userTel = checkSet(q_userTel);	}
	
	public String getQ_userJobName() {
		return checkGet(q_userJobName);
	}
	public void setQ_userJobName(String qUserJobName) {
		q_userJobName = checkSet(qUserJobName);
	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from CommonUser where 1 = 1 ";
		if(!"".equals(getQ_department())){
			hql += " and commonDepartment.shortCode = " + Common.sqlChar(getQ_department());
		}else{
			hql += " and commonDepartment.shortCode in ('01','02','03','04')";
		}

		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if (!"in".equalsIgnoreCase(c.getInORout())) {
			setQ_userId(c.getUserId());  //非內部使用者只能查詢自己
		}
		c = null;
		
		if(!"".equals(getQ_userId()))
			hql += " and userId = " + Common.sqlChar(getQ_userId());
		
		if(!"".equals(getQ_userName()))
			hql += " and userName like " + Common.sqlChar("%" + getQ_userName() + "%");
		if(!"".equals(getQ_personalId()))
			hql += " and personalId = " + Common.sqlChar(TCBWCommon.getEncodeString(getQ_personalId()));
		if(!"".equals(getQ_userTel()))
			hql += " and userTel = " + Common.sqlChar(getQ_userTel());
		if(!"".equals(getQ_userJobName())){
			if("02".equals(getQ_department()))
				hql += " and userJob in (select cast(id as string) from Con1005Db where name like " + Common.sqlChar("%" + getQ_userJobName() + "%") + ")";
			if("03".equals(getQ_department()))
				hql += " and userJob in (select cast(id as string) from Con1009Db where medAgencyName like " + Common.sqlChar("%" + getQ_userJobName() + "%") + ")";
			if("04".equals(getQ_department()))
				hql += " and userJob in (select cast(id as string) from Con1003Db where unionName like " + Common.sqlChar("%" + getQ_userJobName() + "%") + ")";
		}
		
		System.out.println("[TCBW]-[CONEX0201F]-[QUERYALL] : " + hql + " order by userId ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by applyDate,userId ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				for(Object dtlObj : objList){
					CommonUser dtl = (CommonUser)dtlObj;
					
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = dtl.getCommonDepartment()!=null?Common.get(dtl.getCommonDepartment().getDepartment()):"";
					rowArray[2] = Common.get(dtl.getUserId());
					rowArray[3] = Common.get(dtl.getUserName());
					//rowArray[4] = Common.get(dtl.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(dtl.getPersonalId());
					rowArray[4] = Common.get(dtl.getUserTel());
					rowArray[5] = Common.get(dtl.getApplyDate());
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
