package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Med1001Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med2002Db;
import com.kangdainfo.tcbw.model.bo.Med2003Db;
import com.kangdainfo.tcbw.model.bo.Med2004Db;

import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med2005Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;
import com.kangdainfo.tcbw.model.bo.Med5002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;
import com.kangdainfo.tcbw.model.bo.Med5004Db;
import com.kangdainfo.tcbw.model.bo.Med5005Db;
import com.kangdainfo.tcbw.model.dao.Medex2Dao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.medex.MEDEX0104F;
import com.kangdainfo.tcbw.view.medex.MEDEX5102F;
import com.kangdainfo.tcbw.view.medex.MEDEX5201F;

public class Medex2DaoImpl extends BaseDaoImpl implements Medex2Dao{

	
	@Override
	public void updateByMedEX5102F(MEDEX5102F ref) throws Exception 
	{
		
		String yyymmdd = Datetime.getYYYMMDD();
	
		java.util.List<Med5002Db> saveListMed5002Db = new java.util.ArrayList<Med5002Db>();	
		java.util.List<Med5002Db> updateListMed5002Db = new java.util.ArrayList<Med5002Db>();
		java.util.List<Med5002Db> deleteListMed5002Db = new java.util.ArrayList<Med5002Db>();
		java.util.List<Med5002Db> med5002DbList =null ;	
		java.util.Map<String,Med5002Db> med5002DbMap=null;
		
		java.util.List<Med5003Db> saveListMed5003Db = new java.util.ArrayList<Med5003Db>();	
		java.util.List<Med5003Db> updateListMed5003Db = new java.util.ArrayList<Med5003Db>();
		java.util.List<Med5003Db> deleteListMed5003Db = new java.util.ArrayList<Med5003Db>();
		java.util.List<Med5003Db> med5003DbList =null ;	
		java.util.Map<String,Med5003Db> med5003DbMap=null;
		
		java.util.List<Med5004Db> saveListMed5004Db = new java.util.ArrayList<Med5004Db>();	
		java.util.List<Med5004Db> updateListMed5004Db = new java.util.ArrayList<Med5004Db>();
		java.util.List<Med5004Db> deleteListMed5004Db = new java.util.ArrayList<Med5004Db>();
		java.util.List<Med5004Db> med5004DbList =null ;	
		java.util.Map<String,Med5004Db> med5004DbMap=null;
		
		java.util.List<Med5005Db> saveListMed5005Db = new java.util.ArrayList<Med5005Db>();	
		java.util.List<Med5005Db> updateListMed5005Db = new java.util.ArrayList<Med5005Db>();
		java.util.List<Med5005Db> deleteListMed5005Db = new java.util.ArrayList<Med5005Db>();
		java.util.List<Med5005Db> med5005DbList =null ;	
		java.util.Map<String,Med5005Db> med5005DbMap=null;
		
		Med5001Db obj = (Med5001Db)View.getObject(" from Med5001Db where id = " + Common.getLong(ref.getId()));

		if(obj != null)
		{

			//基本資料--通報訊息-------------------------------------------------------------------
			obj.setOccurDate(ref.getOccurDate());//發生日期	VARCHAR(7)
			obj.setNotifierRevDate(ref.getNotifierRevDate());//通報者獲知日期
			obj.setCaseSource(ref.getCaseSource());//案例來源	VARCHAR(4)
			
			//依案例來源來分別儲存資料
			if("out".equals(ref.getCaseSource()))
			{	
			  obj.setCaseSourceOutCountry(ref.getCaseSourceOutCountry());//案例來源-國外-國家	NVARCHAR(20)
			  obj.setCaseSourceInHospital("");//案例來源-國內-試驗醫院	NVARCHAR(10)
			  obj.setCaseSourceInDoctor("");//案例來源-國內-試驗醫師	NVARCHAR(30)
			}
			else
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
			else
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
			
			//案件屬於哪個通報者-------------------------------------------------------------------
			if("Out".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getUserID());
			}	
			else  if("In".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getNotifierUserID());
			}	
			
			//基本資料--通報者資訊-------------------------------------------------------------------
			
			obj.setNotifierName(ref.getNotifierName());//通報者-姓名	NVARCHAR(10)
			//obj.setNotifierTitleDept(ref.getNotifierTitleDept());//通報者-服務機構	NVARCHAR(30)
			obj.setNotifierAreaCode(ref.getNotifierAreaCode());//通報者-區域號碼	VARCHAR(3)
			obj.setNotifierTel(ref.getNotifierTel());//通報者-電話	VARCHAR(10)
			obj.setNotifierTelExt(ref.getNotifierTelExt());
			obj.setNotifierDeptID(ref.getNotifierDeptID());	//通報者屬性-服務機構
			obj.setNotifierDept(ref.getNotifierDept());	
			obj.setNotifierAddress(ref.getNotifierAddress());//通報者-地址	NVARCHAR(50)
			obj.setNotifierCounty(ref.getNotifierCounty());
			obj.setNotifierZipCode(ref.getNotifierZipCode());
			obj.setNotifierEamil(ref.getNotifierEamil());//通報者-電子郵件	VARCHAR(30)
			
