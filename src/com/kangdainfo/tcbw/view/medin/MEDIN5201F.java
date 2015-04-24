package com.kangdainfo.tcbw.view.medin;

import java.util.ArrayList;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN5201F extends SuperBean{
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	private String id;//序號	NUMERIC(19,0)
	private String doType;
	private String isInOrOutPerson;
    private String inOrOut;//分內外網
    private String statusType;//依狀態查不同資料
    
	private String q_applNoS;//案件編號起
	private String q_applNoE;//案件編號迄
	//通報日期起訖
	private String q_notifierRepDateS;//通報中心接獲通報日期起
	private String q_notifierRepDateE;//通報中心接獲通報日期迄
	//發生日期起訖
	private String q_occurDateS;//發生日期起
	private String q_occurDateE;//發生日期迄
	//收案日期起訖
	private String q_notifierRevDateS;//通報者獲知日期起
	private String q_notifierRevDateE;//通報者獲知日期迄
	//通報來源
	
	//通報單位
	
	//案例來源
	private String q_caseSource;//案例來源
	//許可證字號
	private String q_medPermit;//懷疑的醫療器材-許可證字號-字
	private String q_medPermitNumber;//懷疑的醫療器材-許可證字號-號
	//列管單位
	//醫材品名
	//製造廠
	
	//醫材主類別
	private String q_medMainCategory;//懷疑的醫療器材-醫材主類別
	//醫材次類別
	private String q_medSecCategory;//懷疑的醫療器材-醫材次類別
	//不良反應結果
	
	//案件狀態
	private String q_status;//案件狀態
	
	
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
        java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med2001Db a where 1=1 ";

		if("10".equals(getStatusType()))
		{	
			hql += " and ( a.status ="+Common.sqlChar(getStatusType());
			hql += " or a.status ='20' )";
		}
		else
		{
		    hql += " and a.status ="+Common.sqlChar(getStatusType());
		}
		
		if("1".equals(caseType))
		{
			hql += " and eventKind like '%1%' ";
		}
		else if("2".equals(caseType))
		{
			hql += " and eventKind like '%2%' ";
		}
		
		
		if((!"".equals(getQ_applNoS())) || !"".equals(getQ_applNoE()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS()) + "and applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if((!"".equals(getQ_occurDateS())) || !"".equals(getQ_occurDateE()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS()) + "and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		
		if(!"".equals(getQ_medPermit()))
			hql += "and medPermit = " + Common.sqlChar(getQ_medPermit());
		
		if(!"".equals(getQ_medPermitNumber()))
			hql += "and medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		
//		if(!"".equals(getQ_occurDateS()))
//			hql += " and a.occurDate >= " + Common.sqlChar(getQ_occurDateS());
//		
//		if(!"".equals(getQ_occurDateE()))
//			hql += " and a.occurDate <= " + Common.sqlChar(getQ_occurDateE());
//		
//		if(!"".equals(getQ_medPermit()))
//			hql += " and a.medPermit = " + Common.sqlChar(getQ_medPermit());
//		
//		if(!"".equals(getQ_medPermitNumber()))
//			hql += " and a.medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		
		
		
		System.out.println("[TCBW]-[MEDEX5201Q]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			//objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id desc", this.getRecordStart(), this.getPageSize());
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by a.id desc");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS1");

				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				
				for(Object dtlObj : objList)
				{
					Med2001Db dtl = (Med2001Db)dtlObj;
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = Common.get(dtl.getFdaNum());
				
					if(!"".equals(Common.get(dtl.getMedPermit())))
						rowArray[4] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
						rowArray[4] ="";
					rowArray[5] = Common.get(dtl.getMedTestMedical());
					rowArray[6] = Common.get(medMainCategoryMap.get(dtl.getMedMainCategory()));
					rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	public String getQuerylist( boolean primaryArray[], boolean displayArray[], String[] arrAlign, java.util.ArrayList objList, String queryAllFlag, boolean withListNo, String noDataMsg, boolean checkHtml) {
    	int counter = 0;
    	boolean even = false;	
    	StringBuilder sb = new StringBuilder();
    	if(objList!=null && objList.size()>0){
			String rowArray[] = new String[primaryArray.length];
			
			java.util.Iterator it = objList.iterator();
			while(it.hasNext()){
				rowArray = (String[])it.next();
				counter++;				
				String classTR = "listTROdd";
				String classTD = "listTDOdd";				
				if(even){
					classTR = "listTREven";
					classTD = "listTDEven";
				}				
			
				String pk = "";
				for(int i=0; i<primaryArray.length; i++){			
					if(primaryArray[i]){
						pk += Common.escapeReturnChar(rowArray[i]);
					}
				}
				boolean trFlag = false;
				StringBuilder v = new StringBuilder();
				for(int i=0; i<primaryArray.length; i++){
					if (primaryArray[i]) {
						if(trFlag){
							v.append(",'").append(Common.escapeReturnChar(rowArray[i])).append("'");
						}else{
							v.append("'").append(Common.escapeReturnChar(rowArray[i])).append("'");
							trFlag = true;
						}
					}
				}
				sb.append(" <tr id='").append("listContainerRow").append(pk).append("'");
				sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');updateData(").append(v).append(")\" >\n");
				
				// 顯示TD
				if(withListNo){
					sb.append(" <td nowrap class='").append(classTD).append("' >").append(counter).append(".</td>\n");			
				}
				for(int i=0; i<displayArray.length; i++){
					if(displayArray[i]){
						if(arrAlign!=null && arrAlign.length>0){
							sb.append(" <td nowrap class='").append(classTD).append("' style=\"text-align:").append(arrAlign[i]).append("\">"); 
						}else{
							sb.append(" <td nowrap class='").append(classTD).append("' >");
						}
						
						sb.append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i]));
						sb.append("</td>\n");
					}
				}
				
				// BUTTON
				sb.append(" <td nowrap class='").append(classTD).append("'>");
				sb.append(" <input type='button' class='toolbar_default' name='btn" + counter + "' value='" + "明　細"  + "' ");
			//	if(Common.getInt(rowArray[isDisabled]) > 90){
			//		sb.append("disabled");
			//	}
				sb.append(" onClick=\"").append("listContainerRowClick('").append(pk).append("');")
				  .append("updateData(").append(v).append(");\"> ");
				sb.append("</td>\n");
				sb.append("</tr>\n");
				even = !even;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td nowrap class='listTD' colspan='100'>").append("".equals(Common.get(noDataMsg))?"查無資料，請您重新輸入查詢條件！":noDataMsg).append("</td></tr>");
    	}
    	//System.out.println("SB: " + sb.toString());
		return sb.toString();
		
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
	
	public static String getCheckboxlist( ArrayList objList,String queryAllFlag ) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
    }
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
	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}
	public void setQ_notifierRepDateS(String qNotifierRepDateS) {
		q_notifierRepDateS = checkSet(qNotifierRepDateS);
	}
	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}
	public void setQ_notifierRepDateE(String qNotifierRepDateE) {
		q_notifierRepDateE = checkSet(qNotifierRepDateE);
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
	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}
	public void setQ_notifierRevDateS(String qNotifierRevDateS) {
		q_notifierRevDateS = checkSet(qNotifierRevDateS);
	}
	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}
	public void setQ_notifierRevDateE(String qNotifierRevDateE) {
		q_notifierRevDateE = checkSet(qNotifierRevDateE);
	}
	public String getQ_caseSource() {
		return checkGet(q_caseSource);
	}
	public void setQ_caseSource(String qCaseSource) {
		q_caseSource = checkSet(qCaseSource);
	}
	public String getQ_medPermit() {
		return checkGet(q_medPermit);
	}
	public void setQ_medPermit(String qMedPermit) {
		q_medPermit = checkSet(qMedPermit);
	}
	public String getQ_medPermitNumber() {
		return checkGet(q_medPermitNumber);
	}
	public void setQ_medPermitNumber(String qMedPermitNumber) {
		q_medPermitNumber = checkSet(qMedPermitNumber);
	}
	public String getQ_medMainCategory() {
		return checkGet(q_medMainCategory);
	}
	public void setQ_medMainCategory(String qMedMainCategory) {
		q_medMainCategory = checkSet(qMedMainCategory);
	}
	public String getQ_medSecCategory() {
		return checkGet(q_medSecCategory);
	}
	public void setQ_medSecCategory(String qMedSecCategory) {
		q_medSecCategory = checkSet(qMedSecCategory);
	}
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}
	public String getDoType() {
		return checkGet(doType);
	}
	public void setDoType(String doType) {
		this.doType = checkSet(doType);
	}
	public String getIsInOrOutPerson() {
		return checkGet(isInOrOutPerson);
	}
	public void setIsInOrOutPerson(String isInOrOutPerson) {
		this.isInOrOutPerson = checkSet(isInOrOutPerson);
	}
	public String getInOrOut() {
		return checkGet(inOrOut);
	}
	public void setInOrOut(String inOrOut) {
		this.inOrOut = checkSet(inOrOut);
	}
	public String getId() {
		return checkGet(id);
	}
	public void setId(String id) {
		this.id = checkSet(id);
	}
	public String getStatusType() {
		return checkGet(statusType);
	}
	public void setStatusType(String statusType) {
		this.statusType = checkSet(statusType);
	}
	
	
	

}
