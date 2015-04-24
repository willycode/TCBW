package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1007Db;
import com.kangdainfo.tcbw.model.bo.Con1008Db;

public class CONSE0008F extends SuperBean {

	private String id;
	private String con1007_id;
	private String fieldType;
	private String tabId;
	private String name;
	private String chName;
	private String isRequired;
	private String isDate;
	private String isNum;
	private String isLength;
	private String isComplete;
	private String isMlms;
	
	private String q_isQuery;
	private String q_id;
	private String q_con1007_id;
	private String q_name;
	private String q_chName;
	private String q_isRequired;
	private String q_isComplete;
	private String q_isMlms;
	private String q_tabId;

	
	@Override
	public Object doQueryOne() throws Exception 
	{
		CONSE0008F obj = this;
		Con1008Db c = (Con1008Db)View.getObject("from Con1008Db where id = " + Common.getLong(getId()));		
		if(c != null)
		{
			obj.setId(Common.get(c.getId()));
			obj.setCon1007_id(Common.get(c.getCon1007Db().getId()));
			obj.setFieldType(c.getFieldType());
			obj.setTabId(c.getTabId());
			obj.setName(c.getName());
			obj.setChName(c.getChName());
			obj.setIsRequired(c.getIsRequired());
			obj.setIsDate(c.getIsDate());
			obj.setIsNum(c.getIsNum());
			obj.setIsLength(c.getIsLength());
			obj.setIsComplete(c.getIsComplete());
			obj.setIsMlms(c.getIsMlms());
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
		String hql = " from Con1008Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_con1007_id()))
				hql += " and con1007Db.id = " + Common.getLong(getQ_con1007_id());
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
			if(!"".equals(getQ_chName()))
				hql += " and chName like " + Common.sqlChar("%" + getQ_chName() + "%");
			if(!"".equals(getQ_isRequired()))
				hql += " and isRequired = " + Common.sqlChar(getQ_isRequired());
			if(!"".equals(getQ_isComplete()))
				hql += " and isComplete = " + Common.sqlChar(getQ_isComplete());
			if(!"".equals(getQ_isMlms()))
				hql += " and isMlms = " + Common.sqlChar(getQ_isMlms());
			if(!"".equals(getQ_tabId()))
				hql += " and tabId = " + Common.sqlChar(getQ_tabId());
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {	
					Con1008Db dtl = (Con1008Db)dtlObj;
					String[] rowArray = new String[10];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCon1007Db().getFormdName());
					rowArray[2] = Common.get(dtl.getName());
					rowArray[3] = Common.get(dtl.getChName());		
					rowArray[4] = Common.get(dtl.getIsRequired()).equals("S")?"是":Common.get(dtl.getIsRequired()).equals("N")?"否":"";
					rowArray[5] = Common.get(dtl.getIsDate()).equals("Y")?"是":Common.get(dtl.getIsDate()).equals("N")?"否":"";
					rowArray[6] = Common.get(dtl.getIsNum()).equals("Y")?"是":Common.get(dtl.getIsNum()).equals("N")?"否":"";
					rowArray[7] = Common.get(dtl.getIsComplete()).equals("Y")?"是":Common.get(dtl.getIsComplete()).equals("N")?"否":"";
					rowArray[8] = Common.get(dtl.getIsMlms()).equals("Y")?"是":Common.get(dtl.getIsMlms()).equals("N")?"否":"";
					rowArray[9] = Common.get(dtl.getIsLength());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1007Db con1007id = (Con1007Db)View.getObject("from Con1007Db where id = " + Common.getLong(getCon1007_id()));
		Con1008Db obj = new Con1008Db();
		obj.setCon1007Db(con1007id);
		obj.setFieldType(getFieldType());
		obj.setTabId(getTabId());
		obj.setName(getName());
		obj.setChName(getChName());
		obj.setIsRequired(getIsRequired());
		obj.setIsDate(getIsDate());
		obj.setIsNum(getIsNum());
		obj.setIsLength(getIsLength());
		obj.setIsComplete(getIsComplete());
		obj.setIsMlms(getIsMlms());
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
		Con1007Db con1007id = (Con1007Db)View.getObject("from Con1007Db where id = " + Common.getLong(getCon1007_id()));
		Con1008Db obj = (Con1008Db)View.getObject(" from Con1008Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setCon1007Db(con1007id);
			obj.setFieldType(getFieldType());
			obj.setTabId(getTabId());
			obj.setName(getName());
			obj.setChName(getChName());
			obj.setIsRequired(getIsRequired());
			obj.setIsDate(getIsDate());
			obj.setIsNum(getIsNum());
			obj.setIsLength(getIsLength());
			obj.setIsComplete(getIsComplete());
			obj.setIsMlms(getIsMlms());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1008Db obj = (Con1008Db)View.getObject(" from Con1008Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getFieldType() {
		return checkGet(fieldType);
	}

	public void setFieldType(String fieldType) {
		this.fieldType = checkSet(fieldType);
	}

	public String getCon1007_id() {
		return checkGet(con1007_id);
	}

	public void setCon1007_id(String con1007Id) {
		con1007_id = checkSet(con1007Id);
	}

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
	}

	public String getChName() {
		return checkGet(chName);
	}

	public void setChName(String chName) {
		this.chName = checkSet(chName);
	}

	public String getIsRequired() {
		return checkGet(isRequired);
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = checkSet(isRequired);
	}

	public String getIsDate() {
		return checkGet(isDate);
	}

	public void setIsDate(String isDate) {
		this.isDate = checkSet(isDate);
	}

	public String getIsNum() {
		return checkGet(isNum);
	}

	public void setIsNum(String isNum) {
		this.isNum = checkSet(isNum);
	}

	public String getIsLength() {
		return checkGet(isLength);
	}

	public void setIsLength(String isLength) {
		this.isLength = checkSet(isLength);
	}

	public String getIsComplete() {
		return checkGet(isComplete);
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = checkSet(isComplete);
	}

	public String getIsMlms() {
		return checkGet(isMlms);
	}

	public void setIsMlms(String isMlms) {
		this.isMlms = checkSet(isMlms);
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
	
	public String getQ_con1007_id() {
		return checkGet(q_con1007_id);
	}

	public void setQ_con1007_id(String qCon1007Id) {
		q_con1007_id = checkSet(qCon1007Id);
	}

	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}

	public String getQ_chName() {
		return checkGet(q_chName);
	}

	public void setQ_chName(String qChName) {
		q_chName = checkSet(qChName);
	}

	public String getQ_isRequired() {
		return checkGet(q_isRequired);
	}

	public void setQ_isRequired(String qIsRequired) {
		q_isRequired = checkSet(qIsRequired);
	}

	public String getQ_isComplete() {
		return checkGet(q_isComplete);
	}

	public void setQ_isComplete(String qIsComplete) {
		q_isComplete = checkSet(qIsComplete);
	}

	public String getQ_isMlms() {
		return checkGet(q_isMlms);
	}

	public void setQ_isMlms(String q_isMlms) {
		this.q_isMlms = checkSet(q_isMlms);
	}

	public String getTabId() {
		return checkGet(tabId);
	}

	public void setTabId(String tabId) {
		this.tabId = checkSet(tabId);
	}

	public String getQ_tabId() {
		return checkGet(q_tabId);
	}

	public void setQ_tabId(String q_tabId) {
		this.q_tabId = checkSet(q_tabId);
	}
	
	


	
}
