package com.kangdainfo.tcbw.view.drgex;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6002Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGEX0302F extends DRGEX0301F{
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	private String updateType;//接收前端按鈕狀態
	public String getUpdateType() {		return checkGet(updateType);	}
	public void setUpdateType(String updateType) {		this.updateType = checkSet(updateType);	}
	
	private String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}

	private String isNeedBackQuery;
	public String getIsNeedBackQuery() {return checkGet(isNeedBackQuery);}
	public void setIsNeedBackQuery(String isNeedBackQuery) {this.isNeedBackQuery = checkSet(isNeedBackQuery);}
	
	private String isEnabledUpdate;
	public String getIsEnabledUpdate() {return checkGet(isEnabledUpdate);}
	public void setIsEnabledUpdate(String isEnabledUpdate) {this.isEnabledUpdate = checkSet(isEnabledUpdate);}
	
	private String formType;
	public String getFormType() {return checkGet(formType);}
	public void setFormType(String s) {this.formType = checkSet(s);}
	
	private String tabId;
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String s) {this.tabId = checkSet(s);}

	private String applNo;	//案件號碼	VARCHAR(11)
	private String revision;  //版次	VARCHAR(2)
	private String status;	//案件狀態	VARCHAR(2)
	private String chargeMan;	//作業人員	VARCHAR(50)
	private String enrolledDate;	//收案日期	VARCHAR(7)
	private String inOrOutcreator;	//案件擁有者	VARCHAR(50)
	
	//基本資料------------------------------------------------------------------
	private String occurDate;	//發生日期	VARCHAR(7)
	private String notifierRevDate;	//通報者接獲日期	VARCHAR(7)
	private String notifierRepDate;	//通報中心接獲通報日期	VARCHAR(7)
	private String notifierSource;	//通報來源	VARCHAR(2)
	private String notifierUserID; // 通報者ID
	private String notifierName;	//通報者姓名	NVARCHAR(20)
	private String notifierDeptID, notifierDept;	//通報者服務機構	NVARCHAR(50)
	private String notifierTelArea;
	private String notifierTel;	//通報者電話	VARCHAR(10)
	private String notifierTelExt;
	private String notifierPhone;	//通報者手機	VARCHAR(10)
	private String notifierCounty;
	private String notifierAddress;	//通報者地址	NVARCHAR(100)
	private String notifierZipCode;
	private String notifierEmail;	//通報者電子信箱	VARCHAR(50)
	private String notifierFaxArea;
	private String notifierFax;	//通報者傳真	VARCHAR(10)
	private String notifierType;	//通報者屬性	VARCHAR(2)
	private String notifierTitle;	//通報者職稱	VARCHAR(2)
    private String patientId;	//病人識別代號
    private String patientSex;	//病人性別
    private String patientBirth;  //病人出生日期
    private String patientAge;	//病人年齡
    private String patientHeight;	//病人身高
    private String patientWeight;	//病人體重

    //療效不等反應------------------------------------------------------------------
    private String[] conSequence;	//通報事件後果
    private String effectChangeDesc;	//通報事件後果-藥效改變狀況    
    private String badReactionLev;	//通報事件後果-不良反應等級
    private String badReactionDesc;	//通報事件後果-不良反應狀況
    private String beforeDesc;	//事件前描述
    private String changeDesc;	//藥品轉換描述
    private String occurDesc;	//發生事件描述
    private String afterDesc;	//事件後描述
    private String otherDesc;	//其他相關資料
    private String dealWith;	//發生事件後之處置
    private String dealWithOther;	//發生事件後之處置其他
    private String isImproveYn;	//病人恢復原藥或轉換同成分藥品其症狀是否改善
    private String isContactYn;	//提供聯絡資訊供廠商後續調查評估
    private String dressingAttitude;	//醫師對換藥的態度
    private String obedienceLev;	//病人服藥順從性
    
    
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getRevision() {return checkGet(revision);}
	public void setRevision(String revision) {this.revision = checkSet(revision);}
	public String getStatus() {return checkGet(status);}
	public void setStatus(String status) {this.status = checkSet(status);}
	public String getChargeMan() {return checkGet(chargeMan);}
	public void setChargeMan(String chargeMan) {this.chargeMan = checkSet(chargeMan);}
	public String getEnrolledDate() {return checkGet(enrolledDate);}
	public void setEnrolledDate(String enrolledDate) {this.enrolledDate = checkSet(enrolledDate);}
	public String getInOrOutcreator() {return checkGet(inOrOutcreator);}
	public void setInOrOutcreator(String inOrOutcreator) {this.inOrOutcreator = checkSet(inOrOutcreator);}
	public String getOccurDate() {return checkGet(occurDate);}
	public void setOccurDate(String occurDate) {this.occurDate = checkSet(occurDate);}
	public String getNotifierRevDate() {return checkGet(notifierRevDate);}
	public void setNotifierRevDate(String notifierRevDate) {this.notifierRevDate = checkSet(notifierRevDate);}
	public String getNotifierRepDate() {return checkGet(notifierRepDate);}
	public void setNotifierRepDate(String notifierRepDate) {this.notifierRepDate = checkSet(notifierRepDate);}
	public String getNotifierSource() {return checkGet(notifierSource);}
	public void setNotifierSource(String notifierSource) {this.notifierSource = checkSet(notifierSource);}
	public String getNotifierUserID() {return checkGet(notifierUserID);}
	public void setNotifierUserID(String s) {this.notifierUserID = checkSet(s);}
	public String getNotifierName() {return checkGet(notifierName);}
	public void setNotifierName(String notifierName) {this.notifierName = checkSet(notifierName);}
	public String getNotifierDept() {return checkGet(notifierDept);}
	public void setNotifierDept(String notifierDept) {this.notifierDept = checkSet(notifierDept);}
	public String getNotifierDeptID() {	return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {	this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierTelArea() {return checkGet(notifierTelArea);}
	public void setNotifierTelArea(String notifierTelArea) {this.notifierTelArea = checkSet(notifierTelArea);}
	public String getNotifierTel() {return checkGet(notifierTel);}
	public void setNotifierTel(String notifierTel) {this.notifierTel = checkSet(notifierTel);}
	public String getNotifierTelExt() {return checkGet(notifierTelExt);}
	public void setNotifierTelExt(String notifierTelExt) {this.notifierTelExt = checkSet(notifierTelExt);}
	public String getNotifierPhone() {return checkGet(notifierPhone);}
	public void setNotifierPhone(String notifierPhone) {this.notifierPhone = checkSet(notifierPhone);}
	public String getNotifierCounty() {return checkGet(notifierCounty);}
	public void setNotifierCounty(String notifierCounty) {this.notifierCounty = checkSet(notifierCounty);}
	public String getNotifierAddress() {return checkGet(notifierAddress);}
	public void setNotifierAddress(String notifierAddress) {this.notifierAddress = checkSet(notifierAddress);}
	public String getNotifierZipCode() {return checkGet(notifierZipCode);}
	public void setNotifierZipCode(String notifierZipCode) {this.notifierZipCode = checkSet(notifierZipCode);}
	public String getNotifierEmail() {return checkGet(notifierEmail);}
	public void setNotifierEmail(String notifierEmail) {this.notifierEmail = checkSet(notifierEmail);}
	public String getNotifierFaxArea() {return checkGet(notifierFaxArea);}
	public void setNotifierFaxArea(String notifierFaxArea) {this.notifierFaxArea = checkSet(notifierFaxArea);}
	public String getNotifierFax() {return checkGet(notifierFax);}
	public void setNotifierFax(String notifierFax) {this.notifierFax = checkSet(notifierFax);}
	public String getNotifierType() {return checkGet(notifierType);}
	public void setNotifierType(String notifierType) {this.notifierType = checkSet(notifierType);}
	public String getNotifierTitle() {return checkGet(notifierTitle);}
	public void setNotifierTitle(String notifierTitle) {this.notifierTitle = checkSet(notifierTitle);}
	
	public String getPatientId() {return checkGet(patientId);}
	public void setPatientId(String patientId) {this.patientId = checkSet(patientId);}
	public String getPatientSex() {	return checkGet(patientSex);}
	public void setPatientSex(String patientSex) {this.patientSex = checkSet(patientSex);}
	public String getPatientBirth() {return checkGet(patientBirth);}
	public void setPatientBirth(String patientBirth) {this.patientBirth = checkSet(patientBirth);}
	public String getPatientAge() {return checkGet(patientAge);}
	public void setPatientAge(String patientAge) {this.patientAge = checkSet(patientAge);}
	public String getPatientHeight() {return checkGet(patientHeight);}
	public void setPatientHeight(String patientHeight) {this.patientHeight = checkSet(patientHeight);}
	public String getPatientWeight() {return checkGet(patientWeight);}
	public void setPatientWeight(String patientWeight) {this.patientWeight = checkSet(patientWeight);}
	public String[] getConSequence() {return conSequence;}
	public void setConSequence(String[] conSequence) {this.conSequence = conSequence;}
	public String getEffectChangeDesc() {return checkGet(effectChangeDesc);}
	public void setEffectChangeDesc(String effectChangeDesc) {this.effectChangeDesc = checkSet(effectChangeDesc);}
	public String getBadReactionLev() {return checkGet(badReactionLev);}
	public void setBadReactionLev(String badReactionLev) {this.badReactionLev = checkSet(badReactionLev);}
	public String getBadReactionDesc() {return checkGet(badReactionDesc);}
	public void setBadReactionDesc(String badReactionDesc) {this.badReactionDesc = checkSet(badReactionDesc);}
	public String getBeforeDesc() {return checkGet(beforeDesc);}
	public void setBeforeDesc(String beforeDesc) {this.beforeDesc = checkSet(beforeDesc);}
	public String getChangeDesc() {return checkGet(changeDesc);}
	public void setChangeDesc(String changeDesc) {this.changeDesc = checkSet(changeDesc);}
	public String getOccurDesc() {return checkGet(occurDesc);}
	public void setOccurDesc(String occurDesc) {this.occurDesc = checkSet(occurDesc);}
	public String getAfterDesc() {return checkGet(afterDesc);}
	public void setAfterDesc(String afterDesc) {this.afterDesc = checkSet(afterDesc);}
	public String getOtherDesc() {return checkGet(otherDesc);}
	public void setOtherDesc(String otherDesc) {this.otherDesc = checkSet(otherDesc);}
	public String getDealWith() {return checkGet(dealWith);}
	public void setDealWith(String dealWith) {this.dealWith = checkSet(dealWith);}
	public String getDealWithOther() {return checkGet(dealWithOther);}
	public void setDealWithOther(String dealWithOther) {this.dealWithOther = checkSet(dealWithOther);}
	public String getIsImproveYn() {return checkGet(isImproveYn);}
	public void setIsImproveYn(String isImproveYn) {this.isImproveYn = checkSet(isImproveYn);}
	public String getIsContactYn() {return checkGet(isContactYn);}
	public void setIsContactYn(String s) {this.isContactYn = checkSet(s);}
	public String getDressingAttitude() {return checkGet(dressingAttitude);}
	public void setDressingAttitude(String dressingAttitude) {this.dressingAttitude = checkSet(dressingAttitude);}
	public String getObedienceLev() {return checkGet(obedienceLev);}
	public void setObedienceLev(String obedienceLev) {this.obedienceLev = checkSet(obedienceLev);}
	
	//==========  其他相關檢查及檢驗數據資訊 =================
	String[] drg62Row;
	public String[] getDrg62Row() {return drg62Row;}
	public void setDrg62Row(String[] drg62Row) {this.drg62Row = drg62Row;}
	public final String[] arrDrg62FieldName = {"testDate","testItems","testNum"};
	String drg62JSBuilder;
	public String getDrg62JSBuilder() {
		if (drg62JSBuilder!=null) return drg62JSBuilder;
		else return "";
	}
	public void setDrg62JSBuilder(String drg62JSBuilder) {this.drg62JSBuilder = drg62JSBuilder;}
	public String genDrg6002DbSet(java.util.Set dtlSet) throws Exception {
	    if (dtlSet!=null && dtlSet.size()>0) {
	    	StringBuilder sb = new StringBuilder(1024);  
	    	int j=0;
	    	for (Object dtlObj : dtlSet) {
	    		Drg6002Db dtl = (Drg6002Db) dtlObj;
	    		sb.append("addDrg62Row('drg62Table'");
				for (j=0; j<arrDrg62FieldName.length; j++) {
					sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg62FieldName[j])))));
				}        
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDrg62JSBuilder(sb.toString());
	    	return sb.toString();    	
	    } else if (httpRequest!=null && this.getDrg62Row()!=null && this.getDrg62Row().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i=0; i<getDrg62Row().length; i++) {
				String rid = getDrg62Row()[i];
				sb.append("addDrg62Row('drg62Table'");
				for (int j=0; j<arrDrg62FieldName.length; j++) {				
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg62FieldName[j] + rid)),true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(checkGet(org.apache.commons.lang.StringEscapeUtils.escapeHtml(httpRequest.getParameter("drg62Id" + rid)))).append("'");
				sb.append(");\n");
				
			}
			this.setDrg62JSBuilder(sb.toString());
			return sb.toString();
		}
	    return "";
	}
	
	//==========  使用藥品 =================
	String[] drg63Row;
	public String[] getDrg63Row() {return drg63Row;}
	public void setDrg63Row(String[] drg63Row) {this.drg63Row = drg63Row;}
	public final String[] arrDrg63FieldName = {"medType","permitKey","permitNo","scientificName","productName","applicationName","manufactorName","manufactorNo","content","medModel","medPath","dosage","frequency","startDare","endDate","indication","manufactorID","medModelOther","medPathOther","frequencyOther"};
	String drg63JSBuilder;
	public String getDrg63JSBuilder() {
		if (drg63JSBuilder!=null) return drg63JSBuilder;
		else return "";
	}
	public void setDrg63JSBuilder(String drg63JSBuilder) {this.drg63JSBuilder = drg63JSBuilder;}
	public String genDrg6003DbSet(java.util.Set dtlSet) throws Exception {
	    if (dtlSet!=null && dtlSet.size()>0) {
	    	StringBuilder sb = new StringBuilder(1024);  
	    	int j=0;
	    	for (Object dtlObj : dtlSet) {
	    		Drg6003Db dtl = (Drg6003Db) dtlObj;
	    		sb.append("addDrg63Row").append(dtl.getMedType()).append("('drg63Table").append(dtl.getMedType()).append("'");
				for (j=0; j<arrDrg63FieldName.length; j++) {
					sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg63FieldName[j])))));
				}        
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDrg63JSBuilder(sb.toString());
	    	return sb.toString();    	
	    }
	    return "";
	}
	

	//========== 附件上傳 =================
	String[] conRow;
	public String[] getConRow() {return conRow;}
	public void setConRow(String[] conRow) {this.conRow = conRow;}
	public final String[] arrConFieldName = {"fileName","fileExplan","fileRoute"};
	String conJSBuilder;
	public String getConJSBuilder() {
		if (conJSBuilder!=null) return conJSBuilder;
		else return "";
	}
	public void setConJSBuilder(String conJSBuilder) {this.conJSBuilder = conJSBuilder;}
	public String genCon0001DbSet(Long drg4001Id) throws Exception {
		String hql = " from Con0001Db where fileKind='DRG' and systemType = 'DRG020001' and dbName='Drg6001Db' and upLoadId="+drg4001Id;
		List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
	    if (null != conList && !conList.isEmpty()) {
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for (Con0001Db dtl : conList) {       		
	    		sb.append("addConRow('conTable'");
				for (int j=0; j<arrConFieldName.length; j++) {
					if(j == 2){
						String attFile = Common.get(dtl.getFileRoute())+":;:"+Common.get(dtl.getFileName());
						sb.append(",").append(Common.sqlChar(attFile));
					}else{
						sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl, arrConFieldName[j])),true)));
					}
				}        
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setConJSBuilder(sb.toString());
	    	System.out.println(sb.toString());
	    	return sb.toString();    	
	    }
	    return "";
	}
	
	// FOR 新增登入該頁面時，自動新增一筆資料
	public void doInsert()throws Exception
	{
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(c == null)
		{
			c = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			c.setCommonDepartment(d);
			System.out.println("[TCBW]-[DRGEX0102F]-[新增]-[無法辨別登入的使用者]");
		}

		Drg6001Db obj = new Drg6001Db();
		obj.setStatus("00");
		
		obj.setInOrOutcreator(c.getUserId());
		//基本資料------------------------------------------------------------------
		obj.setNotifierUserID(Common.get(c.getUserId()));
		obj.setNotifierName(Common.get(c.getUserName()));
		obj.setNotifierDeptID(Common.get(c.getUserJob()));		
		obj.setNotifierDept(Common.get(TCBWCommon.getNotifierDeptName(c.getCommonDepartment().getDepartmentCode(),c.getUserJob())));
		obj.setNotifierTelArea(Common.get(c.getUserTelArea()));
		obj.setNotifierTel(Common.get(c.getUserTel()));
		obj.setNotifierTelExt(Common.get(c.getUserTelExt()));
		obj.setNotifierCounty(Common.get(c.getUserCounty()));
		obj.setNotifierZipCode(Common.get(c.getUserZipCode()));
		obj.setNotifierAddress(Common.get(c.getUserAddr()));
		obj.setNotifierEmail(Common.get(c.getUserEmail()));
		obj.setNotifierType(Common.get(c.getCommonDepartment().getDepartmentCode()));
		obj.setNotifierTitle(Common.get(c.getJobTitle()));
		obj.setNotifierFax(Common.get(c.getUserFax()));
		obj.setNotifierFaxArea(Common.get(c.getUserFaxArea()));
		obj.setNotifierPhone(Common.get(c.getUserMobile()));
		obj.setRevision("1");
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		//先預設新增使用藥品的資料
		
		//先預設新增檢驗數據資料
		java.util.Set dtl62Set = new ListOrderedSet();
		Drg6002Db drg62 = new Drg6002Db();
		drg62.setDrg6001Db(obj);
		drg62.setCreator(c.getUserId());
		drg62.setCreateDate(Datetime.getYYYMMDD());
		drg62.setCreateTime(Datetime.getHHMMSS());
		dtl62Set.add(drg62);
		obj.setDrg6002Dbs(dtl62Set);
		
		java.util.List<CommonCode> list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0307");
		if(null != list && !list.isEmpty()){
			java.util.Set dtlSet = new ListOrderedSet();
			for(CommonCode code:list){
				Drg6003Db dtl = new Drg6003Db();
				dtl.setDrg6001Db(obj);
				dtl.setMedType(code.getCodeId());
				dtl.setCreator(c.getUserId());
				dtl.setCreateDate(Datetime.getYYYMMDD());
				dtl.setCreateTime(Datetime.getHHMMSS());
				dtlSet.add(dtl);
			}
			obj.setDrg6003Dbs(dtlSet);
		}
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	public void doUpdateType() throws Exception{
		DRGEX0302F obj = ServiceGetter.getInstance().getTcbwService().getDrgexDao().updateByDrgEX0302F(this);
		setId(Common.get(obj.getId()));
	}	

	public DefaultTableModel getDefaultTableModel(List<Drg4001Db> drg41List) throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		String[] cols = new String[] { "id", "applNo", "status", "chargeMan", //0~3
				"occurDate", "notifierRevDate", "notifierRepDate",	//4~6
				"notifierSource", "notifierName", "notifierDept",	//7~9
				"notifierTel", "address", "notifierEamil", "notifierFax",	//10~13
				"notifierType", "notifierTitle", "patientId", "patientSex",	//14~17
				"patientBDate", "patientAge", "patientHeight", "patientWeight",	//18~21
				"conSequence", "beforeDesc", "changeDesc", "occurDesc",	//22~25
				"afterDesc", "otherDesc","permitKey1",  //26~28
				"scientificName1", "productName1", "applicationName1",	//29~31
				"manufactorName1", "manufactorNo1", "content1", "medModel1",	//32~35
				"medPath1", "dosage1", "frequency1", "startDare1", "endDate1",	//36~40
				"indication1", "permitKey2","scientificName2", //41~43
				"productName2", "applicationName2", "manufactorName2",	//44~46
				"manufactorNo2", "content2", "medModel2", "medPath2",	//47~50
				"dosage2", "frequency2", "startDare2", "endDate2",	//51~54
				"indication2","obj1","detail1","detail2","isprint1","isprint2",	//55~60
				"permitKey3","scientificName3","productName3", "applicationName3",//61~64
				"manufactorName3","manufactorNo3", "content3", "medModel3", "medPath3",	//65~69
				"dosage3", "frequency3", "startDare3", "endDate3",	//70~73
				"indication3","dealWith","isImproveYn","dressingAttitude","obedienceLev"//74~78

		};
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		java.util.List list = null;
		if(null == drg41List || drg41List.isEmpty()){
			String hql = "from Drg6001Db where 1=1 ";

			if (!"".equals(getId()))
				hql += "and id = " + Common.getLong(getId());
			if (!"".equals(getApplNo()))
				hql += "and applNo = " + Common.sqlChar(getApplNo());
			System.out.println("HQL: " + hql);
			list = ServiceGetter.getInstance().getCommonService().load(hql);
		}else{
			list = drg41List;
		}
		
		int dt1 = 0;
		int dt2 = 0;
		
		if (list != null && list.size() > 0) {
			
			java.util.Map<String, String> medModelMap = TCBWCommon.getCommonCodeMap("DRGFLN");
			
			for (int i = 0; i < list.size(); i++) {
				Drg6001Db obj = (Drg6001Db) list.get(i);
				Object rowArray[] = new Object[cols.length];

				rowArray[0] = Common.get(obj.getId());
				rowArray[1] = Common.get(obj.getApplNo());
				rowArray[2] = Common.get(obj.getStatus());
				rowArray[3] = Common.get(obj.getChargeMan());
				rowArray[4] = Common.formatYYYMMDD(obj.getOccurDate(), 2);
				rowArray[5] = Common.formatYYYMMDD(obj.getNotifierRevDate(), 2);
				rowArray[6] = Common.formatYYYMMDD(obj.getNotifierRepDate(), 2);
				rowArray[7] = getNotifierSource(Common.get(obj.getNotifierSource()));
				rowArray[8]=Common.get(obj.getNotifierName());
				rowArray[9]=Common.get(obj.getNotifierDept());
				rowArray[10]=Common.get(obj.getNotifierTel());
				rowArray[11]=Common.get(obj.getNotifierAddress());
				rowArray[12]=Common.get(obj.getNotifierEmail());
				rowArray[13]=Common.get(obj.getNotifierFax());
				rowArray[14] = Common.get(obj.getNotifierType());
				rowArray[15] = getNotifierTitle(Common.get(obj.getNotifierTitle()));
				rowArray[16] = Common.get(obj.getPatientId());
				if ("M".equals(Common.get(obj.getPatientSex()))) {
					rowArray[17] = "■男　□女";
				} else if ("F".equals(Common.get(obj.getPatientSex()))) {
					rowArray[17] = "□男　■女";
				} else {
					rowArray[17] = "□男　□女";
				}

				rowArray[18] = Common.formatYYYMMDD(obj.getPatientBirth());
				rowArray[19] = Common.get(obj.getPatientAge());
				rowArray[20] = Common.get(obj.getPatientHeight());
				rowArray[21] = Common.get(obj.getPatientWeight());				

				if ("1".equals(Common.get(obj.getConSequence()))) {
					String effectChangeDesc = "";
					if ("1".equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "■增強　□減弱";
					} else if ("2"
							.equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "□增強　■減弱";
					} else {
						effectChangeDesc += "□增強　□減弱";
					}
					rowArray[22] = "■藥效改變 -- "
							+ effectChangeDesc
							+ "\n□不良反應發生、強度增加或頻率增加(不良反應：_____)\n--□嚴重不良反應　□非嚴重不良反應\n--□可預期不良反應　□非預期不良反應";
				} else if ("2".equals(Common.get(obj.getConSequence()))) {
					String effectChangeDesc = "□增強　□減弱";
					String badReactionLev = "";
					badReactionLev = getBadReactionLev(badReactionLev,
							Common.get(obj.getBadReactionLev()));
					String badReactionDesc = "";
					if (!"".equals(Common.get(obj.getBadReactionDesc()))) {
						badReactionDesc += "(不良反應："
								+ Common.get(obj.getBadReactionDesc()) + ")";
					} else {
						badReactionDesc += "(不良反應：______)";
					}
					rowArray[22] = "□藥效改變 -- " + effectChangeDesc
							+ "\n■不良反應發生、強度增加或頻率增加　" + badReactionDesc + "\n"
							+ badReactionLev;
				} else if(!"".equals(Common.get(obj.getConSequence()))) {
					if(obj.getConSequence().length() > 1) {  //通報事件之後果：藥效改變與不良反應發生同時被勾選
						String effectChangeDesc = "■藥效改變 -- ";
						if ("1".equals(Common.get(obj.getEffectChangeDesc()))) {
							effectChangeDesc += "■增強　□減弱";
						} else if ("2"
								.equals(Common.get(obj.getEffectChangeDesc()))) {
							effectChangeDesc += "□增強　■減弱";
						} else {
							effectChangeDesc += "□增強　□減弱";
						}
						effectChangeDesc += "\n■不良反應發生、強度增加或頻率增加";
						String badReactionDesc = "";
						if (!"".equals(Common.get(obj.getBadReactionDesc()))) {
							badReactionDesc += "(不良反應："
									+ Common.get(obj.getBadReactionDesc()) + ")";
						} else {
							badReactionDesc += "(不良反應：______)";
						}
						String badReactionLev = "";
						badReactionLev = getBadReactionLev(badReactionLev,
								Common.get(obj.getBadReactionLev()));
						effectChangeDesc += badReactionDesc + "\n" + badReactionLev;
						
						rowArray[22] = effectChangeDesc;
					}
				}
					
				else {
					rowArray[22] = "□　藥效改變 -- □增強　□減弱\n□　不良反應發生、強度增加或頻率增加　(不良反應：________)\n" +
					"--□嚴重不良反應　□非嚴重不良反應\n--□可預期不良反應　□非預期不良反應";
				}

				rowArray[23] = Common.get(obj.getBeforeDesc());
				rowArray[24] = Common.get(obj.getChangeDesc());
				rowArray[25] = Common.get(obj.getOccurDesc());
				rowArray[26] = Common.get(obj.getAfterDesc());
				rowArray[27] = Common.get(obj.getOtherDesc());
//				rowArray[28] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
//				if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "■是　□否　□不知";
//				} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "□是　■否　□不知";
//				} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "□是　□否　■不知";
//				} else {
//					rowArray[29] = "□是　□否　□不知";
//				}
//				
//				rowArray[30] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
//				rowArray[31] = getObedienceLev(Common.get(obj.getObedienceLev()));
				
				if(obj.getDrg6003Dbs()!=null && obj.getDrg6003Dbs().size()>0) {
					java.util.Iterator it2 = obj.getDrg6003Dbs().iterator();
					while(it2.hasNext())
					{
						Drg6003Db drg6003Db = (Drg6003Db)it2.next();
						if("01".equals(Common.get(drg6003Db.getMedType()))) {
							if(getPermitKey(Common.get(drg6003Db.getPermitKey())).length() > 0 ) {
								rowArray[28]=getPermitKey(Common.get(drg6003Db.getPermitKey()))+"字";
								if(Common.get(drg6003Db.getPermitNo()).length() > 0) {
									rowArray[28]=rowArray[28]+"第"+Common.get(drg6003Db.getPermitNo())+"號";
								}
							} else {
								rowArray[28]="";
							}
//							rowArray[28]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字"+"第"+Common.get(drg4003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[29]=Common.get(drg6003Db.getScientificName());
							rowArray[30]=Common.get(drg6003Db.getProductName());
							rowArray[31]=Common.get(drg6003Db.getApplicationName());
							rowArray[32]=Common.get(drg6003Db.getManufactorName());
							rowArray[33]=Common.get(drg6003Db.getManufactorNo());
							rowArray[34]=Common.get(drg6003Db.getContent());
							rowArray[35]=Common.get(medModelMap.get(drg6003Db.getMedModel()));
							rowArray[36]=Common.get(drg6003Db.getMedPath());
							rowArray[37]=Common.get(drg6003Db.getDosage());
							rowArray[38]=Common.get(drg6003Db.getFrequency());
							rowArray[39]=Common.get(drg6003Db.getStartDare());
							rowArray[40]=Common.get(drg6003Db.getEndDate());
							rowArray[41]=Common.get(drg6003Db.getIndication());
						}
						if("02".equals(Common.get(drg6003Db.getMedType()))) {
							String permitKey1=getPermitKey(Common.get(drg6003Db.getPermitKey()));
							rowArray[42]=getPermitKey(Common.get(drg6003Db.getPermitKey()))+"字第"+ Common.get(drg6003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[43]=Common.get(drg6003Db.getScientificName());
							rowArray[44]=Common.get(drg6003Db.getProductName());
							rowArray[45]=Common.get(drg6003Db.getApplicationName());
							rowArray[46]=Common.get(drg6003Db.getManufactorName());
							rowArray[47]=Common.get(drg6003Db.getManufactorNo());
							rowArray[48]=Common.get(drg6003Db.getContent());
							rowArray[49]=Common.get(medModelMap.get(drg6003Db.getMedModel()));
							rowArray[50]=Common.get(drg6003Db.getMedPath());
							rowArray[51]=Common.get(drg6003Db.getDosage());
							rowArray[52]=Common.get(drg6003Db.getFrequency());
							rowArray[53]=Common.get(drg6003Db.getStartDare());
							rowArray[54]=Common.get(drg6003Db.getEndDate());
							rowArray[55]=Common.get(drg6003Db.getIndication());
						}
						if("03".equals(Common.get(drg6003Db.getMedType()))) {
							String permitKey1=getPermitKey(Common.get(drg6003Db.getPermitKey()));
							rowArray[61]=getPermitKey(Common.get(drg6003Db.getPermitKey()))+"字第"+ Common.get(drg6003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[62]=Common.get(drg6003Db.getScientificName());
							rowArray[63]=Common.get(drg6003Db.getProductName());
							rowArray[64]=Common.get(drg6003Db.getApplicationName());
							rowArray[65]=Common.get(drg6003Db.getManufactorName());
							rowArray[66]=Common.get(drg6003Db.getManufactorNo());
							rowArray[67]=Common.get(drg6003Db.getContent());
							rowArray[68]=Common.get(medModelMap.get(drg6003Db.getMedModel()));
							rowArray[69]=Common.get(drg6003Db.getMedPath());
							rowArray[70]=Common.get(drg6003Db.getDosage());
							rowArray[71]=Common.get(drg6003Db.getFrequency());
							rowArray[72]=Common.get(drg6003Db.getStartDare());
							rowArray[73]=Common.get(drg6003Db.getEndDate());
							rowArray[74]=Common.get(drg6003Db.getIndication());
						}
						else {
							rowArray[61]="";//許可證字+許可證號
							rowArray[62]="";
							rowArray[63]="";
							rowArray[64]="";
							rowArray[65]="";
							rowArray[66]="";
							rowArray[67]="";
							rowArray[68]="";
							rowArray[69]="";
							rowArray[70]="";
							rowArray[71]="";
							rowArray[72]="";
							rowArray[73]="";
							rowArray[74]="";
						}
						rowArray[75] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
						if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "■是　□否　□不知";
						} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "□是　■否　□不知";
						} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "□是　□否　■不知";
						} else {
							rowArray[76] = "□是　□否　□不知";
						}
						
						rowArray[77] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
						rowArray[78] = getObedienceLev(Common.get(obj.getObedienceLev()));
						
						//16.其他併用藥品
//						if("03".equals(Common.get(drg4003Db.getMedType()))) {
//							java.util.Iterator it3 = obj.getDrg4003Dbs().iterator();
//							rowArray[61] = new JRTableModelDataSource(getSubModel02(it3));
//						}
					}
				}
				
				if(obj.getDrg6002Dbs()!=null && obj.getDrg6002Dbs().size()>0) {
					java.util.Iterator it2 = obj.getDrg6002Dbs().iterator();
					rowArray[56] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[56]=null;
				}
//				rowArray[57] = new JRTableModelDataSource(getDetail1Model(obj));
//				rowArray[58] = new JRTableModelDataSource(getDetail2Model(obj));
//				rowArray[59] = "tmp" + dt1;
//				rowArray[60] = "tmp" + dt2;
				rowArray[57] = "";
				rowArray[58] = "";
				rowArray[59] = "";
				rowArray[60] = "";
				arrList.add(rowArray);
			}
		}
		if (arrList != null && arrList.size() > 0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][]) arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
	}
	//子報表路徑設定
	public void setParameter(java.util.HashMap<String, Object> params)
	{
		//傳給報表的查詢條件參數,在此設定
		String detail1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_detail1.jasper");
		String detail2FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_detail2.jasper");
		String subreport0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_subreport0.jasper");
		String subreport1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_subreport1.jasper");
		
		params.put("detail1", detail1FilePath);
		params.put("detail2", detail2FilePath);
		params.put("subreport0", subreport0FilePath);			
		params.put("subreport1", subreport1FilePath);
	}
	
	//16.其他併用藥品
	public DefaultTableModel getDetail1Model(Drg6001Db obj) throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{"obj2"};

		Object[] rowArray = new Object[cols.length];
		
		if(obj.getDrg6003Dbs()!=null && obj.getDrg6003Dbs().size()>0) {
			java.util.Iterator it3 = obj.getDrg6003Dbs().iterator();
			rowArray[0] = new JRTableModelDataSource(getSubModel02(it3));
//			java.util.Iterator it2 = obj.getDrg4003Dbs().iterator();
//			while(it2.hasNext())
//			{
//				Drg4003Db drg4003Db = (Drg4003Db)it2.next();
//				if("03".equals(Common.get(drg4003Db.getMedType()))) {
//					
//					
//				}
//			}
			
		} else {
			rowArray[0] = null;
		}
		arrList.add(rowArray);
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, cols);
		}
		System.out.println("其他併用藥品： " + model.getDataVector());
		return model;
	}
	//Ⅳ.其他相關資訊
	public DefaultTableModel getDetail2Model(Drg6001Db obj) throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{"dealWith","isImproveYn","dressingAttitude","obedienceLev"};

		Object[] rowArray = new Object[cols.length];
		
		rowArray[0] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
		if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "■是　□否　□不知";
		} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "□是　■否　□不知";
		} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "□是　□否　■不知";
		} else {
			rowArray[1] = "□是　□否　□不知";
		}
		
		rowArray[2] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
		rowArray[3] = getObedienceLev(Common.get(obj.getObedienceLev()));
		arrList.add(rowArray);
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, cols);
		}
