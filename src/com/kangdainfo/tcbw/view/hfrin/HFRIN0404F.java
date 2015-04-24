package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0404F extends SuperBean {

	private String q_type;
	private String q_userId;
	private String q_userName;
	
	public String getQ_type() {		return checkGet(q_type);	}
	public void setQ_type(String q_type) {		this.q_type = checkSet(q_type);	}
	public String getQ_userId() {		return checkGet(q_userId);	}
	public void setQ_userId(String q_userId) {		this.q_userId = checkSet(q_userId);	}
	public String getQ_userName() {		return checkGet(q_userName);	}
	public void setQ_userName(String q_userName) {		this.q_userName = checkSet(q_userName);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from CommonUser where 1=1 ";
		if("E".equals(getQ_type())){													// 簡表
			hql += " and commonDepartment.shortCode = '01' ";
		}else if("C".equals(getQ_type())){												// 一般表	
			hql += " and commonDepartment.shortCode in ('02','03','04') ";
		}
		if(!"".equals(getQ_userId())){
			hql += " and userId = " + Common.sqlChar(getQ_userId());
		}
		if(!"".equals(getQ_userName())){
			hql += " and userName = " + Common.sqlChar("%" + getQ_userName() + "%");
		}
		System.out.println("[TCBW]-[HFRIN0404F]-[QUERYALL] : " + hql + " order by userId ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by userId ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				for(Object dtlObj : objList){
					CommonUser dtl = (CommonUser)dtlObj;
					
					String[] rowArray = new String[14];
					rowArray[0] = Common.get(dtl.getUserId());
					rowArray[1] = Common.get(dtl.getUserName());
					rowArray[2] = Common.get(dtl.getUserTelArea());
					rowArray[3] = Common.get(dtl.getUserTel());
					rowArray[4] = Common.get(dtl.getUserTelExt());
					rowArray[5] = Common.get(dtl.getUserEmail());
					rowArray[6] = Common.get(dtl.getCommonDepartment()!=null?dtl.getCommonDepartment().getShortCode():"");
					rowArray[7] = Common.get(dtl.getUserJob());
					
					rowArray[8] = "";
					if(dtl.getCommonDepartment() != null){
						if("01".equals(dtl.getCommonDepartment().getShortCode())){
							
						}else{
							if("02".equals(dtl.getCommonDepartment().getShortCode())){
								rowArray[8] = View.getLookupField("select name from Con1005Db where id = " + Common.getLong(dtl.getUserJob()));
							}else if("04".equals(dtl.getCommonDepartment().getShortCode())){
								rowArray[8] = View.getLookupField("select unionName from Con1003Db where id = " + Common.getLong(dtl.getUserJob()));
							}else if("03".equals(dtl.getCommonDepartment().getShortCode())){
								rowArray[8] = View.getLookupField("select medAgencyName from Con1009Db where id = " + Common.getLong(dtl.getUserJob()));
							}
						}
					}
					rowArray[9] = Common.get(dtl.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(dtl.getPersonalId()));
					rowArray[10] = Common.get(dtl.getJobTitle());
					rowArray[11] = Common.get(dtl.getUserCounty());
					rowArray[12] = Common.get(dtl.getUserZipCode());
					rowArray[13] = Common.get(dtl.getUserAddr());
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
