package com.kangdainfo.tcbw.view.medin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med2002Db;
import com.kangdainfo.tcbw.model.bo.Med2003Db;
import com.kangdainfo.tcbw.model.bo.Med2004Db;
import com.kangdainfo.tcbw.model.bo.Med2005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN5101F extends MEDIN5201F
{
	

	private String isNeedBackQuery;//
	javax.servlet.ServletRequest httpRequest;
	
	private String isEnabledUpdate;
	private String applNo;//案件號碼	VARCHAR(11)
	
	private String updateType;//接收前端按鈕狀態
	
	private String inOrOutcreator;//案件擁有者

	private String notifierUserID;//內網選擇外網帳號
	
	private String notifierDeptID;
	private String notifierDept;
	

	private String commonUser;
	
	//基本資料--通報訊息-------------------------------------------------------------------
	private String occurDate;//發生日期	VARCHAR(7)
	private String notifierRevDate;//通報者獲知日期	VARCHAR(7)
	private String notifierRepDate;//通報中心接獲通報日期	VARCHAR(7)
	private String caseSource;//案例來源	VARCHAR(4)
	private String caseSourceOutCountry;//案例來源-國外-國家	NVARCHAR(20)
	private String caseSourceInHospital;//案例來源-國內-試驗醫院	NVARCHAR(10)
	private String caseSourceInDoctor;//案例來源-國內-試驗醫師	NVARCHAR(30)
	private String reportKind;//報告類別	VARCHAR(2)
	private String trackingNum;//報告類別-追蹤報告-次數	VARCHAR(2)
	private String testName;//試驗名稱	NVARCHAR(20)
	private String fdaNum;//衛生福利主管機關核准函-文號	NVARCHAR(20)
	private String fdaOptions;//衛生福利主管機關核准函-選項	VARCHAR(2)
	private String approvedUnits;//核准單位 	VARCHAR(2)
	private String approvedUnitsOther;//核准單位-其他-描述	NVARCHAR(30)
	private String firmTestNo;//廠商試驗編號	NVARCHAR(20)
	
	//基本資料--通報者資訊-------------------------------------------------------------------
	private String notifier;//
	private String notifierName;//通報者-姓名	NVARCHAR(10)
	private String notifierTitleDept;//通報者-服務機構	NVARCHAR(30)
	private String notifierAreaCode;//通報者-區域號碼	VARCHAR(3)
	private String notifierTel;//通報者-電話	VARCHAR(10)
	private String notifierTelExt;//通報者-電話	VARCHAR(10)
	private String notifierCounty;
	private String notifierZipCode;
	private String notifierAddress;//通報者-地址	NVARCHAR(50)
	private String notifierEmail;//通報者-電子郵件	VARCHAR(30)
	private String notifierType;//通報者-屬性	VARCHAR(2)
	private String notifierTitle;//通報者-醫療人員職稱	NVARCHAR(20)
	
	//基本資料--病人相關資料-------------------------------------------------------------------
	private String patientId;//病人基本資料-識別代碼	VARCHAR(10)
	private String patientSex;//病人基本資料-性別	VARCHAR(1)
	private String patientBirth;//病人基本資料-出生日期	VARCHAR(7)
	private String patientAge;//病人基本資料-年齡	VARCHAR(3)
	private String patientWeight;//病人基本資料-體重	VARCHAR(3)
	private String patientHeigth;//病人基本資料-身高	VARCHAR(3)
	
	
	//不良事件--不良反應結果-------------------------------------------------------------------
	private String badReactionResults;//不良反應結果	VARCHAR(2)
	private String badReactionResultsDeathDate;//不良反應結果-A死亡-日期	VARCHAR(7)
	private String badReactionResultsDeathReason;//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
	private String badReactionResultsOther;//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
	
	private String isAdverseEvents;//是否為非預期之不良事件 VARCHAR(1)
	
	//不良事件--不良事件描述-------------------------------------------------------------------
	private String bulletinDate[];//日期	VARCHAR(7)
	private String position[];//發生不良反應之部位	NVARCHAR(30)
	private String symptom[];//症狀	NVARCHAR(30)
	private String severity[];//嚴重程度	NVARCHAR(30)
	private String dealWith[];//處置	NVARCHAR(30)
	private String med2002DbType[];
	private String med2002DbTypeId[];
	
	
	//不良事件--懷疑的醫療器材-------------------------------------------------------------------
	private String medPermit;//懷疑的醫療器材-許可證字號-字	VARCHAR(10)
	private String medPermitNumber;//懷疑的醫療器材-許可證字號-號	VARCHAR(10)
	private String medTestMedical ;//懷疑的醫療器材-試驗醫材名稱	NVARCHAR(30)
	private String medMainCategory;//懷疑的醫療器材-醫材主類別
	private String medMainCategoryCodeName;
	private String medSecCategory;//懷疑的醫療器材-醫材次類別
	private String medSecCategoryCodeName;
	private String medType;//懷疑的醫療器材-器材種類	NVARCHAR(30)
	private String medFactory;//懷疑的醫療器材-製造商	NVARCHAR(30)
	private String medSupplier;//懷疑的醫療器材-供應商	NVARCHAR(30)
	private String medModel;//懷疑的醫療器材-型號	VARCHAR(10)
	private String medNo;//懷疑的醫療器材-序號	VARCHAR(10)
	private String medLotNum;//懷疑的醫療器材-批號	VARCHAR(10)
	private String medManufactureDate;//懷疑的醫療器材-製造日期	VARCHAR(7)
	private String medOperator;//懷疑的醫療器材-醫療器材操作者	VARCHAR(2)
	private String medUseDate;//懷疑的醫療器材-使用日期	VARCHAR(7)
	private String medStopDate;//懷疑的醫療器材-停用日期	VARCHAR(7)
	private String medUseReason;//懷疑的醫療器材-使用原因	NVARCHAR(30)
	private String medUseIsYn;//懷疑的醫療器材-是否可提供器材作評估	VARCHAR(1)
	private String medYesSoruce;//懷疑的醫療器材-是否可提供器材作評估-是-取得來源	NVARCHAR(30)
	private String medNoReturn;//懷疑的醫療器材-是否可提供器材作評估-否-已於退還	VARCHAR(1)
	private String medNoReturnDate;//懷疑的醫療器材-是否可提供器材作評估-否-已於退還日期	VARCHAR(7)
	private String medOnceUseMed;//懷疑的醫療器材-曾經使用同類醫材之經驗	VARCHAR(1)
	private String medOnceUseMedName;//懷疑的醫療器材-曾經使用同類醫材之經驗-是-醫材	NVARCHAR(30)
	private String medOnceUseBadReaction;//懷疑的醫療器材-曾經使用同類醫材之經驗-是-不良反應	NVARCHAR(30)
	private String medStopMedMitigate;//懷疑的醫療器材-停用後不良反應是否減輕	VARCHAR(1)
	
	private String onceSameReaction;//併用之醫療器材或藥品-再使用是否出現同樣反應	VARCHAR(1)
	private String sameTimeUse;//併用之醫療器材或藥品-是否同時使用	VARCHAR(2)
	private String sameTimeUseOther;//併用之醫療器材或藥品-是否同時使用-其他描述	NVARCHAR(30)
	private String medSea;//試驗醫師評估醫材與SAE之因果關係	VARCHAR(2)
	private String procedureSea;//試驗醫師評估手續程序與SAE之因果關係	VARCHAR(2)
	private String noticeSponsor;//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者	VARCHAR(1)
	private String noticeSponsorWritten;//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者-書面	VARCHAR(1)
	private String noticeIRB;//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會	VARCHAR(1)
	private String noticeIRBWritten;//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會-書面	VARCHAR(1)
	private String noticeApprovedUnits;//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位	VARCHAR(1)
	private String noticeApprovedUnitsWritten;//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位-書面	VARCHAR(1)
	
	private String otherDesc;//其他相關資料
			
	//不良事件--相關檢查及檢驗數據檔	-------------------------------------------------------------------
	private String testDate[];//檢驗日期	VARCHAR(7)
	private String testItems[];//檢驗項目	NVARCHAR(30)
	private String testNum[];//檢驗數據	NVARCHAR(30)
	private String med2003DbType[];
	private String med2003DbTypeId[];
	
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
	private String med2004DbType[];
	private String med2004DbTypeId[];
	
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
	private String med2005DbType[];
	private String med2005DbTypeId[];
	
	
	public void doInsert()throws Exception
	{
		
	}
	
	public void doUpdateType() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByMedIN5101F(this);
	}
	
	public String getMed2002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med2002Db where 1=1 and med2001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med2002Db o = (Med2002Db) it.next();
				sb.append("addMed2002Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getBulletinDate()).append("'");//發生日期
				sb.append(",'").append(o.getPosition()).append("'");//部位
				sb.append(",'").append(o.getSymptom()).append("'");//症狀
				sb.append(",'").append(o.getSeverity()).append("'");//嚴重程度
				sb.append(",'").append(o.getDealWith()).append("');\n");//處置
			}
		}
		return sb.toString(); 
	}
	
	public String getMed2003DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med2003Db where 1=1 and med2001Db.id="+Common.get(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med2003Db o = (Med2003Db) it.next();
				sb.append("addMed2003Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getTestDate()).append("'");//檢驗日期
				sb.append(",'").append(o.getTestItems()).append("'");//檢驗項目
				sb.append(",'").append(o.getTestNum()).append("');\n");//檢驗數據
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed2004DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med2004Db where 1=1 and med2001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med2004Db o = (Med2004Db) it.next();
				sb.append("addMed2004Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getPermit()).append("'");//許可證字
				sb.append(",'").append(o.getPermitNumber()).append("'");//許可證號
				sb.append(",'").append(o.getcName()).append("'");//品名
				sb.append(",'").append(o.getPermitFirm()).append("'");//許可證申請商
				sb.append(",'").append(o.getModel()).append("'");//型號
				sb.append(",'").append(o.getMainCategory()).append("'");//器材主類別
				sb.append(",'").append(o.getUseDate()).append("'");//使用日期
				sb.append(",'").append(o.getUseReason()).append("');\n");//使用原因
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed2005DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med2005Db where 1=1 and med2001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med2005Db o = (Med2005Db) it.next();
				sb.append("addMed2005Db('").append(Common.get(o.getId())).append("'");
				
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
	  MEDIN5101F obj = this;
		
	  Med2001Db c = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(obj.getId()));
	  
	  java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");   //通報職稱
		
	  System.out.println("[TCBW]-[MEDIN0501F]-[doQueryOne] : " + obj.getId());
	  
	  if(c!=null)
	  {	
		  obj.setIsEnabledUpdate("Y");
		  
		  obj.setApplNo(c.getApplNo());
		  obj.setStatusType(c.getStatus());//案件狀態
		  //基本資料--通報訊息-------------------------------------------------------------------
		  obj.setOccurDate(c.getOccurDate());//發生日期
		  obj.setNotifierRevDate(c.getNotifierRevDate());//通報者獲知日期
		  obj.setNotifierRepDate(c.getNotifierRepDate());//通報中心接獲通報日期
          obj.setCaseSource(c.getCaseSource());//案例來源
		  obj.setCaseSourceOutCountry(c.getCaseSourceOutCountry());//案例來源-國外-國家
		  obj.setCaseSourceInHospital(c.getCaseSourceInHospital());//案例來源-國內-試驗醫院
		  obj.setCaseSourceInDoctor(c.getCaseSourceInDoctor());//案例來源-國內-試驗醫師
		  obj.setReportKind(c.getReportKind());//報告類別。1:初始報告,2:追蹤報告
		  obj.setTrackingNum(c.getTrackingNum());//報告類別-追蹤報告-次數
		  obj.setTestName(c.getTestName());//試驗名稱
		  obj.setFdaNum(c.getFdaNum());//衛生福利主管機關核准函-文號	
		  obj.setFdaOptions(c.getFdaOptions());//衛生福利主管機關核准函-選項。1;查驗登記用;2;學術研究用
		  obj.setApprovedUnits(c.getApprovedUnits());//核准單位。1;醫事司;2;食品藥物管理署;3;其他
	      obj.setApprovedUnitsOther(c.getApprovedUnitsOther());//核准單位-其他-描述
		  obj.setFirmTestNo(c.getFirmTestNo());//廠商試驗編號
		  
		  //基本資料--通報者資訊-------------------------------------------------------------------
		  obj.setNotifierName(c.getNotifierName());//通報者-姓名
		  //obj.setNotifierTitleDept(c.getNotifierTitleDept());//通報者-服務機構
		  obj.setNotifierAreaCode(c.getNotifierAreaCode());//通報者-區域號碼	
		  obj.setNotifierTel(c.getNotifierTel());//通報者-電話
		  obj.setNotifierTelExt(c.getNotifierTelExt());//通報者-電話
		  obj.setNotifierCounty(c.getNotifierCounty());
		  obj.setNotifierZipCode(c.getNotifierZipCode());
		  obj.setNotifierAddress(c.getNotifierAddress());//通報者-地址
		  obj.setNotifierEmail(c.getNotifierEamil());//通報者-電子郵件
		  obj.setNotifierType(c.getNotifierType());//通報者-屬性。1:醫療人員,2:廠商
		  obj.setNotifierTitle(titleMap.get(c.getNotifierTitle()));//通報者職稱
		  obj.setNotifierDeptID(c.getNotifierDeptID());//
		  obj.setNotifierDept(c.getNotifierDept());//
		  
		  
		  //基本資料--病人相關資料-------------------------------------------------------------------
		  obj.setPatientId(c.getPatientId());//病人基本資料-識別代碼
		  obj.setPatientSex(c.getPatientSex());//病人基本資料-性別
		  obj.setPatientBirth(c.getPatientBirth());//病人基本資料-出生日期
		  obj.setPatientAge(c.getPatientAge());//病人基本資料-年齡
		  obj.setPatientWeight(c.getPatientWeight());//病人基本資料-體重
		  obj.setPatientHeigth(c.getPatientHeigth());//病人基本資料-身高
		  
		  //不良事件描述--是否為非預期之不良事件------------------------------------------------
		  obj.setIsAdverseEvents(c.getIsAdverseEvents());
		  
		  //不良事件--懷疑的醫療器材-------------------------------------------------------------------
		  obj.setMedPermit(c.getMedPermit());//懷疑的醫療器材-許可證字號-字
		  obj.setMedPermitNumber(c.getMedPermitNumber());//懷疑的醫療器材-許可證字號-號
		  obj.setMedTestMedical(c.getMedTestMedical());//懷疑的醫療器材-試驗醫材名稱	
		  obj.setMedMainCategory(c.getMedMainCategoryCode());		//醫材主類別
		  obj.setMedMainCategoryCodeName(c.getMedMainCategory());			//醫材主類別
			  
		  obj.setMedSecCategory(c.getMedSecCategoryCode());			//醫材次類別
		  obj.setMedSecCategoryCodeName(c.getMedSecCategory());				//醫材次類別
		  obj.setMedType(c.getMedType());//懷疑的醫療器材-器材種類
		  obj.setMedFactory(c.getMedFactory());//懷疑的醫療器材-製造商
		  obj.setMedSupplier(c.getMedSupplier());//懷疑的醫療器材-供應商
		  obj.setMedModel(c.getMedModel());//懷疑的醫療器材-型號
		  obj.setMedNo(c.getMedNo());//懷疑的醫療器材-序號
		  obj.setMedLotNum(c.getMedLotNum());//懷疑的醫療器材-批號
		  obj.setMedManufactureDate(c.getMedManufactureDate());//懷疑的醫療器材-製造日期
		  obj.setMedOperator(c.getMedOperator());//懷疑的醫療器材-醫療器材操作者。1;醫療人員;2;病人或其家屬;3;其他
		  obj.setMedUseDate(c.getMedUseDate());//懷疑的醫療器材-使用日期
		  obj.setMedStopDate(c.getMedStopDate());//懷疑的醫療器材-停用日期
		  obj.setMedUseReason(c.getMedUseReason());//懷疑的醫療器材-使用原因
		  obj.setMedUseIsYn(c.getMedUseIsYn());//懷疑的醫療器材-是否可提供器材作評估
		  obj.setMedYesSoruce(c.getMedYesSoruce());//懷疑的醫療器材-是否可提供器材作評估-是-取得來源
		  obj.setMedNoReturn(c.getMedNoReturn());//懷疑的醫療器材-是否可提供器材作評估-否-已於退還
		  obj.setMedNoReturnDate(c.getMedNoReturnDate());//懷疑的醫療器材-是否可提供器材作評估-否-已於退還日期
		  obj.setMedOnceUseMed(c.getMedOnceUseMed());//懷疑的醫療器材-曾經使用同類醫材之經驗。Y:是,N:否,O:無法得知
		  obj.setMedOnceUseMedName(c.getMedOnceUseMedName());//懷疑的醫療器材-曾經使用同類醫材之經驗-是-醫材名稱
		  obj.setMedOnceUseBadReaction(c.getMedOnceUseBadReaction());//懷疑的醫療器材-曾經使用同類醫材之經驗-是-若發生不良反應請描述
		  obj.setMedStopMedMitigate(c.getMedStopMedMitigate());//懷疑的醫療器材-停用後不良反應是否減輕
		  obj.setOnceSameReaction(c.getOnceSameReaction());//併用之醫療器材或藥品-再使用是否出現同樣反應

		  obj.setSameTimeUse(c.getSameTimeUse());//併用之醫療器材或藥品-是否同時使用 
		  obj.setSameTimeUseOther(c.getSameTimeUseOther());//併用之醫療器材或藥品-是否同時使用-其他描述
		  obj.setMedSea(c.getMedSea());//試驗醫師評估醫材與SAE之因果關係
		  obj.setProcedureSea(c.getProcedureSea());//試驗醫師評估手續程序與SAE之因果關係
		  obj.setNoticeSponsor(c.getNoticeSponsor());//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者
		  obj.setNoticeSponsorWritten(c.getNoticeSponsorWritten());//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者-書面
		  obj.setNoticeIRB(c.getNoticeIRB());//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會
		  obj.setNoticeIRBWritten(c.getNoticeIRBWritten());//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會-書面
		  obj.setNoticeApprovedUnits(c.getNoticeApprovedUnits());//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位
		  obj.setNoticeApprovedUnitsWritten(c.getNoticeApprovedUnitsWritten());//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位-書面
          
		  obj.setBadReactionResults(c.getBadReactionResults());//不良反應結果
		  
		  obj.setBadReactionResultsDeathDate(c.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	
		  obj.setBadReactionResultsDeathReason(c.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因
		  obj.setBadReactionResultsOther(c.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	
		  
		  obj.setInOrOutcreator(c.getInOrOutcreator());
		  
		  obj.setOtherDesc(c.getOtherDesc());
	   }
	   return obj;
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
		
		String hql = " from Con0001Db where fileKind='MED' ";
		       hql += " and systemType like 'MED030001%' ";
		       hql += " and dbName='Med2001Db' " ;
		       hql += " and upLoadId="+getId();
		
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
    
	
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}

	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	
	public String getIsEnabledUpdate() {
		return checkGet(isEnabledUpdate);
	}
	public void setIsEnabledUpdate(String isEnabledUpdate) {
		this.isEnabledUpdate = checkSet(isEnabledUpdate);
	}
	public String getIsNeedBackQuery() {
		return checkGet(isNeedBackQuery);
	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {
		this.isNeedBackQuery = checkSet(isNeedBackQuery);
	}
	

	public String[] getTestDate() {
		return testDate;
	}
	public void setTestDate(String[] testDate) {
		this.testDate = testDate;
	}
	

	public String[] getMed2003DbType() {
		return med2003DbType;
	}

	public void setMed2003DbType(String[] med2003DbType) {
		this.med2003DbType = med2003DbType;
	}

	public String[] getMed2003DbTypeId() {
		return med2003DbTypeId;
	}

	public void setMed2003DbTypeId(String[] med2003DbTypeId) {
		this.med2003DbTypeId = med2003DbTypeId;
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

	public String getMedMainCategory() {
		return checkGet(medMainCategory);
	}
	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = checkSet(medMainCategory);
	}
	
	public String getMedMainCategoryCodeName() {
		return checkGet(medMainCategoryCodeName);
	}

	public void setMedMainCategoryCodeName(String medMainCategoryCodeName) {
		this.medMainCategoryCodeName = checkSet(medMainCategoryCodeName);
	}
	
	public String getMedSecCategory() {
		return checkGet(medSecCategory);
	}
	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = checkSet(medSecCategory);
	}
	
	public String getMedSecCategoryCodeName() {
		return checkGet(medSecCategoryCodeName);
	}

	public void setMedSecCategoryCodeName(String medSecCategoryCodeName) {
		this.medSecCategoryCodeName = checkSet(medSecCategoryCodeName);
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
	public String getNotifierName() {
		return checkGet(notifierName);
	}
	public void setNotifierName(String notifierName) {
		this.notifierName = checkSet(notifierName);
	}
	public String getNotifierTitleDept() {
		return checkGet(notifierTitleDept);
	}
	public void setNotifierTitleDept(String notifierTitleDept) {
		this.notifierTitleDept = checkSet(notifierTitleDept);
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
	public String getNotifierEmail() {
		return checkGet(notifierEmail);
	}
	public void setNotifierEmail(String notifierEmail) {
		this.notifierEmail = checkSet(notifierEmail);
	}
	public String getNotifierType() {
		return checkGet(notifierType);
	}
	public void setNotifierType(String notifierType) {
		this.notifierType = checkSet(notifierType);
	}
	public String getNotifierTitle() {
		return checkGet(notifierTitle);
	}
	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = checkSet(notifierTitle);
	}
	public String getCaseSource() {
		return checkGet(caseSource);
	}
	public void setCaseSource(String caseSource) {
		this.caseSource = checkSet(caseSource);
	}
	public String getCaseSourceInHospital() {
		return checkGet(caseSourceInHospital);
	}
	public void setCaseSourceInHospital(String caseSourceInHospital) {
		this.caseSourceInHospital = checkSet(caseSourceInHospital);
	}
	public String getCaseSourceOutCountry() {
		return checkGet(caseSourceOutCountry);
	}
	public void setCaseSourceOutCountry(String caseSourceOutCountry) {
		this.caseSourceOutCountry = checkSet(caseSourceOutCountry);
	}
	public String getCaseSourceInDoctor() {
		return checkGet(caseSourceInDoctor);
	}
	public void setCaseSourceInDoctor(String caseSourceInDoctor) {
		this.caseSourceInDoctor = checkSet(caseSourceInDoctor);
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
	public String getTestName() {
		return checkGet(testName);
	}
	public void setTestName(String testName) {
		this.testName = checkSet(testName);
	}
	public String getFdaNum() {
		return checkGet(fdaNum);
	}
	public void setFdaNum(String fdaNum) {
		this.fdaNum = checkSet(fdaNum);
	}
	public String getFdaOptions() {
		return checkGet(fdaOptions);
	}
	public void setFdaOptions(String fdaOptions) {
		this.fdaOptions = checkSet(fdaOptions);
	}
	public String getApprovedUnits() {
		return checkGet(approvedUnits);
	}
	public void setApprovedUnits(String approvedUnits) {
		this.approvedUnits = checkSet(approvedUnits);
	}
	public String getApprovedUnitsOther() {
		return checkGet(approvedUnitsOther);
	}
	public void setApprovedUnitsOther(String approvedUnitsOther) {
		this.approvedUnitsOther = checkSet(approvedUnitsOther);
	}
	public String getFirmTestNo() {
		return checkGet(firmTestNo);
	}
	public void setFirmTestNo(String firmTestNo) {
		this.firmTestNo = checkSet(firmTestNo);
	}
	public String getPatientId() {
		return checkGet(patientId);
	}
	public void setPatientId(String patientId) {
		this.patientId = checkSet(patientId);
	}
	public String getPatientSex() {
		return checkGet(patientSex);
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = checkSet(patientSex);
	}
	public String getPatientBirth() {
		return checkGet(patientBirth);
	}
	public void setPatientBirth(String patientBirth) {
		this.patientBirth = checkSet(patientBirth);
	}
	public String getPatientAge() {
		return checkGet(patientAge);
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = checkSet(patientAge);
	}
	public String getPatientWeight() {
		return checkGet(patientWeight);
	}
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = checkSet(patientWeight);
	}
	public String getPatientHeigth() {
		return checkGet(patientHeigth);
	}
	public void setPatientHeigth(String patientHeigth) {
		this.patientHeigth = checkSet(patientHeigth);
	}
	

	public String getBadReactionResults() {
		return checkGet(badReactionResults);
	}

	public void setBadReactionResults(String badReactionResults) {
		this.badReactionResults = checkSet(badReactionResults);
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

	public String getMedTestMedical() {
		return checkGet(medTestMedical);
	}
	public void setMedTestMedical(String medTestMedical) {
		this.medTestMedical = checkSet(medTestMedical);
	}
	public String getMedType() {
		return checkGet(medType);
	}
	public void setMedType(String medType) {
		this.medType = checkSet(medType);
	}
	public String getMedFactory() {
		return checkGet(medFactory);
	}
	public void setMedFactory(String medFactory) {
		this.medFactory = checkSet(medFactory);
	}
	public String getMedSupplier() {
		return checkGet(medSupplier);
	}
	public void setMedSupplier(String medSupplier) {
		this.medSupplier = checkSet(medSupplier);
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
	public String getMedManufactureDate() {
		return checkGet(medManufactureDate);
	}
	public void setMedManufactureDate(String medManufactureDate) {
		this.medManufactureDate = checkSet(medManufactureDate);
	}
	public String getMedOperator() {
		return checkGet(medOperator);
	}
	public void setMedOperator(String medOperator) {
		this.medOperator = checkSet(medOperator);
	}
	public String getMedStopDate() {
		return checkGet(medStopDate);
	}
	public void setMedStopDate(String medStopDate) {
		this.medStopDate = checkSet(medStopDate);
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
	public String getMedUseIsYn() {
		return checkGet(medUseIsYn);
	}
	public void setMedUseIsYn(String medUseIsYn) {
		this.medUseIsYn = checkSet(medUseIsYn);
	}
	public String getMedYesSoruce() {
		return checkGet(medYesSoruce);
	}
	public void setMedYesSoruce(String medYesSoruce) {
		this.medYesSoruce = checkSet(medYesSoruce);
	}
	public String getMedNoReturn() {
		return checkGet(medNoReturn);
	}
	public void setMedNoReturn(String medNoReturn) {
		this.medNoReturn = checkSet(medNoReturn);
	}
	public String getMedNoReturnDate() {
		return checkGet(medNoReturnDate);
	}
	public void setMedNoReturnDate(String medNoReturnDate) {
		this.medNoReturnDate = checkSet(medNoReturnDate);
	}
	public String getMedOnceUseMed() {
		return checkGet(medOnceUseMed);
	}
	public void setMedOnceUseMed(String medOnceUseMed) {
		this.medOnceUseMed = checkSet(medOnceUseMed);
	}
	public String getMedOnceUseMedName() {
		return checkGet(medOnceUseMedName);
	}
	public void setMedOnceUseMedName(String medOnceUseMedName) {
		this.medOnceUseMedName = checkSet(medOnceUseMedName);
	}
	public String getMedOnceUseBadReaction() {
		return checkGet(medOnceUseBadReaction);
	}
	public void setMedOnceUseBadReaction(String medOnceUseBadReaction) {
		this.medOnceUseBadReaction = checkSet(medOnceUseBadReaction);
	}
	public String getMedStopMedMitigate() {
		return checkGet(medStopMedMitigate);
	}
	public void setMedStopMedMitigate(String medStopMedMitigate) {
		this.medStopMedMitigate = checkSet(medStopMedMitigate);
	}
	public String getOnceSameReaction() {
		return checkGet(onceSameReaction);
	}
	public void setOnceSameReaction(String onceSameReaction) {
		this.onceSameReaction = checkSet(onceSameReaction);
	}
	public String getSameTimeUse() {
		return checkGet(sameTimeUse);
	}
	public void setSameTimeUse(String sameTimeUse) {
		this.sameTimeUse = checkSet(sameTimeUse);
	}
	public String getSameTimeUseOther() {
		return checkGet(sameTimeUseOther);
	}
	public void setSameTimeUseOther(String sameTimeUseOther) {
		this.sameTimeUseOther = checkSet(sameTimeUseOther);
	}
	public String getMedSea() {
		return checkGet(medSea);
	}
	public void setMedSea(String medSea) {
		this.medSea = checkSet(medSea);
	}
	public String getProcedureSea() {
		return checkGet(procedureSea);
	}
	public void setProcedureSea(String procedureSea) {
		this.procedureSea = checkSet(procedureSea);
	}
	public String getNoticeSponsor() {
		return checkGet(noticeSponsor);
	}
	public void setNoticeSponsor(String noticeSponsor) {
		this.noticeSponsor = checkSet(noticeSponsor);
	}
	public String getNoticeSponsorWritten() {
		return checkGet(noticeSponsorWritten);
	}
	public void setNoticeSponsorWritten(String noticeSponsorWritten) {
		this.noticeSponsorWritten = checkSet(noticeSponsorWritten);
	}
	public String getNoticeIRB() {
		return checkGet(noticeIRB);
	}
	public void setNoticeIRB(String noticeIRB) {
		this.noticeIRB = checkSet(noticeIRB);
	}
	public String getNoticeIRBWritten() {
		return checkGet(noticeIRBWritten);
	}
	public void setNoticeIRBWritten(String noticeIRBWritten) {
		this.noticeIRBWritten = checkSet(noticeIRBWritten);
	}
	public String getNoticeApprovedUnits() {
		return checkGet(noticeApprovedUnits);
	}
	public void setNoticeApprovedUnits(String noticeApprovedUnits) {
		this.noticeApprovedUnits = checkSet(noticeApprovedUnits);
	}
	public String getNoticeApprovedUnitsWritten() {
		return checkGet(noticeApprovedUnitsWritten);
	}
	public void setNoticeApprovedUnitsWritten(String noticeApprovedUnitsWritten) {
		this.noticeApprovedUnitsWritten = checkSet(noticeApprovedUnitsWritten);
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

	
	


	public String getBadReactionResultsDeathDate() {
		return checkGet(badReactionResultsDeathDate);
	}

	public void setBadReactionResultsDeathDate(String badReactionResultsDeathDate) {
		this.badReactionResultsDeathDate = checkSet(badReactionResultsDeathDate);
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

	public String[] getMed2002DbType() {
		return med2002DbType;
	}

	public void setMed2002DbType(String[] med2002DbType) {
		this.med2002DbType = med2002DbType;
	}

	public String[] getMed2002DbTypeId() {
		return med2002DbTypeId;
	}

	public void setMed2002DbTypeId(String[] med2002DbTypeId) {
		this.med2002DbTypeId = med2002DbTypeId;
	}

	public String getIsAdverseEvents() {
		return checkGet(isAdverseEvents);
	}
	public void setIsAdverseEvents(String isAdverseEvents) {
		this.isAdverseEvents = checkSet(isAdverseEvents);
	}
	public String getUpdateType() {		return checkGet(updateType);	}
	public void setUpdateType(String updateType) {		this.updateType = checkSet(updateType);	}

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

	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
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
		this.permitNumber =permitNumber;
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

	public String[] getMainCategoryCode() {
		return mainCategoryCode;
	}

	public void setMainCategoryCode(String[] mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
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

	public String[] getMed2004DbType() {
		return med2004DbType;
	}

	public void setMed2004DbType(String[] med2004DbType) {
		this.med2004DbType = med2004DbType;
	}

	public String[] getMed2004DbTypeId() {
		return med2004DbTypeId;
	}

	public void setMed2004DbTypeId(String[] med2004DbTypeId) {
		this.med2004DbTypeId = med2004DbTypeId;
	}

	public String[] getcName2() {
		return checkGet(cName2);
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

	public String[] getMed2005DbType() {
		return med2005DbType;
	}

	public void setMed2005DbType(String[] med2005DbType) {
		this.med2005DbType = med2005DbType;
	}

	public String[] getMed2005DbTypeId() {
		return med2005DbTypeId;
	}

	public void setMed2005DbTypeId(String[] med2005DbTypeId) {
		this.med2005DbTypeId = med2005DbTypeId;
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

	public String getOtherDesc() {
		return otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
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

	public String getNotifier() {
		return checkGet(notifier);
	}

	public void setNotifier(String notifier) {
		this.notifier = checkSet(notifier);
	}
	
	
	
}
