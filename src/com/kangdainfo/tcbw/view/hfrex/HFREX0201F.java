package com.kangdainfo.tcbw.view.hfrex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;

public class HFREX0201F extends SuperBean {
	
	private String q_notifierType;						// FOR 登入者屬性
	private String q_notifierDept;						// FOR 登入者服務機構(暫不使用)
	private String q_notifierDeptID;					// FOR 登入者服務機構(103.07.05改成已ID去查詢)
	private String q_userJobModDate;					// FOR 登入者單位異動日期
	
	public String getQ_notifierType() {		return checkGet(q_notifierType);	}
	public void setQ_notifierType(String q_notifierType) {		this.q_notifierType = checkSet(q_notifierType);	}
	public String getQ_notifierDept() {		return checkGet(q_notifierDept);	}
	public void setQ_notifierDept(String q_notifierDept) {		this.q_notifierDept = checkSet(q_notifierDept);	}
	public String getQ_notifierDeptID() {		return checkGet(q_notifierDeptID);	}
	public void setQ_notifierDeptID(String q_notifierDeptID) {		this.q_notifierDeptID = checkSet(q_notifierDeptID);	}
	public String getQ_userJobModDate() {		return checkGet(q_userJobModDate);	}
	public void setQ_userJobModDate(String q_userJobModDate) {		this.q_userJobModDate = checkSet(q_userJobModDate);	}

	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}

	private javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public void doSend() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateSendApplNoByHFREX0201F(this);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Hfr4001Db where status = '01' ";
		
		// 登錄單位屬性為廠商時，需以機關單位去比對
		if("02".equals(getQ_notifierType())){
			hql += " and notifierDeptId = " + Common.sqlChar(getQ_notifierDeptID());
		}else{
			hql += " and (creator = " + Common.sqlChar(getUserID()) + " or caseOwner = " + Common.sqlChar(getUserID()) + ")"; 
		}
		System.out.println("[TCBW]-[HFREX0201F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0){
				java.util.Map<String, String> FCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("FCS", null);
				java.util.Map<String, String> HFRPKIDMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRPKID", null);
				
				for(Object dtlObj : objList){
					Hfr4001Db dtl = (Hfr4001Db)dtlObj;
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getOccurDate());
					rowArray[3] = "";
					rowArray[4] = "";
					if(dtl.getHfr4002Dbs()!=null && dtl.getHfr4002Dbs().size()>0){
						boolean flag = true;
						for(Object dObj : dtl.getHfr4002Dbs()){
							if(flag){
								Hfr4002Db d = (Hfr4002Db)dObj;
								rowArray[3] = ( Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))).equals("")?Common.get(d.getPermitKey()):Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))) ) + Common.get(d.getPermitNo());
								rowArray[4] = Common.get(d.getChProduct());
								flag = false;
							}else{
								break;
							}
						}
					}
					rowArray[5] = Common.get(FCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(FCSMap.get(Common.get(dtl.getStatus())));
					arrList.add(rowArray);
				}
				
				FCSMap.clear();
				HFRPKIDMap.clear();
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