//		System.out.println("其他相關資訊： " + model.getDataVector());
		return model;
	}
	//子報表-相關檢查,檢驗數據及其他資料
	public DefaultTableModel getSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"testDate","testItems","testNum"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Drg6002Db drg6002Db = (Drg6002Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(drg6002Db.getTestDate(),2);
			rowArray[1]=Common.get(drg6002Db.getTestItems());
			rowArray[2]=Common.get(drg6002Db.getTestNum());
			
			arrList.add(rowArray);
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//子報表-其他併用藥品
	public DefaultTableModel getSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{ "permitKey","scientificName",
				"productName", "applicationName", "manufactorName",
				"manufactorNo", "content", "medModel", "medPath",
				"dosage", "frequency", "startDare", "endDate",
				"indication"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Drg6003Db drg6003Db = (Drg6003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=getPermitKey(Common.get(drg6003Db.getPermitKey()))+"字第"+ Common.get(drg6003Db.getPermitNo())+"號";//許可證字+許可證號
			rowArray[1]=Common.get(drg6003Db.getScientificName());
			rowArray[2]=Common.get(drg6003Db.getProductName());
			rowArray[3]=Common.get(drg6003Db.getApplicationName());
			rowArray[4]=Common.get(drg6003Db.getManufactorName());
			rowArray[5]=Common.get(drg6003Db.getManufactorNo());
			rowArray[6]=Common.get(drg6003Db.getContent());
			rowArray[7]=Common.get(drg6003Db.getMedModel());
			rowArray[8]=Common.get(drg6003Db.getMedPath());
			rowArray[9]=Common.get(drg6003Db.getDosage());
			rowArray[10]=Common.get(drg6003Db.getFrequency());
			rowArray[11]=Common.get(drg6003Db.getStartDare());
			rowArray[12]=Common.get(drg6003Db.getEndDate());
			rowArray[13]=Common.get(drg6003Db.getIndication());
			
			if("03".equals(drg6003Db.getMedType())){
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
	        System.out.println("其他併用藥品： " + model.getDataVector());
		}
        return model;
	}	

	// Common_CodeKind查詢條件
	public String getCommonCodeKindHQL(String codeKindId) {
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId
				+ "'";
		return HQL;
	}

	// 通報來源
	public String getNotifierSource( String NotifierSource) {
		String hql = getCommonCodeKindHQL("26");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(NotifierSource)) {
					checkbox = "■" + obj.getCodeName();

				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				if ("03".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";

				}

			}
		}
		return rowArray2;
	}

	// 通報者職稱
	public String getNotifierTitle(String NotifierTitle) {
		String hql = getCommonCodeKindHQL("2");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(NotifierTitle)) {
					checkbox = "■" + obj.getCodeName();

				} else {
					checkbox = checkbox + obj.getCodeName();
				}

				if ("09".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";
				}

			}
		}
		return rowArray2;
	}

	// 不良反應等級
	public String getBadReactionLev(String rowArray2, String BadReactionLev) {
		String hql = getCommonCodeKindHQL("101");
		rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (i == 0 || i == 2) {
					checkbox = "--" + checkbox;
				}

				if (obj.getCodeId().equals(BadReactionLev)) {
					checkbox = "■" + obj.getCodeName();
					if (i == 0 || i == 2) {
						checkbox = "--" + checkbox;
					}
					if (i == 1 || i == 3) {
						checkbox += "\n";
					}
				} else {
					checkbox = checkbox + obj.getCodeName();

					if (i == 1 || i == 3) {
						checkbox += "\n";
					}
				}

				if ("02".equals(obj.getCodeId())) {
					rowArray2 += checkbox;
				} else {
					rowArray2 += checkbox + "　";
				}
			}
		}
		return rowArray2;
	}

	// 發生事件後之處置
	public String getDealWith(String DealWith,String DealwithOther) {
		String hql = getCommonCodeKindHQL("35");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(DealWith)) {
					checkbox = "■" + obj.getCodeName();
					if ("ZZ".equals(obj.getCodeId())
							&& !"".equals(DealwithOther)) {
						checkbox += DealwithOther;
					} else if ("ZZ".equals(obj.getCodeId())) {
						checkbox += "_____";
					}
				} else {
					checkbox = checkbox + obj.getCodeName();
					if ("ZZ".equals(obj.getCodeId())) {
						checkbox += "_____";
					}
				}

				if ("05".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";
				}
			}
		}
		return rowArray2;
	}

	// 醫師對換藥的態度
	public String getDressingAttitude(String DressingAttitude) {
		String hql = getCommonCodeKindHQL("36");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(DressingAttitude)) {
					checkbox = "■" + obj.getCodeName();
				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox + "　";
			}
		}
		return rowArray2;
	}

	// 病人服藥順從性
	public String getObedienceLev(String ObedienceLev) {
		String hql = getCommonCodeKindHQL("37");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(ObedienceLev)) {
					checkbox = "■" + obj.getCodeName();
				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox + "　";
			}
		}
		return rowArray2;
	}
	
	//許可證字
	public String getPermitKey(String Permitkey) {
		String hql = getCommonCodeKindHQL("25");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";
				if (obj.getCodeId().equals(Permitkey)) {
					checkbox = obj.getCodeName();
				}
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGEX0302F obj = this;
		obj.setIsEnabledUpdate("Y");
		
		Drg6001Db c = (Drg6001Db) View.getObject(" from Drg6001Db where id=" + Common.getInt(obj.getId()));
		
		System.out.println("[TCBW]-[DRGEX0302F]-[doQueryOne] : " + obj.getId());
		
		if (c!=null) {		
			//通報訊息
			obj.setApplNo(c.getApplNo());
			obj.setRevision(c.getRevision());
			obj.setStatus(c.getStatus());
			obj.setOccurDate(c.getOccurDate());								//通報日期
			obj.setNotifierRepDate(c.getNotifierRepDate());					//通報者接獲日期
			obj.setNotifierRevDate(c.getNotifierRevDate());					//通報中心接獲日期
			obj.setNotifierSource(c.getNotifierSource());					//原始藥物不良事件獲知來源
			
			//通報者資訊
			obj.setNotifierUserID(c.getNotifierUserID());
			obj.setNotifierName(c.getNotifierName());						//姓名
			obj.setNotifierDept(Common.get(c.getNotifierDept()));
			obj.setNotifierDeptID(Common.get(c.getNotifierDeptID()));		//服務機構
			String jobName = "";
			if ("02".equals(Common.get(c.getNotifierType()))){	
				jobName = View.getLookupField("select name from Con1005Db where id="+Common.getLong(c.getNotifierDept()));		
			}		
			else if ("03".equals(Common.get(c.getNotifierType()))){			
				jobName = View.getLookupField("select medAgencyName from Con1009Db where id="+Common.getLong(c.getNotifierDept()));		
			}		
			else if ("04".equals(Common.get(c.getNotifierType()))){			
				jobName = View.getLookupField("select unionName from Con1003Db where id="+Common.getLong(c.getNotifierDept()));	
			}
			
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel());							//電話
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierEmail(c.getNotifierEmail());						//E-Mail
			obj.setNotifierPhone(c.getNotifierPhone());						//手機
			obj.setNotifierFax(c.getNotifierFax());							//傳真
			obj.setNotifierFaxArea(c.getNotifierFaxArea());
			obj.setNotifierCounty(c.getNotifierCounty());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setNotifierAddress(c.getNotifierAddress());					//地址
			obj.setNotifierTitle(c.getNotifierTitle());						//職稱
			obj.setNotifierType(c.getNotifierType());						//屬性
		
			//病人相關資料
			obj.setPatientId(c.getPatientId());								//識別代碼
			obj.setPatientSex(c.getPatientSex());							//性別
			obj.setPatientBirth(c.getPatientBirth());						//出生日期
			obj.setPatientAge(c.getPatientAge());							//年齡
			obj.setPatientHeight(c.getPatientHeight());						//身高
			obj.setPatientWeight(c.getPatientWeight());						//體重
			
			//療效不等反應
			obj.setConSequence(Common.get(c.getConSequence()).split(","));				//通報事件後果
			obj.setEffectChangeDesc(c.getEffectChangeDesc());				//通報事件後果-藥效改變狀況
			obj.setBadReactionLev(c.getBadReactionLev());					//通報事件後果-不良反應等級
			obj.setBadReactionDesc(c.getBadReactionDesc());					//通報事件後果-不良反應狀況
			obj.setBeforeDesc(c.getBeforeDesc());							//事件前描述
			obj.setChangeDesc(c.getChangeDesc());							//藥品轉換描述
			obj.setOccurDesc(c.getOccurDesc());								//發生事件描述
			obj.setAfterDesc(c.getAfterDesc());								//事件後描述
			obj.setOtherDesc(c.getOtherDesc());								//其他相關資料
			obj.setDealWith(c.getDealWith());								//發生事件後之處置
			obj.setDealWithOther(c.getDealWithOther());						//發生事件後之處置其他
			obj.setIsImproveYn(c.getIsImproveYn());							//病人恢復原藥或轉是同成分藥品其症狀是否改善
			obj.setIsContactYn(c.getIsContactYn());							//提供聯絡資訊供廠商後續調查評估
			obj.setDressingAttitude(c.getDressingAttitude());				//醫師對換藥的態度
			obj.setObedienceLev(c.getObedienceLev());						//病人服藥順從性
			obj.setDrg62JSBuilder(this.genDrg6002DbSet(c.getDrg6002Dbs())); 
			obj.setDrg63JSBuilder(this.genDrg6003DbSet(c.getDrg6003Dbs())); 
			obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));
		}
		return obj;
	}

	
	public Object doQueryAll() throws Exception {		
		return null;
	}

	public void doDelete() throws Exception {
		Drg6001Db obj = (Drg6001Db)View.getObject(" from Drg6001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}		
	}
	
}
