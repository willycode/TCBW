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
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.model.bo.Med9003Db;
import com.kangdainfo.tcbw.model.dao.PmedDao;
import com.kangdainfo.tcbw.model.dao.SdrgDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.pmed.PMED0101F;
import com.kangdainfo.tcbw.view.pmed.PMED0201F;
import com.kangdainfo.tcbw.view.pmed.PMED0301F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0201F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0301F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0401F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0501F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0601F;

public class PmedDaoImpl extends BaseDaoImpl implements PmedDao{
	
	@Override
	public void updateByPmed0101F(PMED0101F ref) throws Exception 
	{		
		java.util.List<Med9002Db> saveListMed9002Db = new java.util.ArrayList<Med9002Db>();	
		java.util.List<Med9002Db> updateListMed9002Db = new java.util.ArrayList<Med9002Db>();
		java.util.List<Med9002Db> deleteListMed9002Db = new java.util.ArrayList<Med9002Db>();
		java.util.List<Med9002Db> med9002DbList =null ;	
		java.util.Map<String,Med9002Db> med9002DbMap=null;
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Med9001Db obj = (Med9001Db)View.getObject(" from Med9001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	if("".equals(Common.get(ref.getApplNo()))) {
        		obj.setApplNo(Common.get(ref.getApplNo()));
    			obj.setMonitorNo(Common.get(ref.getMonitorNo()));
    			obj.setStatus("00");
            	obj.setPermitKey(Common.get(ref.getPermitKey()));
            	obj.setPermitNo(Common.get(ref.getPermitNo()));
            	obj.setChProduct(Common.get(ref.getChProduct()));
            	obj.setEnProduct(Common.get(ref.getEnProduct())); 

            	obj.setMedapprovedate(Common.get(ref.getMedapprovedate()));
            	obj.setMedEffectiveDate(Common.get(ref.getMedEffectiveDate()));
            	obj.setApplicationID(Common.get(ref.getApplicationID()));
            	obj.setApplicationName(Common.get(ref.getApplicationName()));
            	obj.setManufactorName(Common.get(ref.getManufactorName()));
            	obj.setManufactorCountry(Common.get(ref.getManufactorCountry()));
            	obj.setMedclass(Common.get(ref.getMedclass()));
            	obj.setMedMainCategory(Common.get(ref.getMedMainCategory()));
            	obj.setMedSecCategory(Common.get(ref.getMedSecCategory()));
            	obj.setMedModel(Common.get(ref.getMedModel()));
            	obj.setMedeffect(Common.get(ref.getMedeffect()));
            	obj.setMonitorSDate(Common.get(ref.getMonitorSDate()));
            	obj.setMonitorEDate(Common.get(ref.getMonitorEDate()));
            	obj.setReportIssuenum(Common.getLong(ref.getReportIssuenum()));
            	obj.setIntervalmonth(Common.getLong(ref.getIntervalmonth()));
            	obj.setMonitorremark(Common.get(ref.getMonitorremark()));	
            	
    			obj.setModifier(ref.getUserID());
    			obj.setModifyDate(yyymmdd);
    			obj.setModifyTime(hhmmss);
        	}
        	
			//報告繳交清單
			if(ref.getMed9002DbType()!=null)
			{		   
				med9002DbList = ServiceGetter.getInstance().getCommonService().load("from Med9002Db where med9001Db.id="+Common.getLong(ref.getId())+"order by prehanddate,reporttype asc");			   
				med9002DbMap= new java.util.HashMap<String,Med9002Db>();
			   
				for(Med9002Db obj1:med9002DbList){				  
					med9002DbMap.put(Common.get(obj1.getId()),obj1);			   
				}
			   
				boolean isUpdate = true;
			
			    int reportissueno = 0;
				for(int i=0;i<ref.getMed9002DbType().length;i++){				  
					String oldId = Common.get(ref.getMed9002DbTypeId()[i]);					
					Med9002Db obj2 = med9002DbMap.get(oldId);		
				  
					if (obj2==null){					
						isUpdate = false;				
						obj2 = new Med9002Db();				  
					}
					//重新計算期數
					reportissueno++;
					if("02".equals(Common.get(ref.getReporttype()[i]))) {
						reportissueno--;
					}

					if("01".equals(Common.get(ref.getReporttype()[i]))) {
						obj2.setReportIssueno(Common.getLong(reportissueno));	//期數	
					} else {
						obj2.setReportIssueno(Common.getLong(""));	//期數	
					}
								  
					obj2.setPrehanddate(Common.get(ref.getPrehanddate()[i]));  //繳交日期			  
					obj2.setReporttype(Common.get(ref.getReporttype()[i]));    //報告類別
					
					if(null != ref.getHandstatus() && ref.getHandstatus().length > 0) {
						if(!"".equals(Common.get(ref.getHandstatus()[i]))) {
							obj2.setHandstatus(Common.get(ref.getHandstatus()[i]));
						} else {
							obj2.setHandstatus("01");		//繳交狀態-預設為待繳交
						}
					}else {
						obj2.setHandstatus("01");		//繳交狀態-預設為待繳交
					}

					if(!"".equals(Common.get(ref.getApplNo()))) {
						
					}
					
					
					obj2.setMonitorsdate(Common.get(ref.getMonitorSDate()));	//[本次報告監控期間(起)]預設值為案件資料的[監控期間(起)]
					obj2.setMonitoredate(Common.get(ref.getPrehanddate()[i]));		//[本次報告監控期間(迄)]預設值為報告資料的[預計繳交日期]
					
					obj2.setModifier(ref.getUserID());				  
					obj2.setModifyDate(Datetime.getYYYMMDD());				  
					obj2.setModifyTime(Datetime.getHHMMSS());			  
								  
					obj2.setMed9001Db(obj);				  
				  
					if (isUpdate) {				  
						updateListMed9002Db.add(obj2);				  
					}				  
					else {					  
						saveListMed9002Db.add(obj2);			  
					}			    
				}	
			
			    for(Med9002Db objdelete:med9002DbList){
			    	boolean isdelete = true;				   
			    	for(int i=0;i<ref.getMed9002DbType().length;i++){		    		  
			    		if(Common.get(objdelete.getId()).equals(Common.get(ref.getMed9002DbTypeId()[i]))){		    			
			    			isdelete = false;		    			
			    			break;		    		  
			    		}				   
			    	}			   
			    	if(isdelete){					  
			    		deleteListMed9002Db.add(objdelete);				   
			    	}			     
			    }			
		    }  
		    else{
			   java.util.List<Med9002Db> deleteList = new java.util.ArrayList<Med9002Db>();
			   java.util.List<Med9002Db> Med9002DbList = ServiceGetter.getInstance().getCommonService().load("from Med9002Db where med9001Db.id="+Common.get(ref.getId()));
			   for(Med9002Db obj0:Med9002DbList)
			   {
				 deleteList.add(obj0);
			   }	
			
			   saveOrUpdateMed9002Db(null, null, deleteList);  
	        }
			
			//案件送出
			if("1".equals(ref.getUpdateType()) && "00".equals(ref.getStatus()))  
			{
				obj.setStatus("10");
				//系統給號		
	        	if("".equals(Common.get(obj.getApplNo())))		
	        	{		  
	        		String no=TCBWCommon.getApplNo("MED05","P04",Datetime.getYYY());	
	        		if(no!=null)		  
	        		{			
	        			obj.setApplNo(no);		  
	        		}
//	        		obj.setNotifierRepDate(yyymmdd);
	        	}
	        	//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",obj.getId(), obj.getApplNo(),"10", "案件登錄 - 送件", ref.getUserID());
			}
			
			saveOrUpdateMed9002Db(saveListMed9002Db, updateListMed9002Db, deleteListMed9002Db);
			saveOrUpdate(obj);
			
			ref.setId(Common.get(obj.getId()));			
		}	
		
	}
	
	public void saveOrUpdateMed9002Db(java.util.List<Med9002Db> saveList,java.util.List<Med9002Db> updateList,java.util.List<Med9002Db> deleteList) 
	throws Exception
	{
		deleteAndSave(deleteList, saveList);
		updateBatch(updateList);
	}
	
	@Override
	public void updateByPmed0201F(PMED0201F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Med9001Db med9001 = (Med9001Db)View.getObject(" from Med9001Db where id = " + Common.getLong(ref.getId()));
		Med9002Db obj = (Med9002Db)View.getObject(" from Med9002Db where id = " + Common.getLong(ref.getId2()));
		Med9003Db med9003 = (Med9003Db)View.getObject(" from Med9003Db where med9002Db.id = " + Common.getLong(ref.getId2()));

        if(obj != null)
		{
        	obj.setReportIssueno(Common.getLong(ref.getReportIssueno()));	    //期數
			obj.setReporttype(Common.get(ref.getReporttype()));	                //報告類別
			obj.setHandstatus(Common.get(ref.getHandstatus()));	             	//繳交狀態
			obj.setPrehanddate(Common.get(ref.getPrehanddate()));	            //預計繳交日期
			obj.setHanddate(Common.get(ref.getHanddate()));	         			//實際繳交日期
			obj.setMonitorsdate(Common.get(ref.getMonitorsdateR()));	        //本次報告監控期間起日
			obj.setMonitoredate(Common.get(ref.getMonitoredateR()));	        //本次報告監控期間迄日
			obj.setReportreceiveno(Common.get(ref.getReportreceiveno()));		//報告收文字號
			obj.setReportsummary(Common.get(ref.getReportsummary()));			//報告摘要
			obj.setInDefCaseNum(Common.getLong(ref.getInDefCaseNum()));			//國內嚴重不良反應案件件數
			obj.setInUnDefCaseNum(Common.getLong(ref.getInUnDefCaseNum()));		//國內非嚴重不良反應案件件數
			obj.setAbDefCaseNum(Common.getLong(ref.getAbDefCaseNum()));			//國外嚴重不良反應案件件數
			obj.setAbUnDefCaseNum(Common.getLong(ref.getAbUnDefCaseNum()));		//國外非嚴重不良反應案件件數
			obj.setScienceNum(Common.getLong(ref.getScienceNum()));				//國內外學術期刊文獻及研討會報告件數
			obj.setReportremark(Common.get(ref.getReportremark()));				//報告備註
			
			
			if(med9003 != null) {
				med9003.setNoticereupdateNo(ref.getNoticereupdateNo());
				med9003.setNoticereupdatedate(ref.getNoticereupdatedate());
				med9003.setNoticereupdatesummary(ref.getNoticereupdatesummary());
				med9003.setReupdateNo(ref.getReupdateNo());
				med9003.setReupdatedate(ref.getReupdatedate());
				med9003.setReupdatesummary(ref.getReupdatesummary());
				med9003.setModifier(Common.get(ref.getUserID()));
				med9003.setModifyDate(yyymmdd);
				med9003.setModifyTime(hhmmss);
				ServiceGetter.getInstance().getTcbwService().update(med9003);
			}

			if("1".equals(ref.getUpdateType()) && "01".equals(ref.getHandstatus())) {
				//案件繳交
				if("01".equals(ref.getReporttype())) {
					med9001.setStatus("20");
				} else if("02".equals(ref.getReporttype())) {
					med9001.setStatus("40");
				}
				med9001.setModifier(Common.get(ref.getUserID()));
				med9001.setModifyDate(yyymmdd);
				med9001.setModifyTime(hhmmss);
				obj.setHandstatus("02");											//繳交狀態-待評估
				ServiceGetter.getInstance().getTcbwService().update(med9001);
				
				if("01".equals(ref.getReporttype())) {
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",med9001.getId(), med9001.getApplNo(),"20", "定期報告繳交 - 評估中", ref.getUserID());
				} else if("02".equals(ref.getReporttype())) {
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",med9001.getId(), med9001.getApplNo(),"40", "總結報告繳交 - 評估中", ref.getUserID());
				}
			} else if("2".equals(ref.getUpdateType())) {
				//補件回覆
				if("01".equals(ref.getReporttype())) {
					med9001.setStatus("20");
				} else if("02".equals(ref.getReporttype())) {
					med9001.setStatus("40");
				}
				obj.setHandstatus("02");											//繳交狀態-待評估
				if("01".equals(ref.getReporttype())) {
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",med9001.getId(), med9001.getApplNo(),"20", "定期報告補件回覆 - 評估中", ref.getUserID());
				} else if("02".equals(ref.getReporttype())) {
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",med9001.getId(), med9001.getApplNo(),"40", "總結報告補件回覆 - 評估中", ref.getUserID());
				}
			}
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	@Override
	public void updateByPmed0301F(PMED0301F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Med9001Db med9001 = (Med9001Db)View.getObject(" from Med9001Db where id = " + Common.getLong(ref.getId()));
		Med9002Db obj = (Med9002Db)View.getObject(" from Med9002Db where id = " + Common.getLong(ref.getId2()));

        if(obj != null)
		{
			obj.setAssessdate(Common.get(ref.getAssessdate()));				//評估日期
			obj.setAssesssummary(Common.get(ref.getAssesssummary()));		//評估摘要
			obj.setAssessresult(Common.get(ref.getAssessresult()));			//評估結果
			obj.setAssessresultdesc(Common.get(ref.getAssessresultdesc()));	//評估結果-其他
			obj.setAssessremark(Common.get(ref.getAssessremark()));			//評估備註

			if("1".equals(ref.getUpdateType())) {
				//案件評估完成
				if("Y".equals(ref.getIsNotHand())) {
					med9001.setStatus("10");
				} else if("N".equals(ref.getIsNotHand())) {
					med9001.setStatus("30");
				} else if("02".equals(ref.getReporttype())) {
					med9001.setStatus("90");
				}
				med9001.setModifier(Common.get(ref.getUserID()));
				med9001.setModifyDate(yyymmdd);
				med9001.setModifyTime(hhmmss);
				obj.setHandstatus("04");											//繳交狀態-已評估
				ServiceGetter.getInstance().getTcbwService().update(med9001);
			}
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	//補件
	@Override
	public void updateByDoAddocuments(PMED0301F ref) throws Exception 
	{
		Med9001Db obj = (Med9001Db) View.getObject(" from Med9001Db where id = " + ref.getId());
		Med9002Db med9002 = (Med9002Db) View.getObject(" from Med9002Db where id = " + ref.getId2());
		
		if("01".equals(ref.getReporttype())) {	//定期報告補件
			obj.setStatus("21");
		} else if("02".equals(ref.getReporttype())) {	//總結報告補件
			obj.setStatus("41");
		}
		med9002.setHandstatus("03");
		
		ServiceGetter.getInstance().getTcbwService().update(obj);
		ServiceGetter.getInstance().getTcbwService().update(med9002);

		
		//系統背景新增補件資料
		Med9003Db med9003 = new Med9003Db();
		med9003.setNoticereupdatedate(Datetime.getYYYMMDD());
		med9003.setCreator(ref.getUserID());
		med9003.setCreateDate(Datetime.getYYYMMDD());
		med9003.setCreateTime(Datetime.getHHMMSS());
		med9003.setMed9002Db(med9002);
		
		ServiceGetter.getInstance().getTcbwService().save(med9003);
		
		
		if("01".equals(ref.getReporttype())) {
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",obj.getId(), obj.getApplNo(),"21", "定期報告補件 - 補件中", ref.getUserID());
		} else if("02".equals(ref.getReporttype())) {
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED05",obj.getId(), obj.getApplNo(),"41", "總結報告補件 - 補件中", ref.getUserID());
		}
	}
}
