package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0002Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;
import com.kangdainfo.tcbw.model.bo.Cos0008Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.bo.Med0003Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0005Db;
import com.kangdainfo.tcbw.model.bo.Med0006Db;
import com.kangdainfo.tcbw.model.bo.Med0007Db;
import com.kangdainfo.tcbw.model.bo.Med0008Db;
import com.kangdainfo.tcbw.model.bo.Med0009Db;
import com.kangdainfo.tcbw.model.bo.Med0010Db;
import com.kangdainfo.tcbw.model.bo.Med1005Db;
import com.kangdainfo.tcbw.model.bo.Med1006Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;
import com.kangdainfo.tcbw.model.dao.Medin1Dao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.medex.MEDEX0104F;
import com.kangdainfo.tcbw.view.medex.MEDEX5102F;
import com.kangdainfo.tcbw.view.medin.MEDIN0101F;
import com.kangdainfo.tcbw.view.medin.MEDIN0202F;
import com.kangdainfo.tcbw.view.medin.MEDIN0302F;
import com.kangdainfo.tcbw.view.medin.MEDIN0402F;
import com.kangdainfo.tcbw.view.medin.MEDIN0501F;
import com.kangdainfo.tcbw.view.medin.MEDIN0702F;
import com.kangdainfo.tcbw.view.medin.MEDIN0802F;
import com.kangdainfo.tcbw.view.medin.MEDIN0902F;
import com.kangdainfo.tcbw.view.medin.MEDIN0903F;


public class Medin1DaoImpl extends BaseDaoImpl implements Medin1Dao
{
    //-醫療器材不良事件通報-----------------------------------------------------------------------------------
	
