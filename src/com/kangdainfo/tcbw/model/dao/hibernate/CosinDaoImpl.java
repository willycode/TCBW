package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections.set.ListOrderedSet;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.Validate;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0004Db;
import com.kangdainfo.tcbw.model.bo.Cos0005Db;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;
import com.kangdainfo.tcbw.model.bo.Cos0007Db;
import com.kangdainfo.tcbw.model.bo.Cos0008Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;
import com.kangdainfo.tcbw.model.bo.Cos0011Db;
import com.kangdainfo.tcbw.model.bo.Cos0012Db;
import com.kangdainfo.tcbw.model.bo.Cos0013Db;
import com.kangdainfo.tcbw.model.bo.Cos0014Db;
import com.kangdainfo.tcbw.model.bo.Cos0015Db;
import com.kangdainfo.tcbw.model.bo.Cos0016Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;
import com.kangdainfo.tcbw.model.bo.Cos4004Db;
import com.kangdainfo.tcbw.model.bo.Cos4005Db;
import com.kangdainfo.tcbw.model.dao.CosinDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.comple.COMPLE1002F;
import com.kangdainfo.tcbw.view.cosex.COSEX0102F;
import com.kangdainfo.tcbw.view.cosin.COSIN0302F;
import com.kangdainfo.tcbw.view.cosin.COSIN0402F;
import com.kangdainfo.tcbw.view.cosin.COSIN0502F;
import com.kangdainfo.tcbw.view.cosin.COSIN0602F;
import com.kangdainfo.tcbw.view.cosin.COSIN0702F;
import com.kangdainfo.tcbw.view.cosin.COSIN0802F;
import com.kangdainfo.tcbw.view.cosin.COSIN0902F;
import com.kangdainfo.tcbw.view.cosin.COSIN1002F;

public class CosinDaoImpl extends BaseDaoImpl implements CosinDao {
	
	@Override
	public void insertByCOSIN0302F(COSIN0302F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[COSEX0102F]-[新增]-[無法辨別登入的使用者]");
		}
		
