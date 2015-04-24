package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0116F extends SuperBean{
	
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
	
	String drgLevType ;
	public String getDrgLevType() {return checkGet(drgLevType);}
	public void setDrgLevType(String drgLevType) {this.drgLevType = checkSet(drgLevType);}
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS;
	private String q_occurDateE;
	private String q_chargeMan;
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
	public String getQ_chargeMan() {
		return checkGet(q_chargeMan);
	}
	public void setQ_chargeMan(String qchargeMan) {
		q_chargeMan = checkSet(qchargeMan);
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
		
		String hql = " from Drg0001Db a where 1=1 ";

		//hql += " where a.creator = " + Common.sqlChar(getUserID());
		
		if(!"".equals(getCaseType())){
			if("2x".equals(getCaseType())){ 
				hql += " and a.status in ('21','22','23','24')" ;
			}
			else{ 
				hql += " and a.status = "+ Common.sqlChar(getCaseType());
			}
		}
		
		if(!"".equals(getDrgLevType())){
			if("1".equals(getDrgLevType())){ 
				hql += " and a.applNo in ( select applNo from Drg0004Db where drgLev in ('01','02','03')) " ;
			}
			else{ 
				hql += " and a.applNo in ( select applNo from Drg0004Db where drgLev in ('04','05')) ";
			}
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

		if(!"".equals(getQ_chargeMan()))
			hql += " and a.chargeMan = " + Common.sqlChar(getQ_chargeMan());	
		
		System.out.println("[TCBW]-[DRGIN0105F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id desc");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1"); //不良品流程
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
				java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRGDPD"); //不良品缺陷(主)
				java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
				java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN"); //風險評估
				java.util.Map<String, String> rklMap = TCBWCommon.getCommonCodeMap("DRGRKL"); //風險評估(風險等級)
				for(Object dtlObj : objList)
				{
					Drg0001Db dtl = (Drg0001Db)dtlObj;					

					java.util.List drg02List = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(dtl.getId())+" order by id asc");
					String dpdList ="";
					if(drg02List!=null && drg02List.size()>0)
					{
						java.util.Iterator it = drg02List.iterator();
						while (it.hasNext()) 
						{					
							Drg0002Db o = (Drg0002Db) it.next();
							if(dpdList.length()>0) dpdList += ", ";
							dpdList += dpdMap.get(o.getMainCode())+":";
							
							if(!"".equals(Common.get(o.getSubCode()))){
								String subCodeList ="";
								String[] subCode = Common.get(o.getSubCode()).split(",");
								for(int j=0; j<subCode.length; j++){
									if(subCodeList.length()>0) subCodeList += ",";
									subCodeList += subMap.get(subCode[j]);
									if("ZZ".equals(subCode[j].substring(2))){
										subCodeList += (":"+o.getOtherDescribe());										
									}
								}
								dpdList += subCodeList;
							}else{							
								dpdList += o.getOtherDescribe();
							}
						}
					}
					drg02List.clear();
					
					if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());					
					String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(dtl.getApplNo()));
					
					String light =""; //燈號(有異動過為紅色)
					String hql2 = " select id,firstResult from Drg0003Db where applNo="+Common.sqlChar(dtl.getApplNo())+" order by id desc";
					java.util.List drg03list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(hql2);
					if (drg03list!=null && drg03list.size()>0) {
						String oldfirstResult = "";
						for(int i=0 ; i<drg03list.size();i++){
							Object[] o = (Object[]) drg03list.get(i);
							if(i==0){
								oldfirstResult = Common.get(o[1]); //取最新一筆
							}else{
								if(!oldfirstResult.equals(Common.get(o[1]))){
									//light="紅";
									light="<img width='30px' src='../../images/ballRed.png'>";
								    break;
								}
							}
						}
					}				
					
					String[] rowArray = new String[12];					
					rowArray[0] = Common.get(dtl.getId());					
					rowArray[1] = Common.get(light);					
					rowArray[2] = Common.get(dtl.getApplNo());					
					rowArray[3] = Common.get(dtl.getNotifierRepDate());					
					rowArray[4] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");					
					rowArray[5] = Common.get(dtl.getChProduct());					
					rowArray[6] = Common.get(dtl.getEnProduct());
					rowArray[7] = Common.get(flnMap.get(dtl.getMedModel()));
					rowArray[8] = Common.get(dpdList);			
					rowArray[9] = Common.get(rklMap.get(drgLev));				
					rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));					
					rowArray[11] = Common.get(dtl.getChargeMan());					
					arrList.add(rowArray);					
				}
				dpdMap.clear();
				objList.clear();
				statusMap.clear();
				pkidMap.clear();
				rklMap.clear();
				flnMap.clear();
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
