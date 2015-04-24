package com.kangdainfo.tcbw.model.dao.hibernate;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8003Db;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;
import com.kangdainfo.tcbw.model.bo.Drg8005Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.dao.SdrgDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0201F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0301F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0401F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0501F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0601F;

public class SdrgDaoImpl extends BaseDaoImpl implements SdrgDao{
	
	@Override
	public void updateBySdrg0101F(SDRG0101F ref) throws Exception 
	{		
		java.util.List<Drg8002Db> saveListDrg8002Db = new java.util.ArrayList<Drg8002Db>();	
		java.util.List<Drg8002Db> updateListDrg8002Db = new java.util.ArrayList<Drg8002Db>();
		java.util.List<Drg8002Db> deleteListDrg8002Db = new java.util.ArrayList<Drg8002Db>();
		
		java.util.List<Drg8002Db> drg8002DbList =null ;	
		java.util.Map<String,Drg8002Db> drg8002DbMap=null;
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	obj.setApplNo(Common.get(ref.getApplNo()));
        	obj.setChProduct(Common.get(ref.getChProduct()));
        	obj.setEnProduct(Common.get(ref.getEnProduct())); 
        	obj.setPermitKey(Common.get(ref.getPermitKey()));
        	obj.setPermitNo(Common.get(ref.getPermitNo()));
        	obj.setIngredient(Common.get(ref.getIngredient()));
        	obj.setMedModel(Common.get(ref.getMedModel()));
        	obj.setManufactorName(Common.get(ref.getManufactorName()));
        	obj.setManufactorAddr(Common.get(ref.getManufactorAddr()));
        	obj.setManufactorCountry(Common.get(ref.getManufactorCountry()));
        	obj.setAppID(Common.get(ref.getAppID()));
        	obj.setAppName(Common.get(ref.getAppName()));
        	obj.setAppAddr(Common.get(ref.getAppAddr()));
        	obj.setOrirecyclereason(Common.get(ref.getOrirecyclereason()));
        	obj.setDanger(Common.get(ref.getDanger()));
        	obj.setPrerecycledate(Common.get(ref.getPrerecycledate()));
        	obj.setIsabroad(Common.get(ref.getIsabroad()));
        	obj.setAbroadCountry(Common.get(ref.getAbroadCountry()));
        	obj.setAbroadCountryOther(Common.get(ref.getAbroadCountryOther()));
        	obj.setDrugsupplier(Common.get(ref.getDrugsupplier()));
        	obj.setPrecaution(Common.get(ref.getPrecaution()));
        	obj.setLotnumStockcity(Common.get(ref.getLotnumStockcity()));
        	obj.setLotnumStockarea(Common.get(ref.getLotnumStockarea()));
        	obj.setLotnumStock(Common.get(ref.getLotnumStock()));
        	obj.setContactman(Common.get(ref.getContactman()));
        	obj.setContacttel(Common.get(ref.getContacttel()));
        	obj.setPostDate(Common.get(ref.getPostDate()));
        	obj.setPostNo(Common.get(ref.getPostNo()));
        	obj.setRecycleclass(Common.get(ref.getRecycleclass()));
        	obj.setMsgsource(Common.get(ref.getMsgsource()));
        	obj.setMsgsourcedesc(Common.get(ref.getMsgsourcedesc()));
        	obj.setCureapplno(Common.get(ref.getCureapplno()));
        	obj.setQuaapplno(Common.get(ref.getQuaapplno()));
        	obj.setRecycledeadline(Common.get(ref.getRecycledeadline()));			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			//回收清單明細
			if(ref.getDrg8002DbType()!=null)
			{		   
				drg8002DbList = ServiceGetter.getInstance().getCommonService().load("from Drg8002Db where drg8001Db.id="+Common.getLong(ref.getId()));			   
				drg8002DbMap= new java.util.HashMap<String,Drg8002Db>();
			   
				for(Drg8002Db obj1:drg8002DbList){				  
					drg8002DbMap.put(Common.get(obj1.getId()),obj1);			   
				}
			   
				boolean isUpdate = true;
			
			   
				for(int i=0;i<ref.getDrg8002DbType().length;i++){				  
					String oldId = Common.get(ref.getDrg8002DbTypeId()[i]);					
					Drg8002Db obj2 = drg8002DbMap.get(oldId);		
				  
					if (obj2==null){					
						isUpdate = false;				
						obj2 = new Drg8002Db();				  
					}
					
					obj2.setLotNo(ref.getLotNo()[i]);                              //批號				  
					obj2.setReservesNum(Common.getLong(ref.getReservesNum()[i]));  //案件登錄-庫存量				  
					obj2.setReservesUnit(ref.getReservesUnit()[i]);                //案件登錄-庫存量單位				  
					obj2.setSellnum(Common.getLong(ref.getSellnum()[i]));          //案件登錄-銷售數量				  
					obj2.setSellunit(ref.getSellunit()[i]);                        //案件登錄-銷售數量單位				  
					obj2.setModifier(ref.getUserID());				  
					obj2.setModifyDate(Datetime.getYYYMMDD());				  
					obj2.setModifyTime(Datetime.getHHMMSS());			  
								  
					obj2.setDrg8001Db(obj);				  
				  
					if (isUpdate) {				  
						updateListDrg8002Db.add(obj2);				  
					}				  
					else {					  
						saveListDrg8002Db.add(obj2);			  
					}			    
				}	
			
			    for(Drg8002Db objdelete:drg8002DbList){
			    	boolean isdelete = true;				   
			    	for(int i=0;i<ref.getDrg8002DbType().length;i++){		    		  
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getDrg8002DbTypeId()[i]))){		    			
			    			isdelete = false;		    			
			    			break;		    		  
			    		}				   
			    	}			   
			    	if(isdelete){					  
			    		deleteListDrg8002Db.add(objdelete);				   
			    	}			     
			    }			
		    }  
		    else{
			   java.util.List<Drg8002Db> deleteList = new java.util.ArrayList<Drg8002Db>();
			   java.util.List<Drg8002Db> Drg8002DbList = ServiceGetter.getInstance().getCommonService().load("from Drg8002Db where drg8001Db.id="+Common.get(ref.getId()));
			   for(Drg8002Db obj0:Drg8002DbList)
			   {
				 deleteList.add(obj0);
			   }	
			
			   saveOrUpdateDrg8002Db(null, null, deleteList);  
	        }
			
			//送出審核
			if("1".equals(ref.getUpdateType()) && "00".equals(ref.getStatus()))  
			{
				obj.setStatus("10");
				//系統給號		
	        	if("".equals(Common.get(obj.getApplNo())))		
	        	{		  
	        		String no=TCBWCommon.getApplNo("DRG04","A04",Datetime.getYYY());	
	        		if(no!=null)		  
	        		{			
	        			obj.setApplNo(no);		  
	        		}
	        		obj.setNotifierRepDate(yyymmdd);
	        	}
	        	//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"10", "案件登錄 - 送件", ref.getUserID());
			}
			
			saveOrUpdateDrg8002Db(saveListDrg8002Db, updateListDrg8002Db, deleteListDrg8002Db);
			saveOrUpdate(obj);
			
			ref.setId(Common.get(obj.getId()));			
		}	
		
	}
	
	public void saveOrUpdateDrg8002Db(java.util.List<Drg8002Db> saveList,java.util.List<Drg8002Db> updateList,java.util.List<Drg8002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	@Override
	public void updateBySdrg0201F(SDRG0201F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	obj.setPostDate(Common.get(ref.getPostDate()));	                     //發文日期
			obj.setPostNo(Common.get(ref.getPostNo()));	                         //回收文號
			obj.setRecycleclass(Common.get(ref.getRecycleclass()));	             //回收分級
			obj.setMsgsource(Common.get(ref.getMsgsource()));	                 //訊息來源
			obj.setMsgsourcedesc(Common.get(ref.getMsgsourcedesc()));	         //訊息來源說明
			obj.setCureapplno(Common.get(ref.getCureapplno()));	                 //通報編號
			obj.setQuaapplno(Common.get(ref.getQuaapplno()));	                 //警訊編號
			obj.setRecycledeadline(Common.get(ref.getRecycledeadline()));	     //回收期限
			obj.setHealthbureau1(Common.get(ref.getHealthbureau1()));	         //主管衛生局一
			obj.setHealthbureautype1(Common.get(ref.getHealthbureautype1()));    //主管衛生局一類型
			obj.setHealthbureau2(Common.get(ref.getHealthbureau2()));	         //主管衛生局二
			obj.setHealthbureautype2(Common.get(ref.getHealthbureautype2()));	 //主管衛生局二類型
        	obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			if("2".equals(ref.getUpdateType()) && "10".equals(ref.getStatus()))  //審核完成
			{
				obj.setStatus("20");
				//新增藥品重大品質事件分析及評估檔
				Drg8004Db master = new Drg8004Db();				
				master.setDrg8001Db(obj);
				master.setStatus("40");
				master.setCreator(ref.getUserID());
				master.setCreateDate(yyymmdd);
				master.setCreateTime(hhmmss);				
				ServiceGetter.getInstance().getTcbwService().save(master);
				
				//將預計回收清單寫入實際回收清單中
				copyToDrg8005Db(obj);
				
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"20", "案件審核 - 審核通過", ref.getUserID());
				
			}
			else if("3".equals(ref.getUpdateType()) && "10".equals(ref.getStatus()))  //審核退件
			{
				obj.setStatus("00");
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"00", "案件審核 - 審核退件", ref.getUserID());
			}
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	public void copyToDrg8005Db(Drg8001Db drg8001Db) throws Exception 
	{		
		if(drg8001Db != null){	
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			java.util.List<Drg8005Db> saveList = new java.util.ArrayList<Drg8005Db>();
			String hql = " from Drg8002Db where 1=1 and drg8001Db.id="+Common.get(drg8001Db.getId());			   
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
			if(objList!=null && objList.size()>0){			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext()) {				
					Drg8002Db o = (Drg8002Db) it.next();
					
					Drg8005Db master = new Drg8005Db();
					master.setDrg8001Db(drg8001Db);	
					master.setLotNo(Common.get(o.getLotNo()));                       //批號
					master.setAppreservesNum(o.getReservesNum());                    //廠商回收-庫存量
					master.setAppreservesUnit(Common.get(o.getReservesUnit()));      //廠商回收-庫存量單位					
					master.setAppsellnum(o.getSellnum());	                         //廠商回收-銷售數量
					master.setAppsellunit(Common.get(o.getSellunit()));	             //廠商回收-銷售數量單位
					master.setAppprerecyclenum(Common.getLong(0));                   //廠商回收-回收數量
					master.setAppprerecycleunit(Common.get(o.getReservesUnit()));    //廠商回收-回收數量單位(與庫存量單位相同，先用庫存量單位存入)
					
					master.setAppktotalNum(o.getReservesNum());	                     //廠商回收-總計
					master.setCreator("SYS");
					master.setCreateDate(yyymmdd);
					master.setCreateTime(hhmmss);
					saveList.add(master);
				}
				saveOrUpdateDrg8005Db(saveList, null, null);  
			}
		}		
	}
	
	public void saveOrUpdateDrg8005Db(java.util.List<Drg8005Db> saveList,java.util.List<Drg8005Db> updateList,java.util.List<Drg8005Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	@Override
	public void updateBySdrg0301F(SDRG0301F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	java.util.List<Drg8005Db> saveListDrg8005Db = new java.util.ArrayList<Drg8005Db>();	
    		java.util.List<Drg8005Db> updateListDrg8005Db = new java.util.ArrayList<Drg8005Db>();
    		java.util.List<Drg8005Db> deleteListDrg8005Db = new java.util.ArrayList<Drg8005Db>();    		
    		java.util.List<Drg8005Db> drg8005DbList =null ;	
    		java.util.Map<String,Drg8005Db> drg8005DbMap=null;
        	
        	obj.setAppRecDate(Common.get(ref.getAppRecDate()));	                        //廠商完成回收日期
			obj.setAppRecMan(Common.get(ref.getAppRecMan()));	                        //廠商回收-作業人員
			obj.setApprecyclestorage(Common.get(ref.getApprecyclestorage()));	        //廠商回收-回收品及庫存品處置方式
			obj.setApprecyclestoragedesc(Common.get(ref.getApprecyclestoragedesc()));	//廠商回收-回收品及庫存品處置方式說明
			obj.setAppprepunishdate(Common.get(ref.getAppprepunishdate()));	            //廠商回收-預計處置日期
			
			obj.setAppreplydate(Common.get(ref.getAppreplydate()));	                    //廠商回收-回覆日期
			obj.setAppmedicineType(Common.get(ref.getAppmedicineType()));	            //廠商回收-學名藥/原廠藥
			obj.setAppproduceType(Common.get(ref.getAppproduceType()));	                //廠商回收-國產/輸入
			obj.setApplotType(Common.get(ref.getApplotType()));	                        //廠商回收-批號範圍
			obj.setApprecyclereason(Common.get(ref.getApprecyclereason()));	            //廠商回收-回收原因
			obj.setApprecyclersdesc(Common.get(ref.getApprecyclersdesc()));	            //廠商回收-回收原因說明
			String appsurveyStr ="";
			if(ref.getAppsurvey() != null && ref.getAppsurvey().length>0){			
				for(int i=0;i<ref.getAppsurvey().length;i++){								
					if(appsurveyStr.length() > 0){
						appsurveyStr += ",";
					}
					appsurveyStr += ref.getAppsurvey()[i];
				}
			}			
			obj.setAppsurvey(Common.get(appsurveyStr));	                                //廠商回收-調查結果
			obj.setAppsurveyOther(Common.get(ref.getAppsurveyOther()));        	        //廠商回收-調查結果說明
			String appprecautionStr ="";
			if(ref.getAppprecaution() != null && ref.getAppprecaution().length>0){			
				for(int i=0;i<ref.getAppprecaution().length;i++){								
					if(appprecautionStr.length() > 0){
						appprecautionStr += ",";
					}
					appprecautionStr += ref.getAppprecaution()[i];
				}
			}
			obj.setAppprecaution(Common.get(appprecautionStr));        	                //廠商回收-預防措施
			obj.setAppprecautionOther(Common.get(ref.getAppprecautionOther()));        	//廠商回收-預防措施說明
			obj.setAppsurveyresult(Common.get(ref.getAppsurveyresult()));	            //廠商回收-清查結果
			obj.setAppsurveyreport(Common.get(ref.getAppsurveyreport()));	            //廠商回收-調查報告
			obj.setAppprecautiontime(Common.get(ref.getAppprecautiontime()));	        //廠商回收-預防矯正措施及改善時程
			obj.setAppcheckmanudate(Common.get(ref.getAppcheckmanudate()));	            //廠商回收-查廠日期
			obj.setAppcheckmanuresult(Common.get(ref.getAppcheckmanuresult()));	        //廠商回收-查廠查核結果
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			long checktotalNum = 0;
			//回收清單明細(實際)
			if(ref.getDrg8005DbType()!=null)
			{		   
				drg8005DbList = ServiceGetter.getInstance().getCommonService().load("from Drg8005Db where drg8001Db.id="+Common.getLong(ref.getId()));			   
				drg8005DbMap= new java.util.HashMap<String,Drg8005Db>();
			   
				for(Drg8005Db obj1:drg8005DbList){				  
					drg8005DbMap.put(Common.get(obj1.getId()),obj1);			   
				}
			   
				boolean isUpdate = true;
				for(int i=0;i<ref.getDrg8005DbType().length;i++){				  
					String oldId = Common.get(ref.getDrg8005DbTypeId()[i]);					
					Drg8005Db obj5 = drg8005DbMap.get(oldId);		
				    
					if (obj5==null){					
						isUpdate = false;				
						obj5 = new Drg8005Db();				  
					}
					
					obj5.setLotNo(ref.getApplotNo()[i]);                                         //廠商回收-批號				  
					obj5.setAppreservesNum(Common.getLong(ref.getAppreservesNum()[i]));          //廠商回收-庫存量				  
					obj5.setAppreservesUnit(ref.getAppreservesUnit()[i]);                        //廠商回收-庫存量單位				  
					obj5.setAppsellnum(Common.getLong(ref.getAppsellnum()[i]));                  //廠商回收-銷售數量				  
					obj5.setAppsellunit(ref.getAppsellunit()[i]);                                //廠商回收-銷售數量單位
					obj5.setAppprerecyclenum(Common.getLong(ref.getAppprerecyclenum()[i]));      //廠商回收-回收數量
					obj5.setAppprerecycleunit(ref.getAppprerecycleunit()[i]);                    //廠商回收-回收數量單位
					obj5.setAppktotalNum(Common.getLong(ref.getAppktotalNum()[i]));              //廠商回收-總數量
					obj5.setModifier(ref.getUserID());				  
					obj5.setModifyDate(Datetime.getYYYMMDD());				  
					obj5.setModifyTime(Datetime.getHHMMSS());				  
									  
					obj5.setDrg8001Db(obj);				  
				    
					checktotalNum += Common.getInt(ref.getAppktotalNum()[i]);
					
					if (isUpdate) {				  
						updateListDrg8005Db.add(obj5);				  
					}				  
					else {					  
						saveListDrg8005Db.add(obj5);			  
					}			    
				}	
			
			    for(Drg8005Db objdelete:drg8005DbList){
			    	boolean isdelete = true;				   
			    	for(int i=0;i<ref.getDrg8005DbType().length;i++){		    		  
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getDrg8005DbTypeId()[i]))){		    			
			    			isdelete = false;		    			
			    			break;		    		  
			    		}				   
			    	}			   
			    	if(isdelete){					  
			    		deleteListDrg8005Db.add(objdelete);				   
			    	}			     
			    }			
		    }  
		    else{
			   java.util.List<Drg8005Db> deleteList = new java.util.ArrayList<Drg8005Db>();
			   java.util.List<Drg8005Db> Drg8005DbList = ServiceGetter.getInstance().getCommonService().load("from Drg8005Db where drg8001Db.id="+Common.get(ref.getId()));
			   for(Drg8005Db obj0:Drg8005DbList)
			   {
				 deleteList.add(obj0);
			   }	
			
			   saveOrUpdateDrg8005Db(null, null, deleteList);  
	        }

			if(("4".equals(ref.getUpdateType()) || "6".equals(ref.getUpdateType()))&& "20".equals(ref.getStatus()))  //回收完成
			{				
				obj.setStatus("21");			
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"21", "廠商回收 - 廠商回收完成", ref.getUserID());
				
			}
			if(("5".equals(ref.getUpdateType()) || "6".equals(ref.getUpdateType()))&& "40".equals(ref.getStatus2()))  //回覆完成
			{	
				Drg8004Db drg8004 = (Drg8004Db)View.getObject(" from Drg8004Db where drg8001Db.id = " + Common.getLong(ref.getId()));
				if(drg8004 != null){
					drg8004.setStatus("50");
					drg8004.setAssesssurveyresult(Common.get(ref.getAppsurveyresult()));               //案件評估-清查結果
					drg8004.setAssesssurveyreport(Common.get(ref.getAppsurveyreport()));	           //案件評估-調查報告
					drg8004.setAssessprecaution(Common.get(ref.getAppprecautiontime()));	           //案件評估-預防矯正措施及改善時程

					ServiceGetter.getInstance().getTcbwService().update(drg8004);
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"21", "廠商回收 - 廠商回覆完成", ref.getUserID());
				}				
			}
			if("7".equals(ref.getUpdateType()) && "21".equals(ref.getStatus()))  //廠商回收-審核完成
			{				
				obj.setStatus("30");				
				obj.setChecktotalNum(Common.getLong(checktotalNum));
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"30", "廠商回收 - 審核完成", ref.getUserID());			
			}
			if("8".equals(ref.getUpdateType()) && "21".equals(ref.getStatus()))  //廠商回收-審核退件
			{	
				obj.setStatus("20");			
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"20", "廠商回收 - 審核退件", ref.getUserID());			
			}
			saveOrUpdateDrg8005Db(saveListDrg8005Db, updateListDrg8005Db, deleteListDrg8005Db);
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}

	public void updateBySdrg0401F(SDRG0401F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{        		
			obj.setCheckpunishdate(Common.get(ref.getCheckpunishdate()));	                //回收確認-處置日期
			obj.setCheckcyclestorage(Common.get(ref.getCheckcyclestorage()));	            //回收確認-回收品及庫存品處置方式
			obj.setCheckcyclestorageOther(Common.get(ref.getCheckcyclestorageOther()));	    //回收確認-回收品及庫存品處置方式說明
			obj.setChecktotalNum(Common.getLong(ref.getChecktotalNum()));	                //回收確認-總數量
			obj.setChecktotalUnit(Common.get(ref.getChecktotalUnit()));	                    //回收確認-總數量單位
			obj.setCheckhealthbureau(Common.get(ref.getCheckhealthbureau()));	            //回收確認-查核衛生局
			obj.setIscheckmatchnum(Common.get(ref.getIscheckmatchnum()));	                //回收確認-是否與回收報告書所載數量相符
			obj.setChecknonmatchreason(Common.get(ref.getChecknonmatchreason()));	        //回收確認-數量不符之理由
			obj.setCheckmanudate(Common.get(ref.getCheckmanudate()));                       //回收確認-查核日期
			obj.setCheckcontactman(Common.get(ref.getCheckcontactman()));	                //回收確認-聯絡窗口
			obj.setCheckcontacttel(Common.get(ref.getCheckcontacttel()));	                //回收確認-聯絡電話
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);	
			
			if("A".equals(ref.getUpdateType()) && "30".equals(ref.getStatus()))  //回收確認完成
			{				
				obj.setCheckUpdateYn("Y");//回收確認-廠商確認完成 (不讓廠商一改再改)
			}
			if("9".equals(ref.getUpdateType()) && "30".equals(ref.getStatus()))  //回收確認完成
			{				
				obj.setStatus("31");			
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"21", "回收確認 - 衛生局確認完成", ref.getUserID());				
			}			
			if("10".equals(ref.getUpdateType()) && "31".equals(ref.getStatus()))  //回收確認-審核完成
			{	
				String ststus2 = View.getLookupField("select status from Drg8004Db where drg8001Db.id="+Common.getLong(obj.getId()));				
				obj.setStatus(ststus2);			
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),ststus2, "回收確認 - 審核完成", ref.getUserID());			
			}
			if("11".equals(ref.getUpdateType()) && "31".equals(ref.getStatus()))  //回收確認-審核退件
			{	
				obj.setStatus("30");			
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"20", "回收確認 - 審核退件", ref.getUserID());			
			}
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	public void updateBySdrg0501F(SDRG0501F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();		
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));
		Drg8004Db c = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id=" + Common.getInt(ref.getId()));
        
		if(c != null && obj!= null)
		{        		
        	c.setAssessdate(Common.get(ref.getAssessdate()));	                     //案件評估-評估日期
			c.setAssessrecyclereason(Common.get(ref.getAssessrecyclereason()));	     //案件評估-本案原由
			c.setAssesssurveyresult(Common.get(ref.getAssesssurveyresult()));	     //案件評估-清查結果
			c.setAssesssurveyreport(Common.get(ref.getAssesssurveyreport()));	     //案件評估-調查報告
			c.setAssessprecaution(Common.get(ref.getAssessprecaution()));	         //案件評估-預防矯正措施及改善時程
			String assessdealWithStr ="";
			if(ref.getAssessdealWith() != null && ref.getAssessdealWith().length>0){			
				for(int i=0;i<ref.getAssessdealWith().length;i++){								
					if(assessdealWithStr.length() > 0){
						assessdealWithStr += ",";
					}
					assessdealWithStr += ref.getAssessdealWith()[i];
				}
			}
			c.setAssessdealWith(Common.get(assessdealWithStr));	                     //案件評估-擬辦事項
			c.setAssessresult(Common.get(ref.getAssessresult()));	                 //案件評估-評估結果	
			c.setModifier(ref.getUserID());
			c.setModifyDate(yyymmdd);
			c.setModifyTime(hhmmss);		
			
			if("12".equals(ref.getUpdateType()) && "50".equals(ref.getStatus2()))           //案件評估
			{				
				c.setStatus("60");				
				c.setAnamedicineType(Common.get(obj.getAppmedicineType()));	                //案件分析-學名藥/原廠藥
				c.setAnaproduceType(Common.get(obj.getAppproduceType()));	                //案件分析-國產/輸入
				c.setAnalotType(Common.get(obj.getApplotType()));	                        //案件分析-批號範圍
				c.setAnarecyclereason(Common.get(obj.getApprecyclereason()));	            //案件分析-回收原因
				c.setAnarecyclersdesc(Common.get(obj.getApprecyclersdesc()));	            //案件分析-回收原因說明
				c.setAnasurvey(Common.get(obj.getAppsurvey()));	                            //案件分析-調查結果
				c.setAnasurveyOther(Common.get(obj.getAppsurveyOther()));        	        //案件分析-調查結果說明
				c.setAnaprecaution(Common.get(obj.getAppprecaution()));                     //案件分析-預防措施
				c.setAnaprecautionOther(Common.get(obj.getAppprecautionOther()));           //案件分析-預防措施說明
				
				if(Common.getInt(obj.getStatus()) >=40 ){
					obj.setStatus("60");
					obj.setModifier(ref.getUserID());
					obj.setModifyDate(yyymmdd);
					obj.setModifyTime(hhmmss);
					ServiceGetter.getInstance().getTcbwService().update(obj);
				}
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"60", "案件評估 - 評估完成", ref.getUserID());			
			}
			ServiceGetter.getInstance().getTcbwService().update(c);
		}
	}
	
	public void updateBySdrg0601F(SDRG0601F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();		
		
		Drg8001Db obj = (Drg8001Db)View.getObject(" from Drg8001Db where id = " + Common.getLong(ref.getId()));
		Drg8004Db c = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id=" + Common.getInt(ref.getId()));
        
		if(c != null && obj!= null)
		{        		
			c.setAnadate(Common.get(ref.getAnadate()));	                           //案件分析-分析日期
			c.setAnamedicineType(Common.get(ref.getAnamedicineType()));	           //案件分析-學名藥/原廠藥
			c.setAnaproduceType(Common.get(ref.getAnaproduceType()));	           //案件分析-國產/輸入
			c.setAnalotType(Common.get(ref.getAnalotType()));	                   //案件分析-批號範圍
			c.setAnarecyclereason(Common.get(ref.getAnarecyclereason()));	       //案件分析-回收原因
			c.setAnarecyclersdesc(Common.get(ref.getAnarecyclersdesc()));	       //案件分析-回收原因說明
			String anasurveyStr ="";
			if(ref.getAnasurvey() != null && ref.getAnasurvey().length>0){			
				for(int i=0;i<ref.getAnasurvey().length;i++){								
					if(anasurveyStr.length() > 0){
						anasurveyStr += ",";
					}
					anasurveyStr += ref.getAnasurvey()[i];
				}
			}
			c.setAnasurvey(Common.get(anasurveyStr));	                           //案件分析-調查結果
			c.setAnasurveyOther(Common.get(ref.getAnasurveyOther()));        	   //案件分析-調查結果說明
			String anaprecautionStr ="";
			if(ref.getAnaprecaution() != null  && ref.getAnaprecaution().length > 0){			
				for(int i=0;i<ref.getAnaprecaution().length;i++){								
					if(anaprecautionStr.length() > 0){
						anaprecautionStr += ",";
					}
					anaprecautionStr += ref.getAnaprecaution()[i];
				}
			}
			c.setAnaprecaution(Common.get(anaprecautionStr));                      //案件分析-預防措施
			c.setAnaprecautionOther(Common.get(ref.getAnaprecautionOther()));      //案件分析-預防措施說明	
			c.setModifier(ref.getUserID());
			c.setModifyDate(yyymmdd);
			c.setModifyTime(hhmmss);		
			
			if("13".equals(ref.getUpdateType()) && "60".equals(ref.getStatus2()))           //案件評估
			{				
				c.setStatus("90");				
				if(Common.getInt(obj.getStatus()) >=40 ){
					obj.setStatus("90");
					obj.setModifier(ref.getUserID());
					obj.setModifyDate(yyymmdd);
					obj.setModifyTime(hhmmss);
					ServiceGetter.getInstance().getTcbwService().update(obj);
				}
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG4",obj.getId(), obj.getApplNo(),"90", "案件分析 - 分析完成", ref.getUserID());			
			}
			ServiceGetter.getInstance().getTcbwService().update(c);
		}
	}
}
