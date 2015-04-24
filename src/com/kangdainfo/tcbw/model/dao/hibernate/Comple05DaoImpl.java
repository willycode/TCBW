package com.kangdainfo.tcbw.model.dao.hibernate;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.bo.Med0003Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0005Db;
import com.kangdainfo.tcbw.model.dao.Comple05Dao;
import com.kangdainfo.tcbw.view.comple.COMPLE0502F;


public class Comple05DaoImpl extends BaseDaoImpl implements Comple05Dao
{


	@Override
	public void updateByCOMPLE0502F(COMPLE0502F ref) throws Exception 
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
		
		Med0001Db obj = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(ref.getId()));
		
		if(obj != null) 
		{
			obj.setOccurDate(ref.getOccurDate());								//通報日期
			
			//obj.setNotifierRevDate(ref.getNotifierRevDate());					//通報者接獲日期
			
			obj.setNotifierRepDate(ref.getNotifierRepDate());	
			
			obj.setDrugEventsSources(ref.getDrugEventsSources());				//原始藥物不良事件獲知來源

			if("1".equals(ref.getDrugEventsSources())) 
			{
				obj.setMedicalStaff(ref.getMedicalStaff());						//原始藥物不良事件獲知來源-醫療人員
				obj.setHealthUnits(null);
				obj.setHealthUnitsOther(null);
				if("5".equals(ref.getMedicalStaff())) 
				{
					obj.setMedicalStaffOther(ref.getMedicalStaffOther());		//原始藥物不良事件獲知來源-醫療人員-其他
				} 
				else 
				{
					obj.setMedicalStaffOther(null);
				}
			} 
			else if("2".equals(ref.getDrugEventsSources()))
			{
				obj.setHealthUnits(ref.getHealthUnits());//原始藥物不良事件獲知來源-衛生單位
				obj.setMedicalStaff(null);
				obj.setMedicalStaffOther(null);
				if("2".equals(ref.getHealthUnits())) 
				{
					obj.setHealthUnitsOther(ref.getHealthUnitsOther());//原始藥物不良事件獲知來源-衛生單位-其他
				} 
				else 
				{
					obj.setHealthUnitsOther(null);
				}
			}

			obj.setCaseSource(ref.getCaseSource());//案例來源
			
			if("out".equals(ref.getCaseSource())) 
			{
				obj.setCaseSourceOutCountry(ref.getCaseSourceOutCountry());	//案例來源-國外(國家)
			} 
			else 
			{
				obj.setCaseSourceOutCountry(null);
			}
		
			obj.setReportKind(ref.getReportKind());//報告類別
			
			if("2".equals(ref.getReportKind())) 
			{
				obj.setTrackingNum(ref.getTrackingNum());//報告類別-追蹤報告第N次
			}
			else 
			{
				obj.setTrackingNum(null);
			}
			
			obj.setCorrectiveAction(ref.getCorrectiveAction());					//報告類別-矯正措施
			obj.setAttachment(ref.getAttachment());								//附件
			
			if("Y".equals(ref.getAttachment()))
			{
				obj.setAttachmentYnum(ref.getAttachmentYnum());					//附件-有共N件
			} 
			else 
			{
				obj.setAttachmentYnum(null);
			}
			
			obj.setDrugSafetyMonitoring(ref.getDrugSafetyMonitoring());			//產品經公告列入 藥物安全監視
			  
			//通報者資訊
			obj.setNotifierName(ref.getNotifierName());							//姓名
			obj.setNotifierAreaCode(ref.getNotifierAreaCode());					//電話區碼
			obj.setNotifierTel(ref.getNotifierTel());							//電話
			obj.setNotifierTelExt(ref.getNotifierTelExt());
			obj.setNotifierDeptID(ref.getNotifierDeptID());	//通報者屬性-服務機構
			obj.setNotifierDept(ref.getNotifierDept());	
			obj.setNotifierEamil(ref.getNotifierEamil());						//E-mail
			obj.setNotifierAddress(ref.getNotifierAddress());					//地址
			obj.setNotifierCounty(ref.getNotifierCounty());						//縣市
			obj.setNotifierZipCode(ref.getNotifierZipCode());					//地區
			obj.setNotifierType(ref.getNotifierType());							//通報者屬性
			
			if("03".equals(ref.getNotifierType())) 
			{
				obj.setNotifierStaffHospital(ref.getNotifierStaffHospital());	//通報者屬性-醫院名稱
				obj.setNotifierStaffTitle(ref.getNotifierStaffTitle());			//通報者屬性-醫療人員-職稱
				if("5".equals(ref.getNotifierStaffTitle())) 
				{
					obj.setNotifierStaffTitleOther(ref.getNotifierStaffTitleOther());//通報者屬性-醫療人員-職稱-其他
				} 
				else 
				{
					obj.setNotifierStaffTitleOther(null);
				}
			} 
			else if("02".equals(ref.getNotifierType())) 
			{
				
				//obj.setNotifierFirmDept(ref.getNotifierFirmDept());//通報者屬性-服務機構
				obj.setNotifierStaffHospital(null);
				obj.setNotifierStaffTitle(null);
				obj.setNotifierStaffTitleOther(null);
			} 
			else if("04".equals(ref.getNotifierType()))
			{
				obj.setNotifierStaffHospital(null);
				obj.setNotifierStaffTitle(null);
				obj.setNotifierStaffTitleOther(null);
			} 
			else 
			{
				obj.setNotifierStaffHospital(null);
				obj.setNotifierStaffTitle(null);
				obj.setNotifierStaffTitleOther(null);
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
			obj.setMedPermitFirmCode(ref.getMedPermitFirmCode());				//許可證申請商
			obj.setMedPermitFirm(ref.getMedPermitFirm());						//許可證申請商
			obj.setMedCname(ref.getMedCname());									//中文品名
			
			obj.setMedMainCategoryCode(ref.getMedMainCategory());		//醫材主類別
			obj.setMedMainCategory(ref.getMedSecCategoryCodeName());			//醫材主類別
			  
			obj.setMedSecCategoryCode(ref.getMedSecCategory());			//醫材次類別
			obj.setMedSecCategory(ref.getMedSecCategoryCodeName());				//醫材次類別
			
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
				
			obj.setBadReactionResultsDeathDate(ref.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	VARCHAR(7)
			obj.setBadReactionResultsDeathReason(ref.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			obj.setBadReactionResultsOther(ref.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			  
			if(obj.getBadReactionResults().indexOf("01")!=-1)
			{	
			  obj.setBadReactionResultsDeathDate(ref.getBadReactionResultsDeathDate());//不良反應結果-A死亡-日期	VARCHAR(7)
			  obj.setBadReactionResultsDeathReason(ref.getBadReactionResultsDeathReason());//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
			}
	
			if(obj.getBadReactionResults().indexOf("08")!=-1)
			{
			  obj.setBadReactionResultsOther(ref.getBadReactionResultsOther());//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
			}
			
			//器材操作
            String productProblemKind1="";
			if (ref.getProductProblemKind1()!=null && ref.getProductProblemKind1().length>0) 
			{
				for (int i=0; i<ref.getProductProblemKind1().length; i++) 
				{
					productProblemKind1+=ref.getProductProblemKind1()[i]+","; 
				}
			}
			if(productProblemKind1.length()>0)
			  obj.setProductProblemKind1(productProblemKind1.substring(0, productProblemKind1.length()-1));
			else
			  obj.setProductProblemKind1("");
			
			//環境設施
			String productProblemKind2="";
			if (ref.getProductProblemKind2()!=null && ref.getProductProblemKind2().length>0) 
			{
				for (int i=0; i<ref.getProductProblemKind2().length; i++) 
				{
					productProblemKind2+=ref.getProductProblemKind2()[i]+","; 
				}
			}
			if(productProblemKind2.length()>0)
			  obj.setProductProblemKind2(productProblemKind2.substring(0, productProblemKind2.length()-1));
			else
			  obj.setProductProblemKind2("");
			
			//人因
			String productProblemKind3="";
			if (ref.getProductProblemKind3()!=null && ref.getProductProblemKind3().length>0) 
			{
				for (int i=0; i<ref.getProductProblemKind3().length; i++) 
				{
					productProblemKind3+=ref.getProductProblemKind3()[i]+","; 
				}
			}
			if(productProblemKind3.length()>0)
			  obj.setProductProblemKind3(productProblemKind3.substring(0, productProblemKind3.length()-1));
			else
			  obj.setProductProblemKind3("");
			
			//物理特性
			String productProblemKind4="";
			if (ref.getProductProblemKind4()!=null && ref.getProductProblemKind4().length>0) 
			{
				for (int i=0; i<ref.getProductProblemKind4().length; i++) 
				{
					productProblemKind4+=ref.getProductProblemKind4()[i]+","; 
				}
			}
			if(productProblemKind4.length()>0)
			  obj.setProductProblemKind4(productProblemKind4.substring(0, productProblemKind4.length()-1));
			else
			  obj.setProductProblemKind4("");
			
			//其他(請敘述)
			obj.setProductProblemKindOther(ref.getProductProblemKindOther());
			
			//不良反應頁籤-其他相關資料
			obj.setOtherRelatedData(ref.getOtherRelatedData());
		
			obj.setDefProductOtherDescription(ref.getDefProductOtherDescription());
			
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
			
				  Med0001Db master01 = new Med0001Db();
				  master01.setId(Common.getLong(ref.getId()));
				  obj2.setMed0001Db(master01);
				
				  if (isUpdate) 
				  {	
					  updateListMed0002Db.add(obj2);
				  }
				  else 
				  {
					  saveListMed0002Db.add(obj2);
				  }
				
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
		
		     
		     obj.setModifier(ref.getNotifierUserID());
			 obj.setModifyDate(Datetime.getYYYMMDD());
			 obj.setModifyTime(Datetime.getHHMMSS());
		     
	
	
			 update(obj);
			 
		}
		
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
