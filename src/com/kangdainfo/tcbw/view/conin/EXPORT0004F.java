package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Export0004Db;

public class EXPORT0004F extends SuperBean {

	private String id;
	private String seq;
	private String field;
	private String fieldname;
	private String tablename;
	private String codename;
	private String isStop;
	private String isMany;
	private String manyName;
	private String modifier;
	private String modifyDate;
	
	private String q_id;
	private String q_isQuery;
	private String q_field;
	private String q_fieldname;
	private String q_isStop;
	private String q_isMany;

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getSeq() {
		return checkGet(seq);
	}

	public void setSeq(String seq) {
		this.seq = checkSet(seq);
	}

	public String getField() {
		return checkGet(field);
	}

	public void setField(String field) {
		this.field = checkSet(field);
	}

	public String getFieldname() {
		return checkGet(fieldname);
	}

	public void setFieldname(String fieldname) {
		this.fieldname = checkSet(fieldname);
	}

	public String getTablename() {
		return checkGet(tablename);
	}

	public void setTablename(String tablename) {
		this.tablename = checkSet(tablename);
	}

	public String getCodename() {
		return checkGet(codename);
	}

	public void setCodename(String codename) {
		this.codename = checkSet(codename);
	}

	public String getIsStop() {
		return checkGet(isStop);
	}

	public void setIsStop(String isStop) {
		this.isStop = checkSet(isStop);
	}

	public String getIsMany() {
		return checkGet(isMany);
	}

	public void setIsMany(String isMany) {
		this.isMany = checkSet(isMany);
	}

	public String getManyName() {
		return checkGet(manyName);
	}

	public void setManyName(String manyName) {
		this.manyName = checkSet(manyName);
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

	public String getQ_field() {
		return checkGet(q_field);
	}

	public void setQ_field(String qField) {
		q_field = checkSet(qField);
	}

	public String getQ_fieldname() {
		return checkGet(q_fieldname);
	}

	public void setQ_fieldname(String qFieldname) {
		q_fieldname = checkSet(qFieldname);
	}

	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	public String getQ_isMany() {
		return checkGet(q_isMany);
	}

	public void setQ_isMany(String qIsMany) {
		q_isMany = checkSet(qIsMany);
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

	@Override
	public Object doQueryOne() throws Exception {
		EXPORT0004F obj = this;
		Export0004Db c = (Export0004Db)View.getObject("from Export0004Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSeq(Common.get(c.getSeq()));
			obj.setField(Common.get(c.getField()));
			obj.setFieldname(Common.get(c.getFieldname()));
			obj.setTablename(Common.get(c.getTablename()));
			obj.setCodename(Common.get(c.getCodename()));
			obj.setIsStop(Common.get(c.getIsStop()));
			obj.setIsMany(Common.get(c.getIsMany()));
			obj.setManyName(Common.get(c.getManyName()));
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
		hql.append("from Export0004Db where 1 = 1");
		if(!"".equals(getQ_id())){
			hql.append(" and id = " + Common.getLong(getQ_id()));
		}
		else{
			if(!"".equals(getQ_field()))
				hql.append(" and field like " + Common.sqlChar("%" + getQ_field() +"%"));
			if(!"".equals(getQ_fieldname()))
				hql.append(" and fieldname like " + Common.sqlChar("%" + getQ_fieldname() + "%"));
			if(!"".equals(getQ_isStop()))
				hql.append(" and isStop = " + Common.sqlChar(getQ_isStop()));
			if(!"".equals(getQ_isMany()))
				hql.append(" and isMany = " + Common.sqlChar(getQ_isMany()));
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql.toString()));
		if(getTotalRecord() > 0){
			if(getState().indexOf("query") < 0){
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			}
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql.toString());
			if(objList != null && objList.size() > 0){
				for(Object dtlObj : objList){
					Export0004Db dtl = (Export0004Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getSeq());
					rowArray[2] = Common.get(dtl.getField());
					rowArray[3] = Common.get(dtl.getFieldname());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}			
		return arrList;
	}

	public String[][] getInsertCheckSQL(){
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Export0004Db where field = " + Common.sqlChar(getField()) + " and tablename = " + Common.sqlChar(getTablename()) + " and codename = " + Common.sqlChar(getCodename());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "已有相同欄位名稱、資料表名稱、代碼名稱存在，請重新輸入!";
		return checkSQLArray;
	}
	
	@Override
	public void doCreate() throws Exception {
		Export0004Db obj = new Export0004Db();
		obj.setSeq(Integer.parseInt(seq));
		obj.setField(field);
		obj.setFieldname(fieldname);
		obj.setTablename(tablename);
		obj.setCodename(codename);
		obj.setIsStop(isStop);
		obj.setIsMany(isMany);
		obj.setManyName(manyName);
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
		checkSQLArray[0][0] = "select count(*) from Export0004Db where id != " + Common.getLong(getId()) + " and field = " + Common.sqlChar(getField()) + " and tablename = " + Common.sqlChar(getTablename()) + " and codename = " + Common.sqlChar(getCodename());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "已有相同欄位名稱、資料表名稱、代碼名稱存在，請重新輸入!";
		return checkSQLArray;
	}
	
	@Override
	public void doUpdate() throws Exception {
		Export0004Db obj = (Export0004Db)View.getObject("from Export0004Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setSeq(Integer.parseInt(seq));
			obj.setField(field);
			obj.setFieldname(fieldname);
			obj.setTablename(tablename);
			obj.setCodename(codename);
			obj.setIsStop(isStop);
			obj.setIsMany(isMany);
			obj.setManyName(manyName);		
			obj.setModifier(getEditID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}
	
	@Override
	public void doDelete() throws Exception {
		Export0004Db obj = (Export0004Db)View.getObject("from Export0004Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}
		else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
