package com.kangdainfo.tcbw.view.medex;


import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDEX0101F extends SuperBean
{
	private String caseType;
	private String id;//序號	NUMERIC(19,0)
	private String doType;
	private String isInOrOutPerson;
    private String inOrOut;//分內外網
    private String talbe3;
    private String talbe4;
    


	private String q_applNoS;//案件編號起
	private String q_applNoE;//案件編號訖
	private String q_occurDateS;//通報日期起
	private String q_occurDateE;//通報日期訖
	private String q_medPermit;//懷疑的醫療器材-許可證字號-字
	private String q_medPermitNumber;//懷疑的醫療器材-許可證字號-號
	private String q_fdaNum;//核准文號
	private String q_medTestMedical;//醫材品名
	private String q_status;//狀態
	


	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med4001Db a where 1 = 1 ";

		if("out".equals(getInOrOut()))
		{	
		  hql += " and inOrOutcreator = " + Common.sqlChar(getUserID());
		}
		else if("in".equals(getInOrOut()))
		{	
		  hql += " and creator= " + Common.sqlChar(getUserID());
		}
		if(!"".equals(getQ_status())) {
			if("xx".equals(getQ_status())) {
				hql += " and status not in('00','01','02','03','30')";
			} else if("30".equals(getQ_status())) {
				hql += " and ( status='30' or status='02' )";
			} else {
				hql += " and status = " + Common.sqlChar(getQ_status());
			}
		}

		
		if((!"".equals(getQ_applNoS())) || !"".equals(getQ_applNoE()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS()) + "and applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if((!"".equals(getQ_occurDateS())) || !"".equals(getQ_occurDateE()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS()) + "and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		
		if(!"".equals(getQ_medPermit()))
			hql += "and medPermit = " + Common.sqlChar(getQ_medPermit());
		
		
		if(!"".equals(getQ_medPermitNumber()))
			hql += "and medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		
		if(!"".equals(getQ_fdaNum()))
			hql += "and fdaNum = " + Common.sqlChar(getQ_fdaNum());
		
		if(!"".equals(getQ_medTestMedical()))
			hql += "and medCname like " + Common.sqlChar("%" + getQ_medTestMedical() + "%");

		
		//若有相同案號，僅顯示最新一筆
		if(!"2".equals(getQ_status()) && !"30".equals(getQ_status()))
		{
			hql+=" and ( id in ( select max(id) from Med4001Db b where a.med0001ID = b.med0001ID and applNo != null  group by applNo )"+
		     "  or id in ( select max(id) from Med4001Db b where a.applNo = b.applNo and applNo is null group by id) )";
		}
		System.out.println("MEDEX0101F=="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				for(Object dtlObj : objList) 
				{				
					Med4001Db dtl = (Med4001Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());	
					
					String no="";
					if(!"".equals(Common.get(dtl.getApplNo())))
					{
						no=Common.get(dtl.getApplNo());
					}
					
					rowArray[1] = Common.get(no);		
					rowArray[2] = Common.get(dtl.getNotifierRevDate());	
					
					if(!"".equals(Common.get(dtl.getMedPermit())))
					   rowArray[3] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
					   rowArray[3] ="";
					rowArray[4] = Common.get(dtl.getMedCname());
					rowArray[5] = Common.get(medMainCategoryMap.get(dtl.getMedMainCategoryCode()));
					//rowArray[6] = Common.get(statusMap.get(dtl.getStatus()));
					
					rowArray[6] = "";
					if(StringUtils.contains("00,01,02,03", dtl.getStatus()))
					{
						rowArray[6] = Common.get(statusMap.get(dtl.getStatus()));
					}
					else if(StringUtils.contains("30", dtl.getStatus()))
					{
						rowArray[6] = "補件中";
					}
					else
					{
						rowArray[6] = "通報完成";
					}
					
					rowArray[7] = Common.get(dtl.getApplNo());
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
		return sb.toString();
    }

	public String getCaseType() {
		return checkGet(caseType);
	}

	public void setCaseType(String caseType) {
		this.caseType = checkSet(caseType);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
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

	public String getQ_fdaNum() {
		return checkGet(q_fdaNum);
	}

	public void setQ_fdaNum(String qFdaNum) {
		q_fdaNum = checkSet(qFdaNum);
	}

	public String getQ_medTestMedical() {
		return checkGet(q_medTestMedical);
	}

	public void setQ_medTestMedical(String qMedTestMedical) {
		q_medTestMedical = checkSet(qMedTestMedical);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}

	public String getTalbe3() {
		return checkGet(talbe3);
	}

	public void setTalbe3(String talbe3) {
		this.talbe3 = checkSet(talbe3);
	}

	public String getTalbe4() {
		return checkGet(talbe4);
	}

	public void setTalbe4(String talbe4) {
		this.talbe4 = checkSet(talbe4);
	}

	

	


	

}
