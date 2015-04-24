package com.kangdainfo.tcbw.model.dao.hibernate;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6002Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.dao.DrgexDao;

import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgex.DRGEX0102F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0105F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0107F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0109F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0302F;
import com.kangdainfo.tcbw.view.medex.MEDEX5102F;



public class DrgexDaoImpl extends BaseDaoImpl implements DrgexDao{

	@Override
	public void updateByDrgEX0102F(DRGEX0102F ref) throws Exception 
	{
		java.util.List<Drg5002Db> saveListDrg5002Db = new java.util.ArrayList<Drg5002Db>();	
		java.util.List<Drg5002Db> updateListDrg5002Db = new java.util.ArrayList<Drg5002Db>();
		java.util.List<Drg5002Db> deleteListDrg5002Db = new java.util.ArrayList<Drg5002Db>();
		java.util.List<Drg5002Db> drg5002DbList =null ;
		java.util.List<Drg5002Db> drg5002DbList3 =null ;
		java.util.Map<String,Drg5002Db> drg5002DbMap=null;
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg5001Db obj = (Drg5001Db)View.getObject(" from Drg5001Db where id = " + Common.getLong(ref.getId()));

        if(obj != null)
		{
        	//基本資料------------------------------------------------------------------
			obj.setOccurDate(Common.get(ref.getOccurDate()));		
			obj.setNotifierRevDate(Common.get(ref.getNotifierRevDate()));
			obj.setNotifierSource(Common.get(ref.getNotifierSource()));

			obj.setNotifierName(Common.get(ref.getNotifierName()));
			if("I".equals(ref.getIsInOrOutPerson())) obj.setNotifierUserID(Common.get(ref.getNotifierUserID()));			
			obj.setNotifierDept(Common.get(ref.getNotifierDept()));
			obj.setNotifierDeptID(Common.get(ref.getNotifierDeptID()));
			obj.setNotifierTel(Common.get(ref.getNotifierTel()));
			obj.setNotifierTelArea(Common.get(ref.getNotifierTelArea()));	
			obj.setNotifierTelExt(Common.get(ref.getNotifierTelExt()));	
			obj.setNotifierCounty(Common.get(ref.getNotifierCounty()));	
			obj.setNotifierZipCode(Common.get(ref.getNotifierZipCode()));	
			obj.setNotifierAddress(Common.get(ref.getNotifierAddress()));		
			obj.setNotifierEmail(Common.get(ref.getNotifierEmail()));		
			obj.setNotifierType(Common.get(ref.getNotifierType()));
			obj.setNotifierTitle(Common.get(ref.getNotifierTitle()));
			
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
			obj.setManufactorCountry(Common.get(ref.getManufactorCountry()));
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
			obj.setDefectDesc(Common.get(ref.getDefectDesc()));
			obj.setFirstResult(Common.get(ref.getFirstResult()));
			obj.setFirstRemark(Common.get(ref.getFirstRemark()));			
			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			if(null != ref.getMainCode()){
				Map<String, String> subMap = new HashMap<String, String>();
				drg5002DbList = ServiceGetter.getInstance().getCommonService().load("from Drg5002Db where drg5001Db.id="+Common.getLong(ref.getId()));	
				drg5002DbMap= new java.util.HashMap<String,Drg5002Db>();
				for(Drg5002Db obj1:drg5002DbList){
					drg5002DbMap.put(Common.get(obj1.getMainCode()),obj1);
				}
				if(drg5002DbList!=null) drg5002DbList.clear();
				
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
						Drg5002Db drg52 = drg5002DbMap.get(mainCode);
						if(null == drg52){
							drg52 = new Drg5002Db();
							drg52.setDrg5001Db(obj);
						}
						//主代碼				
						drg52.setMainCode(Common.get(mainCode));
						//子代碼						
						drg52.setSubCode(subMap.get(mainCode));			                     
						//說明描述
						java.util.List codeList = ServiceGetter.getInstance().getCommonService().load("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' and isStop='N' order by codeId");
						if (codeList!=null && codeList.size()>0) {
							for(int i=0 ; i< codeList.size();i++){
								Object[] o = (Object[]) codeList.get(i);
								if(Common.get(o[0]).equals(mainCode)){
									drg52.setOtherDescribe(Common.get(ref.getOtherDescribe()[i]));
									break;
								}
							}
						}						
						if (null != drg52.getId() && drg52.getId() > 0){
							dtlKey.append(",").append(drg52.getId());
							updateListDrg5002Db.add(drg52);					
						}else{
							saveListDrg5002Db.add(drg52);
						}
					}
					deleteListDrg5002Db.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg5002Db where drg5001Db.id=" + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));
				}else{
					//刪除資料				
					drg5002DbList3 = ServiceGetter.getInstance().getCommonService().load("from Drg5002Db where drg5001Db.id="+Common.getLong(ref.getId()));
					if(drg5002DbList != null) deleteListDrg5002Db.addAll(drg5002DbList3);
					if(drg5002DbList3 != null) drg5002DbList3.clear();
				}
			}
			
			//避免複製drg0001時沒資料，先儲存一次
			saveOrUpdate(obj);
			saveOrUpdateDrg5002Db(saveListDrg5002Db, updateListDrg5002Db, deleteListDrg5002Db);
			
