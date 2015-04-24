package com.kangdainfo.tcbw.view.medin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.bo.Med0003Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0101F extends MEDIN0201F
{
	
	private String isNeedBackQuery;//
	javax.servlet.ServletRequest httpRequest;
	
	private String eventKindTemp;	//事件類別，暫存用
	
	private String revision;
	private String autoReUpdate;//自動補件
	private String isEnabledUpdate;
	private String updateType;//接收前端按鈕狀態
	private String inOrOutcreator;//案件擁有者
	private String notifierUserID;//內網選擇外網帳號
	private String commonUser;
	private String applNo;//案件號碼	VARCHAR(20)
	private String applNo1;//案件關聯號碼	VARCHAR(20)
	private String kind;
	private String status;//案件狀態	VARCHAR(2)
	
	private String statusCh;//案件狀態中文    
	private String statusCh1;//案件關聯狀態中文    
	
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
	private String notifier;//是否勾選
	private String notifierName;//通報者-姓名	NVARCHAR(10)
	private String notifierAreaCode;//通報者-區域號碼	VARCHAR(3)
	private String notifierTel;//通報者-電話	VARCHAR(10)
	private String notifierTelExt;//通報者-分機
	private String notifierCounty;
	private String notifierZipCode;
	private String notifierAddress;//通報者-地址	NVARCHAR(50)
	private String notifierEamil;//通報者-電子郵件	VARCHAR(30)
	private String notifierType;//通報者-屬性	VARCHAR(2)
	private String notifierStaffHospital;//通報者-醫療人員-醫院名稱	NVARCHAR(30)
	private String notifierStaffTitle;//通報者-醫療人員-職稱	VARCHAR(2)
	private String notifierStaffTitleOther;//通報者-醫療人員-職稱-其他描述	NVARCHAR(10)
	private String notifierDeptID;//通報者-廠商-服務機構
    private String notifierDept;
	
	private String isContactYn;//是否提供聯絡資料以助廠商分析不良事件	VARCHAR(1)
	
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
	private String medMainCategoryCodeName;
	private String medMainCategory;//懷疑的醫療器材-醫材主類別	VARCHAR(30)
	private String medSecCategoryCode;//懷疑的醫療器材-醫材次類別	VARCHAR(10)
	private String medSecCategoryCodeName;
	private String medSecCategory;//懷疑的醫療器材-醫材次類別	VARCHAR(30)
	
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
	//private String badReactionResults;//不良反應結果	VARCHAR(2)
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
	private String med0002DbType[];
	private String med0002DbTypeId[];
	
	//不良反應--相關檢查及檢驗數據	-------------------------------------------------------------------
	private String testDate[];//檢驗日期	VARCHAR(7)
	private String testItems[];//檢驗項目	NVARCHAR(30)
	private String testNum[];//檢驗數據	NVARCHAR(30)
	private String med0003DbType[];
	private String med0003DbTypeId[];
	
	//不良反應--併用醫療器材	-------------------------------------------------------------------
	private String ccname[];//品名		    NVARCHAR(30)
	private String permit[];//許可證字  	NVARCHAR(30)
	private String permitNumber[];//許可證號	NVARCHAR(30)
	private String permitFirm[];//許可證申請商	NVARCHAR(30)
	private String model[];//型號		NVARCHAR(20)
	private String mainCategoryCode[];
	private String mainCategory[];//器材主類別		NVARCHAR(20)
	private String useDate[];//使用日期	VARCHAR(7)
	private String useReason[];//使用原因	NVARCHAR(50)
	private String med0004DbType[];
	private String med0004DbTypeId[];
	
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
	private String med0005DbType[];
	private String med0005DbTypeId[];
	
	//是否為強制結案之案件 -------------------------------------------------------------------
	private String isReCalibration;//是否為強制結案之案件	VARCHAR(1)
	private String reCalibrationReason;//強制結案理由	VARCHAR(100)
	
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
			System.out.println("[TCBW]-[MEDIN0101F]-[新增]-[無法辨別登入的使用者]");
		}
		
		Med0001Db obj = new Med0001Db();
		
		obj.setStatus("00");
		obj.setNotifierName(c.getUserName());
		obj.setNotifierTel(c.getUserTel());
		obj.setNotifierEamil(c.getUserEmail());
		
		//obj.setNotifierIdNum(Common.get(c.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(c.getPersonalId())));
		obj.setNotifierType(c.getCommonDepartment()!=null?Common.get(c.getCommonDepartment().getShortCode()):"");
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
		
		if("out".equals(getInOrOut()))
		  obj.setInOrOutcreator(c.getUserId());
		else
		  obj.setInOrOutcreator("");
		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	public void doUpdateType() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByMedIN0101F(this);
	}
	
	public String getMed0002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med0002Db where 1=1 and med0001Db.id="+Common.get(getId());

		System.out.println(hql);
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med0002Db o = (Med0002Db) it.next();
				sb.append("addMed0002Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getOccurDate()).append("'");//通報日期
				sb.append(",'").append(o.getPosition()).append("'");//部位
				sb.append(",'").append(o.getSymptom()).append("'");//症狀
				sb.append(",'").append(o.getSeverity()).append("'");//嚴重程度
				sb.append(",'").append(o.getDealWith()).append("');\n");//處置
			}
		}
		return sb.toString(); 
	}
	
	public String getMed0003DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med0003Db where 1=1 and med0001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med0003Db o = (Med0003Db) it.next();
				sb.append("addMed0003Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getTestDate()).append("'");//檢驗日期
				sb.append(",'").append(o.getTestItems()).append("'");//檢驗項目
				sb.append(",'").append(o.getTestNum()).append("');\n");//檢驗數據
			}
		}
		
		return sb.toString(); 
	}

	public String getMed0004DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med0004Db where 1=1 and med0001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med0004Db o = (Med0004Db) it.next();
				sb.append("addMed0004Db('").append(Common.get(o.getId())).append("'");
				
				sb.append(",'").append(o.getPermit()).append("'");//許可證字
				sb.append(",'").append(o.getPermitNumber()).append("'");//許可證號
				sb.append(",'").append(o.getcName()).append("'");//品名
				sb.append(",'").append(o.getPermitFirm()).append("'");//許可證申請商
				sb.append(",'").append(o.getModel()).append("'");//型號
				sb.append(",'").append(o.getMainCategory()).append("'");//器材主類別
				sb.append(",'").append(o.getUseDate()).append("'");//使用日期
				sb.append(",'").append(o.getUseReason()).append("'");//使用原因
				sb.append(",'").append(o.getMainCategoryCode()).append("');\n");//器材主類別
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed0005DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med0005Db where 1=1 and med0001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med0005Db o = (Med0005Db) it.next();
				sb.append("addMed0005Db('").append(Common.get(o.getId())).append("'");
				
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
	
	@Override
	public Object doQueryOne() throws Exception 
	{
	  //撰寫
	  MEDIN0101F obj = this;
	  Med0001Db c = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(obj.getId()));
		
	  java.util.Map<String, String> status2Map = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
	  
	  System.out.println("[TCBW]-[MEDIN0101F]-[doQueryOne] : " + obj.getId());
	  
	  if(c!=null)
	  {	
		  obj.setId(Common.get(c.getId()));
		  
		  obj.setKind(c.getEventKind());//不良事件類別
          obj.setStatus(c.getStatus());//案件狀態
		  obj.setStatusCh(status2Map.get(c.getStatus()));//案件狀態中文名稱
		  
		  String statusCh1=View.getLookupField("select status from Med0001Db where applNo="+Common.sqlChar(c.getApplNo1()));
		  obj.setStatusCh1(status2Map.get(statusCh1));//關聯案件狀態
		  
		  obj.setApplNo(Common.get(c.getApplNo()));//案號
		  obj.setApplNo1(Common.get(c.getApplNo1()));//關聯案號
		  
		  
		  obj.setRevision(c.getRevision());
		  obj.setOccurDate(c.getOccurDate());							//通報日期
		  obj.setNotifierRepDate(c.getNotifierRepDate());				//通報者接獲日期
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
		  obj.setNotifierName(c.getNotifierName());						//姓名
		  obj.setNotifierAreaCode(c.getNotifierAreaCode());				//電話區碼
		  obj.setNotifierTel(c.getNotifierTel());						//電話
		  obj.setNotifierTelExt(c.getNotifierTelExt());//分機

		  obj.setNotifierEamil(c.getNotifierEamil());					//E-mail
		  
		  obj.setNotifierCounty(c.getNotifierCounty());
		  obj.setNotifierZipCode(c.getNotifierZipCode());
		  obj.setNotifierAddress(c.getNotifierAddress());				//地址
		  
		  obj.setNotifierType(c.getNotifierType());						//通報者屬性
		  obj.setNotifierStaffHospital(c.getNotifierStaffHospital());	//通報者屬性-醫院名稱
		  obj.setNotifierStaffTitle(c.getNotifierStaffTitle());			//通報者屬性-醫療人員-職稱	
		  obj.setNotifierStaffTitleOther(c.getNotifierStaffTitleOther());//通報者屬性-醫療人員-職稱-其他
		  //obj.setNotifierFirmDept(c.getNotifierFirmDept());			//通報者屬性-服務機構
		  obj.setNotifierDeptID(c.getNotifierDeptID());	//通報者屬性-服務機構
		  obj.setNotifierDept(c.getNotifierDept());	
		  obj.setIsContactYn(c.getIsContactYn());						//是否願意提供
		  
		  //病人相關資料
		  obj.setBadReactionPatientCode(c.getBadReactionPatientCode());	//病人代號
		  obj.setBadReactionSex(c.getBadReactionSex());					//病人性別
		  obj.setBadReactionBirthday(c.getBadReactionBirthday());		//病人出生日期
		  obj.setBadReactionAge(c.getBadReactionAge());					//病人年齡
		  obj.setBadReactionWeight(c.getBadReactionWeight());			//病人體重
		  obj.setBadReactionHeight(c.getBadReactionHeight());			//病人身高
		  
		  obj.setBadReactionResults(c.getBadReactionResults());//不良反應結果
		  
		  obj.setBadReactionResultsDeathDate(c.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期
		  obj.setBadReactionResultsDeathReason(c.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因
		  obj.setBadReactionResultsOther(c.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	
		  
		  
		  
		  //不良事件類別
		  //obj.setEventKind(c.getEventKind());							
		  String splitString = c.getEventKind();
		  String[] names = null;
		  
		  if(c.getEventKind()!=null)
			  names = splitString.split(",");
		  
		  obj.setEventKind(names);//不良事件類別
		  
		  obj.setEventKindTemp(c.getEventKind());	//不良事件類別，暫存用
		  
		  //不良事件資訊
		  obj.setMedPermit(c.getMedPermit());							//許可證字號-字
		  
		  obj.setMedPermitNumber(c.getMedPermitNumber());				//許可證字號-號
		  obj.setMedPermitFirmCode(c.getMedPermitFirmCode());					//許可證申請商代碼
		  
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
		  obj.setOtherRelatedData(c.getOtherRelatedData());				//其他相關資料
		  obj.setDefProductOtherDescription(c.getDefProductOtherDescription());//其他資料
		  				
		  																//產品問題分類
		  obj.setDefProductDescription(c.getDefProductDescription());	//不良品缺陷之描述
		  
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
		  
		  
		  obj.setIsEnabledUpdate("Y");//請勿刪除
		  obj.setAutoReUpdate(c.getAutoReUpdate());
		  
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
	        		if (selectedCheckBox!=null) 
	        		{
	        			//for (j=0; j<selectedCheckBox.length; j++) 
	        			//{
	        				if(Common.get(o[0]).equals(selectedCheckBox)) sb.append(" checked");
	        			//}
	        		}
	        	    sb.append(">").append(o[1]).append(" ");
	      
	        	    if("01".equals(Common.get(o[0])))
	        	    {
	        	    	sb.append("，日期:").append(View.getPopCalendar("field","badReactionResultsDeathDate",value1));
	        	    	sb.append("，死亡原因:").append("<input type='text' name='badReactionResultsDeathReason' size='20' maxlength='30' value='").append(value2).append("'>");
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
	
	
	  public String getAddFile() throws Exception 
	  {
		  StringBuilder sb = new StringBuilder(1024); 
			
		  String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED010001'  and upLoadId="+getId();

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
					if(!"N".equals(o.getIsDelete()))
					{					  
					  sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");
					}
					
					sb.append("</td>\n");
					sb.append("</tr>\n");
					
					i++;
				}
		 }
		 return sb.toString(); 
	}
	  public String getAddFile(String fileType) throws Exception 
	  {
		  StringBuilder sb = new StringBuilder(1024); 
			
		  String hql = " from Con0001Db where fileKind='MED' and systemType like '" + fileType + "%' and upLoadId="+getId();

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
					if(!"N".equals(o.getIsDelete()))
					{					  
					  sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");
					}
					
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
	public void doDelete() throws Exception {
		
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

	public void setMed0004DbType(String[] med0004DbType) {
		this.med0004DbType = med0004DbType;
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

	public String[] getMed0002DbType() {
		return med0002DbType;
	}

	public void setMed0002DbType(String[] med0002DbType) {
		this.med0002DbType = med0002DbType;
	}

	public String[] getMed0002DbTypeId() {
		return med0002DbTypeId;
	}

	public void setMed0002DbTypeId(String[] med0002DbTypeId) {
		this.med0002DbTypeId = med0002DbTypeId;
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

	public String[] getMed0003DbType() {
		return med0003DbType;
	}
	
	public void setMed0003DbType(String[] med0003DbType) {
		this.med0003DbType = med0003DbType;
	}

	public String[] getMed0003DbTypeId() {
		return med0003DbTypeId;
	}

	public void setMed0003DbTypeId(String[] med0003DbTypeId) {
		this.med0003DbTypeId = med0003DbTypeId;
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

	public String[] getMed0004DbType() {
		return med0004DbType;
	}

	public void setMed4004DbType(String[] med0004DbType) {
		this.med0004DbType = med0004DbType;
	}

	public String[] getMed0004DbTypeId() {
		return med0004DbTypeId;
	}

	public void setMed0004DbTypeId(String[] med0004DbTypeId) {
		this.med0004DbTypeId = med0004DbTypeId;
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

	public String[] getMed0005DbType() {
		return med0005DbType;
	}

	public void setMed0005DbType(String[] med0005DbType) {
		this.med0005DbType = med0005DbType;
	}

	public String[] getMed0005DbTypeId() {
		return med0005DbTypeId;
	}

	public void setMed0005DbTypeId(String[] med0005DbTypeId) {
		this.med0005DbTypeId = med0005DbTypeId;
	}

	public String getAutoReUpdate() {
		return checkGet(autoReUpdate);
	}

	public void setAutoReUpdate(String autoReUpdate) {
		this.autoReUpdate = checkSet(autoReUpdate);
	}

	public String getRevision() {
		return checkGet(revision);
	}

	public void setRevision(String revision) {
		this.revision = checkSet(revision);
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

	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
	}

	public String getApplNo1() {
		return checkGet(applNo1);
	}

	public void setApplNo1(String applNo1) {
		this.applNo1 = checkSet(applNo1);
	}

	public String getStatusCh() {
		return checkGet(statusCh);
	}

	public void setStatusCh(String statusCh) {
		this.statusCh = checkSet(statusCh);
	}

	public String getStatusCh1() {
		return checkGet(statusCh1);
	}

	public void setStatusCh1(String statusCh1) {
		this.statusCh1 = checkSet(statusCh1);
	}

	public String[] getMainCategoryCode() {
		return checkGet(mainCategoryCode);
	}

	public void setMainCategoryCode(String[] mainCategoryCode) {
		this.mainCategoryCode = checkSet(mainCategoryCode);
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
	
	public String getKind() {
		return checkGet(kind);
	}

	public void setKind(String kind) {
		this.kind = checkSet(kind);
	}

	public String getNotifier() {
		return checkGet(notifier);
	}

	public void setNotifier(String notifier) {
		this.notifier = checkSet(notifier);
	}
	
	public String getIsReCalibration() {
		return checkGet(isReCalibration);
	}

	public void setIsReCalibration(String isReCalibration) {
		this.isReCalibration = checkSet(isReCalibration);
	}
	
	public String getReCalibrationReason() {
		return checkGet(reCalibrationReason);
	}

	public void setReCalibrationReason(String reCalibrationReason) {
		this.reCalibrationReason = checkSet(reCalibrationReason);
	}

	public String getEventKindTemp() {
		return checkGet(eventKindTemp);
	}

	public void setEventKindTemp(String eventKindTemp) {
		this.eventKindTemp = checkSet(eventKindTemp);
	}

	
	
}
