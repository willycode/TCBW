package com.kangdainfo.tcbw.view.medex;

import javax.swing.table.DefaultTableModel;

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
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.model.bo.Med5002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;
import com.kangdainfo.tcbw.model.bo.Med5004Db;
import com.kangdainfo.tcbw.model.bo.Med5005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class MEDEX5102F extends MEDEX5101F
{
	

	private String isNeedBackQuery;//
	javax.servlet.ServletRequest httpRequest;
	
	private String isEnabledUpdate;
	private String applNo;//案件號碼	VARCHAR(11)
	private String status;//狀態
	private String updateType;//接收前端按鈕狀態
	
	private String inOrOutcreator;//案件擁有者

	private String notifierUserID;//內網選擇外網帳號
	
	private String changeTabValue;
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
	private String notifierName;//通報者-姓名	NVARCHAR(10)
	private String notifierTitleDept;//通報者-服務機構	NVARCHAR(30)
	private String notifierAreaCode;//通報者-區域號碼	VARCHAR(3)
	private String notifierTelExt;
	private String notifierDeptID;
	private String notifierDept;
	private String notifierTel;//通報者-電話	VARCHAR(10)
	private String notifierAddress;//通報者-地址	NVARCHAR(50)
	private String notifierCounty;
	private String notifierZipCode;
	private String notifierEamil;//通報者-電子郵件	VARCHAR(30)
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
	private String med5002DbType[];
	private String med5002DbTypeId[];
	
	//不良事件--其他相關資料-------------------------------------------------------------------
	private String otherDesc;//其他相關資料
	
	
	//不良事件--懷疑的醫療器材-------------------------------------------------------------------
	private String medPermit;//懷疑的醫療器材-許可證字號-字	VARCHAR(10)
	private String medPermitNumber;//懷疑的醫療器材-許可證字號-號	VARCHAR(10)
	private String medTestMedical ;//懷疑的醫療器材-試驗醫材名稱	NVARCHAR(30)

	private String medMainCategory;//懷疑的醫療器材-醫材主類別
	private String medMainCategoryCodeName;//
	private String medSecCategory;//懷疑的醫療器材-醫材次類別
	private String medSecCategoryCodeName;//
	
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
	
			
	//不良事件--相關檢查及檢驗數據檔	-------------------------------------------------------------------
	private String testDate[];//檢驗日期	VARCHAR(7)
	private String testItems[];//檢驗項目	NVARCHAR(30)
	private String testNum[];//檢驗數據	NVARCHAR(30)
	private String med5003DbType[];
	private String med5003DbTypeId[];
	
	//不良反應--併用醫療器材	-------------------------------------------------------------------
	private String cName[];//品名		    NVARCHAR(30)
	private String permit[];//許可證字  	NVARCHAR(30)
	private String permitNumber[];//許可證號	NVARCHAR(30)
	private String permitFirm[];//許可證申請商	NVARCHAR(30)
	private String model[];//型號		NVARCHAR(20)
	private String mainCategoryCode[];//器材主類別		NVARCHAR(20)
	private String mainCategory[];//器材主類別		NVARCHAR(50)
	private String useDate[];//使用日期	VARCHAR(7)
	private String useReason[];//使用原因	NVARCHAR(50)
	private String med5004DbType[];
	private String med5004DbTypeId[];
	
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
	private String med5005DbType[];
	private String med5005DbTypeId[];
	
	
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
			System.out.println("[TCBW]-[MEDEX5102F]-[新增]-[無法辨別登入的使用者]");
		}
		
		Med5001Db obj = new Med5001Db();
		
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
		obj.setNotifierDept(c.getUserName());
		obj.setNotifierType(c.getCommonDepartment()!=null?Common.get(c.getCommonDepartment().getShortCode()):"");
		obj.setNotifierTitle(c.getJobTitle());
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		
		
		obj.setInOrOutcreator(c.getUserId());
	
		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	//送出、暫存、待上傳
	public void doUpdateType() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedex2Dao().updateByMedEX5102F(this);
	}
	
	@Override
	public Object doQueryOne() throws Exception 
	{
	  MEDEX5102F obj = this;
		
	  Med5001Db c = (Med5001Db)View.getObject(" from Med5001Db where id = " + Common.getLong(obj.getId()));
		
	  System.out.println("[TCBW]-[MEDEX5102F]-[doQueryOne] : " + obj.getId());

	  obj.setIsEnabledUpdate("Y");
	  java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");   //通報職稱
	  if(c!=null)
	  {	
		  
		  obj.setStatus(c.getStatus());
		  obj.setApplNo(c.getApplNo());
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
	
		  obj.setNotifierAreaCode(c.getNotifierAreaCode());//通報者-區域號碼	
		  obj.setNotifierTel(c.getNotifierTel());//通報者-電話
		  obj.setNotifierTelExt(c.getNotifierTelExt());
		  
		  obj.setNotifierDeptID(c.getNotifierDeptID());	//通報者屬性-服務機構
		  obj.setNotifierDept(c.getNotifierDept());	
		  
		  obj.setNotifierAddress(c.getNotifierAddress());//通報者-地址
		  obj.setNotifierCounty(c.getNotifierCounty());
		  obj.setNotifierZipCode(c.getNotifierZipCode());
		  obj.setNotifierEamil(c.getNotifierEamil());//通報者-電子郵件
		  obj.setNotifierType(c.getNotifierType());//通報者-屬性。1:醫療人員,2:廠商
		  obj.setNotifierTitle(titleMap.get(c.getNotifierTitle()));//通報者-醫療人員職稱
		  
		  
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
		  obj.setMedMainCategory(c.getMedMainCategoryCode());		//醫材主類別
		  obj.setMedMainCategoryCodeName(c.getMedMainCategory());			//醫材主類別
			  
		  obj.setMedSecCategory(c.getMedSecCategoryCode());			//醫材次類別
		  obj.setMedSecCategoryCodeName(c.getMedSecCategory());				//醫材次類別
		  obj.setMedType(c.getMedType());//懷疑的醫療器材-器材種類
		  obj.setMedTestMedical(c.getMedTestMedical());//懷疑的醫療器材-試驗醫材名稱
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
		  obj.setOtherDesc(c.getOtherDesc());//其他相關資料 VARCHAR(100)//其他相關資料
		  
		  
	   }
	   return obj;
	}
	
	
	//主動補件
	public void doAutoReUpdate() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedex2Dao().updateByAutoReUpdateMedEX5102F(this,"Y");
	}
	
	public String getMed5002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med5002Db where 1=1 and med5001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();

			while (it.hasNext()) 
			{
				Med5002Db o = (Med5002Db) it.next();
				sb.append("addMed5002Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getBulletinDate()).append("'");//發生日期
				sb.append(",'").append(o.getPosition()).append("'");//部位
				sb.append(",'").append(o.getSymptom()).append("'");//症狀
				sb.append(",'").append(o.getSeverity()).append("'");//嚴重程度
				sb.append(",'").append(o.getDealWith()).append("');\n");//處置
			}
		}
		return sb.toString(); 
	}
	
	public String getMed5003DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med5003Db where 1=1 and med5001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med5003Db o = (Med5003Db) it.next();
				sb.append("addMed5003Db('").append(Common.get(o.getId())).append("'");
				sb.append(",'").append(o.getTestDate()).append("'");//檢驗日期
				sb.append(",'").append(o.getTestItems()).append("'");//檢驗項目
				sb.append(",'").append(o.getTestNum()).append("');\n");//檢驗數據
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed5004DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med5004Db where 1=1 and med5001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med5004Db o = (Med5004Db) it.next();
				sb.append("addMed5004Db('").append(Common.get(o.getId())).append("'");
				
				sb.append(",'").append(o.getPermit()).append("'");//許可證字
				sb.append(",'").append(o.getPermitNumber()).append("'");//許可證號
				sb.append(",'").append(o.getcName()).append("'");//品名
				sb.append(",'").append(o.getPermitFirm()).append("'");//許可證申請商
				sb.append(",'").append(o.getModel()).append("'");//型號
				sb.append(",'").append(o.getMainCategory()).append("'");//器材主類別
				sb.append(",'").append(o.getUseDate()).append("'");//使用日期
				sb.append(",'").append(o.getUseReason()).append("'");//使用原因
				sb.append(",'").append(o.getMainCategoryCode()).append("');\n");//器材主類別代碼
			}
		}
		
		return sb.toString(); 
	}
	
	public String getMed5005DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String hql = " from Med5005Db where 1=1 and med5001Db.id="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) 
			{
				Med5005Db o = (Med5005Db) it.next();
				sb.append("addMed5005Db('").append(Common.get(o.getId())).append("'");
				
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
	//案件列印
	public DefaultTableModel getDefaultTableModel() throws Exception
	{		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","occurDate","notifierRevDate","notifierRepDate","notifierName",
    			"notifierTitleDept","notifierAreaCode","notifierTel","notifierAddress","notifierEamil",
    			"notifierType","caseSource","reportKind","testName","fdaNum","fdaOptions","approvedUnits",
    			"firmTestNo","patientId","patientSex","patientBirth","patientAge","patientWeight",
    			"patientHeigth","badReactionResults","medTestMedical","medType","medFactory","medSupplier",
    			"medModel","medNo","medLotNum","medManufactureDate","medOperator","medPurchaseDate",
    			"medUseDate","medUseReason","medUseIsYn","medOnceUseMed","medStopMedMitigate",
    			"onceSameReaction","sameTimeUse","medSea","procedureSea","noticeSponsor","noticeSponsorWritten",
    			"noticeIRB","noticeIRBWritten","noticeApprovedUnits","noticeApprovedUnitsWritten","isAdverseEvents",
    			"obj1","obj2","otherDesc","obj3","obj4"
    			};		
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();

		String hql = "from Med5001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());

		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			java.util.Map<String, String> countryMap = TCBWCommon.getCommonCodeMap("COUC");
			
			for (int i=0; i<list.size(); i++) {
				Med5001Db obj = (Med5001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2); 
				rowArray[6]=Common.get(obj.getNotifierName());
				rowArray[7]=Common.get(obj.getNotifierDept());
				rowArray[8]=Common.get(obj.getNotifierAreaCode());
				rowArray[9]=Common.get(obj.getNotifierTel());
				rowArray[10]=Common.get(obj.getNotifierAddress());
				rowArray[11]=Common.get(obj.getNotifierEamil());
				if("3".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="■醫療人員 " + Common.get(obj.getNotifierTitle()) + "    □廠商\n　　　□民眾    □衛生單位";
				} else if("2".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□醫療人員_______    ■廠商\n　　　□民眾    □衛生單位";
				} else if("1".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□醫療人員_______    □廠商\n　　　■民眾    □衛生單位";
				} else if("4".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□醫療人員_______    □廠商\n　　　□民眾    ■衛生單位";
				} else {
					rowArray[12]="□醫療人員_______    □廠商\n　　　□民眾    □衛生單位";
				}
				if("out".equals(Common.get(obj.getCaseSource()))) {
					rowArray[13]="■ " + " 國外, " + Common.get(countryMap.get(obj.getCaseSourceOutCountry())) + "(國家)\n" + "□ 國內，試驗醫院：________\n　　　　 試驗醫師：________";
				} else if("in".equals(Common.get(obj.getCaseSource()))) {
					rowArray[13]="□ " + " 國外，________(國家)" + "\n" + "■ 國內，試驗醫院" + Common.get(obj.getCaseSourceInHospital()) + "\n" + "　　　　 試驗醫師：" + Common.get(obj.getCaseSourceInDoctor());  
				}
				
				if("1".equals(Common.get(obj.getReportKind()))) {
					rowArray[14]="■ " + "初始報告\n" + "□ 追蹤報告，第___次";
				} else if("2".equals(Common.get(obj.getReportKind()))) {
					rowArray[14]="□ " + "初始報告\n" + "■ 追蹤報告，第" + Common.get(obj.getTrackingNum()) + "次";
				} else {
					rowArray[14]="□ " + "初始報告\n" + "□ 追蹤報告，第___次";
				}
				rowArray[15]=Common.get(obj.getTestName());
				rowArray[16]=Common.get(obj.getFdaNum());
				
				if("1".equals(Common.get(obj.getFdaOptions()))) {
					rowArray[17]="■查驗登記用　□學術研究用";
				} else if("2".equals(Common.get(obj.getFdaOptions()))) {
					rowArray[17]="□查驗登記用　■學術研究用";
				} else {
					rowArray[17]="□查驗登記用　□學術研究用";
				}
				
				
				
				if("1".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="■ 醫事司　□ 食品藥物管理署　□ 其他：________";
				} else if("2".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="□ 醫事司　■ 食品藥物管理署　□ 其他：________";
				} else if("3".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="□ 醫事司　□ 食品藥物管理署　■ 其他：" + Common.get(obj.getApprovedUnitsOther());
				} else {
					rowArray[18]="□ 醫事司　□ 食品藥物管理署　□ 其他：________";
				}
				
				rowArray[19]=Common.get(obj.getFirmTestNo());
				rowArray[20]=Common.get(obj.getPatientId());
				if("M".equals(Common.get(obj.getPatientSex()))) {
					rowArray[21]="■ 男　□女";
				}else if("F".equals(Common.get(obj.getPatientSex()))) {
					rowArray[21]="□ 男　■女";
				}else {
					rowArray[21]="□ 男　□女";
				}
				rowArray[22]=Common.get(obj.getPatientBirth());
				rowArray[23]=Common.get(obj.getPatientAge());
				rowArray[24]=Common.get(obj.getPatientWeight());
				rowArray[25]=Common.get(obj.getPatientHeigth());
				String DeathDateAndReason = "，日期：" + Common.formatYYYMMDD(obj.getBadReactionResultsDeathDate(),2) + "，死亡原因：" + Common.get(obj.getBadReactionResultsDeathReason()); //死亡日期與原因
				String BadReactionResultsOther = Common.get(obj.getBadReactionResultsOther());
				rowArray[26]=getCommonCodeKindOfBadReactionResults(Common.get(obj.getBadReactionResults()),DeathDateAndReason,BadReactionResultsOther);
				rowArray[27]=Common.get(obj.getMedTestMedical());
				rowArray[28]=Common.get(obj.getMedType());
				rowArray[29]=Common.get(obj.getMedFactory());
				rowArray[30]=Common.get(obj.getMedSupplier());
				rowArray[31]=Common.get(obj.getMedModel());
				rowArray[32]=Common.get(obj.getMedNo());
				rowArray[33]=Common.get(obj.getMedLotNum());
				rowArray[34]=Common.formatYYYMMDD(obj.getMedManufactureDate(),2);
			
				rowArray[35]=getMedOperator(Common.get(obj.getMedOperator()));

				rowArray[36]=Common.formatYYYMMDD(obj.getMedUseDate(),2);
				rowArray[37]=Common.formatYYYMMDD(obj.getMedStopDate(),2);
				rowArray[38]=Common.get(obj.getMedUseReason());
				if("Y".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="■ 是 取得來源" + Common.get(obj.getMedYesSoruce()) + "\n□ 否" + "　□ 已於____年____月____日 退還給廠商";
				} else if("N".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="□ 是 取得來源____________\n" + "■ 否" + "　□ 已於____年____月____日 退還給廠商";
				} else if("O".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="□ 是 取得來源____________\n" + "□ 否" + "　■ 已於" + Common.formatYYYMMDD(obj.getMedNoReturnDate()) + " 退還給廠商";
				} else {
					rowArray[39]="□ 是 取得來源____________\n" + "□ 否" + "　□ 已於____年____月____日 退還給廠商";

				}
				
				if("Y".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="■ 是　醫材：" + Common.get(obj.getMedOnceUseMedName()) + "不良反應：" + Common.get(obj.getMedOnceUseBadReaction()) + "　□ 否 □ 無法得知";
				} else if("N".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　■ 否 □ 無法得知";
				} else if("O".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　□ 否 ■ 無法得知";
				} else {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　□ 否 □ 無法得知";
				}
				
				rowArray[41]=getMedTFO(Common.get(obj.getMedStopMedMitigate()));			
				rowArray[42]=getMedTFO(Common.get(obj.getOnceSameReaction()));
				rowArray[43]=getSameTimeUse(Common.get(obj.getSameTimeUse()),Common.get(obj.getSameTimeUseOther()));
				
				if("1".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="■確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("2".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) ■很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("3".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) ■可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("4".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) ■不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("5".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n■不相關(unrelated) □無法評估(unknow)";
				} else if("6".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) ■無法評估(unknow)";
				}
				else {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				}
				
				if("1".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="■確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("2".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) ■很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("3".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) ■可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("4".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) ■不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("5".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n■不相關(unrelated) □無法評估(unknow)";
				} else if("6".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) ■無法評估(unknow)";
				}
				else {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				}
				
				if("Y".equals(Common.get(obj.getNoticeSponsor()))) {
					rowArray[46]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeSponsor()))) {
					rowArray[46]="□ 是　■　否";
				} else {
					rowArray[46]="□ 是　□　否";
				}

				if("Y".equals(Common.get(obj.getNoticeSponsorWritten()))) {
					rowArray[47]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeSponsorWritten()))) {
					rowArray[47]="□ 是　■　否";
				} else {
					rowArray[47]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeIRB()))) {
					rowArray[48]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeIRB()))) {
					rowArray[48]="□ 是　■　否";
				} else {
					rowArray[48]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeIRBWritten()))) {
					rowArray[49]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeIRBWritten()))) {
					rowArray[49]="□ 是　■　否";
				} else {
					rowArray[49]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeApprovedUnits()))) {
					rowArray[50]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeApprovedUnits()))) {
					rowArray[50]="□ 是　■　否";
				} else {
					rowArray[50]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeApprovedUnitsWritten()))) {
					rowArray[51]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeApprovedUnitsWritten()))) {
					rowArray[51]="□ 是　■　否";
				} else {
					rowArray[51]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getIsAdverseEvents()))) {
					rowArray[52]="■是　□否";
				} else if("N".equals(Common.get(obj.getIsAdverseEvents()))) {
					rowArray[52]="□是　■否";
				}else {
					rowArray[52]="□是　□否";
				}
				
				if(obj.getMed5002Dbs()!=null && obj.getMed5002Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed5002Dbs().iterator();
					rowArray[53] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[53]=null;
				}
				
				if(obj.getMed5003Dbs()!=null && obj.getMed5003Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed5003Dbs().iterator();
					rowArray[54] = new JRTableModelDataSource(getSubModel02(it2));
				} else {
					rowArray[54]=null;
				}
				
				rowArray[55]=Common.get(obj.getOtherDesc());
				
				if(obj.getMed5004Dbs()!=null && obj.getMed5004Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed5004Dbs().iterator();
					
					rowArray[56] = new JRTableModelDataSource(getSubModel03(it2));
				}
				
				if(obj.getMed5005Dbs()!=null && obj.getMed5005Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed5005Dbs().iterator();
					rowArray[57] = new JRTableModelDataSource(getSubModel04(it2));
					
				}
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
	
	//設定子報表路徑
	 public void setParameter(java.util.HashMap<String, Object> params)
		{
			//傳給報表的查詢條件參數,在此設定
			String subreport0FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport0.jasper");
			String subreport1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport1.jasper");
			String subreport2FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport2.jasper");
			String subreport3FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport3.jasper");
	
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
			Med5002Db med5002Db = (Med5002Db)it2.next();
			String[] rowArray= new String[col02.length];
			rowArray[0]=Common.formatYYYMMDD(med5002Db.getBulletinDate(),2);
			rowArray[1]=Common.get(med5002Db.getPosition());
			rowArray[2]=Common.get(med5002Db.getSymptom());
			rowArray[3]=Common.get(med5002Db.getSeverity());
			rowArray[4]=Common.get(med5002Db.getDealWith());
			
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
			Med5003Db med5003Db = (Med5003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(med5003Db.getTestDate(),2);
			rowArray[1]=Common.get(med5003Db.getTestItems());
			rowArray[2]=Common.get(med5003Db.getTestNum());
			
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
			Med5004Db med5004db = (Med5004Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med5004db.getcName());
			rowArray[1]=Common.get(med5004db.getPermitNumber());
			rowArray[2]=Common.get(med5004db.getPermitFirm());
			rowArray[3]=Common.get(med5004db.getModel());
			rowArray[4]=Common.get(med5004db.getMainCategory());
			rowArray[5]=Common.formatYYYMMDD(med5004db.getUseDate(),2);
			rowArray[6]=Common.get(med5004db.getUseReason());
			arrList.add(rowArray);

		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
	        System.out.println("併用醫材： " + model.getDataVector());
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
			Med5005Db med5005db = (Med5005Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med5005db.getcName());
			rowArray[1]=Common.get(med5005db.getContent());
			rowArray[2]=Common.get(med5005db.getFormulation());
			rowArray[3]=Common.get(med5005db.getDrgApproach());
			rowArray[4]=Common.get(med5005db.getDose());
			rowArray[5]=Common.get(med5005db.getFrequency());
			rowArray[6]=Common.formatYYYMMDD(med5005db.getsDate(),2)+"~";
			rowArray[7]=Common.formatYYYMMDD(med5005db.geteDate(),2);
			rowArray[8]=Common.get(med5005db.getMedCauses());
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
	//取回common_code內容
	public String getCommonCodeKindHQL(String codeKindId) {
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId + "'";
		return HQL;
	}
	
	//是、否、無法得知
	public String getMedTFO(String MedUse) {
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
	public String getMedOperator(String MedOperator) {
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
				rowArray2 += checkbox +"\n";
			}
		}
		return rowArray2;
	}
	
	//是否同時使用
	public String getSameTimeUse(String SameTimeUse,String SameTimeUseOther) {
		String hql = getCommonCodeKindHQL("17");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(SameTimeUse)) 
				{
					checkbox = "■" + obj.getCodeName();
					if("4".equals(SameTimeUse)) {
						checkbox += SameTimeUseOther;
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
					if(3 == i) {
						checkbox += "________";
					}
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	//不良反應結果
	public String getCommonCodeKindOfBadReactionResults(String BadReactionResults,String DeathDateAndReason,String BadReactionResultsOther) {
		String hql =getCommonCodeKindHQL("21");
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
						} else if("06".equals(BadReactionResultsList[j]))
						{
							check2 = "■";
							check2 = check2 + obj.getCodeName() + BadReactionResultsOther;
						}
					}
					
				}
				if(i == 0) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check + "\n";
					} else {
						rowArray2 += check + obj.getCodeName() +  "，日期________　死亡原因：________\n";
					}
					
				} else if(i == 5) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check2 + "\n";
					} else {
						rowArray2 += check + obj.getCodeName() +  "________\n";
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
		Med5001Db c = (Med5001Db)View.getObject(" from Med5001Db where id = " + Common.getLong(getId()));
		if(c != null){
			ServiceGetter.getInstance().getTcbwService().delete(c);
		}
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
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED030001%' and dbName='Med5001Db'";
		if(getId() != null && !"".equals(getId()))
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
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
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
	

	public String[] getMed5003DbType() {
		return med5003DbType;
	}

	public void setMed5003DbType(String[] med5003DbType) {
		this.med5003DbType = med5003DbType;
	}

	public String[] getMed5003DbTypeId() {
		return med5003DbTypeId;
	}

	public void setMed5003DbTypeId(String[] med5003DbTypeId) {
		this.med5003DbTypeId = med5003DbTypeId;
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

	public String getMedMainCategoryCodeName() {
		return checkGet(medMainCategoryCodeName);
	}
	public void setMedMainCategoryCodeName(String medMainCategoryCodeName) {
		this.medMainCategoryCodeName = checkSet(medMainCategoryCodeName);
	}
	
	public String getMedMainCategory() {
		return checkGet(medMainCategory);
	}
	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = checkSet(medMainCategory);
	}
	
	public String getMedSecCategoryCodeName() {
		return checkGet(medSecCategoryCodeName);
	}
	public void setMedSecCategoryCodeName(String medSecCategoryCodeName) {
		this.medSecCategoryCodeName = checkSet(medSecCategoryCodeName);
	}
	
	public String getMedSecCategory() {
		return checkGet(medSecCategory);
	}
	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = checkSet(medSecCategory);
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

	
	


	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
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

	public String[] getMed5002DbType() {
		return med5002DbType;
	}

	public void setMed5002DbType(String[] med5002DbType) {
		this.med5002DbType = med5002DbType;
	}

	public String[] getMed5002DbTypeId() {
		return med5002DbTypeId;
	}

	public void setMed5002DbTypeId(String[] med5002DbTypeId) {
		this.med5002DbTypeId = med5002DbTypeId;
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

	public String getStatus() {
		return checkGet(status);
	}

	public void setStatus(String status) {
		this.status = checkSet(status);
	}

	public String getOtherDesc() {
		return checkGet(otherDesc);
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = checkSet(otherDesc);
	}

	public String[] getcName() {
		return checkGet(cName);
	}

	public void setcName(String[] cName) {
		this.cName = checkSet(cName);
	}

	public String[] getPermit() {
		return checkGet(permit);
	}

	public void setPermit(String[] permit) {
		this.permit = checkSet(permit);
	}

	public String[] getPermitNumber() {
		return checkGet(permitNumber);
	}

	public void setPermitNumber(String[] permitNumber) {
		this.permitNumber = checkSet(permitNumber);
	}

	public String[] getPermitFirm() {
		return checkGet(permitFirm);
	}

	public void setPermitFirm(String[] permitFirm) {
		this.permitFirm = checkSet(permitFirm);
	}

	public String[] getModel() {
		return checkGet(model);
	}

	public void setModel(String[] model) {
		this.model = checkSet(model);
	}

	public String[] getMainCategoryCode() {
		return checkGet(mainCategoryCode);
	}

	public void setMainCategoryCode(String[] mainCategoryCode) {
		this.mainCategoryCode = checkSet(mainCategoryCode);
	}
	
	public String[] getMainCategory() {
		return checkGet(mainCategory);
	}

	public void setMainCategory(String[] mainCategory) {
		this.mainCategory = checkSet(mainCategory);
	}

	public String[] getUseDate() {
		return checkGet(useDate);
	}

	public void setUseDate(String[] useDate) {
		this.useDate = checkSet(useDate);
	}

	public String[] getUseReason() {
		return checkGet(useReason);
	}

	public void setUseReason(String[] useReason) {
		this.useReason = checkSet(useReason);
	}

	public String[] getcName2() {
		return checkGet(cName2);
	}

	public void setcName2(String[] cName2) {
		this.cName2 = checkSet(cName2);
	}

	public String[] getContent() {
		return checkGet(content);
	}

	public void setContent(String[] content) {
		this.content = checkSet(content);
	}

	public String[] getFormulation() {
		return checkGet(formulation);
	}

	public void setFormulation(String[] formulation) {
		this.formulation = checkSet(formulation);
	}

	public String[] getDrgApproach() {
		return checkGet(drgApproach);
	}

	public void setDrgApproach(String[] drgApproach) {
		this.drgApproach = checkSet(drgApproach);
	}

	public String[] getDose() {
		return checkGet(dose);
	}

	public void setDose(String[] dose) {
		this.dose = checkSet(dose);
	}

	public String[] getFrequency() {
		return checkGet(frequency);
	}

	public void setFrequency(String[] frequency) {
		this.frequency = checkSet(frequency);
	}

	public String[] getsDate() {
		return checkGet(sDate);
	}

	public void setsDate(String[] sDate) {
		this.sDate = checkSet(sDate);
	}

	public String[] geteDate() {
		return checkGet(eDate);
	}

	public void seteDate(String[] eDate) {
		this.eDate = checkSet(eDate);
	}

	public String[] getMedCauses() {
		return checkGet(medCauses);
	}

	public void setMedCauses(String[] medCauses) {
		this.medCauses = checkSet(medCauses);
	}

	public String[] getMed5004DbType() {
		return checkGet(med5004DbType);
	}

	public void setMed5004DbType(String[] med5004DbType) {
		this.med5004DbType = checkSet(med5004DbType);
	}

	public String[] getMed5004DbTypeId() {
		return checkGet(med5004DbTypeId);
	}

	public void setMed5004DbTypeId(String[] med5004DbTypeId) {
		this.med5004DbTypeId = checkSet(med5004DbTypeId);
	}

	public String[] getMed5005DbType() {
		return checkGet(med5005DbType);
	}

	public void setMed5005DbType(String[] med5005DbType) {
		this.med5005DbType = checkSet(med5005DbType);
	}

	public String[] getMed5005DbTypeId() {
		return checkGet(med5005DbTypeId);
	}

	public void setMed5005DbTypeId(String[] med5005DbTypeId) {
		this.med5005DbTypeId = checkSet(med5005DbTypeId);
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
	
	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	
	
}
