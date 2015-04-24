package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1005Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;

public class Con1005DbSearch extends SuperBean{
	
	private String drgId;
	private String q_facName;

	public String getDrgId() {return checkGet(drgId);}
	public void setDrgId(String s) {this.drgId = checkSet(s);}

	public String getQ_facName() {
		return checkGet(q_facName);
	}
	public void setQ_facName(String q_facName) {
		this.q_facName = checkSet(q_facName);
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
		String hql = " from Con1005Db where 1=1";
		
		if(!"".equals(getQ_facName()))
			hql += " and Name like " + Common.sqlChar("%" + getQ_facName() + "%");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		System.out.println("HQL: " + hql);
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {
					Con1005Db dtl = (Con1005Db)dtlObj;
					String[] rowArray = new String[3];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCompegno());	
					rowArray[2] = Common.get(dtl.getName());
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


