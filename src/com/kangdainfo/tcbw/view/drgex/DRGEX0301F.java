package com.kangdainfo.tcbw.view.drgex;

import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGEX0301F extends SuperBean{
	
	private String q_applNo;
	private String q_permitKey;
	private String q_permitNo;
	private String q_notifierRepDateS, q_notifierRepDateE;
	private String q_productName;
	
	public String getQ_applNo() {return checkGet(q_applNo);}
	public void setQ_applNo(String s) {this.q_applNo = checkSet(s);}
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}
	public String getQ_notifierRepDateS() {return checkGet(q_notifierRepDateS);}
	public void setQ_notifierRepDateS(String s) {this.q_notifierRepDateS = checkSet(s);}
	public String getQ_notifierRepDateE() {return checkGet(q_notifierRepDateE);}
	public void setQ_notifierRepDateE(String s) {this.q_notifierRepDateE = checkSet(s);}
	public String getQ_productName() {return checkGet(q_productName);}
	public void setQ_productName(String s) {this.q_productName = checkSet(s);}
	
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
	


	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg6001Db where ( creator="+Common.sqlChar(TCBWCommon.getCurrentUserId())+" or notifierUserID="+Common.sqlChar(TCBWCommon.getCurrentUserId())+")";		
		if("2".equals(getCaseType())){
			hql += " and status in ('02','21','22')";
		}else if("3".equals(getCaseType())){
			hql += " and status in ('00')";
		}
		hql += " and ( id in ( select max(id) from Drg6001Db where drg4001Id != null  group by drg4001Id )"+
			"  or id in ( select id from Drg6001Db where (applNo is null or applNo = '') and drg4001Id is null ) )";

		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(this.getQ_productName())){
			hql += " and id in (select drg6001Db.id from Drg6003Db where productName like " + Common.likeSqlChar(getQ_productName())+")";
		}
		if(!"".equals(getQ_permitKey())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitKey = " + Common.sqlChar(getQ_permitKey())+")";
		}
		if(!"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}
		System.out.println("[TCBW]-[DRGEX0301F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRG0310");
				for(Object dtlObj : objList)
				{
					Drg6001Db dtl = (Drg6001Db)dtlObj;
					String[] rowArray = new String[9];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = "";
					if("1".equals(dtl.getConSequence())){
						rowArray[3] = "藥效改變";
					}else if("2".equals(dtl.getConSequence())){
						rowArray[3] = "不良反應發生、強度增強或頻率增加";
					}else if("1,2".equals(dtl.getConSequence())){
						rowArray[3] = "藥效改變及不良反應發生、強度增強或頻率增加";
					}
					rowArray[4] = "";
					rowArray[5] = "";
					rowArray[6] = "";
					if(null != dtl.getDrg6003Dbs() && !dtl.getDrg6003Dbs().isEmpty()){
						for(Object dtl63:dtl.getDrg6003Dbs()){
							Drg6003Db drg63 = (Drg6003Db)dtl63;
							if("02".equals(drg63.getMedType())){
								if(null != drg63.getPermitKey() && !"".equals(drg63.getPermitKey()) && null != drg63.getPermitNo() && !"".equals(drg63.getPermitNo())){
									rowArray[4] =  Common.get(View.getCommonCodeName("DRGPKID", drg63.getPermitKey()))+"第"+Common.get(drg63.getPermitNo())+"號";
								}
								rowArray[5] =  Common.get(drg63.getScientificName());
								rowArray[6] =  Common.get(drg63.getProductName());
							}
						}
					}
					rowArray[7] = "";
					if(StringUtils.contains("00,01,02,03", dtl.getStatus())){
						rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
					}else if(StringUtils.contains("21,22", dtl.getStatus())){
						rowArray[7] = "補件中";
					}else{
						rowArray[7] = "通報完成";
					}
					
					arrList.add(rowArray);
				}
				objList.clear();
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
