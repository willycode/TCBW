package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;

public class Hfr4001DbSearch extends SuperBean{

	private String applNo;
	private String hfr0001Id;
	
	public String getHfr0001Id() {
		return checkGet(hfr0001Id);
	}

	public void setHfr0001Id(String hfr0001Id) {
		this.hfr0001Id = checkSet(hfr0001Id);
	}

	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr4001Db where id = " + Common.getLong(getHfr0001Id());
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {
					Hfr4001Db dtl = (Hfr4001Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getHfrType());		
					rowArray[2] = Common.get(dtl.getApplNo());	
					rowArray[3] = "";			
					rowArray[4] = Common.formatYYYMMDD(Common.get(dtl.getCreateDate()),4);
					rowArray[5] = "明細";			
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{}

	@Override
	public void doDelete() throws Exception{}
	
	
}


