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


public class DRGIN0308F extends DRGIN0302F{
	
	private String q_applNo;
	private String q_permitKey;
	private String q_permitNo;
	private String q_notifierRepDateS, q_notifierRepDateE;
	private String q_productName;
	private String q_chargeMan;
	
	private String id;//序號	NUMERIC(19,0)
	private String actionType;
	private String chargeMan;	//作業人員	VARCHAR(50)
	private String caseType;
	
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getChargeMan() {return checkGet(chargeMan);}
	public void setChargeMan(String s) {this.chargeMan = checkSet(s);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String s) {this.caseType = checkSet(s);}
	
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
	public String getQ_chargeMan() {return checkGet(q_chargeMan);}
	public void setQ_chargeMan(String s) {this.q_chargeMan = checkSet(s);}

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
		hql += " and status in ('40','50')";
		
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_chargeMan()))
			hql += " and chargeMan = " + Common.sqlChar(getQ_chargeMan());
		if(!"".equals(getQ_productName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and productName like " + Common.likeSqlChar(getQ_productName())+")";
		}
		if(!"".equals(getQ_permitKey())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitKey = " + Common.sqlChar(getQ_permitKey())+")";
		}
		if(!"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}

		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
		if(objList!=null && objList.size()>0)
		{
			java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRG0310");
			for(Object dtlObj : objList)
			{
				Drg4001Db dtl = (Drg4001Db)dtlObj;
				String[] rowArray = new String[10];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRevDate());
				rowArray[3] = "";
				Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(dtl.getApplNo()));
				if(null != drg04){
					if(StringUtils.contains(drg04.getConSequence(), "1")) {
						rowArray[3] += "藥效改變";
					}
					if(StringUtils.contains(drg04.getConSequence(), "2")) {
						rowArray[3] += "不良反應發生、強度增強或頻率增加";
					}
				}
				rowArray[4] = "";
				rowArray[5] = "";
				rowArray[6] = "";
				rowArray[7] = "";
				if(null != dtl.getDrg4003Dbs() && !dtl.getDrg4003Dbs().isEmpty()){
					for(Object dtl43:dtl.getDrg4003Dbs()){
						Drg4003Db drg43 = (Drg4003Db)dtl43;
						if("02".equals(drg43.getMedType())){
							if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
								rowArray[4] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
							}
							rowArray[5] =  Common.get(drg43.getScientificName());
							rowArray[6] =  Common.get(drg43.getProductName());
							rowArray[7] =  Common.get(drg43.getManufactorName());
						}
					}
				}
				rowArray[8] =  Common.get(statusMap.get(dtl.getStatus()));
				rowArray[9] =  Common.get(View.getCommonUserName(dtl.getChargeMan()));
				arrList.add(rowArray);
			}
			objList.clear();
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
