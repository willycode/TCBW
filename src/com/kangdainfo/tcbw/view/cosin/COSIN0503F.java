package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSIN0503F extends SuperBean {
	
	private String field1;
	
	public String getField1() {		return checkGet(field1);	}
	public void setField1(String field1) {		this.field1 = checkSet(field1);	}

	private String q_unionName;
	private String q_county;
	private String q_zipcode;
	private String q_address;
	
	public String getQ_unionName() {		return checkGet(q_unionName);	}
	public void setQ_unionName(String q_unionName) {		this.q_unionName = checkSet(q_unionName);	}
	public String getQ_county() {		return checkGet(q_county);	}
	public void setQ_county(String q_county) {		this.q_county = checkSet(q_county);	}
	public String getQ_zipcode() {		return checkGet(q_zipcode);	}
	public void setQ_zipcode(String q_zipcode) {		this.q_zipcode = checkSet(q_zipcode);	}
	public String getQ_address() {		return checkGet(q_address);	}
	public void setQ_address(String q_address) {		this.q_address = checkSet(q_address);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Con1003Db where 1 = 1 ";
		if(!"".equals(getQ_unionName()))
			hql += " and unionName like " + Common.sqlChar("%" + getQ_unionName() + "%");
		if(!"".equals(getQ_county()))
			hql += " and county = " + Common.sqlChar(getQ_county());
		if(!"".equals(getQ_zipcode()))
			hql += " and zipcode = " + Common.sqlChar(getQ_zipcode());
		if(!"".equals(getQ_address()))
			hql += " and address like " + Common.sqlChar("%" + getQ_address() + "%");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
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
				ctyMap.clear();
				zipMap.clear();
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
