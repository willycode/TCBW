package com.kangdainfo.tcbw.view.cosex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;

public class COSEX0201F extends SuperBean {

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
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateSendByCOSEX0201F(this);
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos4001Db where status = '01' ";
		if("02".equals(getQ_notifierType())){
			hql += " and notifierDeptId = " + Common.sqlChar(getQ_notifierDeptID());
		}else{
			hql += " and (creator = " + Common.sqlChar(getUserID()) + " or caseOwner = " + Common.sqlChar(getUserID()) + ")"; 
		}
		System.out.println("[TCBW]-[COSEX0201F]-[QUERYALL] : " + hql + " order by id ");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> CCTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCT", null);
				java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
				java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
				java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
				java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>(); 
				java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
				if(cos1001DbList!=null && cos1001DbList.size()>0){
					for(Cos1001Db cos1001Db : cos1001DbList){
						subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
					}
					cos1001DbList.clear();
				}
				
				for(Object dtlObj : objList) {				
					Cos4001Db dtl = (Cos4001Db)dtlObj;
					
					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRepDate());
					rowArray[3] = Common.get(CCTMap.get(Common.get(dtl.getCosType()))).equals("")?Common.get("不良品與不良反應"):CCTMap.get(Common.get(dtl.getCosType()));
					rowArray[4] = "";
					if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
						rowArray[4] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
									  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
					}
					rowArray[5] = Common.get(dtl.getChProduct());
					rowArray[6] = Common.get(dtl.getEnProduct());
					rowArray[7] = Common.get(dtl.getManufactorName());
					
					rowArray[8] = "";
					if(dtl.getCos4002Dbs()!=null && dtl.getCos4002Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos4002DbObj : dtl.getCos4002Dbs()){
							Cos4002Db cos4002Db = (Cos4002Db)cos4002DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(CACMap.get(Common.get(cos4002Db.getAdverseCondition()))).equals("")?Common.get(cos4002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos4002Db.getAdverseCondition()))));
						}
						rowArray[8] = sb.toString();
					}
					
					rowArray[9] = "";
					if(dtl.getCos4003Dbs()!=null && dtl.getCos4003Dbs().size()>0){
						StringBuffer sb = new StringBuffer();
						for(Object cos4003DbObj : dtl.getCos4003Dbs()){
							Cos4003Db cos4003Db = (Cos4003Db)cos4003DbObj;
							if(sb.toString().length() > 0){
								sb.append("、");
							}
							sb.append(Common.get(subCodeNameMap.get(Common.get(cos4003Db.getSubCode()))));
						}
					}
					
					rowArray[10] = "";
					if("00,01,02,30,90".indexOf(Common.get(dtl.getStatus())) == -1){
						rowArray[10] = "處理中";
					}else{
						rowArray[10] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
					}
					arrList.add(rowArray);
				}
				objList.clear();
				
				CCTMap.clear();
				CPTMap.clear();
				CCSMap.clear();
				CACMap.clear();
				subCodeNameMap.clear();
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
