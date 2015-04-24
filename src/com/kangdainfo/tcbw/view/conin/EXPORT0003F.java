package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Export0003Db;

public class EXPORT0003F extends SuperBean {

	private String id;
	private String code;
	private String name;
	private String modifier;
	private String modifyDate;
	private String modifyTime;
	
	private String q_id;
	private String q_isQuery;
	private String q_code;
	private String q_name;		
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getCode() {
		return checkGet(code);
	}

	public void setCode(String code) {
		this.code = checkSet(code);
	}

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
	}	

	public String getModifier() {
		return checkGet(modifier);
	}

	public void setModifier(String modifier) {
		this.modifier = checkSet(modifier);
	}

	public String getModifyDate() {
		return checkGet(modifyDate);
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = checkSet(modifyDate);
	}

	public String getModifyTime() {
		return checkGet(modifyTime);
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = checkSet(modifyTime);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_code() {
		return checkGet(q_code);
	}

	public void setQ_code(String qCode) {
		q_code = checkSet(qCode);
	}

	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}

	@Override
	public Object doQueryOne() throws Exception {
		EXPORT0003F obj = this;
		Export0003Db c = (Export0003Db)View.getObject("from Export0003Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setCode(Common.get(c.getCode()));
			obj.setName(Common.get(c.getName()));
			obj.setModifier(Common.get(c.getModifier()));
			obj.setModifyDate(Common.get(c.getModifyDate()));
		}
		return obj;
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		if(!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		StringBuffer hql = new StringBuffer();
		hql.append("from Export0003Db where 1 = 1");
		if(!"".equals(getQ_id())){
			hql.append(" and id = " + Common.getLong(getQ_id()));
		}
		else{
			if(!"".equals(getQ_code()))
				hql.append(" and code = " + Common.sqlChar(getQ_code()));
			if(!"".equals(getQ_name()))
				hql.append(" and name like" + Common.sqlChar("%" + getQ_name() + "%"));
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql.toString()));
		if(getTotalRecord() > 0){
			if(getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql.toString() + "order by id",this.getRecordStart(),this.getPageSize());
			if(objList != null && objList.size() > 0){
				for(Object dtlObj : objList){
					Export0003Db dtl = (Export0003Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getCode());
					rowArray[2] = Common.get(dtl.getName());
					rowArray[3] = Common.get(dtl.getModifier());
					rowArray[4] = Common.get(dtl.getModifyDate());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}		
		return arrList;
	}
	
	public String[][] getInsertCheckSQL(){
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Export0003Db where code = " + Common.sqlChar(getCode()); 
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該代號已存在，請重新輸入!";
		return checkSQLArray;
	}
	
	@Override
	public void doCreate() throws Exception {
		Export0003Db obj = new Export0003Db();
		obj.setCode(code);
		obj.setName(name);
		obj.setCreator(getEditID());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());		
		obj.setModifier(getEditID());
		obj.setModifyDate(Datetime.getYYYMMDD());
		obj.setModifyTime(Datetime.getHHMMSS());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	public String[][] getUpdateCheckSQL(){
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Export0003Db where id != " + Common.getLong(getId()) + " and code = " + Common.sqlChar(getCode());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該代號已存在，請重新輸入!";
		return checkSQLArray;
	}
	
	@Override
	public void doUpdate() throws Exception {
		Export0003Db obj = (Export0003Db)View.getObject("from Export0003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setCode(code);
			obj.setName(name);
			obj.setModifier(getEditID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Export0003Db obj = (Export0003Db)View.getObject("from Export0003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}
		else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
