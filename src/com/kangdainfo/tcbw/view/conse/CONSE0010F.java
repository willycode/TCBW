package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1012Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0010F extends SuperBean{

	private String id;
	private String systemType;
	private String code;
	private String name;
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_code;
	private String q_name;

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

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
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

	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}
	
	public String getCode() {
		return checkGet(code);
	}

	public void setCode(String code) {
		this.code = checkSet(code);
	}

	public String getQ_code() {
		return checkGet(q_code);
	}

	public void setQ_code(String q_code) {
		this.q_code = checkSet(q_code);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONSE0010F obj = this;
		Con1012Db c = (Con1012Db)View.getObject("from Con1012Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setCode(c.getCode());
			obj.setName(c.getName());
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
		String hql = " from Con1012Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar(getQ_systemType());
			if(!"".equals(getQ_code()))
				hql += " and code = " + Common.sqlChar("%" + getQ_code() + "%");			
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
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
					Con1012Db dtl = (Con1012Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getSystemType()));
					rowArray[2] = Common.get(dtl.getCode());
					rowArray[3] = Common.get(dtl.getName());		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1012Db obj = new Con1012Db();		
		obj.setSystemType(getSystemType());
		obj.setCode(getCode());
		obj.setName(getName());
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
		Con1012Db obj = (Con1012Db)View.getObject(" from Con1012Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setSystemType(getSystemType());
			obj.setCode(getCode());
			obj.setName(getName());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1012Db obj = (Con1012Db)View.getObject(" from Con1012Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