		Cos4001Db obj = new Cos4001Db();
		obj.setStatus("00");
		obj.setCosType(ref.getCosType());
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		obj.setModifier(c.getUserId());
		obj.setModifyDate(Datetime.getYYYMMDD());
		obj.setModifyTime(Datetime.getHHMMSS());
		save(obj);
		
		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", obj.getId(), "", "00", "新增一筆登錄資料", c.getUserId());
		ref.setId(Common.get(obj.getId()));
		
	}

	@Override
	public void updateByCOSIN0302F(COSIN0302F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String msg = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "化妝品通報登入作業-內部-暫存";
			msg = "暫存完成";
		}else if("doSend".equals(ref.getState())){
			logName = "化妝品通報登入作業-內部-送出";
			msg = "送出完成";
		}
		
		Cos4001Db obj = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Cos4001Db();
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			save(obj);
			logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0001_DB，新建一筆]");
		}
		
		// 案件狀態處理
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
		}
		
		// 不良事件類別處理
		if("1".equals(ref.getCosType()) && ("Y".equals(ref.getIsDamageYn()) || "O".equals(ref.getIsDamageYn()))){
			obj.setCosType("3");
		}else if("1".equals(ref.getCosType()) && "N".equals(ref.getIsDamageYn())){
			obj.setCosType("1");
		}else{
			obj.setCosType(ref.getCosType());
		}
		if("doSend".equals(ref.getState())){
			obj.setNotifierRepDate(yyymmdd);
		}
		obj.setCaseOwner(ref.getCaseOwner());
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierSource(ref.getNotifierSource());
		obj.setNotifierSourceOther(ref.getNotifierSourceOther());
		obj.setNotifierName(ref.getNotifierName());
		obj.setNotifierTelArea(ref.getNotifierTelArea());
		obj.setNotifierTel(ref.getNotifierTel());
		obj.setNotifierTelExt(ref.getNotifierTelExt());
		obj.setNotifierEamil(ref.getNotifierEamil());
		obj.setNotifierType(ref.getNotifierType());
		obj.setNotifierDeptId(ref.getNotifierDeptID());
		obj.setNotifierDept(ref.getNotifierDept());
		obj.setNotifierTitle(ref.getNotifierTitle());
		obj.setNotifierArea(ref.getNotifierArea());
		obj.setNotifierZipCode(ref.getNotifierZipCode());
		obj.setAddress(ref.getAddress());
		obj.setIsContactYn(ref.getIsContactYn());
		obj.setIsDamageYn(ref.getIsDamageYn());
		obj.setOtherIsDamageYn(ref.getOtherIsDamageYn());
		obj.setOtherInformation(ref.getOtherInformation());
		obj.setOtherExplain(ref.getOtherExplain());
		
		obj.setChProduct(ref.getChProduct());
		obj.setEnProduct(ref.getEnProduct());
		obj.setPermitKey(ref.getPermitKey());
		obj.setPermitNo(ref.getPermitNo());
		obj.setTraffickWay(ref.getTraffickWay());
		obj.setTraffickWayOther(ref.getTraffickWayOther());
		obj.setBusinessName(ref.getBusinessName());
		obj.setManufactorID(ref.getManufactorID());
		obj.setManufactorName(ref.getManufactorName());
		obj.setManufactorTelArea(ref.getManufactorTelArea());
		obj.setManufactorTel(ref.getManufactorTel());
		obj.setManufactorTelExt(ref.getManufactorTelExt());
		obj.setManufactorArea(ref.getManufactorArea());
		obj.setManufactorZipCode(ref.getManufactorZipCode());
		obj.setManufactorAddr(ref.getManufactorAddr());
		obj.setManufactorNo(ref.getManufactorNo());
		obj.setExpirationDate(ref.getExpirationDate());
		obj.setTradeDate(ref.getTradeDate());
		obj.setTradePlace(ref.getTradePlace());
		obj.setTradePlaceZipCode(ref.getTradePlaceZipCode());
		obj.setTradePlaceAddr(ref.getTradePlaceAddr());
		obj.setIsSampleYn(ref.getIsSampleYn());
		obj.setEvenContactYn(ref.getEvenContactYn());
		obj.setDealWith(ref.getDealWith());
		obj.setIsSimilarYn(ref.getIsSimilarYn());
		obj.setIsRecurrenceYn(ref.getIsRecurrenceYn());
		obj.setIsOtherDeptYn(ref.getIsOtherDeptYn());
		obj.setOtherDpetName(ref.getOtherDpetName());
		obj.setCaseNo(ref.getCaseNo());
		obj.setIngredient(ref.getIngredient());
		obj.setModifier(ref.getUserID());
		obj.setModifyDate(yyymmdd);
		obj.setModifyTime(hhmmss);
		
		if("1".equals(obj.getCosType()) || "3".equals(obj.getCosType())){
			java.util.Set cos4003Dbs = obj.getCos4003Dbs();
			if(cos4003Dbs == null){
				cos4003Dbs = new ListOrderedSet();
				obj.setCos4003Dbs(cos4003Dbs);
			}
			java.util.Map<String, Cos4003Db> oldCos4003DbMap = new java.util.HashMap<String, Cos4003Db>();
			for(Object dtlObj : cos4003Dbs){
				Cos4003Db dtl = (Cos4003Db)dtlObj;
				oldCos4003DbMap.put(Common.get(dtl.getMainCode()), dtl);
			}
			
			java.util.List<Cos4003Db> cos4003DbDList = new java.util.ArrayList<Cos4003Db>();
			if(ref.getSubCode()!=null && ref.getSubCode().length>0){
				for(Map.Entry<String, Cos4003Db> cos : oldCos4003DbMap.entrySet()){
					boolean flag = false;
					for(String rid : ref.getSubCode()){
						if(rid.length() != 4){
							continue;
						}
						if(rid.substring(0, 2).equals(Common.get(cos.getKey()).substring(0, 2))){
							flag = true;
						}
						if(flag){
							break;
						}
					}
					if(!flag){
						cos4003DbDList.add(cos.getValue());
					}
				}
				
				// 取得代碼其他的輸入值，大類須有設定才可寫入資料，且描述資料，只能有一筆，前端大類輸出的排序也須根據 CODE_ID
				java.util.List<CommonCode> commonCodeList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("COSDPD");
				if(commonCodeList!=null && commonCodeList.size()>0){
					for(int i=0; i<commonCodeList.size(); i++){
						CommonCode dtl = commonCodeList.get(i);
						
						boolean isHasSub = false;
						boolean isHasOther = false;							
						StringBuffer sb = new StringBuffer();
						for(String codeId : ref.getSubCode()){
							if(codeId.length() != 4){
								continue;
							}
							if(codeId.substring(0, 2).equals(Common.get(dtl.getCodeId()).substring(0, 2))){
								if(sb.toString().length() > 0){
									sb.append(",");
								}
								sb.append(codeId);
								
								isHasSub = true;						// 判斷該大類是否有子類被選取							
								if(codeId.indexOf("99") != -1){			// 判斷是否有需描述的CODE被選取	
									isHasOther = true;
								}
							}
						}
						if(isHasSub){
							Cos4003Db cos4003Db = oldCos4003DbMap.get(Common.get(dtl.getCodeId()));
							if(cos4003Db == null){
								cos4003Db = new Cos4003Db();
								cos4003Db.setCos4001Db(obj);
								cos4003Db.setMainCode(Common.get(dtl.getCodeId()));
								cos4003Db.setCreator(ref.getUserID());
								cos4003Db.setCreateDate(yyymmdd);
								cos4003Db.setCreateTime(hhmmss);
							}
							if(isHasOther){
								cos4003Db.setOtherDescribe(Common.get(ref.getOtherDescribe()[i]));
							}else{
								cos4003Db.setOtherDescribe("");
							}
							cos4003Db.setSubCode(sb.toString());
							cos4003Db.setModifier(ref.getUserID());
							cos4003Db.setModifyDate(yyymmdd);
							cos4003Db.setModifyTime(hhmmss);
							cos4003Dbs.add(cos4003Db);
						}
					}
					commonCodeList.clear();
				}
			}else{
				for(Map.Entry<String, Cos4003Db> cos : oldCos4003DbMap.entrySet()){
					cos4003DbDList.add(cos.getValue());
				}
			}
			if(cos4003DbDList.size() > 0){
				for(Cos4003Db dtl : cos4003DbDList){
					dtl.getCos4001Db().getCos4003Dbs().remove(dtl);
					dtl.setCos4001Db(null);
				}
				deleteBatch(cos4003DbDList);
				cos4003DbDList.clear();
			}
			
		}
		
		if("2".equals(obj.getCosType()) || "3".equals(obj.getCosType())){
			java.util.Set cos4002Dbs = obj.getCos4002Dbs();
			if(cos4002Dbs == null){
				cos4002Dbs = new ListOrderedSet();
				obj.setCos4002Dbs(cos4002Dbs);
			}
			Cos4002Db cos4002Db = null;
			if(cos4002Dbs.size() > 0){
				for(Object dtlObj : cos4002Dbs){
					cos4002Db = (Cos4002Db)dtlObj;
					if(cos4002Db != null)
						break;
				}
			}
			if(cos4002Db == null){
				cos4002Db = new Cos4002Db();
				cos4002Db.setCos4001Db(obj);
				cos4002Db.setCreator(ref.getUserID());
				cos4002Db.setCreateDate(yyymmdd);
				cos4002Db.setCreateTime(hhmmss);
				cos4002Dbs.add(cos4002Db);
			}
			cos4002Db.setAdverseCondition(ref.getAdverseCondition());
			cos4002Db.setNonSeriousOther(ref.getNonSeriousOther());
			cos4002Db.setNonSeriousDis(ref.getNonSeriousDis());
			cos4002Db.setUseDateS(ref.getUseDateS());
			cos4002Db.setUseDateE(ref.getUseDateE());
			cos4002Db.setUseRate(ref.getUseRate());
			cos4002Db.setUseMethod(ref.getUseMethod());
			cos4002Db.setIsMitigateYn(ref.getIsMitigateYn());
			cos4002Db.setIsRecurYn(ref.getIsRecurYn());
			cos4002Db.setDiagnosisProof(ref.getDiagnosisProof());
			cos4002Db.setDiagnosisReport(ref.getDiagnosisReport());
			cos4002Db.setDiagnosisOther(ref.getDiagnosisOther());
			cos4002Db.setModifier(ref.getUserID());
			cos4002Db.setModifyDate(yyymmdd);
			cos4002Db.setModifyTime(hhmmss);
			saveOrUpdate(cos4002Db);
			
			// 相關檢查及檢驗數據
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCos4004Db(cos4002Db, ref.getHttpRequest(), ref.getCOS4004DbRow(), ref.arrCOS4004DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS4004_DB發生異常，COS4002_DB.ID : " + cos4002Db.getId() + "]");
				e.printStackTrace();
			}
			
			// 相關附件(相關檢查及檢驗數據)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSID", "COS", ref.getCOSIDFileRow(), "COS4002DB", ref.arrCOSIDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(醫師診斷證明)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSDP", "COS", ref.getCOSDPFileRow(), "COS4002DB", ref.arrCOSDPFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(就醫紀錄)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSSD", "COS", ref.getCOSSDFileRow(), "COS4002DB", ref.arrCOSSDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 併用的其他化妝品
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCos4005Db(cos4002Db, ref.getHttpRequest(), ref.getCOS4005DbRow(), ref.arrCOS4005DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS4005_DB發生異常，COS4002_DB.ID : " + cos4002Db.getId() + "]");
				e.printStackTrace();
			}
		}
		saveOrUpdate(obj);
		
		// 案件本相關資料處理
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(obj.getId()), "C", "COS", ref.getCFileRow(), "COS4001DB", ref.arrCFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		
		if("10".equals(obj.getStatus())){
			ServiceGetter.getInstance().getTcbwService().getCosexDao().updateDoCopyCos4001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
		}
		ref.setId(Common.get(obj.getId()));
		
		// 寫入流程
		if("pauseSave".equals(ref.getState())){
			logName = "暫存";
		}else if("doSend".equals(ref.getState())){
			logName = "送出";
		}
		
		if("doSend".equals(ref.getState())){
			// 送出後，需將流程檔的ID換成內部新增好的COS0001_DB.ID
			long cos0001ID = Common.getLong(View.getLookupField(" select id from Cos0001Db where cos4001DbId = " + obj.getId()));
			java.util.List<Con2001Db> con2001DbList = load(" from Con2001Db where systemType = 'COS01' and formID = " + obj.getId());
			if(con2001DbList!=null && con2001DbList.size()>0){
				for(Con2001Db con2001Db : con2001DbList){
					con2001Db.setFormID(cos0001ID);
				}
				updateBatch(con2001DbList);
				con2001DbList.clear();
			}
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", cos0001ID, Common.get(obj.getApplNo()), obj.getStatus(), logName, ref.getUserID());
		}
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}

	@Override
	public void deleteByCOSIN0302F(COSIN0302F ref) throws Exception {
		Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(ref.getId()));
		if(cos4001Db != null){
			if(cos4001Db.getCos4002Dbs()!=null && cos4001Db.getCos4002Dbs().size()>0){
				for(Object dtlObj : cos4001Db.getCos4002Dbs()){
					Cos4002Db cos4002Db = (Cos4002Db)dtlObj;
					
					java.util.List<Con0001Db> con0001DbsList = load(" from Con0001Db where fileKind in ('COSSD','COSDP') and dbName = 'COS4002DB' and upLoadId = " + Common.getLong(cos4002Db.getId()));
					if(con0001DbsList!=null && con0001DbsList.size()>0){
						for(Con0001Db dtl : con0001DbsList){
							String fileID = Common.isoToBig5(Common.get(dtl.getFileRoute()));
							File dir = new File("./upload/COS" + File.separator + fileID);
							try{
								Common.RemoveDirectory(dir);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						deleteBatch(con0001DbsList);
					}
					
					java.util.List<Cos4004Db> cos4004DbsList = load(" from Cos4004Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
					java.util.List<Cos4005Db> cos4005DbsList = load(" from Cos4005Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
					deleteBatch(cos4004DbsList);
					deleteBatch(cos4005DbsList);
				}
			}
			java.util.List<Con0001Db> con0001DbsList = load(" from Con0001Db where fileKind = 'C' and dbName = 'COS4001DB' and upLoadId = " + Common.getLong(cos4001Db.getId()));
			if(con0001DbsList!=null && con0001DbsList.size()>0){
				for(Con0001Db dtl : con0001DbsList){
					String fileID = Common.isoToBig5(Common.get(dtl.getFileRoute()));
					File dir = new File("./upload/COS" + File.separator + fileID);
					try{
						Common.RemoveDirectory(dir);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				deleteBatch(con0001DbsList);
			}
			delete(cos4001Db);
			
			// 流程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", Common.getLong(ref.getId()), "", "", "放棄新增登錄資料", ref.getUserID());
		}
		
		ref.setId("");
	}

	@Override
	public void updateByCOSIN0402F(COSIN0402F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "化妝品通報-審核作業-暫存";
			msg = "暫存完成";
		}else if("acceptCase".equals(ref.getState())){
			logName = "化妝品通報-審核作業-受理";
			msg = "受理完成";
		}
		
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Cos0001Db();
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0001_DB，新建一筆]");
		}
		
		// 案件狀態處理
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("10");
		}
		// 作業人員
		if("pauseSave".equals(ref.getState())){
			obj.setChargeMan(ref.getUserID());
		}
		
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierSource(ref.getNotifierSource());
		obj.setNotifierSourceOther(ref.getNotifierSourceOther());
//		obj.setNotifierName(ref.getNotifierName());						// 通報者資料不能異動
//		obj.setNotifierTel(ref.getNotifierTel());
//		obj.setNotifierEamil(ref.getNotifierEamil());
//		obj.setNotifierDept(ref.getNotifierDept());
//		obj.setNotifierType(ref.getNotifierType());
//		obj.setNotifierTitle(ref.getNotifierTitle());
//		obj.setAddress(ref.getAddress());
		obj.setIsContactYn(ref.getIsContactYn());
		obj.setIsDamageYn(ref.getIsDamageYn());
		obj.setOtherInformation(ref.getOtherInformation());
		obj.setOtherExplain(ref.getOtherExplain());
		
		obj.setChProduct(ref.getChProduct());
		obj.setEnProduct(ref.getEnProduct());
		obj.setPermitKey(ref.getPermitKey());
		obj.setPermitNo(ref.getPermitNo());
		obj.setTraffickWay(ref.getTraffickWay());
		obj.setTraffickWayOther(ref.getTraffickWayOther());
		obj.setBusinessName(ref.getBusinessName());
		obj.setManufactorName(ref.getManufactorName());
		obj.setManufactorTelArea(ref.getManufactorTelArea());
		obj.setManufactorTel(ref.getManufactorTel());
		obj.setManufactorTelExt(ref.getManufactorTelExt());
		obj.setManufactorArea(ref.getManufactorArea());
		obj.setManufactorZipCode(ref.getManufactorZipCode());
		obj.setManufactorAddr(ref.getManufactorAddr());
		obj.setManufactorNo(ref.getManufactorNo());
		obj.setExpirationDate(ref.getExpirationDate());
		obj.setTradeDate(ref.getTradeDate());
		obj.setTradePlace(ref.getTradePlace());
		obj.setTradePlaceZipCode(ref.getTradePlaceZipCode());
		obj.setTradePlaceAddr(ref.getTradePlaceAddr());
		obj.setIsSampleYn(ref.getIsSampleYn());
		obj.setEvenContactYn(ref.getEvenContactYn());
		obj.setDealWith(ref.getDealWith());
		obj.setIsSimilarYn(ref.getIsSimilarYn());
		obj.setIsRecurrenceYn(ref.getIsRecurrenceYn());
		obj.setIsOtherDeptYn(ref.getIsOtherDeptYn());
		obj.setOtherDpetName(ref.getOtherDpetName());
		obj.setCaseNo(ref.getCaseNo());
		obj.setIngredient(ref.getIngredient());
		obj.setModifier(ref.getUserID());
		obj.setModifyDate(yyymmdd);
		obj.setModifyTime(hhmmss);
		
		if("1".equals(obj.getCosType()) || "3".equals(obj.getCosType())){
			updateCos0003Db(obj, ref.getSubCode(), ref.getOtherDescribe(), ref.getUserID(), yyymmdd, hhmmss);
		}
		
		if("2".equals(obj.getCosType())){
			java.util.Set cos0002Dbs = obj.getCos0002Dbs();
			if(cos0002Dbs == null){
				cos0002Dbs = new ListOrderedSet();
				obj.setCos0002Dbs(cos0002Dbs);
			}
			Cos0002Db cos0002Db = null;
			if(cos0002Dbs.size() > 0){
				for(Object dtlObj : cos0002Dbs){
					cos0002Db = (Cos0002Db)dtlObj;
					if(cos0002Db != null)
						break;
				}
			}
			if(cos0002Db == null){
				cos0002Db = new Cos0002Db();
				cos0002Db.setCos0001Db(obj);
				cos0002Db.setCreator(ref.getUserID());
				cos0002Db.setCreateDate(yyymmdd);
				cos0002Db.setCreateTime(hhmmss);
				cos0002Dbs.add(cos0002Db);
			}
			cos0002Db.setAdverseCondition(ref.getAdverseCondition());
			cos0002Db.setNonSeriousOther(ref.getNonSeriousOther());
			cos0002Db.setNonSeriousAR(ref.getNonSeriousAR());
			cos0002Db.setNonSeriousAROther(ref.getNonSeriousAROther());
			cos0002Db.setNonSeriousDis(ref.getNonSeriousDis());
			cos0002Db.setUseDateS(ref.getUseDateS());
			cos0002Db.setUseDateE(ref.getUseDateE());
			cos0002Db.setUseRate(ref.getUseRate());
			cos0002Db.setUseMethod(ref.getUseMethod());
			cos0002Db.setIsMitigateYn(ref.getIsMitigateYn());
			cos0002Db.setIsRecurYn(ref.getIsRecurYn());
			cos0002Db.setDiagnosisProof(ref.getDiagnosisProof());
			cos0002Db.setDiagnosisReport(ref.getDiagnosisReport());
			cos0002Db.setDiagnosisOther(ref.getDiagnosisOther());
			cos0002Db.setModifier(ref.getUserID());
			cos0002Db.setModifyDate(yyymmdd);
			cos0002Db.setModifyTime(hhmmss);
			saveOrUpdate(cos0002Db);
			
			// 相關檢查及檢驗數據
			try{
				updateCos0004Db(cos0002Db, ref.getHttpRequest(), ref.getCOS0004DbRow(), ref.arrCOS0004DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0004_DB發生異常，COS0002_DB.ID : " + cos0002Db.getId() + "]");
				e.printStackTrace();
			}
			
			// 相關附件(醫師診斷證明)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0002Db.getId()), "COSDP", "COS", ref.getCOSDPFileRow(), "COS0002DB", ref.arrCOSDPFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(就醫紀錄)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0002Db.getId()), "COSSD", "COS", ref.getCOSSDFileRow(), "COS0002DB", ref.arrCOSSDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 併用的其他化妝品
			try{
				updateCos0005Db(cos0002Db, ref.getHttpRequest(), ref.getCOS0005DbRow(), ref.arrCOS0005DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0005_DB發生異常，COS0002_DB.ID : " + cos0002Db.getId() + "]");
				e.printStackTrace();
			}
		}
		saveOrUpdate(obj);
		ref.setId(Common.get(obj.getId()));
		
		// 案件本相關資料處理
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(obj.getId()), "C", "COS", ref.getCFileRow(), "COS0001DB", ref.arrCFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		
		// 案件審核
		if("acceptCase".equals(ref.getState())){
			try{
				updateAcceptCase(obj, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[案件受理發生異常，COS0001_DB.ID : " + obj.getId() + "]");
				e.printStackTrace();
			}
			try{
				if(!"".equals(Common.get(obj.getNotifierEamil())) && Validate.checkEmail(Common.get(obj.getNotifierEamil()))) {
					java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					mailList.add(new javax.mail.internet.InternetAddress(Common.get(obj.getNotifierEamil())));
					
					String mailTitle = "通報案件已受理";
					StringBuffer mailBody = new StringBuffer();
					String[] mailRef =  TCBWCommon.getCon1001DbMail("COS", "COS01", "COS010002");
					String applNoCOS = Common.get(cosMap.get(Common.get(obj.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(obj.getCosType())) + Common.get(obj.getApplNo());
					if(mailRef!=null && mailRef.length==2){
						mailTitle = mailRef[0].replace("[T_品名]", obj.getChProduct());					// 變更標題內容
						
						mailRef[1] = mailRef[1].replace("[F_通報者]", obj.getNotifierName());			// 變更通報者名稱
						String applNoData = applNoCOS;													// 變更案號內容	
						if(!"".equals(Common.get(obj.getForeignApplNo()))){
							applNoData += "、" + Common.get(obj.getForeignApplNo());
						}
						mailRef[1] = mailRef[1].replace("[F_案號]", applNoData);
						mailBody.append(mailRef[1]);
					}else{
						mailBody.append(applNoCOS).append("已受理");
					}
					
					ServiceGetter.getInstance().getTcbwService().sendEmail(mailTitle, mailBody.toString(), mailList, null, true, null, null, null, "COS", Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):Common.get(obj.getApplNo()));
				}else{
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[發送信件發生異常，COS0001_DB.ID : " + obj.getId() + "，通報者信箱 : " + obj.getNotifierEamil() + "]");
				}
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[發送信件發生異常，COS0001_DB.ID : " + obj.getId() + "]");
				e.printStackTrace();
			}
		}
		
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}
	
	public void updateCos0003Db(Cos0001Db obj, String[] subCode, String[] otherDescribe, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Set cos0003Dbs = obj.getCos0003Dbs();
		if(cos0003Dbs == null){
			cos0003Dbs = new ListOrderedSet();
			obj.setCos0003Dbs(cos0003Dbs);
		}
		java.util.Map<String, Cos0003Db> oldCos0003DbMap = new java.util.HashMap<String, Cos0003Db>();
		for(Object dtlObj : cos0003Dbs){
			Cos0003Db dtl = (Cos0003Db)dtlObj;
			oldCos0003DbMap.put(Common.get(dtl.getMainCode()), dtl);
		}
		
		java.util.List<Cos0003Db> cos0003DbDList = new java.util.ArrayList<Cos0003Db>();
		if(subCode!=null && subCode.length>0){
			for(Map.Entry<String, Cos0003Db> cos : oldCos0003DbMap.entrySet()){
				boolean flag = false;
				for(String rid : subCode){
					if(rid.length() != 4){
						continue;
					}
					if(rid.substring(0, 2).equals(Common.get(cos.getKey()).substring(0, 2))){
						flag = true;
					}
					if(flag){
						break;
					}
				}
				if(!flag){
					cos0003DbDList.add(cos.getValue());
				}
			}
			
			// 取得代碼其他的輸入值，大類須有設定才可寫入資料，且描述資料，只能有一筆，前端大類輸出的排序也須根據 CODE_ID
			java.util.List<CommonCode> commonCodeList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("COSDPD");
			if(commonCodeList!=null && commonCodeList.size()>0){
				for(int i=0; i<commonCodeList.size(); i++){
					CommonCode dtl = commonCodeList.get(i);
					
					boolean isHasSub = false;
					boolean isHasOther = false;							
					StringBuffer sb = new StringBuffer();
					for(String codeId : subCode){
						if(codeId.length() != 4){
							continue;
						}
						if(codeId.substring(0, 2).equals(Common.get(dtl.getCodeId()).substring(0, 2))){
							if(sb.toString().length() > 0){
								sb.append(",");
							}
							sb.append(codeId);
							
							isHasSub = true;						// 判斷該大類是否有子類被選取							
							if(codeId.indexOf("99") != -1){			// 判斷是否有需描述的CODE被選取	
								isHasOther = true;
							}
						}
					}
					if(isHasSub){
						Cos0003Db cos0003Db = oldCos0003DbMap.get(Common.get(dtl.getCodeId()));
						if(cos0003Db == null){
							cos0003Db = new Cos0003Db();
							cos0003Db.setCos0001Db(obj);
							cos0003Db.setMainCode(Common.get(dtl.getCodeId()));
							cos0003Db.setCreator(userId);
							cos0003Db.setCreateDate(yyymmdd);
							cos0003Db.setCreateTime(hhmmss);
						}
						if(isHasOther){
							cos0003Db.setOtherDescribe(Common.get(otherDescribe[i]));
						}else{
							cos0003Db.setOtherDescribe("");
						}
						cos0003Db.setSubCode(sb.toString());
						cos0003Db.setModifier(userId);
						cos0003Db.setModifyDate(yyymmdd);
						cos0003Db.setModifyTime(hhmmss);
						cos0003Dbs.add(cos0003Db);
					}
				}
				commonCodeList.clear();
			}
		}else{
			for(Map.Entry<String, Cos0003Db> cos : oldCos0003DbMap.entrySet()){
				cos0003DbDList.add(cos.getValue());
			}
		}
		if(cos0003DbDList.size() > 0){
			for(Cos0003Db dtl : cos0003DbDList){
				dtl.getCos0001Db().getCos0003Dbs().remove(dtl);
				dtl.setCos0001Db(null);
			}
			deleteBatch(cos0003DbDList);
			cos0003DbDList.clear();
		}
	}
	
	
	public void updateCos0004Db(Cos0002Db cos0002Db, javax.servlet.ServletRequest httpRequest, String[] COS0004DbRow, String[] arrCOS0004DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos0004Db> oCos04Map = new java.util.HashMap<Long, Cos0004Db>();  
		java.util.List<Cos0004Db> cos04List = load(" from Cos0004Db where cos0002Db.id = " + Common.getLong(cos0002Db.getId()));
		if(cos04List!=null && cos04List.size()>0){
			for(Cos0004Db dtl : cos04List){
				oCos04Map.put(dtl.getId(), dtl);
			}
			cos04List.clear();
		}
		java.util.List<Cos0004Db> dCos04List = new java.util.ArrayList<Cos0004Db>();
		if(httpRequest!=null && COS0004DbRow!=null && COS0004DbRow.length>0){
			if(oCos04Map.size() > 0){
				for(Map.Entry<Long, Cos0004Db> dtl : oCos04Map.entrySet()){
					boolean flag = true;
					for(String rid : COS0004DbRow){
						if(rid.equals(Common.get(dtl.getKey()))){
							flag = false;
						}
						if(!flag)	break;
					}
					if(flag){
						dCos04List.add(dtl.getValue());
					}
				}
			}
			for(String rid : COS0004DbRow){
				boolean isUpdate = true;
				Cos0004Db cos0004Db = oCos04Map.get(Common.getLong(rid));
				if(cos0004Db == null){
					cos0004Db = new Cos0004Db();
					cos0004Db.setCos0002Db(cos0002Db);
					cos0004Db.setCreator(userId);
					cos0004Db.setCreateDate(yyymmdd);
					cos0004Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos0004Db.setTestDate(httpRequest.getParameter(arrCOS0004DbFieldName[0] + rid));
				cos0004Db.setTestItems(httpRequest.getParameter(arrCOS0004DbFieldName[1] + rid));
				cos0004Db.setTestNum(httpRequest.getParameter(arrCOS0004DbFieldName[2] + rid));
				cos0004Db.setModifier(userId);
				cos0004Db.setModifyDate(yyymmdd);
				cos0004Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos0004Db);
				}else{
					save(cos0004Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos0004Db> dtl : oCos04Map.entrySet()){
				dCos04List.add(dtl.getValue());
			}
		}
		if(dCos04List.size() > 0){
			deleteBatch(dCos04List);
			dCos04List.clear();
		}
		oCos04Map.clear();
	}
	
	public void updateCos0005Db(Cos0002Db cos0002Db, javax.servlet.ServletRequest httpRequest, String[] COS0005DbRow, String[] arrCOS0005DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos0005Db> oCos05Map = new java.util.HashMap<Long, Cos0005Db>();  
		java.util.List<Cos0005Db> cos05List = load(" from Cos0005Db where cos0002Db.id = " + Common.getLong(cos0002Db.getId()));
		if(cos05List!=null && cos05List.size()>0){
			for(Cos0005Db dtl : cos05List){
				oCos05Map.put(dtl.getId(), dtl);
			}
			cos05List.clear();
		}
		java.util.List<Cos0005Db> dCos05List = new java.util.ArrayList<Cos0005Db>();
		if(httpRequest!=null && COS0005DbRow!=null && COS0005DbRow.length>0){
			if(oCos05Map.size() > 0){
				for(Map.Entry<Long, Cos0005Db> dtl : oCos05Map.entrySet()){
					boolean flag = true;
					for(String rid : COS0005DbRow){
						if(rid.equals(Common.get(dtl.getKey()))){
							flag = false;
						}
						if(!flag)	break;
					}
					if(flag){
						dCos05List.add(dtl.getValue());
					}
				}
			}
			for(String rid : COS0005DbRow){
				boolean isUpdate = true;
				Cos0005Db cos0005Db = oCos05Map.get(Common.getLong(rid));
				if(cos0005Db == null){
					cos0005Db = new Cos0005Db();
					cos0005Db.setCos0002Db(cos0002Db);
					cos0005Db.setCreator(userId);
					cos0005Db.setCreateDate(yyymmdd);
					cos0005Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos0005Db.setcName(httpRequest.getParameter(arrCOS0005DbFieldName[0] + rid));
				cos0005Db.setManufactorName(httpRequest.getParameter(arrCOS0005DbFieldName[1] + rid));
				cos0005Db.setUseDateS(httpRequest.getParameter(arrCOS0005DbFieldName[2] + rid));
				cos0005Db.setUseDateE(httpRequest.getParameter(arrCOS0005DbFieldName[3] + rid));
				cos0005Db.setUseRate(httpRequest.getParameter(arrCOS0005DbFieldName[4] + rid));
				cos0005Db.setUseMethod(httpRequest.getParameter(arrCOS0005DbFieldName[5] + rid));
				cos0005Db.setManufactorNo(httpRequest.getParameter(arrCOS0005DbFieldName[6] + rid));
				cos0005Db.setExpirationDate(httpRequest.getParameter(arrCOS0005DbFieldName[7] + rid));
				cos0005Db.setTradeDate(httpRequest.getParameter(arrCOS0005DbFieldName[8] + rid));
				cos0005Db.setModifier(userId);
				cos0005Db.setModifyDate(yyymmdd);
				cos0005Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos0005Db);
				}else{
					save(cos0005Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos0005Db> dtl : oCos05Map.entrySet()){
				dCos05List.add(dtl.getValue());
			}
		}
		if(dCos05List.size() > 0){
			deleteBatch(dCos05List);
			dCos05List.clear();
		}
		oCos05Map.clear();
	}
	
	@Override
	public void updateByCOSIN0402FDoUnAcceptCase(COSIN0402F ref) throws Exception {
		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(cos0001Db != null){
			cos0001Db.setStatus("02");
			cos0001Db.setChargeMan("");
			cos0001Db.setModifier(ref.getUserID());
			cos0001Db.setModifyDate(Datetime.getYYYMMDD());
			cos0001Db.setModifyTime(Datetime.getHHMMSS());
			update(cos0001Db);
			
			Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(cos0001Db.getCos4001DbId()));
			if(cos4001Db != null){
				cos4001Db.setStatus("02");
				cos4001Db.setModifier(ref.getUserID());
				cos4001Db.setModifyDate(Datetime.getYYYMMDD());
				cos4001Db.setModifyTime(Datetime.getHHMMSS());
				update(cos4001Db);
			}
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", cos0001Db.getId(), Common.get(cos0001Db.getApplNo()), cos0001Db.getStatus(), "撤案，發送退件通知", ref.getUserID());
			
			ref.setErrorMsg("撤案完成 !");
			ref.setIsNeedBackQuery("Y");
			if("02".equals(cos0001Db.getStatus())) {
				//撤案後產生檔案
				closedPrint(Common.get(cos4001Db.getId()),Common.get(cos0001Db.getId()),ref.getUserID(),cos0001Db.getStatus());
			}
		}else{
			ref.setErrorMsg("查無該案件，重新操作 !");
			ref.setIsNeedBackQuery("N");
		}
	}

	public void updateAcceptCase(Cos0001Db obj, String userId, String yyymmdd, String hhmmss) throws Exception{
		if("3".equals(obj.getCosType())){
			String[] ignoreFields = new String[]{"id", "cos0002Dbs", "cos0003Dbs"};
			Cos0001Db master = new Cos0001Db();
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			master.setCreator(userId);
			master.setCreateDate(yyymmdd);
			master.setCreateTime(hhmmss);
			master.setCosType("1");
			master.setStatus("20");
			master.setAcceptDate(yyymmdd);
			master.setApplNo(TCBWCommon.getApplNo("COS02", "A01" , Datetime.getYYY()));
			master.setChargeMan(TCBWCommon.getUserID("COS02","02","Cos0001Db"));
			if(obj.getCos0003Dbs()!=null && obj.getCos0003Dbs().size()>0){							// 複製不良品COS0003_DB給新的COS0001_DB	
				String[] dtlIgnoreFields = new String[]{"id","cos0001Db"};
				java.util.Set cos0003Dbs = new ListOrderedSet();
				for(Object dtlObj : obj.getCos0003Dbs()){
					Cos0003Db cos0003Db = (Cos0003Db)dtlObj;
					
					Cos0003Db tmp = new Cos0003Db();
					org.springframework.beans.BeanUtils.copyProperties(cos0003Db, tmp, dtlIgnoreFields);
					tmp.setCos0001Db(master);
					tmp.setCreator(userId);
					tmp.setCreateDate(yyymmdd);
					tmp.setCreateTime(hhmmss);
					cos0003Dbs.add(tmp);
				}
				master.setCos0003Dbs(cos0003Dbs);
			}
			
			obj.setCosType("2");
			obj.setStatus("20");
			obj.setAcceptDate(yyymmdd);
			obj.setApplNo(TCBWCommon.getApplNo("COS03", "B01" , Datetime.getYYY()));
			obj.setChargeMan(TCBWCommon.getUserID("COS03","06","Cos0001Db"));
			if(obj.getCos0003Dbs()!=null && obj.getCos0003Dbs().size()>0){							// 原案件處理，刪除原案件的不良品資料COS0003_DB
				java.util.List<Cos0003Db> dList = new java.util.ArrayList<Cos0003Db>(); 
				for(Object dtlObj : obj.getCos0003Dbs()){
					Cos0003Db dtl = (Cos0003Db)dtlObj;
					dList.add(dtl);
				}
				if(dList.size() > 0){
					for(Cos0003Db dtl: dList){
						dtl.getCos0001Db().getCos0003Dbs().remove(dtl);
						dtl.setCos0001Db(null);
					}
					deleteBatch(dList);
					dList.clear();
				}
			}
			
			// 原始案件處理
			Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + obj.getCos4001DbId());
			if(cos4001Db != null){
				String[] cos4001DbFields = new String[]{"id", "cos4002Dbs", "cos4003Dbs"};
				Cos4001Db outMaster = new Cos4001Db();
				org.springframework.beans.BeanUtils.copyProperties(cos4001Db, outMaster, cos4001DbFields);
				outMaster.setCosType("1");
				outMaster.setStatus("20");
				outMaster.setApplNo(master.getApplNo());
				if(cos4001Db.getCos4003Dbs()!=null && cos4001Db.getCos4003Dbs().size()>0){								
					String[] cos4003IgnoreFields = new String[]{"id","cos4001Db"};
					java.util.Set cos4003Dbs = new ListOrderedSet();
					for(Object dtlObj : cos4001Db.getCos4003Dbs()){
						Cos4003Db cos4003Db = (Cos4003Db)dtlObj;
						Cos4003Db tmp = new Cos4003Db();
						org.springframework.beans.BeanUtils.copyProperties(cos4003Db, tmp, cos4003IgnoreFields);
						tmp.setCos4001Db(outMaster);
						
						cos4003Dbs.add(tmp);
					}
					outMaster.setCos4003Dbs(cos4003Dbs);
				}
				save(outMaster);
				master.setCos4001DbId(Common.getLong(outMaster.getId()));					// 重要，紀錄相對應案件的ID
				
				cos4001Db.setCosType("2");
				cos4001Db.setStatus("20");
				cos4001Db.setApplNo(obj.getApplNo());
				if(cos4001Db.getCos4003Dbs()!=null && cos4001Db.getCos4003Dbs().size()>0){
					java.util.List<Cos4003Db> dList = new java.util.ArrayList<Cos4003Db>(); 
					for(Object dtlObj : cos4001Db.getCos4003Dbs()){
						Cos4003Db dtl = (Cos4003Db)dtlObj;
						dList.add(dtl);
					}
					if(dList.size() > 0){
						for(Cos4003Db dtl: dList){
							dtl.getCos4001Db().getCos4003Dbs().remove(dtl);
							dtl.setCos4001Db(null);
						}
						deleteBatch(dList);
						dList.clear();
					}
				}
				update(cos4001Db);
				
				java.util.List<Con0001Db> cos0001DbFileList = load(" from Con0001Db where fileKind = " + Common.sqlChar("C") + " and dbName = 'COS4001DB' and upLoadId = " + Common.getLong(cos4001Db.getId()));
				if(cos0001DbFileList!=null && cos0001DbFileList.size()>0){
					java.util.List<Con0001Db> sCos0001DbFileList = new java.util.ArrayList<Con0001Db>();
					for(Con0001Db con0001Db : cos0001DbFileList){
						Con0001Db tmp = new Con0001Db();
						org.springframework.beans.BeanUtils.copyProperties(con0001Db, tmp, ignoreFields);
						tmp.setUpLoadId(outMaster.getId());
						sCos0001DbFileList.add(tmp);
					}
					saveBatch(sCos0001DbFileList);
					sCos0001DbFileList.clear();
					cos0001DbFileList.clear();
				}
			}
			
			// 新案件處理
			master.setForeignApplNo(obj.getApplNo());									
			save(master);
			java.util.List<Con0001Db> cos0001DbFileList = load(" from Con0001Db where fileKind = " + Common.sqlChar("C") + " and dbName = 'COS0001DB' and upLoadId = " + Common.getLong(obj.getId()));
			if(cos0001DbFileList!=null && cos0001DbFileList.size()>0){
				java.util.List<Con0001Db> sCos0001DbFileList = new java.util.ArrayList<Con0001Db>();
				for(Con0001Db con0001Db : cos0001DbFileList){
					Con0001Db tmp = new Con0001Db();
					org.springframework.beans.BeanUtils.copyProperties(con0001Db, tmp, ignoreFields);
					tmp.setUpLoadId(master.getId());
					tmp.setCreator(userId);
					tmp.setCreateDate(yyymmdd);
					tmp.setCreateTime(hhmmss);
					sCos0001DbFileList.add(tmp);
				}
				saveBatch(sCos0001DbFileList);
				sCos0001DbFileList.clear();
				cos0001DbFileList.clear();
			}
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", master.getId(), Common.get(master.getApplNo()), master.getStatus(), "受理完成", userId);
			
			// 複製一份審核前的流程給新案件
			java.util.List<Con2001Db> saveCon2001DbList = new java.util.ArrayList<Con2001Db>(); 
			java.util.List<Con2001Db> con2001DbList = load(" from Con2001Db where systemType = 'COS01' and formID = " + obj.getId());
			if(con2001DbList!=null && con2001DbList.size()>0){
				for(Con2001Db con2001Db : con2001DbList){
					Con2001Db newCon2001Db = new Con2001Db();
					org.springframework.beans.BeanUtils.copyProperties(con2001Db, newCon2001Db, new String[]{"id", "applNo", "formID"});
					newCon2001Db.setFormID(master.getId());
					saveCon2001DbList.add(newCon2001Db);
				}
				con2001DbList.clear();
			}
			if(saveCon2001DbList.size() > 0){
				saveBatch(saveCon2001DbList);
			}
			saveCon2001DbList.clear();
			
			// 原案件處理
			obj.setForeignApplNo(master.getApplNo());
			update(obj);
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "受理完成", userId);
		}else{
			if("1".equals(obj.getCosType())){
				obj.setApplNo(TCBWCommon.getApplNo("COS02", "A01" , Datetime.getYYY()));
				obj.setChargeMan(TCBWCommon.getUserID("COS02","02","Cos0001Db"));
			}else if("2".equals(obj.getCosType())){
				obj.setApplNo(TCBWCommon.getApplNo("COS03", "B01" , Datetime.getYYY()));
				obj.setChargeMan(TCBWCommon.getUserID("COS03","06","Cos0001Db"));
			}
			obj.setStatus("20");
			obj.setAcceptDate(yyymmdd);
			update(obj);
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "受理完成", userId);
			
			Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + obj.getCos4001DbId());
			if(cos4001Db != null){
				cos4001Db.setStatus(obj.getStatus());
				cos4001Db.setApplNo(obj.getApplNo());
				cos4001Db.setModifier(userId);
				cos4001Db.setModifyDate(yyymmdd);
				cos4001Db.setModifyTime(hhmmss);
				update(cos4001Db);
			}
		}
	}
	
	@Override
	public void updateByCOSIN0502F(COSIN0502F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String msg = "";

		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(cos0001Db != null){	
			boolean isNextUpd = true;
			if(null != ref.getIsSend06Mail() && !"".equals(ref.getIsSend06Mail())){
				for(String recently : ref.getRecentlyMeasure().split(",")){
					if(!"3".equals(recently)){
						if(ref.getIsSend06Mail().indexOf(recently) < 0){
							isNextUpd = false;
						}
					}
				}
			}
			if(isNextUpd){
				if("1P".equals(ref.getQ_type())){
					msg = "衛生單位/廠商通知完成";
				}else{
					msg = "廠商通知完成";
				}
				String logType = "";
				if("1P".equals(ref.getQ_type())){
					logType = "COS02";
				}else{
					logType = "COS03";
				}
				cos0001Db.setCon1003DbId(Common.getLong(ref.getDept1()));
				cos0001Db.setChargeMan(ref.getUserID());
				cos0001Db.setStatus("50");
				cos0001Db.setIsSend06Mail(ref.getIsSend06Mail());
				cos0001Db.setModifier(ref.getUserID());
				cos0001Db.setModifyDate(yyymmdd);
				cos0001Db.setModifyTime(hhmmss);
				update(cos0001Db);
				
				Cos0001Db anotherCos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where applNo = " + Common.sqlChar(cos0001Db.getForeignApplNo()));
				
				boolean isUpdate = true;							// 新增廠商回覆資料，需判斷是否有最新一筆廠商未回覆資料			
				String nDate = "";									// 通知日期
				if("1".equals(Common.get(cos0001Db.getCosType()))){
					if(ref.getRecentlyMeasure().indexOf("2") != -1){
						if(anotherCos0001Db != null){
							Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where (isClose is null or isClose <> 'Y') " +
																			" and applNo = " + Common.sqlChar(anotherCos0001Db.getApplNo()) + 
																			" order by id desc ");
							if(cos0010Db != null){
								if(!"".equals(Common.get(cos0010Db.getNotifyDate()))){
									if(Common.getInt(yyymmdd) - Common.getInt(cos0010Db.getNotifyDate()) <= 3){
										nDate = Common.get(cos0010Db.getNotifyDate());
									}
								}
							}
						}
						if("".equals(nDate)){
							nDate = Datetime.getYYYMMDD();
						}
						
						Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where replyDate is null " +
																		" and applNo = " + Common.sqlChar(cos0001Db.getApplNo()) + " order by id desc ");
						if(cos0009Db == null){
							cos0009Db = new Cos0009Db();
							isUpdate = false;
						}
						cos0009Db.setApplNo(Common.get(cos0001Db.getApplNo()));
						cos0009Db.setNotifyDate(nDate);
						cos0009Db.setCreator(ref.getUserID());
						cos0009Db.setCreateDate(yyymmdd);
						cos0009Db.setCreateTime(hhmmss);
						cos0009Db.setModifier(ref.getUserID());
						cos0009Db.setModifyDate(yyymmdd);
						cos0009Db.setModifyTime(hhmmss);
						if(isUpdate){
							update(cos0009Db);
						}else{
							save(cos0009Db);
						}
					}
				}else{
					if(anotherCos0001Db != null){
						Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where (isClose is null or isClose <> 'Y') " +
																		" and applNo = " + Common.sqlChar(anotherCos0001Db.getApplNo()) + 
																		" order by id desc ");
						if(cos0009Db != null){
							if(!"".equals(Common.get(cos0009Db.getNotifyDate()))){
								if(Common.getInt(yyymmdd) - Common.getInt(cos0009Db.getNotifyDate()) <= 3){
									nDate = Common.get(cos0009Db.getNotifyDate());
								}
							}
						}
					}
					if("".equals(nDate)){
						nDate = Datetime.getYYYMMDD();
					}
					
					Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where replyDate is null " +
																	" and applNo = " + Common.sqlChar(cos0001Db.getApplNo()) +  
																	" order by id desc ");
					if(cos0010Db == null){
						cos0010Db = new Cos0010Db();
						isUpdate = false;
					}
					cos0010Db.setNotifyDate(nDate);
					cos0010Db.setApplNo(Common.get(cos0001Db.getApplNo()));
					cos0010Db.setCreator(ref.getUserID());
					cos0010Db.setCreateDate(yyymmdd);
					cos0010Db.setCreateTime(hhmmss);
					cos0010Db.setModifier(ref.getUserID());
					cos0010Db.setModifyDate(yyymmdd);
					cos0010Db.setModifyTime(hhmmss);
					if(isUpdate){
						update(cos0010Db);
					}else{
						save(cos0010Db);
					}
				}
				
				// 同步外部狀態
				updateCos4001DbStatus(cos0001Db);
				
				// 寫入流程
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, cos0001Db.getId(), Common.get(cos0001Db.getApplNo()), cos0001Db.getStatus(), msg, ref.getUserID());
			}else{
				cos0001Db.setIsSend06Mail(ref.getIsSend06Mail());
				cos0001Db.setModifier(ref.getUserID());
				cos0001Db.setModifyDate(yyymmdd);
				cos0001Db.setModifyTime(hhmmss);
				update(cos0001Db);
			}
		}
		if(!"".equals(Common.get(msg))){
			ref.setIsNeedBackQuery("Y");
			ref.setErrorMsg(msg);
		}
	}
	
	@Override
	public void updateByCOSIN0602F(COSIN0602F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String msg = "";
		String logType = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "化粧品追蹤處理-暫存作業";
			msg = "暫存完成";
		}else if("caseBackUpdate".equals(ref.getState())){
			logName = "化粧品追蹤處理-回覆作業";
			msg = "回覆完成";
		}else if("caseTrackUpdate".equals(ref.getState())){
			logName = "化粧品追蹤處理-追蹤作業";
			msg = "追蹤完成";
		}
		
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			if("1".equals(Common.get(obj.getCosType()))){
				logType = "COS02";
			}else{
				logType = "COS03";
			}
			
			// 因廠商回覆與回覆完成，再補件時會新增一筆COS0009_DB or COS0010_DB，所以如果查不到，不可以自己新增
			if("1".equals(Common.get(obj.getCosType()))){
				Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(ref.getCos0009DbId()));
				if(cos0009Db != null){
					cos0009Db.setHandling(ref.getHandling());
					cos0009Db.setPrecaution(ref.getPrecaution9());
					cos0009Db.setSimilarComplaint(ref.getSimilarComplaint());
					cos0009Db.setRemark(ref.getRemark9());
					cos0009Db.setPredictDate(ref.getPredictDate());
					cos0009Db.setReplyDate(ref.getReplyDate9());
					cos0009Db.setModifier(ref.getUserID());
					cos0009Db.setModifyDate(yyymmdd);
					cos0009Db.setModifyTime(hhmmss);
					if("caseBackUpdate".equals(ref.getState())){
						cos0009Db.setIsClose("Y");
					}else{
						cos0009Db.setIsClose("N");
					}
					try{
						ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0009Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0009DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良品處理廠商回覆上傳檔案異常，COS0009_DB.ID : " + cos0009Db.getId() + " ]");
						e.printStackTrace();
					}
					update(cos0009Db);
				}
				
				// 不良品處理頁籤
				Cos0011Db cos0011Db = (Cos0011Db)View.getObject(" from Cos0011Db where applNo = " + Common.sqlChar(obj.getApplNo()));
				if(cos0011Db == null){
					cos0011Db = new Cos0011Db();
					cos0011Db.setApplNo(Common.get(obj.getApplNo()));
					cos0011Db.setCreator(ref.getUserID());
					cos0011Db.setCreateDate(yyymmdd);
					cos0011Db.setCreateTime(hhmmss);
				}
				cos0011Db.setIsLetterYn(ref.getIsLetterYn11());
				cos0011Db.setReplyMemo(ref.getReplyMemo11());
				cos0011Db.setRemark(ref.getRemark11());
				cos0011Db.setModifier(ref.getUserID());
				cos0011Db.setModifyDate(yyymmdd);
				cos0011Db.setModifyTime(hhmmss);
				saveOrUpdate(cos0011Db);
				
				// 發文資料
				try{
					updateCos0012Db(cos0011Db, ref.getHttpRequest(), ref.getCOS0012DbRow(), ref.arrCOS0012DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0012_DB發生異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
					e.printStackTrace();
				}
				
				// 發文相關檔案
				try{
					ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0011Db.getId()), "COSPT", "COS", ref.getCOSPTFileRow(), "COS0011DB", ref.arrCOSPTFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良品發文資料相關附件上傳異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
					e.printStackTrace();
				}
				
				// 收文資料
				try{
					updateCos0013Db(cos0011Db, ref.getHttpRequest(), ref.getCOS0013DbRow(), ref.arrCOS0013DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0013_DB發生異常，COS0011_DB.ID : " + cos0011Db.getId() + "]");
					e.printStackTrace();
				}
				
				// 收文相關檔案
				try{
					ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0011Db.getId()), "COSRT", "COS", ref.getCOSRTFileRow(), "COS0011DB", ref.arrCOSRTFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良品收文資料相關附件上傳異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
					e.printStackTrace();
				}
			}else{
				Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where id = " + Common.getLong(ref.getCos0010DbId()));
				if(cos0010Db != null){
					cos0010Db.setIsComplaintYn(ref.getIsComplaintYn10());
					cos0010Db.setComplaintNum(ref.getComplaintNum());
					cos0010Db.setDealWith(ref.getDealWith10());
					cos0010Db.setReplyOther(ref.getReplyOther10());
					cos0010Db.setReplyDate(ref.getReplyDate10());
					cos0010Db.setModifier(ref.getUserID());
					cos0010Db.setModifyDate(yyymmdd);
					cos0010Db.setModifyTime(hhmmss);
					if("caseBackUpdate".equals(ref.getState())){
						cos0010Db.setIsClose("Y");
					}else{
						cos0010Db.setIsClose("N");
					}
					try{
						ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0010Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0010DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理廠商回覆上傳檔案異常，COS0010_DB.ID : " + cos0010Db.getId() + " ]");
						e.printStackTrace();
					}
					update(cos0010Db);
				}
				
				Cos0014Db cos0014Db = (Cos0014Db)View.getObject(" from Cos0014Db where applNo = " + Common.sqlChar(obj.getApplNo()));
				if(cos0014Db == null){
					cos0014Db = new Cos0014Db();
					cos0014Db.setApplNo(Common.get(obj.getApplNo()));
					cos0014Db.setCreator(ref.getUserID());
					cos0014Db.setCreateDate(yyymmdd);
					cos0014Db.setCreateTime(hhmmss);
				}
				cos0014Db.setIsLetterYn(ref.getIsLetterYn14());
				cos0014Db.setReplyMemo(ref.getReplyMemo14());
				cos0014Db.setDealWith(ref.getDealWith14());
				cos0014Db.setDealWithOther(ref.getDealWithOther14());
				cos0014Db.setAssess(ref.getAssess());
				cos0014Db.setSummary(ref.getSummary());
				cos0014Db.setResolution(ref.getResolution());
				cos0014Db.setResult(ref.getResult());
				cos0014Db.setPrecaution(ref.getPrecaution14());
				cos0014Db.setRecycling(ref.getRecycling());
				cos0014Db.setOtherDisposal(ref.getOtherDisposal());
				cos0014Db.setRemark(ref.getRemark14());
				saveOrUpdate(cos0014Db);
				
				try{
					ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0014Db.getId()), "COSHA", "COS", ref.getCOSHAFileRow(), "COS0014DB", ref.arrCOSHAFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理頁籤上傳檔案異常(HA)，COS0014_DB.ID : " + cos0014Db.getId() + " ]");
					e.printStackTrace();
				}
				try{
					ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0014Db.getId()), "COSEB", "COS", ref.getCOSEBFileRow(), "COS0014DB", ref.arrCOSEBFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理頁籤上傳檔案異常(EB)，COS0014_DB.ID : " + cos0014Db.getId() + " ]");
					e.printStackTrace();
				}
				try{
					ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0014Db.getId()), "COSMS", "COS", ref.getCOSMSFileRow(), "COS0014DB", ref.arrCOSMSFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理頁籤上傳檔案異常(MS)，COS0014_DB.ID : " + cos0014Db.getId() + " ]");
					e.printStackTrace();
				}
			}
			
			if("pauseSave".equals(ref.getState())){
				obj.setChargeMan(ref.getUserID());
				obj.setStatus("50");
			}else if("caseBackUpdate".equals(ref.getState())){
				obj.setChargeMan(ref.getUserID());
				obj.setStatus("50");
			}else if("caseTrackUpdate".equals(ref.getState())){
				if("1".equals(Common.get(obj.getCosType()))){
					obj.setChargeMan(TCBWCommon.getUserID("COS02","05","Cos0001Db"));
				}else{
					obj.setChargeMan(TCBWCommon.getUserID("COS03","08","Cos0001Db"));
				}
				obj.setStatus("70");
				
				// 寫入流程
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
			}
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			// 同步外部狀態
			updateCos4001DbStatus(obj);
			
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
		
	}
	
	public void updateCos0012Db(Cos0011Db cos0011Db, javax.servlet.ServletRequest httpRequest, String[] COS0012DbRow, String[] arrCOS0012DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos0012Db> oCos12Map = new java.util.HashMap<Long, Cos0012Db>();  
		java.util.List<Cos0012Db> cos12List = load(" from Cos0012Db where cos0011Db.id = " + Common.getLong(cos0011Db.getId()));
		if(cos12List!=null && cos12List.size()>0){
			for(Cos0012Db dtl : cos12List){
				oCos12Map.put(dtl.getId(), dtl);
			}
			cos12List.clear();
		}
		java.util.List<Cos0012Db> dCos12List = new java.util.ArrayList<Cos0012Db>();
		if(httpRequest!=null && COS0012DbRow!=null && COS0012DbRow.length>0){
			if(oCos12Map.size() > 0){
				for(Map.Entry<Long, Cos0012Db> dtl : oCos12Map.entrySet()){
					boolean flag = true;
					for(String rid : COS0012DbRow){
						if(rid.equals(Common.get(dtl.getKey()))){
							flag = false;
						}
						if(!flag)	break;
					}
					if(flag){
						dCos12List.add(dtl.getValue());
					}
				}
			}
			
			for(String rid : COS0012DbRow){
				boolean isUpdate = true;
				Cos0012Db cos0012Db = oCos12Map.get(Common.getLong(rid));
				if(cos0012Db == null){
					cos0012Db = new Cos0012Db();
					cos0012Db.setCos0011Db(cos0011Db);
					cos0012Db.setCreator(userId);
					cos0012Db.setCreateDate(yyymmdd);
					cos0012Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos0012Db.setPostDate(httpRequest.getParameter(arrCOS0012DbFieldName[0] + rid));
				cos0012Db.setPostNo(httpRequest.getParameter(arrCOS0012DbFieldName[1] + rid));
				cos0012Db.setCon1003DbId(Common.getLong(httpRequest.getParameter(arrCOS0012DbFieldName[2] + rid)));
				cos0012Db.setPostMemo(httpRequest.getParameter(arrCOS0012DbFieldName[3] + rid));
				cos0012Db.setModifier(userId);
				cos0012Db.setModifyDate(yyymmdd);
				cos0012Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos0012Db);
				}else{
					save(cos0012Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos0012Db> dtl : oCos12Map.entrySet()){
				dCos12List.add(dtl.getValue());
			}
		}
		if(dCos12List.size() > 0){
			deleteBatch(dCos12List);
			dCos12List.clear();
		}
		oCos12Map.clear();
	}
	
	public void updateCos0013Db(Cos0011Db cos0011Db, javax.servlet.ServletRequest httpRequest, String[] COS0013DbRow, String[] arrCOS0013DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos0013Db> oCos13Map = new java.util.HashMap<Long, Cos0013Db>();  
		java.util.List<Cos0013Db> cos13List = load(" from Cos0013Db where cos0011Db.id = " + Common.getLong(cos0011Db.getId()));
		if(cos13List!=null && cos13List.size()>0){
			for(Cos0013Db dtl : cos13List){
				oCos13Map.put(dtl.getId(), dtl);
			}
			cos13List.clear();
		}
		java.util.List<Cos0013Db> dCos13List = new java.util.ArrayList<Cos0013Db>();
		if(httpRequest!=null && COS0013DbRow!=null && COS0013DbRow.length>0){
			if(oCos13Map.size() > 0){
				for(Map.Entry<Long, Cos0013Db> dtl : oCos13Map.entrySet()){
					boolean flag = true;
					for(String rid : COS0013DbRow){
						if(rid.equals(Common.get(dtl.getKey()))){
							flag = false;
						}
						if(!flag)	break;
					}
					if(flag){
						dCos13List.add(dtl.getValue());
					}
				}
			}
			for(String rid : COS0013DbRow){
				boolean isUpdate = true;
				Cos0013Db cos0013Db = oCos13Map.get(Common.getLong(rid));
				if(cos0013Db == null){
					cos0013Db = new Cos0013Db();
					cos0013Db.setCos0011Db(cos0011Db);
					cos0013Db.setCreator(userId);
					cos0013Db.setCreateDate(yyymmdd);
					cos0013Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos0013Db.setReceiptDate(httpRequest.getParameter(arrCOS0013DbFieldName[0] + rid));
				cos0013Db.setPostDate(httpRequest.getParameter(arrCOS0013DbFieldName[1] + rid));
				cos0013Db.setPostDept(httpRequest.getParameter(arrCOS0013DbFieldName[2] + rid));
				cos0013Db.setPostNo(httpRequest.getParameter(arrCOS0013DbFieldName[3] + rid));
				cos0013Db.setPostMemo(httpRequest.getParameter(arrCOS0013DbFieldName[4] + rid));
				cos0013Db.setModifier(userId);
				cos0013Db.setModifyDate(yyymmdd);
				cos0013Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos0013Db);
				}else{
					save(cos0013Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos0013Db> dtl : oCos13Map.entrySet()){
				dCos13List.add(dtl.getValue());
			}
		}
		if(dCos13List.size() > 0){
			deleteBatch(dCos13List);
			dCos13List.clear();
		}
		oCos13Map.clear();
	}

	
	@Override
	public void updateByCOSIN0602FAdditionCase(COSIN0602F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String msg = "廠商補件通知完成";
		
		String logType = "";
		if("1S".equals(ref.getQ_type())){
			logType = "COS02";
		}else{
			logType = "COS03";
		}
		
		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(cos0001Db != null){	
			cos0001Db.setChargeMan(ref.getUserID());
			cos0001Db.setStatus("50");
			cos0001Db.setModifier(ref.getUserID());
			cos0001Db.setModifyDate(yyymmdd);
			cos0001Db.setModifyTime(hhmmss);
			update(cos0001Db);
			
			// 新增廠商回覆資料
			if("1".equals(Common.get(cos0001Db.getCosType()))){
				Cos0009Db cos0009Db = new Cos0009Db();
				cos0009Db.setApplNo(Common.get(cos0001Db.getApplNo()));
				cos0009Db.setNotifyDate(yyymmdd);
				cos0009Db.setCreator(ref.getUserID());
				cos0009Db.setCreateDate(yyymmdd);
				cos0009Db.setCreateTime(hhmmss);
				cos0009Db.setModifier(ref.getUserID());
				cos0009Db.setModifyDate(yyymmdd);
				cos0009Db.setModifyTime(hhmmss);
				save(cos0009Db);
			}else{
				Cos0010Db cos0010Db = new Cos0010Db();
				cos0010Db.setNotifyDate(yyymmdd);
				cos0010Db.setApplNo(Common.get(cos0001Db.getApplNo()));
				cos0010Db.setCreator(ref.getUserID());
				cos0010Db.setCreateDate(yyymmdd);
				cos0010Db.setCreateTime(hhmmss);
				cos0010Db.setModifier(ref.getUserID());
				cos0010Db.setModifyDate(yyymmdd);
				cos0010Db.setModifyTime(hhmmss);
				save(cos0010Db);
			}
			
			// 同步外部狀態
			updateCos4001DbStatus(cos0001Db);
			
			// 寫入流程
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, cos0001Db.getId(), Common.get(cos0001Db.getApplNo()), cos0001Db.getStatus(), msg, ref.getUserID());
		}
		ref.setIsNeedBackQuery("Y");
		ref.setErrorMsg(msg);
	}
	
	@Override
	public void updateByCOSIN0602FReDisCase(COSIN0602F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String logType = "";
		if("1S".equals(ref.getQ_type())){
			logType = "COS02";
		}else{
			logType = "COS03";
		}
		
		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(cos0001Db != null){
			cos0001Db.setChargeMan("");
			cos0001Db.setStatus("20");
			cos0001Db.setModifier(ref.getUserID());
			cos0001Db.setModifyDate(yyymmdd);
			cos0001Db.setModifyTime(hhmmss);
			
			// 返回分類中時，先清空之前記錄的發送MAIL記錄
			if("1".equals(Common.get(cos0001Db.getCosType()))){
				cos0001Db.setIsSend06Mail("");
			}
			update(cos0001Db);
			
			// 同步外部狀態
			updateCos4001DbStatus(cos0001Db);
			
			// 案件回到分類作業，建立新的COS0006_DB或COS0008_DB
			if("1".equals(Common.get(cos0001Db.getCosType()))){
				Cos0006Db newCos0006Db = new Cos0006Db();
				Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where applNo = " + Common.sqlChar(cos0001Db.getApplNo()) + " order by id desc ");
				if(cos0006Db != null){
					org.springframework.beans.BeanUtils.copyProperties(cos0006Db, newCos0006Db, new String[]{"id", "cos0016Dbs"});
				}else{
					logger.info("[TCBW]-[CosinDaoImpl]-[案號 : " + cos0001Db.getApplNo() + "，追蹤中案件，重新分類作業，查無最新一筆COS0006_DB資料]");
				}
				newCos0006Db.setCreator(ref.getUserID());
				newCos0006Db.setCreateDate(yyymmdd);
				newCos0006Db.setCreateTime(hhmmss);
				newCos0006Db.setModifier(ref.getUserID());
				newCos0006Db.setModifyDate(yyymmdd);
				newCos0006Db.setModifyTime(hhmmss);
				save(newCos0006Db);
			}else{
				Cos0008Db newCos0008Db = new Cos0008Db();
				Cos0008Db cos0008Db = (Cos0008Db)View.getObject(" from Cos0008Db where applNo = " + Common.sqlChar(cos0001Db.getApplNo()) + " order by id desc ");
				if(cos0008Db != null){
					org.springframework.beans.BeanUtils.copyProperties(cos0008Db, newCos0008Db, new String[]{"id"});
				}else{
					logger.info("[TCBW]-[CosinDaoImpl]-[案號 : " + cos0001Db.getApplNo() + "，追蹤中案件，重新分類作業，查無最新一筆COS0008_DB資料]");
				}
				newCos0008Db.setCreator(ref.getUserID());
				newCos0008Db.setCreateDate(yyymmdd);
				newCos0008Db.setCreateTime(hhmmss);
				newCos0008Db.setModifier(ref.getUserID());
				newCos0008Db.setModifyDate(yyymmdd);
				newCos0008Db.setModifyTime(hhmmss);
				save(newCos0008Db);
			}
			
			// 寫入流程
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, cos0001Db.getId(), Common.get(cos0001Db.getApplNo()), cos0001Db.getStatus(), ref.getReDisReason().equals("")?"重新分類":ref.getReDisReason(), ref.getUserID());
			
			ref.setIsNeedBackQuery("Y");
			ref.setErrorMsg("重新分類完成");
		}else{
			ref.setIsNeedBackQuery("N");
			ref.setErrorMsg("查無案件資料，請確認");
		}
	}

	@Override
	public void updateByCOSIN0702F(COSIN0702F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String logType = "";
		String logName = "";
		String msg = "";
		if("1B".equals(ref.getQ_type())){
			logType = "COS02";
		}else{
			logType = "COS03";
		}
		if("pauseSave".equals(ref.getState())){
			logName = "化粧品-廠商回覆作業-暫存";
			msg = "暫存完成";
		}else{
			logName = "化粧品-廠商回覆作業-回覆完成";
			msg = "廠商回覆完成";
		}
		
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			if("Y".equals(ref.getIsCombineBussinessPage())){
				String[] idArray = ref.getCos0910Id().split(";");
				if(idArray!=null && idArray.length==2){
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(idArray[0]));
					Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where id = " + Common.getLong(idArray[1]));
					if(cos0009Db != null){
						cos0009Db.setSimilarComplaint(ref.getSimilarComplaint0910());
						cos0009Db.setPrecaution(ref.getPrecaution0910());
						cos0009Db.setHandling(ref.getHandling0910());
						cos0009Db.setRemark(ref.getRemark0910());
						cos0009Db.setPredictDate(ref.getPredictDate0910());
						cos0009Db.setModifier(ref.getUserID());
						cos0009Db.setModifyDate(yyymmdd);
						cos0009Db.setModifyTime(hhmmss);
						if("caseBackUpdate".equals(ref.getState())){
							cos0009Db.setReplyDate(yyymmdd);
							cos0009Db.setIsClose("Y");
							
							if(cos0010Db != null){
								cos0009Db.setCos0010DbId(cos0010Db.getId());
							}
						}else{
							cos0009Db.setIsClose("N");
						}
						try{
							ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0009Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0009DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
						}catch(Exception e){
							logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良品處理廠商回覆上傳檔案異常，COS0009_DB.ID : " + cos0009Db.getId() + " ]");
							e.printStackTrace();
						}
						update(cos0009Db);
					}
					if(cos0010Db != null){
						cos0010Db.setIsComplaintYn(ref.getIsComplaintYn0910());
						cos0010Db.setComplaintNum(ref.getComplaintNum0910());
						cos0010Db.setDealWith(ref.getSimilarComplaint0910());
						cos0010Db.setReplyOther(ref.getRemark0910());
						cos0010Db.setModifier(ref.getUserID());
						cos0010Db.setModifyDate(yyymmdd);
						cos0010Db.setModifyTime(hhmmss);
						if("caseBackUpdate".equals(ref.getState())){
							cos0010Db.setReplyDate(yyymmdd);
							cos0010Db.setIsClose("Y");
							
							if(cos0009Db != null){
								cos0010Db.setCos0009DbId(cos0009Db.getId());
							}
						}else{
							cos0010Db.setIsClose("N");
						}
						try{
							ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0010Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0010DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
						}catch(Exception e){
							logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理廠商回覆上傳檔案異常，COS0010_DB.ID : " + cos0010Db.getId() + " ]");
							e.printStackTrace();
						}
						update(cos0010Db);
					}
				}else{
					throw new Exception("回覆資料異常，請重新操作");
				}
			}else{
				if("1".equals(Common.get(obj.getCosType()))){
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(ref.getCos0009DbId()));
					if(cos0009Db != null){
						cos0009Db.setHandling(ref.getHandling());
						cos0009Db.setPrecaution(ref.getPrecaution9());
						cos0009Db.setSimilarComplaint(ref.getSimilarComplaint());
						cos0009Db.setRemark(ref.getRemark9());
						cos0009Db.setPredictDate(ref.getPredictDate());
						cos0009Db.setModifier(ref.getUserID());
						cos0009Db.setModifyDate(yyymmdd);
						cos0009Db.setModifyTime(hhmmss);
						if("caseBackUpdate".equals(ref.getState())){
							cos0009Db.setReplyDate(yyymmdd);
							cos0009Db.setIsClose("Y");
						}else{
							cos0009Db.setIsClose("N");
						}
						try{
							ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0009Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0009DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
						}catch(Exception e){
							logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良品處理廠商回覆上傳檔案異常，COS0009_DB.ID : " + cos0009Db.getId() + " ]");
							e.printStackTrace();
						}
						update(cos0009Db);
					}
				}else{
					Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where id = " + Common.getLong(ref.getCos0010DbId()));
					if(cos0010Db != null){
						cos0010Db.setIsComplaintYn(ref.getIsComplaintYn10());
						cos0010Db.setComplaintNum(ref.getComplaintNum());
						cos0010Db.setDealWith(ref.getDealWith10());
						cos0010Db.setReplyOther(ref.getReplyOther10());
						cos0010Db.setModifier(ref.getUserID());
						cos0010Db.setModifyDate(yyymmdd);
						cos0010Db.setModifyTime(hhmmss);
						if("caseBackUpdate".equals(ref.getState())){
							cos0010Db.setReplyDate(yyymmdd);
							cos0010Db.setIsClose("Y");
						}else{
							cos0010Db.setIsClose("N");
						}
						try{
							ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0010Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0010DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
						}catch(Exception e){
							logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[不良反應處理廠商回覆上傳檔案異常，COS0010_DB.ID : " + cos0010Db.getId() + " ]");
							e.printStackTrace();
						}
						update(cos0010Db);
					}
				}
			}
			
			// 寫入流程
			if("caseBackUpdate".equals(ref.getState())){
				CommonUser user = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(obj.getChargeMan()));
				if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail()))){
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					mailList.add(new javax.mail.internet.InternetAddress(Common.get(user.getUserEmail())));
					String mailTitle = "廠商回覆通知(" + obj.getApplNo() + ")";
					
					StringBuffer mailBody = new StringBuffer();
					mailBody.append(user.getUserName()).append("你好 : ").append("<br>");
					mailBody.append(obj.getApplNo()).append("，廠商已回覆");
					try{
						ServiceGetter.getInstance().getTcbwService().sendEmail(mailTitle, mailBody.toString(), mailList, null, true, null, null, null);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[廠商回覆完成，發信通知作業人員發生異常，案號 : " + obj.getApplNo() + "]");
						e.printStackTrace();
					}
				}
				
				// 寫入流程
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "廠商回覆作業，回覆完成", ref.getUserID());
			}
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
			logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0001_DB，不更新資料]");
		}
		
	}

	@Override
	public void updateByCOSIN0802F(COSIN0802F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "化粧品-不良品評估作業-暫存";
			msg = "暫存完成";
		}else if("assessComplete".equals(ref.getState())){
			logName = "化粧品-不良品評估作業-評估完成";
			msg = "評估完成";
		}
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where applNo = " + Common.sqlChar(obj.getApplNo()) + " order by id desc ");
			if(cos0006Db != null){
				Cos0007Db cos0007Db = (Cos0007Db)View.getObject(" from Cos0007Db where cos0006Db.id = " + Common.getLong(cos0006Db.getId()) + 
																" and applNo = " + Common.sqlChar(cos0006Db.getApplNo()));
				if(cos0007Db == null){
					cos0007Db = new Cos0007Db();
					cos0007Db.setCos0006Db(cos0006Db);
					cos0007Db.setApplNo(cos0006Db.getApplNo());
					cos0007Db.setCreator(ref.getUserID());
					cos0007Db.setCreateDate(yyymmdd);
					cos0007Db.setCreateTime(hhmmss);
				}
				cos0007Db.setAssessMan(ref.getUserID());
				cos0007Db.setAssessResult(ref.getAssessResult());
				cos0007Db.setAssessRemark(ref.getAssessRemark());
				cos0007Db.setModifier(ref.getUserID());
				cos0007Db.setModifyDate(yyymmdd);
				cos0007Db.setModifyTime(hhmmss);
				if("assessComplete".equals(ref.getState())){
					cos0007Db.setAssessDate(yyymmdd);
				}else{
					cos0007Db.setAssessDate("");
				}
				saveOrUpdate(cos0007Db);
				
				// COS0001_DB
				boolean isNeedSendMail = false;
				CommonUser user = null;
				if("assessComplete".equals(ref.getState())){
					if("1".equals(ref.getAssessResult())){
						// 取得前一個分類作業人員
						String userId = View.getLookupField(" select userID from Con2001Db where systemType = 'COS02' " +
								   						    " and formID = " + Common.getLong(obj.getId()) + 
								   						    " and applNo = " + Common.sqlChar(obj.getApplNo()) + 
														   	" and state = '60' order by id desc ");
						user = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(userId));
						obj.setChargeMan(userId);
						obj.setStatus("20");
						isNeedSendMail = true;
						
						// 案件回到分類作業，建立新的COS0006_DB
						Cos0006Db newCos0006Db = new Cos0006Db();
						org.springframework.beans.BeanUtils.copyProperties(cos0006Db, newCos0006Db, new String[]{"id", "cos0016Dbs"});
						newCos0006Db.setCreator(ref.getUserID());
						newCos0006Db.setCreateDate(yyymmdd);
						newCos0006Db.setCreateTime(hhmmss);
						newCos0006Db.setModifier(ref.getUserID());
						newCos0006Db.setModifyDate(yyymmdd);
						newCos0006Db.setModifyTime(hhmmss);
						save(newCos0006Db);
						
						// 返回分類中時，先清空之前記錄的發送MAIL記錄
						obj.setIsSend06Mail("");
					}else{
						obj.setStatus("70");
						obj.setChargeMan(TCBWCommon.getUserID("COS03","08","Cos0001Db"));
					}
				}else{
					obj.setStatus("60");
					obj.setChargeMan(ref.getUserID());
				}

				obj.setModifier(ref.getUserID());
				obj.setModifyDate(yyymmdd);
				obj.setModifyTime(hhmmss);
				update(obj);
				
				if("assessComplete".equals(ref.getState())){
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS02", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "評估完成", ref.getUserID());
					
					// 同步外部狀態
					updateCos4001DbStatus(obj);
				}
				
				if(isNeedSendMail){
					if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail()))){
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
						mailList.add(new javax.mail.internet.InternetAddress(Common.get(user.getUserEmail())));
						try{
							String title = "案件評估完成通知" + Common.sqlChar(obj.getApplNo());
							StringBuffer sb = new StringBuffer();
							sb.append(user.getUserName()).append("你好").append("<br>");
							sb.append(obj.getApplNo()).append("已評估完成 ! ");
							ServiceGetter.getInstance().getTcbwService().sendEmail(title, sb.toString(), mailList, null, true, null, null, null, "COS", Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):Common.get(obj.getApplNo()));
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[發送通知前作業人員EMAIL異常，不發送信件，COS0001_DB.APPL_NO : " + obj.getApplNo() + " ]");
					}
				}
				
				ref.setErrorMsg(msg);
				ref.setIsNeedBackQuery("Y");
			}else{
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0006_DB，不更新評估資料]");
			}
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
	}

	@Override
	public void updateByCOSIN0902F(COSIN0902F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		String logType = "";
		if("1".equals(ref.getCosType())){
			logType = "COS02";
		}else{
			logType = "COS03";
		}
		if("pauseSave".equals(ref.getState())){
			msg = "暫存完成";
			logName = "化粧品-結案作業-暫存";
		}else{
			msg = "結案完成";
			logName = "化粧品-結案作業-結案完成";
		}
		
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			Cos0015Db cos0015Db = (Cos0015Db)View.getObject(" from Cos0015Db where applNo = " + Common.sqlChar(obj.getApplNo()) +
															" and cosType = " + Common.sqlChar(obj.getCosType()));
			if(cos0015Db == null){
				cos0015Db = new Cos0015Db();
				cos0015Db.setApplNo(obj.getApplNo());
				cos0015Db.setCosType(obj.getCosType());
				cos0015Db.setCreator(ref.getUserID());
				cos0015Db.setCreateDate(yyymmdd);
				cos0015Db.setCreateTime(hhmmss);
			}
			cos0015Db.setCosCorrelation(ref.getCosCorrelation());
			cos0015Db.setFeedBack(ref.getFeedBack());
			cos0015Db.setRecordOpinion(ref.getRecordOpinion());
			cos0015Db.setModifier(ref.getUserID());
			cos0015Db.setModifyDate(yyymmdd);
			cos0015Db.setModifyTime(hhmmss);
			saveOrUpdate(cos0015Db);
			
			if("endCase".equals(ref.getState())){
				obj.setChargeMan("");
				obj.setStatus("90");
			}else{
				obj.setChargeMan(ref.getUserID());
				obj.setStatus("70");
			}
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			if("90".equals(obj.getStatus())) {
				//結案後產生檔案
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getStatus());
			}
			
			if("endCase".equals(ref.getState())){
				// 寫入流程
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "結案完成", ref.getUserID());
				
				// 同步外部狀態
				updateCos4001DbStatus(obj);
			}
			
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
			logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0001_DB，不更新資料]");
		}
		
	}

	@Override
	public void updateByCOSIN1002F(COSIN1002F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String msg = "";
		String logName = "";
		String logType = "";
		
		if("pauseSave".equals(ref.getState())){
			msg = "暫存完成";
			logName = "化妝品通報-分類作業-暫存";
		}else if("disComplete".equals(ref.getState())){
			msg = "分類完成";
			logName = "化妝品通報-分類作業-分類完成";
		}
		
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Cos0001Db();
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[查無COS0001_DB，新建一筆]");
		}
		
		if("1".equals(ref.getCosType())){
			logType = "COS02";
		}else{
			logType = "COS03";
		}
		
		// 案件狀態處理
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("20");
			obj.setChargeMan(ref.getUserID());
		}

		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierSource(ref.getNotifierSource());
		obj.setNotifierSourceOther(ref.getNotifierSourceOther());
