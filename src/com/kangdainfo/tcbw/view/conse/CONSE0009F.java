package com.kangdainfo.tcbw.view.conse;

import java.util.Arrays;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1005Db;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.model.bo.Con2002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0009F extends SuperBean{

	private String id;
	private String bnhi;
	private String medAgencyCode;
	private String medAgencyName;
	private String agencyAddress;
	private String areaTel;
	private String tel;
	private String engageKind;
    private String medAgencyKind;
    private String endDate;
	
	private String q_isQuery;
	private String q_id;
	private String q_bnhi;
	private String q_medAgencyCode;
	private String q_medAgencyName;

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getBnhi() {
		return checkGet(bnhi);
	}

	public void setBnhi(String bnhi) {
		this.bnhi = checkSet(bnhi);
	}

	public String getMedAgencyCode() {
		return checkGet(medAgencyCode);
	}

	public void setMedAgencyCode(String medAgencyCode) {
		this.medAgencyCode = checkSet(medAgencyCode);
	}

	public String getMedAgencyName() {
		return checkGet(medAgencyName);
	}

	public void setMedAgencyName(String medAgencyName) {
		this.medAgencyName = checkSet(medAgencyName);
	}

	public String getAgencyAddress() {
		return checkGet(agencyAddress);
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = checkSet(agencyAddress);
	}

	public String getAreaTel() {
		return checkGet(areaTel);
	}

	public void setAreaTel(String areaTel) {
		this.areaTel = checkSet(areaTel);
	}

	public String getTel() {
		return checkGet(tel);
	}

	public void setTel(String tel) {
		this.tel = checkSet(tel);
	}

	public String getEngageKind() {
		return checkGet(engageKind);
	}

	public void setEngageKind(String engageKind) {
		this.engageKind = checkSet(engageKind);
	}

	public String getMedAgencyKind() {
		return checkGet(medAgencyKind);
	}

	public void setMedAgencyKind(String medAgencyKind) {
		this.medAgencyKind = checkSet(medAgencyKind);
	}

	public String getEndDate() {
		return checkGet(endDate);
	}

	public void setEndDate(String endDate) {
		this.endDate = checkSet(endDate);
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
	
	public String getQ_bnhi() {
		return checkGet(q_bnhi);
	}

	public void setQ_bnhi(String qBnhi) {
		q_bnhi = checkSet(qBnhi);
	}

	public String getQ_medAgencyCode() {
		return checkGet(q_medAgencyCode);
	}

	public void setQ_medAgencyCode(String qMedAgencyCode) {
		q_medAgencyCode = checkSet(qMedAgencyCode);
	}

	public String getQ_medAgencyName() {
		return checkGet(q_medAgencyName);
	}

	public void setQ_medAgencyName(String qMedAgencyName) {
		q_medAgencyName = checkSet(qMedAgencyName);
	}


	@Override
	public Object doQueryOne() throws Exception {
		CONSE0009F obj = this;
		Con1009Db c = (Con1009Db)View.getObject("from Con1009Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setBnhi(c.getBnhi());
			obj.setMedAgencyCode(c.getMedAgencyCode());
			obj.setMedAgencyName(c.getMedAgencyName());
			obj.setAgencyAddress(c.getAgencyAddress());
			obj.setAreaTel(c.getAreaTel());
			obj.setTel(c.getTel());
			obj.setEngageKind(c.getEngageKind());
			obj.setMedAgencyKind(c.getMedAgencyKind());
			obj.setEndDate(c.getEndDate());
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
		String hql = " from Con1009Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_bnhi()))
				hql += " and bnhi = " + Common.sqlChar(getQ_bnhi());
			if(!"".equals(getQ_medAgencyCode()))
				hql += " and medAgencyCode = " + Common.sqlChar(getQ_medAgencyCode());
			if(!"".equals(getQ_medAgencyName()))
				hql += " and medAgencyName like " + Common.sqlChar("%" + getQ_medAgencyName() + "%");
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> bnhiMap = TCBWCommon.getCommonCodeMap("MEDDIV");
				java.util.Map<String, String> engkindMap = TCBWCommon.getCommonCodeMap("MEDENG");
				java.util.Map<String, String> medkindMap = TCBWCommon.getCommonCodeMap("MEDKIND");

				for(Object dtlObj : objList) {	
					Con1009Db dtl = (Con1009Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(bnhiMap.get(dtl.getBnhi()));
					rowArray[2] = Common.get(dtl.getMedAgencyCode());
					rowArray[3] = Common.get(dtl.getMedAgencyName());
					rowArray[4] = Common.get(dtl.getAreaTel()) + Common.get(dtl.getTel());		
					rowArray[5] = Common.get(engkindMap.get(dtl.getEngageKind()));	
					rowArray[6] = Common.get(medkindMap.get(dtl.getMedAgencyKind()));		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1009Db con1009 = (Con1009Db) View.getObject(" from Con1009Db where medAgencyCode="+Common.sqlChar(getMedAgencyCode()));
		if(con1009 != null){
			throw new Exception("已有此醫事機構代碼的資料，無法新增請查明 !");
		}
		else{	
			Con1009Db obj = new Con1009Db();		
			obj.setBnhi(getBnhi());		
			obj.setMedAgencyCode(getMedAgencyCode());		
			obj.setMedAgencyName(getMedAgencyName());		
			obj.setAgencyAddress(getAgencyAddress());		
			obj.setAreaTel(getAreaTel());		
			obj.setTel(getTel());		
			obj.setEngageKind(getEngageKind());		
			obj.setMedAgencyKind(getMedAgencyKind());		
			obj.setEndDate(getEndDate());	    
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
		
		Con1009Db old = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
		//oldData，要比較的舊資料
		String[] oldData = {old.getBnhi(),old.getMedAgencyCode(),old.getMedAgencyName(),old.getAgencyAddress(),
				old.getAreaTel(),old.getTel(),old.getEngageKind(),old.getMedAgencyKind(),old.getEndDate()};
		
		Con1009Db obj = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setBnhi(getBnhi());
			obj.setMedAgencyCode(getMedAgencyCode());
			obj.setMedAgencyName(getMedAgencyName());
			obj.setAgencyAddress(getAgencyAddress());
			obj.setAreaTel(getAreaTel());
			obj.setTel(getTel());
			obj.setEngageKind(getEngageKind());
			obj.setMedAgencyKind(getMedAgencyKind());
			obj.setEndDate(getEndDate());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
		//newData，要比較的新資料
		String[] newData = {obj.getBnhi(),obj.getMedAgencyCode(),obj.getMedAgencyName(),obj.getAgencyAddress(),
				obj.getAreaTel(),obj.getTel(),obj.getEngageKind(),obj.getMedAgencyKind(),obj.getEndDate()};
		
		//con2002db，資料有異動將舊資料寫入歷程
		Con2002Db con2002db = new Con2002Db();
		
		Con1009Db con1009db = new Con1009Db();
		con1009db.setId(obj.getId());
		con2002db.setCon1009Db(con1009db.getId());//取消與con1009DB的關聯，直接到con1009DB抓ID
		
		con2002db.setBnhi(oldData[0]);
		con2002db.setMedAgencyCode(oldData[1]);
		con2002db.setMedAgencyName(oldData[2]);
		con2002db.setAgencyAddress(oldData[3]);
		con2002db.setAreaTel(oldData[4]);
		con2002db.setTel(oldData[5]);
		con2002db.setEngageKind(oldData[6]);
		con2002db.setMedAgencyKind(oldData[7]);
		con2002db.setEndDate(oldData[8]);
		con2002db.setCreator(obj.getCreator());
		con2002db.setCreateDate(obj.getCreateDate());
		con2002db.setCreateTime(obj.getCreateTime());
		con2002db.setModifier(obj.getModifier());
		con2002db.setModifyDate(obj.getModifyDate());
		con2002db.setModifyTime(obj.getModifyDate());

		if(!Arrays.equals(oldData,newData)) {
			ServiceGetter.getInstance().getTcbwService().save(con2002db);
		}
		
	}

	@Override
	public void doDelete() throws Exception {
		Con1009Db obj = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
