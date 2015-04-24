package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0304F extends DRGIN0301F{
	
	private String q_applNoS, q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS, q_occurDateE;
	private String q_productName;
	
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
	
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String s) {this.q_applNoS = checkSet(s);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String s) {this.q_applNoE = checkSet(s);}
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}
	public String getQ_occurDateS() {return checkGet(q_occurDateS);}
	public void setQ_occurDateS(String s) {this.q_occurDateS = checkSet(s);}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);}
	public void setQ_occurDateE(String s) {this.q_occurDateE = checkSet(s);}
	public String getQ_productName() {return checkGet(q_productName);}
	public void setQ_productName(String s) {this.q_productName = checkSet(s);}

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
		hql += " and status in ('30')";
		
		if(!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if(!"".equals(getQ_occurDateS()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		if(!"".equals(getQ_productName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and productName like " + Common.likeSqlChar(getQ_productName())+")";
		}
		if(!"".equals(getQ_permitKey())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitKey = " + Common.sqlChar(getQ_permitKey())+")";
		}
		if(!"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}

		System.out.println("[TCBW]-[DRGEX0301F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc", this.getRecordStart(), this.getPageSize());
		if(objList!=null && objList.size()>0)
		{
			for(Object dtlObj : objList)
			{
				Drg4001Db dtl = (Drg4001Db)dtlObj;
				String[] rowArray = new String[7];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRevDate());
				rowArray[3] = "";
				rowArray[4] = "";
				rowArray[5] = "";
				rowArray[6] = "";
				Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(dtl.getApplNo()));
				if(null != drg04){
					if("Y".equals(drg04.getMedNti())){
						rowArray[3] = "NTI Drugs";
					}
					if(null != drg04.getMedAtcCode() && !"".equals(drg04.getMedAtcCode())){
						rowArray[3] += "藥理治療分類(ATC code)："+drg04.getMedAtcCode();
					}
					if(StringUtils.contains(drg04.getConSequence(), "1")) {
						rowArray[4] += "藥效改變";
					}
					if(StringUtils.contains(drg04.getConSequence(), "2")) {
						rowArray[4] += "不良反應發生、強度增強或頻率增加";
						rowArray[5] = Common.get(View.getCommonCodeName("DRG0308", drg04.getBadReactionLev()));
					}
					rowArray[6] = Common.get(View.getCommonCodeName("DRG2RKL", drg04.getAssessResult()));
				}
				
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
