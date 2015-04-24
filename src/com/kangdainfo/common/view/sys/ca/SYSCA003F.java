package com.kangdainfo.common.view.sys.ca;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1016Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class SYSCA003F extends SuperBean{

	private String id;
	private String systemType;	//系統別
	private String codeKindId;	//代碼種類
	private String codeKindName;	//代碼名稱
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_codeKindName;

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

	public String getCodeKindId() {
		return checkGet(codeKindId);
	}

	public void setCodeKindId(String codeKindId) {
		this.codeKindId = checkSet(codeKindId);
	}

	public String getCodeKindName() {
		return checkGet(codeKindName);
	}

	public void setCodeKindName(String codeKindName) {
		this.codeKindName = checkSet(codeKindName);
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

	public String getQ_codeKindName() {
		return checkGet(q_codeKindName);
	}

	public void setQ_codeKindName(String qcodeKindName) {
		q_codeKindName = checkSet(qcodeKindName);
	}

	@Override
	public Object doQueryOne() throws Exception {
		SYSCA003F obj = this;
		CommonCodeKind c = (CommonCodeKind)View.getObject("from CommonCodeKind where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setCodeKindId(c.getCodeKindId());
			obj.setCodeKindName(c.getCodeKindName());
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from CommonCodeKind where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar(getQ_systemType());
			if(!"".equals(getQ_codeKindName()))
				hql += " and codeKindName like " + Common.sqlChar("%" + getQ_codeKindName() + "%");
		}
		System.out.println("HQL: " + hql);
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");

				for(Object dtlObj : objList) {	
					CommonCodeKind dtl = (CommonCodeKind)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getSystemType()));
					rowArray[2] = Common.get(dtl.getCodeKindId());
					rowArray[3] = Common.get(dtl.getCodeKindName());		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		CommonCodeKind obj = new CommonCodeKind();		
		obj.setSystemType(getSystemType());
		obj.setCodeKindId(getCodeKindId());
		obj.setCodeKindName(getCodeKindName());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		CommonCodeKind obj = (CommonCodeKind)View.getObject(" from CommonCodeKind where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setSystemType(getSystemType());
			obj.setCodeKindId(getCodeKindId());
			obj.setCodeKindName(getCodeKindName());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		CommonCodeKind obj = (CommonCodeKind)View.getObject(" from CommonCodeKind where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
