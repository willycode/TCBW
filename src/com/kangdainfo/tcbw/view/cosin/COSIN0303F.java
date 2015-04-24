package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1005Db;

public class COSIN0303F extends SuperBean {

	private String q_name;
	
	public String getQ_name() {		return checkGet(q_name);	}
	public void setQ_name(String q_name) {		this.q_name = checkSet(q_name);	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1005Db where 1 = 1 ";
		if(!"".equals(getQ_name())){
			hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
		}
		System.out.println("[TCBW]-[COSIN0303F]-[QUERYALL] : " + hql + " order by county ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by county ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				for(Object dtlObj : objList){
					Con1005Db dtl = (Con1005Db)dtlObj;
					
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getCompegno());
					rowArray[1] = Common.get(dtl.getName());
					
					rowArray[2] = Common.get(dtl.getCounty());
					rowArray[3] = Common.get(dtl.getZipcode());
					rowArray[4] = Common.get(dtl.getAddress());
					rowArray[5] = Common.get(dtl.getTelarea());
					rowArray[6] = Common.get(dtl.getTel());
					arrList.add(rowArray);
				}
				
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
