package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0117F extends SuperBean{
	
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
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id DESC");
			
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
					
					String light =""; //燈號					
					
					if("41,42".indexOf(Common.get(getCaseType())) != -1){ //交付中、已交付
						Drg0008Db c = (Drg0008Db) View.getObject(" from Drg0008Db where applNo=" + Common.sqlChar(dtl.getApplNo()));
						if(c!=null){
							//回覆日期
							String replyDate = View.getLookupField("select assessDate from Drg0007Db where applNo="+Common.sqlChar(dtl.getApplNo())+" order by id desc");							
                            String days = Datetime.getDateDiff("d",c.getPayDate(),replyDate);
                            if(Common.getInt(days) >=0 && Common.getInt(days) <=7) light="<img width='30px' src='../../images/ballYellow.png'>"; //廠商回覆逾期(系統日期超過交付CAPA日期)，且<=7天：燈號顯示黃色。
                            if(Common.getInt(days) > 7) light="<img width='30px' src='../../images/ballRed.png'>";             //廠商回覆逾期(系統日期超過交付CAPA日期)，且＞7天者：燈號顯示紅色。
                            
							String[] rowArray = new String[18];					
							rowArray[0] = Common.get(dtl.getId());						
							rowArray[1] = Common.get(light);						
							rowArray[2] = Common.get(dtl.getApplNo());						
							rowArray[3] = Common.get(dtl.getNotifierRepDate());						
							rowArray[4] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");						
							rowArray[5] = Common.get(dtl.getChProduct());						
							rowArray[6] = Common.get(dtl.getEnProduct());						
							rowArray[7] = Common.get(dtl.getApplicationName());						
							rowArray[8] = Common.get(dtl.getManufactorName());					
							rowArray[9] = Common.get(dtl.getManufactorNo());						
							rowArray[10] = Common.get(dpdList);						
							rowArray[11] = Common.get(c.getPayDate());						
							rowArray[12] = Common.get(c.getDelayDate());						
							rowArray[13] = Common.get(c.getAddDocDate());						
							rowArray[14] = Common.get(replyDate);						
							rowArray[15] = Common.get(statusMap.get(dtl.getStatus()));						
							rowArray[16] = Common.get(dtl.getChargeMan());	
							rowArray[17] =  "<div class='discolor'><a href='drgin0112f.jsp?id="+Common.get(dtl.getId())+"&caseType="+getCaseType()+"'>"+"明細"+"</a></div>";
							arrList.add(rowArray);
						}
					}
					else if("43".indexOf(Common.get(getCaseType())) != -1){ //再評估						
						String[] rowArray = new String[12];					
						rowArray[0] = Common.get(dtl.getId());										
						rowArray[1] = Common.get(dtl.getApplNo());					
						rowArray[2] = Common.get(dtl.getNotifierRepDate());					
						rowArray[3] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");				
						rowArray[4] = Common.get(dtl.getChProduct());					
						rowArray[5] = Common.get(dtl.getEnProduct());					
						rowArray[6] = Common.get(dtl.getApplicationName());			
						rowArray[7] = Common.get(dtl.getManufactorName());			
						rowArray[8] = Common.get(dtl.getManufactorNo());					
						rowArray[9] = Common.get(dpdList);
						rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));	
						rowArray[11] = Common.get(dtl.getChargeMan());						
						arrList.add(rowArray);
						
					}
					else{ //待發文
						String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(dtl.getApplNo()));
						if("02,03".indexOf(Common.get(drgLev)) != -1) light="<img width='30px' src='../../images/ballRed.png'>"; //A+級、A級案件：燈號顯示紅色
						String[] rowArray = new String[13];					
						rowArray[0] = Common.get(dtl.getId());					
						rowArray[1] = Common.get(light);					
						rowArray[2] = Common.get(dtl.getApplNo());					
						rowArray[3] = Common.get(dtl.getNotifierRepDate());					
						rowArray[4] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");				
						rowArray[5] = Common.get(dtl.getChProduct());					
						rowArray[6] = Common.get(dtl.getEnProduct());					
						rowArray[7] = Common.get(dtl.getApplicationName());			
						rowArray[8] = Common.get(dtl.getManufactorName());			
						rowArray[9] = Common.get(dtl.getManufactorNo());					
						rowArray[10] = Common.get(dpdList);
						rowArray[11] = Common.get(statusMap.get(dtl.getStatus()));	
						rowArray[12] = Common.get(dtl.getChargeMan());						
						arrList.add(rowArray);
					}				
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
