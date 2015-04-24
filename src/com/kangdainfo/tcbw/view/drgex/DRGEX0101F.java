package com.kangdainfo.tcbw.view.drgex;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgin.DRGIN0106F;


public class DRGEX0101F extends SuperBean{
	
	private String isInOrOutPerson;
	public String getIsInOrOutPerson() {return checkGet(isInOrOutPerson);	}
	public void setIsInOrOutPerson(String isInOrOutPerson) {		this.isInOrOutPerson = checkSet(isInOrOutPerson);	}
	
	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	private String id;//序號	NUMERIC(19,0)
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	
	String applNo ;
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS;
	private String q_occurDateE;
	private String q_status;
	private String q_Product;
	
	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}
	public void setQ_applNoS(String qApplNoS) {
		q_applNoS = checkSet(qApplNoS);
	}
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}
	public void setQ_applNoE(String qApplNoE) {
		q_applNoE = checkSet(qApplNoE);
	}
	
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}
	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}
	public void setQ_occurDateS(String qOccurDateS) {
		q_occurDateS = checkSet(qOccurDateS);
	}
	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}
	public void setQ_occurDateE(String qOccurDateE) {
		q_occurDateE = checkSet(qOccurDateE);
	}
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}
	public String getQ_Product() {
		return checkGet(q_Product);
	}
	public void setQ_Product(String qProduct) {
		q_Product = checkSet(qProduct);
	}		
	
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg5001Db a where 1=1 ";
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(!"3".equals(getCaseType())){  //非廠商回復
			hql += " and ( a.creator = " + Common.sqlChar(getUserID())+" or a.notifierUserID = "+Common.sqlChar(getUserID());					
			//廠商:同廠商的人都能看到相同廠商登錄案件
			if("02".equals(c.getCommonDepartment().getDepartmentCode())){			
				hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";		
			}
            //醫事機構 :1.若有變更，只能查到變更日期後的案件 2.同醫事機構的人都看的到
//			else if("03".equals(c.getCommonDepartment().getDepartmentCode())){			
//				if(!"".equals(Common.get(c.getUserJobModDate()))){				
//					hql += " or ( a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" and a.notifierRepDate >"+Common.sqlChar(c.getUserJobModDate())+" ))";			
//				}else{				
//					hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";			
//				}		
//			}
            //衛生單位:同衛生單位的人都能看到相同廠商登錄案件		
//			else if("04".equals(c.getCommonDepartment().getDepartmentCode())){			
//				hql += " or a.notifierDeptID ="+Common.sqlChar(c.getUserJob())+" )";
//			}
			else{			
				hql += " )";		
			}	
		}else{  //廠方回復
			//已使用者查詢出所屬廠商統編，再比對案件
			hql += " and a.applicationID in ( select a.con1005Db.compegno from Con1006Db a where a.commonUser.userId in ("+Common.sqlChar(getUserID())+" ))";
		}		

		
		if(!"".equals(getCaseType())){
			if("2".equals(getCaseType())) hql +=" and a.status in ('02','22','23') ";
			if("3".equals(getCaseType())){	hql +=" and a.status = ('41') ";}
			if("4".equals(getCaseType())){	hql +=" and a.status = ('00') ";}
		}
		
		if(!"".equals(getQ_applNoS()))
			hql += " and a.applNo >= " + Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			hql += " and a.applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if(!"".equals(getQ_occurDateS()))
			hql += " and a.occurDate >= " + Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			hql += " and a.occurDate <= " + Common.sqlChar(getQ_occurDateE());
		
		if(!"".equals(getQ_permitKey()))
			hql += " and a.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and a.permitNo = " + Common.sqlChar(getQ_permitNo());
		
		if(!"".equals(getQ_Product()))
			hql += " and ( a.chProduct like " + Common.sqlChar("%"+Common.get(getQ_Product())+"%")+
		           " or a.enProduct like "+ Common.sqlChar("%"+Common.get(getQ_Product())+"%")+" )";		

		if(!"".equals(getQ_status())){
			if("2x".equals(getQ_status())){
				hql += " and a.status in ('22','23') ";	
			}else if("xx".equals(getQ_status())){
				hql += " and a.status not in ('00','01','02','03','22','23') ";
			}else{
				hql += " and a.status = " + Common.sqlChar(getQ_status());
			}				
		}
		//若有相同案號，僅顯示最新一筆
		hql += " and ( a.id in ( select max(id) from Drg5001Db where drg0001Id != null  group by drg0001Id )"+
		         "  or a.id in ( select id from Drg5001Db where (applNo is null or applNo = '') and drg0001Id is null ) )";
		
		System.out.println("[TCBW]-[DRGEX0101F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id DESC", this.getRecordStart(), this.getPageSize());
			
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
				for(Object dtlObj : objList)
				{
					Drg5001Db dtl = (Drg5001Db)dtlObj;
					
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRepDate());
					rowArray[3] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					//rowArray[6] = Common.get(statusMap.get(dtl.getStatus()));
					
					if("00,01,02,03".indexOf(Common.get(dtl.getStatus())) != -1)
					{
						rowArray[6] = Common.get(statusMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(statusMap.get(Common.get(dtl.getStatus())));
					}
					else if("22,23".indexOf(Common.get(dtl.getStatus())) != -1){
						rowArray[6] = "補件中";
					}
					else
					{
						rowArray[6] = "通報完成";
					}
					
					arrList.add(rowArray);
				}
				objList.clear();
				statusMap.clear();
				pkidMap.clear();
			}
			
		}
		return arrList;
	}


	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}
	
}
