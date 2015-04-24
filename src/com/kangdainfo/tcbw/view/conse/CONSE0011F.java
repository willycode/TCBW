package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1016Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0011F extends SuperBean{

	private String id;
	private String systemType;	//系統別
	private String formType;	//聲明類別
	private String formName;	//表單聲明名稱
	private String formDesc = "";	//聲明內容
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_formName;

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

	public String getFormName() {
		return checkGet(formName);
	}

	public void setFormName(String formName) {
		this.formName = checkSet(formName);
	}

	public String getFormType() {
		return checkGet(formType);
	}

	public void setFormType(String formType) {
		this.formType = checkSet(formType);
	}
	
	public String getFormDesc() {
		return get(formDesc);
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
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

	public String getQ_formName() {
		return checkGet(q_formName);
	}

	public void setQ_formName(String qFormName) {
		q_formName = checkSet(qFormName);
	}


	@Override
	public Object doQueryOne() throws Exception {
		CONSE0011F obj = this;
		Con1016Db c = (Con1016Db)View.getObject("from Con1016Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setFormType(c.getFormType());
			obj.setFormName(c.getFormName());
			obj.setFormDesc(c.getFormDesc());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1016Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar(getQ_systemType());		
			if(!"".equals(getQ_formName()))
				hql += " and formName like " + Common.sqlChar("%" + getQ_formName() + "%");
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");

				for(Object dtlObj : objList) {	
					Con1016Db dtl = (Con1016Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getSystemType()));
					rowArray[2] = Common.get(dtl.getFormType());
					rowArray[3] = Common.get(dtl.getFormName());		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1016Db obj = new Con1016Db();		
		obj.setSystemType(getSystemType());
		obj.setFormType(getFormType());
		obj.setFormName(getFormName());
		obj.setFormDesc(getFormDesc());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Con1016Db obj = (Con1016Db)View.getObject(" from Con1016Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setSystemType(getSystemType());
			obj.setFormType(getFormType());
			obj.setFormName(getFormName());
			obj.setFormDesc(getFormDesc());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1016Db obj = (Con1016Db)View.getObject(" from Con1016Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
