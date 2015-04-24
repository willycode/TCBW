package com.kangdainfo.tcbw.view.drgin;


import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0309F extends DRGIN0301F{
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS, q_occurDateE;
	private String q_notifierRepDateS, q_notifierRepDateE;
	private String q_enrolledDateS, q_enrolledDateE;
	private String q_notifierSource;
	private String q_notifierDept;
	private String q_productName;
	private String q_scientificName;
	private String q_applicationName;
	private String q_manufactorName;
	private String[] q_conSequence;
	private String q_medNti;
	private String q_medAtcCode;
	private String q_assessResult;
	private String q_status;
	
	private String id;//序號	NUMERIC(19,0)
	private String actionType;
	private String chargeMan;	//作業人員	VARCHAR(50)
	private String caseType;
	private String q_isTrans;  	//是否為歷史移轉資料
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getChargeMan() {return checkGet(chargeMan);}
	public void setChargeMan(String s) {this.chargeMan = checkSet(s);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String s) {this.caseType = checkSet(s);}
	

	public String getQ_applNoS() {	return checkGet(q_applNoS);	}
	public void setQ_applNoS(String q_applNoS) {	this.q_applNoS = checkSet(q_applNoS);	}
	public String getQ_applNoE() {	return checkGet(q_applNoE);	}
	public void setQ_applNoE(String q_applNoE) {	this.q_applNoE = checkSet(q_applNoE);	}
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
	public String getQ_occurDateS() {return checkGet(q_occurDateS);}
	public void setQ_occurDateS(String s) {this.q_occurDateS = checkSet(s);}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);}
	public void setQ_occurDateE(String s) {this.q_occurDateE = checkSet(s);}
	public String getQ_enrolledDateS() {return checkGet(q_enrolledDateS);}
	public void setQ_enrolledDateS(String s) {this.q_enrolledDateS = checkSet(s);}
	public String getQ_enrolledDateE() {return checkGet(q_enrolledDateE);}
	public void setQ_enrolledDateE(String s) {this.q_enrolledDateE = checkSet(s);}
	public String getQ_notifierSource() {return checkGet(q_notifierSource);}
	public void setQ_notifierSource(String s) {this.q_notifierSource = checkSet(s);}
	public String getQ_notifierDept() {return checkGet(q_notifierDept);}
	public void setQ_notifierDept(String s) {this.q_notifierDept = checkSet(s);}
	public String getQ_scientificName() {return checkGet(q_scientificName);}
	public void setQ_scientificName(String s) {this.q_scientificName = checkSet(s);}
	public String getQ_applicationName() {return checkGet(q_applicationName);}
	public void setQ_applicationName(String s) {this.q_applicationName = checkSet(s);}
	public String getQ_manufactorName() {return checkGet(q_manufactorName);}
	public void setQ_manufactorName(String s) {this.q_manufactorName = checkSet(s);}
	public String[] getQ_conSequence() {return q_conSequence;}
	public void setQ_conSequence(String[] s) {this.q_conSequence = s;}
	public String getQ_medNti() {return checkGet(q_medNti);}
	public void setQ_medNti(String s) {this.q_medNti = checkSet(s);}
	public String getQ_medAtcCode() {return checkGet(q_medAtcCode);}
	public void setQ_medAtcCode(String s) {this.q_medAtcCode = checkSet(s);}
	public String getQ_assessResult() {return checkGet(q_assessResult);}
	public void setQ_assessResult(String s) {this.q_assessResult = checkSet(s);}
	public String getQ_status() {return checkGet(q_status);}
	public void setQ_status(String s) {this.q_status = checkSet(s);}
	public String getQ_isTrans() {	return checkGet(q_isTrans);	}
	public void setQ_isTrans(String q_isTrans) { this.q_isTrans = checkSet(q_isTrans);	}

	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	String[] ids;
	public String[] getIds() {		return ids;	}
	public void setIds(String[] ids) {		this.ids = ids;	}
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg4001Db where (id in ( select max(a.id) from Drg4001Db a where (a.applNo is not null or a.applNo <> '')  group by a.applNo )"+
		     	"  or id in ( select b.id from Drg4001Db b where (b.applNo is null or b.applNo = '')))";
		
		if(!"".equals(getQ_applNoS()) && !"".equals(getQ_applNoE())) 
			hql += " and applNo >= " +  Common.sqlChar(getQ_applNoS())+ " and applNo <= " + Common.sqlChar(getQ_applNoE());
		else if(!"".equals(getQ_applNoS()) && "".equals(getQ_applNoE()))
			hql += " and applNo like " + Common.sqlChar("%"+getQ_applNoS()+"%");
		else if("".equals(getQ_applNoS()) && !"".equals(getQ_applNoE()))
			hql += " and applNo like " + Common.sqlChar("%"+getQ_applNoE()+"%");
		
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_occurDateS()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		if(!"".equals(getQ_enrolledDateS()))
			hql += " and enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS());
		if(!"".equals(getQ_enrolledDateE()))
			hql += " and enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE());
		if(!"".equals(getQ_notifierSource()))
			hql += " and notifierSource = " + Common.sqlChar(getQ_notifierSource());
		if(!"".equals(getQ_notifierDept()))
			hql += " and notifierDept = " + Common.sqlChar(getQ_notifierDept());
		if(!"".equals(getQ_permitKey())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and permitKey = " + Common.sqlChar(getQ_permitKey())+")";
		}
		if(!"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}
		if(!"".equals(getQ_productName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and productName like " + Common.likeSqlChar(getQ_productName())+")";
		}
		if(!"".equals(getQ_scientificName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and scientificName like " + Common.likeSqlChar(getQ_scientificName())+")";
		}
		if(!"".equals(getQ_applicationName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and applicationName like " + Common.likeSqlChar(getQ_applicationName())+")";
		}
		if(!"".equals(getQ_manufactorName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and manufactorName like " + Common.likeSqlChar(getQ_manufactorName())+")";
		}
		if(null != getQ_conSequence() && !"".equals(getQ_conSequence())){
			hql += " and ( ";
			for(int i=0;i<getQ_conSequence().length;i++){
				if(i > 0) hql += " or ";
				hql += "  conSequence  like " + Common.likeSqlChar(getQ_conSequence()[i]);
			}
			hql += ")";
		}
		if(!"".equals(getQ_medNti())){
			hql += " and applNo in (select applNo from Drg4004Db where medNti = 'Y')";
		}
		if(!"".equals(getQ_medAtcCode())){
			hql += " and applNo in (select applNo from Drg4004Db where medAtcCode like "+Common.likeSqlChar(getQ_medAtcCode(),false)+")";
		}
		if(!"".equals(getQ_assessResult())){
			hql += " and applNo in (select applNo from Drg4004Db where assessResult = " + Common.sqlChar(getQ_assessResult())+")";
		}
		if(!"".equals(getQ_status())){
			hql += " and status = " + Common.sqlChar(getQ_status());
		}
		if(!"".equals(getQ_isTrans())){
			if("Y".equals(getQ_isTrans())) hql += " and trans='Y' ";
			else if ("N".equals(getQ_isTrans())) hql += " and ( trans is null or trans='' ) ";
		}
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
					Drg4001Db dtl = (Drg4001Db)dtlObj;
					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = "";
					rowArray[4] = "";
					rowArray[5] = "";
					rowArray[6] = "";
					rowArray[7] = "";
					rowArray[8] = "";
					if(null != dtl.getDrg4003Dbs() && !dtl.getDrg4003Dbs().isEmpty()){
						for(Object dtl43:dtl.getDrg4003Dbs()){
							Drg4003Db drg43 = (Drg4003Db)dtl43;
							if("02".equals(drg43.getMedType())){
								if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
									rowArray[3] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
								}
								rowArray[4] =  Common.get(drg43.getScientificName());
								rowArray[5] =  Common.get(drg43.getProductName());
								rowArray[6] =  Common.get(drg43.getApplicationName());
								rowArray[7] =  Common.get(drg43.getManufactorName());
								rowArray[8] =  Common.get(drg43.getManufactorNo());
							}
						}
					}
					rowArray[9] = "";
					if(StringUtils.contains(dtl.getConSequence(), "1")) {
						rowArray[9] += "藥效改變";
					}
					if(StringUtils.contains(dtl.getConSequence(), "2")) {
						rowArray[9] += "不良反應發生、強度增強或頻率增加";
					}
					rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));					
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