//		obj.setNotifierName(ref.getNotifierName());
//		obj.setNotifierTel(ref.getNotifierTel());
//		obj.setNotifierEamil(ref.getNotifierEamil());
//		obj.setNotifierDept(ref.getNotifierDept());
//		obj.setNotifierType(ref.getNotifierType());
//		obj.setNotifierTitle(ref.getNotifierTitle());
//		obj.setAddress(ref.getAddress());
		obj.setIsContactYn(ref.getIsContactYn());
		obj.setIsDamageYn(ref.getIsDamageYn());
		obj.setOtherInformation(ref.getOtherInformation());
		obj.setOtherExplain(ref.getOtherExplain());
		
		obj.setChProduct(ref.getChProduct());
		obj.setEnProduct(ref.getEnProduct());
		obj.setPermitKey(ref.getPermitKey());
		obj.setPermitNo(ref.getPermitNo());
		obj.setTraffickWay(ref.getTraffickWay());
		obj.setTraffickWayOther(ref.getTraffickWayOther());
		obj.setBusinessName(ref.getBusinessName());
		obj.setManufactorName(ref.getManufactorName());
		obj.setManufactorTelArea(ref.getManufactorTelArea());
		obj.setManufactorTel(ref.getManufactorTel());
		obj.setManufactorTelExt(ref.getManufactorTelExt());
		obj.setManufactorArea(ref.getManufactorArea());
		obj.setManufactorZipCode(ref.getManufactorZipCode());
		obj.setManufactorAddr(ref.getManufactorAddr());
		obj.setManufactorNo(ref.getManufactorNo());
		obj.setExpirationDate(ref.getExpirationDate());
		obj.setTradeDate(ref.getTradeDate());
		obj.setTradePlace(ref.getTradePlace());
		obj.setTradePlaceZipCode(ref.getTradePlaceZipCode());
		obj.setTradePlaceAddr(ref.getTradePlaceAddr());
		obj.setIsSampleYn(ref.getIsSampleYn());
		obj.setEvenContactYn(ref.getEvenContactYn());
		obj.setDealWith(ref.getDealWith());
		obj.setIsSimilarYn(ref.getIsSimilarYn());
		obj.setIsRecurrenceYn(ref.getIsRecurrenceYn());
		obj.setIsOtherDeptYn(ref.getIsOtherDeptYn());
		obj.setOtherDpetName(ref.getOtherDpetName());
		obj.setCaseNo(ref.getCaseNo());
		obj.setIngredient(ref.getIngredient());
		obj.setModifier(ref.getUserID());
		obj.setModifyDate(yyymmdd);
		obj.setModifyTime(hhmmss);
		
		if("1".equals(obj.getCosType()) || "3".equals(obj.getCosType())){
			updateCos0003Db(obj, ref.getSubCode(), ref.getOtherDescribe(), ref.getUserID(), yyymmdd, hhmmss);
		}
		
		if("2".equals(obj.getCosType())){
			java.util.Set cos0002Dbs = obj.getCos0002Dbs();
			if(cos0002Dbs == null){
				cos0002Dbs = new ListOrderedSet();
				obj.setCos0002Dbs(cos0002Dbs);
			}
			Cos0002Db cos0002Db = null;
			if(cos0002Dbs.size() > 0){
				for(Object dtlObj : cos0002Dbs){
					cos0002Db = (Cos0002Db)dtlObj;
					if(cos0002Db != null)
						break;
				}
			}
			if(cos0002Db == null){
				cos0002Db = new Cos0002Db();
				cos0002Db.setCos0001Db(obj);
				cos0002Db.setCreator(ref.getUserID());
				cos0002Db.setCreateDate(yyymmdd);
				cos0002Db.setCreateTime(hhmmss);
				cos0002Dbs.add(cos0002Db);
			}
			cos0002Db.setAdverseCondition(ref.getAdverseCondition());
			cos0002Db.setNonSeriousOther(ref.getNonSeriousOther());
			cos0002Db.setNonSeriousAR(ref.getNonSeriousAR());
			cos0002Db.setNonSeriousAROther(ref.getNonSeriousAROther());
			cos0002Db.setNonSeriousDis(ref.getNonSeriousDis());
			cos0002Db.setUseDateS(ref.getUseDateS());
			cos0002Db.setUseDateE(ref.getUseDateE());
			cos0002Db.setUseRate(ref.getUseRate());
			cos0002Db.setUseMethod(ref.getUseMethod());
			cos0002Db.setIsMitigateYn(ref.getIsMitigateYn());
			cos0002Db.setIsRecurYn(ref.getIsRecurYn());
			cos0002Db.setDiagnosisProof(ref.getDiagnosisProof());
			cos0002Db.setDiagnosisReport(ref.getDiagnosisReport());
			cos0002Db.setDiagnosisOther(ref.getDiagnosisOther());
			cos0002Db.setModifier(ref.getUserID());
			cos0002Db.setModifyDate(yyymmdd);
			cos0002Db.setModifyTime(hhmmss);
			saveOrUpdate(cos0002Db);
			
			// 相關檢查及檢驗數據
			try{
				updateCos0004Db(cos0002Db, ref.getHttpRequest(), ref.getCOS0004DbRow(), ref.arrCOS0004DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0004_DB發生異常，COS0002_DB.ID : " + cos0002Db.getId() + "]");
				e.printStackTrace();
			}
			
			// 相關附件(醫師診斷證明)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0002Db.getId()), "COSDP", "COS", ref.getCOSDPFileRow(), "COS0002DB", ref.arrCOSDPFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(就醫紀錄)
			try{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0002Db.getId()), "COSSD", "COS", ref.getCOSSDFileRow(), "COS0002DB", ref.arrCOSSDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 併用的其他化妝品
			try{
				updateCos0005Db(cos0002Db, ref.getHttpRequest(), ref.getCOS0005DbRow(), ref.arrCOS0005DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosinDaoImpl]-[" + logName + "]-[更新COS0005_DB發生異常，COS0002_DB.ID : " + cos0002Db.getId() + "]");
				e.printStackTrace();
			}
		}
		
		if("1".equals(obj.getCosType())){
			Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where id = " + Common.getLong(ref.getCos0006DbId()));
			if(cos0006Db == null){
				cos0006Db = new Cos0006Db();
				cos0006Db.setApplNo(obj.getApplNo());
				cos0006Db.setCreator(ref.getUserID());
				cos0006Db.setCreateDate(yyymmdd);
				cos0006Db.setCreateTime(hhmmss);
			}
			if("disComplete".equals(ref.getState())){
				cos0006Db.setDisDate(yyymmdd);
			}else{
				cos0006Db.setDisDate("");
			}
			cos0006Db.setFirstResult(ref.getFirstResult());
			cos0006Db.setLeaveCaseReason(ref.getLeaveCaseReason());
			cos0006Db.setNonDefective(ref.getNonDefective());
			cos0006Db.setMarked(ref.getdMarked());
			cos0006Db.setLawlessIng(ref.getdLawlessIng());
			cos0006Db.setLawlessIngOther(ref.getdLawlessIngOther());
			cos0006Db.setExteriorError(ref.getdExteriorError());
			cos0006Db.setExteriorErrorOther(ref.getdExteriorErrorOther());
			cos0006Db.setPackageError(ref.getdPackageError());
			cos0006Db.setPackageErrorOther(ref.getdPackageErrorOther());
			cos0006Db.setExpired(ref.getdExpired());
			cos0006Db.setExpiredOther(ref.getdExpiredOther());
			cos0006Db.setOthers(ref.getdOthers());
			cos0006Db.setOthersDesc(ref.getdOthersDesc());
			cos0006Db.setModifier(ref.getUserID());
			cos0006Db.setModifyDate(yyymmdd);
			cos0006Db.setModifyTime(hhmmss);
			if(ref.getMeasure()!=null && ref.getMeasure().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : ref.getMeasure()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				cos0006Db.setMeasure(sb.toString());
			}else{
				cos0006Db.setMeasure("");
			}
			saveOrUpdate(cos0006Db);	
			
			// 不良品分類，初步判定結果選擇[資料不足留案備查-代碼02]，則狀態變更為結案
			if("disComplete".equals(ref.getState())){
				if("03".equals(Common.get(cos0006Db.getFirstResult()))){
					obj.setStatus("90");
					obj.setChargeMan("");															// 結案
				}else if("01".equals(Common.get(cos0006Db.getFirstResult()))){
					obj.setStatus("40");
					obj.setChargeMan(TCBWCommon.getUserID("COS02","03","Cos0001Db"));				// 處理
				}else{
					obj.setStatus("60");
					obj.setChargeMan(TCBWCommon.getUserID("COS02","04","Cos0001Db"));				// 評估
				}
			}
			
			// 分類歷史歷程
			if("disComplete".equals(ref.getState())){
				Cos0016Db cos0016Db = new Cos0016Db();
				cos0016Db.setCos0006Db(cos0006Db);
				cos0016Db.setCreator(ref.getUserID());
				cos0016Db.setCreateDate(yyymmdd);
				cos0016Db.setCreateTime(hhmmss);
				cos0016Db.setModifier(ref.getUserID());
				cos0016Db.setModifyDate(yyymmdd);
				cos0016Db.setModifyTime(hhmmss);
				save(cos0016Db);
			}
		}
		
		if("2".equals(obj.getCosType())){
			Cos0008Db cos0008Db = (Cos0008Db)View.getObject(" from Cos0008Db where id = " + Common.sqlChar(ref.getCos0008DbId()));
			if(cos0008Db == null){
				cos0008Db = new Cos0008Db();
				cos0008Db.setApplNo(obj.getApplNo());
				cos0008Db.setCreator(ref.getUserID());
				cos0008Db.setCreateDate(yyymmdd);
				cos0008Db.setCreateTime(hhmmss);
			}
			if("disComplete".equals(ref.getState())){
				cos0008Db.setDisDate(yyymmdd);
			}else{
				cos0008Db.setDisDate("");
			}
			cos0008Db.setPreResult(ref.getPreResult());
			cos0008Db.setLeftCaseReason(ref.getLeftCaseReason());
			cos0008Db.setIsCompleteYn(ref.getIsCompleteYn());
			cos0008Db.setIsContactYn(ref.getdIsContactYn());
			cos0008Db.setReactionLev(ref.getReactionLev());
			cos0008Db.setTimingLev(ref.getTimingLev());
			cos0008Db.setPreviousNotify(ref.getPreviousNotify());
			cos0008Db.setIsComplaintYn(ref.getIsComplaintYn());
			cos0008Db.setIsOtherDeptYn(ref.getdIsOtherDeptYn());
			cos0008Db.setModifier(ref.getUserID());
			cos0008Db.setModifyDate(yyymmdd);
			cos0008Db.setModifyTime(hhmmss);
			saveOrUpdate(cos0008Db);
			
			// 不良反應分類，初步判定結果選擇[資料不足留案備查-代碼02]，則狀態變更為結案
			if("disComplete".equals(ref.getState())){
				if("02".equals(ref.getPreResult())){
					obj.setStatus("90");
					obj.setChargeMan("");
				}else{
					obj.setStatus("40");
					obj.setChargeMan(TCBWCommon.getUserID("COS03","07","Cos0001Db"));
				}
			}
		}
		saveOrUpdate(obj);
		updateCos4001DbStatus(obj);
		ref.setId(Common.get(obj.getId()));
		
		// 案件本相關資料處理
		ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(obj.getId()), "C", "COS", ref.getCFileRow(), "COS0001DB", ref.arrCFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		
		// 分類完成
		if("disComplete".equals(ref.getState())){
		//	updateCos4001DbStatus(obj);
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "90".equals(obj.getStatus())?"分類完成-案件結案":msg, ref.getUserID());
			
			if("1".equals(obj.getCosType())){
				java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
				java.util.List<javax.mail.internet.InternetAddress> mailList = getEmailGroupData("COS", "TFDA01");
				if(mailList!=null && mailList.size()>0){
					String applNoStr = Common.get(cosMap.get(Common.get(obj.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(obj.getCosType())) + Common.get(obj.getApplNo());
					boolean isSendMail = false;
					String[] mail = null;
					String mailTitle = "";
					String mailBody = "";
					if("01".equals(ref.getFirstResult())){
						mail = TCBWCommon.getCon1001DbMail("COS", "COS02", "COS020002");
						if(mail!=null && mail.length==2){
							isSendMail = true;
							mailTitle = Common.get(mail[0]).replace("[T_案號]", applNoStr);
							mailBody = Common.get(mail[1]);
							mailBody = mailBody.replace("[F_案號]", applNoStr);
							mailBody = mailBody.replace("[F_品名]", obj.getChProduct());
							
							StringBuffer discriptCos0006Db = new StringBuffer();
							if(ref.getdMarked().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("標示問題");
							}
							if(ref.getdLawlessIng().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("疑似含有不法或其他有效成分");
							}
							if(ref.getdExpired().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("外觀異常");
							}
							if(ref.getdPackageError().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("包裝瑕疵");
							}
							if(ref.getdExpired().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("過期");
							}
							if(ref.getdOthers().equals("Y")){
								if(discriptCos0006Db.toString().length() > 0){
									discriptCos0006Db.append("、");
								}
								discriptCos0006Db.append("其他");
							}
							mailBody = mailBody.replace("[F_不良品狀況]", discriptCos0006Db.toString());
						}
					}else{
						mail = TCBWCommon.getCon1001DbMail("COS", "COS02", "COS020003");
						if(mail!=null && mail.length==2){
							isSendMail = true;
							mailTitle = Common.get(mail[0]).replace("[T_案號]", applNoStr);
							mailBody = Common.get(mail[1]);
							mailBody = mailBody.replace("[F_案號]", applNoStr);
							mailBody = mailBody.replace("[F_品名]", obj.getChProduct());
						}
					}
					if(isSendMail){
						ServiceGetter.getInstance().getTcbwService().sendEmail(mailTitle, mailBody, mailList, null, true, null, null, null, "COS", Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):Common.get(obj.getApplNo()));
					}
				}else{
					logger.info("[TCBW]-[CosinDaoImpl.java]-[" + logName + "]-[無TFDA群組EMAIL資料，不發信件]");
				}
			}
		}
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}

	@Override
	public void updateAdditionalCase(Cos0001Db obj, String userId) throws Exception {
		if(obj != null){
			String logType = "";
			if("1".equals(obj.getCosType())){
				logType = "COS02";
			}else{
				logType = "COS03";
			}
			
			obj.setStatus("30");
			obj.setChargeMan(userId);
			obj.setModifier(userId);
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());
			update(obj);
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "補件通知完成", userId);
			
			// 同步更新COS4001_DB.STATUS
			updateCos4001DbStatus(obj);
		}
		
	}
	
	public void updateCos4001DbStatus(Cos0001Db obj) throws Exception {
		if(obj != null){
			Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(obj.getCos4001DbId()));
			if(cos4001Db != null){
				cos4001Db.setApplNo(obj.getApplNo());
				cos4001Db.setStatus(obj.getStatus());
				cos4001Db.setModifier(obj.getModifier());
				cos4001Db.setModifyDate(obj.getModifyDate());
				cos4001Db.setModifyTime(obj.getModifyTime());
				update(cos4001Db);
			}
		}
	}
	
	public java.util.List<javax.mail.internet.InternetAddress> getEmailGroupData(String systemType, String mailGroupCode) throws Exception {
		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		
		java.util.List<Con1013Db> con1013DbList = load(" from Con1013Db where con1012Db.systemType = " + Common.sqlChar(systemType) + 
													   " and con1012Db.code = " + Common.sqlChar(mailGroupCode));
		if(con1013DbList!=null && con1013DbList.size()>0)
		{
			for(Con1013Db dtl : con1013DbList)
			{
				CommonUser user = dtl.getCommonUser();
				if(user!=null && !"".equals(Common.get(user.getUserEmail())) && Validate.checkEmail(Common.get(user.getUserEmail())))
				{
					mailList.add(new javax.mail.internet.InternetAddress(Common.get(user.getUserEmail())));
				}
			}
			con1013DbList.clear();
		}
		return mailList;
	}
	
	//產生備份PDF
	public void closedPrint(String applNo,String id,String userID,String status) throws Exception 
	{
		COSEX0102F cosex0102f=new COSEX0102F();
		
		if(!"".equals(applNo)) {
			if("90".equals(status)) {
				cosex0102f.setApplNo(applNo);
			} else if("02".equals(status)) {
				cosex0102f.setId(applNo);
			}
		}
		
//		if(!"".equals(applNo))cosex0102f.setApplNo(applNo);
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
		
		cosex0102f.setParameter(parms);
	    
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		javax.swing.table.DefaultTableModel model = cosex0102f.getTableModel();

		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/cos/COSIN0401R.jasper"));
		env.setFormat("PDF");	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	    
	    String cos = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("COS");
	    
	    String fileName="";//檔案名稱
	    String fileDir="COS000001Backup";//存放資料夾
	    
	    if(!"".equals(applNo))fileName=applNo;
	   
	    File meddir = new File(cos);
	    //判斷資料夾是否存在，若不存在則建立
	    if(!meddir.isDirectory())
	    {
	    	meddir.mkdir();
	    }	
	    
	    
	    
	    File dir = new File(cos+"\\"+fileDir+"\\");
	    //判斷資料夾是否存在，若不存在則建立
	    if(!dir.isDirectory())
	    {
	    	dir.mkdir();
	    }	
	  
	    File output = new File(cos+"\\"+fileDir+"\\"+fileName+".pdf");
	    //產生檔案存放
	    generator.reportToFile(output, parms);
	    
	    Con0001Db o = new Con0001Db();
	    
	    o.setFileKind("C");
	    o.setUpLoadId(Common.getLong(id));
	    o.setFileRoute(fileDir);
	    o.setFileName(fileName+".pdf");
	    o.setFileExplan("化粧品不良事件通報備查PDF");
	    o.setIsInsert("N");
	    o.setIsDelete("N");
	    o.setSystemType("COS000001");
	    o.setDbName("Cos0001Db");
	    o.setCreator(userID);
	    o.setCreateDate(Datetime.getYYYMMDD());
	    o.setCreateTime(Datetime.getHHMMSS());

	    save(o);

	    env.clear();

	}

	@Override
	public void updateByCOMPLE1002F(COMPLE1002F ref) throws Exception {
		Cos0001Db obj = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			boolean flag = false;
			if("Y".equals(ref.getCh1())){
				obj.setNotifierRevDate(ref.getNotifierRevDate());
				obj.setOccurDate(ref.getOccurDate());
				obj.setNotifierSource(ref.getNotifierSource());
				obj.setNotifierSourceOther(ref.getNotifierSourceOther());
				obj.setNotifierName(ref.getNotifierName());						// 通報者資料不能異動
				obj.setNotifierTel(ref.getNotifierTel());
				obj.setNotifierEamil(ref.getNotifierEamil());
				obj.setNotifierDept(ref.getNotifierDept());
				obj.setNotifierType(ref.getNotifierType());
				obj.setNotifierTitle(ref.getNotifierTitle());
				obj.setAddress(ref.getAddress());
				obj.setIsContactYn(ref.getIsContactYn());
				obj.setIsDamageYn(ref.getIsDamageYn());
				obj.setOtherInformation(ref.getOtherInformation());
				obj.setOtherExplain(ref.getOtherExplain());
				
				obj.setChProduct(ref.getChProduct());
				obj.setEnProduct(ref.getEnProduct());
				obj.setPermitKey(ref.getPermitKey());
				obj.setPermitNo(ref.getPermitNo());
				obj.setTraffickWay(ref.getTraffickWay());
				obj.setTraffickWayOther(ref.getTraffickWayOther());
				obj.setBusinessName(ref.getBusinessName());
				obj.setManufactorName(ref.getManufactorName());
				obj.setManufactorTelArea(ref.getManufactorTelArea());
				obj.setManufactorTel(ref.getManufactorTel());
				obj.setManufactorTelExt(ref.getManufactorTelExt());
				obj.setManufactorArea(ref.getManufactorArea());
				obj.setManufactorZipCode(ref.getManufactorZipCode());
				obj.setManufactorAddr(ref.getManufactorAddr());
				obj.setManufactorNo(ref.getManufactorNo());
				obj.setExpirationDate(ref.getExpirationDate());
				obj.setTradeDate(ref.getTradeDate());
				obj.setTradePlace(ref.getTradePlace());
				obj.setTradePlaceZipCode(ref.getTradePlaceZipCode());
				obj.setTradePlaceAddr(ref.getTradePlaceAddr());
				obj.setIsSampleYn(ref.getIsSampleYn());
				obj.setEvenContactYn(ref.getEvenContactYn());
				obj.setDealWith(ref.getDealWith());
				obj.setIsSimilarYn(ref.getIsSimilarYn());
				obj.setIsRecurrenceYn(ref.getIsRecurrenceYn());
				obj.setIsOtherDeptYn(ref.getIsOtherDeptYn());
				obj.setOtherDpetName(ref.getOtherDpetName());
				obj.setCaseNo(ref.getCaseNo());
				obj.setIngredient(ref.getIngredient());
				obj.setModifier(ref.getUserID());
				obj.setModifyDate(ref.getEditDate());
				obj.setModifyTime(ref.getEditTime());
				
				if("1".equals(obj.getCosType()) || "3".equals(obj.getCosType())){
					updateCos0003Db(obj, ref.getSubCode(), ref.getOtherDescribe(), ref.getUserID(), yyymmdd, hhmmss);
				}
				
				// 暫時不撰寫，此程式只針對不良品
				if("2".equals(obj.getCosType())){
					
				}
				
				// 案件本相關資料處理
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(obj.getId()), "C", "COS", ref.getCFileRow(), "COS0001DB", ref.arrCFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
				
				flag = true;
			}
			
			// 分類
			if("Y".equals(ref.getCh2())){
				if("1".equals(obj.getCosType())){
					Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where id = " + Common.getLong(ref.getCos0006DbId()));
					if(cos0006Db == null){
						cos0006Db = new Cos0006Db();
						cos0006Db.setApplNo(obj.getApplNo());
						cos0006Db.setCreator(ref.getUserID());
						cos0006Db.setCreateDate(yyymmdd);
						cos0006Db.setCreateTime(hhmmss);
					}
				//	if("disComplete".equals(ref.getState())){
						cos0006Db.setDisDate(yyymmdd);
				//	}else{
				//		cos0006Db.setDisDate("");
				//	}
					cos0006Db.setFirstResult(ref.getFirstResult());
					cos0006Db.setLeaveCaseReason(ref.getLeaveCaseReason());
					cos0006Db.setNonDefective(ref.getNonDefective());
					cos0006Db.setMarked(ref.getdMarked());
					cos0006Db.setLawlessIng(ref.getdLawlessIng());
					cos0006Db.setLawlessIngOther(ref.getdLawlessIngOther());
					cos0006Db.setExteriorError(ref.getdExteriorError());
					cos0006Db.setExteriorErrorOther(ref.getdExteriorErrorOther());
					cos0006Db.setPackageError(ref.getdPackageError());
					cos0006Db.setPackageErrorOther(ref.getdPackageErrorOther());
					cos0006Db.setExpired(ref.getdExpired());
					cos0006Db.setExpiredOther(ref.getdExpiredOther());
					cos0006Db.setOthers(ref.getdOthers());
					cos0006Db.setOthersDesc(ref.getdOthersDesc());
					cos0006Db.setModifier(ref.getUserID());
					cos0006Db.setModifyDate(yyymmdd);
					cos0006Db.setModifyTime(hhmmss);
					if(ref.getMeasure()!=null && ref.getMeasure().length>0){
						StringBuffer sb = new StringBuffer();
						for(String rid : ref.getMeasure()){
							if(sb.toString().length() > 0){
								sb.append(",");
							}
							sb.append(rid);
						}
						cos0006Db.setMeasure(sb.toString());
					}else{
						cos0006Db.setMeasure("");
					}
					saveOrUpdate(cos0006Db);	
					
					/* 不良品分類，初步判定結果選擇[資料不足留案備查-代碼02]，則狀態變更為結案
					if("disComplete".equals(ref.getState())){
						if("03".equals(Common.get(cos0006Db.getFirstResult()))){
							obj.setStatus("90");
							obj.setChargeMan("");															// 結案
						}else if("01".equals(Common.get(cos0006Db.getFirstResult()))){
							obj.setStatus("40");
							obj.setChargeMan(TCBWCommon.getUserID("COS02","03","Cos0001Db"));				// 處理
						}else{
							obj.setStatus("60");
							obj.setChargeMan(TCBWCommon.getUserID("COS02","04","Cos0001Db"));				// 評估
						}
					}
					
					// 分類歷史歷程
					if("disComplete".equals(ref.getState())){
						Cos0016Db cos0016Db = new Cos0016Db();
						cos0016Db.setCos0006Db(cos0006Db);
						cos0016Db.setCreator(ref.getUserID());
						cos0016Db.setCreateDate(yyymmdd);
						cos0016Db.setCreateTime(hhmmss);
						cos0016Db.setModifier(ref.getUserID());
						cos0016Db.setModifyDate(yyymmdd);
						cos0016Db.setModifyTime(hhmmss);
						save(cos0016Db);
					}*/
				}
				
				if("2".equals(obj.getCosType())){
					// 暫不撰寫
				}
				flag = true;
			}
			
			// 評估
			if("Y".equals(ref.getCh5())){
				if("1".equals(Common.get(obj.getCosType()))){
					Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where applNo = " + Common.sqlChar(obj.getApplNo()) + " order by id desc ");
					if(cos0006Db != null){
						Cos0007Db cos0007Db = (Cos0007Db)View.getObject(" from Cos0007Db where cos0006Db.id = " + Common.getLong(cos0006Db.getId()) + 
																		" and applNo = " + Common.sqlChar(cos0006Db.getApplNo()));
						if(cos0007Db == null){
							cos0007Db = new Cos0007Db();
							cos0007Db.setCos0006Db(cos0006Db);
							cos0007Db.setApplNo(cos0006Db.getApplNo());
							cos0007Db.setCreator(ref.getUserID());
							cos0007Db.setCreateDate(yyymmdd);
							cos0007Db.setCreateTime(hhmmss);
						}
						cos0007Db.setAssessMan(ref.getUserID());
						cos0007Db.setAssessResult(ref.getAssessResult());
						cos0007Db.setAssessRemark(ref.getAssessRemark());
						cos0007Db.setModifier(ref.getUserID());
						cos0007Db.setModifyDate(yyymmdd);
						cos0007Db.setModifyTime(hhmmss);
					//	if("assessComplete".equals(ref.getState())){
							cos0007Db.setAssessDate(yyymmdd);
					//	}else{
					//		cos0007Db.setAssessDate("");
					//	}
						saveOrUpdate(cos0007Db);
					}
				}
				
				if("2".equals(Common.get(obj.getCosType()))){
					// 暫不撰寫
				}
				flag = true;
			}
			
			// 廠商回覆
			if("Y".equals(ref.getCh3())){
				if("1".equals(Common.get(obj.getCosType()))){
					Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where id = " + Common.getLong(ref.getCos0009DbId()));
					if(cos0009Db == null){
						cos0009Db = new Cos0009Db();
						cos0009Db.setApplNo(obj.getApplNo());
					}
					cos0009Db.setHandling(ref.getHandling());
					cos0009Db.setPrecaution(ref.getPrecaution9());
					cos0009Db.setSimilarComplaint(ref.getSimilarComplaint());
					cos0009Db.setRemark(ref.getRemark9());
					cos0009Db.setPredictDate(ref.getPredictDate());
					cos0009Db.setReplyDate(ref.getReplyDate9());
					cos0009Db.setModifier(ref.getUserID());
					cos0009Db.setModifyDate(yyymmdd);
					cos0009Db.setModifyTime(hhmmss);
				//	if("caseBackUpdate".equals(ref.getState())){
						cos0009Db.setIsClose("Y");
				//	}else{
				//		cos0009Db.setIsClose("N");
				//	}
					saveOrUpdate(cos0009Db);
					
					try{
						ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0009Db.getId()), "COSVR", "COS", ref.getCOSVRFileRow(), "COS0009DB", ref.arrCOSVRFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + "化妝品修正作業" + "]-[不良品處理廠商回覆上傳檔案異常，COS0009_DB.ID : " + cos0009Db.getId() + " ]");
						e.printStackTrace();
					}
				}
				flag = true;
			}
			
			// 處理頁籤
			if("Y".equals(ref.getCh4())){
				if("1".equals(Common.get(obj.getCosType()))){
					Cos0011Db cos0011Db = (Cos0011Db)View.getObject(" from Cos0011Db where applNo = " + Common.sqlChar(obj.getApplNo()));
					if(cos0011Db == null){
						cos0011Db = new Cos0011Db();
						cos0011Db.setApplNo(Common.get(obj.getApplNo()));
						cos0011Db.setCreator(ref.getUserID());
						cos0011Db.setCreateDate(yyymmdd);
						cos0011Db.setCreateTime(hhmmss);
					}
					cos0011Db.setIsLetterYn(ref.getIsLetterYn11());
					cos0011Db.setReplyMemo(ref.getReplyMemo11());
					cos0011Db.setRemark(ref.getRemark11());
					cos0011Db.setModifier(ref.getUserID());
					cos0011Db.setModifyDate(yyymmdd);
					cos0011Db.setModifyTime(hhmmss);
					saveOrUpdate(cos0011Db);
					
					// 發文資料
					try{
						updateCos0012Db(cos0011Db, ref.getHttpRequest(), ref.getCOS0012DbRow(), ref.arrCOS0012DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + "化妝品修正作業" + "]-[更新COS0012_DB發生異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
						e.printStackTrace();
					}
					
					// 發文相關檔案
					try{
						ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0011Db.getId()), "COSPT", "COS", ref.getCOSPTFileRow(), "COS0011DB", ref.arrCOSPTFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + "化妝品修正作業" + "]-[不良品發文資料相關附件上傳異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
						e.printStackTrace();
					}
					
					// 收文資料
					try{
						updateCos0013Db(cos0011Db, ref.getHttpRequest(), ref.getCOS0013DbRow(), ref.arrCOS0013DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + "化妝品修正作業" + "]-[更新COS0013_DB發生異常，COS0011_DB.ID : " + cos0011Db.getId() + "]");
						e.printStackTrace();
					}
					
					// 收文相關檔案
					try{
						ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos0011Db.getId()), "COSRT", "COS", ref.getCOSRTFileRow(), "COS0011DB", ref.arrCOSRTFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
					}catch(Exception e){
						logger.info("[TCBW]-[CosinDaoImpl]-[" + "化妝品修正作業" + "]-[不良品收文資料相關附件上傳異常，COS0011_DB.ID : " + cos0011Db.getId() + " ]");
						e.printStackTrace();
					}
				}else{
					// 暫不處理
				}
				flag = true;
			}
			
			// 結案頁籤
			if("Y".equals(ref.getCh6())){
				Cos0015Db cos0015Db = (Cos0015Db)View.getObject(" from Cos0015Db where applNo = " + Common.sqlChar(obj.getApplNo()) +
																" and cosType = " + Common.sqlChar(obj.getCosType()));
				if(cos0015Db == null){
					cos0015Db = new Cos0015Db();
					cos0015Db.setApplNo(obj.getApplNo());
					cos0015Db.setCosType(obj.getCosType());
					cos0015Db.setCreator(ref.getUserID());
					cos0015Db.setCreateDate(yyymmdd);
					cos0015Db.setCreateTime(hhmmss);
				}
				cos0015Db.setCosCorrelation(ref.getCosCorrelation());
				cos0015Db.setFeedBack(ref.getFeedBack());
				cos0015Db.setRecordOpinion(ref.getRecordOpinion());
				cos0015Db.setModifier(ref.getUserID());
				cos0015Db.setModifyDate(yyymmdd);
				cos0015Db.setModifyTime(hhmmss);
				saveOrUpdate(cos0015Db);

			//	if("endCase".equals(ref.getState())){
			//		obj.setChargeMan("");
			//		obj.setStatus("90");
			//	}else{
			//		obj.setChargeMan(ref.getUserID());
			//		obj.setStatus("70");
			//	}
				flag = true;
			}
			
			if(flag){
				obj.setChargeMan(ref.getUserID());
				obj.setModifier(ref.getUserID());
				obj.setModifyDate(yyymmdd);
				obj.setModifyTime(hhmmss);
				update(obj);
			}
			
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg("修改成功 !");
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，無法更新 !");
		}
	}
	
	
	
}
