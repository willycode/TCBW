package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0003F extends SuperBean{
	
	private String id;
	private String city;
	private String zipcode;
	private String zipname;
	private String isactive;

	public String getId() {	return checkGet(id);	}
	public void setId(String id) {	this.id = checkSet(id);	}
	public String getCity() {
		return checkGet(city);
	}
	public void setCity(String city) {
		this.city = checkSet(city);
	}
	public String getZipcode() {	return checkGet(zipcode);	}
	public void setZipcode(String zipcode) {	this.zipcode = checkSet(zipcode);	}
	public String getZipname() {
		return checkGet(zipname);
	}
	public void setZipname(String zipname) {
		this.zipname = checkSet(zipname);
	}
	public String getIsactive() {	return checkGet(isactive);	}
	public void setIsactive(String isactive) {	this.isactive = checkSet(isactive);	}
	
	private String q_isQuery;
	private String q_id;
	private String q_city;
	private String q_zipcode;
	private String q_zipname;
	private String q_isactive;
	
	public String getQ_isQuery() {		return checkGet(q_isQuery);	}
	public void setQ_isQuery(String q_isQuery) {		this.q_isQuery = checkSet(q_isQuery);	}
	public String getQ_id() {	return checkGet(q_id);	}
	public void setQ_id(String q_id) {	this.q_id = checkSet(q_id);	}
	public String getQ_city() {
		return checkGet(q_city);
	}
	public void setQ_city(String qCity) {
		q_city = checkSet(qCity);
	}
	public String getQ_zipcode() {	return checkGet(q_zipcode);	}
	public void setQ_zipcode(String q_zipcode) {	this.q_zipcode = checkSet(q_zipcode);	}
	public String getQ_zipname() {
		return checkGet(q_zipname);
	}
	public void setQ_zipname(String qZipname) {
		q_zipname = checkSet(qZipname);
	}
	public String getQ_isactive() {	return checkGet(q_isactive);	}
	public void setQ_isactive(String q_isactive) {	this.q_isactive = checkSet(q_isactive);	}
	
	@Override
	public Object doQueryOne() throws Exception{
		CONSE0003F obj = this;
		Con1002Db c = (Con1002Db)View.getObject(" from Con1002Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setCity(c.getCity());
			obj.setZipcode(c.getZipcode());
			obj.setZipname(c.getZipname());
			obj.setIsactive(c.getIsStop());
		}
		return obj;
	}
	
	@Override
	public Object doQueryAll() throws Exception{
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		String hql = " from Con1002Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_city()))
				hql += " and city = " + Common.sqlChar(getQ_city());
			if(!"".equals(getQ_zipcode()))
				hql += " and zipcode like " + Common.sqlChar("%" + getQ_zipcode() + "%");
			if(!"".equals(getQ_zipname()))
				hql += " and zipname like " + Common.sqlChar("%" + getQ_zipname() + "%");
			if(!"".equals(getQ_isactive()))
				hql += " and isStop like " + Common.sqlChar("%" + getQ_isactive() + "%");			
		}

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id ", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> ctyMap = TCBWCommon.getCommonCodeMap("CTY");
				for(Object dtlObj : objList) {	
					Con1002Db dtl = (Con1002Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(ctyMap.get(dtl.getCity()));
					rowArray[2] = Common.get(dtl.getZipname());
					rowArray[3] = Common.get(dtl.getZipcode());
					rowArray[4] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Con1002Db where zipcode = " + Common.sqlChar(getZipcode());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "郵遞區號重複，請重新輸入！";
		return checkSQLArray;
	}
	
	@Override
	public void doCreate() throws Exception{
		Con1002Db obj = new Con1002Db();
		obj.setCity(getCity());
		obj.setZipcode(getZipcode());
		obj.setZipname(getZipname());
		obj.setIsStop(getIsactive());
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));	
	}
	
	protected String[][] getUpdateCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];		
		checkSQLArray[0][0] = "select count(*) from Con1002Db where zipcode = " + Common.sqlChar(getZipcode()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "郵遞區號重複，請重新輸入！";		
		return checkSQLArray;		
	}
	
	@Override
	public void doUpdate() throws Exception {
		Con1002Db obj = (Con1002Db)View.getObject(" from Con1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setCity(getCity());
			obj.setZipcode(getZipcode());
			obj.setZipname(getZipname());
			obj.setIsStop(getIsactive());
			obj.setModifier(getEditID());
			obj.setModifyDate(getEditDate());
			obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}
	
	@Override
	public void doDelete() throws Exception {
		Con1002Db obj = (Con1002Db)View.getObject(" from Con1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，重新操作 !");
		}
	}
}
