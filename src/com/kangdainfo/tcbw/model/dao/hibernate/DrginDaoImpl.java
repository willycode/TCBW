package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.ReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg0009Db;
import com.kangdainfo.tcbw.model.bo.Drg1002Db;
import com.kangdainfo.tcbw.model.bo.Drg1003Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med4003Db;
import com.kangdainfo.tcbw.model.bo.Med4004Db;
import com.kangdainfo.tcbw.model.bo.Med4005Db;
import com.kangdainfo.tcbw.model.dao.DrginDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgex.DRGEX0102F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0101F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102Q;
import com.kangdainfo.tcbw.view.drgin.DRGIN0106F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0109F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0112F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0114F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0302F;
import com.kangdainfo.tcbw.view.medin.MEDIN5202F;



public class DrginDaoImpl extends BaseDaoImpl implements DrginDao{

	@Override
	public void updateByDrgIN0102F(DRGIN0102F ref) throws Exception 
	{
		java.util.List<Drg0002Db> saveListDrg0002Db = new java.util.ArrayList<Drg0002Db>();	
		java.util.List<Drg0002Db> updateListDrg0002Db = new java.util.ArrayList<Drg0002Db>();
		java.util.List<Drg0002Db> deleteListDrg0002Db = new java.util.ArrayList<Drg0002Db>();
		java.util.List<Drg0002Db> drg0002DbList =null ;
		java.util.List<Drg0002Db> drg0002DbList3 =null ;
		java.util.Map<String,Drg0002Db> drg0002DbMap=null;
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	//不良藥品資料--------------------------------------------------------------
			obj.setPermitKey(Common.get(ref.getPermitKey()));
			obj.setPermitNo(Common.get(ref.getPermitNo()));
			obj.setChProduct(Common.get(ref.getChProduct()));
			obj.setEnProduct(Common.get(ref.getEnProduct()));
			obj.setIngredient(Common.get(ref.getIngredient()));
			obj.setContent(Common.get(ref.getContent()));
			obj.setMedModel(Common.get(ref.getMedModel()));
			obj.setMedModelOther(Common.get(ref.getMedModelOther()));
			obj.setMedPackage(Common.get(ref.getMedPackage()));
			obj.setMedPackageOther(Common.get(ref.getMedPackageOther()));
			obj.setApplicationID(Common.get(ref.getApplicationID()));
			obj.setApplicationName(Common.get(ref.getApplicationName()));
			obj.setManufactorName(Common.get(ref.getManufactorName()));
			obj.setManufactorNo(Common.get(ref.getManufactorNo()));
			obj.setManufactorDate(Common.get(ref.getManufactorDate()));
			obj.setExpirationDate(Common.get(ref.getExpirationDate()));
			obj.setStorage(Common.get(ref.getStorage()));
			obj.setStorageOther(Common.get(ref.getStorageOther()));
			obj.setIsFindYn(Common.get(ref.getIsFindYn()));
			obj.setIsSingleYn(Common.get(ref.getIsSingleYn()));
			obj.setSameNum(Common.get(ref.getSameNum()));
			obj.setDiffNum(Common.get(ref.getDiffNum()));
			obj.setIsHarmYn(Common.get(ref.getIsHarmYn()));
			obj.setIsUsedYn(Common.get(ref.getIsUsedYn()));
			obj.setEvenContactYn(Common.get(ref.getEvenContactYn()));
			obj.setDealWith(Common.get(ref.getDealWith()));
			obj.setIsContactYn(Common.get(ref.getIsContactYn()));
			obj.setFirstResult(Common.get(ref.getFirstResult()));
			obj.setFirstRemark(Common.get(ref.getFirstRemark()));			
			obj.setChargeMan(Common.get(ref.getUserID()));
			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			if(null != ref.getMainCode()){
				Map<String, String> subMap = new HashMap<String, String>();
				drg0002DbList = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(ref.getId()));	
				drg0002DbMap= new java.util.HashMap<String,Drg0002Db>();
				for(Drg0002Db obj1:drg0002DbList)
				{
					drg0002DbMap.put(Common.get(obj1.getMainCode()),obj1);			
				}   
				
				if(drg0002DbList!=null) drg0002DbList.clear();

				for(int i=0;i<ref.getMainCode().length;i++){
					String mainCode = ref.getMainCode()[i];
					String subCodeStr ="";
					for(int j=0;j<ref.getSubCode().length;j++){
						if(ref.getSubCode()[j].substring(0,2).equals(mainCode)){
							if(subCodeStr.length() > 0){
								subCodeStr += ",";
							}
							subCodeStr += ref.getSubCode()[j];
						}
					}
					subMap.put(mainCode, subCodeStr);
				}
				
				if(null != subMap && !subMap.isEmpty()){
					StringBuilder dtlKey = new StringBuilder("-2,-1");		
					for(String mainCode:subMap.keySet()){
						Drg0002Db drg02 = drg0002DbMap.get(mainCode);
						if(null == drg02){
							drg02 = new Drg0002Db();
							drg02.setDrg0001Db(obj);
						}
						//主代碼				
						drg02.setMainCode(Common.get(mainCode));
						//子代碼						
						drg02.setSubCode(subMap.get(mainCode));			                     
						//說明描述
						java.util.List codeList = ServiceGetter.getInstance().getCommonService().load("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' and isStop='N' order by codeId");
						if (codeList!=null && codeList.size()>0) {
							for(int i=0 ; i< codeList.size();i++){
								Object[] o = (Object[]) codeList.get(i);
								if(Common.get(o[0]).equals(mainCode)){
									drg02.setOtherDescribe(Common.get(ref.getOtherDescribe()[i]));
									break;
								}
							}
						}	
						if (null != drg02.getId() && drg02.getId() > 0){
							dtlKey.append(",").append(drg02.getId());
							updateListDrg0002Db.add(drg02);					
						}else{
							saveListDrg0002Db.add(drg02);
						}
					}
					deleteListDrg0002Db.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where drg0001Db.id=" + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));
				}else{
					//刪除資料				
					drg0002DbList3 = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(ref.getId()));
					deleteListDrg0002Db.addAll(drg0002DbList3);
					if(drg0002DbList3 != null) drg0002DbList3.clear();
				}
			}			

			saveOrUpdate(obj);
			saveOrUpdateDrg0002Db(saveListDrg0002Db, updateListDrg0002Db, deleteListDrg0002Db);
			ref.setId(Common.get(obj.getId()));
			
			if(saveListDrg0002Db != null) saveListDrg0002Db.clear();
			if(updateListDrg0002Db != null) updateListDrg0002Db.clear();
			if(deleteListDrg0002Db != null) deleteListDrg0002Db.clear();
		}	
		
	}
	


	@Override
	public void saveOrUpdateDrg0002Db(java.util.List<Drg0002Db> saveList,java.util.List<Drg0002Db> updateList,java.util.List<Drg0002Db> deleteList)	
	throws Exception {
		if(deleteList !=null) deleteBatch(deleteList);
		if(saveList !=null) saveBatch(saveList);		
		if(updateList !=null) updateBatch(updateList);		
	}
	
	public void backPiecesDrgByIN0102F(DRGIN0102F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("02");//退件
			obj.setChargeMan(ref.getUserID());
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()));
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("02");//退件
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"02", "審核作業 - 退件", ref.getUserID());
		}	
	}
	
	public void dismissalByDrgIN0102F(DRGIN0102F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("03");//撤案
			obj.setChargeMan("");//清空作業人員
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()));
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("03");//撤案
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			
			if("03".equals(obj.getStatus())) {
				//撤案後產生檔案
				closedPrint(Common.get(drg5001Db.getId()),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
				System.out.println("傳入案件狀態：" + obj.getStatus());
			}
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"03", "審核作業 - 撤案", ref.getUserID());
		}	
	}
	
	public void acceptedByDrgIN0102F(Drg0001Db obj,String user) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();		
		
        if(obj != null ){		
        	//系統給號		
        	if("".equals(Common.get(obj.getApplNo())))		
        	{		  
        		String no=TCBWCommon.getApplNo("DRG01","A01",Datetime.getYYY());	
        		if(no!=null)		  
        		{			
        			obj.setApplNo(no);		  
        		}		
        	}
		
        	obj.setStatus("20");//案件分級中(一般)		
        	obj.setEnrolledDate(yyymmdd);//收案日期=系統日期		
        	obj.setChargeMan(TCBWCommon.getUserID("DRG01", "02", "Drg0001Db"));//依據案件分級自動分派原則設定人員
		
		
        	//新增案件初評資料檔        
        	Drg0003Db drg0003Db = new Drg0003Db();        
        	Drg0003Db drg03Db =  this.checkDrg03Result(obj,drg0003Db); //系統自動設定案件初評表的「風險等級、通報時效、通報品質」初始值。        
        	drg0003Db.setApplNo(obj.getApplNo());        
        	drg0003Db.setFirstResult(drg03Db.getFirstResult());        
        	drg0003Db.setNotifierAging(drg03Db.getNotifierAging());        
        	drg0003Db.setIntervalDays(drg03Db.getIntervalDays());        
        	drg0003Db.setNotifierQuality(drg03Db.getNotifierQuality());        
        	drg0003Db.setCreator(user);        
        	drg0003Db.setCreateDate(yyymmdd);        
        	drg0003Db.setCreateTime(hhmmss);
        	
        	//新增案件分級資料檔 (此時新增為了分級時，歷史案件判斷用)
        	Drg0004Db drg0004Db = new Drg0004Db();
        	drg0004Db.setApplNo(obj.getApplNo());
        	drg0004Db.setDrgLev(drg03Db.getFirstResult());     
        	drg0004Db.setCreator(user);        
        	drg0004Db.setCreateDate(yyymmdd);        
        	drg0004Db.setCreateTime(hhmmss);
        
		
        	Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001ID = " + Common.getLong(obj.getId())+" order by revision desc ");		
        	if(drg5001Db!=null)		
        	{			
        		drg5001Db.setApplNo(obj.getApplNo());			
        		drg5001Db.setStatus("20"); //案件分級中(一般)		    
        		ServiceGetter.getInstance().getTcbwService().update(obj);		    
        		ServiceGetter.getInstance().getTcbwService().update(drg5001Db);		    
        		ServiceGetter.getInstance().getTcbwService().save(drg0003Db);
        		ServiceGetter.getInstance().getTcbwService().save(drg0004Db);
        	}		
        	//歷程紀錄		
        	ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"20", "審核作業 - 案件受理", user);
        	
            //發送信件通知
    		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='DRG010001'");	
    		
    		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
    		
    		String mailBody="",title="",mail=Common.get(obj.getNotifierEmail());
    		
    		if(c!=null)
    		{
    			java.util.Map<String, String> drgdpdMap = TCBWCommon.getCommonCodeMap("DRGDPD");
    			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId())+" order by id asc");
    			String mainCode ="";
    			if(objList!=null && objList.size()>0)
    			{
    				java.util.Iterator it = objList.iterator();
    								
    				while (it.hasNext()) 
    				{					
    					Drg0002Db o = (Drg0002Db) it.next();
    					mainCode += Common.get(drgdpdMap.get(o.getMainCode())+",");    					
    				}    				
    			}
    			drgdpdMap.clear();
    			if(mainCode.length()>0) mainCode = mainCode.substring(0,mainCode.length()-1);
    			
    			mailBody=c.getMailBody();
    			title=c.getTitle();			
    			
    			if(obj!=null)
    			{
    				title=title.replace("[T_案號]",obj.getApplNo());
    				mailBody=mailBody.replace("[F_案號]", obj.getApplNo());
    				mailBody=mailBody.replace("[F_產品名稱]", obj.getChProduct());
    				mailBody=mailBody.replace("[F_不良品缺陷]", mainCode);
    			}	
    			
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
    			ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null,"DRG01",Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):obj.getApplNo());
    		}
        }		
	}
	
	/**
	8.	「風險等級、通報時效、通報品質」判定規則說明：
	8.1	風險等級：
			依據藥品不良品案件分級設定作業的設定規則，等級判定優先順序為「專案→A+/A→B→C」，且件數統計需包含此次案件。
			專案案件：符合「特定藥品(許可證字號)＋不良品缺陷描述(小類)」設定規則的案件。
			A+級、A級案件(高風險)：符合「劑型＋不良品缺陷描述(小類)」設定規則的案件。
			B級案件(高頻率)：符合「相同藥品(許可證字號)＋時間區間＋同批號/不同批號＋件數」設定規則的案件。
			非屬上述分級者則為C級案件。
	8.2	通報時效：
			間隔天數＝接獲通報日期－發生日期。
			時效佳：發生日期至接獲通報日期≦14天。
	    	時效待加強：發生日期至接獲通報日期＞14天。
	8.3	通報品質：
			評估項目共4個：
			提供不良品照片。
			提供不良品缺陷詳細描述：此定義為各類缺陷中有任一類缺陷有輸入「其他」說明。
			提供不良品樣品供廠商或TFDA檢驗：此定義為[後續處理]欄位有選取「已交付廠商不良品、將交付廠商不良品、預計不交付廠商但提供TFDA調查」其中任一者。
			提供不良品原因初評。
			Excellent：4個評估項目皆有提供者。
			Good：前2個評估項目皆有提供者。
			Fair：前2個評估項目有任一未提供者。
	**/
	private Drg0003Db checkDrg03Result(Drg0001Db obj, Drg0003Db drg03Db){
		if (obj!=null) {			
			String hql = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId());
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
			String otherDesc =""; //通報品質判斷用
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				String subCodeList ="";				
				while (it.hasNext()) 
				{					
					Drg0002Db o = (Drg0002Db) it.next();
					otherDesc += Common.get(o.getOtherDescribe());
					String[] subList = o.getSubCode().split(",");
					for(int j=0; j<subList.length; j++){
						if(Common.get(subList[j]) != ""){							
							subCodeList += "defectiveProduct ="+Common.sqlChar(subList[j])+" or ";
						}
					}
				}
				if(subCodeList != null) subCodeList = subCodeList.substring(0,subCodeList.length()-3);
				
				String hql1 = " from Drg1002Db where level='01' and isStop='N' and permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
				              " and ("+subCodeList+")";
				//System.out.println("hql1==="+hql1);
				int count1 = ServiceGetter.getInstance().getCommonService().loadCount(hql1);
				if(count1 > 0){  
					drg03Db.setFirstResult("01"); //符合專案案件
				}else{
					String hql2 = " from Drg1002Db where level='02' and isStop='N' and formulation="+Common.sqlChar(obj.getMedModel())+
					              " and ("+subCodeList+")";
					//System.out.println("hql2==="+hql2);
					int count2 = ServiceGetter.getInstance().getCommonService().loadCount(hql2);
					if(count2 > 0){
						drg03Db.setFirstResult("02"); //符合A+級
					}else{
						String hql3 = " from Drg1002Db where level='03' and isStop='N' and formulation="+Common.sqlChar(obj.getMedModel())+
			              " and ("+subCodeList+")";
						//System.out.println("hql3==="+hql3);
						int count3 = ServiceGetter.getInstance().getCommonService().loadCount(hql3);
						if(count3 > 0){
							drg03Db.setFirstResult("03"); //符合A級
						}
					}
				}				
			}
			if("".equals(Common.get(drg03Db.getFirstResult()))){
				//相同藥品(許可證字號)＋時間區間＋同批號/不同批號＋件數
				boolean checkNum = false;
				String hql4 = " from Drg1002Db where level='04' and isStop='N' ";
				java.util.List drg1002List = ServiceGetter.getInstance().getCommonService().load(hql4);
				//System.out.println("hql4==="+hql4);
				if(drg1002List!=null && drg1002List.size()>0){
					java.util.Iterator drg1002 = drg1002List.iterator();					
					while (drg1002.hasNext()) 
					{					
						Drg1002Db o = (Drg1002Db) drg1002.next();
						if("Y".equals(o.getLotNum())){							
							String hql5 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
							              " and manufactorNo ="+Common.sqlChar(obj.getManufactorNo())+ 
							              " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",Common.getInt(o.getYear()),Datetime.getYYYMMDD()));
							int drg01Count = ServiceGetter.getInstance().getCommonService().loadCount(hql5);
							if(drg01Count >= Common.getInt(o.getNum())) checkNum = true;
							//System.out.println("hql5_1==="+hql5);
						}else{
							String hql5 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+				              
				                          " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",Common.getInt(o.getYear()),Datetime.getYYYMMDD()));				
							int drg01Count = ServiceGetter.getInstance().getCommonService().loadCount(hql5);				
							if(drg01Count >= Common.getInt(o.getNum())) checkNum = true;
							//System.out.println("hql5_2==="+hql5);
						}						
					}
				}
				if(checkNum) drg03Db.setFirstResult("04");  //符合B級
				else drg03Db.setFirstResult("05"); //符合C級
			}
			System.out.println("案號："+obj.getApplNo()+" 風險等級判斷結束。等級為:"+drg03Db.getFirstResult());
			String dayNum = Datetime.getDateDiff("d", obj.getOccurDate(), obj.getNotifierRevDate());
			//System.out.println("dayNum=="+dayNum);
			drg03Db.setIntervalDays(dayNum); //間隔天數
			if(Common.getInt(dayNum) <= 14)  drg03Db.setNotifierAging("1"); //時效佳
			else drg03Db.setNotifierAging("1"); //時效待加強
			System.out.println("案號："+obj.getApplNo()+" 通報時效判斷結束。時效為:"+drg03Db.getNotifierAging());			
			//檢查有無附檔			
			String hql6 = " from Con0001Db where dbName='Drg0001Db' and upLoadId="+Common.getLong(obj.getId());
			int count = ServiceGetter.getInstance().getCommonService().loadCount(hql6);			
			if(!"".equals(otherDesc) && count > 0 ){
				if("01,02,03".indexOf(Common.get(obj.getDealWith())) != -1 && !"".equals(Common.get(obj.getFirstResult()))){
					drg03Db.setNotifierQuality("1"); //通報品質Excellent
				}else{
					drg03Db.setNotifierQuality("2"); //通報品質Good
				}				
			}else{
				drg03Db.setNotifierQuality("3"); //通報品質Fair
			}
			System.out.println("案號："+obj.getApplNo()+" 通報品質判斷結束。品質為:"+drg03Db.getNotifierQuality());			
		}
		return drg03Db;
	}
	
	public void acceptedByDrgIN0101F(DRGIN0101F ref) throws Exception 
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
				java.util.List objList = load(" from Drg0001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0)
				{
					java.util.List<Drg5001Db> uList = new java.util.ArrayList<Drg5001Db>();
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					for(Object dtlObj : objList)
					{
						Drg0001Db dtl = (Drg0001Db)dtlObj;
						if("10".equals(Common.get(dtl.getStatus())) || "11".equals(Common.get(dtl.getStatus())))
						{
							//轉入內網資料
							acceptedByDrgIN0102F(dtl,ref.getUserID());
						}
						else
						{
							logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-批次受理]-[案件狀態不為審核中，不更新資料，DRG0001_DB.ID : " + dtl.getId() + "]");
						}
					}
					if(uList.size() > 0){
						updateBatch(uList);
						uList.clear();
					}
				}else{
					logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-批次受理]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}else{
			logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-批次受理]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
		
	}
	
	public void updateByDrgIN0106F(DRGIN0106F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0003Db obj = (Drg0003Db)View.getObject(" from Drg0003Db where applNo = " + Common.sqlChar(ref.getApplNo())+" order by id desc" );
        if(obj != null)
		{
        	obj.setAssessMan(ref.getUserID());
        	obj.setFirstResult(Common.get(ref.getFirstResult1()));
        	obj.setNotifierAging(Common.get(ref.getNotifierAging()));
        	obj.setNotifierQuality(Common.get(ref.getNotifierQuality()));
        	obj.setIntervalDays(Common.get(ref.getIntervalDays()));
        	obj.setRemark(Common.get(ref.getRemark()));
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);       	
        	
            //同步更新分級確認資料
            Drg0004Db obj2 = (Drg0004Db)View.getObject(" from Drg0004Db where applNo = " + Common.sqlChar(ref.getApplNo()));
            if(obj2 != null)
    		{
            	obj2.setDrgLev(Common.get(ref.getFirstResult1()));
            	obj2.setModifier(ref.getUserID());
            	obj2.setModifyDate(yyymmdd);
            	obj2.setModifyTime(hhmmss); 
            	
            	ServiceGetter.getInstance().getTcbwService().update(obj);
            	ServiceGetter.getInstance().getTcbwService().update(obj2);
    		}            
		}

	}
	
	public void backForMailByDrgIN0106F(DRGIN0106F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("23");//案件分級中(待補件)
			obj.setChargeMan(ref.getUserID());
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId())+" order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("23");//案件分級中(待補件)
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
			}
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"23", "分級作業 - 通知補件", ref.getUserID());
		}	
	}
	
	public void backPiecesByDrgIN0106F(DRGIN0106F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("22");//案件補件中
			obj.setChargeMan(ref.getUserID());
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId())+"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("22");//案件補件中				
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
				
				//新增一筆外網資料
				Drg5001Db master = new Drg5001Db();
				String[] ignoreFields = new String[]{"id", "drg5002Dbs"};
				org.springframework.beans.BeanUtils.copyProperties(drg5001Db, master, ignoreFields);
				
				//版次
				String revision = View.getLookupField(" select max(CAST(revision as int)) from Drg5001Db where drg0001Id="+Common.getLong(drg5001Db.getDrg0001Id()));
				int newRevision = Common.getInt(revision)+1;
				master.setRevision(Common.get(newRevision));
				
				java.util.Set drg5002Dbs = new ListOrderedSet();
				if(drg5001Db.getDrg5002Dbs()!=null && drg5001Db.getDrg5002Dbs().size()>0)
				{
					String[] dtlIgnoreFields = new String[]{"id"};
					for(Object dtlObj : drg5001Db.getDrg5002Dbs())
					{
						Drg5002Db oldDtl = (Drg5002Db)dtlObj;
						Drg5002Db newDtl = new Drg5002Db();					
						org.springframework.beans.BeanUtils.copyProperties(oldDtl, newDtl, dtlIgnoreFields);
						newDtl.setDrg5001Db(master);
						drg5002Dbs.add(newDtl);
					}
				}
				
				master.setDrg5002Dbs(drg5002Dbs);			
				ServiceGetter.getInstance().getTcbwService().save(master);
				
				//轉入附件(抓上版本的附件資料)
				List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and upLoadId="+drg5001Db.getId());
				if(null != con01List && !con01List.isEmpty()){
					for(Con0001Db oldcon : con01List){
						Con0001Db newcon = new Con0001Db();
						org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
						newcon.setDbName("Drg5001Db");
						newcon.setUpLoadId(master.getId());
						newcon.setIsInsert("N");
						ServiceGetter.getInstance().getCommonService().save(newcon);
					}
				}
			}		
			
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"22", "分級作業 - 退件補件", ref.getUserID());
		}	
	}
	
	public void doAccessByDrgIN0106F(DRGIN0106F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("30");//分級確認中
			obj.setChargeMan(TCBWCommon.getUserID("DRG01", "03", "Drg0001Db"));
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("30");//分級確認中
				
				Drg0003Db drg0003Db = (Drg0003Db)View.getObject(" from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo()) +"order by id desc");
				if(drg0003Db!=null)
				{
					String yyymmdd = Datetime.getYYYMMDD();
					drg0003Db.setAssessDate(yyymmdd);
					drg0003Db.setAssessMan(ref.getUserID());
					
					ServiceGetter.getInstance().getTcbwService().update(drg0003Db);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
					ServiceGetter.getInstance().getTcbwService().update(obj);
					
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"30", "分級作業 - 初評完成", ref.getUserID());
				}		
			}			
		}	
	}
	
	public void doPostDocByDrgIN0106F(DRGIN0106F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("90");//結案
			obj.setChargeMan(""); //清除作業人員
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("90");//結案									
				
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
				
				//結案後產生檔案
	    		if("90".equals(obj.getStatus())){
					closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
	    		}
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"90", "分級作業 - 案件發文(結案)", ref.getUserID());					
			}			
		}	
	}
	
	public void updateByDrgIN0109F(DRGIN0109F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0004Db obj = (Drg0004Db)View.getObject(" from Drg0004Db where applNo = " + Common.sqlChar(ref.getApplNo()));
        if(obj != null)
		{
        	obj.setDrgLev(Common.get(ref.getDrgLev()));
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
        	
        	ServiceGetter.getInstance().getTcbwService().update(obj);
		}

	}
	
	public void gradeByDrgIN0109F(DRGIN0109F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			Drg0004Db drg0004Db = (Drg0004Db)View.getObject(" from Drg0004Db where applNo="+Common.sqlChar(obj.getApplNo()));
			if(drg0004Db != null){
				drg0004Db.setGradeDate(yyymmdd);
				drg0004Db.setGradeMan(ref.getUserID());
				drg0004Db.setModifier(ref.getUserID());
				drg0004Db.setModifyDate(yyymmdd);
				drg0004Db.setModifyTime(hhmmss);
				//C級 & 專案			
				if("01".equals(drg0004Db.getDrgLev()) || "05".equals(drg0004Db.getDrgLev())){
					//check有無廠商信箱
					String mail ="";
					java.util.List objList = load(" from Con1006Db where con1005Db.compegno="+Common.sqlChar(obj.getApplicationID()));
					if(objList!=null && objList.size()>0)
					{
						for(Object dtlObj : objList)
						{
							Con1006Db dtl = (Con1006Db)dtlObj;
							mail += dtl.getCommonUser().getUserEmail()+",";
						}
					}
					
					if("".equals(Common.get(mail))){				
						obj.setStatus("25");//專案待發文
						String chargeMan = View.getLookupField(" select assessMan from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc");
						obj.setChargeMan(chargeMan); //取最新一筆初評人員	
			        	obj.setModifier(ref.getUserID());
			        	obj.setModifyDate(yyymmdd);
			        	obj.setModifyTime(hhmmss);

						ServiceGetter.getInstance().getTcbwService().update(drg0004Db);
						ServiceGetter.getInstance().getTcbwService().update(obj);
						
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"25", "分級確認作業 - 確認完成(C級,專案)", ref.getUserID());
					}else{						
						obj.setStatus("90");//結案
						obj.setChargeMan(""); //清空作業人員
			        	obj.setModifier(ref.getUserID());
			        	obj.setModifyDate(yyymmdd);
			        	obj.setModifyTime(hhmmss);
						
						ServiceGetter.getInstance().getTcbwService().update(drg0004Db);
						ServiceGetter.getInstance().getTcbwService().update(obj);
												
						//結案後產生檔案
			    		if("90".equals(obj.getStatus())){
							closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			    		}
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"90", "分級確認作業 - 確認完成(C級,專案)", ref.getUserID());
						
						//發送信件通知
			    		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='DRG010005'");	
			    		
			    		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			    		
			    		String mailBody="",title="";
			    		mail = mail.substring(0,mail.length()-1);
			    		
			    		if(c!=null)
			    		{
			    			mailBody=c.getMailBody();
			    			title=c.getTitle();		    			
			    			
			    			String notdate = "";
			    			if(obj.getNotifierRepDate()!=null)  
			    				notdate = obj.getNotifierRepDate().substring(0,3)+"年"+obj.getNotifierRepDate().substring(3,5)+"月"+obj.getNotifierRepDate().substring(5,7)+"日";
			    			
			    			//帶入相關欄位
			    			title=title.replace("[T_案號]",obj.getApplNo());
			    			title=title.replace("[T_英文品名]",obj.getEnProduct());
			    			title=title.replace("[T_中文品名]",obj.getChProduct());
			    			mailBody=mailBody.replace("[F_接獲通報日期]", notdate);
			    			mailBody=mailBody.replace("[F_案號]", obj.getApplNo());			    				
			    			
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
			    			ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null,"DRG01",Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):obj.getApplNo());
			    		}
					}			
				}
				//A+,A,B級
				else{
					obj.setStatus("40");//案件評估中(待發文)
					//作業人員自動分派
					obj.setChargeMan(TCBWCommon.getUserID("DRG01", "04", "Drg0001Db"));
		        	obj.setModifier(ref.getUserID());
		        	obj.setModifyDate(yyymmdd);
		        	obj.setModifyTime(hhmmss);
		        	
		        	//B級才需新增(因為有拉回機制，維持Drg0005Db 為單筆架構)
		        	if("04".equals(drg0004Db.getDrgLev())){
		        		Drg0005Db drg0005Db = (Drg0005Db) getObject(" from Drg0005Db where applNo="+Common.sqlChar(obj.getApplNo()));
		        		if(drg0005Db != null){
		        			drg0005Db.setReplyDate(null);
		        			drg0005Db.setCreator(ref.getUserID());		        	
			        		drg0005Db.setCreateDate(yyymmdd);	        	
			        		drg0005Db.setCreateTime(hhmmss);
		        		}else{
		        			//新增藥品不良品廠商回覆(CAPA確認)		        	
			        		drg0005Db = new Drg0005Db();		        	
			        		drg0005Db.setApplNo(obj.getApplNo());
			        		drg0005Db.setLotNo(obj.getManufactorNo());
			        		drg0005Db.setCreator(ref.getUserID());		        	
			        		drg0005Db.setCreateDate(yyymmdd);	        	
			        		drg0005Db.setCreateTime(hhmmss);			        				        		
		        		}
		        		ServiceGetter.getInstance().getTcbwService().saveOrUpdate(drg0005Db);
		        	}
		        	
		        	//新增藥品不良品廠商回覆分析資料檔(因為有拉回機制，維持Drg0006Db 為單筆架構)
		        	Drg0006Db drg0006Db = (Drg0006Db) getObject(" from Drg0006Db where applNo="+Common.sqlChar(obj.getApplNo()));
		        	if(drg0006Db != null){
		        		drg0006Db.setAnalyDate(null);
		        		drg0006Db.setCreator(ref.getUserID());        
			        	drg0006Db.setCreateDate(yyymmdd);        
			        	drg0006Db.setCreateTime(hhmmss);
		        	}else{
		        		drg0006Db = new Drg0006Db();
			        	drg0006Db.setApplNo(obj.getApplNo());
			        	drg0006Db.setCreator(ref.getUserID());        
			        	drg0006Db.setCreateDate(yyymmdd);        
			        	drg0006Db.setCreateTime(hhmmss);
		        	}		        	
		        	
		        	//新增藥品不良品廠商回覆評估表資料檔
		        	Drg0007Db drg0007Db = (Drg0007Db) getObject(" from Drg0007Db where applNo="+Common.sqlChar(obj.getApplNo())+" and assessDate is null");
		        	if(drg0007Db != null){
		        		//do Nothing ...
		        	}else{
		        		drg0007Db = new Drg0007Db();
			        	drg0007Db.setApplNo(obj.getApplNo());
			        	drg0007Db.setCreator(ref.getUserID());        
			        	drg0007Db.setCreateDate(yyymmdd);        
			        	drg0007Db.setCreateTime(hhmmss);
		        	}		        	
		        	
		        	//新增案件評估資料檔
		        	Drg0008Db drg0008Db = (Drg0008Db) getObject(" from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
		        	if(drg0008Db != null){
		        		drg0008Db.setAssessDate(null);
		        		drg0008Db.setCreator(ref.getUserID());        
			        	drg0008Db.setCreateDate(yyymmdd);        
			        	drg0008Db.setCreateTime(hhmmss);
		        	}else{
		        		drg0008Db = new Drg0008Db();
			        	drg0008Db.setApplNo(obj.getApplNo());
			        	drg0008Db.setCreator(ref.getUserID());        
			        	drg0008Db.setCreateDate(yyymmdd);        
			        	drg0008Db.setCreateTime(hhmmss);
		        	}		        	
		        	
		        	ServiceGetter.getInstance().getTcbwService().saveOrUpdate(drg0006Db);
		        	ServiceGetter.getInstance().getTcbwService().saveOrUpdate(drg0007Db);
		        	ServiceGetter.getInstance().getTcbwService().saveOrUpdate(drg0008Db);
		        	ServiceGetter.getInstance().getTcbwService().update(drg0004Db);
					ServiceGetter.getInstance().getTcbwService().update(obj);
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"40", "分級確認作業 - 確認完成(A+,A,B級)", ref.getUserID());					
				}
				
			}
		}

	}
	
	public void backAccessByDrgIN0109F(DRGIN0109F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("21");//案件分級中(TFDA退件)
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("21");//案件分級中(TFDA退件)
				drg5001Db.setModifier(ref.getUserID());
				drg5001Db.setModifyDate(yyymmdd);
				drg5001Db.setModifyTime(hhmmss);
				
				Drg0003Db drg0003Db = (Drg0003Db)View.getObject(" from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo()) +"order by id desc");
				if(drg0003Db!=null)
				{
					//新增一筆Drg0003Db
					Drg0003Db master = new Drg0003Db();
					master.setApplNo(drg0003Db.getApplNo());
					master.setAssessMan(drg0003Db.getAssessMan());
					master.setFirstResult(drg0003Db.getFirstResult());
					master.setNotifierAging(drg0003Db.getNotifierAging());
					master.setNotifierQuality(drg0003Db.getNotifierQuality());
					master.setIntervalDays(drg0003Db.getIntervalDays());
					master.setCreator(ref.getUserID());        
					master.setCreateDate(yyymmdd);        
					master.setCreateTime(hhmmss);
			
					obj.setChargeMan(drg0003Db.getAssessMan()); //[作業人員]＝之前案件分級作業的作業人員
					
					ServiceGetter.getInstance().getTcbwService().save(master);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
					ServiceGetter.getInstance().getTcbwService().update(obj);
					
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"21", "分級確認作業 - 退件", ref.getUserID());
				}		
			}			
		}
	}

	public void updateByDrgIN0112F(DRGIN0112F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		////修改主檔chargemen為目前登入角色
		Drg0001Db drg0001db = (Drg0001Db)View.getObject(" from Drg0001Db where applNo = " + Common.sqlChar((ref.getApplNo())));
		if(drg0001db != null)
		{
			drg0001db.setChargeMan(Common.get(ref.getUserID()));
        	ServiceGetter.getInstance().getTcbwService().update(drg0001db);
		}
		
		Drg0008Db obj = (Drg0008Db)View.getObject(" from Drg0008Db where applNo = " + Common.sqlChar(ref.getApplNo()));
        if(obj != null)
		{        	
        	if("40".equals(Common.get(ref.getStatus()))){
        		obj.setIsPostYn(Common.get(ref.getIsPostYn08())); //是否發文
        		obj.setReason(Common.get(ref.getReason08()));  //不發文理由
        		obj.setFdaPostNo(Common.get(ref.getFdaPostNo08())); //FDA發文字號
        		obj.setPayDate(Common.get(ref.getPayDate08())); //交付CAPA日期
        	}
        	else{
        		obj.setDelayDate(Common.get(ref.getDelayDate08())); //展延日期        	
        		obj.setDrgLev(Common.get(ref.getDrgLev08())); //風險等級(評估)
        		obj.setCapaDownDate(Common.get(ref.getCapaDownDate08())); //CAPA執行完成日期
        		obj.setDrgReason(Common.get(ref.getDrgReason08())); //本案原由
        		obj.setCheckResult(Common.get(ref.getCheckResult08())); //清查結果
        		obj.setSurvey(Common.get(ref.getSurvey08())); //調查結果
        		obj.setPrecaution(Common.get(ref.getPrecaution08())); //預防矯正措施及改善時程
        		String dealWithStr ="";
    			if(ref.getDealWith08() != null  && ref.getDealWith08().length > 0){			
    				for(int i=0;i<ref.getDealWith08().length;i++){							
    					if(dealWithStr.length() > 0){
    						dealWithStr += ",";
    					}
    					dealWithStr += ref.getDealWith08()[i];
    				}
    			}
        		obj.setDealWith(Common.get(dealWithStr)); //擬辦事項
        		obj.setAssessResult(Common.get(ref.getAssessResult08())); //評估結果
        	}
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
        	
        	ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	public void doPostNoByDrgIN0112F(DRGIN0112F ref) throws Exception 
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("41");//案件評估中(交付中)
			obj.setChargeMan(Common.get(ref.getUserID())); //作業人員＝目前登入人員
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("41");//案件評估中(交付中)								
				
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
					
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"41", "評估作業 - 發文", ref.getUserID());					
			}			
		}	
	}
	
	public void doAssessByDrgIN0112F(DRGIN0112F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("50");//案件分析中
			obj.setChargeMan(TCBWCommon.getUserID("DRG01", "05", "Drg0001Db"));
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("50");//案件分析中
				
				Drg0008Db drg0008Db = (Drg0008Db)View.getObject(" from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
				if(drg0008Db!=null)
				{
					drg0008Db.setAssessDate(yyymmdd);
				    drg0008Db.setAssessMan(ref.getUserID());
				    drg0008Db.setModifier(ref.getUserID());
				    drg0008Db.setModifyDate(yyymmdd);
				    drg0008Db.setModifyTime(hhmmss);
				    
				    Drg0006Db drg0006Db = (Drg0006Db)View.getObject(" from Drg0006Db where applNo="+Common.sqlChar(obj.getApplNo()));
					if(drg0006Db!=null)
					{
						//新增案件分析資料(帶入CAPA廠商分析)
						Drg0009Db master = new Drg0009Db();
						master.setApplNo(obj.getApplNo());
						master.setMedicineType(Common.get(drg0006Db.getMedicineType()));	//學名藥/原廠藥
						master.setProduceType(Common.get(drg0006Db.getProduceType()));	//國產/輸入
						master.setLotType(Common.get(drg0006Db.getLotType()));	//批號範圍
						master.setDefect(Common.get(drg0006Db.getDefect()));	//不良品缺陷
						master.setDefectOther(Common.get(drg0006Db.getDefectOther()));	//不良品缺陷(其他)
						master.setSurvey(Common.get(drg0006Db.getSurvey()));	//調查結果
						master.setSurveyOther(Common.get(drg0006Db.getSurveyOther())); //調查結果(其他)
						master.setPrecaution(Common.get(drg0006Db.getPrecaution()));	//預防措施
						master.setPrecautionOther(Common.get(drg0006Db.getPrecautionOther()));	//預防措施(其他)
						master.setCreator(ref.getUserID());        
						master.setCreateDate(yyymmdd);        
						master.setCreateTime(hhmmss);
						
						ServiceGetter.getInstance().getTcbwService().save(master);
						ServiceGetter.getInstance().getTcbwService().update(drg0008Db);
						ServiceGetter.getInstance().getTcbwService().update(drg5001Db);			
						ServiceGetter.getInstance().getTcbwService().update(obj);			
					
						//歷程紀錄				
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"50", "評估作業 - 評估完成", ref.getUserID());
					}
				}
			}			
		}	
	}
	
	public void doBackByDrgIN0112F(DRGIN0112F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("41");//案件評估中(交付中)
			obj.setChargeMan(Common.get(ref.getUserID())); //作業人員＝目前登入人員
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("41");//案件評估中(交付中)
				
				Drg0008Db drg0008Db = (Drg0008Db)View.getObject(" from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
				if(drg0008Db!=null)
				{
					drg0008Db.setAddDocDate(yyymmdd);
				    
					//新增一筆CAPA評估表
					Drg0007Db drg07 = new Drg0007Db();
					drg07.setApplNo(Common.get(obj.getApplNo()));
					drg07.setCreator(ref.getUserID());        
					drg07.setCreateDate(yyymmdd);        
					drg07.setCreateTime(hhmmss);
				
					ServiceGetter.getInstance().getTcbwService().save(drg07);
					ServiceGetter.getInstance().getTcbwService().update(drg0008Db);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);		
					ServiceGetter.getInstance().getTcbwService().update(obj);			
				
					//歷程紀錄				
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"41", "評估作業 - 通知廠商補件", ref.getUserID());
				}
			}			
		}	
	}
	
	public void updateByDrgIN0114F(DRGIN0114F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		//修改主檔chargemen為目前登入角色
		Drg0001Db drg0001db = (Drg0001Db)View.getObject(" from Drg0001Db where applNo = " + Common.sqlChar((ref.getApplNo())));
		if(drg0001db != null)
		{
			drg0001db.setChargeMan(Common.get(ref.getUserID()));
        	ServiceGetter.getInstance().getTcbwService().update(drg0001db);
		}
		
		Drg0009Db obj = (Drg0009Db)View.getObject(" from Drg0009Db where applNo = " + Common.sqlChar(ref.getApplNo()));
        if(obj != null)
		{        	
        	obj.setMedicineType(Common.get(ref.getMedicineType09()));	//學名藥/原廠藥
        	obj.setProduceType(Common.get(ref.getProduceType09()));	//國產/輸入
        	obj.setLotType(Common.get(ref.getLotType09()));	//批號範圍
        	obj.setDefect(Common.get(ref.getDefect09()));	//不良品缺陷
        	obj.setDefectOther(Common.get(ref.getDefectOther09()));	//不良品缺陷(其他)
        	obj.setSurvey(Common.get(ref.getSurvey09()));	//調查結果
        	obj.setSurveyOther(Common.get(ref.getSurveyOther09())); //調查結果(其他)
			obj.setPrecaution(Common.get(ref.getPrecaution09()));	//預防措施
			obj.setPrecautionOther(Common.get(ref.getPrecautionOther09()));	//預防措施(其他)
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
        	
        	ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}
	
	public void doAnalyByDrgIN0114F(DRGIN0114F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("90"); //結案
			obj.setChargeMan(""); //清空作業人員
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("90"); //結案
				
				Drg0009Db drg0009Db = (Drg0009Db)View.getObject(" from Drg0009Db where applNo="+Common.sqlChar(obj.getApplNo()));
				if(drg0009Db!=null)
				{
					drg0009Db.setAnalyDate(yyymmdd);
				    drg0009Db.setAnalyMan(ref.getUserID());
				    drg0009Db.setModifier(ref.getUserID());
				    drg0009Db.setModifyDate(yyymmdd);
				    drg0009Db.setModifyTime(hhmmss);
						
         			ServiceGetter.getInstance().getTcbwService().update(drg0009Db);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);			
					ServiceGetter.getInstance().getTcbwService().update(obj);			
					
					//結案後產生檔案
		    		if("90".equals(obj.getStatus())){
						closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
		    		}
					//歷程紀錄				
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"90", "分析作業 - 分析完成", ref.getUserID());
					
				}
			}			
		}	
	}
	
	public void doDeleteCon0003Db(Drg0001Db obj)
	{
	  String hql = "from Con0003Db where systemType='DRG' ";
             hql +=" and formCode='DRG01' ";
             hql +=" and dbID=" + Common.sqlChar(Common.get(obj.getId()));

             Con0003Db con0003Db = (Con0003Db)View.getObject(hql);	

       if (con0003Db != null) 
       { 
		 ServiceGetter.getInstance().getTcbwService().delete(con0003Db);
       }

	}
	
	public String chkIsCouncilYnByDrg44(Drg4001Db drg41, Drg4004Db drg44, String category){
		String isCouncilYn = "N";
		String permitKeyNo = "";
		//1.先查詢是否有符合NTI或不良反應等級的條件
		String hql = " from Drg1003Db where category = " + Common.sqlChar(category);
		if("PHY".equals(category)){
			hql += "  and (nti is null or nti = '' or nti=  " + Common.sqlChar(drg44.getMedNti())+")";
		}else if("ADV".equals(category)){
			hql += "  and (advLevel is null or advLevel = '' or advLevel=  " + Common.sqlChar(drg44.getBadReactionLev())+")";
		}
			
		java.util.List<Drg1003Db> drg13List = ServiceGetter.getInstance().getCommonService().load(hql);
		if(null != drg13List && !drg13List.isEmpty()){
			//2.取得懷疑療效不等案件資料
			if(null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
				for(Object dtlObj:drg41.getDrg4003Dbs()){
					Drg4003Db drg43 = (Drg4003Db)dtlObj;
					if("02".equals(drg43.getMedType())){
						permitKeyNo = drg43.getPermitKey()+ drg43.getPermitNo();
					}
				}
			}
			for(Drg1003Db drg13:drg13List){
				String notifierDateS = Datetime.getDateAdd("y", Common.getInt("-"+drg13.getYear()), drg41.getNotifierRepDate());
				if(null != notifierDateS && !"".equals(notifierDateS)){
					//2.查詢案件區間
					String hqls = " from Drg4001Db where notifierRepDate >= " + Common.sqlChar(notifierDateS) + " and notifierRepDate <=" + Common.sqlChar(drg41.getNotifierRepDate());
					//3.查詢是否(不)同批號
					if(null != drg13.getLotNum() && !"".equals(drg13.getLotNum())){
						hqls += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and permitKey+permitNo ";
						if("Y".equals(drg13.getLotNum())){
							hqls += " = "+Common.sqlChar(permitKeyNo);
						}else if("N".equals(drg13.getLotNum())){
							hqls += " <> "+Common.sqlChar(permitKeyNo);
						}
						hqls += ")";
					}
					int count = ServiceGetter.getInstance().getCommonService().loadCount(hqls);
					if(count >= Common.getInt(drg13.getNum())){
						isCouncilYn = "Y";
					}
				}
			}
		}
		if(!"Y".equals(isCouncilYn) && !"ADV".equals(category)){
			//1.查詢是否有符合不良反應等級的條件
			isCouncilYn = ServiceGetter.getInstance().getTcbwService().getDrginDao().chkIsCouncilYnByDrg44(drg41, drg44, "ADV");
		}
		return isCouncilYn;
	}
	
	//產生備份PDF
	public void closedPrint(String applNo,String id,String userID,String status) throws Exception 
	{
		DRGEX0102F drgex0102f=new DRGEX0102F();
		
		if(!"".equals(applNo)) {
			if("90".equals(status)) {
				drgex0102f.setApplNo(applNo);
			} else if("03".equals(status)) {
				drgex0102f.setId(applNo);
			}
		}
		
		
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
		
//		drgin0102q.setParameter(parms);
	    
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		javax.swing.table.DefaultTableModel model = drgex0102f.getDefaultTableModel();

		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0101r.jasper"));
		env.setFormat("PDF");	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	    
	    String drg = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("DRG");
	    
	    String fileName="";//檔案名稱
	    String fileDir="DRG010001";//存放資料夾
	    
	    if(!"".equals(applNo)) {
			if("90".equals(status)) {
				fileName=applNo;
			} else if("03".equals(status)) {
				fileName=id;
			}
		}
	    
	    
//	    if(!"".equals(applNo))fileName=applNo;
	   
	    File meddir = new File(drg);
	    //判斷資料夾是否存在，若不存在則建立
	    if(!meddir.isDirectory())
	    {
	    	meddir.mkdir();
	    }	
	    
	    
	    
	    File dir = new File(drg+"\\"+fileDir+"\\");
	    //判斷資料夾是否存在，若不存在則建立
	    if(!dir.isDirectory())
	    {
	    	dir.mkdir();
	    }	
	  
	    File output = new File(drg+"\\"+fileDir+"\\"+fileName+".pdf");
	    //產生檔案存放
	    generator.reportToFile(output, parms);
	    
	    Con0001Db o = new Con0001Db();
	    
	    o.setFileKind("DRG");
	    o.setUpLoadId(Common.getLong(id));
	    o.setFileRoute(fileDir);
	    o.setFileName(fileName+".pdf");
	    o.setFileExplan("藥品不良品通報備查PDF");
	    o.setIsInsert("N");
	    o.setIsDelete("N");
	    o.setSystemType("DRG010001");
	    o.setDbName("Drg0001Db");
	    o.setCreator(userID);
	    o.setCreateDate(Datetime.getYYYMMDD());
	    o.setCreateTime(Datetime.getHHMMSS());

	    ServiceGetter.getInstance().getCommonService().save(o);

	    env.clear();

	}
	
	public void closedCaseToPDFByDrg(Drg4001Db drg41){
		List drg41TmpList = new ArrayList();
		try {
			if(null != drg41){
				//1. 查詢外部最新案件
				Drg6001Db drg61 = (Drg6001Db)ServiceGetter.getInstance().getCommonService().getObject(" from Drg6001Db where drg4001Id = " + drg41.getId()+" order by revision desc ");
				if(null != drg61){
					//2. 轉成DRG4001
					Drg4001Db drg41Tmp = (Drg4001Db) ServiceGetter.getInstance().getTcbwService().getDrgexDao().setFileValue(new Drg4001Db(), drg61);
					if(null != drg61.getDrg6002Dbs() && !drg61.getDrg6002Dbs().isEmpty()){
						Set dtlSet = new ListOrderedSet();
						for(Object dtlObj:drg61.getDrg6002Dbs()){
							Drg4002Db drg42 = (Drg4002Db) ServiceGetter.getInstance().getTcbwService().getDrgexDao().setFileValue(new Drg4002Db(), dtlObj);
							drg42.setDrg4001Db(drg41Tmp);
							dtlSet.add(drg42);
						}
						drg41Tmp.setDrg4002Dbs(dtlSet);
					}
					
					if(null != drg61.getDrg6003Dbs() && !drg61.getDrg6003Dbs().isEmpty()){
						Set dtlSet = new ListOrderedSet();
						for(Object dtlObj:drg61.getDrg6003Dbs()){
							Drg4003Db drg43 = (Drg4003Db) ServiceGetter.getInstance().getTcbwService().getDrgexDao().setFileValue(new Drg4003Db(), dtlObj);
							drg43.setId(null);
							drg43.setDrg4001Db(drg41Tmp);
							dtlSet.add(drg43);
						}
						drg41Tmp.setDrg4003Dbs(dtlSet);
					}
					
					//3. 組成PDF
					if(null != drg41Tmp){
						drg41TmpList.add(drg41Tmp);
						
						DRGIN0302F DRGIN0302F = new DRGIN0302F();
						TableModelReportEnvironment env = new TableModelReportEnvironment();
						java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
						ServletContext webContextInfo = ServiceGetter.getInstance().getMyServletContext().getServletContext();
						
						DefaultTableModel model = DRGIN0302F.getDefaultTableModel(drg41TmpList);
						if(null != model){
							env.setTableModel(model);
							env.setHtmlImagePattern(Common.getReportImageCachePath());
							env.setJasperFile(webContextInfo.getRealPath("/report/drg/DRGIN0302tir.jasper"));
							env.setFormat(ReportEnvironment.VAL_FORMAT_PDF);	
							DRGIN0302F.setParameter(parms);
							//4. 儲存產製的報表檔案
							ServletContext application = ServiceGetter.getInstance().getMyServletContext().getServletContext();
							
							//建立資料夾
						    //先執行資安檢測
						    String systemType = Common.isValidChildFilePath("DRG020001");
						    File tempDirectory = new File(application.getInitParameter("DRG"));
						    tempDirectory = new File(tempDirectory,systemType);                
						    tempDirectory.mkdirs();
						       
						    //建立亂碼資料夾
						    String uploadCaseID = new java.rmi.dgc.VMID().toString();
						    uploadCaseID = uploadCaseID.replaceAll(":","_");
						       
						    tempDirectory = new File(tempDirectory,uploadCaseID);                
						    tempDirectory.mkdirs();  
						       
							String saveDirectory = application.getInitParameter("DRG")+"/"+systemType+"/"+uploadCaseID;
							
							
							
							if(!"".equals(drg41.getApplNo())) {
								saveDirectory+="/"+drg41.getApplNo()+"." + ReportEnvironment.VAL_FORMAT_PDF;
							} else {
								saveDirectory+="/"+drg61.getId()+"." + ReportEnvironment.VAL_FORMAT_PDF;
							}
							
							java.io.File outputFile = new java.io.File(saveDirectory);
							
//						    java.io.File outputFile = new java.io.File(saveDirectory+"/"+drg41.getApplNo()+"." + ReportEnvironment.VAL_FORMAT_PDF);
						    
							TableModelReportGenerator generator = new TableModelReportGenerator(env);							   
							try {
								generator.reportToFile(outputFile , parms);
							} catch (Exception e) {
								e.printStackTrace();
							} finally{
								//5.存入附件檔
								Con0001Db con0001 = new Con0001Db();
								con0001.setFileKind("DRG");
								con0001.setSystemType(systemType);
								con0001.setUpLoadId(Long.valueOf(drg41.getId()));
								con0001.setFileRoute(systemType+":;:"+uploadCaseID);
								if(!"".equals(drg41.getApplNo())) {
									con0001.setFileName(drg41.getApplNo()+"." + ReportEnvironment.VAL_FORMAT_PDF);
								} else {
									con0001.setFileName(drg61.getId()+"." + ReportEnvironment.VAL_FORMAT_PDF);
								}
								
								con0001.setFileExplan("藥品療效不等通報備查PDF");//檔案說明
								con0001.setDbName("Drg4001Db");
								con0001.setCreator(TCBWCommon.getCurrentUserId());
								con0001.setIsInsert("Y");
								con0001.setCreateDate(Datetime.getYYYMMDD());
								con0001.setCreateTime(Datetime.getHHMMSS());
								ServiceGetter.getInstance().getCommonService().save(con0001);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doCorrectionByDrgIN0112F(DRGIN0112F ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("21");//案件分級中(TFDA退件)
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("21");//案件分級中(TFDA退件)
				drg5001Db.setModifier(ref.getUserID());
				drg5001Db.setModifyDate(yyymmdd);
				drg5001Db.setModifyTime(hhmmss);
				
				Drg0003Db drg0003Db = (Drg0003Db)View.getObject(" from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo()) +"order by id desc");
				if(drg0003Db!=null)
				{
					//新增一筆Drg0003Db
					Drg0003Db master = new Drg0003Db();
					master.setApplNo(drg0003Db.getApplNo());
					master.setAssessMan(drg0003Db.getAssessMan());
					master.setFirstResult(drg0003Db.getFirstResult());
					master.setNotifierAging(drg0003Db.getNotifierAging());
					master.setNotifierQuality(drg0003Db.getNotifierQuality());
					master.setIntervalDays(drg0003Db.getIntervalDays());
					master.setCreator(ref.getUserID());        
					master.setCreateDate(yyymmdd);        
					master.setCreateTime(hhmmss);
			
					obj.setChargeMan(drg0003Db.getAssessMan()); //[作業人員]＝之前案件分級作業的作業人員
					
					ServiceGetter.getInstance().getTcbwService().save(master);
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
					ServiceGetter.getInstance().getTcbwService().update(obj);					
					
					//發送信件通知給之前案件分級作業的作業人員
					String userMail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(drg0003Db.getAssessMan()));
					if(!"".equals(Common.get(userMail))){					
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();		    	    
						javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();		    		
						p.setAddress(userMail);		    		
						mailList.add(p);  		
		    		
						String mailBody="",title="";	    		
						mailBody="案件編號:"+drg0003Db.getApplNo()+"重新進行校正，校正理由:"+ref.getCorrectionReason();		    		
						title="案件編號:"+drg0003Db.getApplNo()+"重新校正通知";  		
		    		
						ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true, null, null, null,"DRG01",Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):obj.getApplNo());		    		
					}
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"21", "案件評估作業 - 重新校正,重新校正理由:"+ref.getCorrectionReason(), ref.getUserID());
				}		
			}			
		}
	}
	
	public void doReAssessByDrgIN0102Q(DRGIN0102Q ref) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("43");//案件評估中(再評估)
        	obj.setModifier(ref.getUserID());
        	obj.setModifyDate(yyymmdd);
        	obj.setModifyTime(hhmmss);
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("43");//案件評估中(再評估)
				drg5001Db.setModifier(ref.getUserID());
				drg5001Db.setModifyDate(yyymmdd);
				drg5001Db.setModifyTime(hhmmss);				
				
				
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
				
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"43", "案件查詢作業 - 重新評估,重新評估理由:"+ref.getCorrectionReason(), ref.getUserID());
			}
		}
	}
	
	public void doReAssessByDrgIN0112F(DRGIN0112F ref) throws Exception 
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getId()));
		if(obj!=null)
		{
			obj.setStatus("90");//結案
			obj.setChargeMan(""); //清空作業人員
			
			Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getId()) +"order by id desc");
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("90");//結案
				
				Drg0008Db drg0008Db = (Drg0008Db)View.getObject(" from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
				if(drg0008Db!=null)
				{
					drg0008Db.setReAssessDate(yyymmdd);
				    drg0008Db.setReAssessMan(ref.getUserID());
				    drg0008Db.setModifier(ref.getUserID());
				    drg0008Db.setModifyDate(yyymmdd);
				    drg0008Db.setModifyTime(hhmmss);			    		
					
				    //結案後產生檔案
		    		if("90".equals(obj.getStatus())){
						closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
		    		}
				    //歷程紀錄				
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"90", "評估作業 - 再評估完成", ref.getUserID());				
				}
			}			
		}	
	}
}
