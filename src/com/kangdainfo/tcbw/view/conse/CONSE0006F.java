package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0006F extends SuperBean{

	private String id;
	private String unionName;
	private String county;
	private String zipcode;
	private String address;
	private String tel;
	private String fax;
    private String telarea;
    private String faxarea;
	
	private String q_isQuery;
	private String q_id;
	private String q_unionName;
	private String q_county;
	private String q_zipcode;
	private String q_address;	

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getUnionName() {
		return checkGet(unionName);
	}

	public void setUnionName(String unionName) {
		this.unionName = checkSet(unionName);
	}

	public String getCounty() {
		return checkGet(county);
	}

	public void setCounty(String county) {
		this.county = checkSet(county);
	}

	public String getZipcode() {
		return checkGet(zipcode);
	}

	public void setZipcode(String zipcode) {
		this.zipcode = checkSet(zipcode);
	}

	public String getAddress() {
		return checkGet(address);
	}

	public void setAddress(String address) {
		this.address = checkSet(address);
	}

	public String getTel() {
		return checkGet(tel);
	}

	public void setTel(String tel) {
		this.tel = checkSet(tel);
	}

	public String getFax() {
		return checkGet(fax);
	}

	public void setFax(String fax) {
		this.fax = checkSet(fax);
	}
	
	public String getTelarea() {
		return checkGet(telarea);
	}

	public void setTelarea(String telarea) {
		this.telarea = checkSet(telarea);
	}

	public String getFaxarea() {
		return checkGet(faxarea);
	}

	public void setFaxarea(String faxarea) {
		this.faxarea = checkSet(faxarea);
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
	
	public String getQ_unionName() {
		return checkGet(q_unionName);
	}

	public void setQ_unionName(String qUnionName) {
		q_unionName = checkSet(qUnionName);
	}

	public String getQ_county() {
		return checkGet(q_county);
	}

	public void setQ_county(String qCounty) {
		q_county = checkSet(qCounty);
	}

	public String getQ_zipcode() {
		return checkGet(q_zipcode);
	}

	public void setQ_zipcode(String qZipcode) {
		q_zipcode = checkSet(qZipcode);
	}

	public String getQ_address() {
		return checkGet(q_address);
	}

	public void setQ_address(String qAddress) {
		q_address = checkSet(qAddress);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONSE0006F obj = this;
		Con1003Db c = (Con1003Db)View.getObject("from Con1003Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setCounty(c.getCounty());
			obj.setUnionName(c.getUnionName());
			obj.setZipcode(c.getZipcode());
			obj.setAddress(c.getAddress());
			obj.setTel(c.getTel());
			obj.setFax(c.getFax());
			obj.setTelarea(c.getTelarea());
			obj.setFaxarea(c.getFaxarea());
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
		String hql = " from Con1003Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_unionName()))
				hql += " and unionName like " + Common.sqlChar("%" + getQ_unionName() + "%");
			if(!"".equals(getQ_county()))
				hql += " and county = " + Common.sqlChar(getQ_county());
			if(!"".equals(getQ_zipcode()))
				hql += " and zipcode = " + Common.sqlChar(getQ_zipcode());
			if(!"".equals(getQ_address()))
				hql += " and address like " + Common.sqlChar("%" + getQ_address() + "%");
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> ctyMap = TCBWCommon.getCommonCodeMap("CTY");
				java.util.Map<String, String> zipMap = View.getZipCode();
				for(Object dtlObj : objList) {	
					Con1003Db dtl = (Con1003Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getUnionName());
					rowArray[2] = Common.get(ctyMap.get(dtl.getCounty())) + Common.get(zipMap.get(dtl.getZipcode())) + Common.get(dtl.getAddress());
					rowArray[3] = Common.get(dtl.getTelarea()) + Common.get(dtl.getTel());		
					rowArray[4] = Common.get(dtl.getFaxarea()) + Common.get(dtl.getFax());		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1003Db con1003 = (Con1003Db) View.getObject(" from Con1003Db where unionName="+Common.sqlChar(getUnionName()));
		if(con1003 != null){
			throw new Exception("已有此單位名稱資料，無法新增請查明 !");
		}else{		
			Con1003Db obj = new Con1003Db();		
			obj.setUnionName(getUnionName());		
			obj.setCounty(getCounty());		
			obj.setUnionName(getUnionName());		
			obj.setZipcode(getZipcode());		
			obj.setAddress(getAddress());		
			obj.setTel(getTel());		
			obj.setFax(getFax());		
			obj.setTelarea(getTelarea());		
			obj.setFaxarea(getFaxarea());	   
			obj.setCreator(getEditID());	   
			obj.setCreateDate(getEditDate());	    
			obj.setCreateTime(getEditTime());	   
			obj.setModifier(getEditID());	   
			obj.setModifyDate(getEditDate());	    
			obj.setModifyTime(getEditTime());		
			ServiceGetter.getInstance().getTcbwService().save(obj);		
			setId(Common.get(obj.getId()));			
		}
	}

	@Override
	public void doUpdate() throws Exception {
		Con1003Db obj = (Con1003Db)View.getObject(" from Con1003Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setUnionName(getUnionName());
			obj.setCounty(getCounty());
			obj.setUnionName(getUnionName());
			obj.setZipcode(getZipcode());
			obj.setAddress(getAddress());
			obj.setTel(getTel());
			obj.setFax(getFax());
			obj.setTelarea(getTelarea());
			obj.setFaxarea(getFaxarea());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1003Db obj = (Con1003Db)View.getObject(" from Con1003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
