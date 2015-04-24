package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med2002Db;
import com.kangdainfo.tcbw.model.bo.Med2003Db;
import com.kangdainfo.tcbw.model.bo.Med2004Db;
import com.kangdainfo.tcbw.model.bo.Med2005Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.model.dao.Medin2Dao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.medex.MEDEX5102F;
import com.kangdainfo.tcbw.view.medex.MEDEX5201F;
import com.kangdainfo.tcbw.view.medin.MEDIN5101F;
import com.kangdainfo.tcbw.view.medin.MEDIN5202F;


public class Medin2DaoImpl extends BaseDaoImpl implements Medin2Dao{


	@Override
	public void updateByMedIN5101F(MEDIN5101F ref) throws Exception 
	{
		
		java.util.List<Med2002Db> saveListMed2002Db = new java.util.ArrayList<Med2002Db>();	
		java.util.List<Med2002Db> updateListMed2002Db = new java.util.ArrayList<Med2002Db>();
		java.util.List<Med2002Db> deleteListMed2002Db = new java.util.ArrayList<Med2002Db>();
		java.util.List<Med2002Db> med2002DbList =null ;	
		java.util.Map<String,Med2002Db> med2002DbMap=null;
		
		java.util.List<Med2003Db> saveListMed2003Db = new java.util.ArrayList<Med2003Db>();	
		java.util.List<Med2003Db> updateListMed2003Db = new java.util.ArrayList<Med2003Db>();
		java.util.List<Med2003Db> deleteListMed2003Db = new java.util.ArrayList<Med2003Db>();
		java.util.List<Med2003Db> med2003DbList =null ;	
		java.util.Map<String,Med2003Db> med2003DbMap=null;
		
		java.util.List<Med2004Db> saveListMed2004Db = new java.util.ArrayList<Med2004Db>();	
		java.util.List<Med2004Db> updateListMed2004Db = new java.util.ArrayList<Med2004Db>();
		java.util.List<Med2004Db> deleteListMed2004Db = new java.util.ArrayList<Med2004Db>();
		java.util.List<Med2004Db> med2004DbList =null ;	
		java.util.Map<String,Med2004Db> med2004DbMap=null;
		
		java.util.List<Med2005Db> saveListMed2005Db = new java.util.ArrayList<Med2005Db>();	
		java.util.List<Med2005Db> updateListMed2005Db = new java.util.ArrayList<Med2005Db>();
		java.util.List<Med2005Db> deleteListMed2005Db = new java.util.ArrayList<Med2005Db>();
		java.util.List<Med2005Db> med2005DbList =null ;	
		java.util.Map<String,Med2005Db> med2005DbMap=null;
		
		
		
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));

		if(obj != null)
		{

			//基本資料--通報訊息-------------------------------------------------------------------
			obj.setOccurDate(ref.getOccurDate());//發生日期	VARCHAR(7)
			obj.setNotifierRevDate(ref.getNotifierRevDate());//通報者獲知日期	VARCHAR(7)

			obj.setCaseSource(ref.getCaseSource());//案例來源	VARCHAR(4)
			//依案例來源來分別儲存資料
			if("out".equals(ref.getCaseSource()))
			{	
			  obj.setCaseSourceOutCountry(ref.getCaseSourceOutCountry());//案例來源-國外-國家	NVARCHAR(20)
			  obj.setCaseSourceInHospital("");//案例來源-國內-試驗醫院	NVARCHAR(10)
			  obj.setCaseSourceInDoctor("");//案例來源-國內-試驗醫師	NVARCHAR(30)
			}
			else if("in".equals(ref.getCaseSource()))
			{
			  obj.setCaseSourceInHospital(ref.getCaseSourceInHospital());//案例來源-國內-試驗醫院	NVARCHAR(10)
			  obj.setCaseSourceInDoctor(ref.getCaseSourceInDoctor());//案例來源-國內-試驗醫師	NVARCHAR(30)
			  obj.setCaseSourceOutCountry("");//案例來源-國外-國家	NVARCHAR(20)
			}
			
			//報告類別	VARCHAR(2)。1:初始報告,2:追蹤報告
			obj.setReportKind(ref.getReportKind());
			if("1".equals(ref.getReportKind()))
			{	
			   obj.setTrackingNum("");//報告類別-追蹤報告-次數	VARCHAR(2)
			}
			else if("2".equals(ref.getReportKind()))
			{	
			   obj.setTrackingNum(ref.getTrackingNum());//報告類別-追蹤報告-次數	VARCHAR(2)
			}
			
			obj.setTestName(ref.getTestName());//試驗名稱	NVARCHAR(20)
			obj.setFdaNum(ref.getFdaNum());//衛生福利主管機關核准函-文號	NVARCHAR(20)
			
			//1;查驗登記用;2;學術研究用
			obj.setFdaOptions(ref.getFdaOptions());//衛生福利主管機關核准函-選項	VARCHAR(2)
			
			//核准單位 	VARCHAR(2)。1;醫事司;2;食品藥物管理署;3;其他
			obj.setApprovedUnits(ref.getApprovedUnits());
			if("3".equals(ref.getApprovedUnits()))
			{
			  obj.setApprovedUnitsOther(ref.getApprovedUnitsOther());//核准單位-其他-描述	NVARCHAR(30)
			}
			else
			{	
			  obj.setApprovedUnitsOther("");//核准單位-其他-描述	NVARCHAR(30)
			}	
			
			obj.setFirmTestNo(ref.getFirmTestNo());//廠商試驗編號	NVARCHAR(20)
			
			//基本資料--通報者資訊-------------------------------------------------------------------
			if(Common.getInt(obj.getStatus())<10)
			{	
			  obj.setNotifierName(ref.getNotifierName());//通報者-姓名	NVARCHAR(10)
			  //obj.setNotifierTitleDept(ref.getNotifierTitleDept());//通報者-服務機構	NVARCHAR(30)
			  obj.setNotifierAreaCode(ref.getNotifierAreaCode());//通報者-區域號碼	VARCHAR(3)
			  obj.setNotifierTel(ref.getNotifierTel());//通報者-電話	VARCHAR(10)
			  obj.setNotifierTelExt(ref.getNotifierTelExt());//通報者-電話	VARCHAR(10)
			  obj.setNotifierCounty(ref.getNotifierCounty());
			  obj.setNotifierZipCode(ref.getNotifierZipCode());
			  obj.setNotifierAddress(ref.getNotifierAddress());//通報者-地址	NVARCHAR(50)
			  obj.setNotifierEamil(ref.getNotifierEmail());//通報者-電子郵件	VARCHAR(30)
			}
			
			obj.setNotifierDeptID(obj.getNotifierDeptID());
			obj.setNotifierDept(obj.getNotifierDept());
			
			//通報者-屬性	VARCHAR(2)。1:醫療人員,2:廠商
			obj.setNotifierType(ref.getNotifierType());
			if("1".equals(ref.getNotifierType()))
			{	
			   obj.setNotifierTitle(ref.getNotifierTitle());//通報者-醫療人員職稱	NVARCHAR(20)
			}
			else
			{
			   obj.setNotifierTitle("");//通報者-醫療人員職稱	NVARCHAR(20)
			}	
			
			//基本資料--病人相關資料-------------------------------------------------------------------
			obj.setPatientId(ref.getPatientId());//病人基本資料-識別代碼	VARCHAR(10)
			obj.setPatientSex(ref.getPatientSex());//病人基本資料-性別	VARCHAR(1)
			obj.setPatientBirth(ref.getPatientBirth());//病人基本資料-出生日期	VARCHAR(7)
			obj.setPatientAge(ref.getPatientAge());//病人基本資料-年齡	VARCHAR(3)
			obj.setPatientWeight(ref.getPatientWeight());//病人基本資料-體重	VARCHAR(3)
			obj.setPatientHeigth(ref.getPatientHeigth());//病人基本資料-身高	VARCHAR(3)
			
			//不良事件描述--是否為非預期之不良事件 VARCHAR(1)------------------------------------------------
			obj.setIsAdverseEvents(ref.getIsAdverseEvents());
			
			//不良事件--不良反應結果-------------------------------------------------------------------

			if(!"".equals(ref.getBadReactionResults()))
			  obj.setBadReactionResults(ref.getBadReactionResults());//不良反應結果	VARCHAR(2)
			else
			  obj.setBadReactionResults("");
				
			obj.setBadReactionResultsDeathDate("");//不良反應結果-A死亡-日期	VARCHAR(7)
			obj.setBadReactionResultsDeathReason("");//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			obj.setBadReactionResultsOther("");//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			  
			if(obj.getBadReactionResults().indexOf("01")==0)
			{	
			  obj.setBadReactionResultsDeathDate(ref.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	VARCHAR(7)
			  obj.setBadReactionResultsDeathReason(ref.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			}
			else if(obj.getBadReactionResults().indexOf("06")==0)
			{
			  obj.setBadReactionResultsOther(ref.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			}

			//不良事件--懷疑的醫療器材-------------------------------------------------------------------
			obj.setMedPermit(ref.getMedPermit());//懷疑的醫療器材-許可證字號-字	VARCHAR(10)
			obj.setMedPermitNumber(ref.getMedPermitNumber());//懷疑的醫療器材-許可證字號-號	VARCHAR(10)
			obj.setMedTestMedical(ref.getMedTestMedical());//懷疑的醫療器材-試驗醫材名稱	NVARCHAR(30)
			obj.setMedMainCategoryCode(ref.getMedMainCategory());				//醫材主類別代碼
			obj.setMedMainCategory(ref.getMedMainCategoryCodeName());				//醫材主類別中文

			obj.setMedSecCategoryCode(ref.getMedSecCategory());						//醫材次類別代碼
			obj.setMedSecCategory(ref.getMedSecCategoryCodeName());	
			obj.setMedType(ref.getMedType());//懷疑的醫療器材-器材種類	NVARCHAR(30)
			obj.setMedFactory(ref.getMedFactory());//懷疑的醫療器材-製造商	NVARCHAR(30)
			obj.setMedSupplier(ref.getMedSupplier());//懷疑的醫療器材-供應商	NVARCHAR(30)
			obj.setMedModel(ref.getMedModel());//懷疑的醫療器材-型號	VARCHAR(10)
			obj.setMedNo(ref.getMedNo());//懷疑的醫療器材-序號	VARCHAR(10)
			obj.setMedLotNum(ref.getMedLotNum());//懷疑的醫療器材-批號	VARCHAR(10)
			obj.setMedManufactureDate(ref.getMedManufactureDate());//懷疑的醫療器材-製造日期	VARCHAR(7)
			obj.setMedOperator(ref.getMedOperator());//懷疑的醫療器材-醫療器材操作者	VARCHAR(2)。1;醫療人員;2;病人或其家屬;3;其他
			obj.setMedUseDate(ref.getMedUseDate());//懷疑的醫療器材-使用日期	VARCHAR(7)
			obj.setMedStopDate(ref.getMedStopDate());//懷疑的醫療器材-停用日期	VARCHAR(7)
			obj.setMedUseReason(ref.getMedUseReason());//懷疑的醫療器材-使用原因	NVARCHAR(30)
			
			//懷疑的醫療器材-是否可提供器材作評估	VARCHAR(1)。Y:是,N:否,O:已歸還
			obj.setMedUseIsYn(ref.getMedUseIsYn());
			obj.setMedYesSoruce("");//懷疑的醫療器材-是否可提供器材作評估-是-取得來源	NVARCHAR(30)
			obj.setMedNoReturnDate("");//懷疑的醫療器材-是否可提供器材作評估-否-已於退還日期	VARCHAR(7)
			if("Y".equals(ref.getMedUseIsYn()))
			{	
			  obj.setMedYesSoruce(ref.getMedYesSoruce());//懷疑的醫療器材-是否可提供器材作評估-是-取得來源 NVARCHAR(30)
			}
			else if("O".equals(ref.getMedUseIsYn()))
			{	
			  obj.setMedNoReturnDate(ref.getMedNoReturnDate());//懷疑的醫療器材-是否可提供器材作評估-否-已於退還日期 VARCHAR(7)
			}
			
			//懷疑的醫療器材-曾經使用同類醫材之經驗 VARCHAR(1)。Y:是,N:否,O:無法得知
			obj.setMedOnceUseMed(ref.getMedOnceUseMed());
			obj.setMedOnceUseMedName("");//懷疑的醫療器材-曾經使用同類醫材之經驗-是-醫材名稱	NVARCHAR(30)
			obj.setMedOnceUseBadReaction("");//懷疑的醫療器材-曾經使用同類醫材之經驗-是-若發生不良反應請描述	NVARCHAR(30)
			
			if("Y".equals(ref.getMedOnceUseMed()))
			{	
			  obj.setMedOnceUseMedName(ref.getMedOnceUseMedName());//懷疑的醫療器材-曾經使用同類醫材之經驗-是-醫材名稱	NVARCHAR(30)
			  obj.setMedOnceUseBadReaction(ref.getMedOnceUseBadReaction());//懷疑的醫療器材-曾經使用同類醫材之經驗-是-若發生不良反應請描述	NVARCHAR(30)
			}
			
			obj.setMedStopMedMitigate(ref.getMedStopMedMitigate());//懷疑的醫療器材-停用後不良反應是否減輕	VARCHAR(1)
			obj.setOnceSameReaction(ref.getOnceSameReaction());//併用之醫療器材或藥品-再使用是否出現同樣反應	VARCHAR(1)
			
			//併用之醫療器材或藥品-是否同時使用  VARCHAR(2)
			obj.setSameTimeUse(ref.getSameTimeUse());
			obj.setSameTimeUseOther("");//併用之醫療器材或藥品-是否同時使用-其他描述	NVARCHAR(30)
			if("".equals(obj.getSameTimeUse()))
			{	
			  obj.setSameTimeUseOther(ref.getSameTimeUseOther());//併用之醫療器材或藥品-是否同時使用-其他描述NVARCHAR(30)
			}
			
			obj.setMedSea(ref.getMedSea());//試驗醫師評估醫材與SAE之因果關係	VARCHAR(2)
			obj.setProcedureSea(ref.getProcedureSea());//試驗醫師評估手續程序與SAE之因果關係	VARCHAR(2)
			obj.setNoticeSponsor(ref.getNoticeSponsor());//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者	VARCHAR(1)
			obj.setNoticeSponsorWritten(ref.getNoticeSponsorWritten());//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者-書面	VARCHAR(1)
			obj.setNoticeIRB(ref.getNoticeIRB());//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會	VARCHAR(1)
			obj.setNoticeIRBWritten(ref.getNoticeIRBWritten());//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會-書面	VARCHAR(1)
			obj.setNoticeApprovedUnits(ref.getNoticeApprovedUnits());//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位	VARCHAR(1)
			obj.setNoticeApprovedUnitsWritten(ref.getNoticeApprovedUnitsWritten());//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位-書面	VARCHAR(1)
			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			
			if("Out".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getUserID());
			}	
			else  if("In".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getNotifierUserID());
			}	
				
			if(ref.getMed2002DbType()!=null)
			{	
				med2002DbList = ServiceGetter.getInstance().getCommonService().load("from Med2002Db where med2001Db.id="+Common.getLong(ref.getId()));	
				med2002DbMap= new java.util.HashMap<String,Med2002Db>();
				for(Med2002Db obj1:med2002DbList)
				{
					med2002DbMap.put(Common.get(obj1.getId()),obj1);			
				}    
				boolean isUpdate = true;
				
				for(int i=0;i<ref.getMed2002DbType().length;i++)
				{
					String oldId = Common.get(ref.getMed2002DbTypeId()[i]);
					Med2002Db obj2 = med2002DbMap.get(oldId);
					if (obj2==null) 
					{
						isUpdate = false;
						obj2 = new Med2002Db();				
					}		
					obj2.setBulletinDate(ref.getBulletinDate()[i]);//日期	
					obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
					obj2.setSymptom(ref.getSymptom()[i]);//症狀
					obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
					obj2.setDealWith(ref.getDealWith()[i]);//處置
				
					
					Med2001Db master01 = new Med2001Db();
					master01.setId(Common.getLong(ref.getId()));
					obj2.setMed2001Db(master01);
					
					if (isUpdate) {	updateListMed2002Db.add(obj2);}
					else {saveListMed2002Db.add(obj2);}
					
				}	
				
				for(Med2002Db objdelete:med2002DbList)
				{
					boolean isdelete = true;
					
					for(int i=0;i<ref.getMed2002DbType().length;i++)
					{
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed2002DbTypeId()[i])))
			    		{
			    			isdelete = false;
			    			break;
			    		}
					}
					
					if(isdelete)
					{
						deleteListMed2002Db.add(objdelete);
					}
					
				 }
				
			}
			
			if(ref.getMed2003DbType()!=null)
			{	
				med2003DbList = ServiceGetter.getInstance().getCommonService().load("from Med2003Db where med2001Db.id="+Common.getLong(ref.getId()));	
				med2003DbMap= new java.util.HashMap<String,Med2003Db>();
				for(Med2003Db obj1:med2003DbList)
				{
					med2003DbMap.put(Common.get(obj1.getId()),obj1);			
				}    
				boolean isUpdate = true;
				
				for(int i=0;i<ref.getMed2003DbType().length;i++)
				{
					String oldId = Common.get(ref.getMed2003DbTypeId()[i]);
					Med2003Db obj2 = med2003DbMap.get(oldId);
					if (obj2==null) 
					{
						isUpdate = false;
						obj2 = new Med2003Db();				
					}		
					obj2.setTestDate(ref.getTestDate()[i]);
					obj2.setTestItems(ref.getTestItems()[i]);
					obj2.setTestNum(ref.getTestNum()[i]);
					
					Med2001Db master01 = new Med2001Db();
					master01.setId(Common.getLong(ref.getId()));
					obj2.setMed2001Db(master01);
					
					if (isUpdate) {	updateListMed2003Db.add(obj2);}
					else {saveListMed2003Db.add(obj2);}
					
				}	
				
				for(Med2003Db objdelete:med2003DbList)
				{
					boolean isdelete = true;
					
					for(int i=0;i<ref.getMed2003DbType().length;i++)
					{
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed2003DbTypeId()[i])))
			    		{
			    			isdelete = false;
			    			break;
			    		}
					}
					
					if(isdelete)
					{
						deleteListMed2003Db.add(objdelete);
					}
					
				 }
				
			}
			
			if(ref.getMed2004DbType()!=null)
		    {	
			  med2004DbList = ServiceGetter.getInstance().getCommonService().load("from Med2004Db where med2001Db.id="+Common.getLong(ref.getId()));	
			  med2004DbMap= new java.util.HashMap<String,Med2004Db>();
			  for(Med2004Db obj1:med2004DbList)
			  {
				med2004DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed2004DbType().length;i++)
			  {
				
				String oldId = Common.get(ref.getMed2004DbTypeId()[i]);
				Med2004Db obj2 = med2004DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med2004Db();				
				}		
					
				obj2.setcName(ref.getCcname()[i]);//品名
				
				obj2.setPermit(ref.getPermit()[i]);//許可證字
				obj2.setPermitNumber(ref.getPermitNumber()[i]);//許可證號
				
				obj2.setPermitFirm(ref.getPermitFirm()[i]);//許可證申請商
				obj2.setModel(ref.getModel()[i]);//型號
				
				obj2.setMainCategoryCode(ref.getMainCategoryCode()[i]);//器材主類別
				obj2.setMainCategory(ref.getMainCategory()[i]);//器材主類別
				
				obj2.setUseDate(ref.getUseDate()[i]);//使用日期
				obj2.setUseReason(ref.getUseReason()[i]);//使用原因
				obj2.setModifier(ref.getUserID());
				obj2.setModifyDate(Datetime.getYYYMMDD());
				obj2.setModifyTime(Datetime.getHHMMSS());
				Med2001Db master01 = new Med2001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed2001Db(master01);
				
				if (isUpdate) {	updateListMed2004Db.add(obj2);}
				else {saveListMed2004Db.add(obj2);}
				
			  }	
			

			  for(Med2004Db objdelete:med2004DbList)
			  {
				boolean isdelete = true;
				
				for(int i=0;i<ref.getMed2004DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed2004DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed2004Db.add(objdelete);
				}
				
			   }
			
		    }  
		    else 
	        {

			  //若沒有明細代表全部刪除
			  java.util.List<Med2004Db> deleteList = new java.util.ArrayList<Med2004Db>();
			  java.util.List<Med2004Db> Med2004DbList = ServiceGetter.getInstance().getCommonService().load("from Med2004Db where med2001Db.id="+Common.get(ref.getId()));
			  for(Med2004Db obj0:Med2004DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed2004Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed2005DbType()!=null)
		    {	
			  med2005DbList = ServiceGetter.getInstance().getCommonService().load("from Med2005Db where med2001Db.id="+Common.getLong(ref.getId()));	
			  med2005DbMap= new java.util.HashMap<String,Med2005Db>();
			  for(Med2005Db obj1:med2005DbList)
			  {
				med2005DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			   boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed2005DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed2005DbTypeId()[i]);
				Med2005Db obj2 = med2005DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med2005Db();				
				}			
				obj2.setcName(ref.getcName2()[i]);//學名/商品名
				obj2.setContent(ref.getContent()[i]);//含量
				obj2.setFormulation(ref.getFormulation()[i]);//劑型
				obj2.setDrgApproach(ref.getDrgApproach()[i]);//給藥途徑
				obj2.setDose(ref.getDose()[i]);//劑量
				obj2.setFrequency(ref.getFrequency()[i]);//使用頻率
				obj2.setsDate(ref.getsDate()[i]);//起日期
				obj2.seteDate(ref.geteDate()[i]);//迄日期
				obj2.setMedCauses(ref.getMedCauses()[i]);//用藥原因
				obj2.setModifier(ref.getUserID());
				obj2.setModifyDate(Datetime.getYYYMMDD());
				obj2.setModifyTime(Datetime.getHHMMSS());
				Med2001Db master01 = new Med2001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed2001Db(master01);
				
				if (isUpdate) {	updateListMed2005Db.add(obj2);}
				else {saveListMed2005Db.add(obj2);}
				
			  }	
			
			  for(Med2005Db objdelete:med2005DbList)
			  {
				boolean isdelete = true;
				for(int i=0;i<ref.getMed2005DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed2005DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed2005Db.add(objdelete);
				}
				
			  }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med2005Db> deleteList = new java.util.ArrayList<Med2005Db>();
			  java.util.List<Med2005Db> Med2005DbList = ServiceGetter.getInstance().getCommonService().load("from Med2005Db where med2001Db.id="+Common.get(ref.getId()));
			  for(Med2005Db obj0:Med2005DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed2005Db(null, null, deleteList);  
	         }

			saveOrUpdateMed2002Db(saveListMed2002Db, updateListMed2002Db, deleteListMed2002Db);    		
			saveOrUpdateMed2003Db(saveListMed2003Db, updateListMed2003Db, deleteListMed2003Db);    		
			saveOrUpdateMed2004Db(saveListMed2004Db, updateListMed2004Db, deleteListMed2004Db);    		
			saveOrUpdateMed2005Db(saveListMed2005Db, updateListMed2005Db, deleteListMed2005Db);    		
			
			saveOrUpdate(obj);
			
			ref.setId(Common.get(obj.getId()));
			
		}
		
	}


	public void saveOrUpdateMed2002Db(java.util.List<Med2002Db> saveList,java.util.List<Med2002Db> updateList,java.util.List<Med2002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed2003Db(java.util.List<Med2003Db> saveList,java.util.List<Med2003Db> updateList,java.util.List<Med2003Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	public void saveOrUpdateMed2004Db(java.util.List<Med2004Db> saveList,java.util.List<Med2004Db> updateList,java.util.List<Med2004Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	public void saveOrUpdateMed2005Db(java.util.List<Med2005Db> saveList,java.util.List<Med2005Db> updateList,java.util.List<Med2005Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	@Override 
	public void updateSendApplNoByMEDEX5201F(MEDEX5201F ref) throws Exception 
	{
		if(ref.getFds()!=null && ref.getFds().length>0)
		{
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getFds())
			{
				if(!"".equals(Common.get(rid)))
				{
					if(sb.toString().length() > 0)
					{
						sb.append(",");
					}
					sb.append(Common.getLong(rid));
				}
			}
			if(sb.toString().length() > 0)
			{
				java.util.List objList = load(" from Med5001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0)
				{
					java.util.List<Med5001Db> uList = new java.util.ArrayList<Med5001Db>();
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					for(Object dtlObj : objList)
					{
						Med5001Db dtl = (Med5001Db)dtlObj;
						if("01".equals(Common.get(dtl.getStatus())))
						{
	
							//無相對應核准文件，狀態改為案件審核中(一般)
							dtl.setStatus("10");
							
							//接獲通報日期=系統日期
							dtl.setNotifierRepDate(yyymmdd);

							dtl.setModifier(ref.getUserID());
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							uList.add(dtl);
						}
						else
						{
							logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[案件狀態不為待上傳，不更新資料，MED5001_DB.ID : " + dtl.getId() + "]");
						}
					}
					if(uList.size() > 0){
						updateBatch(uList);
						uList.clear();
					}
				}else{
					logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}else{
			logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
		
	}

	
    //受理時給號
	public void updateByMedIN5202F(MEDIN5202F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{

		  //系統給號
		  if("".equals(Common.get(obj.getApplNo())))
		  {	
		     String no=TCBWCommon.getApplNo("MED03","S03",Datetime.getYYY());
		     if(no!=null)
		     {
			   obj.setApplNo(no);
		     }	
		  }
		  obj.setStatus("30");//案件處理中
		  obj.setEnrolledDate(yyymmdd);//收案日期=系統日期
		  obj.setWorkers(ref.getUserID());//目前登入人員
		  
		  obj.setModifier(ref.getUserID());
		  obj.setModifyDate(Datetime.getYYYMMDD());
		  obj.setModifyTime(Datetime.getHHMMSS());
		  
		  Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID = " + Common.getLong(obj.getId()));
		  if(med5001Db!=null)
		  {	
		    med5001Db.setApplNo(obj.getApplNo());
		    med5001Db.setStatus("30");//案件處理中
		    med5001Db.setModifier(ref.getUserID());
		    med5001Db.setModifyDate(Datetime.getYYYMMDD());
		    med5001Db.setModifyTime(Datetime.getHHMMSS());
		    ServiceGetter.getInstance().getTcbwService().update(obj);
		    ServiceGetter.getInstance().getTcbwService().update(med5001Db);
		  }
		  
		  //記錄一筆案件流程紀錄
		  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-受理", ref.getUserID());
	   }
	}
	
	//退件
	public void updateByBackPiecesMedIN5202F(MEDIN5202F ref) throws Exception 
	{
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
	
			obj.setStatus("02");//退件
			obj.setWorkers(ref.getUserID());//目前登入人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID="+Common.getLong(ref.getId()));
			if(med5001Db!=null)
			{
				med5001Db.setStatus("02");//退件
				med5001Db.setModifier(ref.getUserID());
			    med5001Db.setModifyDate(Datetime.getYYYMMDD());
			    med5001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-退件", ref.getUserID());
		}	
	}
	
	//撤案完成
	public void updateByDismissalMedIN5202F(MEDIN5202F ref) throws Exception 
	{
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));
		Med5001Db med5001db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("03");//撤案
			obj.setWorkers("");//清空作業人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			//結案後產生檔案
			closedPrint(Common.get(med5001db.getId()),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			
			Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID="+Common.getLong(ref.getId()));
			if(med5001Db!=null)
			{
				med5001Db.setStatus("03");//撤案
				med5001Db.setModifier(ref.getUserID());
				med5001Db.setModifyDate(Datetime.getYYYMMDD());
				med5001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-撤案", ref.getUserID());
		}	
	}
	
	//校正完成
	public void updateByCorrectionMedIN5202F(MEDIN5202F ref) throws Exception 
	{
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));

		if(obj!=null)
		{
			obj.setStatus("90");//結案
			obj.setWorkers("");//清空作業人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			//結案後產生檔案
			closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			
			Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID="+Common.getLong(ref.getId()));
			if(med5001Db!=null)
			{
				med5001Db.setStatus("90");//結案
				med5001Db.setModifier(ref.getUserID());
				med5001Db.setModifyDate(Datetime.getYYYMMDD());
				med5001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
				
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-校正完成", ref.getUserID());
		    
		}	
	}
	
	//產生備份PDF
	public void closedPrint(String applNo,String id,String userID,String status) throws Exception 
	{
		MEDEX5102F medex5102f=new MEDEX5102F();
		
		if(!"".equals(applNo)) {
			if("90".equals(status)) {
				medex5102f.setApplNo(applNo);
			} else if("03".equals(status)) {
				medex5102f.setId(applNo);
			}
		}
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
		
		medex5102f.setParameter(parms);
	    
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		javax.swing.table.DefaultTableModel model = medex5102f.getDefaultTableModel();

		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r.jasper"));
		env.setFormat("PDF");	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	    
	    String med = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("MED");
	    
	    String fileName="";//檔案名稱
	    String fileDir="MED030001Backup";//存放資料夾
	    
	    if(!"".equals(applNo))fileName=applNo;
	   
	    File meddir = new File(med);
	    //判斷資料夾是否存在，若不存在則建立
	    if(!meddir.isDirectory())
	    {
	    	meddir.mkdir();
	    }	
	    
	    
	    
	    File dir = new File(med+"\\"+fileDir+"\\");
	    //判斷資料夾是否存在，若不存在則建立
	    if(!dir.isDirectory())
	    {
	    	dir.mkdir();
	    }	
	  
	    File output = new File(med+"\\"+fileDir+"\\"+fileName+".pdf");
	    //產生檔案存放
	    generator.reportToFile(output, parms);
	    
	    Con0001Db o = new Con0001Db();
	    
	    o.setFileKind("MED");
	    o.setUpLoadId(Common.getLong(id));
	    o.setFileRoute(fileDir);
	    o.setFileName(fileName+".pdf");
	    o.setFileExplan("醫療器材臨床試驗不良事件通報備查PDF");
	    o.setIsInsert("N");
	    o.setIsDelete("N");
	    o.setSystemType("MED030001");
	    o.setDbName("Med2001Db");
	    o.setCreator(userID);
	    o.setCreateDate(Datetime.getYYYMMDD());
	    o.setCreateTime(Datetime.getHHMMSS());

	    save(o);

	    env.clear();

	}
	
	
	//補件中
	public void updateByAddocumentsMedIN5202F(MEDIN5202F ref) throws Exception 
	{
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(ref.getId()));

		if(obj!=null)
		{
			obj.setStatus("40");//補件中
			obj.setWorkers(ref.getUserID());//登入人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where med2001ID="+Common.getLong(ref.getId()));
			if(med5001Db!=null)
			{
				med5001Db.setStatus("40");//補件中
				med5001Db.setModifier(ref.getUserID());
				med5001Db.setModifyDate(Datetime.getYYYMMDD());
				med5001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-補件", ref.getUserID());
		}	
	}
	
	public void updateByDeleteCon0003DbMedIN5202F(MEDIN5202F ref)
	{
	  String hql = "from Con0003Db where systemType='MED' ";
             hql +=" and formCode='MED03' ";
             hql +=" and dbID=" + Common.sqlChar(ref.getId());

             Con0003Db con0003Db = (Con0003Db)View.getObject(hql);	

       if (con0003Db != null) 
       { 
		 ServiceGetter.getInstance().getTcbwService().delete(con0003Db);
       }

	}
	public int getStatEventKind1(String medPermit,String medPermitNumber,String medCname,String startDate,String endDate) throws Exception{
		return getStatEventKind(medPermit, medPermitNumber, medCname, startDate, endDate, "1");
	}
	public int getStatEventKind2(String medPermit,String medPermitNumber,String medCname,String startDate,String endDate) throws Exception{
		return getStatEventKind(medPermit, medPermitNumber, medCname, startDate, endDate, "2");
	}
	private int getStatEventKind(String medPermit,String medPermitNumber,String medCname,String startDate,String endDate,String eventKind)throws Exception{
		//如有參數沒有則不做
		if(medPermit != null && !"".equals(medPermit) && medPermitNumber != null && !"".equals(medPermitNumber) 
				&& medCname != null && !"".equals(medCname) && eventKind != null && !"".equals(eventKind)){
			String hql = "select count(*) from Med0001Db where 1=1 ";
			hql += " and medPermit = " + Common.sqlChar(medPermit);
			if("Z0".equals(medPermit) || "Z5".equals(medPermit))
				hql += " and medCname = " + Common.sqlChar(medCname);
			else
				hql += " and medPermitNumber = " + Common.sqlChar(medPermitNumber);
			hql += " and eventKind = " + Common.sqlChar(eventKind);
			if(startDate != null && !"".equals(startDate))
				hql += " and notifierRevDate >= " + Common.sqlChar(startDate);
			if(endDate != null && !"".equals(endDate))
				hql += " and notifierRevDate <= " + Common.sqlChar(endDate);
			return Common.getInt(View.getObject(hql));
		}
		return 0;
	}
}
