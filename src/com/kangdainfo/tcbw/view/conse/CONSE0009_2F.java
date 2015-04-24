package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.model.bo.Con2002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0009_2F extends CONSE0009F{

	private String detailid;

	public String getDetailid() {
		return checkGet(detailid);
	}

	public void setDetailid(String detailid) {
		this.detailid = checkSet(detailid);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONSE0009_2F obj = this;
		Con2002Db c = (Con2002Db)View.getObject("from Con2002Db where id = " + Common.getLong(getDetailid()));		
		if(c != null){
			obj.setBnhi(c.getBnhi());
			obj.setDetailid(Common.get(c.getId()));
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
		String hql = " from Con2002Db where 1 = 1 and con1009id = " + getId();
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> bnhiMap = TCBWCommon.getCommonCodeMap("MEDDIV");
				java.util.Map<String, String> engkindMap = TCBWCommon.getCommonCodeMap("MEDENG");
				java.util.Map<String, String> medkindMap = TCBWCommon.getCommonCodeMap("MEDKIND");

				for(Object dtlObj : objList) {	
					Con2002Db dtl = (Con2002Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getModifyDate());
					rowArray[2] = Common.get(bnhiMap.get(dtl.getBnhi()));
					rowArray[3] = Common.get(dtl.getMedAgencyCode());
					rowArray[4] = Common.get(dtl.getMedAgencyName());
					rowArray[5] = Common.get(dtl.getAreaTel()) + Common.get(dtl.getTel());		
					rowArray[6] = Common.get(engkindMap.get(dtl.getEngageKind()));	
					rowArray[7] = Common.get(medkindMap.get(dtl.getMedAgencyKind()));		
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {

	}

	@Override
	public void doUpdate() throws Exception {

	}

	@Override
	public void doDelete() throws Exception {

	}

}
