package com.kangdainfo.tcbw.view.medex;



import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class MEDEX0104F extends MEDEX0101F
{
	javax.servlet.ServletRequest httpRequest;
	private String isNeedBackQuery;//
	private String isEnabledUpdate;
	private String updateType;//接收前端按鈕狀態
	private String inOrOutcreator;//案件擁有者
	private String notifierUserID;//內網選擇外網帳號
	private String commonUser;
	
	private String applNo;//案件號碼	VARCHAR(20)
	private String applNo1;//案件關聯號碼	VARCHAR(20)
	
	private String status;//案件狀態	VARCHAR(2)
	
	private String statusCh;//案件狀態中文    
	private String statusCh1;//案件關聯狀態中文    
	
	private String med0001ID;
	
	private String changeTabValue;
	
	//通報訊息--------------------------------------------------
	private String occurDate;//發生日期	VARCHAR(7)
	private String notifierRevDate;//通報者獲知日期	VARCHAR(7)
	private String notifierRepDate;//通報中心接獲通報日期	VARCHAR(7)
	private String drugEventsSources;//原始藥物不良事件獲知來源	VARCHAR(2)
	private String medicalStaff;//原始藥物不良事件獲知來源-由醫療人員轉知-選項	VARCHAR(2)
	private String medicalStaffOther;//原始藥物不良事件獲知來源-由醫療人員轉知-其他說明	NVARCHAR(30)
	private String healthUnits;//原始藥物不良事件獲知來源-由衛生單位得知-選項	VARCHAR(2)
	private String healthUnitsOther;//原始藥物不良事件獲知來源-由衛生單位得知-其他說明	NVARCHAR(30)
	private String caseSource;//案例來源	VARCHAR(4)
	private String caseSourceOutCountry;//案例來源-國外-國家	NVARCHAR(20)
	private String reportKind;//報告類別	VARCHAR(2)
	private String trackingNum;//報告類別-追蹤報告-次數	VARCHAR(2)
	private String correctiveAction;//報告類別-矯正措施	VARCHAR(2)
	private String attachment;//附件	VARCHAR(2)
	private String attachmentYnum;//附件-有-件數	VARCHAR(2)
	private String drugSafetyMonitoring;//產品經公告列入藥物安全監視	VARCHAR(2)
	
	//通報者資訊--------------------------------------------------
	private String notifierName;//通報者-姓名	NVARCHAR(10)
	private String notifierAreaCode;//通報者-區域號碼	VARCHAR(3)
	private String notifierTel;//通報者-電話	    VARCHAR(10)
	private String notifierTelExt;//通報者-分機 	VARCHAR(5)	
	private String notifierAddress;//通報者-地址	NVARCHAR(50)
	private String notifierCounty;	//通報者縣市	VARCHAR(2)
	private String notifierZipCode;	//通報者地區	VARCHAR(5)
	private String notifierEamil;//通報者-電子郵件	VARCHAR(30)
	private String notifierType;//通報者-屬性	VARCHAR(2)
	private String notifierStaffHospital;//通報者-醫療人員-醫院名稱	NVARCHAR(30)
	private String notifierStaffTitle;//通報者-醫療人員-職稱	VARCHAR(2)
	private String notifierStaffTitleOther;//通報者-醫療人員-職稱-其他描述	NVARCHAR(10)
	private String notifierFirmDept;//通報者-廠商-服務機構	NVARCHAR(30)
	private String isContactYn;//是否提供聯絡資料以助廠商分析不良事件	VARCHAR(1)
	private String notifierDeptID;//通報者-廠商-服務機構
    private String notifierDept;
	
	//病人相關資料--------------------------------------------------
	private String badReactionPatientCode;//不良反應相關資料-病人識別代號	VARCHAR(10)
	private String badReactionSex;//不良反應相關資料-性別	VARCHAR(1)
	private String badReactionBirthday;//不良反應相關資料-出生日期	VARCHAR(7)
	private String badReactionAge;//不良反應相關資料-年齡	VARCHAR(3)
	private String badReactionWeight;//不良反應相關資料-體重	VARCHAR(3)
	private String badReactionHeight;//不良反應相關資料-身高	VARCHAR(3)
	
	//不良事件類別--------------------------------------------------
	private String[] eventKind;//事件類別	VARCHAR(1)
	
	
	private String medPermit;//懷疑的醫療器材-許可證字號-字	VARCHAR(10)
	private String medPermitNumber;//懷疑的醫療器材-許可證字號-號	VARCHAR(20)
	private String medPermitFirmCode;//懷疑的醫療器材-許可證申請商代碼	NVARCHAR(30)
	private String medPermitFirm;//懷疑的醫療器材-許可證申請商	NVARCHAR(30)
	private String medCname;//懷疑的醫療器材-中文品名	NVARCHAR(30)
	
	private String medMainCategoryCode;//懷疑的醫療器材-醫材主類別	VARCHAR(10)
	private String medMainCategory;//懷疑的醫療器材-醫材主類別	VARCHAR(30)
	private String medMainCategoryCodeName;//
	private String medSecCategoryCode;//懷疑的醫療器材-醫材次類別	VARCHAR(10)
	private String medSecCategory;//懷疑的醫療器材-醫材次類別	VARCHAR(30)
	private String medSecCategoryCodeName;//
	
	private String medFactory;//懷疑的醫療器材-製造廠	NVARCHAR(30)
	private String medCountr;//懷疑的醫療器材-製造國別	NVARCHAR(20)
	
	private String medModel;//懷疑的醫療器材-型號	VARCHAR(10)
	private String medNo;//懷疑的醫療器材-序號	VARCHAR(10)
	private String medLotNum;//懷疑的醫療器材-批號	VARCHAR(10)
	private String medSoftwareVersion;//懷疑的醫療器材-軟體版本	VARCHAR(10)
	private String medManufactureDate;//懷疑的醫療器材-製造日期	VARCHAR(7)
	private String medEffectiveDate;//懷疑的醫療器材-有效日期/保存期間	VARCHAR(7)
	private String medPurchaseDate;//懷疑的醫療器材-採購日期	VARCHAR(7)
	private String medUseDate;//懷疑的醫療器材-使用日期	VARCHAR(7)
	private String medUseReason;//懷疑的醫療器材-使用原因	NVARCHAR(30)
	
	//不良反應相關資料--------------------------------------------------
	private String badReactionResults;//不良反應結果	VARCHAR(2)
	private String badReactionResultsDeathDate;//不良反應結果-A死亡-日期	VARCHAR(7)
	private String badReactionResultsDeathReason;//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
	private String badReactionResultsOther;//不良反應結果-G非嚴重不良反應-請敘述	NVARCHAR(30)
	private String medOperator;//醫療器材操作者	VARCHAR(2)
	private String medDisposalStatus;//器材處置現況	VARCHAR(2)
	private String medDisposalStatusDate;//器材處置現況-尚植於病患體內-日期	VARCHAR(7)
	private String medUse;//器材使用	VARCHAR(2)
	private String medUseOther;//器材使用-其他	NVARCHAR(30)
	private String onceUseMed;//曾使用同類醫材之經驗	VARCHAR(2)
	private String onceUseMedName;//曾使用同類醫材之經驗-是-醫材名稱	NVARCHAR(20)
	private String onceUseMedOther;//曾使用同類醫材之經驗-是-若發生不良反應請描述	NVARCHAR(30)
	private String stopMedMitigate;//本次事件停用醫材後，症狀是否減輕	VARCHAR(2)
	private String sameReaction;//承上，再使用是否出現同樣反應 	VARCHAR(2)
	private String otherRelatedData;//其他相關資料	NVARCHAR(255)
	
	private String[] productProblemKind1;//產品問題分類	VARCHAR(2)
	private String[] productProblemKind2;//產品問題分類	VARCHAR(2)
	private String[] productProblemKind3;//產品問題分類	VARCHAR(2)
	private String[] productProblemKind4;//產品問題分類	VARCHAR(2)
	private String productProblemKindOther;//產品問題分類-其他	NVARCHAR(30)
	
	private String defProductDescription;//不良品缺陷描述	NVARCHAR(255)
	private String defProductOtherDescription;//不良品缺陷描述	NVARCHAR(255)
	
	//不良反應--不良事件描述-------------------------------------------------------------------
	private String bulletinDate[];//日期	VARCHAR(7)
	private String position[];//發生不良反應之部位	NVARCHAR(30)
	private String symptom[];//症狀	NVARCHAR(30)
	private String severity[];//嚴重程度	NVARCHAR(30)
	private String dealWith[];//處置	NVARCHAR(30)
	private String med4002DbType[];
	private String med4002DbTypeId[];
	
	//不良反應--相關檢查及檢驗數據	-------------------------------------------------------------------
	private String testDate[];//檢驗日期	VARCHAR(7)
	private String testItems[];//檢驗項目	NVARCHAR(30)
	private String testNum[];//檢驗數據	NVARCHAR(30)
	private String med4003DbType[];
	private String med4003DbTypeId[];
	
	//不良反應--併用醫療器材	-------------------------------------------------------------------
	private String ccname[];//品名		    NVARCHAR(30)
	private String permit[];//許可證字  	NVARCHAR(30)
	private String permitNumber[];//許可證號	NVARCHAR(30)
	private String permitFirm[];//許可證申請商	NVARCHAR(30)
	private String model[];//型號		NVARCHAR(20)
	private String mainCategory[];//器材主類別		NVARCHAR(20)
	private String mainCategoryCode[];//器材主類別		NVARCHAR(20)
	private String useDate[];//使用日期	VARCHAR(7)
	private String useReason[];//使用原因	NVARCHAR(50)
	private String med4004DbType[];
	private String med4004DbTypeId[];
	
	//不良反應--併用藥品	-------------------------------------------------------------------
	private String cName2[];//學名/商品名		    NVARCHAR(30)
	private String content[];//含量  	NVARCHAR(30)
	private String formulation[];//劑型	NVARCHAR(30)
	private String drgApproach[];//給藥途徑	NVARCHAR(30)
	private String dose[];//劑量		NVARCHAR(20)
	private String frequency[];//使用頻率		NVARCHAR(10)
	private String sDate[];//起日期	VARCHAR(7)
	private String eDate[];//迄日期	VARCHAR(7)
	private String medCauses[];//用藥原因	NVARCHAR(50)
	private String med4005DbType[];
	private String med4005DbTypeId[];
	
	// FOR 新增登入該頁面時，自動新增一筆資料
	public void doInsert()throws Exception
	{
		System.out.println("=======================doInsert================");
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		
		if(c == null)
		{
			c = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			c.setCommonDepartment(d);
			System.out.println("[TCBW]-[MEDEX0102F]-[新增]-[無法辨別登入的使用者]");
		}
		
		Med4001Db obj = new Med4001Db();
		
		obj.setStatus("00");
		obj.setRevision("01");
		obj.setNotifierName(c.getUserName());
		
		obj.setNotifierAreaCode(c.getUserTelArea());
		obj.setNotifierTel(c.getUserTel());
		obj.setNotifierTelExt(c.getUserTelExt());
		obj.setNotifierEamil(c.getUserEmail());
		
		
		obj.setNotifierAddress(c.getUserAddr());
		obj.setNotifierCounty(c.getUserCounty());
	    obj.setNotifierZipCode(c.getUserZipCode());
		
		obj.setNotifierDeptID(c.getUserJob());
		obj.setNotifierDept(Common.get(TCBWCommon.getNotifierDeptName(c.getCommonDepartment().getDepartmentCode(),c.getUserJob())));
		obj.setNotifierType(c.getCommonDepartment()!=null?Common.get(c.getCommonDepartment().getDepartmentCode()):"");
		
		
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		
		
		String splitString ="";
			
		if(!"".equals(getTalbe3()))
		{
			splitString+=getTalbe3();
		}
		
		if(!"".equals(getTalbe4()))
		{
			if(!"".equals(getTalbe3()))
			   splitString+=",";
			
			splitString+=getTalbe4();
		}
		
	    obj.setEventKind(splitString);


		obj.setInOrOutcreator(c.getUserId());

		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	//暫存、待上傳、送出
	public void doUpdateType() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedex1Dao().updateByMedEX0104F(this);
	}
	
	//主動補件
	public void doAutoReUpdate() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedex1Dao().updateByAutoReUpdateMedEX0104F(this,"Y");
	}
	
	//案件列印
	public DefaultTableModel getDefaultTableModel() throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","occurDate","notifierRevDate","notifierRepDate","notifierName",//0~6
    			"notifierAreaCode","notifierTel","notifierAddress","notifierEamil","notifierType","notifierStaffHospital",//7~12
    			"notifierStaffTitle","notifierDept","isContactYn","drugEventsSources","medicalStaff",//13~17
    			"healthUnits","caseSource","reportKind","trackingNum","correctiveAction","attachment","attachmentYnum",//18~24
    			"drugSafetyMonitoring","medCname","medPermit","medPermitNumber","medPermitFirm","medMainCategory",//25~30
    			"medSecCategory","medCountry","medFactory","medModel","medNo","medLotNum","medSoftwareVersion",//31~37
    			"medManufactureDate","medEffectiveDate","medPurchaseDate","medUseDate","medUseReason","eventKind",//38~43
    			"badReactionPatientCode","badReactionSex","badReactionBirthday","badReactionAge","badReactionWeight",//44~48
    			"badReactionHeight","badReactionResults","obj1","isprint1","detail1"//49~51
    	        };
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		
		String hql = "from Med4001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		int dt1 = 0;
		if (list!=null && list.size()>0) 
		{
			java.util.Map<String, String> countryMap = TCBWCommon.getCommonCodeMap("COUC");
			java.util.Map<String, String> cityMap = TCBWCommon.getCommonCodeMap("CTY");//縣市別
			java.util.Map<String, String> areaMap = TCBWCommon.getMap("select zipcode, zipname from Con1002Db ");//郵遞區號
			java.util.Map<String, String> medpkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//許可證-字
			
			for (int i=0; i<list.size(); i++) 
			{
				Med4001Db obj = (Med4001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2); 
				rowArray[6]=Common.get(obj.getNotifierName());
				rowArray[7]=Common.get(obj.getNotifierAreaCode());
				rowArray[8]=Common.get(obj.getNotifierTel());
				rowArray[9]=Common.get(obj.getNotifierAddress());
				rowArray[10]=Common.get(obj.getNotifierEamil());
				
				if("03".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="■ 醫療人員\n\n□ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]=Common.get(obj.getNotifierStaffHospital());
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("02".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n■ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("01".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n■ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("04".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n□ 民眾\n■ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				}
				rowArray[14]=Common.get(obj.getNotifierDept());

				
				if("Y".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[15]="■願意　　□不願意";
				} else {
					rowArray[15]="□願意　　■不願意";
				}
				
				if("1".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="■由醫療人員轉知\n□由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else if("2".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n■由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					if("1".equals(Common.get(obj.getHealthUnits()))) {
						rowArray[18]="■衛生局(所)　□其他________";
					} else {
						rowArray[18]="□衛生局(所)　■其他" + Common.get(obj.getHealthUnitsOther());
					}
				} else if("3".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n■廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else if("4".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n□廠商\n■由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				}
				
				if("in".equals(Common.get(obj.getCaseSource()))) {
					rowArray[19]="■國內，或　□國外，";
				} else {
					rowArray[19]="□國內，或　■國外，" + Common.get(obj.getCaseSourceOutCountry());
				}
				
				if("1".equals(Common.get(obj.getReportKind()))) {
					rowArray[20]="■初始報告\n□追蹤報告";
					rowArray[21]="________";
				} else if("2".equals(Common.get(obj.getReportKind()))) {
					rowArray[20]="□初始報告\n■追蹤報告";
					rowArray[21]=Common.get(obj.getTrackingNum());
				} else {
					rowArray[20]="□初始報告\n□追蹤報告";
					rowArray[21]="________";
				}
				
				rowArray[22]=getMedTFO(Common.get(obj.getCorrectiveAction()));
				if("N".equals(Common.get(obj.getAttachment()))) {
					rowArray[23]="■無\n□有";
					rowArray[24]="______";
				} else if("Y".equals(Common.get(obj.getAttachment()))) {
					rowArray[23]="□無\n■有";
					rowArray[24]=Common.get(obj.getAttachmentYnum());
				}else {
					rowArray[23]="□無\n□有";
					rowArray[24]="______";
				}
				
				rowArray[25]=getMedTFO(Common.get(obj.getDrugSafetyMonitoring()));
				rowArray[26]=Common.get(obj.getMedCname());
				rowArray[27]=getPermitKey(Common.get(obj.getMedPermit()));
				rowArray[28]=Common.get(obj.getMedPermitNumber());
				rowArray[29]=Common.get(obj.getMedPermitFirm());
				rowArray[30]=Common.get(obj.getMedMainCategory());
				rowArray[31]=Common.get(obj.getMedSecCategory());
				rowArray[32]=Common.get(obj.getMedCountry());
				rowArray[33]=Common.get(obj.getMedFactory());
				rowArray[34]=Common.get(obj.getMedModel());
				rowArray[35]=Common.get(obj.getMedNo());
				rowArray[36]=Common.get(obj.getMedLotNum());
				rowArray[37]=Common.get(obj.getMedSoftwareVersion());
				rowArray[38]=Common.formatYYYMMDD(obj.getMedManufactureDate(),2);
				rowArray[39]=Common.formatYYYMMDD(obj.getMedEffectiveDate(),2);
				rowArray[40]=Common.formatYYYMMDD(obj.getMedPurchaseDate(),2);
				rowArray[41]=Common.formatYYYMMDD(obj.getMedUseDate(),2);
				rowArray[42]=Common.get(obj.getMedUseReason());
				
				if("1".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="■不良反應(請續填 Ⅱ-a)　　　□不良品(請續填Ⅱ-b)";
				} else if("2".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="□不良反應(請續填 Ⅱ-a)　　　■不良品(請續填Ⅱ-b)";
				} else if("1".equals(Common.get(obj.getEventKind())) && "2".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="■不良反應(請續填 Ⅱ-a)　　　■不良品(請續填Ⅱ-b)";
				}
				
				rowArray[44]=Common.get(obj.getBadReactionPatientCode());
				
				
				if("M".equals(Common.get(obj.getEventKind()))) {
					rowArray[45]="■男　　□女";
				} else if("F".equals(Common.get(obj.getEventKind()))) {
					rowArray[45]="□男　　■女";
				} else {
					rowArray[45]="□男　　□女";
				}
				
				rowArray[46]=Common.formatYYYMMDD(obj.getBadReactionBirthday(),2);
				rowArray[47]=Common.get(obj.getBadReactionAge());
				rowArray[48]=Common.get(obj.getBadReactionWeight());
				rowArray[49]=Common.get(obj.getBadReactionHeight());
				
				String DeathDateAndReason = "，日期：" + Common.formatYYYMMDD(obj.getBadReactionResultsDeathDate(),2) + "，死亡原因：" + Common.get(obj.getBadReactionResultsDeathReason()); //死亡日期與原因
				String BadReactionResultsOther = Common.get(obj.getBadReactionResultsOther());
				rowArray[50]=getCommonCodeKindOfBadReactionResults(Common.get(obj.getBadReactionResults()),DeathDateAndReason,BadReactionResultsOther);

				if(obj.getMed4002Dbs()!=null && obj.getMed4002Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed4002Dbs().iterator();
					rowArray[51] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[51]=null;
				}
				rowArray[52] = "tmp" + dt1;
				rowArray[53] = new JRTableModelDataSource(getDetail1Model(obj));

				arrList.add(rowArray);
			}
		}
		if (arrList!=null && arrList.size()>0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
		
	}
	
	//第二頁子報表
	 public DefaultTableModel getDetail1Model(Med4001Db obj) throws Exception
		{
			DefaultTableModel model = new javax.swing.table.DefaultTableModel();
			java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
			String[] cols = new String[]{"medOperator","medDisposalStatus","medUse","onceUseMed",//0~3
	    			"stopMedMitigate","sameReaction","otherRelatedData","productProblemKind1",//4~7
	    			"productProblemKind2","productProblemKind3","productProblemKind4",//8~10
	    			"productProblemKindOther","defProductDescription","obj2","obj3","obj4",//11~15
	    			"defProductOtherDescription"//16
	    			};

			Object[] rowArray = new Object[cols.length];
			
			rowArray[0]=getMedOperator(Common.get(obj.getMedOperator()));
			
			if("1".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="■ 已銷毀　□ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			} else if("2".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　■ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			} else if("3".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　■ 當植於病患體內　□於___年___月___日";
			} else if("4".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　□ 當植於病患體內　■於" + Common.formatYYYMMDD(obj.getMedDisposalStatusDate(),2);
			} else {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			}
			
			String MedUseOther = Common.get(obj.getMedUseOther());
			rowArray[2]=getMedUse(Common.get(obj.getMedUse()),MedUseOther);
			
			if("Y".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="■是，醫材名稱：" + Common.get(obj.getOnceUseMedName()) + " 若發生不良反應請描述：" + Common.get(obj.getOnceUseMedName()) + "\n□否　□無法得知";
			} else if("N".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n■否　□無法得知";
			} else if("O".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n□否　■無法得知";
			} else {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n□否　□無法得知";
			}

			rowArray[4]=getMedTFO(Common.get(obj.getStopMedMitigate()));
			rowArray[5]=getMedTFO(Common.get(obj.getSameReaction()));
			rowArray[6]=Common.get(obj.getOtherRelatedData());
			rowArray[7]=getProductProblemKind1(Common.get(obj.getProductProblemKind1()));
			rowArray[8]=getProductProblemKind2(Common.get(obj.getProductProblemKind2()));
			rowArray[9]=getProductProblemKind3(Common.get(obj.getProductProblemKind3()));
			rowArray[10]=getProductProblemKind4(Common.get(obj.getProductProblemKind4()));
			if(!"".equals(Common.get(obj.getProductProblemKindOther()))) {
				rowArray[11]=Common.get(obj.getProductProblemKindOther());
			} else {
				rowArray[11]="_______";

			}
			rowArray[12]=Common.get(obj.getDefProductDescription());


			if(obj.getMed4003Dbs()!=null && obj.getMed4003Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed4003Dbs().iterator();
				rowArray[13] = new JRTableModelDataSource(getSubModel02(it2));
			} else {
				rowArray[13]=null;
			}
			
			if(obj.getMed4004Dbs()!=null && obj.getMed4004Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed4004Dbs().iterator();
				
				rowArray[14] = new JRTableModelDataSource(getSubModel03(it2));
			}
			
			if(obj.getMed4005Dbs()!=null && obj.getMed4005Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed4005Dbs().iterator();
				rowArray[15] = new JRTableModelDataSource(getSubModel04(it2));
			}
			rowArray[16]=Common.get(obj.getDefProductOtherDescription());
			
			//-----------------------
			arrList.add(rowArray);
			if(null != arrList && arrList.size() >0)
			{
				Object[][] rs = new Object[0][0];
		        rs = (Object[][])arrList.toArray(rs);
		        model.setDataVector(rs, cols);
			}
			return model;
		}
	
	//設定子報表路徑
	 public void setParameter(java.util.HashMap<String, Object> params)
		{
			//傳給報表的查詢條件參數,在此設定
		 	String detail1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_detail1.jasper");
			String subreport0FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport0.jasper");
			String subreport1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport1.jasper");
			String subreport2FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport2.jasper");
			String subreport3FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport3.jasper");
	
			params.put("detail1", detail1FilePath);
			params.put("subreport0", subreport0FilePath);			
			params.put("subreport1", subreport1FilePath);	
			params.put("subreport2", subreport2FilePath);			
			params.put("subreport3", subreport3FilePath);
		}
	
	//子報表-不良事件描述資訊
	public DefaultTableModel getSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"bulletinDate","position","symptom","severity","dealWith"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med4002Db med4002Db = (Med4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			rowArray[0]=Common.formatYYYMMDD(med4002Db.getOccurDate(),2);
			rowArray[1]=Common.get(med4002Db.getPosition());
			rowArray[2]=Common.get(med4002Db.getSymptom());
			rowArray[3]=Common.get(med4002Db.getSeverity());
			rowArray[4]=Common.get(med4002Db.getDealWith());
			
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
	
	//子報表-相關檢查,檢驗數據及其他資料
	public DefaultTableModel getSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"testDate","testItems","testNum"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med4003Db med4003Db = (Med4003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(med4003Db.getTestDate(),2);
			rowArray[1]=Common.get(med4003Db.getTestItems());
			rowArray[2]=Common.get(med4003Db.getTestNum());
			
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
	
	//子報表-併用醫材
	public DefaultTableModel getSubModel03(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"cName","permitNumber","permitFirm","model",
				"mainCategory","useDate","useReason"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		
		while(it2.hasNext())
		{
			Med4004Db med4004db = (Med4004Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(med4004db.getcName());
			if(getPermitKey(Common.get(med4004db.getPermit())).length() > 0) {
				rowArray[1]=getPermitKey(Common.get(med4004db.getPermit()))+"字";
				if(Common.get(med4004db.getPermitNumber()).length() > 0) {
					rowArray[1] += "第"+Common.get(med4004db.getPermitNumber())+"號";
				}
			}
			else {
				rowArray[1] = "";
			}
			rowArray[2]=Common.get(med4004db.getPermitFirm());
			rowArray[3]=Common.get(med4004db.getModel());
			rowArray[4]=Common.get(med4004db.getMainCategory());
			rowArray[5]=Common.formatYYYMMDD(med4004db.getUseDate(),2);
			rowArray[6]=Common.get(med4004db.getUseReason());
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
	
	//子報表-併用藥品
	public DefaultTableModel getSubModel04(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"cName","content","formulation","drgApproach","dose",
    			"frequency","sDate","eDate","medCauses"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med4005Db med4005db = (Med4005Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med4005db.getcName());
			rowArray[1]=Common.get(med4005db.getContent());
			rowArray[2]=Common.get(med4005db.getFormulation());
			rowArray[3]=Common.get(med4005db.getDrgApproach());
			rowArray[4]=Common.get(med4005db.getDose());
			rowArray[5]=Common.get(med4005db.getFrequency());
			rowArray[6]=Common.formatYYYMMDD(med4005db.getsDate(),2);
			rowArray[7]=Common.formatYYYMMDD(med4005db.geteDate(),2);
			rowArray[8]=Common.get(med4005db.getMedCauses());
			arrList.add(rowArray);

		}

		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        System.out.println("併用藥品： " + model.getDataVector());

        return model;
	}	
	//Common_CodeKind查詢條件
	public String getCommonCodeKindHQL(String codeKindId) 
	{
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId + "'";
		return HQL;
	}
	
	//許可證字
	public String getPermitKey(String Permitkey) {
		String hql = getCommonCodeKindHQL("79");
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
//		System.out.println("PK: " + rowArray2);
		return rowArray2;
	}
	
	//是、否、無法得知
	public String getMedTFO(String MedUse) 
	{
		String hql = getCommonCodeKindHQL("16");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(MedUse)) 
				{
					checkbox = "■" + obj.getCodeName();

				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}

	//醫療器材操作者
	public String getMedOperator(String MedOperator) 
	{
		String hql = getCommonCodeKindHQL("8");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(MedOperator)) 
				{
					checkbox = "■" + obj.getCodeName();

				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	
	
	//職稱
	public String getNotifierStaffTitle(String NotifierStaffTitle,
			String NotifierStaffTitleOther) 
	{
		String hql = getCommonCodeKindHQL("6");

		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(NotifierStaffTitle)) 
				{
					checkbox = "■" + obj.getCodeName();
					if("5".equals(NotifierStaffTitle))
					{
						checkbox += NotifierStaffTitleOther;
					} 

				}
				else if("5".equals(obj.getCodeId()))
				{
					
					checkbox += obj.getCodeName() + "_____";
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
		
	}
	
	//不良反應結果
	public String getCommonCodeKindOfBadReactionResults
	(String BadReactionResults,String DeathDateAndReason,
			String BadReactionResultsOther) 
	{
		String hql = getCommonCodeKindHQL("20");
		String rowArray2 = "";
		String[] BadReactionResultsList = BadReactionResults.split(",");
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				
				String check = "□";
				String check2 = "□";
				for(int j=0; j<BadReactionResultsList.length; j++) 
				{
					if(obj.getCodeId().equals(BadReactionResultsList[j]))
					{
						check = "■";
						if("01".equals(BadReactionResultsList[j]))
						{
							check = check + obj.getCodeName() + DeathDateAndReason; //不良反應結果為死亡時帶入日期與死亡原因
						} else if("07".equals(BadReactionResultsList[j]))
						{
							check2 = "■";
							check2 = check2 + obj.getCodeName() + BadReactionResultsOther;
						}
					}
					
				}
				if(i == 0) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check + "\n";//不良反應結果為死亡時
					} else {
						rowArray2 += check + obj.getCodeName() +  "，日期________　死亡原因：________\n";//非死亡時
					}
					
				} else if(i == 6)
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check2 + "\n";//不良反應結果為非嚴重不良反應時
					} else {
						rowArray2 += check + obj.getCodeName() +  "________\n";//非嚴重不良反應時
					}
					
				}
				else 
				{
					rowArray2 += check + obj.getCodeName() + "\n";
				}
			}
		}
		return rowArray2;
	}
	
	//器材使用
	public String getMedUse(String MedUse,String MedUseOther) {
		String hql = getCommonCodeKindHQL("9");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(MedUse)) 
				{
					checkbox = "■" + obj.getCodeName();
					if("5".equals(MedUse))
					{
						checkbox += MedUseOther;
					}

				}
				else if("5".equals(obj.getCodeId()))
				{
					
					checkbox += obj.getCodeName() + "__________";
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	//產品問題分類-器材操作
	public String getProductProblemKind1(String ProductProblemKind1) {
		String hql = getCommonCodeKindHQL("10");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind1)) 
				{
					checkbox = "■" + obj.getCodeName();
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	//產品問題分類-器材操作
	public String getProductProblemKind2(String ProductProblemKind2) {
		String hql = getCommonCodeKindHQL("11");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind2)) 
				{
					checkbox = "■" + obj.getCodeName();
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	//產品問題分類-器材操作
	public String getProductProblemKind3(String ProductProblemKind3) {
		String hql = getCommonCodeKindHQL("12");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind3)) 
				{
					checkbox = "■" + obj.getCodeName();
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	//產品問題分類-器材操作
	public String getProductProblemKind4(String ProductProblemKind4) {
		String hql = getCommonCodeKindHQL("13");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind4)) 
				{
					checkbox = "■" + obj.getCodeName();
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	@Override
	public Object doQueryOne() throws Exception 
	{
	  //撰寫
	  MEDEX0104F obj = this;
	  Med4001Db c = (Med4001Db)View.getObject(" from Med4001Db where id = " + Common.getLong(obj.getId()));
		
	  System.out.println("[TCBW]-[MEDEX0102F]-[doQueryOne] : " + obj.getId());
	  
	  java.util.Map<String, String> status2Map = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
	  
	  
	  if(c!=null)
	  {	
		  obj.setId(Common.get(c.getId()));
		  
		  obj.setStatus(c.getStatus());
		  
		  if(StringUtils.contains("00,01,02,03,90", c.getStatus()))
		  {
				obj.setStatusCh(status2Map.get(c.getStatus()));//案件狀態
		  }
		  else if(StringUtils.contains("30", c.getStatus()))
		  {
				obj.setStatusCh("補件中");//案件狀態
		  }
		  else
		  {
				obj.setStatusCh("處理中");//案件狀態
		  }
		  
		  String statusCh1=View.getLookupField("select status from Med4001Db where applNo="+Common.sqlChar(c.getApplNo1()));
		  
		  if(StringUtils.contains("00,01,02,03,90",statusCh1))
		  {
				obj.setStatusCh1(status2Map.get(statusCh1));//關聯案件狀態
		  }
		  else if(StringUtils.contains("30", statusCh1))
		  {
				obj.setStatusCh1("補件中");//關聯案件狀態
		  }
		  else
		  {
				obj.setStatusCh1("處理中");//關聯案件狀態
		  }
		  
		  
		  //obj.setStatusCh1(status2Map.get(statusCh1));//關聯案件狀態
		  
		  obj.setApplNo(Common.get(c.getApplNo()));//案號
		  obj.setApplNo1(Common.get(c.getApplNo1()));//關聯案號
		  
		  obj.setMed0001ID(Common.get(c.getMed0001ID()));
		  
		  obj.setOccurDate(c.getOccurDate());							//通報日期
		  obj.setNotifierRepDate(c.getNotifierRevDate());				//通報者接獲日期
		  obj.setNotifierRepDate(c.getNotifierRepDate());
		  
		  obj.setNotifierRevDate(c.getNotifierRevDate());				//通報中心接獲日期
		  obj.setDrugEventsSources(c.getDrugEventsSources());			//原始藥物不良事件獲知來源
		  obj.setMedicalStaff(c.getMedicalStaff());						//原始藥物不良事件獲知來源-醫療人員
		  obj.setHealthUnits(c.getHealthUnits());						//原始藥物不良事件獲知來源-衛生單位
		  
		  obj.setMedicalStaffOther(c.getMedicalStaffOther());
		  obj.setHealthUnitsOther(c.getHealthUnitsOther());
		  
		  
		  obj.setCaseSource(c.getCaseSource());							//案例來源
		  obj.setCaseSourceOutCountry(c.getCaseSourceOutCountry());		//案例來源-國外(國家)
		  obj.setReportKind(c.getReportKind());							//報告類別
		  obj.setTrackingNum(c.getTrackingNum());						//報告類別-追蹤報告第N次
		  obj.setCorrectiveAction(c.getCorrectiveAction());				//報告類別-矯正措施
		  obj.setAttachment(c.getAttachment());							//附件
		  obj.setAttachmentYnum(c.getAttachmentYnum());					//附件-有共N件
		  
		  obj.setDrugSafetyMonitoring(c.getDrugSafetyMonitoring());		//產品經公告列入 藥物安全監視
		  
		  //通報者資訊
		  obj.setNotifierName(c.getNotifierName());//姓名
		  obj.setNotifierAreaCode(c.getNotifierAreaCode());//電話區碼
		  obj.setNotifierTel(c.getNotifierTel());//電話
		  obj.setNotifierTelExt(c.getNotifierTelExt());//電話分機
		  obj.setNotifierDeptID(c.getNotifierDeptID());//通報者屬性-服務機構ID
		  obj.setNotifierDept(c.getNotifierDept());//通報者屬性-服務機構名稱
		  obj.setNotifierEamil(c.getNotifierEamil());//E-mail
		  obj.setNotifierCounty(c.getNotifierCounty());//縣市別
		  obj.setNotifierZipCode(c.getNotifierZipCode());//郵遞區號
		  obj.setNotifierAddress(c.getNotifierAddress());//地址
		  obj.setNotifierType(c.getNotifierType());//通報者屬性
		  obj.setNotifierStaffHospital(c.getNotifierStaffHospital());//通報者屬性-醫院名稱
		  obj.setNotifierStaffTitle(c.getNotifierStaffTitle());//通報者屬性-醫療人員-職稱	
		  obj.setNotifierStaffTitleOther(c.getNotifierStaffTitleOther());//通報者屬性-醫療人員-職稱-其他
		  obj.setNotifierFirmDept(c.getNotifierFirmDept());//通報者屬性-廠商-服務機構
		  
		  
		  
					

		  obj.setIsContactYn(c.getIsContactYn());//是否願意提供
		  
		  //病人相關資料
		  obj.setBadReactionPatientCode(c.getBadReactionPatientCode());	//病人代號
		  obj.setBadReactionSex(c.getBadReactionSex());					//病人性別
		  obj.setBadReactionBirthday(c.getBadReactionBirthday());		//病人出生日期
		  obj.setBadReactionAge(c.getBadReactionAge());					//病人年齡
		  obj.setBadReactionWeight(c.getBadReactionWeight());			//病人體重
		  obj.setBadReactionHeight(c.getBadReactionHeight());			//病人身高
		  
		  //不良事件類別
		  //obj.setEventKind(c.getEventKind());							
		  String splitString = c.getEventKind();
		  String[] names = null;
		  
		  if(c.getEventKind()!=null)
			  names = splitString.split(",");
		  
		  obj.setEventKind(names);//不良事件類別
		  
		  
		  //不良事件資訊
		  obj.setMedPermit(c.getMedPermit());							//許可證字號-字
		  obj.setMedPermitNumber(c.getMedPermitNumber());				//許可證字號-號
		  obj.setMedPermitFirmCode(c.getMedPermitFirmCode());			//許可證申請商
		  obj.setMedPermitFirm(c.getMedPermitFirm());					//許可證申請商
		  obj.setMedCname(c.getMedCname());								//中文品名
		  
		  obj.setMedMainCategory(c.getMedMainCategoryCode());		//醫材主類別
		  obj.setMedMainCategoryCodeName(c.getMedMainCategory());			//醫材主類別
			  
		  obj.setMedSecCategory(c.getMedSecCategoryCode());			//醫材次類別
		  obj.setMedSecCategoryCodeName(c.getMedSecCategory());				//醫材次類別
		  
		  obj.setMedFactory(c.getMedFactory());							//製造廠
		  obj.setMedCountr(c.getMedCountry());							//製造國別
		 
		  
		  obj.setMedModel(c.getMedModel());								//型號
		  obj.setMedNo(c.getMedNo());									//序號
		  obj.setMedLotNum(c.getMedLotNum());							//批號
		  obj.setMedSoftwareVersion(c.getMedSoftwareVersion());			//軟體版本
		  obj.setMedManufactureDate(c.getMedManufactureDate());			//製造日期
		  obj.setMedEffectiveDate(c.getMedEffectiveDate());				//有效日期
		  obj.setMedPurchaseDate(c.getMedPurchaseDate());				//採購日期
		  obj.setMedUseDate(c.getMedUseDate());							//使用日期
		  obj.setMedUseReason(c.getMedUseReason());						//使用原因
		  

		  obj.setBadReactionResults(c.getBadReactionResults());//不良反應結果
		
		  obj.setBadReactionResultsDeathDate(c.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期
		  obj.setBadReactionResultsDeathReason(c.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因
		  obj.setBadReactionResultsOther(c.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	

		  //器材操作
		  String productProblemKind1 = c.getProductProblemKind1();
		  String[] productProblemKind1s = null;
		  if(c.getProductProblemKind1()!=null)
			  productProblemKind1s = productProblemKind1.split(",");
		  
		  obj.setProductProblemKind1(productProblemKind1s);
		  
		  //環境設施
		  String productProblemKind2 = c.getProductProblemKind2();
		  String[] productProblemKind2s = null;
		  if(c.getProductProblemKind2()!=null)
			  productProblemKind2s = productProblemKind2.split(",");
		  
		  obj.setProductProblemKind2(productProblemKind2s);
		  
		  //人因
		  String productProblemKind3 = c.getProductProblemKind3();
		  String[] productProblemKind3s = null;
		  if(c.getProductProblemKind3()!=null)
			  productProblemKind3s = productProblemKind3.split(",");
		  
		  obj.setProductProblemKind3(productProblemKind3s);
		  
		  //物理特性
		  String productProblemKind4 = c.getProductProblemKind4();
		  String[] productProblemKind4s = null;
		  if(c.getProductProblemKind4()!=null)
			  productProblemKind4s = productProblemKind4.split(",");
		  
		  obj.setProductProblemKind4(productProblemKind4s);
		  
		  //其他(請敘述)
		  obj.setProductProblemKindOther(c.getProductProblemKindOther());
		  
		  //不良反應相關資料
		  																//不良反應結果
		  obj.setMedOperator(c.getMedOperator());						//醫療器材操作者
		  
		  obj.setMedDisposalStatus(c.getMedDisposalStatus());			//器材處置狀況
		  obj.setMedDisposalStatusDate(c.getMedDisposalStatusDate());	//器材處置狀況-尚植於病人體內於XXX退還廠商
		  
		  obj.setMedUse(c.getMedUse());									//器材使用
		  obj.setMedUseOther(c.getMedUseOther());						//器材使用-其他
		  
		  obj.setOnceUseMed(c.getOnceUseMed());							//曾使用同類醫材
		  obj.setOnceUseMedName(c.getOnceUseMedName());					//曾使用同類醫材-是-醫材名稱
		  obj.setOnceUseMedOther(c.getOnceUseMedOther());				//曾使用同類醫材-是-若發生不良反應請描述
		  
		  obj.setStopMedMitigate(c.getStopMedMitigate());				//停用後不良反應是否減輕
		  obj.setSameReaction(c.getSameReaction());						//再使用是否出現同樣反應
		  				
		  																//產品問題分類
		  obj.setDefProductDescription(c.getDefProductDescription());	//不良品缺陷之描述
		  
		  obj.setDefProductOtherDescription(c.getDefProductOtherDescription());
		  
		  obj.setOtherRelatedData(c.getOtherRelatedData());
		  
		  obj.setIsEnabledUpdate("Y");//請勿刪除
	  }
	  return obj;
	}

	
	public static String getCheckBoxOptionAD(String className, String checkBoxFieldName, 
    		String sql, String selectedCheckBox,String value1,String value2,String value3) 
    {
    	StringBuilder sb = new StringBuilder();    	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	if (list!=null && list.size()>0) 
    	{
    		int j=0,x=0;
        	for(int i=0 ; i<list.size();i++)
        	{
        		x++;
        		Object[] o = (Object[]) list.get(i);
        		sb.append("<input class=\"" ).append( className ).append( "\" type=\"radio\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox!=null ) 
        		{
        			if(Common.get(o[0]).equals(selectedCheckBox)) sb.append(" checked");
        		}
        		sb.append(" onClick = \"chgBadReactionResults()\"");
        	    sb.append(">").append(o[1]).append(" ");
               
                if("01".equals(Common.get(o[0])))
        	    {
        	    	sb.append("，日期:").append(View.getPopCalendar("field","badReactionResultsDeathDate",value1));
        	    	sb.append("，死亡原因:").append("<input class='field' type='text' name='badReactionResultsDeathReason' size='20' maxlength='30' value='").append(value2).append("'>");
        	    }	
        	    
        	    if("08".equals(Common.get(o[0])))
        	    {
        	    	sb.append("<input type='text' name='badReactionResultsOther' size='20' maxlength='30' value='").append(value3).append("'>");
        	    }	
        	 
        	    if(x % 3 == 0)
        	    	sb.append("<br>");
        	
        	}    		
    	}
        return sb.toString();    	
    }    
	
	public String getMed4002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med4002Db where 1=1 and med4001Db.id="+Common.get(getId());
        
		System.out.println(hql);
		
        java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
	
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med4002Db o = (Med4002Db) it.next();
				sb.append("addMed4002Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getOccurDate()).append("'");//通報日期
				sb.append(",'").append(o.getPosition()).append("'");//部位
				sb.append(",'").append(o.getSymptom()).append("'");//症狀
				sb.append(",'").append(o.getSeverity()).append("'");//嚴重程度
				sb.append(",'").append(o.getDealWith()).append("');\n");//處置
			}
		}
		return sb.toString(); 
	}
	
	public String getMed4003DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med4003Db where 1=1 and med4001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med4003Db o = (Med4003Db) it.next();
				sb.append("addMed4003Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getTestDate()).append("'");//檢驗日期
				sb.append(",'").append(o.getTestItems()).append("'");//檢驗項目
				sb.append(",'").append(o.getTestNum()).append("');\n");//檢驗數據
			}
		}
		
		return sb.toString(); 
	}

	public String getMed4004DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med4004Db where 1=1 and med4001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med4004Db o = (Med4004Db) it.next();
				sb.append("addMed4004Db('").append(Common.get(o.getId())).append("'");
				
				sb.append(",'").append(o.getPermit()).append("'");//許可證字
				sb.append(",'").append(o.getPermitNumber()).append("'");//許可證號
				sb.append(",'").append(o.getcName()).append("'");//品名
				sb.append(",'").append(o.getPermitFirm()).append("'");//許可證申請商
				sb.append(",'").append(o.getModel()).append("'");//型號
				sb.append(",'").append(o.getMainCategory()).append("'");//器材主類別
				sb.append(",'").append(o.getUseDate()).append("'");//使用日期
				sb.append(",'").append(o.getUseReason()).append("'");//使用原因
				sb.append(",'").append(o.getMainCategoryCode()).append("');\n");//使用原因
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed4005DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med4005Db where 1=1 and med4001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med4005Db o = (Med4005Db) it.next();
				sb.append("addMed4005Db('").append(Common.get(o.getId())).append("'");
				
				sb.append(",'").append(o.getcName()).append("'");//學名/商品名
				sb.append(",'").append(o.getContent()).append("'");//含量
				sb.append(",'").append(o.getFormulation()).append("'");//劑型
				sb.append(",'").append(o.getDrgApproach()).append("'");//給藥途徑
				sb.append(",'").append(o.getDose()).append("'");//劑量
				sb.append(",'").append(o.getFrequency()).append("'");//使用頻率
				sb.append(",'").append(o.getsDate()).append("'");//起日期
				sb.append(",'").append(o.geteDate()).append("'");//迄日期
				sb.append(",'").append(o.getMedCauses()).append("');\n");//用藥原因
			}
		}
		return sb.toString(); 
	}
	
	
	
	public String getAddFile() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED%' and dbName='Med4001Db' and upLoadId="+getId();

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td  style='text-align:left'>");
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=MED&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
			
				
				sb.append("<td style='text-align:center'>");				
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");
				sb.append("</td>\n");
			
				sb.append("</tr>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	
	
	
	@Override
	public Object doQueryAll() throws Exception {		
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception 
	{
		Med4001Db obj = (Med4001Db)View.getObject(" from Med4001Db where id = " + Common.getLong(getId()));
		if(obj != null)
		{
			deleteCon0001DbByMedex0104(obj.getId(),"1");//刪除檔案上傳資料
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}	
	}
	
	public String deleteCon0001DbByMedex0104(Long id,String type) throws Exception 
	{		
		if("1".equals(type)){ //刪除全部檔案
			String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db' and upLoadId="+id;		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
			if(objList!=null && objList.size()>0)		
			{			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext())			
				{				
					Con0001Db o = (Con0001Db) it.next();				
					ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());			
				}		
			}
		}else{ //僅刪除新上傳檔案(主動補件取消)
			String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db' and isInsert='Y' and upLoadId="+id;			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");			
			if(objList!=null && objList.size()>0)			
			{				
				java.util.Iterator it = objList.iterator();				
				while (it.hasNext())				
				{					
					Con0001Db o = (Con0001Db) it.next();				
					ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());				
				}		
			}			
		}
		return null;
	}
	

	public String getIsNeedBackQuery() {
		return checkGet(isNeedBackQuery);
	}

	public void setIsNeedBackQuery(String isNeedBackQuery) {
		this.isNeedBackQuery = checkSet(isNeedBackQuery);
	}

	public javax.servlet.ServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(javax.servlet.ServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public String getIsEnabledUpdate() {
		return checkGet(isEnabledUpdate);
	}

	public void setIsEnabledUpdate(String isEnabledUpdate) {
		this.isEnabledUpdate = checkSet(isEnabledUpdate);
	}

	public String getUpdateType() {
		return checkGet(updateType);
	}

	public void setUpdateType(String updateType) {
		this.updateType = checkSet(updateType);
	}

	public String getInOrOutcreator() {
		return checkGet(inOrOutcreator);
	}

	public void setInOrOutcreator(String inOrOutcreator) {
		this.inOrOutcreator = checkSet(inOrOutcreator);
	}

	public String getNotifierUserID() {
		return checkGet(notifierUserID);
	}

	public void setNotifierUserID(String notifierUserID) {
		this.notifierUserID = checkSet(notifierUserID);
	}

	public String getCommonUser() {
		return checkGet(commonUser);
	}

	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}

	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	public String getStatus() {
		return checkGet(status);
	}

	public void setStatus(String status) {
		this.status = checkSet(status);
	}

	public String getOccurDate() {
		return checkGet(occurDate);
	}

	public void setOccurDate(String occurDate) {
		this.occurDate = checkSet(occurDate);
	}

	public String getNotifierRevDate() {
		return checkGet(notifierRevDate);
	}

	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = checkSet(notifierRevDate);
	}

	public String getNotifierRepDate() {
		return checkGet(notifierRepDate);
	}

	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = checkSet(notifierRepDate);
	}

	public String getDrugEventsSources() {
		return checkGet(drugEventsSources);
	}

	public void setDrugEventsSources(String drugEventsSources) {
		this.drugEventsSources = checkSet(drugEventsSources);
	}

	public String getMedicalStaff() {
		return checkGet(medicalStaff);
	}

	public void setMedicalStaff(String medicalStaff) {
		this.medicalStaff = checkSet(medicalStaff);
	}

	public String getMedicalStaffOther() {
		return checkGet(medicalStaffOther);
	}

	public void setMedicalStaffOther(String medicalStaffOther) {
		this.medicalStaffOther = checkSet(medicalStaffOther);
	}

	public String getHealthUnits() {
		return checkGet(healthUnits);
	}

	public void setHealthUnits(String healthUnits) {
		this.healthUnits = checkSet(healthUnits);
	}

	public String getHealthUnitsOther() {
		return checkGet(healthUnitsOther);
	}

	public void setHealthUnitsOther(String healthUnitsOther) {
		this.healthUnitsOther = checkSet(healthUnitsOther);
	}

	public String getCaseSource() {
		return checkGet(caseSource);
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = checkSet(caseSource);
	}

	public String getCaseSourceOutCountry() {
		return checkGet(caseSourceOutCountry);
	}

	public void setCaseSourceOutCountry(String caseSourceOutCountry) {
		this.caseSourceOutCountry = checkSet(caseSourceOutCountry);
	}

	public String getReportKind() {
		return checkGet(reportKind);
	}

	public void setReportKind(String reportKind) {
		this.reportKind = checkSet(reportKind);
	}

	public String getTrackingNum() {
		return checkGet(trackingNum);
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = checkSet(trackingNum);
	}

	public String getCorrectiveAction() {
		return checkGet(correctiveAction);
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = checkSet(correctiveAction);
	}

	public String getAttachment() {
		return checkGet(attachment);
	}

	public void setAttachment(String attachment) {
		this.attachment = checkSet(attachment);
	}

	public String getAttachmentYnum() {
		return checkGet(attachmentYnum);
	}

	public void setAttachmentYnum(String attachmentYnum) {
		this.attachmentYnum = checkSet(attachmentYnum);
	}

	public String getDrugSafetyMonitoring() {
		return checkGet(drugSafetyMonitoring);
	}

	public void setDrugSafetyMonitoring(String drugSafetyMonitoring) {
		this.drugSafetyMonitoring = checkSet(drugSafetyMonitoring);
	}

	public String getNotifierName() {
		return checkGet(notifierName);
	}

	public void setNotifierName(String notifierName) {
		this.notifierName = checkSet(notifierName);
	}

	public String getNotifierAreaCode() {
		return checkGet(notifierAreaCode);
	}

	public void setNotifierAreaCode(String notifierAreaCode) {
		this.notifierAreaCode = checkSet(notifierAreaCode);
	}

	public String getNotifierTel() {
		return checkGet(notifierTel);
	}

	public void setNotifierTel(String notifierTel) {
		this.notifierTel = checkSet(notifierTel);
	}

	public String getNotifierAddress() {
		return checkGet(notifierAddress);
	}

	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = checkSet(notifierAddress);
	}

	public String getNotifierEamil() {
		return checkGet(notifierEamil);
	}

	public void setNotifierEamil(String notifierEamil) {
		this.notifierEamil = checkSet(notifierEamil);
	}

	public String getNotifierType() {
		return checkGet(notifierType);
	}

	public void setNotifierType(String notifierType) {
		this.notifierType = checkSet(notifierType);
	}
	
	

	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
	}

	public String getNotifierStaffHospital() {
		return checkGet(notifierStaffHospital);
	}

	public void setNotifierStaffHospital(String notifierStaffHospital) {
		this.notifierStaffHospital = checkSet(notifierStaffHospital);
	}

	public String getNotifierStaffTitle() {
		return checkGet(notifierStaffTitle);
	}

	public void setNotifierStaffTitle(String notifierStaffTitle) {
		this.notifierStaffTitle = checkSet(notifierStaffTitle);
	}

	public String getNotifierStaffTitleOther() {
		return checkGet(notifierStaffTitleOther);
	}

	public void setNotifierStaffTitleOther(String notifierStaffTitleOther) {
		this.notifierStaffTitleOther = checkSet(notifierStaffTitleOther);
	}

	public String getNotifierFirmDept() {
		return checkGet(notifierFirmDept);
	}

	public void setNotifierFirmDept(String notifierFirmDept) {
		this.notifierFirmDept = checkSet(notifierFirmDept);
	}

	public String getIsContactYn() {
		return checkGet(isContactYn);
	}

	public void setIsContactYn(String isContactYn) {
		this.isContactYn = checkSet(isContactYn);
	}

	public String getBadReactionPatientCode() {
		return checkGet(badReactionPatientCode);
	}

	public void setBadReactionPatientCode(String badReactionPatientCode) {
		this.badReactionPatientCode = checkSet(badReactionPatientCode);
	}

	public String getBadReactionSex() {
		return checkGet(badReactionSex);
	}

	public void setBadReactionSex(String badReactionSex) {
		this.badReactionSex = checkSet(badReactionSex);
	}

	public String getBadReactionBirthday() {
		return checkGet(badReactionBirthday);
	}

	public void setBadReactionBirthday(String badReactionBirthday) {
		this.badReactionBirthday = checkSet(badReactionBirthday);
	}

	public String getBadReactionAge() {
		return checkGet(badReactionAge);
	}

	public void setBadReactionAge(String badReactionAge) {
		this.badReactionAge = checkSet(badReactionAge);
	}

	public String getBadReactionWeight() {
		return checkGet(badReactionWeight);
	}

	public void setBadReactionWeight(String badReactionWeight) {
		this.badReactionWeight = checkSet(badReactionWeight);
	}

	public String getBadReactionHeight() {
		return checkGet(badReactionHeight);
	}

	public void setBadReactionHeight(String badReactionHeight) {
		this.badReactionHeight = checkSet(badReactionHeight);
	}

	

	public String[] getEventKind() {
		return eventKind;
	}

	public void setEventKind(String[] eventKind) {
		this.eventKind = eventKind;
	}

	public String getMedPermit() {
		return checkGet(medPermit);
	}

	public void setMedPermit(String medPermit) {
		this.medPermit = checkSet(medPermit);
	}

	public String getMedPermitNumber() {
		return checkGet(medPermitNumber);
	}

	public void setMedPermitNumber(String medPermitNumber) {
		this.medPermitNumber = checkSet(medPermitNumber);
	}

	public String getMedPermitFirm() {
		return checkGet(medPermitFirm);
	}

	public void setMedPermitFirm(String medPermitFirm) {
		this.medPermitFirm = checkSet(medPermitFirm);
	}

	public String getMedCname() {
		return checkGet(medCname);
	}

	public void setMedCname(String medCname) {
		this.medCname = checkSet(medCname);
	}

	public String getMedMainCategory() {
		return checkGet(medMainCategory);
	}

	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = checkSet(medMainCategory);
	}

	public String getMedSecCategory() {
		return checkGet(medSecCategory);
	}

	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = checkSet(medSecCategory);
	}

	public String getMedFactory() {
		return checkGet(medFactory);
	}

	public void setMedFactory(String medFactory) {
		this.medFactory = checkSet(medFactory);
	}

	public String getMedCountr() {
		return checkGet(medCountr);
	}

	public void setMedCountr(String medCountr) {
		this.medCountr = checkSet(medCountr);
	}

	public String getMedModel() {
		return checkGet(medModel);
	}

	public void setMedModel(String medModel) {
		this.medModel = checkSet(medModel);
	}

	public String getMedNo() {
		return checkGet(medNo);
	}

	public void setMedNo(String medNo) {
		this.medNo = checkSet(medNo);
	}

	public String getMedLotNum() {
		return checkGet(medLotNum);
	}

	public void setMedLotNum(String medLotNum) {
		this.medLotNum = checkSet(medLotNum);
	}

	public String getMedSoftwareVersion() {
		return checkGet(medSoftwareVersion);
	}

	public void setMedSoftwareVersion(String medSoftwareVersion) {
		this.medSoftwareVersion = checkSet(medSoftwareVersion);
	}

	public String getMedManufactureDate() {
		return checkGet(medManufactureDate);
	}

	public void setMedManufactureDate(String medManufactureDate) {
		this.medManufactureDate = checkSet(medManufactureDate);
	}

	public String getMedEffectiveDate() {
		return checkGet(medEffectiveDate);
	}

	public void setMedEffectiveDate(String medEffectiveDate) {
		this.medEffectiveDate = checkSet(medEffectiveDate);
	}

	public String getMedPurchaseDate() {
		return checkGet(medPurchaseDate);
	}

	public void setMedPurchaseDate(String medPurchaseDate) {
		this.medPurchaseDate = checkSet(medPurchaseDate);
	}

	public String getMedUseDate() {
		return checkGet(medUseDate);
	}

	public void setMedUseDate(String medUseDate) {
		this.medUseDate = checkSet(medUseDate);
	}

	public String getMedUseReason() {
		return checkGet(medUseReason);
	}

	public void setMedUseReason(String medUseReason) {
		this.medUseReason = checkSet(medUseReason);
	}



	public String getBadReactionResults() {
		return checkGet(badReactionResults);
	}

	public void setBadReactionResults(String badReactionResults) {
		this.badReactionResults = checkSet(badReactionResults);
	}

	public String getBadReactionResultsDeathDate() {
		return checkGet(badReactionResultsDeathDate);
	}

	public void setBadReactionResultsDeathDate(String badReactionResultsDeathDate) {
		this.badReactionResultsDeathDate = checkSet(badReactionResultsDeathDate);
	}

	public String getBadReactionResultsDeathReason() {
		return checkGet(badReactionResultsDeathReason);
	}

	public void setBadReactionResultsDeathReason(
			String badReactionResultsDeathReason) {
		this.badReactionResultsDeathReason = checkSet(badReactionResultsDeathReason);
	}

	public String getBadReactionResultsOther() {
		return checkGet(badReactionResultsOther);
	}

	public void setBadReactionResultsOther(String badReactionResultsOther) {
		this.badReactionResultsOther = checkSet(badReactionResultsOther);
	}

	public String getMedOperator() {
		return checkGet(medOperator);
	}

	public void setMedOperator(String medOperator) {
		this.medOperator = checkSet(medOperator);
	}

	public String getMedDisposalStatus() {
		return checkGet(medDisposalStatus);
	}

	public void setMedDisposalStatus(String medDisposalStatus) {
		this.medDisposalStatus = checkSet(medDisposalStatus);
	}

	public String getMedDisposalStatusDate() {
		return checkGet(medDisposalStatusDate);
	}

	public void setMedDisposalStatusDate(String medDisposalStatusDate) {
		this.medDisposalStatusDate = checkSet(medDisposalStatusDate);
	}

	public String getMedUse() {
		return checkGet(medUse);
	}

	public void setMedUse(String medUse) {
		this.medUse = checkSet(medUse);
	}

	public String getMedUseOther() {
		return checkGet(medUseOther);
	}

	public void setMedUseOther(String medUseOther) {
		this.medUseOther = checkSet(medUseOther);
	}

	public String getOnceUseMed() {
		return checkGet(onceUseMed);
	}

	public void setOnceUseMed(String onceUseMed) {
		this.onceUseMed = checkSet(onceUseMed);
	}

	public String getOnceUseMedName() {
		return checkGet(onceUseMedName);
	}

	public void setOnceUseMedName(String onceUseMedName) {
		this.onceUseMedName = checkSet(onceUseMedName);
	}

	public String getOnceUseMedOther() {
		return checkGet(onceUseMedOther);
	}

	public void setOnceUseMedOther(String onceUseMedOther) {
		this.onceUseMedOther = checkSet(onceUseMedOther);
	}

	public String getStopMedMitigate() {
		return checkGet(stopMedMitigate);
	}

	public void setStopMedMitigate(String stopMedMitigate) {
		this.stopMedMitigate = checkSet(stopMedMitigate);
	}

	public String getSameReaction() {
		return checkGet(sameReaction);
	}

	public void setSameReaction(String sameReaction) {
		this.sameReaction = checkSet(sameReaction);
	}

	public String getOtherRelatedData() {
		return checkGet(otherRelatedData);
	}

	public void setOtherRelatedData(String otherRelatedData) {
		this.otherRelatedData = checkSet(otherRelatedData);
	}

	
	
	public String[] getProductProblemKind1() {
		return productProblemKind1;
	}

	public void setProductProblemKind1(String[] productProblemKind1) {
		this.productProblemKind1 = productProblemKind1;
	}

	public String[] getProductProblemKind2() {
		return productProblemKind2;
	}

	public void setProductProblemKind2(String[] productProblemKind2) {
		this.productProblemKind2 = productProblemKind2;
	}

	public String[] getProductProblemKind3() {
		return productProblemKind3;
	}

	public void setProductProblemKind3(String[] productProblemKind3) {
		this.productProblemKind3 = productProblemKind3;
	}

	public String[] getProductProblemKind4() {
		return productProblemKind4;
	}

	public void setProductProblemKind4(String[] productProblemKind4) {
		this.productProblemKind4 = productProblemKind4;
	}

	public String getProductProblemKindOther() {
		return checkGet(productProblemKindOther);
	}

	public void setProductProblemKindOther(String productProblemKindOther) {
		this.productProblemKindOther = checkSet(productProblemKindOther);
	}

	public String getDefProductDescription() {
		return checkGet(defProductDescription);
	}

	public void setDefProductDescription(String defProductDescription) {
		this.defProductDescription = checkSet(defProductDescription);
	}

	public String getDefProductOtherDescription() {
		return checkGet(defProductOtherDescription);
	}

	public void setDefProductOtherDescription(String defProductOtherDescription) {
		this.defProductOtherDescription = checkSet(defProductOtherDescription);
	}

	public String[] getBulletinDate() {
		return bulletinDate;
	}

	public void setBulletinDate(String[] bulletinDate) {
		this.bulletinDate = bulletinDate;
	}

	public String[] getPosition() {
		return position;
	}

	public void setPosition(String[] position) {
		this.position = position;
	}

	public String[] getSymptom() {
		return symptom;
	}

	public void setSymptom(String[] symptom) {
		this.symptom = symptom;
	}

	public String[] getSeverity() {
		return severity;
	}

	public void setSeverity(String[] severity) {
		this.severity = severity;
	}

	public String[] getDealWith() {
		return dealWith;
	}

	public void setDealWith(String[] dealWith) {
		this.dealWith = dealWith;
	}

	public String[] getMed4002DbType() {
		return med4002DbType;
	}

	public void setMed4002DbType(String[] med4002DbType) {
		this.med4002DbType = med4002DbType;
	}

	public String[] getMed4002DbTypeId() {
		return med4002DbTypeId;
	}

	public void setMed4002DbTypeId(String[] med4002DbTypeId) {
		this.med4002DbTypeId = med4002DbTypeId;
	}

	public String[] getTestDate() {
		return testDate;
	}

	public void setTestDate(String[] testDate) {
		this.testDate = testDate;
	}

	public String[] getTestItems() {
		return testItems;
	}

	public void setTestItems(String[] testItems) {
		this.testItems = testItems;
	}

	public String[] getTestNum() {
		return testNum;
	}

	public void setTestNum(String[] testNum) {
		this.testNum = testNum;
	}

	public String[] getMed4003DbType() {
		return med4003DbType;
	}
	
	public void setMed4003DbType(String[] med4003DbType) {
		this.med4003DbType = med4003DbType;
	}

	public String[] getMed4003DbTypeId() {
		return med4003DbTypeId;
	}

	public void setMed4003DbTypeId(String[] med4003DbTypeId) {
		this.med4003DbTypeId = med4003DbTypeId;
	}

	

	public String[] getCcname() {
		return ccname;
	}

	public void setCcname(String[] ccname) {
		this.ccname = ccname;
	}

	public String[] getPermit() {
		return permit;
	}

	public void setPermit(String[] permit) {
		this.permit = permit;
	}

	public String[] getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String[] permitNumber) {
		this.permitNumber = permitNumber;
	}

	public String[] getPermitFirm() {
		return permitFirm;
	}

	public void setPermitFirm(String[] permitFirm) {
		this.permitFirm = permitFirm;
	}

	public String[] getModel() {
		return model;
	}

	public void setModel(String[] model) {
		this.model = model;
	}

	public String[] getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String[] mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String[] getUseDate() {
		return useDate;
	}

	public void setUseDate(String[] useDate) {
		this.useDate = useDate;
	}

	public String[] getUseReason() {
		return useReason;
	}

	public void setUseReason(String[] useReason) {
		this.useReason = useReason;
	}

	public String[] getMed4004DbType() {
		return med4004DbType;
	}

	public void setMed4004DbType(String[] med4004DbType) {
		this.med4004DbType = med4004DbType;
	}

	public String[] getMed4004DbTypeId() {
		return med4004DbTypeId;
	}

	public void setMed4004DbTypeId(String[] med4004DbTypeId) {
		this.med4004DbTypeId = med4004DbTypeId;
	}


	public String[] getcName2() {
		return cName2;
	}

	public void setcName2(String[] cName2) {
		this.cName2 = cName2;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public String[] getFormulation() {
		return formulation;
	}

	public void setFormulation(String[] formulation) {
		this.formulation = formulation;
	}

	public String[] getDrgApproach() {
		return drgApproach;
	}

	public void setDrgApproach(String[] drgApproach) {
		this.drgApproach = drgApproach;
	}

	public String[] getDose() {
		return dose;
	}

	public void setDose(String[] dose) {
		this.dose = dose;
	}

	public String[] getFrequency() {
		return frequency;
	}

	public void setFrequency(String[] frequency) {
		this.frequency = frequency;
	}

	public String[] getsDate() {
		return sDate;
	}

	public void setsDate(String[] sDate) {
		this.sDate = sDate;
	}

	public String[] geteDate() {
		return eDate;
	}

	public void seteDate(String[] eDate) {
		this.eDate = eDate;
	}

	public String[] getMedCauses() {
		return medCauses;
	}

	public void setMedCauses(String[] medCauses) {
		this.medCauses = medCauses;
	}

	public String[] getMed4005DbType() {
		return med4005DbType;
	}

	public void setMed4005DbType(String[] med4005DbType) {
		this.med4005DbType = med4005DbType;
	}

	public String[] getMed4005DbTypeId() {
		return med4005DbTypeId;
	}

	public void setMed4005DbTypeId(String[] med4005DbTypeId) {
		this.med4005DbTypeId = med4005DbTypeId;
	}

	public String getMedPermitFirmCode() {
		return checkGet(medPermitFirmCode);
	}

	public void setMedPermitFirmCode(String medPermitFirmCode) {
		this.medPermitFirmCode = checkSet(medPermitFirmCode);
	}

	public String getMedMainCategoryCode() {
		return checkGet(medMainCategoryCode);
	}

	public void setMedMainCategoryCode(String medMainCategoryCode) {
		this.medMainCategoryCode = checkSet(medMainCategoryCode);
	}

	public String getMedSecCategoryCode() {
		return checkGet(medSecCategoryCode);
	}

	public void setMedSecCategoryCode(String medSecCategoryCode) {
		this.medSecCategoryCode = checkSet(medSecCategoryCode);
	}

	public String getNotifierDeptID() {
		return checkGet(notifierDeptID);
	}

	public void setNotifierDeptID(String notifierDeptID) {
		this.notifierDeptID = checkSet(notifierDeptID);
	}

	public String getNotifierDept() {
		return checkGet(notifierDept);
	}

	public void setNotifierDept(String notifierDept) {
		this.notifierDept = checkSet(notifierDept);
	}

	public String getMed0001ID() {
		return checkGet(med0001ID);
	}

	public void setMed0001ID(String med0001id) {
		med0001ID = checkSet(med0001id);
	}

	public String getStatusCh() {
		return checkGet(statusCh);
	}

	public void setStatusCh(String statusCh) {
		this.statusCh = checkSet(statusCh);
	}

	public String getApplNo1() {
		return checkGet(applNo1);
	}

	public void setApplNo1(String applNo1) {
		this.applNo1 = checkSet(applNo1);
	}

	public String getStatusCh1() {
		return checkGet(statusCh1);
	}

	public void setStatusCh1(String statusCh1) {
		this.statusCh1 = checkSet(statusCh1);
	}
	public String getNotifierCounty() {
		return checkGet(notifierCounty);
	}
	public void setNotifierCounty(String notifierCounty) {
		this.notifierCounty = checkSet(notifierCounty);
	}
	public String getNotifierZipCode() {
		return checkGet(notifierZipCode);
	}
	public void setNotifierZipCode(String notifierZipCode) {
		this.notifierZipCode = checkSet(notifierZipCode);
	}

	public String getMedMainCategoryCodeName() {
		return checkGet(medMainCategoryCodeName);
	}

	public void setMedMainCategoryCodeName(String medMainCategoryCodeName) {
		this.medMainCategoryCodeName = checkSet(medMainCategoryCodeName);
	}

	public String getMedSecCategoryCodeName() {
		return checkGet(medSecCategoryCodeName);
	}

	public void setMedSecCategoryCodeName(String medSecCategoryCodeName) {
		this.medSecCategoryCodeName = checkSet(medSecCategoryCodeName);
	}

	public String[] getMainCategoryCode() {
		return checkGet(mainCategoryCode);
	}

	public void setMainCategoryCode(String[] mainCategoryCode) {
		this.mainCategoryCode = checkSet(mainCategoryCode);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}
	
	
}
