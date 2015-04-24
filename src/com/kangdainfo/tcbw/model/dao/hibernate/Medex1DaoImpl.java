package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.bo.Med0003Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0005Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;

import com.kangdainfo.tcbw.model.dao.Medex1Dao;
import com.kangdainfo.tcbw.util.TCBWCommon;


import com.kangdainfo.tcbw.view.medex.MEDEX0104F;
import com.kangdainfo.tcbw.view.medex.MEDEX0201F;


public class Medex1DaoImpl extends BaseDaoImpl implements Medex1Dao
{

	//批次上傳
	@Override 
	public void updateSendByMEDEX0201F(MEDEX0201F ref) throws Exception 
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
				java.util.List objList = load(" from Med4001Db where id in (" + sb.toString() + ")");
				
				if(objList!=null && objList.size()>0)
				{
					java.util.List<Med4001Db> uList = new java.util.ArrayList<Med4001Db>();
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					for(Object dtlObj : objList)
					{
						Med4001Db dtl = (Med4001Db)dtlObj;
						if("01".equals(Common.get(dtl.getStatus())))
						{
							//接獲通報日期=系統日期
							dtl.setNotifierRevDate(yyymmdd);
				
							dtl.setModifier(ref.getUserID());
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							//如果[許可證字號]有值，則[案件編號]由系統給號。
							if("".equals(Common.get(dtl.getApplNo()))&&
								   !"".equals(dtl.getMedPermit()) &&
								   !"".equals(dtl.getMedPermitNumber())	)
							{
			                    String applNo="",applNo1="";
								
			                    String splitString = dtl.getEventKind();
			          		    
			                    String[] names = null;
			          		  
			          		    if(dtl.getEventKind()!=null)
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
										else if("2".equals(names[i]))
										{
										   //不良品
										   applNo1=TCBWCommon.getApplNo("MED02","R02",Datetime.getYYY());
										}
									}
								}
								
								dtl.setStatus("20");
								
								if(!"".equals(applNo))
								{
								   dtl.setApplNo(applNo);//不良反應案號
								   dtl.setApplNo1(applNo1);//對方案號
								   dtl.setRevision("01");
								   dtl.setEventKind("1");//一份案件的[不良事件類別]＝不良反應
								   updateDoCopyMed0001Db(dtl);
								   updateDoCopyMed4001Db(dtl,"");
								   sendMail(dtl.getNotifierEamil(),Common.get(applNo));
								}
								
								if(!"".equals(applNo1))
								{
								   dtl.setApplNo(applNo1);//不良品案號
								   dtl.setApplNo1(applNo);//對方案號
								   dtl.setRevision("01");
								   dtl.setEventKind("2");//一份案件的[不良事件類別]＝不良品
								   updateDoCopyMed0001Db(dtl);
								   updateDoCopyMed4001Db(dtl,"");
								   sendMail(dtl.getNotifierEamil(),Common.get(applNo1));
								}
								Med4001Db med4001Db=(Med4001Db)View.getObject("from Med4001Db where id="+Common.getLong(dtl.getId()));
								if(med4001Db!=null)
							    {
							    	delete(med4001Db);
							    }	
								
								Med4001Db a=(Med4001Db)View.getObject("from Med4001Db where applNo="+Common.sqlChar(applNo));
								if(a!=null)
							    {
									Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED01' and formID="+dtl.getId());
									con2001Db.setFormID(a.getMed0001ID());
									update(con2001Db);
							    }	
								
								Med4001Db b=(Med4001Db)View.getObject("from Med4001Db where applNo="+Common.sqlChar(applNo1));
								if(b!=null)
							    {
									Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED02' and formID="+dtl.getId());
									con2001Db.setFormID(b.getMed0001ID());
									update(con2001Db);
							    }	
			
								
								
							}
							else
							{	 
							   dtl.setStatus("10");
							   outDoCopyMed0001Db(dtl);		   
							   uList.add(dtl);
							}
						}
						else
						{
							logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材不良事件通報-待上傳作業]-[案件狀態不為待上傳，不更新資料，MED5001_DB.ID : " + dtl.getId() + "]");
						}
					}
					if(uList.size() > 0)
					{
						updateBatch(uList);
						uList.clear();
					}
				}
				else
				{
					logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材不良事件通報-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}
		else
		{
			logger.info("[TCBW]-[MedexDaoImpl]-[醫療器材不良事件通報-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
		
	}
	
	@Override
	public void updateByMedEX0104F(MEDEX0104F ref) throws Exception 
	{

		java.util.List<Med4002Db> saveListMed4002Db = new java.util.ArrayList<Med4002Db>();	
		java.util.List<Med4002Db> updateListMed4002Db = new java.util.ArrayList<Med4002Db>();
		java.util.List<Med4002Db> deleteListMed4002Db = new java.util.ArrayList<Med4002Db>();
		java.util.List<Med4002Db> med4002DbList =null ;	
		java.util.Map<String,Med4002Db> med4002DbMap=null;
		
		java.util.List<Med4003Db> saveListMed4003Db = new java.util.ArrayList<Med4003Db>();	
		java.util.List<Med4003Db> updateListMed4003Db = new java.util.ArrayList<Med4003Db>();
		java.util.List<Med4003Db> deleteListMed4003Db = new java.util.ArrayList<Med4003Db>();
		java.util.List<Med4003Db> med4003DbList =null ;	
		java.util.Map<String,Med4003Db> med4003DbMap=null;
		
		java.util.List<Med4004Db> saveListMed4004Db = new java.util.ArrayList<Med4004Db>();	
		java.util.List<Med4004Db> updateListMed4004Db = new java.util.ArrayList<Med4004Db>();
		java.util.List<Med4004Db> deleteListMed4004Db = new java.util.ArrayList<Med4004Db>();
		java.util.List<Med4004Db> med4004DbList =null ;	
		java.util.Map<String,Med4004Db> med4004DbMap=null;
		
		java.util.List<Med4005Db> saveListMed4005Db = new java.util.ArrayList<Med4005Db>();	
		java.util.List<Med4005Db> updateListMed4005Db = new java.util.ArrayList<Med4005Db>();
		java.util.List<Med4005Db> deleteListMed4005Db = new java.util.ArrayList<Med4005Db>();
		java.util.List<Med4005Db> med4005DbList =null ;	
		java.util.Map<String,Med4005Db> med4005DbMap=null;
		
		Med4001Db obj = (Med4001Db)View.getObject(" from Med4001Db where id = " + Common.getLong(ref.getId()));
		
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
				
				obj.setNotifierFirmDept(ref.getNotifierFirmDept());//通報者屬性-服務機構
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
			obj.setDefProductOtherDescription(ref.getDefProductOtherDescription());//其他資料
			obj.setOtherRelatedData(ref.getOtherRelatedData());					//其他相關資料
			
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
			
		    if(ref.getMed4002DbType()!=null)
		    {	
			   med4002DbList = ServiceGetter.getInstance().getCommonService().load("from Med4002Db where med4001Db.id="+Common.getLong(ref.getId()));	
			   med4002DbMap= new java.util.HashMap<String,Med4002Db>();
			   for(Med4002Db obj1:med4002DbList)
			   {
				  med4002DbMap.put(Common.get(obj1.getId()),obj1);			
			   }
			   
			   boolean isUpdate = true;
			
			   for(int i=0;i<ref.getMed4002DbType().length;i++)
			   {
				  String oldId = Common.get(ref.getMed4002DbTypeId()[i]);
				  Med4002Db obj2 = med4002DbMap.get(oldId);
				  if (obj2==null) 
				  {
					isUpdate = false;
					obj2 = new Med4002Db();				
				  }		
				  obj2.setOccurDate(ref.getBulletinDate()[i]);//日期	
				  obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
				  obj2.setSymptom(ref.getSymptom()[i]);//症狀
				  obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
				  obj2.setDealWith(ref.getDealWith()[i]);//處置
			
				  Med4001Db master01 = new Med4001Db();
				  master01.setId(Common.getLong(ref.getId()));
				  obj2.setMed4001Db(master01);
				
				  if (isUpdate) 
				  {	
					  updateListMed4002Db.add(obj2);
				  }
				  else 
				  {
					  saveListMed4002Db.add(obj2);
				  }
				
			    }	
			
			    for(Med4002Db objdelete:med4002DbList)
			    {
				   boolean isdelete = true;
				   for(int i=0;i<ref.getMed4002DbType().length;i++)
				   {
		    		  if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed4002DbTypeId()[i])))
		    		  {
		    			isdelete = false;
		    			break;
		    		  }
				   }
				
				  if(isdelete)
				  {
					deleteListMed4002Db.add(objdelete);
				  }
				
			     }
			
		    }  
		    else 
	        {
			   java.util.List<Med4002Db> deleteList = new java.util.ArrayList<Med4002Db>();
			   java.util.List<Med4002Db> Med4002DbList = ServiceGetter.getInstance().getCommonService().load("from Med4002Db where med4001Db.id="+Common.get(ref.getId()));
			   for(Med4002Db obj0:Med4002DbList)
			   {
				 deleteList.add(obj0);
			   }	
			
			   saveOrUpdateMed4002Db(null, null, deleteList);  
	        }
		
		
		
		    if(ref.getMed4003DbType()!=null)
		    {	
			  med4003DbList = ServiceGetter.getInstance().getCommonService().load("from Med4003Db where med4001Db.id="+Common.getLong(ref.getId()));	
			  med4003DbMap= new java.util.HashMap<String,Med4003Db>();
			  for(Med4003Db obj1:med4003DbList)
			  {
				med4003DbMap.put(Common.get(obj1.getId()),obj1);			
			  }    
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed4003DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed4003DbTypeId()[i]);
				Med4003Db obj2 = med4003DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med4003Db();				
				}		
				obj2.setTestDate(ref.getTestDate()[i]);
				obj2.setTestItems(ref.getTestItems()[i]);
				obj2.setTestNum(ref.getTestNum()[i]);
				
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
				
				if (isUpdate) {	updateListMed4003Db.add(obj2);}
				else {saveListMed4003Db.add(obj2);}
				
			   }	
			
			   for(Med4003Db objdelete:med4003DbList)
			   {
				  boolean isdelete = true;
				
				  for(int i=0;i<ref.getMed4003DbType().length;i++)
				  {
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed4003DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				  }
				
				  if(isdelete)
				  {
					  deleteListMed4003Db.add(objdelete);
				  }
				
			    }
		    } 
		    else 
	        {
			  java.util.List<Med4003Db> deleteList = new java.util.ArrayList<Med4003Db>();
			  java.util.List<Med4003Db> Med4003DbList = ServiceGetter.getInstance().getCommonService().load("from Med4003Db where med4001Db.id="+Common.get(ref.getId()));
			  for(Med4003Db obj0:Med4003DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed4003Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed4004DbType()!=null)
		    {	
			  med4004DbList = ServiceGetter.getInstance().getCommonService().load("from Med4004Db where med4001Db.id="+Common.getLong(ref.getId()));	
			  med4004DbMap= new java.util.HashMap<String,Med4004Db>();
			  for(Med4004Db obj1:med4004DbList)
			  {
				med4004DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			  boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed4004DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed4004DbTypeId()[i]);
				Med4004Db obj2 = med4004DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med4004Db();				
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
			
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
				
				if (isUpdate) {	updateListMed4004Db.add(obj2);}
				else {saveListMed4004Db.add(obj2);}
				
			  }	
			
			  for(Med4004Db objdelete:med4004DbList)
			  {
				boolean isdelete = true;
				
				for(int i=0;i<ref.getMed4004DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed4004DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed4004Db.add(objdelete);
				}
				
			   }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med4004Db> deleteList = new java.util.ArrayList<Med4004Db>();
			  java.util.List<Med4004Db> Med4004DbList = ServiceGetter.getInstance().getCommonService().load("from Med4004Db where med4001Db.id="+Common.get(ref.getId()));
			  for(Med4004Db obj0:Med4004DbList)
			  {
				deleteList.add(obj0);
			  }	
			  saveOrUpdateMed4004Db(null, null, deleteList);  
	        }
		
		    if(ref.getMed4005DbType()!=null)
		    {	
			  med4005DbList = ServiceGetter.getInstance().getCommonService().load("from Med4005Db where med4001Db.id="+Common.getLong(ref.getId()));	
			  med4005DbMap= new java.util.HashMap<String,Med4005Db>();
			  for(Med4005Db obj1:med4005DbList)
			  {
				med4005DbMap.put(Common.get(obj1.getId()),obj1);			
			  }
			   boolean isUpdate = true;
			
			  for(int i=0;i<ref.getMed4005DbType().length;i++)
			  {
				String oldId = Common.get(ref.getMed4005DbTypeId()[i]);
				Med4005Db obj2 = med4005DbMap.get(oldId);
				if (obj2==null) 
				{
					isUpdate = false;
					obj2 = new Med4005Db();				
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
			
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
				
				if (isUpdate) {	updateListMed4005Db.add(obj2);}
				else {saveListMed4005Db.add(obj2);}
				
			  }	
			
			  for(Med4005Db objdelete:med4005DbList)
			  {
				boolean isdelete = true;
				for(int i=0;i<ref.getMed4005DbType().length;i++)
				{
		    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed4005DbTypeId()[i])))
		    		{
		    			isdelete = false;
		    			break;
		    		}
				}
				
				if(isdelete)
				{
					deleteListMed4005Db.add(objdelete);
				}
				
			  }
			
		    }  
		    else 
	        {
			  //若沒有明細代表全部刪除
			  java.util.List<Med4005Db> deleteList = new java.util.ArrayList<Med4005Db>();
			  java.util.List<Med4005Db> Med4005DbList = ServiceGetter.getInstance().getCommonService().load("from Med4005Db where med4001Db.id="+Common.get(ref.getId()));
			  for(Med4005Db obj0:Med4005DbList)
			  {
				deleteList.add(obj0);
			  }	
			
			  saveOrUpdateMed4005Db(null, null, deleteList);  
	         }
		
		     saveOrUpdateMed4002Db(saveListMed4002Db, updateListMed4002Db, deleteListMed4002Db);    		
		     saveOrUpdateMed4003Db(saveListMed4003Db, updateListMed4003Db, deleteListMed4003Db);
		     saveOrUpdateMed4004Db(saveListMed4004Db, updateListMed4004Db, deleteListMed4004Db);
		     saveOrUpdateMed4005Db(saveListMed4005Db, updateListMed4005Db, deleteListMed4005Db);
		
		     
		     obj.setModifier(ref.getNotifierUserID());
			 obj.setModifyDate(Datetime.getYYYMMDD());
			 obj.setModifyTime(Datetime.getHHMMSS());
		     
			 
			 if("2".equals(ref.getUpdateType()) || "3".equals(ref.getUpdateType()))
			 {
				 
				 String hql4002Db=" from Med4002Db where med4001Db.id="+Common.get(ref.getId());
				        hql4002Db+=" and occurDate='' ";
				 
				 java.util.List<Med4002Db> med4002DbContainer = new java.util.ArrayList<Med4002Db>();
				 java.util.List<Med4002Db> Med4002DbList = ServiceGetter.getInstance().getCommonService().load(hql4002Db);
			
				 for(Med4002Db obj0:Med4002DbList)
				 {
					 med4002DbContainer.add(obj0);
				 }	
				 
				 saveOrUpdateMed4002Db(null, null, med4002DbContainer);
			 }
			 
			 
			 //接收前端按鈕塞入狀態，按下待上傳鈕，案件狀態=待上傳
			 if("2".equals(ref.getUpdateType()))
			 {
				if("00".equals(obj.getStatus()))
				{
					 obj.setStatus("01");
				}
				update(obj);
				//記錄一筆案件流程紀錄
				if("1".equals(obj.getEventKind()))
				{
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-待上傳", obj.getInOrOutcreator());
				}
				else if("2".equals(obj.getEventKind()))
				{
					 ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-待上傳", obj.getInOrOutcreator());
				}
				else
				{
					 ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-待上傳", obj.getInOrOutcreator());
					 ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",obj.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-待上傳", obj.getInOrOutcreator());
				}	  
			 }
			 else if("3".equals(ref.getUpdateType()))
			 {
				obj.setNotifierRevDate(Datetime.getYYYMMDD());//通報中心接獲日期
				
				update(obj);
				
				//如果[許可證字號]有值，則[案件編號]由系統給號。
				if("".equals(Common.get(obj.getApplNo()))&&
					   !"".equals(ref.getMedPermit()) &&
					   !"".equals(ref.getMedPermitNumber())	)
				{	
					updateDoCopy(ref,obj);
				}
				else
				{
					//如果[許可證字號]無值
					//[案件狀態]＝案件審核中(一般)。
					if("02".equals(obj.getStatus()))
					{	
					  obj.setStatus("11");
					  Med0001Db med0001Db=(Med0001Db)View.getObject("from Med0001Db where id="+obj.getMed0001ID());
					  
					  if(med0001Db!=null)
					  {
						  med0001Db.setStatus("11");
						  update(med0001Db);
						  
						  if("1".equals(obj.getEventKind()))
						  {
							  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
						  }
						  else if("2".equals(obj.getEventKind()))
						  {
							  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
						  }
						  else
						  {
							  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
							  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
						  }	  
					  }
				
					  
					
					  
					  
					}  
					else if("00".equals(obj.getStatus()))
					{
					  obj.setStatus("10");
					  updateDoCopyMed0001Db(obj);
					}

					update(obj);

					
				}
				
				
			 }
			 else
			 {
				if("00".equals(obj.getStatus()) || "01".equals(obj.getStatus()))
				{	
					obj.setStatus("00");
				}
				update(obj);
			 }
		}
		
	}

	//有許可證號送出後自動給號
	public void updateDoCopy(MEDEX0104F ref,Med4001Db obj) throws Exception
	{
		String applNo="",applNo1="";
		
		if (ref.getEventKind()!=null && ref.getEventKind().length>0) 
		{
			for (int i=0; i<ref.getEventKind().length; i++) 
			{
				//不良反應
				if("1".equals(ref.getEventKind()[i])) 
				{
				   applNo=TCBWCommon.getApplNo("MED01","A01",Datetime.getYYY());
				}
				else
				{
				   //不良品
				   applNo1=TCBWCommon.getApplNo("MED02","R02",Datetime.getYYY());
				}
			}
		}
		
		obj.setStatus("20");

		if(!"".equals(applNo) && !"".equals(applNo1))
		{
		   if(!"".equals(applNo))
		   {
		     obj.setApplNo(applNo);//不良反應案號
		     obj.setApplNo1(applNo1);//對方案號
		     obj.setRevision("01");
		     obj.setEventKind("1");//一份案件的[不良事件類別]＝不良反應	
		     updateDoCopyFileMed0001Db(obj,"N");
			 updateDoCopyFileMed4001Db(obj,"N");	
			 sendMail(ref.getNotifierEamil(),Common.get(applNo));
		   }
		
		   if(!"".equals(applNo1))
		   {
		     obj.setApplNo(applNo1);//不良品案號
		     obj.setApplNo1(applNo);//對方案號
		     obj.setRevision("01");
		     obj.setEventKind("2");//一份案件的[不良事件類別]＝不良品
		     updateDoCopyFileMed0001Db(obj,"N");
		     updateDoCopyFileMed4001Db(obj,"N");  
		     sendMail(ref.getNotifierEamil(),Common.get(applNo1));
		   }

		}
		else
		{
		  if(!"".equals(applNo))
		  {
			 obj.setApplNo(applNo);//不良反應案號
			 obj.setApplNo1(applNo1);//對方案號
			 obj.setRevision("01");
			 obj.setEventKind("1");//一份案件的[不良事件類別]＝不良反應	
			 updateDoCopyMed0001Db(obj);
			 sendMail(ref.getNotifierEamil(),Common.get(applNo));
		  }
			
		  if(!"".equals(applNo1))
		  {
			 obj.setApplNo(applNo1);//不良品案號
			 obj.setApplNo1(applNo);//對方案號
			 obj.setRevision("01");
			 obj.setEventKind("2");//一份案件的[不良事件類別]＝不良品
			 updateDoCopyMed0001Db(obj);
			 sendMail(ref.getNotifierEamil(),Common.get(applNo1));
		  }
			
		}	
 

	    if(!"".equals(applNo) && !"".equals(applNo1))
		{
			 Med4001Db med4001Db=(Med4001Db)View.getObject("from Med4001Db where id="+Common.getLong(ref.getId()));
			 
			 if(med4001Db!=null)
		     {
		    	  Con0001Db con0001Db=(Con0001Db)View.getObject("from Con0001Db where dbName='Med4001Db' and upLoadId="+Common.getLong(ref.getId()));
		    	  if(con0001Db!=null)
				  {
		    	     delete(con0001Db);
				  }
		    	  delete(med4001Db);
		     }
		} 
		
	}
	
	//送出時有許可證號自動給案號，並寄信
	public void sendMail(String NotifierEamil,String applNo) throws Exception
	{
		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED010001'");	
		
		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		
		String mailBody="",title="",mail=NotifierEamil;
		
		if(c!=null)
		{
			mailBody=c.getMailBody();
			
			title=c.getTitle();
			
			Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where applNo = " + Common.sqlChar(applNo));
			
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
				ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,kind,med0001Db.getApplNo());
				TCBWCommon.setMailbackup(kind,Common.get(med0001Db.getId()),title,mailBody,med0001Db.getApplNo(),"20",med0001Db.getWorkers(),"");	
			}	
		}	
	}
	
	
	public void updateDoCopyMed4001Db(Med4001Db obj,String auto) throws Exception
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
	
	//有許可證號，內網con001db拷貝兩筆
	public void updateDoCopyFileMed0001Db(Med4001Db obj,String isInsert) throws Exception
	{
		if(obj != null)
		{
			Med0001Db master = new Med0001Db(); 

			String[] ignoreFields = new String[]{"id", "med0002Dbs", "med0003Dbs", "med0004Dbs", "med0005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med0002Dbs = new ListOrderedSet();
			
			if(obj.getMed4002Dbs()!=null && obj.getMed4002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4002Dbs())
				{
					Med4002Db dtl = (Med4002Db)dtlObj;
					Med0002Db tmpDtl = new Med0002Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0003Dbs = new ListOrderedSet();
			if(obj.getMed4003Dbs()!=null && obj.getMed4003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4003Dbs())
				{
					Med4003Db dtl = (Med4003Db)dtlObj;
					Med0003Db tmpDtl = new Med0003Db();
			
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0004Dbs = new ListOrderedSet();
			if(obj.getMed4004Dbs()!=null && obj.getMed4004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4004Dbs())
				{
					Med4004Db dtl = (Med4004Db)dtlObj;
					Med0004Db tmpDtl = new Med0004Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0005Dbs = new ListOrderedSet();
			if(obj.getMed4005Dbs()!=null && obj.getMed4005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4005Dbs())
				{
					Med4005Db dtl = (Med4005Db)dtlObj;
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
		
			if("1".equals(obj.getEventKind()))
			{
			  
			  String autoUser="";
			  
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  
			  if("".equals(Common.get(user)))
			  {
			    //依據案件審核自動分派原則設定人員
			    autoUser=TCBWCommon.getUserID("MED01","01","Med0001Db");
			  }
			  
			  master.setWorkers(autoUser);  
			}
			
			if("2".equals(obj.getEventKind()))
			{
				
			  String autoUser="";
				
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  if("".equals(Common.get(user)))
			  {
			     //依據案件審核自動分派原則設定人員
			     autoUser=TCBWCommon.getUserID("MED02","01","Med0001Db");
			  }
			  master.setWorkers(autoUser);  
			}
			
			save(master);
			obj.setMed0001ID(master.getId());
			update(obj);
			
			//內網檔案上傳
			doCopyFileCon0001Db(obj,master,isInsert);
			
			if("1".equals(obj.getEventKind()))
			{	
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			}
			else if("2".equals(obj.getEventKind()))
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			}	
			
		}
	}
	
	//內網檔案上傳
	public void doCopyFileCon0001Db(Med4001Db obj, Med0001Db med01,String isInsert) throws Exception
	{
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db'  and upLoadId="+obj.getId());
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med0001Db");
				newcon.setIsInsert(isInsert);
				newcon.setUpLoadId(med01.getId());
				ServiceGetter.getInstance().getCommonService().save(newcon);
			}
		}
	}
	
	
	//有許可證號送出時，外網將con0001Db拷貝兩筆
	public void updateDoCopyFileMed4001Db(Med4001Db obj,String isInsert) throws Exception
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
			
			//外網檔案上傳
			doCopyCon0001Db(obj,master,isInsert);
			
		}
	}
	
	//外網檔案上傳
	public void doCopyCon0001Db(Med4001Db obj, Med4001Db med01,String isInsert) throws Exception
	{
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db'  and upLoadId="+obj.getId());
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med4001Db");
				newcon.setIsInsert(isInsert);
				newcon.setUpLoadId(med01.getId());
				ServiceGetter.getInstance().getCommonService().save(newcon);
			}
		}
	}
	
	//外網待上傳 --> 送出
	public void outDoCopyMed0001Db(Med4001Db obj) throws Exception
	{
		if(obj != null)
		{
			Med0001Db master = new Med0001Db(); 

			String[] ignoreFields = new String[]{"id", "med0002Dbs", "med0003Dbs", "med0004Dbs", "med0005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med0002Dbs = new ListOrderedSet();
			
			if(obj.getMed4002Dbs()!=null && obj.getMed4002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4002Dbs())
				{
					Med4002Db dtl = (Med4002Db)dtlObj;
					Med0002Db tmpDtl = new Med0002Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0003Dbs = new ListOrderedSet();
			if(obj.getMed4003Dbs()!=null && obj.getMed4003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4003Dbs())
				{
					Med4003Db dtl = (Med4003Db)dtlObj;
					Med0003Db tmpDtl = new Med0003Db();
			
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0004Dbs = new ListOrderedSet();
			if(obj.getMed4004Dbs()!=null && obj.getMed4004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4004Dbs())
				{
					Med4004Db dtl = (Med4004Db)dtlObj;
					Med0004Db tmpDtl = new Med0004Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0005Dbs = new ListOrderedSet();
			if(obj.getMed4005Dbs()!=null && obj.getMed4005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4005Dbs())
				{
					Med4005Db dtl = (Med4005Db)dtlObj;
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
		
			if("1".equals(obj.getEventKind()))
			{
			  
			  String autoUser="";
			  
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  
			  if("".equals(Common.get(user)))
			  {
			    //依據案件審核自動分派原則設定人員
			    autoUser=TCBWCommon.getUserID("MED01","01","Med0001Db");
			  }
			  
			  master.setWorkers(autoUser);  
			}
			
			if("2".equals(obj.getEventKind()))
			{
				
			  String autoUser="";
				
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  if("".equals(Common.get(user)))
			  {
			     //依據案件審核自動分派原則設定人員
			     autoUser=TCBWCommon.getUserID("MED02","01","Med0001Db");
			  }
			  master.setWorkers(autoUser);  
			}
			
			save(master);
			obj.setMed0001ID(master.getId());
			update(obj);
			
			if("1".equals(obj.getEventKind()))
			{	
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			 
			  Med4001Db a=(Med4001Db)View.getObject("from Med4001Db where med0001ID="+master.getId());
			  if(a!=null)
			  {
				Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED01' and formID="+obj.getId());
				con2001Db.setFormID(a.getMed0001ID());
				update(con2001Db);
			  }	
			}
			else if("2".equals(obj.getEventKind()))
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			  
			  Med4001Db b=(Med4001Db)View.getObject("from Med4001Db where med0001ID="+master.getId());
			  if(b!=null)
			  {
				Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED02' and formID="+obj.getId());
				con2001Db.setFormID(b.getMed0001ID());
				update(con2001Db);
			  }	
			}	
			else
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			  
			  Med4001Db a=(Med4001Db)View.getObject("from Med4001Db where med0001ID="+master.getId());
			  if(a!=null)
			  {
				Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED01' and formID="+obj.getId());
				con2001Db.setFormID(a.getMed0001ID());
				update(con2001Db);
			  }	
			  Med4001Db b=(Med4001Db)View.getObject("from Med4001Db where med0001ID="+master.getId());
			  if(b!=null)
			  {
				Con2001Db con2001Db=(Con2001Db)View.getObject("from Con2001Db where systemType='MED02' and formID="+obj.getId());
				con2001Db.setFormID(b.getMed0001ID());
				update(con2001Db);
			  }	
			
			}	
			
			doCopyCon0001Db(obj,master,"1");
		}
	}
	
	
	
	public void updateDoCopyMed0001Db(Med4001Db obj) throws Exception
	{
		if(obj != null)
		{
			Med0001Db master = new Med0001Db(); 

			String[] ignoreFields = new String[]{"id", "med0002Dbs", "med0003Dbs", "med0004Dbs", "med0005Dbs"};
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			
			java.util.Set med0002Dbs = new ListOrderedSet();
			
			if(obj.getMed4002Dbs()!=null && obj.getMed4002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4002Dbs())
				{
					Med4002Db dtl = (Med4002Db)dtlObj;
					Med0002Db tmpDtl = new Med0002Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0002Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0003Dbs = new ListOrderedSet();
			if(obj.getMed4003Dbs()!=null && obj.getMed4003Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4003Dbs())
				{
					Med4003Db dtl = (Med4003Db)dtlObj;
					Med0003Db tmpDtl = new Med0003Db();
			
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0003Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0004Dbs = new ListOrderedSet();
			if(obj.getMed4004Dbs()!=null && obj.getMed4004Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4004Dbs())
				{
					Med4004Db dtl = (Med4004Db)dtlObj;
					Med0004Db tmpDtl = new Med0004Db(); 
					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setMed0001Db(master);
					med0004Dbs.add(tmpDtl);
				}
			}
			
			java.util.Set med0005Dbs = new ListOrderedSet();
			if(obj.getMed4005Dbs()!=null && obj.getMed4005Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getMed4005Dbs())
				{
					Med4005Db dtl = (Med4005Db)dtlObj;
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
		
			if("1".equals(obj.getEventKind()))
			{
			  
			  String autoUser="";
			  
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  
			  if("".equals(Common.get(user)))
			  {
			    //依據案件審核自動分派原則設定人員
			    autoUser=TCBWCommon.getUserID("MED01","01","Med0001Db");
			  }
			  
			  master.setWorkers(autoUser);  
			}
			
			if("2".equals(obj.getEventKind()))
			{
				
			  String autoUser="";
				
			  //查詢是否有相同的許可證號，若有將案件分派給該人員
			  String user=View.getLookupField("select workers from Med0001Db where medPermit="+Common.sqlChar(obj.getMedPermit())+" and medPermitNumber="+Common.sqlChar(obj.getMedPermitNumber()) );
			  if("".equals(Common.get(user)))
			  {
			     //依據案件審核自動分派原則設定人員
			     autoUser=TCBWCommon.getUserID("MED02","01","Med0001Db");
			  }
			  master.setWorkers(autoUser);  
			}
			
			save(master);
			obj.setMed0001ID(master.getId());
			update(obj);
			
			if("1".equals(obj.getEventKind()))
			{	
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			}
			else if("2".equals(obj.getEventKind()))
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			}	
			else
			{
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",master.getId(),obj.getApplNo(),obj.getStatus(),"案件通報者-送出", obj.getInOrOutcreator());
			}	
			
			doCopyCon0001Db(obj,master,"1");
		}
	}
	
	

	//檔案上傳回內網
	public void doCopyCon0001Db(Med4001Db obj, Med0001Db med01 ,String type) throws Exception
	{

		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db' and isInsert='Y' and upLoadId="+obj.getId());
		
		if(null != con01List && !con01List.isEmpty())
		{
			for(Con0001Db oldcon : con01List)
			{
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Med0001Db");
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
	public void updateByAutoReUpdateMedEX0104F(MEDEX0104F ref,String auto) throws Exception 
	{
        String yyymmdd = Datetime.getYYYMMDD();
		
		java.util.List<Med4002Db> saveListMed4002Db = new java.util.ArrayList<Med4002Db>();	
		java.util.List<Med4003Db> saveListMed4003Db = new java.util.ArrayList<Med4003Db>();	
		java.util.List<Med4004Db> saveListMed4004Db = new java.util.ArrayList<Med4004Db>();		
		java.util.List<Med4005Db> saveListMed4005Db = new java.util.ArrayList<Med4005Db>();	

		
		   Med4001Db med4001Db = (Med4001Db)View.getObject(" from Med4001Db where id = " + Common.getLong(ref.getId()));
		

		   //撰寫
		   Med4001Db obj = new Med4001Db();
	
	
		   int revision=0;
		   if(!"".equals(Common.get(med4001Db.getRevision())))
		   {
		      revision=Integer.parseInt(med4001Db.getRevision());
		      revision++;
		   }
	
		    obj.setRevision(Common.formatFrontZero(String.valueOf(revision),2));
			
		    obj.setApplNo(med4001Db.getApplNo());	
		    obj.setApplNo1(med4001Db.getApplNo1());
			obj.setOccurDate(ref.getOccurDate());								//通報日期
			obj.setNotifierRevDate(ref.getNotifierRevDate());					//通報中心接獲日期
			obj.setNotifierRepDate(ref.getNotifierRepDate());					//通報者獲知日期
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
			obj.setNotifierName(ref.getNotifierName());							//姓名
			obj.setNotifierAreaCode(ref.getNotifierAreaCode());					//電話區碼
			obj.setNotifierTel(ref.getNotifierTel());							//電話
			obj.setNotifierTelExt(ref.getNotifierTelExt());						//電話
			obj.setNotifierEamil(ref.getNotifierEamil());						//E-mail
			obj.setNotifierAddress(ref.getNotifierAddress());					//地址
			obj.setNotifierType(ref.getNotifierType());							//通報者屬性
			
			if("1".equals(ref.getNotifierType())) {
				obj.setNotifierStaffHospital(ref.getNotifierStaffHospital());	//通報者屬性-醫院名稱
				obj.setNotifierStaffTitle(ref.getNotifierStaffTitle());			//通報者屬性-醫療人員-職稱
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
			obj.setMedPermitFirmCode(ref.getMedPermitFirmCode());				//許可證申請商
			obj.setMedPermitFirm(ref.getMedPermitFirm());						//許可證申請商
			obj.setMedCname(ref.getMedCname());									//中文品名
			obj.setMedMainCategoryCode(ref.getMedMainCategory());			//醫材主類別
			obj.setMedMainCategory(ref.getMedMainCategoryCodeName());					//醫材主類別
			obj.setMedSecCategoryCode(ref.getMedSecCategory());				//醫材次類別
			obj.setMedSecCategory(ref.getMedSecCategoryCodeName());						//醫材次類別
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
			obj.setDefProductOtherDescription(ref.getDefProductOtherDescription());//其他資料
			obj.setOtherRelatedData(ref.getOtherRelatedData());					//其他相關資料
			
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
			
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(Datetime.getYYYMMDD());
			obj.setCreateTime(Datetime.getHHMMSS());
			obj.setStatus(med4001Db.getStatus());
            obj.setMed0001ID(Common.getLong(med4001Db.getMed0001ID()));
			obj.setInOrOutcreator(med4001Db.getInOrOutcreator());
            
			save(obj);
			
			ref.setId(Common.get(obj.getId()));
			
		    if(ref.getMed4002DbType()!=null)
		    {	
			   for(int i=0;i<ref.getMed4002DbType().length;i++)
			   {
				  Med4002Db obj2=new Med4002Db();				  
				  Med4001Db master01 = new Med4001Db();
				  master01.setId(Common.getLong(obj.getId()));
				  obj2.setMed4001Db(master01);
				  
				  obj2.setOccurDate(ref.getBulletinDate()[i]);//日期	
				  obj2.setPosition(ref.getPosition()[i]);//發生不良反應之部位	
				  obj2.setSymptom(ref.getSymptom()[i]);//症狀
				  obj2.setSeverity(ref.getSeverity()[i]);//嚴重程度
				  obj2.setDealWith(ref.getDealWith()[i]);//處置
                  saveListMed4002Db.add(obj2);
			    }	
		    }  

		
		
		    if(ref.getMed4003DbType()!=null)
		    {	
			  for(int i=0;i<ref.getMed4003DbType().length;i++)
			  {
			
				Med4003Db obj2 = new Med4003Db();				
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
				obj2.setTestDate(ref.getTestDate()[i]);
				obj2.setTestItems(ref.getTestItems()[i]);
				obj2.setTestNum(ref.getTestNum()[i]);
				saveListMed4003Db.add(obj2);	
			   }	 
		    } 
		   
		
		    if(ref.getMed4004DbType()!=null)
		    {	
			  for(int i=0;i<ref.getMed4004DbType().length;i++)
			  {
				Med4004Db obj2 = new Med4004Db();				
				
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
				
				obj2.setcName(ref.getCcname()[i]);//品名
				obj2.setPermit(ref.getPermit()[i]);//許可證字
				obj2.setPermitNumber(ref.getPermitNumber()[i]);//許可證號
				obj2.setPermitFirm(ref.getPermitFirm()[i]);//許可證申請商
				obj2.setModel(ref.getModel()[i]);//型號
				obj2.setMainCategory(ref.getMainCategory()[i]);//器材主類別
				obj2.setUseDate(ref.getUseDate()[i]);//使用日期
				obj2.setUseReason(ref.getUseReason()[i]);//使用原因
			    saveListMed4004Db.add(obj2);
			  }	

		    }  
		  
		
		    if(ref.getMed4005DbType()!=null)
		    {	

			  for(int i=0;i<ref.getMed4005DbType().length;i++)
			  {
				
				Med4005Db obj2 = new Med4005Db();	
				
				Med4001Db master01 = new Med4001Db();
				master01.setId(Common.getLong(ref.getId()));
				obj2.setMed4001Db(master01);
					
				obj2.setcName(ref.getcName2()[i]);//學名/商品名
				obj2.setContent(ref.getContent()[i]);//含量
				obj2.setFormulation(ref.getFormulation()[i]);//劑型
				obj2.setDrgApproach(ref.getDrgApproach()[i]);//給藥途徑
				obj2.setDose(ref.getDose()[i]);//劑量
				obj2.setFrequency(ref.getFrequency()[i]);//使用頻率
				obj2.setsDate(ref.getsDate()[i]);//起日期
				obj2.seteDate(ref.geteDate()[i]);//迄日期
				obj2.setMedCauses(ref.getMedCauses()[i]);//用藥原因
				
		        saveListMed4005Db.add(obj2);
			  }	
		    }  
		    

		saveOrUpdateMed4002Db(saveListMed4002Db, null, null);    		
		saveOrUpdateMed4003Db(saveListMed4003Db, null, null);
		saveOrUpdateMed4004Db(saveListMed4004Db, null, null);
		saveOrUpdateMed4005Db(saveListMed4005Db, null, null);

		if(obj!=null)
		{
		  Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where id = " + Common.getLong(obj.getMed0001ID())); 
		  med0001Db.setAutoReUpdate("Y");
		  update(med0001Db);
		  
		  //轉入附件(抓上版本的附件資料)
		  List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='MED' and systemType like 'MED010001' and dbName='Med4001Db' and upLoadId="+med4001Db.getId());
		  if(null != con01List && !con01List.isEmpty())
		  {
				for(Con0001Db oldcon : con01List)
				{
					Con0001Db newcon = new Con0001Db();
					org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
					newcon.setDbName("Med4001Db");
					newcon.setUpLoadId(obj.getId());
					newcon.setIsInsert("N");
					ServiceGetter.getInstance().getCommonService().save(newcon);
				}
		  }
			
		  //將新檔案傳回內網
		  doCopyCon0001Db(med4001Db,med0001Db,"2");
		  
		  
          String title="【主動補件】",mailBody="",mail="";

		  String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(med0001Db.getWorkers()));
		    
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
		  
		  String kind="";
			
		  if("1".equals(med0001Db.getEventKind()))
		  {
				kind="MED01";
		  }
		  else
		  {
				kind="MED02";
		  }
		  
		  ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null,kind,med0001Db.getApplNo());

		  TCBWCommon.setMailbackup(kind,Common.get(med0001Db.getId()),title, mailBody, med0001Db.getApplNo(),med0001Db.getStatus(), ref.getUserID(),"");	
		  
		  //記錄一筆案件流程紀錄
		  ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(kind,obj.getId(), med4001Db.getApplNo(),med4001Db.getStatus(),"案件通報者-主動補件", ref.getUserID());
		}
	}
	
	
	public void saveOrUpdateMed4002Db(java.util.List<Med4002Db> saveList,java.util.List<Med4002Db> updateList,java.util.List<Med4002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed4003Db(java.util.List<Med4003Db> saveList,java.util.List<Med4003Db> updateList,java.util.List<Med4003Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed4004Db(java.util.List<Med4004Db> saveList,java.util.List<Med4004Db> updateList,java.util.List<Med4004Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	public void saveOrUpdateMed4005Db(java.util.List<Med4005Db> saveList,java.util.List<Med4005Db> updateList,java.util.List<Med4005Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}

}
