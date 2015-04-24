package com.kangdainfo.tcbw.view.drgex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGEX0105F extends SuperBean{

	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}

	private javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public void doSend() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().updateSendApplNoByDRGEX0105F(this);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg5001Db a where a.status = '01' ";
		
		hql += " and ( a.creator = " + Common.sqlChar(getUserID())+" or a.notifierUserID = "+Common.sqlChar(getUserID());
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();		
		if("02".equals(c.getCommonDepartment().getDepartmentCode())){
			hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";
		}else if("03".equals(c.getCommonDepartment().getDepartmentCode())){			
			if(!"".equals(Common.get(c.getUserJobModDate()))){
				hql += " or ( a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" and a.notifierRepDate >"+Common.sqlChar(c.getUserJobModDate())+" ))";
			}else{
				hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";
			}			
		}else if("04".equals(c.getCommonDepartment().getDepartmentCode())){
			hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";
		}else{
			hql += " )";
		}
		c = null;
	    
		//若有相同案號，僅顯示最新一筆
		hql += " and ( a.id in ( select max(id) from Drg5001Db where drg0001Id is not null  group by drg0001Id )"+
               "  or a.id in ( select id from Drg5001Db where (applNo is null or applNo = '') and drg0001Id is null ) )";
		
		System.out.println("[TCBW]-[DRGEX0105F]-[QUERYALL] : " + hql + " order by a.id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by a.id DESC", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				
				for(Object dtlObj : objList){
					Drg5001Db dtl = (Drg5001Db)dtlObj;
					
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRepDate());
					rowArray[3] = Common.get(dtl.getPermitNo());
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(statusMap.get(dtl.getStatus()));
					arrList.add(rowArray);
				}
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
