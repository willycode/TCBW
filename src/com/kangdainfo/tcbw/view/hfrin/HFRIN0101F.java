package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0101F extends SuperBean{
	
	private String id;
	private String name;
	private String sex;
	private String communAddress;
	private String houseAddress;
	private String idNum;
	private String email;
	private String telArea;
	private String tel;
	private String mobile;
	private String faxArea;
	private String fax;
    private String committeeNum;	
	
	private String q_isQuery;
	private String q_id;
	private String q_name;
	private String q_sex;
	private String q_idNum;

	public String getCommitteeNum() {
		return checkGet(committeeNum);
	}

	public void setCommitteeNum(String committeeNum) {
		this.committeeNum = checkSet(committeeNum);
	}
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
	}

	public String getSex() {
		return checkGet(sex);
	}

	public void setSex(String sex) {
		this.sex = checkSet(sex);
	}

	public String getCommunAddress() {
		return checkGet(communAddress);
	}

	public void setCommunAddress(String communAddress) {
		this.communAddress = checkSet(communAddress);
	}

	public String getHouseAddress() {
		return checkGet(houseAddress);
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = checkSet(houseAddress);
	}

	public String getIdNum() {
		return checkGet(idNum);
	}

	public void setIdNum(String idNum) {
		this.idNum = checkSet(idNum);
	}

	public String getEmail() {
		return checkGet(email);
	}

	public void setEmail(String email) {
		this.email = checkSet(email);
	}

	public String getTelArea() {
		return checkGet(telArea);
	}

	public void setTelArea(String telArea) {
		this.telArea = checkSet(telArea);
	}

	public String getTel() {
		return checkGet(tel);
	}

	public void setTel(String tel) {
		this.tel = checkSet(tel);
	}

	public String getMobile() {
		return checkGet(mobile);
	}

	public void setMobile(String mobile) {
		this.mobile = checkSet(mobile);
	}

	public String getFaxArea() {
		return checkGet(faxArea);
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = checkSet(faxArea);
	}

	public String getFax() {
		return checkGet(fax);
	}

	public void setFax(String fax) {
		this.fax = checkSet(fax);
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

	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}

	public String getQ_sex() {
		return checkGet(q_sex);
	}

	public void setQ_sex(String qSex) {
		q_sex = checkSet(qSex);
	}

	public String getQ_idNum() {
		return checkGet(q_idNum);
	}

	public void setQ_idNum(String qIdNum) {
		q_idNum = checkSet(qIdNum);
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0101F obj = this;
		Hfr1001Db c = (Hfr1001Db)View.getObject("from Hfr1001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setCommitteeNum(c.getCommitteeNum());
			obj.setName(c.getName());
			obj.setSex(c.getSex());
			obj.setCommunAddress(c.getCommunAddress());
			obj.setHouseAddress(c.getHouseAddress());
			obj.setIdNum(TCBWCommon.getDecodeString(c.getIdNum()));
			obj.setEmail(c.getEmail());
			obj.setTel(c.getTel());
			obj.setMobile(c.getMobile());
			obj.setFax(c.getFax());
			obj.setTelArea(c.getTelArea());
			obj.setFaxArea(c.getFaxArea());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
			//System.out.println("OBJ: " + obj);
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr1001Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
		    if(!"".equals(getQ_sex()))
				hql += " and sex = " + Common.sqlChar(getQ_sex());
			if(!"".equals(getQ_idNum()))
				hql += " and idNum = " + Common.sqlChar(TCBWCommon.getEncodeString(getQ_idNum()));
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {				
					Hfr1001Db dtl = (Hfr1001Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getCommitteeNum());	
					rowArray[2] = Common.get(dtl.getName());						
					rowArray[3] = dtl.getSex().equals("0")?"女":dtl.getSex().equals("1")?"男":"";						
					rowArray[4] = Common.get(TCBWCommon.getDecodeString(dtl.getIdNum()));	
					rowArray[5] = Common.get(dtl.getEmail());
					arrList.add(rowArray);
					//System.out.println("NAME: " + getName());
				}
				objList.clear();
				
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Hfr0001Db where idNum = " + Common.sqlChar(TCBWCommon.getEncodeString(getIdNum()));
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該身份證已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Hfr1001Db c = (Hfr1001Db)View.getObject("from Hfr1001Db where committeeNum = " + Common.sqlChar(getCommitteeNum()));
		if(c != null){
			throw new Exception("該委員編號已存在，請重新輸入！");
		}
		Hfr1001Db obj = new Hfr1001Db();
		obj.setCommitteeNum(getCommitteeNum());
	    obj.setName(getName());
	    obj.setSex(getSex());
	    obj.setCommunAddress(getCommunAddress());
	    obj.setHouseAddress(getHouseAddress());
	    obj.setIdNum(TCBWCommon.getEncodeString(getIdNum()));
	    obj.setEmail(getEmail());
	    obj.setTel(getTel());
	    obj.setMobile(getMobile());
	    obj.setFax(getFax());
	    obj.setTelArea(getTelArea());
	    obj.setFaxArea(getFaxArea());
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
		checkSQLArray[0][0] = "select count(*) from Hfr0001Db where idNum = " + Common.sqlChar(TCBWCommon.getEncodeString(getIdNum())) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該身份證已存在，請重新輸入！";		
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Hfr1001Db obj = (Hfr1001Db)View.getObject(" from Hfr1001Db where id = " + Common.getLong(getId()));
		if(obj != null){	
			obj.setCommitteeNum(getCommitteeNum());
		    obj.setName(getName());
		    obj.setSex(getSex());
		    obj.setCommunAddress(getCommunAddress());
		    obj.setHouseAddress(getHouseAddress());
		    obj.setIdNum(TCBWCommon.getEncodeString(getIdNum()));
		    obj.setEmail(getEmail());
		    obj.setTel(getTel());
		    obj.setMobile(getMobile());
		    obj.setFax(getFax());
		    obj.setTelArea(getTelArea());
		    obj.setFaxArea(getFaxArea());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1001Db obj = (Hfr1001Db)View.getObject(" from Hfr1001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
	public String getComNum(){
		Integer num = (Integer)View.getObject(" select max(committeeNum)+1 from Hfr1001Db ");
		if("".equals(Common.get(num)))
			num = 1;
		return String.valueOf(num);
	}
}