			//通報者-屬性	VARCHAR(2)。1:醫療人員,2:廠商
			obj.setNotifierType(ref.getNotifierType());
			
//			if("1".equals(ref.getNotifierType()))
//			{	
			   obj.setNotifierTitle(ref.getNotifierTitle());//通報者-醫療人員職稱	NVARCHAR(20)
//			}
//			else
//			{
//			   obj.setNotifierTitle("");//通報者-醫療人員職稱	NVARCHAR(20)
//			}	
			
			
			
			//基本資料--病人相關資料-------------------------------------------------------------------
			obj.setPatientId(ref.getPatientId());//病人基本資料-識別代碼	VARCHAR(10)
			obj.setPatientSex(ref.getPatientSex());//病人基本資料-性別	VARCHAR(1)
			obj.setPatientBirth(ref.getPatientBirth());//病人基本資料-出生日期	VARCHAR(7)
			obj.setPatientAge(ref.getPatientAge());//病人基本資料-年齡	VARCHAR(3)
			obj.setPatientWeight(ref.getPatientWeight());//病人基本資料-體重	VARCHAR(3)
			obj.setPatientHeigth(ref.getPatientHeigth());//病人基本資料-身高	VARCHAR(3)
			
			//基本資料--其他相關資料-------------------------------------------------------------------
			obj.setOtherDesc(ref.getOtherDesc());//其他相關資料 VARCHAR(100)
			
			//不良事件描述--是否為非預期之不良事件 VARCHAR(1)------------------------------------------------
			obj.setIsAdverseEvents(ref.getIsAdverseEvents());
			
			//不良事件--不良反應結果-------------------------------------------------------------------

			if(!"".equals(ref.getBadReactionResults()))
			  obj.setBadReactionResults(ref.getBadReactionResults());//不良反應結果	VARCHAR(2)
			else
			  obj.setBadReactionResults("");
				
			obj.setBadReactionResultsDeathDate(ref.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	VARCHAR(7)
			obj.setBadReactionResultsDeathReason(ref.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			obj.setBadReactionResultsOther(ref.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			  
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
			if("4".equals(obj.getSameTimeUse()))
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
			
		
			
				
			if(ref.getMed5002DbType()!=null)
			{	
				med5002DbList = ServiceGetter.getInstance().getCommonService().load("from Med5002Db where med5001Db.id="+Common.getLong(ref.getId()));	
				med5002DbMap= new java.util.HashMap<String,Med5002Db>();
				for(Med5002Db obj1:med5002DbList)
				{
					med5002DbMap.put(Common.get(obj1.getId()),obj1);			
				}    
				boolean isUpdate = true;
				
				for(int i=0;i<ref.getMed5002DbType().length;i++)
				{
					String oldId = Common.get(ref.getMed5002DbTypeId()[i]);
					Med5002Db obj2 = med5002DbMap.get(oldId);
					if (obj2==null) 
					{
						isUpdate = false;
						obj2 = new Med5002Db();	
						obj2.setCreator(ref.getNotifierUserID());
						obj2.setCreateDate(Datetime.getYYYMMDD());
						obj2.setCreateTime(Datetime.getHHMMSS());
					}		
					else
					{
						obj2.setModifier(ref.getNotifierUserID());
						obj2.setModifyDate(Datetime.getYYYMMDD());
						obj2.setModifyTime(Datetime.getHHMMSS());
					}	
					
					obj2.setBulletinDate(ref.getBulletinDate()[i]);//日期	
					obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
					obj2.setSymptom(ref.getSymptom()[i]);//症狀
					obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
					obj2.setDealWith(ref.getDealWith()[i]);//處置
					
					
					
					Med5001Db master01 = new Med5001Db();
					master01.setId(Common.getLong(ref.getId()));
					obj2.setMed5001Db(master01);
					
					if (isUpdate) {	updateListMed5002Db.add(obj2);}
					else {saveListMed5002Db.add(obj2);}
					
				}	
				
				for(Med5002Db objdelete:med5002DbList)
				{
					boolean isdelete = true;
					
					for(int i=0;i<ref.getMed5002DbType().length;i++)
					{
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed5002DbTypeId()[i])))
			    		{
			    			isdelete = false;
			    			break;
			    		}
					}
					
					if(isdelete)
					{
						deleteListMed5002Db.add(objdelete);
					}
					
				 }
				
			} 
			else 
		    {

				  java.util.List<Med5002Db> deleteList = new java.util.ArrayList<Med5002Db>();
				  java.util.List<Med5002Db> Med5002DbList = ServiceGetter.getInstance().getCommonService().load("from Med5002Db where med5001Db.id="+Common.get(ref.getId()));
				  for(Med5002Db obj0:Med5002DbList)
				  {
					deleteList.add(obj0);
				  }	
				
				  saveOrUpdateMed5002Db(null, null, deleteList);  
		    }
			
