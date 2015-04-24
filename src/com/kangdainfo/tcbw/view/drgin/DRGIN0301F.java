package com.kangdainfo.tcbw.view.drgin;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0301F extends SuperBean{
	
	private String q_permitKey;
	private String q_permitNo;
	private String q_notifierRepDateS, q_notifierRepDateE;
	private String q_productName;
	private String q_isComplete;
	private String q_permitKeyNo;
	private String q_chargeMan;
	
	private String id;//序號	NUMERIC(19,0)
	private String actionType;
	private String chargeMan;	//作業人員	VARCHAR(50)
	private String caseType;
	private String drg45Id;					//是否發送詢問函
	private String drg47Id;					//更新評估資料
	private String formType;
	private String tabId;
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getChargeMan() {return checkGet(chargeMan);}
	public void setChargeMan(String s) {this.chargeMan = checkSet(s);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String s) {this.caseType = checkSet(s);}
	public String getDrg45Id() {return checkGet(drg45Id);}
	public void setDrg45Id(String s) {this.drg45Id = checkSet(s);}
	public String getDrg47Id() {return checkGet(drg47Id);}
	public void setDrg47Id(String s) {this.drg47Id = checkSet(s);}
	public String getFormType() {return checkGet(formType);}
	public void setFormType(String s) {this.formType = checkSet(s);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String s) {this.tabId = checkSet(s);}
	
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
	public String getQ_isComplete() {return checkGet(q_isComplete);}
	public void setQ_isComplete(String s) {this.q_isComplete = checkSet(s);}
	public String getQ_permitKeyNo() {return checkGet(q_permitKeyNo);}
	public void setQ_permitKeyNo(String s) {this.q_permitKeyNo = checkSet(s);}
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
		hql += " and status in ('10','11')";
		if(!"".equals(getQ_isComplete()))
			hql += " and isComplete = " + Common.sqlChar(getQ_isComplete());
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
		
		System.out.println("[TCBW]-[DRGEX0301F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRG0310");
			for(Object dtlObj : objList)
			{
				Drg4001Db dtl = (Drg4001Db)dtlObj;
				String[] rowArray = new String[10];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getNotifierRevDate());
				rowArray[2] = Common.get(View.getCommonCodeName("DRGNFS", dtl.getNotifierSource()));
				rowArray[3] = "";
				if("1".equals(dtl.getConSequence())){
					rowArray[3] += "藥效改變";
				}
				if("2".equals(dtl.getConSequence())){
					if(rowArray[3].length() > 0 ) rowArray[3] += "、";
					rowArray[3] += "不良反應發生、強度增強或頻率增加";
				}
				rowArray[4] = "";
				rowArray[5] = "";
				rowArray[6] = "";
				if(null != dtl.getDrg4003Dbs() && !dtl.getDrg4003Dbs().isEmpty()){
					for(Object dtl43:dtl.getDrg4003Dbs()){
						Drg4003Db drg43 = (Drg4003Db)dtl43;
						if("02".equals(drg43.getMedType())){
							if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
								rowArray[4] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
							}
							rowArray[5] =  Common.get(drg43.getScientificName());
							rowArray[6] =  Common.get(drg43.getProductName());
						}
					}
				}
				rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
				rowArray[8] =  Common.get(View.getCommonUserName(dtl.getChargeMan()));
				//rowArray[9] =  "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryData('" + dtl.getId() + "');\"> ";
				rowArray[9] =  "<div class='discolor'><a href='drgin0302f.jsp?id="+Common.get(dtl.getId())+"&formType=0301&q_isComplete="+getQ_isComplete()+"'>"+"明細"+"</a></div>";

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
	public void doUpdate() throws Exception {}

	@Override
	public void doDelete() throws Exception {
		
	}
}