	@Override
	public void updateByMedIN0101F(MEDIN0101F ref) throws Exception 
	{
	
		java.util.List<Med0002Db> saveListMed0002Db = new java.util.ArrayList<Med0002Db>();	
		java.util.List<Med0002Db> updateListMed0002Db = new java.util.ArrayList<Med0002Db>();
		java.util.List<Med0002Db> deleteListMed0002Db = new java.util.ArrayList<Med0002Db>();
		
		java.util.List<Med0002Db> med0002DbList =null ;	
		java.util.Map<String,Med0002Db> med0002DbMap=null;
		
		java.util.List<Med0003Db> saveListMed0003Db = new java.util.ArrayList<Med0003Db>();	
		java.util.List<Med0003Db> updateListMed0003Db = new java.util.ArrayList<Med0003Db>();
		java.util.List<Med0003Db> deleteListMed0003Db = new java.util.ArrayList<Med0003Db>();
		java.util.List<Med0003Db> med0003DbList =null ;	
		java.util.Map<String,Med0003Db> med0003DbMap=null;
		
		java.util.List<Med0004Db> saveListMed0004Db = new java.util.ArrayList<Med0004Db>();	
		java.util.List<Med0004Db> updateListMed0004Db = new java.util.ArrayList<Med0004Db>();
		java.util.List<Med0004Db> deleteListMed0004Db = new java.util.ArrayList<Med0004Db>();
		java.util.List<Med0004Db> med0004DbList =null ;	
		java.util.Map<String,Med0004Db> med0004DbMap=null;
		
		java.util.List<Med0005Db> saveListMed0005Db = new java.util.ArrayList<Med0005Db>();	
		java.util.List<Med0005Db> updateListMed0005Db = new java.util.ArrayList<Med0005Db>();
		java.util.List<Med0005Db> deleteListMed0005Db = new java.util.ArrayList<Med0005Db>();
		java.util.List<Med0005Db> med0005DbList =null ;	
		java.util.Map<String,Med0005Db> med0005DbMap=null;
		
		//撰寫
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));

		if(obj != null) 
		{
			obj.setOccurDate(ref.getOccurDate());								//通報日期
			obj.setNotifierRepDate(ref.getNotifierRevDate());					//通報者接獲日期
			obj.setNotifierRevDate(ref.getNotifierRevDate());					//通報中心接獲日期
			obj.setDrugEventsSources(ref.getDrugEventsSources());				//原始藥物不良事件獲知來源

			if("1".equals(ref.getDrugEventsSources())) 
			{
				obj.setMedicalStaff(ref.getMedicalStaff());						//原始藥物不良事件獲知來源-醫療人員
				obj.setHealthUnits(null);
				obj.setHealthUnitsOther(null);
				if("5".equals(ref.getMedicalStaff())) {
					obj.setMedicalStaffOther(ref.getMedicalStaffOther());		//原始藥物不良事件獲知來源-醫療人員-其他
				} else {
					obj.setMedicalStaffOther(null);
				}
			} else if("2".equals(ref.getDrugEventsSources())){
				obj.setHealthUnits(ref.getHealthUnits());						//原始藥物不良事件獲知來源-衛生單位
				obj.setMedicalStaff(null);
				obj.setMedicalStaffOther(null);
				if("2".equals(ref.getHealthUnits())) {
					obj.setHealthUnitsOther(ref.getHealthUnitsOther());			//原始藥物不良事件獲知來源-衛生單位-其他
				} else {
					obj.setHealthUnitsOther(null);
				}
			}

			obj.setCaseSource(ref.getCaseSource());								//案例來源
			
			if("out".equals(ref.getCaseSource())) {
				obj.setCaseSourceOutCountry(ref.getCaseSourceOutCountry());		//案例來源-國外(國家)
			} else {
				obj.setCaseSourceOutCountry(null);
			}
		
			obj.setReportKind(ref.getReportKind());								//報告類別
			
			if("2".equals(ref.getReportKind())) {
				obj.setTrackingNum(ref.getTrackingNum());						//報告類別-追蹤報告第N次
			} else {
				obj.setTrackingNum(null);
			}
			
			obj.setCorrectiveAction(ref.getCorrectiveAction());					//報告類別-矯正措施
			obj.setAttachment(ref.getAttachment());								//附件
			
			if("Y".equals(ref.getAttachment())){
				obj.setAttachmentYnum(ref.getAttachmentYnum());					//附件-有共N件
			} else {
				obj.setAttachmentYnum(null);
			}
			
			obj.setDrugSafetyMonitoring(ref.getDrugSafetyMonitoring());			//產品經公告列入 藥物安全監視
			  
			//通報者資訊
			if(Common.getInt(obj.getStatus())<10)
			{	
			  obj.setNotifierName(ref.getNotifierName());//姓名
			  obj.setNotifierAreaCode(ref.getNotifierAreaCode());//電話區碼
			  obj.setNotifierTel(ref.getNotifierTel());//電話
			  obj.setNotifierEamil(ref.getNotifierEamil());//E-mail
			  obj.setNotifierAddress(ref.getNotifierAddress());//地址
			  obj.setNotifierType(ref.getNotifierType());//通報者屬性
			}
			
			if("1".equals(ref.getNotifierType())) 
			{
				obj.setNotifierStaffHospital(ref.getNotifierStaffHospital());	//通報者屬性-醫院名稱
				obj.setNotifierStaffTitle(ref.getNotifierStaffTitle());			//通報者屬性-醫療人員-職稱
				//obj.setNotifierFirmDept(null);
				obj.setNotifierDeptID(null);	//通報者屬性-服務機構
				obj.setNotifierDept(null);	
				if("5".equals(ref.getNotifierStaffTitle())) {
					obj.setNotifierStaffTitleOther(ref.getNotifierStaffTitleOther());//通報者屬性-醫療人員-職稱-其他
				} else {
					obj.setNotifierStaffTitleOther(null);
				}
			} else if("2".equals(ref.getNotifierType())) {
				//obj.setNotifierFirmDept(ref.getNotifierFirmDept());				//通報者屬性-服務機構
				
				obj.setNotifierDeptID(ref.getNotifierDeptID());	//通報者屬性-服務機構
				obj.setNotifierDept(ref.getNotifierDept());	
				obj.setNotifierStaffHospital(null);
				obj.setNotifierStaffTitle(null);
				obj.setNotifierStaffTitleOther(null);
			} else {
				obj.setNotifierStaffHospital(null);
				obj.setNotifierStaffTitle(null);
				obj.setNotifierStaffTitleOther(null);
				obj.setNotifierDeptID(null);	//通報者屬性-服務機構
				obj.setNotifierDept(null);	
			}
			
			obj.setIsContactYn(ref.getIsContactYn());							//是否願意提供
			 
			//病人相關資料
			obj.setBadReactionPatientCode(ref.getBadReactionPatientCode());		//病人代號
			obj.setBadReactionSex(ref.getBadReactionSex());						//病人性別
			obj.setBadReactionBirthday(ref.getBadReactionBirthday());			//病人出生日期
			obj.setBadReactionAge(ref.getBadReactionAge());						//病人年齡
			obj.setBadReactionWeight(ref.getBadReactionWeight());				//病人體重
			obj.setBadReactionHeight(ref.getBadReactionHeight());				//病人身高
			 
			//不良事件類別
			//obj.setEventKind(ref.getEventKind());								//不良事件類別
            String eventKind="";
			
			if (ref.getEventKind()!=null && ref.getEventKind().length>0) 
			{
				for (int i=0; i<ref.getEventKind().length; i++) 
				{
					eventKind+=ref.getEventKind()[i]+","; 
				}
			}
			
			if(eventKind.length()>0)
			  obj.setEventKind(eventKind.substring(0, eventKind.length()-1));//不良反應結果	VARCHAR(2)
			else
			  obj.setEventKind("");
			
			
			
			
			//不良事件資訊
			obj.setMedPermit(ref.getMedPermit());								//許可證字號-字
			obj.setMedPermitNumber(ref.getMedPermitNumber());					//許可證字號-號
			obj.setMedPermitFirmCode(ref.getMedPermitFirmCode());						//許可證申請商
			obj.setMedPermitFirm(ref.getMedPermitFirm());						//許可證申請商
			obj.setMedCname(ref.getMedCname());									//中文品名
			obj.setMedMainCategoryCode(ref.getMedMainCategory());				//醫材主類別代碼
			obj.setMedMainCategory(ref.getMedMainCategoryCodeName());				//醫材主類別中文

			obj.setMedSecCategoryCode(ref.getMedSecCategory());						//醫材次類別代碼
			obj.setMedSecCategory(ref.getMedSecCategoryCodeName());						//醫材次類別中文
			
			
			obj.setMedFactory(ref.getMedFactory());								//製造廠
			obj.setMedCountry(ref.getMedCountr());								//製造國別
			obj.setMedModel(ref.getMedModel());									//型號
			obj.setMedNo(ref.getMedNo());										//序號
			obj.setMedLotNum(ref.getMedLotNum());								//批號
			obj.setMedSoftwareVersion(ref.getMedSoftwareVersion());				//軟體版本
			obj.setMedManufactureDate(ref.getMedManufactureDate());				//製造日期
			obj.setMedEffectiveDate(ref.getMedEffectiveDate());					//有效日期
			obj.setMedPurchaseDate(ref.getMedPurchaseDate());					//採購日期
			obj.setMedUseDate(ref.getMedUseDate());								//使用日期
			obj.setMedUseReason(ref.getMedUseReason());							//使用原因
			 
			//不良反應相關資料
			 																//不良反應結果
			obj.setMedOperator(ref.getMedOperator());							//醫療器材操作者
			obj.setMedDisposalStatus(ref.getMedDisposalStatus());				//器材處置狀況
			
			if("4".equals(ref.getMedDisposalStatus())) {
				obj.setMedDisposalStatusDate(ref.getMedDisposalStatusDate());	//器材處置狀況-於XXX退還廠商
			} else {
				obj.setMedDisposalStatusDate(null);
			}
			
			obj.setMedUse(ref.getMedUse());										//器材使用
			if("5".equals(ref.getMedUse())) {
				obj.setMedUseOther(ref.getMedUseOther());						//器材使用-其他
			} else {
				obj.setMedUseOther(null);
			}
			
			obj.setOnceUseMed(ref.getOnceUseMed());								//曾使用同類醫材
			if("Y".equals(ref.getOnceUseMed())) {
				obj.setOnceUseMedName(ref.getOnceUseMedName());					//曾使用同類醫材-是-醫材名稱
				obj.setOnceUseMedOther(ref.getOnceUseMedOther());				//曾使用同類醫材-是-若發生不良反應請描述
			} else {
				obj.setOnceUseMedName(null);
				obj.setOnceUseMedOther(null);
			}

			obj.setStopMedMitigate(ref.getStopMedMitigate());					//停用後不良反應是否減輕
			obj.setSameReaction(ref.getSameReaction());							//再使用是否出現同樣反應
			 																//產品問題分類
			obj.setDefProductDescription(ref.getDefProductDescription());		//不良品缺陷之描述
			
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
			
			
		    if(ref.getMed0002DbType()!=null)
		    {	
			   med0002DbList = ServiceGetter.getInstance().getCommonService().load("from Med0002Db where med0001Db.id="+Common.getLong(ref.getId()));	
			   med0002DbMap= new java.util.HashMap<String,Med0002Db>();
			   for(Med0002Db obj1:med0002DbList)
			   {
				  
				  med0002DbMap.put(Common.get(obj1.getId()),obj1);			
			   }
			   boolean isUpdate = true;
			
			   for(int i=0;i<ref.getMed0002DbType().length;i++)
			   {
				  String oldId = Common.get(ref.getMed0002DbTypeId()[i]);
	
				  Med0002Db obj2 = med0002DbMap.get(oldId);
			
				  if (obj2==null) 
				  {
					isUpdate = false;
					obj2 = new Med0002Db();				
				  }		
				  obj2.setOccurDate(ref.getBulletinDate()[i]);//日期	
				  obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
				  obj2.setSymptom(ref.getSymptom()[i]);//症狀
				  obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
				  obj2.setDealWith(ref.getDealWith()[i]);//處置
				  obj2.setModifier(ref.getUserID());
				  obj2.setModifyDate(Datetime.getYYYMMDD());
				  obj2.setModifyTime(Datetime.getHHMMSS());
				  
				  Med0001Db master01 = new Med0001Db();
				  master01.setId(Common.getLong(ref.getId()));
				  obj2.setMed0001Db(master01);
				
				  if (isUpdate) {	updateListMed0002Db.add(obj2);}
				  else {saveListMed0002Db.add(obj2);}
				
			    }	
			
			    for(Med0002Db objdelete:med0002DbList)
			    {
				   boolean isdelete = true;
				   for(int i=0;i<ref.getMed0002DbType().length;i++)
				   {
		    		  if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed0002DbTypeId()[i])))
		    		  {
		    			isdelete = false;
		    			break;
		    		  }
				   }
				
				   if(isdelete)
				   {
					  deleteListMed0002Db.add(objdelete);
				   }
				
			     }
			
		    }  
		    else 
	        {
			   java.util.List<Med0002Db> deleteList = new java.util.ArrayList<Med0002Db>();
			   java.util.List<Med0002Db> Med0002DbList = ServiceGetter.getInstance().getCommonService().load("from Med0002Db where med0001Db.id="+Common.get(ref.getId()));
			   for(Med0002Db obj0:Med0002DbList)
			   {
				 deleteList.add(obj0);
			   }	
			
			   saveOrUpdateMed0002Db(null, null, deleteList);  
	        }
		
		
		
		    if(ref.getMed0003DbType()!=null)
		    {	
			  med0003DbList = ServiceGetter.getInstance().getCommonService().load("from Med0003Db where med0001Db.id="+Common.getLong(ref.getId()));	
			  med0003DbMap= new java.util.HashMap<String,Med0003Db>();
			  for(Med0003Db obj1:med0003DbList)
			  {
				med0003DbMap.put(Common.get(obj1.getId()),obj1);			
			  }    
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed0003DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed0003DbTypeId()[i]);
				Med0003Db obj2 = med0003DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med0003Db();				
				}		
				obj2.setTestDate(ref.getTestDate()[i]);
				obj2.setTestItems(ref.getTestItems()[i]);
				obj2.setTestNum(ref.getTestNum()[i]);
				obj2.setModifier(ref.getUserID());
				obj2.setModifyDate(Datetime.getYYYMMDD());
				obj2.setModifyTime(Datetime.getHHMMSS());
				Med0001Db master01 = new Med0001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed0001Db(master01);
				
				if (isUpdate) {	updateListMed0003Db.add(obj2);}
				else {saveListMed0003Db.add(obj2);}
				
			   }	
			
			   for(Med0003Db objdelete:med0003DbList)
			   {
				  boolean isdelete = true;
				
				  for(int i=0;i<ref.getMed0003DbType().length;i++)
				  {
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed0003DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				  }
				
				  if(isdelete)
				  {
					  deleteListMed0003Db.add(objdelete);
				  }
				
			    }
		    } 
		    else 
	        {
			  java.util.List<Med0003Db> deleteList = new java.util.ArrayList<Med0003Db>();
			  java.util.List<Med0003Db> Med0003DbList = ServiceGetter.getInstance().getCommonService().load("from Med0003Db where med0001Db.id="+Common.get(ref.getId()));
			  for(Med0003Db obj0:Med0003DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed0003Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed0004DbType()!=null)
		    {	
			  med0004DbList = ServiceGetter.getInstance().getCommonService().load("from Med0004Db where med0001Db.id="+Common.getLong(ref.getId()));	
			  med0004DbMap= new java.util.HashMap<String,Med0004Db>();
			  for(Med0004Db obj1:med0004DbList)
			  {
				med0004DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed0004DbType().length;i++)
			  {
				
				String oldId = Common.get(ref.getMed0004DbTypeId()[i]);
				Med0004Db obj2 = med0004DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med0004Db();				
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
				Med0001Db master01 = new Med0001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed0001Db(master01);
				
				if (isUpdate) {	updateListMed0004Db.add(obj2);}
				else {saveListMed0004Db.add(obj2);}
				
			  }	
			

			  for(Med0004Db objdelete:med0004DbList)
			  {
				boolean isdelete = true;
				
				for(int i=0;i<ref.getMed0004DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed0004DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed0004Db.add(objdelete);
				}
				
			   }
			
		    }  
		    else 
	        {

			  //若沒有明細代表全部刪除
			  java.util.List<Med0004Db> deleteList = new java.util.ArrayList<Med0004Db>();
			  java.util.List<Med0004Db> Med0004DbList = ServiceGetter.getInstance().getCommonService().load("from Med0004Db where med0001Db.id="+Common.get(ref.getId()));
			  for(Med0004Db obj0:Med0004DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed0004Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed0005DbType()!=null)
		    {	
			  med0005DbList = ServiceGetter.getInstance().getCommonService().load("from Med0005Db where med0001Db.id="+Common.getLong(ref.getId()));	
			  med0005DbMap= new java.util.HashMap<String,Med0005Db>();
			  for(Med0005Db obj1:med0005DbList)
			  {
				med0005DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			   boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed0005DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed0005DbTypeId()[i]);
				Med0005Db obj2 = med0005DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med0005Db();				
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
				Med0001Db master01 = new Med0001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed0001Db(master01);
				
				if (isUpdate) {	updateListMed0005Db.add(obj2);}
				else {saveListMed0005Db.add(obj2);}
				
			  }	
			
			  for(Med0005Db objdelete:med0005DbList)
			  {
				boolean isdelete = true;
				for(int i=0;i<ref.getMed0005DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed0005DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed0005Db.add(objdelete);
				}
				
			  }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med0005Db> deleteList = new java.util.ArrayList<Med0005Db>();
			  java.util.List<Med0005Db> Med0005DbList = ServiceGetter.getInstance().getCommonService().load("from Med0005Db where med0001Db.id="+Common.get(ref.getId()));
			  for(Med0005Db obj0:Med0005DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed0005Db(null, null, deleteList);  
	         }
		
		     saveOrUpdateMed0002Db(saveListMed0002Db, updateListMed0002Db, deleteListMed0002Db);    		
		     saveOrUpdateMed0003Db(saveListMed0003Db, updateListMed0003Db, deleteListMed0003Db);
		     saveOrUpdateMed0004Db(saveListMed0004Db, updateListMed0004Db, deleteListMed0004Db);
		     saveOrUpdateMed0005Db(saveListMed0005Db, updateListMed0005Db, deleteListMed0005Db);
		
		     //若為重新校正案件，將這兩個欄位清空，避免流程一直跑迴圈
		    // if("Y".equals(ref.getIsReCalibration())) {
		    	 obj.setIsReCalibration("");
		    	 obj.setReCalibrationReason("");
		     //}
		     
		     
		     obj.setModifier(ref.getNotifierUserID());
			 obj.setModifyDate(Datetime.getYYYMMDD());
			 obj.setModifyTime(Datetime.getHHMMSS());
			 
			 update(obj);
		     ref.setId(Common.get(obj.getId()));
		}
	}
	
	
	//受理時給號
	public void updateByMedIN0202F(MEDIN0202F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		
		String applNo="",applNo1="";

		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
		
		if(obj!=null)
		{
            String splitString = obj.getEventKind();
  		    
            String[] names = null;
  		  
  		    if(obj.getEventKind()!=null)
  			    names = splitString.split(",");
            
			if (names!=null && names.length>0) 
			{
				for (int i=0; i<names.length; i++) 
				{
					//不良反應
					if("1".equals(names[i])) 
					{
					   applNo=TCBWCommon.getApplNo("MED01","A01",Datetime.getYYY());
					}
					else if("2".equals(names[i]))//不良品
					{
					   applNo1=TCBWCommon.getApplNo("MED02","R02",Datetime.getYYY());
					}
				}
			}
			
	
			obj.setStatus("20");//案件處理中
			obj.setEnrolledDate(yyymmdd);//收案日期=系統日期
			obj.setWorkers(ref.getUserID());//目前登入人員
			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			
			  
			if(!"".equals(applNo) && !"".equals(applNo1))
			{
				
			   if(!"".equals(applNo))
			   {		   
			      obj.setApplNo(applNo);//不良反應案號
			      obj.setApplNo1(applNo1);//對方案號
			      obj.setRevision("01");
			      obj.setEventKind("1");//一份案件的[不良事件類別]＝不良反應
			      
			      updateDoCopyMed0001Db(obj);
			   
			      Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));
			   
			      if(med4001Db!=null)
			      {	
				     med4001Db.setApplNo(applNo);
			         med4001Db.setApplNo1(applNo1);
				     med4001Db.setStatus("20");//案件處理中
				     med4001Db.setEventKind("1");//一份案件的[不良事件類別]＝不良反應
				     med4001Db.setModifier(ref.getUserID());
				     med4001Db.setModifyDate(Datetime.getYYYMMDD());
				     med4001Db.setModifyTime(Datetime.getHHMMSS());
			         updateDoCopyMed4001Db(med4001Db);
			      }
			   }
			   
			   if(!"".equals(applNo1) )
			   {
				   obj.setApplNo(applNo1);//不良品案號
				   obj.setApplNo1(applNo);//對方案號
				   obj.setRevision("01");
				   obj.setEventKind("2");//一份案件的[不良事件類別]＝不良品

				   //內網拷貝
				   updateDoCopyMed0001Db(obj);
				   
				   Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));
				  
				   if(med4001Db!=null)
				   {	
					  med4001Db.setApplNo(applNo1);
					  med4001Db.setApplNo1(applNo);
					  med4001Db.setEventKind("2");//一份案件的[不良事件類別]＝不良品
				      med4001Db.setStatus("20");//案件處理中
					  med4001Db.setModifier(ref.getUserID());
					  med4001Db.setModifyDate(Datetime.getYYYMMDD());
					  med4001Db.setModifyTime(Datetime.getHHMMSS());
					  
					  String id=View.getLookupField("select id from Med0001Db where applNo="+Common.sqlChar(applNo1));
					  med4001Db.setMed0001ID(Common.getLong(id));
				      
					  updateDoCopyMed4001Db(med4001Db);
					
				   }
			   
			     }  
			   

			     Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));     
		         if(med4001Db!=null)
				 {	
		        	 Con0001Db con0001Db=(Con0001Db)View.getObject("from Con0001Db where dbName='Med4001Db' and upLoadId="+Common.getLong(med4001Db.getId()));
			    	 if(con0001Db!=null)
					 {
			    	     delete(con0001Db);
					 }
			    	 ServiceGetter.getInstance().getTcbwService().delete(med4001Db);
			     }
		     
		         Con0001Db con0001Db=(Con0001Db)View.getObject("from Con0001Db where dbName='Med0001Db' and upLoadId="+Common.getLong(obj.getId()));
		    	 
		         if(con0001Db!=null)
				 {
		    	     delete(con0001Db);
				 }
		         
		         bulkUpdate("delete Con0002Db where systemType='MED' and formID="+Common.sqlChar(Common.get(obj.getId())));
		         
		         
			     ServiceGetter.getInstance().getTcbwService().delete(obj);
	
			     Med0001Db a =(Med0001Db)View.getObject("from Med0001Db where applNo="+Common.sqlChar(applNo));
				
			     if(a!=null)
				 {	
					Med4001Db b =(Med4001Db)View.getObject("from Med4001Db where applNo="+Common.sqlChar(applNo));
					if(b!=null)
					{
						b.setMed0001ID(a.getId());
						update(b);
					}
					sendMail(ref.getNotifierEamil(),Common.get(a.getId()));
				 }
					
				 Med0001Db c =(Med0001Db)View.getObject("from Med0001Db where applNo="+Common.sqlChar(applNo1));
				 if(c!=null)
				 {	
					Med4001Db d =(Med4001Db)View.getObject("from Med4001Db where applNo="+Common.sqlChar(applNo1));
					if(d!=null)
					{
						d.setMed0001ID(c.getId());
						update(d);
					}
					sendMail(ref.getNotifierEamil(),Common.get(c.getId()));
				 }
				 
			  }
			  else
			  {
				  
				  if(!"".equals(applNo) )
				  {
					  Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));
					  if(med4001Db!=null)
					  {	
						  med4001Db.setApplNo(applNo);
					      med4001Db.setStatus("20");//案件處理中
						  med4001Db.setModifier(ref.getUserID());
						  med4001Db.setModifyDate(Datetime.getYYYMMDD());
						  med4001Db.setModifyTime(Datetime.getHHMMSS());
                          update(med4001Db);
					   }
					  
					   obj.setApplNo(applNo);
					   update(obj);
					   
					   Con0002Db con0002 =(Con0002Db)View.getObject("from Con0002Db where systemType='MED' and formID="+Common.sqlChar(Common.get(obj.getId())));
					   if(con0002!=null)
					   {
						  con0002.setSystemType("MED01");
						  con0002.setFormID(Common.get(obj.getId()));
						  con0002.setApplNo(applNo);
						  update(con0002);
					   }	 
					   
					   ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件審核者-受理", obj.getWorkers());
					   sendMail(ref.getNotifierEamil(),Common.get(obj.getId()));
				  }
				  
				  if(!"".equals(applNo1) )
				  {
					  Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));
					  if(med4001Db!=null)
					  {	
						  med4001Db.setApplNo(applNo1);
						  med4001Db.setApplNo1(applNo);
						  med4001Db.setStatus("20");//案件處理中
						  med4001Db.setModifier(ref.getUserID());
						  med4001Db.setModifyDate(Datetime.getYYYMMDD());
						  med4001Db.setModifyTime(Datetime.getHHMMSS());
                          update(med4001Db);
					   }
					   obj.setApplNo(applNo1);
					   update(obj);
					   
					   Con0002Db con0002 =(Con0002Db)View.getObject("from Con0002Db where systemType='MED' and formID="+Common.sqlChar(Common.get(obj.getId())));
					   if(con0002!=null)
					   {
						  con0002.setSystemType("MED02");
						  con0002.setFormID(Common.get(obj.getId()));
						  con0002.setApplNo(applNo1);
						  update(con0002);
					   }	
					   sendMail(ref.getNotifierEamil(),Common.get(obj.getId()));
					   ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件審核者-受理", obj.getWorkers());
				  }
				  
			  }
		}
	}
	
	public void sendMail(String NotifierEamil,String id) throws Exception
	{
		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010001'");	
		
		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		
		String mailBody="",title="",mail=NotifierEamil;
		
		if(c!=null)
		{
			mailBody=c.getMailBody();
			
			title=c.getTitle();
			
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(id));
			
			if(med0001Db!=null)
			{
				title=title.replace("[T_案號]",med0001Db.getApplNo());
				mailBody=mailBody.replace("[F_通報中心接獲通報日期]", Common.formatYYYMMDD(med0001Db.getNotifierRevDate(),2));
				mailBody=mailBody.replace("[F_件數]", "1");
				mailBody=mailBody.replace("[F_案號]", med0001Db.getApplNo());
				
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
				
				
				
				String kind="";
				
				if("1".equals(med0001Db.getEventKind()))
				{
					kind="MED01";
				}
				else
				{
					kind="MED02";
				}
					
				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,med0001Db.getApplNo(),kind);
				
				TCBWCommon.setMailbackup(kind,Common.get(med0001Db.getId()),title,mailBody,med0001Db.getApplNo(),"20",med0001Db.getWorkers(),"");	
			}	
		}	
	}
	
	
	//受理時內網拷貝兩筆
	public void updateDoCopyMed0001Db(Med0001Db obj) throws Exception
	{
		
		if(obj != null)
		{
			Med0001Db master = new Med0001Db(); 

			String[] ignoreFields = new String[]{"id", "med0002Dbs", "med0003Dbs", "med0004Dbs", "med0005Dbs","med0007Dbs", "med0008Dbs","med0009Dbs"};
			
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med0002Dbs = new ListOrderedSet();
			
			if(obj.getMed0002Dbs()!=null && obj.getMed0002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed0002Dbs())
				{
					Med0002Db dtl = (Med0002Db)dtlObj;
					Med0002Db tmpDtl = new Med0002Db(); 
					//順序
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0003Dbs = new ListOrderedSet();
			if(obj.getMed0003Dbs()!=null && obj.getMed0003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed0003Dbs())
				{
					Med0003Db dtl = (Med0003Db)dtlObj;
					Med0003Db tmpDtl = new Med0003Db();
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0004Dbs = new ListOrderedSet();
			
	
			if(obj.getMed0004Dbs()!=null && obj.getMed0004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				
				for(Object dtlObj : obj.getMed0004Dbs())
				{
					Med0004Db dtl = (Med0004Db)dtlObj;
					Med0004Db tmpDtl = new Med0004Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0005Dbs = new ListOrderedSet();
			if(obj.getMed0005Dbs()!=null && obj.getMed0005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed0005Dbs())
				{
					Med0005Db dtl = (Med0005Db)dtlObj;
					Med0005Db tmpDtl = new Med0005Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0005Dbs.add(tmpDtl);
				}
			}
			master.setMed0002Dbs(med0002Dbs);
			master.setMed0003Dbs(med0003Dbs);
			master.setMed0004Dbs(med0004Dbs);
			master.setMed0005Dbs(med0005Dbs);
			
			save(master);
			
			update(obj);
			
			if("1".equals(obj.getEventKind()))
			{	
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件審核者-受理", obj.getWorkers());
			  ServiceGetter.getInstance().getTcbwService().bulkUpdate("update Con2001Db set applNo="+Common.sqlChar(obj.getApplNo())+",formID="+master.getId()+" where systemType='MED01' and formID="+obj.getId());
			  
			  java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("from Con0002Db where systemType='MED' and formID="+Common.sqlChar(Common.get(obj.getId())));

			  if (objList != null && objList.size() > 0) 
			  {
				  for(Object dtlObj : objList) 
				  {				
					    Con0002Db con0002 = (Con0002Db)dtlObj;
						TCBWCommon.setMailbackup("MED01",Common.get(master.getId()), con0002.getTitle(),con0002.getMailBody(),obj.getApplNo(), con0002.getState(), con0002.getCreator(),"");	
				  }
				  
			  }	  
			}
			else if("2".equals(obj.getEventKind()))
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件審核者-受理", obj.getWorkers());
			  ServiceGetter.getInstance().getTcbwService().bulkUpdate("update Con2001Db set applNo="+Common.sqlChar(obj.getApplNo())+",formID="+master.getId()+" where systemType='MED02' and formID="+obj.getId());
              
			  java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("from Con0002Db where systemType='MED' and formID="+Common.sqlChar(Common.get(obj.getId())));

			  if (objList != null && objList.size() > 0) 
			  {
				  for(Object dtlObj : objList) 
				  {				
					    Con0002Db con0002 = (Con0002Db)dtlObj;
						TCBWCommon.setMailbackup("MED01",Common.get(master.getId()), con0002.getTitle(),con0002.getMailBody(),obj.getApplNo(), con0002.getState(), con0002.getCreator(),"");	
				  }
				  
			  }	  
			}	
			
			//拷貝檔案
			doCopyFileCon0001Db(obj,master);
		}
	}
	
	//內網檔案上傳
	public void doCopyFileCon0001Db(Med0001Db obj,Med0001Db newObj) throws Exception
	{
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med0001Db'  and upLoadId="+obj.getId());
		
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med0001Db");
				newcon.setIsInsert("N");
				newcon.setUpLoadId(newObj.getId());
				ServiceGetter.getInstance().getCommonService().save(newcon);
			}
		}
	}
	
	//受理時外網拷貝兩筆
	public void updateDoCopyMed4001Db(Med4001Db obj) throws Exception
	{
		if(obj != null)
		{
			Med4001Db master = new Med4001Db(); 

			String[] ignoreFields = new String[]{"id", "med4002Dbs", "med4003Dbs", "med4004Dbs", "med4005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med4002Dbs = new ListOrderedSet();
			
			if(obj.getMed4002Dbs()!=null && obj.getMed4002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4002Dbs())
				{
					Med4002Db dtl = (Med4002Db)dtlObj;
					Med4002Db tmpDtl = new Med4002Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed4001Db(master);
					med4002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med4003Dbs = new ListOrderedSet();
			if(obj.getMed4003Dbs()!=null && obj.getMed4003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4003Dbs())
				{
					Med4003Db dtl = (Med4003Db)dtlObj;
					Med4003Db tmpDtl = new Med4003Db();
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed4001Db(master);
					med4003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med4004Dbs = new ListOrderedSet();
			if(obj.getMed4004Dbs()!=null && obj.getMed4004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4004Dbs())
				{
					Med4004Db dtl = (Med4004Db)dtlObj;
					Med4004Db tmpDtl = new Med4004Db(); 				
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed4001Db(master);
					med4004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med4005Dbs = new ListOrderedSet();
			if(obj.getMed4005Dbs()!=null && obj.getMed4005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4005Dbs())
				{
					Med4005Db dtl = (Med4005Db)dtlObj;
					Med4005Db tmpDtl = new Med4005Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed4001Db(master);
					med4005Dbs.add(tmpDtl);
				}
			}
			master.setMed4002Dbs(med4002Dbs);
			master.setMed4003Dbs(med4003Dbs);
			master.setMed4004Dbs(med4004Dbs);
			master.setMed4005Dbs(med4005Dbs);
			save(master);
			
			doCopyFileCon0001Db(obj,master);
		}
	}

	//外網檔案上傳
	public void doCopyFileCon0001Db(Med4001Db obj,Med4001Db newObj) throws Exception
	{
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db'  and upLoadId="+obj.getId());
		
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med4001Db");
				newcon.setIsInsert("N");
				newcon.setUpLoadId(newObj.getId());
				ServiceGetter.getInstance().getCommonService().save(newcon);
			}
		}
	}
	
	
	//退件
	public void updateByBackPiecesMedIN0202F(MEDIN0202F ref) throws Exception 
	{
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("02");//退件
			obj.setWorkers(ref.getUserID());//目前登入人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID="+Common.getLong(ref.getId()));
			if(med4001Db!=null)
			{
				med4001Db.setStatus("02");//退件
				med4001Db.setModifier(ref.getUserID());
			    med4001Db.setModifyDate(Datetime.getYYYMMDD());
			    med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
				
			}
	
			//記錄一筆案件流程紀錄
			if("1".equals(obj.getEventKind()))
			{	
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-退件", ref.getUserID());
			}
			else if("2".equals(obj.getEventKind()))
			{
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-退件", ref.getUserID());
			}
			else
			{
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-退件", ref.getUserID());
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-退件", ref.getUserID());
			}
			
		}	
	}
	
	//撤案完成
	public void updateByDismissalMedIN0202F(MEDIN0202F ref) throws Exception 
	{
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("03");//撤案
			obj.setWorkers("");//清空作業人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID="+Common.getLong(ref.getId()));
			if(med4001Db!=null)
			{
				med4001Db.setStatus("03");//撤案
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			
            String str="";
			
			if("1".equals(obj.getEventKind()))
				str="MED01";
			else
				str="MED02";
			
			//撤案時產生一PDF附件置於相關附件頁籤-一般表
			if("03".equals(obj.getStatus())) {
				closedPrint(Common.get(med4001Db.getId()),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(str,obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-撤案", ref.getUserID());
		}	
	}
	
	//校正完成
	public void updateByCorrectionMedIN0202F(MEDIN0202F ref) throws Exception 
	{
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
 
		if(obj!=null)
		{
			
			String status="",workers="",result="N";
			//國內
			if("in".equals(obj.getCaseSource()))
			{
				status="40";//轉送評估
				workers=ref.getUserID();//作業人員
				
				Med0006Db med0006Db=new Med0006Db();
				Med0001Db master =new Med0001Db();
				master.setId(Common.getLong(ref.getId()));
				med0006Db.setMed0001Db(master);
			    ServiceGetter.getInstance().getTcbwService().save(med0006Db);
			}
			else
			{
				status="90";//結案
				workers="";//清空作業人員
				
				result="N";
				String[] value = obj.getBadReactionResults().split(",");
				if(value.length>0)
				{
					for(int j=0; j<value.length; j++) 
					{
						if("01".equals(value[j]) || "02".equals(value[j]))
						{
							result="Y";
						}
					}
				}
				
				//未寫 2-231
				if("Y".equals(result))
				{
					//寄信給TFDA人員
				}	
				
			}	
			
			obj.setStatus(status);
			
			obj.setWorkers(workers);	
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID="+Common.getLong(ref.getId()));
			
			if(med4001Db!=null)
			{
				med4001Db.setStatus(status);//狀態
				
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			
			String str="";
				
			if("1".equals(obj.getEventKind()))
				str="MED01";
			else
				str="MED02";
			
			//結案後產生附件檔
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(str,obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-校正完成", ref.getUserID());
		}	
	}
	
	//補件
	public void updateByAddocumentsMedIN0202F(MEDIN0202F ref) throws Exception 
	{
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));

		if(obj!=null)
		{
			obj.setStatus("30");//補件中
			obj.setWorkers(ref.getUserID());//登入人員
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db a where med0001ID="+Common.getLong(ref.getId()) + " and id in ( select max(id) from Med4001Db b where a.med0001ID = b.med0001ID and applNo != null  group by applNo )");
			if(med4001Db!=null)
			{
				med4001Db.setStatus("30");//補件中
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			String str="";
			
			if("1".equals(obj.getEventKind()))
				str="MED01";
			else
				str="MED02";
			
			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(str,obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-補件", ref.getUserID());
		}	
	}
	 

	
	
	//轉送評估(1:不良反應,2:不良品)
	public void updateBydoTransferMedIN0302F(MEDIN0302F ref) throws Exception 
	{
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			String user="";
			
            if(!"".equals(Common.get(ref.getWorker())))
			  obj.setWorkers(ref.getWorker());
            else
              obj.setWorkers(ref.getAutoWorker());
			
            String status="";
            
            //不良反應
			if("1".equals(obj.getEventKind()))
			{	
				status="70";//案件評估中
			}
			else //不良品
			{
			   //40 轉送評估中(初評)
			   if("40".equals(obj.getStatus()))	
			   {	  
				   status="70";//案件評估中(初評)
			   }
			   else
			   {
				   status="75";//案件評估中(復評)  
			   }
			}
			
			//不良反應
			if("1".equals(obj.getEventKind()))
			{	
			   String hql = " select a.publDept,count(*) as c from Med7001Db a, Med7002Db b where 1=1";
			   hql+= " and a.id = b.med7001Db.id";
			   hql+= " and b.permitKey = '05'"; //+ Common.sqlChar(getMedPermit());
			   hql+= " and b.permitNo = '000001'"; //+ Common.sqlChar(getMedPermitNumber());
			   hql+= " and a.publDept in ('08','11')";
			   hql+= " group by a.publDept";
			   hql+= " order by a.publDept";
			   java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);//計算警訊查詢FDA與MHRA的件數

			   Med0007Db med0007Db=new Med0007Db();
			   Med0001Db master =new Med0001Db();
			   master.setId(Common.getLong(ref.getId()));
			   med0007Db.setMed0001Db(master);
			   
			   if(null != objList && objList.size() > 0) {
					String policeQuery = "";
					for(int i = 0; i < objList.size(); i++) {
						Object numbers[] = (Object[])objList.get(i);
						if("08".equals(Common.get(numbers[0]))) {
							policeQuery+="08";
							med0007Db.setPoliceFdaNum(Common.get(numbers[1]));//警訊查詢-FDA件數
						} else if("11".equals(Common.get(numbers[0]))) {
							policeQuery+="11";
							med0007Db.setPoliceMhraNum(Common.get(numbers[1]));//警訊查詢-MHRA件數
						} else if("未定code".equals(Common.get(numbers[0]))) {
							//policeQuery+="??";
							med0007Db.setPoliceMhraNum(Common.get(numbers[1]));//警訊查詢-ECRI件數
						}
						if(i < objList.size() - 1) {
							policeQuery+=",";
						}
					}
					med0007Db.setPoliceQuery(policeQuery);
				}
			   
		       ServiceGetter.getInstance().getTcbwService().save(med0007Db);
			}
			else //不良品
			{
			   //40   轉送評估	
			   if("40".equals(obj.getStatus()))	
			   {	   
				  Med0008Db med0008Db=new Med0008Db();
				  Med0001Db master =new Med0001Db();
				  master.setId(Common.getLong(ref.getId()));
				  med0008Db.setMed0001Db(master);
				  ServiceGetter.getInstance().getTcbwService().save(med0008Db);
			   }
			   else
			   {	   
				  Med0009Db med0009Db=new Med0009Db();
				  Med0001Db master =new Med0001Db();
				  master.setId(Common.getLong(ref.getId()));
				  med0009Db.setMed0001Db(master);
			      ServiceGetter.getInstance().getTcbwService().save(med0009Db);
			   }
			}
			
			obj.setStatus(status);
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			//修改外網狀態
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId())); 
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			
			String str="";
			
			if("1".equals(obj.getEventKind()))
				str="MED01";
			else
				str="MED02";

			//記錄一筆案件流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(str,obj.getId(), obj.getApplNo(),obj.getStatus(),"案件審核者-轉送評估", ref.getUserID());
			
			String hql=" from Med0006Db where ";
			hql+=" id=(select max(id) from  Med0006Db ";
			hql+=" where  med0001Db.id = " + Common.getLong(ref.getId());
			hql+=" ) order by id desc" ;
			
//			Med0006Db med0006Db=new Med0006Db();
			Med0006Db med0006Db = (Med0006Db)View.getObject(hql); //update最新的一筆評估流程記錄
			Med0001Db master =new Med0001Db();
			if(med0006Db!=null)
			{
			  master.setId(Common.getLong(ref.getId()));
			  med0006Db.setMed0001Db(master);	
			  med0006Db.setBulletinKind(ref.getBulletinKind());
			  med0006Db.setAssignmentKind(ref.getAssignmentKind());
			  med0006Db.setRemark(ref.getRemark());
			  
			  //手動分派
			  if("2".equals(ref.getAssignmentKind()))
			    med0006Db.setWorker(ref.getWorker());
			  else
				med0006Db.setWorker(user);
			  
			  med0006Db.setTransDate(Datetime.getYYYMMDD());//轉送日期
			  med0006Db.setModifier(ref.getEditID());
			  med0006Db.setModifyDate(ref.getEditDate());
			  med0006Db.setModifyTime(ref.getEditTime());
			  ServiceGetter.getInstance().getTcbwService().update(med0006Db);
			}
			

//		    ServiceGetter.getInstance().getTcbwService().save(med0006Db);
			
//			Med0006Db med0006Db = (Med0006Db)View.getObject(hql);
//			
			
			
		}
		
		
	}
	
	
	public String userID(String systemType,String state,String id)
	{
	    
		String hql="select userId from Con2001Db";
		       hql+=" where  id=(select max(id) from Con2001Db where ";
		       hql+=" and systemType=" +Common.sqlChar(systemType);
		       hql+=" and state=" +Common.sqlChar(state);
		       hql+=" and formID="+Common.sqlChar(id);
		
		String worker=View.getLookupField(hql);
		
		
		return worker;
		
	}
	
	//案件評估中(1:不良反應)
	public void updateBydoCompletedMedIN0402F(MEDIN0402F ref) throws Exception 
	{		
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			//待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getIsReCalibration()))
			{
			  status="20";//案件處理中
			  
			  obj.setAutoReUpdate("");
			  
			  obj.setNcarResult(ref.getNcarResult());//存  med0007Db NCAR通報狀況
			  
			  String worker=userID("MED01","40",ref.getId());
			  
			  obj.setWorkers(worker);
			  
			  String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
			  
			  Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010008'");	
			  
			  java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			  
			  String mailBody="",title="";
	
			  if(c!=null)
			  {
				  
				  title=c.getTitle();
				  title=title.replace("[T_案號]", obj.getApplNo());
				  
				  mailBody=c.getMailBody();
				  mailBody=mailBody.replace("[F_案號]", obj.getApplNo());

				  String[] mailAddress =null;
					
				  if(mail.length()>0)
					mailAddress = mail.split(",");
					
				  if(mailAddress!=null && mailAddress.length>0)
				  {
					for(String s : mailAddress)
					{
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					}
				  }
				  
				  ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"MED01",obj.getApplNo());
				  TCBWCommon.setMailbackup("MED01",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"70",obj.getWorkers(),"");	
			  }	
			  
			}
			else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) //是否為再校正案件
			{
				status="20";//案件處理中
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED01","20",ref.getId()); 		
				kind="MED01";
				obj.setIsReCalibration("");
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
			}
			else
			{
			  status="71";//案件評估中(待確認)
			  obj.setWorkers("");
			}
			
			obj.setStatus(status);
			
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			   
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}

			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());
			}
			
			String hql=" from Med0007Db where ";
			hql+=" id=(select max(id) from  Med0007Db ";
			hql+=" where  med0001Db.id = " + Common.getLong(ref.getId());
			hql+=" ) order by id desc" ;
			
			Med0007Db med0007Db = (Med0007Db)View.getObject(hql);
			
			if(med0007Db!=null)
			{	
			  med0007Db.setBulletinQuality(ref.getBulletinQuality());//通報品質
			  med0007Db.setInstructionSheet(ref.getInstructionSheet());//仿單
			  med0007Db.setDocumentRecords(ref.getDocumentRecords());//文獻紀錄
			  med0007Db.setDocumentNum(ref.getDocumentNum());//文獻紀錄-件數
			  String policeQuery = "";
			  if(null!=ref.getPoliceQuery() && ref.getPoliceQuery().length > 0) {
				  for(int i = 0; i < ref.getPoliceQuery().length; i++) {
					  
					  policeQuery += ref.getPoliceQuery()[i];
					  if(i < ref.getPoliceQuery().length - 1) {
						  policeQuery +=",";
					  }
				  }
			  }
			  med0007Db.setPoliceQuery(policeQuery);//警訊查詢
			  med0007Db.setPoliceFdaNum(ref.getPoliceFdaNum());//警訊查詢-FDA件數
			  med0007Db.setPoliceMhraNum(ref.getPoliceMhraNum());//警訊查詢-MHRA件數				  
			  med0007Db.setPoliceEcriNum(ref.getPoliceEcriNum());//警訊查詢-ECRI件數
				
			  String med1005DbTypeCode = "";
			  if(ref.getMed1005DbTypeId() != null) {
				  for(int i = 0; i < ref.getMed1005DbTypeId().length; i++) {
					  med1005DbTypeCode+= ref.getMed1005DbTypeCode()[i];
					  if(i != ref.getMed1005DbTypeId().length-1) {
						  med1005DbTypeCode+=",";
					  }
				  }
			  }
			  med0007Db.setMedicalIssues(med1005DbTypeCode);//病人問題代碼
			  
			  String med1006DbTypeCode = "";
			  if(ref.getMed1006_0403DbTypeId() != null) {
				  for(int i = 0; i < ref.getMed1006_0403DbTypeId().length; i++) {
					  med1006DbTypeCode+= ref.getMed1006_0403DbTypeCode()[i];
					  if(i != ref.getMed1006_0403DbTypeId().length-1) {
						  med1006DbTypeCode+=",";
					  }
				  }
			  }
			  med0007Db.setPatientIssues(med1006DbTypeCode);//醫材問題代碼
			  
			  med0007Db.setAssessAdverseReactions(ref.getAssessAdverseReactions());//評估不良反應
			  med0007Db.setAdverseReactionsResult(ref.getAdverseReactionsResult());//評估不良反應結果
			  med0007Db.setAdverseTotal(ref.getAdverseTotal());
				  
			  med0007Db.setEventSeverity(ref.getEventSeverity());//事件嚴重程度
			  med0007Db.setCommentOpinion(ref.getCommentOpinion());//初評意見
			  med0007Db.setCommentExplanation1(ref.getCommentExplanation1());//初評意見說明1
			  med0007Db.setCommentExplanation2(ref.getCommentExplanation2());//初評意見說明2
			  med0007Db.setOtherOpinion(ref.getOtherOpinion());//其他意見
					    
			  med0007Db.setNcarOptions(ref.getNcarOptions());//Ncar通報篩選
			  med0007Db.setNcarResult(ref.getNcarResult());//Ncar通報篩選結果
			  med0007Db.setNcarTotal(ref.getNcarTotal());//Ncar通報總分
			  med0007Db.setEventDetails(ref.getEventDetails());//需再取得事件詳細說明
			  med0007Db.setResultsNotification(ref.getResultsNotification());//結果通知

			  med0007Db.setEvaluationDate(Datetime.getYYYMMDD());//評估日期/
			  med0007Db.setModifier(ref.getEditID());
			  med0007Db.setModifyDate(ref.getEditDate());
			  med0007Db.setModifyTime(ref.getEditTime());
		      ServiceGetter.getInstance().getTcbwService().update(med0007Db);
			}
		}
		
		
	}
	
	//案件確認(1:不良反應)
	public void updateBydoCompletedConfirmedMedIN0402F(MEDIN0402F ref) throws Exception 
	{
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			
			//如果有待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getIsReCalibration()))
			{
				status="20";//案件處理中
				obj.setAutoReUpdate("");
				String worker=userID("MED01","50",ref.getId());
				obj.setWorkers(worker);
				
				String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
	
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				  
				String mailBody="",title="";
		
				title="【案件通知】"+obj.getApplNo()+"案件已回處理中";
				
				mailBody="案件已回案件處理中!";
				
				String[] mailAddress =null;
						
				if(mail.length()>0)
				     mailAddress = mail.split(",");
						
				if(mailAddress!=null && mailAddress.length>0)
				{
					for(String s : mailAddress)
					{
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					}
					
					ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"MED01",obj.getApplNo());
					TCBWCommon.setMailbackup("MED01",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"71",obj.getWorkers(),"");	
				}	
				
				
			}
			else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) //是否為再校正案件
			{
				status="20";//案件處理中
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED01","20",ref.getId()); 		
				kind="MED01";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
			}
			else
			{
				//若需在取得事件詳細說明
				if("Y".equals(ref.getEventDetails()))
				{
				   status="20";//案件處理中
				   String worker=userID("MED01","50",ref.getId());
				   obj.setWorkers(worker);
				}
				else
				{
				   status="90";//結案
				   obj.setWorkers("");
				   
				   String worker=userID("MED01","50",ref.getId());
				   
				   String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
					  
				   Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010008'");	
					  
				   java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					  
				   String mailBody="",title="";
			
				   if(c!=null)
				   {
						  
					  title=c.getTitle();
					  title=title.replace("[T_案號]", obj.getApplNo());
						  
					  mailBody=c.getMailBody();
					  mailBody=mailBody.replace("[F_案號]", obj.getApplNo());

					  String[] mailAddress =null;
							
					  if(mail.length()>0)
						mailAddress = mail.split(",");
							
					  if(mailAddress!=null && mailAddress.length>0)
					  {
						for(String s : mailAddress)
						{
							javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
							p.setAddress(s);
							mailList.add(p);
						}
					  }
					  
					  String[]  bccmailListAddress =null;
						
					  if(obj.getBadReactionResults().indexOf("01")!=-1 || obj.getBadReactionResults().indexOf("02")!=-1)
					  {
					  
						 java.util.List m=getMail("02","MED01");
						 
						 String bccMail="";
						 
						 for(int i=0; i<m.size(); i++)
						 {
							Con1015Db con1015Db = (Con1015Db) m.get(i);
							bccMail+=con1015Db.getCommonUser().getUserEmail();
							bccMail+=",";
						 }
						 
					     if(mail.length()>0)
						    bccmailListAddress = bccMail.split(",");
							
					     if(bccmailListAddress!=null && bccmailListAddress.length>0)
					     {
						   for(String s : bccmailListAddress)
						   {
							 javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
							 p.setAddress(s);
							 mailList.add(p);
						   }
					     }
					  }
					  ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, mailList,"MED01",obj.getApplNo());
					  TCBWCommon.setMailbackup("MED01",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"71",obj.getWorkers(),"");	
					}	
				  
				}
			}	
		
			obj.setStatus(status);
			
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			   
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			
			//結案後產生附件檔
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中(待確認)", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());
			}
			
		}
	}
	
	public java.util.List getMail(String code,String formCode) throws Exception 
	{
	   
		String hql = " from Con1015Db ";
	          hql += " where con1014Db.code = " + Common.sqlChar(code);   
	          hql += " and con1014Db.con1007Db.formCode = " + Common.sqlChar(formCode);
	        
	   java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);

	   return list;

	}
	
	
	//案件評估中(2:不良品)
	public void updateBydoCompletedMedIN0702F(MEDIN0702F ref) throws Exception 
	{
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			//如果有待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getIsReCalibration()))
			{
				status="20";//案件處理中
				
				obj.setAutoReUpdate("");
				
				String worker=userID("MED01","40",ref.getId());
				
				String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
				
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				  
				String mailBody="",title="";
		
				title="【案件通知】"+obj.getApplNo()+"案件已回處理中";
				
				mailBody="案件已回案件處理中!";
				
				String[] mailAddress =null;
						
				if(mail.length()>0)
				     mailAddress = mail.split(",");
						
				if(mailAddress!=null && mailAddress.length>0)
				{
					for(String s : mailAddress)
					{
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					}
					
					ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"MED02",obj.getApplNo());
					TCBWCommon.setMailbackup("MED02",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"70",obj.getWorkers(),"");	
				}	
				
				
			}
			else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) //是否為再校正案件
			{
				status="20";//案件處理中
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED02","20",ref.getId()); 		
				kind="MED02";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
			}
			else
			{
				status="71";//案件評估中(初評待確認)
				obj.setWorkers("");
			}	
			
			obj.setNcarResult(ref.getNcarResult());//med0008Db NCAR通報狀況
			obj.setEventClass(ref.getEventClass());//med0008Db 不良品事件等級
			
			obj.setStatus(status);
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			
			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中(初評)", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());
			}
			
			String hql=" from Med0008Db where ";
			       hql+=" id=(select max(id) from  Med0008Db ";
			       hql+=" where  med0001Db.id = " + Common.getLong(ref.getId());
			       hql+=" ) order by id desc" ;
			
			Med0008Db med0008Db = (Med0008Db)View.getObject(hql);
			
			if(med0008Db!=null)
			{	
				med0008Db.setBulletinQuality(ref.getBulletinQuality());//通報品質
				med0008Db.setEvaluationDate(Datetime.getYYYMMDD());//評估日期
				med0008Db.setEventClass(ref.getEventClass());//事件等級
				med0008Db.setEventDetails(ref.getEventDetails());//需再取得事件詳細說明
				String med1006DbTypeCode = "";
				  if(ref.getMed1006DbTypeId() != null) {
					  for(int i = 0; i < ref.getMed1006DbTypeId().length; i++) {
						  med1006DbTypeCode+= ref.getMed1006DbTypeCode()[i];
						  if(i != ref.getMed1006DbTypeId().length-1) {
							  med1006DbTypeCode+=",";
						  }
					  }
				  }
				med0008Db.setMedicalIssues(med1006DbTypeCode);//醫材問題代碼
				med0008Db.setAssessProposal(ref.getAssessProposal());//評估建議
				med0008Db.setNcarOptions(ref.getNcarOptions());//Ncar通報篩選
				med0008Db.setNcarResult(ref.getNcarResult());//Ncar通報篩選結果
				med0008Db.setNcarTotal(ref.getNcarTotal());//Ncar總分
				  
				med0008Db.setModifier(ref.getEditID());
				med0008Db.setModifyDate(ref.getEditDate());
				med0008Db.setModifyTime(ref.getEditTime());
				
		        ServiceGetter.getInstance().getTcbwService().update(med0008Db);
			}
		}

	}
	
	//案件確認(2:不良品)
	public void updateBydoCompletedConfirmedMedIN0702F(MEDIN0702F ref) throws Exception 
	{
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			//如果有待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getReCalibrationReason()))
			{
				status="20";//案件處理中
                
				String worker=userID("MED01","40",ref.getId());
				
				String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
				
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				  
				String mailBody="",title="";
		
				title="【案件通知】"+obj.getApplNo()+"案件已回處理中";
				
				mailBody="案件已回案件處理中!";
				
				String[] mailAddress =null;
						
				if(mail.length()>0)
				     mailAddress = mail.split(",");
						
				if(mailAddress!=null && mailAddress.length>0)
				{
					for(String s : mailAddress)
					{
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					}
					
					ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"MED02",obj.getApplNo());
					TCBWCommon.setMailbackup("MED02",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"71",obj.getWorkers(),"");	
				}	
			} 
			else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) //是否為再校正案件
			{
				status="20";//案件處理中
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED02","20",ref.getId()); 		
				kind="MED02";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
			}
			else
			{
				//如果需取得在說明=Y
				if("Y".equals(ref.getEventDetails()))
				{
					status="20";//案件處理中
				}
				else
				{
					//如果需取得在說明=N，未發生過主動補件
					if(!"Y".equals(obj.getAutoReUpdate()))
					{
					   status="50";//廠商通知中
					   if(!"3".equals(obj.getEventClass())) {
							Med0006Db med0006Db=new Med0006Db();
							Med0001Db master =new Med0001Db();
							master.setId(Common.getLong(ref.getId()));
							med0006Db.setMed0001Db(master);
						    ServiceGetter.getInstance().getTcbwService().save(med0006Db);
					   }

					}
					else if("Y".equals(obj.getAutoReUpdate()) 
							&& "Y".equals(ref.getConveyedVendors()))
					{
					   status="50";//廠商通知中
						Med0006Db med0006Db=new Med0006Db();
						Med0001Db master =new Med0001Db();
						master.setId(Common.getLong(ref.getId()));
						med0006Db.setMed0001Db(master);
					    ServiceGetter.getInstance().getTcbwService().save(med0006Db);
					}	
					else if("Y".equals(obj.getAutoReUpdate()) 
							&& "N".equals(ref.getConveyedVendors())
							&& ( "A".equals(ref.getEventClass()) ||"B".equals(ref.getEventClass()))
					        )
					{						
						status="75";//案件評估中(複評)
						
						String worker=userID("MED01","71",ref.getId());
						   
						String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
							  
						Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010009'");	
							  
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
							  
						String mailBody="",title="";
					
						if(c!=null)
						{
								  
						   title=c.getTitle();
						   title=title.replace("[T_案號]", obj.getApplNo());
								  
						   mailBody=c.getMailBody();
						   mailBody=mailBody.replace("[F_案號]", obj.getApplNo());

						   String[] mailAddress =null;
									
						   if(mail.length()>0)
							 mailAddress = mail.split(",");
									
						   if(mailAddress!=null && mailAddress.length>0)
						   {
							  for(String s : mailAddress)
							  {
								javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
								p.setAddress(s);
								mailList.add(p);
							  }
						   }
							  
						   String[]  bccmailListAddress =null;
								
						   if(ref.getEventClass().indexOf("A")!=-1 )
						   {
							  
							  java.util.List m=getMail("02","MED01");
								 
							  String bccMail="";
								 
							  for(int i=0; i<m.size(); i++)
							  {
								Con1015Db con1015Db = (Con1015Db) m.get(i);
								bccMail+=con1015Db.getCommonUser().getUserEmail();
								bccMail+=",";
							  }
								 
							  if(mail.length()>0)
								 bccmailListAddress = bccMail.split(",");
									
							  if(bccmailListAddress!=null && bccmailListAddress.length>0)
							  {
								  for(String s : bccmailListAddress)
								  {
									 javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
									 p.setAddress(s);
									 mailList.add(p);
								  }
							  }
							}
							ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, mailList,"MED02",obj.getApplNo());
							TCBWCommon.setMailbackup("MED02",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"71",obj.getWorkers(),"");	
						}	
											    
					    Med0006Db med0006Db=new Med0006Db();
						Med0009Db med0009Db=new Med0009Db();
					    Med0001Db master =new Med0001Db();
						master.setId(Common.getLong(ref.getId()));
						med0006Db.setMed0001Db(master);
						med0009Db.setMed0001Db(master);
						ServiceGetter.getInstance().getTcbwService().save(med0006Db);
					    ServiceGetter.getInstance().getTcbwService().save(med0009Db);
					}	
					else if("Y".equals(obj.getAutoReUpdate()) 
							&& "N".equals(ref.getConveyedVendors())
							&& "C".equals(ref.getEventClass())
					        )
					{
						status="90";//結案
						obj.setWorkers("");
					}
				}
			}	
			
			obj.setStatus(status);
			
			obj.setBulletinQuality(ref.getBulletinQuality());//通報品質
			obj.setEventClass(ref.getEventClass());//事件等級
			String med1006DbTypeCode = "";
			if(ref.getMed1006DbTypeId() != null)
			{
				for(int i = 0; i < ref.getMed1006DbTypeId().length; i++) 
				{
				    med1006DbTypeCode+= ref.getMed1006DbTypeCode()[i];
					if(i != ref.getMed1006DbTypeId().length-1) 
					{
						  med1006DbTypeCode+=",";
					}
				}
			}
			obj.setMedicalIssues(med1006DbTypeCode);//醫材問題代碼
			
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			   
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			//結案後產生附件檔
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中(初評待確認)", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());
			}
			
		}
	}
	
	//案件評估中(複評)(2:不良品)
	public void updateBydoCompletedMedIN0802F(MEDIN0802F ref) throws Exception 
	{
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			//如果有待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getIsReCalibration()))
			{
				status="20";//案件處理中
				
                String worker=userID("MED01","71",ref.getId());
				
				String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
				
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				  
				String mailBody="",title="";
		
				title="【案件通知】"+obj.getApplNo()+"案件已回處理中";
				
				mailBody="案件已回案件處理中!";
				
				String[] mailAddress =null;
						
				if(mail.length()>0)
				     mailAddress = mail.split(",");
						
				if(mailAddress!=null && mailAddress.length>0)
				{
					for(String s : mailAddress)
					{
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					}
					
					ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"MED02",obj.getApplNo());
					TCBWCommon.setMailbackup("MED02",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"75",obj.getWorkers(),"");	
				}	
				
			} else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) {//是否為再校正案件
				status="20";//案件處理中
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED02","20",ref.getId()); 		
				kind="MED02";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
			}
			else
			{
				status="76";//案件評估中(復評待確認)
				obj.setWorkers("");
			}	
			
			obj.setStatus(status);
			
			obj.setNcarResult(ref.getNcarResult0803());//med0009Db NCAR通報狀況
			obj.setEventClass(ref.getEventClass0803());//med0009Db 不良品事件等級
			
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			   
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			
			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中(複評)", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());

			}
			
			
			String hql=" from Med0009Db where ";
			hql+=" id=(select max(id) from  Med0009Db ";
			hql+=" where  med0001Db.id = " + Common.getLong(ref.getId());
			hql+=" ) order by id desc" ;
			
			Med0009Db med0009Db = (Med0009Db)View.getObject(hql);
			
			if(med0009Db!=null)
			{	
				med0009Db.setBulletinQuality(ref.getBulletinQuality());//通報品質
				med0009Db.setEvaluationDate(Datetime.getYYYMMDD());//評估日期
				med0009Db.setEventClass(ref.getEventClass0803());//事件等級
				med0009Db.setEventDetails(ref.getEventDetails());//需再取得事件詳細說明
				String med1006DbTypeCode = "";
				  if(ref.getMed1007DbTypeId() != null) {
					  for(int i = 0; i < ref.getMed1007DbTypeId().length; i++) {
						  med1006DbTypeCode+= ref.getMed1007DbTypeCode()[i];
						  if(i != ref.getMed1007DbTypeId().length-1) {
							  med1006DbTypeCode+=",";
						  }
					  }
				  }
				med0009Db.setMedicalIssues(med1006DbTypeCode);//醫材問題代碼
//				med0009Db.setMedicalIssues(ref.getMedicalIssues());//醫材問題代碼
				med0009Db.setAssessProposal(ref.getAssessProposal0803());//評估建議
				med0009Db.setNcarOptions(ref.getNcarOptions0803());//Ncar通報篩選
				med0009Db.setNcarResult(ref.getNcarResult0803());//Ncar通報篩選結果
				med0009Db.setNcarTotal(ref.getNcarTotal());//Ncar總分
				  
				med0009Db.setModifier(ref.getEditID());
				med0009Db.setModifyDate(ref.getEditDate());
				med0009Db.setModifyTime(ref.getEditTime());
				
		        ServiceGetter.getInstance().getTcbwService().update(med0009Db);
			}
		}
	}
	
	//案件評估中(待確認)(2:不良品)
	public void updateBydoCompletedConfirmedMedIN0802F(MEDIN0802F ref) throws Exception 
	{
		String status="";
		
        Med0001Db obj = (Med0001Db)View.getObject("from Med0001Db where id="+ref.getId());
		
		if(obj!=null)
		{
			//如果有待處理的主動補件
			if("Y".equals(obj.getAutoReUpdate()) || "Y".equals(obj.getIsReCalibration()))
			{
				status="20";//案件處理中
			} else if("".equals(obj.getAutoReUpdate()) && "Y".equals(obj.getIsReCalibration())) { //若為再校正案件
				String worker = "", kind = "";
				String title="【資料再校正】",mailBody="",mail="";
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

				status="20";//案件處理中
				worker=worker("MED02","20",ref.getId()); 		
				kind="MED02";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getWorkers()));
				    
				mail = email;
				
				if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
				  
				mailBody = "資料再校正。";

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
			}
			else
			{
				status="90";//結案
				obj.setWorkers("");
				
				String worker=userID("MED01","75",ref.getId());
				   
				String mail=View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(worker));
					  
				Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010009'");	
					  
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					  
				String mailBody="",title="";
			
				if(c!=null)
				{
						  
				   title=c.getTitle();
				   title=title.replace("[T_案號]", obj.getApplNo());
						  
				   mailBody=c.getMailBody();
				   mailBody=mailBody.replace("[F_案號]", obj.getApplNo());

				   String[] mailAddress =null;
							
				   if(mail.length()>0)
					 mailAddress = mail.split(",");
							
				   if(mailAddress!=null && mailAddress.length>0)
				   {
					  for(String s : mailAddress)
					  {
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						p.setAddress(s);
						mailList.add(p);
					  }
				   }
					  
				   String[]  bccmailListAddress =null;
						
				   if(ref.getEventClass0803().indexOf("A")!=-1 )
				   {
					  
					  java.util.List m=getMail("02","MED01");
						 
					  String bccMail="";
						 
					  for(int i=0; i<m.size(); i++)
					  {
						Con1015Db con1015Db = (Con1015Db) m.get(i);
						bccMail+=con1015Db.getCommonUser().getUserEmail();
						bccMail+=",";
					  }
						 
					  if(mail.length()>0)
						 bccmailListAddress = bccMail.split(",");
							
					  if(bccmailListAddress!=null && bccmailListAddress.length>0)
					  {
						  for(String s : bccmailListAddress)
						  {
							 javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
							 p.setAddress(s);
							 mailList.add(p);
						  }
					  }
					}
				   
				  ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, mailList,"MED02",obj.getApplNo());
				  TCBWCommon.setMailbackup("MED02",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"76",obj.getWorkers(),"");	
			   }	
			}
			obj.setStatus(status);
			
			obj.setBulletinQuality(ref.getBulletinQuality());//通報品質
			obj.setEventClass(ref.getEventClass0803());//事件等級
			String med1007DbTypeCode = "";
			  
			if(ref.getMed1007DbTypeId() != null) 
			{
				  for(int i = 0; i < ref.getMed1007DbTypeId().length; i++) 
				  {
					  med1007DbTypeCode+= ref.getMed1007DbTypeCode()[i];
					  if(i != ref.getMed1007DbTypeId().length-1)
					  {
						  med1007DbTypeCode+=",";
					  }
				  }
			}
		    obj.setMedicalIssues(med1007DbTypeCode);//醫材問題代碼
			
			obj.setModifier(ref.getEditID());
			obj.setModifyDate(ref.getEditDate());
			obj.setModifyTime(ref.getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id=(select max(id) from Med4001Db where med0001ID = " + Common.getLong(obj.getId())+" )" );
			   
			if(med4001Db!=null)
			{	
				med4001Db.setStatus(status);
				med4001Db.setModifier(ref.getUserID());
				med4001Db.setModifyDate(Datetime.getYYYMMDD());
				med4001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().update(med4001Db);
			}
			//結案後產生附件檔
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			//記錄一筆案件流程紀錄
			if(!"Y".equals(obj.getIsReCalibration())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),"案件評估者-案件評估中(待確認)", ref.getUserID());
			} else {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(), obj.getApplNo(),obj.getStatus(),obj.getReCalibrationReason(), ref.getUserID());

			}
		}
	}
	
	//案件再校正
	public void updateBydoReCalibrationMedIN0902F(MEDIN0902F ref) throws Exception 
	{
		String worker = "", kind = "";
		
		Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
		if(med0001Db != null){
			if(!"90".equals(Common.get(med0001Db.getStatus()))) {
				med0001Db.setIsReCalibration("Y");
				med0001Db.setReCalibrationReason(Common.get(ref.getReCalibrationReason()));
			}
			String title="【資料再校正】",mailBody="",mail="";
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();

			//若該案件為結案狀態，改變狀態為處理中並寫入流程
			if("1".equals(med0001Db.getEventKind()) && "90".equals(med0001Db.getStatus()))
			  {
				worker=worker("MED01","20",ref.getId()); 		
				//med0001Db.setWorkers(worker); //設定作業人員
				kind="MED01";
				//設定郵件內容
				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(worker));
				    
				mail = email;
				
				if(med0001Db.getApplNo()!= null) title += "案號:"+med0001Db.getApplNo();	
				  
				mailBody = "資料再校正。";
				           				  
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
				med0001Db.setStatus("20");
				med0001Db.setModifier(ref.getUserID());
				med0001Db.setModifyDate(Datetime.getYYYMMDD());
				med0001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),ref.getReCalibrationReason(), ref.getUserID());

			  }
			  else if("90".equals(med0001Db.getStatus())) //若該案件為結案狀態，改變狀態為處理中並寫入流程
			  {
				worker=worker("MED02","20",ref.getId()); 		
				kind="MED02";
				//設定郵件內容

				String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(med0001Db.getWorkers()));
				    
				mail = email;
				
				if(med0001Db.getApplNo()!= null) title += "案號:"+med0001Db.getApplNo();	
				  
				mailBody = "資料再校正。";

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
				
				med0001Db.setStatus("20");
				med0001Db.setModifier(ref.getUserID());
				med0001Db.setModifyDate(Datetime.getYYYMMDD());
				med0001Db.setModifyTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),ref.getReCalibrationReason(), ref.getUserID());
			 }
			 ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null,kind,med0001Db.getApplNo());

			 TCBWCommon.setMailbackup(kind,Common.get(med0001Db.getId()),title, mailBody, med0001Db.getApplNo(),med0001Db.getStatus(), ref.getUserID(),"");
			 update(med0001Db);
			// 同步外部狀態
			updateMed4001DbStatus(med0001Db);
		}else{
			ref.setIsNeedBackQuery("N");
			ref.setErrorMsg("查無案件資料，請確認");
		}
		ref.setIsRCEnd("Y");
	}
	
	public void updateMed4001DbStatus(Med0001Db obj) throws Exception {
		if(obj != null){
			Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where med0001ID = " + Common.getLong(obj.getId()));
			if(med4001Db != null){
				med4001Db.setStatus(obj.getStatus());
				med4001Db.setModifier(obj.getModifier());
				med4001Db.setModifyDate(obj.getModifyDate());
				med4001Db.setModifyTime(obj.getModifyTime());
				update(med4001Db);
			}
		}
	}
	
	public String worker(String systemType,String state,String id)
	{
		String hql="select userID from Con2001Db";
		       hql+=" where id=(select max(id) from Con2001Db where 1=1 ";
		       hql+=" and systemType=" +Common.sqlChar(systemType);
		       hql+=" and state=" +Common.sqlChar(state);
		       hql+=" and formID="+Common.sqlChar(id);
		       hql+=")";
		
		String worker=View.getLookupField(hql);
		
		
		return worker;
		
	}
	
	public void updateByenforceCloseMedIN0903F(MEDIN0903F ref) throws Exception 
	{
		Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where applNo= " + Common.sqlChar(ref.getApplNo()));
		if(null!=med0001Db) {
			med0001Db.setStatus("90");
			med0001Db.setWorkers("");//清空作業人員
			ServiceGetter.getInstance().getTcbwService().update(med0001Db);
			if("1".equals(med0001Db.getEventKind())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),ref.getEnforceCloseReason(), ref.getUserID());
			} else if("2".equals(med0001Db.getEventKind())) {
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),ref.getEnforceCloseReason(), ref.getUserID());
			}
		}
		if("90".equals(med0001Db.getStatus())) {
			closedPrint(med0001Db.getApplNo(),Common.get(med0001Db.getId()),ref.getUserID(),ref.getState());
		}
		ref.setIsEnd("Y");
		ref.setState("isEnd");
			
	}
	
	
	
	@Override 
	public void updateSendByMEDIN0501F(MEDIN0501F ref,String sb) throws Exception 
	{
		
			if(sb.toString().length() > 0)
			{
				java.util.List objList = load(" from Med0001Db where id in (" + sb.toString() + ")");
				
				if(objList!=null && objList.size()>0)
				{
					java.util.List<Med0001Db> uList = new java.util.ArrayList<Med0001Db>();
					java.util.List<Med0010Db> uList10 = new java.util.ArrayList<Med0010Db>();
					
					String hql=" select eventClass from Med0008Db where ";
					       hql+=" id=(select max(id) from  Med0008Db ";
					       hql+=" where med0001Db.id =";
				
					
					for(Object dtlObj : objList)
					{
						Med0001Db dtl = (Med0001Db)dtlObj;
						
					    String eventClass=View.getLookupField(hql+Common.getLong(dtl.getId())+")");
						
					    Med4001Db med4001db=(Med4001Db)View.getObject("from Med4001Db where med0001ID="+dtl.getId());
					    
					    //事件等級=C
					    if("3".equals(eventClass))
					    {
					    	dtl.setStatus("90");//結案
					    	med4001db.setStatus("90");
					    	dtl.setWorkers("");//清空作業人員
					    }	
					    else
					    {
					    	dtl.setStatus("51");//廠商通知中
					    	med4001db.setStatus("51");
					    	
						    //新增廠商回覆檔
						    Med0010Db med0010Db=new Med0010Db();
						    
					        Med0001Db master=new Med0001Db();
						    master.setId(dtl.getId());
						    med0010Db.setMed0001Db(master);
						    med0010Db.setNoticeDate(Datetime.getYYYMMDD());
						    med0010Db.setMedCountry(dtl.getMedCountry());
						    med0010Db.setMedPermitFirmCode(dtl.getMedPermitFirmCode());
			
						    save(med0010Db);
					    }
					    
					    update(med4001db);

					    uList.add(dtl);

					    
					  //結案後產生附件檔
						if("90".equals(dtl.getStatus())) {
							closedPrint(dtl.getApplNo(),Common.get(dtl.getId()),ref.getUserID(),ref.getState());
						}
					    //記錄一筆案件流程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",dtl.getId(), dtl.getApplNo(),dtl.getStatus(),"案件評估者-廠商通知中", ref.getUserID());
		
					}
					
					if(uList.size() > 0)
					{
						updateBatch(uList);
						uList.clear();
					}
				}
				else
				{
					logger.info("[TCBW]-[MedinDaoImpl]-[醫療器材不良事件通報-廠商通知補件]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
	
		
	}
	
	
	//產生備份PDF
	public void closedPrint(String applNo,String id,String userID,String status) throws Exception 
	{
		MEDEX0104F medex0104f=new MEDEX0104F();
		
		if(!"".equals(applNo)) {
			if("90".equals(status)) {
				medex0104f.setApplNo(applNo);
			} else if("03".equals(status)) {
				medex0104f.setId(applNo);
			}
		}
		
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
		
		medex0104f.setParameter(parms);
	    
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		javax.swing.table.DefaultTableModel model = medex0104f.getDefaultTableModel();
		

		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r.jasper"));
		env.setFormat("PDF");	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	    
	    String med = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("MED");
	    
	    String fileName="";//檔案名稱
	    String fileDir="MED010001Backup";//存放資料夾
	    
	    if(!"".equals(applNo))fileName=applNo;
	   
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
	    o.setFileExplan("醫療器材不良事件通報備查PDF");
	    o.setIsInsert("N");
	    o.setIsDelete("N");
	    o.setSystemType("MED010001");
	    o.setDbName("Med0001Db");
	    o.setCreator(userID);
	    o.setCreateDate(Datetime.getYYYMMDD());
	    o.setCreateTime(Datetime.getHHMMSS());

	    save(o);

	    env.clear();

	}
	
	
	
	public List<Med1005Db> queryMED1005DB() throws Exception{
		String hql = " from Med1005Db ";
		return load(hql);
	}
	public List<Med1006Db> queryMED1006DB() throws Exception{
		String hql = " from Med1006Db ";
		return load(hql);
	}
	
	public void saveOrUpdateMed0002Db(java.util.List<Med0002Db> saveList,java.util.List<Med0002Db> updateList,java.util.List<Med0002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed0003Db(java.util.List<Med0003Db> saveList,java.util.List<Med0003Db> updateList,java.util.List<Med0003Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed0004Db(java.util.List<Med0004Db> saveList,java.util.List<Med0004Db> updateList,java.util.List<Med0004Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed0005Db(java.util.List<Med0005Db> saveList,java.util.List<Med0005Db> updateList,java.util.List<Med0005Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	
	
}
