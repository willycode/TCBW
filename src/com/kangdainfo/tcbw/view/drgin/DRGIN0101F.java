package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0101F extends SuperBean{
	
	private String[] fds;
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}
	
	private String isInOrOutPerson;
	public String getIsInOrOutPerson() {return checkGet(isInOrOutPerson);	}
	public void setIsInOrOutPerson(String isInOrOutPerson) {		this.isInOrOutPerson = checkSet(isInOrOutPerson);	}
	
	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	private String id;//序號	NUMERIC(19,0)
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	String isComplete ;
	public String getIsComplete() {return checkGet(isComplete);}
	public void setIsComplete(String isComplete) {this.isComplete = checkSet(isComplete);}
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS;
	private String q_occurDateE;
	private String q_status;
	private String q_Product;
	private String q_chargeMan;
	
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
	public String getQ_chargeMan() {
		return checkGet(q_chargeMan);
	}
	public void setQ_chargeMan(String qchargeMan) {
		q_chargeMan = checkSet(qchargeMan);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	public void doAccepted() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().acceptedByDrgIN0101F(this);
	}
	
	@Override
	public Object doQueryAll() throws Exception {		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg0001Db a ";

		hql += " where status in ('10','11') ";
		
		if(!"".equals(getIsComplete()))
		    hql += " and a.isComplete = "+ Common.sqlChar(getIsComplete());
		
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

		if(!"".equals(getQ_chargeMan()))
			hql += " and a.chargeMan = " + Common.sqlChar(getQ_chargeMan());
		
		if(!"".equals(getQ_status()))
			hql += " and a.status = " + Common.sqlChar(getQ_status());	
		
		System.out.println("[TCBW]-[DRGIN0101F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id desc");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
				java.util.Map<String, String> nfsMap = TCBWCommon.getCommonCodeMap("DRGNFS");				
				for(Object dtlObj : objList)
				{
					Drg0001Db dtl = (Drg0001Db)dtlObj;
					
					String[] rowArray = new String[10];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRepDate());
					rowArray[3] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(nfsMap.get(dtl.getNotifierSource()));
					rowArray[7] = Common.get(dtl.getChargeMan());
					rowArray[8] = Common.get(statusMap.get(dtl.getStatus()));
					rowArray[9] =  "<div class='discolor'><a href='drgin0102f.jsp?id="+Common.get(dtl.getId())+"&isComplete="+getIsComplete()+"'>"+"明細"+"</a></div>";

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
