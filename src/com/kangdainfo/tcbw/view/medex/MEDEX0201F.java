package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDEX0201F extends SuperBean{

	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}

	private javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public void doSend() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getMedex1Dao().updateSendByMEDEX0201F(this);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med4001Db where status = '01' " +
					 " and  inOrOutcreator = " + Common.sqlChar(getUserID()) ;
				

		System.out.println("[TCBW]-[MEDEX0201F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				
				for(Object dtlObj : objList)
				{
					Med4001Db dtl = (Med4001Db)dtlObj;
					
					String[] rowArray = new String[8];
                    rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getApplNo());		
					rowArray[2] = Common.get(dtl.getNotifierRevDate());	
					
					if(!"".equals(Common.get(dtl.getMedPermit())))
						rowArray[3] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
						rowArray[3] ="";
					rowArray[4] = Common.get(dtl.getMedCname());
					rowArray[5] = Common.get(medMainCategoryMap.get(dtl.getMedMainCategoryCode()));
					rowArray[6] = Common.get(statusMap.get(dtl.getStatus()));
					arrList.add(rowArray);
				}
				statusMap.clear();
				objList.clear();
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