			if(ref.getMed5003DbType()!=null)
			{	
				med5003DbList = ServiceGetter.getInstance().getCommonService().load("from Med5003Db where med5001Db.id="+Common.getLong(ref.getId()));	
				med5003DbMap= new java.util.HashMap<String,Med5003Db>();
				for(Med5003Db obj1:med5003DbList)
				{
					med5003DbMap.put(Common.get(obj1.getId()),obj1);			
				}    
				boolean isUpdate = true;
				
				for(int i=0;i<ref.getMed5003DbType().length;i++)
				{
					String oldId = Common.get(ref.getMed5003DbTypeId()[i]);
					Med5003Db obj2 = med5003DbMap.get(oldId);
					if (obj2==null) 
					{
						isUpdate = false;
						obj2 = new Med5003Db();	
						obj2.setCreator(ref.getNotifierUserID());
						obj2.setCreateDate(Datetime.getYYYMMDD());
						obj2.setCreateTime(Datetime.getHHMMSS());
					}		
					else
					{
						obj2.setModifier(ref.getNotifierUserID());
						obj2.setModifyDate(Datetime.getYYYMMDD());
						obj2.setModifyTime(Datetime.getHHMMSS());
					}		
					
					obj2.setTestDate(ref.getTestDate()[i]);
					obj2.setTestItems(ref.getTestItems()[i]);
					obj2.setTestNum(ref.getTestNum()[i]);
					
					Med5001Db master01 = new Med5001Db();
					master01.setId(Common.getLong(ref.getId()));
					obj2.setMed5001Db(master01);

					if (isUpdate) {	updateListMed5003Db.add(obj2);}
					else {saveListMed5003Db.add(obj2);}
					
				}	
				
				for(Med5003Db objdelete:med5003DbList)
				{
					boolean isdelete = true;
					
					for(int i=0;i<ref.getMed5003DbType().length;i++)
					{
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed5003DbTypeId()[i])))
			    		{
			    			isdelete = false;
			    			break;
			    		}
					}
					
					if(isdelete)
					{
						deleteListMed5003Db.add(objdelete);
					}
					
				 }
				
			} 
			else 
		    {
				
				  java.util.List<Med5003Db> deleteList = new java.util.ArrayList<Med5003Db>();
				  java.util.List<Med5003Db> Med5003DbList = ServiceGetter.getInstance().getCommonService().load("from Med5003Db where med5001Db.id="+Common.get(ref.getId()));
				  for(Med5003Db obj0:Med5003DbList)
				  {
					deleteList.add(obj0);
				  }	
				
				  saveOrUpdateMed5003Db(null, null, deleteList);  
		    }
			
			if(ref.getMed5004DbType()!=null)
		    {	
			  med5004DbList = ServiceGetter.getInstance().getCommonService().load("from Med5004Db where med5001Db.id="+Common.getLong(ref.getId()));	
			  med5004DbMap= new java.util.HashMap<String,Med5004Db>();
			  for(Med5004Db obj1:med5004DbList)
			  {
				med5004DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed5004DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed5004DbTypeId()[i]);
				Med5004Db obj2 = med5004DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med5004Db();				
				}		
					
				obj2.setcName(ref.getcName()[i]);//品名
				obj2.setPermit(ref.getPermit()[i]);//許可證字
				obj2.setPermitNumber(ref.getPermitNumber()[i]);//許可證號
				obj2.setPermitFirm(ref.getPermitFirm()[i]);//許可證申請商
				obj2.setModel(ref.getModel()[i]);//型號
				obj2.setMainCategory(ref.getMainCategory()[i]);//器材主類別
				obj2.setMainCategoryCode(ref.getMainCategoryCode()[i]);
				obj2.setUseDate(ref.getUseDate()[i]);//使用日期
				obj2.setUseReason(ref.getUseReason()[i]);//使用原因
			
				Med5001Db master01 = new Med5001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed5001Db(master01);
				
				if (isUpdate) {	updateListMed5004Db.add(obj2);}
				else {saveListMed5004Db.add(obj2);}
				
			  }	
			
			  for(Med5004Db objdelete:med5004DbList)
			  {
				boolean isdelete = true;
				
				for(int i=0;i<ref.getMed5004DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed5004DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed5004Db.add(objdelete);
				}
				
			   }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med5004Db> deleteList = new java.util.ArrayList<Med5004Db>();
			  java.util.List<Med5004Db> Med5004DbList = ServiceGetter.getInstance().getCommonService().load("from Med5004Db where med5001Db.id="+Common.get(ref.getId()));
			  for(Med5004Db obj0:Med5004DbList)
			  {
				deleteList.add(obj0);
			  }	
			  saveOrUpdateMed5004Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed5005DbType()!=null)
		    {	
			  med5005DbList = ServiceGetter.getInstance().getCommonService().load("from Med5005Db where med5001Db.id="+Common.getLong(ref.getId()));	
			  med5005DbMap= new java.util.HashMap<String,Med5005Db>();
			  for(Med5005Db obj1:med5005DbList)
			  {
				med5005DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			   boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed5005DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed5005DbTypeId()[i]);
				Med5005Db obj2 = med5005DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med5005Db();				
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
			
				Med5001Db master01 = new Med5001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed5001Db(master01);
				
				if (isUpdate) {	updateListMed5005Db.add(obj2);}
				else {saveListMed5005Db.add(obj2);}
				
			  }	
			
			  for(Med5005Db objdelete:med5005DbList)
			  {
				boolean isdelete = true;
				for(int i=0;i<ref.getMed5005DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed5005DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed5005Db.add(objdelete);
				}
				
			  }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med5005Db> deleteList = new java.util.ArrayList<Med5005Db>();
			  java.util.List<Med5005Db> Med5005DbList = ServiceGetter.getInstance().getCommonService().load("from Med5005Db where med5001Db.id="+Common.get(ref.getId()));
			  for(Med5005Db obj0:Med5005DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			   saveOrUpdateMed5005Db(null, null, deleteList);  
	         }
			
			saveOrUpdateMed5002Db(saveListMed5002Db, updateListMed5002Db, deleteListMed5002Db);    		
			saveOrUpdateMed5003Db(saveListMed5003Db, updateListMed5003Db, deleteListMed5003Db);
			saveOrUpdateMed5004Db(saveListMed5004Db, updateListMed5004Db, deleteListMed5004Db);
			saveOrUpdateMed5005Db(saveListMed5005Db, updateListMed5005Db, deleteListMed5005Db);
			
			//接收前端按鈕塞入狀態，按下待上傳鈕，案件狀態=待上傳
			if("2".equals(ref.getUpdateType()))
			{
				if("00".equals(obj.getStatus()))
				{
				   obj.setStatus("01");
				}
				//記錄一筆案件流程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),"01","案件通報者-待上傳", ref.getUserID());
			}
			else if("3".equals(ref.getUpdateType()))
			{
				obj.setNotifierRevDate(Datetime.getYYYMMDD());//通報者獲知日期	VARCHAR(7)
				obj.setNotifierRepDate(yyymmdd);

				//按下送出鈕，先查詢是否有案號
				//查詢臨床試驗核准文號，若有則系統自動給號，案件狀態=案件處理中
				if("".equals(Common.get(obj.getApplNo())) && "Y".equals(getFdaNum(ref.getFdaNum())))
				{
					obj.setStatus("30");//案件狀態=案件處理中
					obj.setEnrolledDate(yyymmdd);//收案日期=系統日期
					if("".equals(Common.get(obj.getApplNo())))
					{	
					  String no=TCBWCommon.getApplNo("MED03","S03",Datetime.getYYY());
					  if(no!=null)
					  {
						obj.setApplNo(no);
					  }
					  obj.setRevision("01");
					}  
				}
				else
				{
					//若無核准文號，也無案號，案件為退件時，案件狀態=案件審核中(優先)20
					//若無核准文號，也無案號，案件狀態=案件審核中(一般)10
					if("02".equals(obj.getStatus()))
					{	
					  obj.setStatus("20");
					  Med2001Db med2001Db=(Med2001Db)View.getObject("from Med2001Db where id="+obj.getMed2001ID());
					  if(med2001Db!=null)
					  {
						  med2001Db.setStatus("20");
						  update(med2001Db);
						  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",med2001Db.getId(), med2001Db.getApplNo(),med2001Db.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
					  }
					}
					else
					{
					  obj.setStatus("10");
					}
				}	

				updateDoCopyMed2001Db(obj);

			}
			else
			{
				if("00".equals(obj.getStatus()) || "01".equals(obj.getStatus()))
				{	
				  obj.setStatus("00");
				}
			}
			
			obj.setModifier(ref.getNotifierUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			
			update(obj);
		    
			ref.setId(Common.get(obj.getId()));
			
		}
		
	}
	
	
	
	

	public String getFdaNum(String fdaNum)
	{
		Med1001Db obj = (Med1001Db)View.getObject(" from Med1001Db where applNo = " + Common.getLong(fdaNum));
		if(obj != null)
		{
			return "Y";
		}
		return "N";
	}
	
	
	
	@Override 
	public void updateSendByMEDEX5201F(MEDEX5201F ref) throws Exception 
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
							//按下送出鈕，先查詢是否有案號
							//查詢臨床試驗核准文號，若有則系統自動給號，案件狀態=案件處理中
							if("".equals(Common.get(dtl.getApplNo())) && "Y".equals(getFdaNum(dtl.getFdaNum())))
							{
								dtl.setStatus("30");
								dtl.setEnrolledDate(yyymmdd);//收案日期=系統日期
								if("".equals(Common.get(dtl.getApplNo())))
								{	
								  String no=TCBWCommon.getApplNo("MED03","S03",Datetime.getYYY());
								  if(no!=null)
								  {
									dtl.setApplNo(no);
									dtl.setRevision("01");
								  }
								   
								}  
							}
							else
							{
								//若無核准文號，也無案號，案件為退件時，案件狀態=案件審核中(優先)20
								//若無核准文號，也無案號，案件狀態=案件審核中(一般)10
								if("02".equals(dtl.getStatus()))
									dtl.setStatus("20");
								else
									dtl.setStatus("10");
							}	
			
							//接獲通報日期=系統日期
							dtl.setNotifierRepDate(yyymmdd);

							//作業人員，依據案件審核自動分派原則設定人員
							dtl.setModifier(ref.getUserID());
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							uList.add(dtl);
							updateDoCopyMed2001Db(dtl);
						}
						else
						{
							logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[案件狀態不為待上傳，不更新資料，MED5001_DB.ID : " + dtl.getId() + "]");
						}
						
						//記錄一筆案件流程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",dtl.getId(), dtl.getApplNo(),dtl.getStatus(),"案件通報者-批次上傳", ref.getUserID());
					}
					if(uList.size() > 0)
					{
						updateBatch(uList);
						uList.clear();
					}
				}
				else
				{
					logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}
		else
		{
			logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材臨床試驗不良事件通報-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
	}
	
	public void updateDoCopyMed2001Db(Med5001Db obj) throws Exception
	{
	
		if(obj != null && (obj.getMed2001ID() == null || "".equals(obj.getMed2001ID())))
		{
			Med2001Db master = new Med2001Db(); 

			String[] ignoreFields = new String[]{"id", "med5002Dbs", "med5003Dbs", "med5004Dbs", "med5005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med2002Dbs = new ListOrderedSet();
			
			if(obj.getMed5002Dbs()!=null && obj.getMed5002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5002Dbs())
				{
					
					Med5002Db dtl = (Med5002Db)dtlObj;
					Med2002Db tmpDtl = new Med2002Db(); 
					tmpDtl.setMed2001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med2002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med2003Dbs = new ListOrderedSet();
			if(obj.getMed5003Dbs()!=null && obj.getMed5003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5003Dbs())
				{
					Med5003Db dtl = (Med5003Db)dtlObj;
					Med2003Db tmpDtl = new Med2003Db();
					tmpDtl.setMed2001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med2003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med2004Dbs = new ListOrderedSet();
			if(obj.getMed5004Dbs()!=null && obj.getMed5004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5004Dbs())
				{
					Med5004Db dtl = (Med5004Db)dtlObj;
					Med2004Db tmpDtl = new Med2004Db(); 
					tmpDtl.setMed2001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med2004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med2005Dbs = new ListOrderedSet();
			if(obj.getMed5005Dbs()!=null && obj.getMed5005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5005Dbs())
				{
					Med5005Db dtl = (Med5005Db)dtlObj;
					Med2005Db tmpDtl = new Med2005Db(); 
					tmpDtl.setMed2001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med2005Dbs.add(tmpDtl);
				}
			}
			
			master.setMed2002Dbs(med2002Dbs);
			master.setMed2003Dbs(med2003Dbs);
			master.setMed2004Dbs(med2004Dbs);
			master.setMed2005Dbs(med2005Dbs);
		
			//按下送出鈕，先查詢是否有案號
			//查詢臨床試驗核准文號，若有則系統自動給號，案件狀態=案件處理中
			if("Y".equals(getFdaNum(obj.getFdaNum())))
			{
			  //查詢是否有相同的許可證字號
			  //如果有直接把案件派給他
				
			  //若沒有則依目前人員手上案件去分配
			  String user=TCBWCommon.getUserID("MED03","02","Med2001Db");
	
			  master.setWorkers(user);  
			}
			
			save(master);

			obj.setMed2001ID(master.getId());
			
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",master.getId(), obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			
			doCopyCon0001Db(obj,master,"1");
			
			update(obj);
		}
		
		
	}
	
	//檔案上傳回內網
	public void doCopyCon0001Db(Med5001Db obj, Med2001Db med01 ,String type) throws Exception
	{
		System.out.println("obj=="+obj.getId());
		System.out.println("obj=="+med01.getId());
		
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED030001' and dbName='Med5001Db' and isInsert='Y' and upLoadId="+obj.getId());
		
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med2001Db");
				newcon.setUpLoadId(med01.getId());
				newcon.setIsInsert("N");
				ServiceGetter.getInstance().getCommonService().save(newcon);
				if("1".equals(type))
				{ 
					//退件補件			
					oldcon.setIsInsert("N"); //使檔案分得出退件補件後上傳的新檔案				
					ServiceGetter.getInstance().getCommonService().update(oldcon);
				}
				else
				{   
					//主動補件
					ServiceGetter.getInstance().getCommonService().delete(oldcon);
				}
			}
		}
	}
	
	//主動補件
	@Override
	public void updateByAutoReUpdateMedEX5102F(MEDEX5102F ref,String auto) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		
		java.util.List<Med5002Db> saveListMed5002Db = new java.util.ArrayList<Med5002Db>();	
		java.util.List<Med5003Db> saveListMed5003Db = new java.util.ArrayList<Med5003Db>();	
		java.util.List<Med5004Db> saveListMed5004Db = new java.util.ArrayList<Med5004Db>();		
		java.util.List<Med5005Db> saveListMed5005Db = new java.util.ArrayList<Med5005Db>();	
		
		Med5001Db med5001Db = (Med5001Db)View.getObject(" from Med5001Db where id = " + Common.getLong(ref.getId()));
		Med5001Db obj = new Med5001Db();
		
		
		   int revision=0;
		   if(!"".equals(Common.get(med5001Db.getRevision())))
		   {
		      revision=Integer.parseInt(med5001Db.getRevision());
		      revision++;
		   }
	
		    obj.setRevision(Common.formatFrontZero(String.valueOf(revision),2));
			
		    obj.setApplNo(med5001Db.getApplNo());	
//		    obj.setApplNo1(med5001Db.getApplNo1());
		    
		    obj.setOccurDate(ref.getOccurDate());//發生日期	VARCHAR(7)
			
			obj.setCaseSource(ref.getCaseSource());//案例來源	VARCHAR(4)
			//依案例來源來分別儲存資料
			if("out".equals(ref.getCaseSource()))
			{	
			  obj.setCaseSourceOutCountry(ref.getCaseSourceOutCountry());//案例來源-國外-國家	NVARCHAR(20)
			  obj.setCaseSourceInHospital("");//案例來源-國內-試驗醫院	NVARCHAR(10)
			  obj.setCaseSourceInDoctor("");//案例來源-國內-試驗醫師	NVARCHAR(30)
			}
			else
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
			else
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
			obj.setNotifierName(ref.getNotifierName());//通報者-姓名	NVARCHAR(10)
			//obj.setNotifierTitleDept(ref.getNotifierTitleDept());//通報者-服務機構	NVARCHAR(30)
			obj.setNotifierAreaCode(ref.getNotifierAreaCode());//通報者-區域號碼	VARCHAR(3)
			obj.setNotifierTel(ref.getNotifierTel());//通報者-電話	VARCHAR(10)
			obj.setNotifierTelExt(ref.getNotifierTelExt());
			obj.setNotifierDeptID(ref.getNotifierDeptID());	//通報者屬性-服務機構
			obj.setNotifierDept(ref.getNotifierDept());	
			  
			obj.setNotifierAddress(ref.getNotifierAddress());//通報者-地址	NVARCHAR(50)
			obj.setNotifierEamil(ref.getNotifierEamil());//通報者-電子郵件	VARCHAR(30)
			
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
			
			//基本資料--其他相關資料-------------------------------------------------------------------
			obj.setOtherDesc(ref.getOtherDesc());//其他相關資料 VARCHAR(100)
			
			//不良事件描述--是否為非預期之不良事件 VARCHAR(1)------------------------------------------------
			obj.setIsAdverseEvents(ref.getIsAdverseEvents());
			
			//不良事件--不良反應結果-------------------------------------------------------------------
			

			if(!"".equals(ref.getBadReactionResults()))
			  obj.setBadReactionResults(ref.getBadReactionResults());//不良反應結果	VARCHAR(2)
			else
			  obj.setBadReactionResults("");
				
			obj.setBadReactionResultsDeathDate(ref.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	VARCHAR(7)
			obj.setBadReactionResultsDeathReason(ref.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			obj.setBadReactionResultsOther(ref.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			  
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
			obj.setMedMainCategory(ref.getMedMainCategory());//懷疑的醫療器材-醫材主類別
			obj.setMedSecCategory(ref.getMedSecCategory());//懷疑的醫療器材-醫材次類別
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
			
			
			obj.setMed2001ID(Common.getLong(med5001Db.getMed2001ID()));
			
			if("Out".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getUserID());
			}	
			else  if("In".equals(ref.getInOrOut()))
			{
				obj.setInOrOutcreator(ref.getNotifierUserID());
			}
			
			save(obj);
			ref.setId(Common.get(obj.getId()));
				
			if(ref.getMed5002DbType()!=null)
			{	
				for(int i=0;i<ref.getMed5002DbType().length;i++)
				{
					Med5002Db obj2=new Med5002Db();				  
					Med5001Db master01 = new Med5001Db();
					master01.setId(Common.getLong(obj.getId()));
					obj2.setMed5001Db(master01);

					obj2.setCreator(ref.getNotifierUserID());
					obj2.setCreateDate(Datetime.getYYYMMDD());
					obj2.setCreateTime(Datetime.getHHMMSS());	
					
					obj2.setBulletinDate(ref.getBulletinDate()[i]);//日期	
					obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
					obj2.setSymptom(ref.getSymptom()[i]);//症狀
					obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
					obj2.setDealWith(ref.getDealWith()[i]);//處置
				}
			}
			
			
			if(ref.getMed5003DbType()!=null)
			{	
				for(int i=0;i<ref.getMed5003DbType().length;i++)
				{
					Med5003Db obj2=new Med5003Db();				  
					Med5001Db master01 = new Med5001Db();
					master01.setId(Common.getLong(obj.getId()));
					obj2.setMed5001Db(master01);
					
					obj2.setCreator(ref.getNotifierUserID());
					obj2.setCreateDate(Datetime.getYYYMMDD());
					obj2.setCreateTime(Datetime.getHHMMSS());	
					
					obj2.setTestDate(ref.getTestDate()[i]);
					obj2.setTestItems(ref.getTestItems()[i]);
					obj2.setTestNum(ref.getTestNum()[i]);
				}
			}
			
			if(ref.getMed5004DbType()!=null)
		    {	
			  for(int i=0;i<ref.getMed5004DbType().length;i++)
			  {				  
				Med5004Db obj2=new Med5004Db();				  
				Med5001Db master01 = new Med5001Db();
				master01.setId(Common.getLong(obj.getId()));
				obj2.setMed5001Db(master01); 
				
				obj2.setcName(ref.getcName()[i]);//品名
				obj2.setPermit(ref.getPermit()[i]);//許可證字
				obj2.setPermitNumber(ref.getPermitNumber()[i]);//許可證號
				obj2.setPermitFirm(ref.getPermitFirm()[i]);//許可證申請商
				obj2.setModel(ref.getModel()[i]);//型號
				obj2.setMainCategory(ref.getMainCategory()[i]);//器材主類別
				obj2.setUseDate(ref.getUseDate()[i]);//使用日期
				obj2.setUseReason(ref.getUseReason()[i]);//使用原因
			  }	
		    }
			
		    if(ref.getMed5005DbType()!=null)
		    {	
			  for(int i=0;i<ref.getMed5005DbType().length;i++)
			  {	
				Med5005Db obj2=new Med5005Db();				  
				Med5001Db master01 = new Med5001Db();
				master01.setId(Common.getLong(obj.getId()));
				obj2.setMed5001Db(master01); 
				
				obj2.setcName(ref.getcName2()[i]);//學名/商品名
				obj2.setContent(ref.getContent()[i]);//含量
				obj2.setFormulation(ref.getFormulation()[i]);//劑型
				obj2.setDrgApproach(ref.getDrgApproach()[i]);//給藥途徑
				obj2.setDose(ref.getDose()[i]);//劑量
				obj2.setFrequency(ref.getFrequency()[i]);//使用頻率
				obj2.setsDate(ref.getsDate()[i]);//起日期
				obj2.seteDate(ref.geteDate()[i]);//迄日期
				obj2.setMedCauses(ref.getMedCauses()[i]);//用藥原因	
			  }	
		    }  

			saveOrUpdateMed5002Db(saveListMed5002Db,null,null);    		
			saveOrUpdateMed5003Db(saveListMed5003Db,null,null);
			saveOrUpdateMed5004Db(saveListMed5004Db,null,null);
			saveOrUpdateMed5005Db(saveListMed5005Db,null,null);
			
			
		    
//		Med5001Db obj = (Med5001Db)View.getObject(" from Med5001Db where id = " + Common.getLong(ref.getId()));
		
		if(obj!=null)
		{
		  Med2001Db med2001Db = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(obj.getMed2001ID()));
		  med2001Db.setAutoReUpdate("Y");
		  		  
		  if("90".equalsIgnoreCase(obj.getStatus()))
		  {
			  med2001Db.setStatus("30");
			  obj.setStatus("30");
		  }
		  
		  updateDoCopyMed5001Db(obj,auto);
		  
		  String title="【主動補件】",mailBody="",mail="";
		  
		  String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(med2001Db.getWorkers()));
		    
		  mail = email;
		
		  if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
		  
		  mailBody = "主動補件。";
		           
		  java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		  
		  String[] mailAddress = mail.split(",");

		  if(mailAddress!=null && mailAddress.length>0)
		  {	
		        for(String s : mailAddress)	
		        {        		   
		        	javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();        		   
		        	p.setAddress(s);        		   
		        	mailList.add(p);         
		        }
		  }
		  ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null,"MED03",obj.getApplNo());
		  TCBWCommon.setMailbackup("MED",Common.get(obj.getMed2001ID()),title, mailBody, obj.getApplNo(),"", ref.getUserID(),"");	
		  
		  //記錄一筆案件流程紀錄
		  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED03",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件通報者-主動補件", ref.getUserID());
		  
		}
	}
	
	public void updateDoCopyMed5001Db(Med5001Db obj,String auto) throws Exception
	{
		
		if(obj != null)
		{
			Med5001Db master = new Med5001Db(); 

			String[] ignoreFields = new String[]{"id", "med5002Dbs", "med5003Dbs", "med5004Dbs", "med5005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med5002Dbs = new ListOrderedSet();
			
			if(obj.getMed5002Dbs()!=null && obj.getMed5002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5002Dbs())
				{
					Med5002Db dtl = (Med5002Db)dtlObj;
					Med5002Db tmpDtl = new Med5002Db(); 
					tmpDtl.setMed5001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med5002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med5003Dbs = new ListOrderedSet();
			if(obj.getMed5003Dbs()!=null && obj.getMed5003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5003Dbs())
				{
					Med5003Db dtl = (Med5003Db)dtlObj;
					Med5003Db tmpDtl = new Med5003Db();
					tmpDtl.setMed5001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med5003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med5004Dbs = new ListOrderedSet();
			if(obj.getMed5004Dbs()!=null && obj.getMed5004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5004Dbs())
				{
					Med5004Db dtl = (Med5004Db)dtlObj;
					Med5004Db tmpDtl = new Med5004Db(); 
					tmpDtl.setMed5001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med5004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med5005Dbs = new ListOrderedSet();
			if(obj.getMed5005Dbs()!=null && obj.getMed5005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed5004Dbs())
				{
					Med5005Db dtl = (Med5005Db)dtlObj;
					Med5005Db tmpDtl = new Med5005Db(); 
					tmpDtl.setMed5001Db(master);
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					med5005Dbs.add(tmpDtl);
				}
			}
			
			master.setMed5002Dbs(med5002Dbs);
			master.setMed5003Dbs(med5003Dbs);
			master.setMed5004Dbs(med5004Dbs);
			master.setMed5005Dbs(med5005Dbs);
		
			save(master);
			int revision=0;
			
			if("Y".equals(auto) && !"".equals(Common.get(obj.getRevision())))
			{
			  revision=Integer.parseInt(obj.getRevision());
			  revision++;
			  master.setRevision(Common.formatFrontZero(String.valueOf(revision),2));
              update(master);
			}
		}
		
		
	}
	
	public void saveOrUpdateMed5002Db(java.util.List<Med5002Db> saveList,java.util.List<Med5002Db> updateList,java.util.List<Med5002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed5003Db(java.util.List<Med5003Db> saveList,java.util.List<Med5003Db> updateList,java.util.List<Med5003Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed5004Db(java.util.List<Med5004Db> saveList,java.util.List<Med5004Db> updateList,java.util.List<Med5004Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed5005Db(java.util.List<Med5005Db> saveList,java.util.List<Med5005Db> updateList,java.util.List<Med5005Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
}
