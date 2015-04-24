package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Hfr1002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0704F extends SuperBean{

	private String q_name;
	private String q_postLev;
	
	public String getQ_name() {		return checkGet(q_name);	}
	public void setQ_name(String q_name) {		this.q_name = checkSet(q_name);	}
	public String getQ_postLev() {		return checkGet(q_postLev);	}
	public void setQ_postLev(String q_postLev) {		this.q_postLev = checkSet(q_postLev);	}
	
	private String fieldName;
	private String fieldId;
	
	public String getFieldName() {		return checkGet(fieldName);	}
	public void setFieldName(String fieldName) {		this.fieldName = checkSet(fieldName);	}
	public String getFieldId() {	return checkGet(fieldId);	}
	public void setFieldId(String fieldId) {		this.fieldId = checkSet(fieldId);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Hfr1002Db where termSdate <= " + Common.sqlChar(Datetime.getYYYMMDD()) + 
					 " and termEdate >= " + Common.sqlChar(Datetime.getYYYMMDD());
		if(!"".equals(getQ_name()))
			hql += " and hfr1001Db.name = " + Common.sqlChar("%" + getQ_name() + "%");
		if(!"".equals(getQ_postLev()))
			hql += " and postLev = " + Common.sqlChar(getQ_postLev());
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by hfr1001Db.name, termSdate", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				java.util.Map<String, String> postLevMap = TCBWCommon.getCommonCodeMap("PL");
				java.util.Map<String, String> unionMap = TCBWCommon.getCommonCodeMap("UI");
				
				for(Object dtlObj : objList){
					Hfr1002Db dtl = (Hfr1002Db)dtlObj;
					
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getTermSdate()) + " ~ " + Common.get(dtl.getTermEdate());
					rowArray[2] = dtl.getHfr1001Db()!=null?Common.get(dtl.getHfr1001Db().getName()):"";
					rowArray[3] = Common.get(postLevMap.get(Common.get(dtl.getPostLev()))).equals("")?Common.get(postLevMap.get(Common.get(dtl.getPostLev()))):Common.get(dtl.getPostLev());
					rowArray[4] = Common.get(unionMap.get(Common.get(dtl.getUnionID()))).equals("")?Common.get(unionMap.get(Common.get(dtl.getUnionID()))):Common.get(dtl.getUnionID());
					arrList.add(rowArray);
				}
				
				postLevMap.clear();
				unionMap.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