			//接收前端按鈕塞入狀態，按下待上傳鈕，案件狀態=待上傳
			if("2".equals(ref.getUpdateType()))
			{
				obj.setStatus("01");
			}
			else if("3".equals(ref.getUpdateType()))
			{
				if("00".equals(obj.getStatus()) || "01".equals(obj.getStatus())){				
					//按下送出鈕				
					obj.setStatus("10");				
					obj.setNotifierRepDate(yyymmdd);
					updateDoCopyDrg0001Db(obj,ref.getUserID());
				}else if("02".equals(obj.getStatus())){
					obj.setStatus("11");
					Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(obj.getDrg0001Id()));
					//案號為空，將資料update回內網
					if("".equals(Common.get(obj.getApplNo()))) updateDoCopyDrg0001Db(obj,ref.getUserID());
					else doCopyCon0001Db(obj,drg01,"1");
					updateStatusDrg0001Db(drg01,"11",Common.get(ref.getUserID()));
				}
				else if("22".equals(obj.getStatus()) || "30".equals(obj.getStatus()))//主動補件會將分級確認中改為案件分級中(已補件)
				{
					obj.setStatus("24");
					Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(obj.getDrg0001Id()));
					doCopyCon0001Db(obj,drg01,"1");
					updateStatusDrg0001Db(drg01,"24",Common.get(ref.getUserID()));
				}
			}
			else
			{
				//如果為退件案件，則不可變換狀態，否則後續再判斷狀態時會有問題
				if(!"02".equals(obj.getStatus())&&!"22".equals(obj.getStatus())){
					obj.setStatus("00");
				}
			}

			update(obj);
			ref.setId(Common.get(obj.getId()));		
			
			if(saveListDrg5002Db != null) saveListDrg5002Db.clear();
			if(updateListDrg5002Db != null) updateListDrg5002Db.clear();
			if(deleteListDrg5002Db != null) deleteListDrg5002Db.clear();
		}	
		
	}
	
	public void saveOrUpdateDrg5002Db(java.util.List<Drg5002Db> saveList,java.util.List<Drg5002Db> updateList,java.util.List<Drg5002Db> deleteList) 
	throws Exception
	{
		if(deleteList !=null) deleteBatch(deleteList);
		if(saveList !=null) saveBatch(saveList);		
		if(updateList !=null) updateBatch(updateList);
	}

	
	public void updateSendApplNoByDRGEX0105F(DRGEX0105F ref) throws Exception 
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
				java.util.List objList = load(" from Drg5001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0)
				{
					java.util.List<Drg5001Db> uList = new java.util.ArrayList<Drg5001Db>();
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					for(Object dtlObj : objList)
					{
						Drg5001Db dtl = (Drg5001Db)dtlObj;
						if("01".equals(Common.get(dtl.getStatus())))
						{
							dtl.setStatus("10");
							
							//接獲通報日期=系統日期
							dtl.setNotifierRepDate(yyymmdd);
							
							dtl.setModifier(ref.getUserID());
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							uList.add(dtl);
							
							//轉入內網資料
							updateDoCopyDrg0001Db(dtl,ref.getUserID());
						}
						else
						{
							logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-待上傳作業]-[案件狀態不為待上傳，不更新資料，MED5001_DB.ID : " + dtl.getId() + "]");
						}
					}
					if(uList.size() > 0){
						updateBatch(uList);
						uList.clear();
					}
				}else{
					logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}else{
			logger.info("[TCBW]-[MedexDaoImpl]-[藥品不良品事件通報-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
		
	}
	
	public void updateDoCopyDrg0001Db(Drg5001Db obj, String userID) throws Exception
	{

		if(obj != null)
		{
			Drg0001Db master = (Drg0001Db) View.getObject(" from Drg0001Db where id="+Common.getLong(obj.getDrg0001Id()));
			boolean isUpdate = false;
			if(master==null){
				master = new Drg0001Db(); 
				String[] ignoreFields = new String[]{"id", "drg5002Dbs"};
				org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			}else{
				//刪除舊drg0002Db
				java.util.List<Drg0002Db> drg0002DbList = ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where drg0001Db.id="+Common.getLong(master.getId()));			
				if(drg0002DbList!=null && drg0002DbList.size()>0) ServiceGetter.getInstance().getCommonService().deleteBatch(drg0002DbList);
				//update drg0001資料
				isUpdate = true;
				long oldDrg01Id = master.getId();
				master = (Drg0001Db) setFileValue(master, obj);
				master.setId(oldDrg01Id);
			}			
			
			java.util.Set drg0002Dbs = new ListOrderedSet();
			
			if(obj.getDrg5002Dbs()!=null && obj.getDrg5002Dbs().size()>0)
			{
				String[] dtlIgnoreFields = new String[]{"id"};
				for(Object dtlObj : obj.getDrg5002Dbs())
				{
					Drg5002Db dtl = (Drg5002Db)dtlObj;
					Drg0002Db tmpDtl = new Drg0002Db();					
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, dtlIgnoreFields);
					tmpDtl.setDrg0001Db(master);
					drg0002Dbs.add(tmpDtl);
				}
			}
			
			master.setDrg0002Dbs(drg0002Dbs);
			if(isUpdate) update(master);
			else save(master);
			
			//檔案上傳複製
			doCopyCon0001Db(obj,master,"1");
			
			//完整性確認			
			boolean isComplete = checkDrg0001Db(master);
			if(isComplete)  master.setIsComplete("Y");
			else  master.setIsComplete("N");			
			update(master);
			
			//存入內網id
			obj.setDrg0001Id(master.getId());
			update(obj);
			
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",master.getId(), master.getApplNo(),"10", "案件上傳", userID);
		}
	}
	
	//檔案上傳回內網
	public void doCopyCon0001Db(Drg5001Db obj, Drg0001Db drg01 ,String type) throws Exception
	{
		
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and isInsert='Y' and upLoadId="+obj.getId());
		
		if(null != con01List && !con01List.isEmpty()){
			for(Con0001Db oldcon : con01List){
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Drg0001Db");
				newcon.setUpLoadId(drg01.getId());
				newcon.setIsInsert("N");
				ServiceGetter.getInstance().getCommonService().save(newcon);
				if("1".equals(type)){ //退件補件			
					oldcon.setIsInsert("N"); //使檔案分得出退件補件後上傳的新檔案				
					ServiceGetter.getInstance().getCommonService().update(oldcon);
				}else{ //主動補件
					ServiceGetter.getInstance().getCommonService().update(oldcon);
				}
			}
		}
	}

	public DRGEX0302F updateByDrgEX0302F(DRGEX0302F ref) throws Exception 
	{

		List delList = new ArrayList();
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg6001Db obj = (Drg6001Db)View.getObject(" from Drg6001Db where id = " + Common.sqlChar(ref.getId()));

        if(obj != null)
		{
        	//通報訊息
			obj.setOccurDate(ref.getOccurDate());								//通報日期
			obj.setNotifierRepDate(ref.getNotifierRepDate());					//通報者接獲日期
			obj.setNotifierRevDate(ref.getNotifierRevDate());					//通報中心接獲日期
			obj.setNotifierSource(ref.getNotifierSource());						//原始藥物不良事件獲知來源
			
			//通報者資訊
			if("I".equals(ref.getIsInOrOutPerson())) obj.setNotifierUserID(Common.get(ref.getNotifierUserID()));
			obj.setNotifierUserID(ref.getNotifierUserID());
			obj.setNotifierName(ref.getNotifierName());							//姓名
			obj.setNotifierDeptID(ref.getNotifierDeptID());
			obj.setNotifierDept(ref.getNotifierDept());							//服務機構
			obj.setNotifierTelArea(ref.getNotifierTelArea());
			obj.setNotifierTel(ref.getNotifierTel());							//電話
			obj.setNotifierTelExt(ref.getNotifierTelExt());
			obj.setNotifierEmail(ref.getNotifierEmail());						//E-Mail
			obj.setNotifierPhone(ref.getNotifierPhone());						//手機
			obj.setNotifierFaxArea(ref.getNotifierFaxArea());
			obj.setNotifierFax(ref.getNotifierFax());							//傳真
			obj.setNotifierCounty(ref.getNotifierCounty());
			obj.setNotifierZipCode(ref.getNotifierZipCode());
			obj.setNotifierAddress(ref.getNotifierAddress());					//地址
			obj.setNotifierTitle(ref.getNotifierTitle());						//職稱
			obj.setNotifierType(ref.getNotifierType());							//屬性
		
			//病人相關資料
			obj.setPatientId(ref.getPatientId());								//識別代碼
			obj.setPatientSex(ref.getPatientSex());								//性別
			obj.setPatientBirth(ref.getPatientBirth());							//出生日期
			obj.setPatientAge(ref.getPatientAge());								//年齡
			obj.setPatientHeight(ref.getPatientHeight());						//身高
			obj.setPatientWeight(ref.getPatientWeight());						//體重
			
			//療效不等反應				
			String conSequences = "";
			if(null != ref.getConSequence() && ref.getConSequence().length > 0){
				for(String conSeq : ref.getConSequence()){
					if(conSequences.length() > 0)	conSequences += ",";
					conSequences += conSeq;
				}
			}
			obj.setConSequence(conSequences);	
			obj.setEffectChangeDesc(ref.getEffectChangeDesc());				//通報事件後果-藥效改變狀況
			obj.setBadReactionLev(ref.getBadReactionLev());					//通報事件後果-不良反應等級
			obj.setBadReactionDesc(ref.getBadReactionDesc());				//通報事件後果-不良反應狀況
			//通報事件後果
			if(!StringUtils.contains(obj.getConSequence(), "1")) {
				obj.setEffectChangeDesc(null);
			}
			if(!StringUtils.contains(obj.getConSequence(), "2")) {
				obj.setBadReactionLev(null);
				obj.setBadReactionDesc(null);
			}

			obj.setBeforeDesc(ref.getBeforeDesc());								//事件前描述
			obj.setChangeDesc(ref.getChangeDesc());								//藥品轉換描述
			obj.setOccurDesc(ref.getOccurDesc());								//發生事件描述
			obj.setAfterDesc(ref.getAfterDesc());								//事件後描述
			obj.setOtherDesc(ref.getOtherDesc());								//其他相關資料
			obj.setDealWith(ref.getDealWith());									//發生事件後之處置
			if("ZZ".equals(ref.getDealWith())) {
				obj.setDealWithOther(ref.getDealWithOther());					//發生事件後之處置其他
			} else {
				obj.setDealWithOther(null);
			}
			
			obj.setIsImproveYn(ref.getIsImproveYn());							//病人恢復原藥或轉是同成分藥品其症狀是否改善
			obj.setIsContactYn(ref.getIsContactYn());							//提供聯絡資訊供廠商後續調查評估
			obj.setDressingAttitude(ref.getDressingAttitude());					//醫師對換藥的態度
			obj.setObedienceLev(ref.getObedienceLev());							//病人服藥順從性

			//其他相關檢查及檢驗數據資訊
			if (true) {
    			java.util.Set dtlSet = new ListOrderedSet();
        		if (ref.getHttpRequest()!=null && ref.getDrg62Row()!=null && ref.getDrg62Row().length>0) {
        			StringBuilder dtlKey = new StringBuilder("-2,-1");		
        			for (int i=0; i<ref.getDrg62Row().length; i++) {
        				String rid = ref.getDrg62Row()[i];
        				Drg6002Db dtl = (Drg6002Db) View.getObject("from Drg6002Db where id="+Common.getLong(ref.getHttpRequest().getParameter("drg62Id" + rid)));
        				if (dtl==null) {
        					dtl = new Drg6002Db();
        					dtl.setDrg6001Db(obj);
        				}
        				for (int j=0; j<ref.arrDrg62FieldName.length; j++) {
        					BeanUtils.setProperty(dtl, ref.arrDrg62FieldName[j], ref.getHttpRequest().getParameter(ref.arrDrg62FieldName[j] + rid));
        				}		
        				dtl.setModifier(ref.getUserID());
        				dtl.setModifyDate(Datetime.getYYYMMDD());
        				dtl.setModifyTime(Datetime.getHHMMSS());
        				dtlSet.add(dtl);
        				
        				dtlKey.append(",").append(dtl.getId());
        			}
        			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6002Db where drg6001Db.id=" + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));
        		} else {
        			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6002Db where drg6001Db.id=" + Common.getLong(obj.getId())));
        		}	
        		obj.setDrg6002Dbs(dtlSet);	
        	}
			
			//使用藥品
			if (true) {
    			java.util.Set dtlSet = new ListOrderedSet();
        		if (ref.getHttpRequest()!=null && ref.getDrg63Row()!=null && ref.getDrg63Row().length>0) {
        			StringBuilder dtlKey = new StringBuilder("-2,-1");		
        			for (int i=0; i<ref.getDrg63Row().length; i++) {
        				String rid = ref.getDrg63Row()[i];
        				Drg6003Db dtl = (Drg6003Db) View.getObject("from Drg6003Db where id="+Common.getLong(ref.getHttpRequest().getParameter("drg63Id" + rid)));
        				if (dtl==null) {
        					dtl = new Drg6003Db();
        					dtl.setDrg6001Db(obj);
        				}
        				for (int j=0; j<ref.arrDrg63FieldName.length; j++) {
        					BeanUtils.setProperty(dtl, ref.arrDrg63FieldName[j], ref.getHttpRequest().getParameter(ref.arrDrg63FieldName[j] + rid));
        				}		
        				dtl.setModifier(ref.getUserID());
        				dtl.setModifyDate(Datetime.getYYYMMDD());
        				dtl.setModifyTime(Datetime.getHHMMSS());
        				dtlSet.add(dtl);
        				
        				dtlKey.append(",").append(dtl.getId());
        			}
        			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6003Db where drg6001Db.id=" + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));
        		} else {
        			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6003Db where drg6001Db.id=" + Common.getLong(obj.getId())));
        		}	
        		obj.setDrg6003Dbs(dtlSet);	
        	}
			
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);			

			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			//附件上傳
			if (true) {
	    		java.util.Set dtlSet = new ListOrderedSet();
	    		if (ref.getHttpRequest()!=null && ref.getConRow()!=null && ref.getConRow().length>0) {
	    			StringBuilder dtlKey = new StringBuilder("-2,-1");		
	    			for (int i=0; i<ref.getConRow().length; i++) {
	    				String rid = ref.getConRow()[i];
	    				Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+Common.getLong(ref.getHttpRequest().getParameter("conId" + rid)));							
	    				dtlKey.append(",").append(dtl.getId());
	    			}
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Drg6001Db' and upLoadId= " + Common.getLong(ref.getId()) + " and id not in (" + dtlKey.toString() + ")"));		
	    		} else {
	    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Drg6001Db' and upLoadId= " + Common.getLong(ref.getId())));
	    		}
	    	}
			if(null != delList && !delList.isEmpty()){
				ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
			}
			ServiceGetter.getInstance().getTcbwService().getDrgexDao().sendDrg061Db(obj, ref.getUpdateType());
			ref.setId(Common.get(obj.getId()));
		}	
		return ref;
	}
	public void sendDrg061Db(Drg6001Db drg61, String updateType){
		//updateType：1暫存；2待上傳；3送件
		boolean isCopy = false;
		Drg4001Db drg41 = null;
		if(null != drg61){
			drg41 = (Drg4001Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4001Db where id = " + drg61.getDrg4001Id());
			//接收前端按鈕塞入狀態，按下待上傳鈕，案件狀態=待上傳
			if("2".equals(updateType)){
				drg61.setStatus("01");
			}else if("3".equals(updateType)){
				drg61.setNotifierRevDate(Datetime.getYYYMMDD());
				if("02".equals(drg61.getStatus())){				//退件
					drg61.setStatus("11");
					isCopy = true;
				}else if("21".equals(drg61.getStatus()) || "22".equals(drg61.getStatus())){		//案件補件中
					drg61.setStatus("23");
				}else if("00".equals(drg61.getStatus()) || "01".equals(drg61.getStatus())){
					isCopy = true;
					drg61.setStatus("10");
				}
			}else if("1".equals(updateType)){
				if(!"02".equals(drg61.getStatus())&&!"21".equals(drg61.getStatus())){
					drg61.setStatus("00");
				}
			}
			ServiceGetter.getInstance().getCommonService().update(drg61);
			if(null != drg41){
				if("23".equals(drg61.getStatus()) || "11".equals(drg61.getStatus())){
					drg41.setStatus(drg61.getStatus());
					drg41.setModifier(TCBWCommon.getCurrentUserId());
					drg41.setModifyDate(Datetime.getYYYMMDD());
					drg41.setModifyTime(Datetime.getHHMM());
					ServiceGetter.getInstance().getCommonService().update(drg41);
					
					//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg41.getId(), drg41.getApplNo(),drg41.getStatus(), "案件登錄 - 案件補件完成", TCBWCommon.getCurrentUserId());
					//寄發mail
					//ServiceGetter.getInstance().getTcbwService().getDrgexDao().sendDrgMailToChargeMan(drg41, "已完成補件。", null);
				}
			}

			if(isCopy){
				ServiceGetter.getInstance().getTcbwService().getDrgexDao().copyToInDrgData(drg61);
			}
		}
	}
	
	public void copyToInDrgData(Drg6001Db drg61){
		try {
			Drg4001Db drg41 = (Drg4001Db)View.getObject("from Drg4001Db where id="+Common.getLong(drg61.getDrg4001Id()));
			boolean update41 = false;
			if(drg41 == null){			
				drg41 = (Drg4001Db) setFileValue(new Drg4001Db(), drg61);
				drg41.setId(null);
			}else{	
				//刪除舊drg4002Db,drg4003Db
				java.util.List<Drg4002Db> drg4002DbList = ServiceGetter.getInstance().getCommonService().load(" from Drg4002Db where drg4001Db.id="+Common.getLong(drg41.getId()));
				java.util.List<Drg4003Db> drg4003DbList = ServiceGetter.getInstance().getCommonService().load(" from Drg4003Db where drg4001Db.id="+Common.getLong(drg41.getId()));				
				if(drg4002DbList!=null && drg4002DbList.size()>0) ServiceGetter.getInstance().getCommonService().deleteBatch(drg4002DbList);
				if(drg4003DbList!=null && drg4003DbList.size()>0) ServiceGetter.getInstance().getCommonService().deleteBatch(drg4003DbList);
				//update drg4001資料
				update41 = true;
				long oldDrg41Id = drg41.getId();
				drg41 = (Drg4001Db) setFileValue(drg41, drg61);
				drg41.setId(oldDrg41Id);
			}			
			if(null != drg61.getDrg6002Dbs() && !drg61.getDrg6002Dbs().isEmpty()){				
				Set dtlSet = new ListOrderedSet();
				for(Object dtlObj:drg61.getDrg6002Dbs()){
					Drg4002Db drg42 = (Drg4002Db) setFileValue(new Drg4002Db(), dtlObj);
					drg42.setId(null);
					drg42.setDrg4001Db(drg41);
					dtlSet.add(drg42);
				}
				drg41.setDrg4002Dbs(dtlSet);
			}
			
			if(null != drg61.getDrg6003Dbs() && !drg61.getDrg6003Dbs().isEmpty()){
				Set dtlSet = new ListOrderedSet();
				for(Object dtlObj:drg61.getDrg6003Dbs()){
					Drg4003Db drg43 = (Drg4003Db) setFileValue(new Drg4003Db(), dtlObj);
					drg43.setId(null);
					drg43.setDrg4001Db(drg41);
					dtlSet.add(drg43);
				}
				drg41.setDrg4003Dbs(dtlSet);
			}
			if(update41) ServiceGetter.getInstance().getCommonService().update(drg41);
			else ServiceGetter.getInstance().getCommonService().save(drg41);
			
			//轉附件資料
			List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType like 'DRG020001' and dbName='Drg6001Db' and isInsert='Y' and upLoadId="+drg61.getId());
			if(null != conList && !conList.isEmpty()){
				for(Con0001Db con : conList){
					Con0001Db obj = new Con0001Db();
					org.springframework.beans.BeanUtils.copyProperties(con, obj, new String[]{"id"});
					obj.setDbName("Drg4001Db");
					obj.setUpLoadId(drg41.getId());
					ServiceGetter.getInstance().getCommonService().save(obj);
					con.setIsInsert("N"); //使檔案分得出退件補件後上傳的新檔案				
					ServiceGetter.getInstance().getCommonService().update(con);
				}
			}
			
			
			//完整性確認			
			boolean isComplete = checkDrg6001Db(drg41);
			if(isComplete)  drg41.setIsComplete("Y");
			else  drg41.setIsComplete("N");
			
			ServiceGetter.getInstance().getCommonService().update(drg41);
			
			drg61.setDrg4001Id(drg41.getId());
			ServiceGetter.getInstance().getCommonService().update(drg61);
			
			if("10".equals(drg41.getStatus())){
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg41.getId(), drg41.getApplNo(),"10", "案件上傳", TCBWCommon.getCurrentUserId());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateStatusDrg0001Db(Drg0001Db drg01,String status,String userId) throws Exception
	{
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();		
		
		if(drg01 != null)
		{
			drg01.setStatus(status);
            
			drg01.setModifier(userId);
			drg01.setModifyDate(yyymmdd);
			drg01.setModifyTime(hhmmss);
			String processName ="";
			
			if("11".equals(status)){  
				processName ="案件登錄 - 案件補件完成(審核)"; 
			}else if("24".equals(status)){  
				processName ="案件登錄 - 案件補件完成(分級)";
			}
			
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",drg01.getId(), drg01.getApplNo(),status, processName, userId);			
			//發Mail給案件退回者
			mailToChargeMan(drg01,userId);
		}		
	}
	
	//checkDrg0001Db
	private boolean checkDrg0001Db(Drg0001Db obj) {
		boolean isComplete = false;	
		//比對藥證資料(中文品名,英文品名,申請商,製造廠)
		Database db2 = new Database("MLMS");
		List<Object> parameter = new ArrayList<Object>();
		//String hql = "select distinct a.CHNAME,a.ENNAME,a.APPNAME,a.FACTNAME from VW_ForADR_TBMLIC a where 1=1 "+
		//             " and a.LICEKID="+Common.sqlChar(obj.getPermitKey())+" and a.LICID1="+Common.sqlChar(obj.getPermitNo());
		String hql = "select distinct a.CHNAME,a.ENNAME,a.APPNAME,a.FACTNAME from VW_ForADR_TBMLIC a where 1=1 "+
		             " and a.LICEKID= ? and a.LICID1= ?";
		
		parameter.add(obj.getPermitKey());
		parameter.add(obj.getPermitNo());
		ResultSet rs;
		try {
			//rs = db2.querySQLByScroll(hql);
			rs = db2.querySQLByPrepareStatement(hql,parameter);
			if (rs!=null) {   		
	    		while (rs.next()){
	    			isComplete = true;
	    			if(obj.getChProduct().equals(Common.get(rs.getString("CHNAME"))) &&
	    					obj.getEnProduct().equals(Common.get(rs.getString("ENNAME"))) &&
	    					obj.getApplicationName().equals(Common.get(rs.getString("APPNAME"))) &&
	    					obj.getManufactorName().equals(Common.get(rs.getString("FACTNAME"))))	    			
	    			{
	    				isComplete = true;
	    			}
	    		}
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db2.closeAll();
		}
		//檢查有無附檔
		if(isComplete){
			String hql3 = " from Con0001Db where dbName='Drg0001Db' and upLoadId="+Common.getLong(obj.getId());
			int count = ServiceGetter.getInstance().getCommonService().loadCount(hql3);
			if(count >0 ){
				isComplete = true;
			}else{
				isComplete = false;
			}
		}
		
		//比對完整性欄位
		if(isComplete){
			String hql2 = " select id,name from Con1008Db where con1007Db.formCode='DRG01' and isComplete='Y' ";
			java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(hql2);
			if (list!=null && list.size()>0) {
				String checkSQL =" from Drg0001Db where id="+Common.getLong(obj.getId())+" and (";
				for(int i=0 ; i<list.size();i++){
					Object[] o = (Object[]) list.get(i);
					checkSQL += Common.get(o[1])+" is null or "+Common.get(o[1])+" ='' or ";
				}
				if(checkSQL != ""){ 
					checkSQL = checkSQL.substring(0, checkSQL.length()-3) +")";
					Drg0001Db obj2 = (Drg0001Db)View.getObject(checkSQL);
					if(obj2 != null) isComplete = false;
									
				}
			}
		}
		return isComplete;
	}
	
	public void mailToChargeMan(Drg0001Db obj, String userId) throws Exception
	{
		String title="【補件回覆】",mailBody="",mail="";
		String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(obj.getChargeMan()));
	    
		mail = email;
	
	    if(obj.getApplNo()!= null) title += "案號:"+obj.getApplNo();	
	    mailBody = "已完成補件。";
	           
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
	    ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null,"DRG01",Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):obj.getApplNo());
	    TCBWCommon.setMailbackup("DRG",Common.get(obj.getId()),title, mailBody, obj.getApplNo(),"", userId,"");	
	
	}
	
	public Object setFileValue(Object newTable, Object oldTable) throws Exception{
		if(null != oldTable ){
			PropertyDescriptor[] newPs = PropertyUtils.getPropertyDescriptors(newTable);	//放入的資料
    		PropertyDescriptor[] oldPs = PropertyUtils.getPropertyDescriptors(oldTable);	//欲放入資料
    		for(int i=0;i<newPs.length;i++){
            	for(int j=0;j<oldPs.length;j++){
            		if(newPs[i].getName().toUpperCase().equals(oldPs[j].getName().toUpperCase())){		//欄位名稱才放入
            			if(!"class".equals(newPs[i].getName()) && !"ID".equals(newPs[i].getName())){
            				if("class java.lang.Long".indexOf(Common.get(newPs[i].getPropertyType())) >= 0){
            					PropertyUtils.setProperty(newTable, newPs[i].getName(), Common.getLong(PropertyUtils.getProperty(oldTable, oldPs[j].getName())));
            				}else if("class java.lang.Double".indexOf(Common.get(newPs[i].getPropertyType())) >= 0){
            					PropertyUtils.setProperty(newTable, newPs[i].getName(), Common.getNumeric(PropertyUtils.getProperty(oldTable, oldPs[j].getName())));
            				}else{
            					PropertyUtils.setProperty(newTable, newPs[i].getName(), Common.get(PropertyUtils.getProperty(oldTable, oldPs[j].getName())));
            				}
            			}
            		}
            	}
    		}
		}
		return newTable;
	}
	
	public void checkNoByDrgEX0107F(DRGEX0107F ref) throws Exception 
	{		
		Drg0001Db drg01 = (Drg0001Db) View.getObject(" from Drg0001Db where applNo="+Common.sqlChar(ref.getApplNo()));
		
		boolean closed = false;
		if(drg01 != null){
			//取得不良品缺陷資料
			String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(drg01.getId());		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");        
			String subCodeList =""; 
		
			if(objList!=null && objList.size()>0){			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext())		
				{				
					Drg0002Db o = (Drg0002Db) it.next();				
					String[] subList = o.getSubCode().split(",");				
					for(int j=0; j<subList.length; j++){					
						if(Common.get(subList[j]) != ""){				        
							subCodeList += "subCode like ('%"+subList[j]+"%') or ";					
						}				
					}			
				}		
			}		
			if(subCodeList.length()>0) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
			
			Drg0005Db drg05 = (Drg0005Db)View.getObject(" from Drg0005Db where applNo="+Common.sqlChar(drg01.getApplNo()));
			if(drg05 != null){
				/**
				 *   查找「一年內本藥品同此次瑕疵」之通報案件，取得其案件評估資料的[CAPA執行完成日期](取有值且最近者)，再與廠商輸入的[製造日期]比較
				 *   如果[製造日期]在[CAPA執行完成日期]之前，則為「前次CAPA執行前製造」
				 *   如果[製造日期]在[CAPA執行完成日期](含)之後，則為「前次CAPA執行後製造」
				 *   同藥品的定義：藥品的許可證字號相同。
	             *   同瑕疵的定義：不良品缺陷(小類)有任一相同，但是“其他”不包含在內。
				 **/
				     
				String subSQL = " select applNo from Drg0001Db where permitKey="+Common.sqlChar(drg01.getPermitKey())+" and permitNo="+Common.sqlChar(drg01.getPermitNo())+                      
	        	                " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+                   
	        	                " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
				//CAPA完成日期
				String maxCapaDownDate = View.getLookupField("select Max(capaDownDate) from Drg0008Db where applNo in ( "+subSQL+" )");		
				
				String beforeOrLater = Datetime.getDateDiff("d",maxCapaDownDate,drg05.getCapaDate());
				
				if(Common.getInt(beforeOrLater) < 0){
					//前次CAPA執行前製造
					drg05.setBeforeOrLater("1");
					closed = true;
				}else{
					drg05.setBeforeOrLater("2");
				}
				ServiceGetter.getInstance().getTcbwService().update(drg05);				
			}			
			            
		}	
		
		//屬於前次CAPA執行前製造
        if(closed){
    		String yyymmdd = Datetime.getYYYMMDD();
    		String hhmmss = Datetime.getHHMMSS();        	
        	Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where id="+Common.getLong(ref.getId()));
        	
			if(drg5001Db!=null)
			{
				drg5001Db.setStatus("90");//結案
				drg5001Db.setModifier(ref.getUserID());
				drg5001Db.setModifyDate(yyymmdd);
				drg5001Db.setModifyTime(hhmmss);
				Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where applNo="+Common.sqlChar(drg5001Db.getApplNo()));
				if(obj!=null)
				{
					obj.setStatus("90");//結案
					obj.setChargeMan(""); //清空[作業人員]欄位
					obj.setModifier(ref.getUserID());
					obj.setModifyDate(yyymmdd);
					obj.setModifyTime(hhmmss);
					
					ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
					ServiceGetter.getInstance().getTcbwService().update(obj);
					
					//發送信件通知		    		
		    		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		    		
		    		String mailBody="案件"+obj.getApplNo()+"經廠商確認為前次CAPA執行前製造，已結案\n\n謝謝!!";
		    		String title = "結案通知- 案號:"+obj.getApplNo();
		    		String mail = "";	    				
		    		//取得評估人員Email
		    		String assessMan = View.getLookupField("select assessMan from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
		    		mail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(assessMan));
		    		
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
		    		
		    		//結案後產生檔案
		    		if("90".equals(obj.getStatus())){
		    			closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
		    		}
		    		//歷程紀錄
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"90", "廠商回覆 - CAPA確認表(前次CAPA執行前製造)", ref.getUserID());
										
				}
			}			       	
		}	
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
	
	public void doReplyByDrgEX0109F(DRGEX0109F ref) throws Exception 
	{	
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS(); 
		
        Drg5001Db drg5001Db = (Drg5001Db)View.getObject(" from Drg5001Db where id="+Common.getLong(ref.getId()));        	
		if(drg5001Db!=null)
		{
			drg5001Db.setStatus("42");//案件評估中(已交付)
			drg5001Db.setModifier(ref.getUserID());
			drg5001Db.setModifyDate(yyymmdd);
			drg5001Db.setModifyTime(hhmmss);
			Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where applNo="+Common.sqlChar(drg5001Db.getApplNo()));
			if(obj!=null)
			{
				obj.setStatus("42");//案件評估中(已交付)
				
				obj.setModifier(ref.getUserID());
				obj.setModifyDate(yyymmdd);
				obj.setModifyTime(hhmmss);
					
				ServiceGetter.getInstance().getTcbwService().update(drg5001Db);
				ServiceGetter.getInstance().getTcbwService().update(obj);
					
				//發送信件通知		    		
	    		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
	    		
	    		String mailBody="案件"+obj.getApplNo()+"，廠商已完成CAPA評估表。\n\n謝謝!!";
	    		String title = "廠商回覆完成通知- 案號:"+obj.getApplNo();
	    		String mail = "";	    				
	    		//取得評估人員Email
	    		String assessMan = View.getLookupField("select assessMan from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo()));
	    		mail = View.getLookupField("select userEmail from CommonUser where userId="+Common.sqlChar(assessMan));
	    		
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
		    		
		    	//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG1",obj.getId(), obj.getApplNo(),"42", "廠商回覆 - 回覆完成", ref.getUserID());
										
			}
		}
	}
	
	//checkDrg6001Db
	private boolean checkDrg6001Db(Drg4001Db obj) {
		boolean isComplete = false;	
		//比對完整性欄位
		if(isComplete){
			String hql2 = " select id,name from Con1008Db where con1007Db.formCode='DRG02' and isComplete='Y' ";
			java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(hql2);
			if (list!=null && list.size()>0) {
				String checkSQL =" from Drg4001Db where id="+Common.getLong(obj.getId())+" and (";
				for(int i=0 ; i<list.size();i++){
					Object[] o = (Object[]) list.get(i);
					checkSQL += Common.get(o[1])+" is null or "+Common.get(o[1])+" ='' or ";
				}
				if(checkSQL != ""){ 
					checkSQL = checkSQL.substring(0, checkSQL.length()-3) +")";
					Drg6001Db obj2 = (Drg6001Db)View.getObject(checkSQL);
					if(obj2 != null) isComplete = false;
									
				}
			}
		}
		return isComplete;
	}
	
	public void autoReUpdateByDrgEX0102F(DRGEX0102F ref) throws Exception 
	{
		java.util.Set drg5002Dbs = new ListOrderedSet();
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();		
		
		Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(ref.getDrg0001Id()));
		boolean updateDrg01 = false;
		
		Drg5001Db obj = new Drg5001Db();
		
		Drg5001Db drg5001old = (Drg5001Db)View.getObject(" from Drg5001Db where drg0001Id="+Common.getLong(ref.getDrg0001Id())+" order by id desc");
		//版次+1
		String revision = drg5001old.getRevision();
		int newRevision = Common.getInt(revision)+1;
		obj.setRevision(Common.get(newRevision));
		obj.setAutoReUpdate("Y");
		
		//案件狀態
		String newStatus ="";
		if("30".equals(Common.get(drg5001old.getStatus()))){//案件分級中(已補件) 
			newStatus="24"; 
			//更新內網狀態			
			if(drg01!=null)
			{
				String assessMan = View.getLookupField(" select assessMan from Drg0003Db where applNo="+Common.sqlChar(drg01.getApplNo())+" order by id desc");
				drg01.setStatus("24");				
				drg01.setChargeMan(assessMan); //更改案件承辦員
				
				Drg0003Db drg0003Db = (Drg0003Db)View.getObject(" from Drg0003Db where applNo="+Common.sqlChar(drg01.getApplNo()) +"order by id desc");
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
					
					ServiceGetter.getInstance().getTcbwService().save(master);					
				}	
				updateDrg01 = true;
			}
		}else{//維持原狀態	 
			newStatus = Common.get(drg5001old.getStatus());  	
		}
		obj.setStatus(newStatus);			
		
		obj.setApplNo(ref.getApplNo());
        obj.setDrg0001Id(Common.getLong(ref.getDrg0001Id()));
        //基本資料------------------------------------------------------------------
		obj.setOccurDate(Common.get(ref.getOccurDate()));		
		obj.setNotifierRevDate(Common.get(ref.getNotifierRevDate()));
		obj.setNotifierRepDate(Common.get(ref.getNotifierRepDate()));
		obj.setNotifierSource(Common.get(ref.getNotifierSource()));
		
		obj.setNotifierName(Common.get(ref.getNotifierName()));
		obj.setNotifierUserID(Common.get(ref.getNotifierUserID()));			
		obj.setNotifierDept(Common.get(ref.getNotifierDept()));
		obj.setNotifierDeptID(Common.get(ref.getNotifierDeptID()));
		obj.setNotifierTel(Common.get(ref.getNotifierTel()));
		obj.setNotifierTelArea(Common.get(ref.getNotifierTelArea()));	
		obj.setNotifierTelExt(Common.get(ref.getNotifierTelExt()));	
		obj.setNotifierCounty(Common.get(ref.getNotifierCounty()));	
		obj.setNotifierZipCode(Common.get(ref.getNotifierZipCode()));	
		obj.setNotifierAddress(Common.get(ref.getNotifierAddress()));		
		obj.setNotifierEmail(Common.get(ref.getNotifierEmail()));		
		obj.setNotifierType(Common.get(ref.getNotifierType()));
		obj.setNotifierTitle(Common.get(ref.getNotifierTitle()));	
			
		//不良藥品資料--------------------------------------------------------------
		obj.setPermitKey(Common.get(ref.getPermitKey()));
		obj.setPermitNo(Common.get(ref.getPermitNo()));
		obj.setChProduct(ref.getChProduct());
		obj.setEnProduct(ref.getEnProduct());
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
		obj.setManufactorCountry(Common.get(ref.getManufactorCountry()));		
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
		obj.setDefectDesc(Common.get(ref.getDefectDesc()));
		obj.setFirstResult(Common.get(ref.getFirstResult()));
		obj.setFirstRemark(Common.get(ref.getFirstRemark()));			
			
		obj.setCreator(ref.getUserID());
		obj.setCreateDate(yyymmdd);
		obj.setCreateTime(hhmmss);
			
		if(ref.getMainCode()!=null){
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId");				
			if(objList!=null && objList.size()>0)					
			{
				java.util.Iterator it = objList.iterator();
				int k =0;
				while (it.hasNext()) 
				{
					CommonCode drgCode = (CommonCode) it.next();
					for(int i=0; i<ref.getMainCode().length; i++){
						if(Common.get(drgCode.getCodeId()).equals(Common.get(ref.getMainCode()[i]))){							
							String subCodeList ="";							
							Drg5002Db obj2 = new Drg5002Db();						
							if(ref.getSubCode()!=null){						
								for(int j=0;j<ref.getSubCode().length;j++)							
								{							
									//抓取第二層資料(前2碼與第一層相同)								
									if(Common.get(ref.getMainCode()[i]).equals(Common.get(ref.getSubCode()[j]).substring(0,2))){									
										subCodeList += ref.getSubCode()[j]+",";								
									}							
								}						
							}
							//主代碼				
							obj2.setMainCode(Common.get(ref.getMainCode()[i]));
							//子代碼						
							if(subCodeList != "") obj2.setSubCode(subCodeList.substring(0, subCodeList.length()-1));						
							else obj2.setSubCode(subCodeList);							                     
							//說明描述						
							obj2.setOtherDescribe(Common.get(ref.getOtherDescribe()[k]));                        
						
							obj2.setDrg5001Db(obj);											
							drg5002Dbs.add(obj2);							
						}
					}
					k++;
				}								
			}			
		}
		
		obj.setDrg5002Dbs(drg5002Dbs);			
		ServiceGetter.getInstance().getTcbwService().save(obj);
		if(updateDrg01) ServiceGetter.getInstance().getCommonService().update(drg01);
		ref.setId(Common.get(obj.getId()));
		
		//轉入附件(抓上版本的附件資料)
		List<Con0001Db> con01List = ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and upLoadId="+drg5001old.getId());
		if(null != con01List && !con01List.isEmpty()){
			for(Con0001Db oldcon : con01List){
				Con0001Db newcon = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(oldcon, newcon, new String[]{"id"});
				newcon.setDbName("Drg5001Db");
				newcon.setUpLoadId(obj.getId());
				newcon.setIsInsert("N");
				ServiceGetter.getInstance().getCommonService().save(newcon);
			}
		}
		
		//將新檔案傳回內網
		doCopyCon0001Db(drg5001old,drg01,"2");
		
		//歷程紀錄
		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG01",obj.getDrg0001Id(), obj.getApplNo(),newStatus, "案件登錄 - 主動補件", ref.getUserID());
		
		//發送Mail		  
        String title="【主動補件】",mailBody="",mail="";		  
		String email = View.getLookupField("select userEmail from CommonUser where userid="+Common.sqlChar(drg01.getChargeMan()));
		    
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
		ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, null, false,null, null, null,"DRG01",Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):obj.getApplNo());
		TCBWCommon.setMailbackup("DRG",Common.get(obj.getDrg0001Id()),title, mailBody, obj.getApplNo(),"", ref.getUserID(),"");
		
	}	
	
	
	public String getMaxRevisionByDrg6001Db(Long drg4001Id){
		String maxRevision = "1";
		if(null != drg4001Id && drg4001Id > 0){
			maxRevision = View.getLookupField(" select max(revision) from Drg6001Db where drg4001Id = " + drg4001Id);
			if(null != maxRevision && !"".equals(maxRevision)){
				maxRevision = Common.get(Common.getInt(maxRevision)+1);
			}
		}
		return maxRevision;
	}
	
	public void sendDrgMailToChargeMan(Drg4001Db drg41, String mailBody, List<String> attFilePathList){
		try {
			if(null != drg41.getChargeMan() && !"".equals(drg41.getChargeMan())){
				String userEmail = View.getLookupField("select userEmail from CommonUser where userId = "+Common.sqlChar(drg41.getChargeMan()));
				if(null != userEmail && !"".equals(userEmail)){
					String title = "【補件回覆】案號:"+drg41.getApplNo();
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					String[] mailAddress = userEmail.split(",");
					if(mailAddress!=null && mailAddress.length>0)
					{
						for(String s : mailAddress)
						{
							javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
						    p.setAddress(s);
						    mailList.add(p);
						}
					}					
					ServiceGetter.getInstance().getTcbwService().sendEmail(title, mailBody, mailList, attFilePathList, false,null, null, null,"DRG02",Common.get(drg41.getApplNo()).equals("")?Common.get(drg41.getId()):drg41.getApplNo());
				    TCBWCommon.setMailbackup("DRG2",Common.get(drg41.getId()),title, mailBody, drg41.getApplNo(),"", TCBWCommon.getCurrentUserId(),"");	
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
