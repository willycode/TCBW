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
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0004Db;
import com.kangdainfo.tcbw.model.bo.Cos0005Db;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.model.bo.Cos4003Db;
import com.kangdainfo.tcbw.model.bo.Cos4004Db;
import com.kangdainfo.tcbw.model.bo.Cos4005Db;
import com.kangdainfo.tcbw.model.dao.CosexDao;
import com.kangdainfo.tcbw.view.cosex.COSEX0102F;
import com.kangdainfo.tcbw.view.cosex.COSEX0104F;
import com.kangdainfo.tcbw.view.cosex.COSEX0201F;

public class CosexDaoImpl extends BaseDaoImpl implements CosexDao{
	
	@Override
	public void updateDoCopyCos4001Db(Cos4001Db obj, String userId, String yyymmdd, String hhmmss) throws Exception {
		String[] ignoreFields = new String[]{"id"};
		Cos0001Db master = new Cos0001Db(); 
		org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
		master.setCreator(userId);
		master.setCreateDate(yyymmdd);
		master.setCreateTime(hhmmss);
		master.setCos4001DbId(Common.getLong(obj.getId()));
		save(master);													// FOR 檔案複製緣故，此就要先行存檔
		
		// 案件相關資料-檔案處理
		java.util.List<Con0001Db> cos4001DbFileList = load(" from Con0001Db where fileKind = 'C' and dbName = 'COS4001DB' " + " and upLoadId = " + Common.getLong(obj.getId()));
		if(cos4001DbFileList!=null && cos4001DbFileList.size()>0){
			java.util.List<Con0001Db> cos001DbFileList = new java.util.ArrayList<Con0001Db>();
			for(Con0001Db con0001Db : cos4001DbFileList){
				Con0001Db tmp = new Con0001Db();
				org.springframework.beans.BeanUtils.copyProperties(con0001Db, tmp, ignoreFields);
				tmp.setUpLoadId(master.getId());
				tmp.setDbName("COS0001DB");
				tmp.setCreator(userId);
				tmp.setCreateDate(yyymmdd);
				tmp.setCreateTime(hhmmss);
				cos001DbFileList.add(tmp);
			}
			saveBatch(cos001DbFileList);
			cos001DbFileList.clear();
			cos4001DbFileList.clear();
		}
		
		// 不良反應
		if(obj.getCos4002Dbs()!=null && obj.getCos4002Dbs().size()>0){
			java.util.List<Cos0004Db> cos0004DbsList = new java.util.ArrayList<Cos0004Db>();
			java.util.List<Cos0005Db> cos0005DbsList = new java.util.ArrayList<Cos0005Db>();
			java.util.List<Con0001Db> cos0002DbFileList = new java.util.ArrayList<Con0001Db>();
			
			java.util.Set cos0002Dbs = new ListOrderedSet();
			for(Object dtlObj : obj.getCos4002Dbs()){
				Cos4002Db cos4002Db = (Cos4002Db)dtlObj;	
				Cos0002Db cos0002Db = new Cos0002Db();
				org.springframework.beans.BeanUtils.copyProperties(cos4002Db, cos0002Db, ignoreFields);
				cos0002Db.setCos0001Db(master);
				cos0002Db.setCreator(userId);
				cos0002Db.setCreateDate(yyymmdd);
				cos0002Db.setCreateTime(hhmmss);
				save(cos0002Db);
				
				java.util.List<Cos4004Db> cos4004DbsList = load(" from Cos4004Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
				if(cos4004DbsList!=null && cos4004DbsList.size()>0){
					for(Cos4004Db cos4004Db : cos4004DbsList){
						Cos0004Db cos0004Db = new Cos0004Db();
						org.springframework.beans.BeanUtils.copyProperties(cos4004Db, cos0004Db, ignoreFields);
						cos0004Db.setCos0002Db(cos0002Db);
						cos0004Db.setCreator(userId);
						cos0004Db.setCreateDate(yyymmdd);
						cos0004Db.setCreateTime(hhmmss);
						cos0004DbsList.add(cos0004Db);
					}
				}
				java.util.List<Cos4005Db> cos4005DbsList = load(" from Cos4005Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
				if(cos4005DbsList!=null && cos4005DbsList.size()>0){
					for(Cos4005Db cos4005Db : cos4005DbsList){
						Cos0005Db cos0005Db = new Cos0005Db();
						org.springframework.beans.BeanUtils.copyProperties(cos4005Db, cos0005Db, ignoreFields);
						cos0005Db.setCos0002Db(cos0002Db);
						cos0005Db.setCreator(userId);
						cos0005Db.setCreateDate(yyymmdd);
						cos0005Db.setCreateTime(hhmmss);
						cos0005DbsList.add(cos0005Db);
					}
				}
				java.util.List<Con0001Db> cos4002DbFileList = load(" from Con0001Db where fileKind in ('COSDP','COSSD','COSID')  and dbName = 'COS4002DB' and upLoadId = " + Common.getLong(cos4002Db.getId()));
				if(cos4002DbFileList!=null && cos4002DbFileList.size()>0){
					for(Con0001Db con0001Db : cos4002DbFileList){
						Con0001Db tmp = new Con0001Db();
						org.springframework.beans.BeanUtils.copyProperties(con0001Db, tmp, ignoreFields);
						tmp.setUpLoadId(cos0002Db.getId());
						tmp.setDbName("COS0002DB");
						tmp.setCreator(userId);
						tmp.setCreateDate(yyymmdd);
						tmp.setCreateTime(hhmmss);
						cos0002DbFileList.add(tmp);
					}
				}
				
				cos0002Dbs.add(cos0002Db);
				saveBatch(cos0004DbsList);
				saveBatch(cos0005DbsList);
				saveBatch(cos0002DbFileList);
				
				cos0004DbsList.clear();
				cos0005DbsList.clear();
				cos0002DbFileList.clear();
			}
			master.setCos0002Dbs(cos0002Dbs);
		}
		
		// 不良品
		if(obj.getCos4003Dbs()!=null && obj.getCos4003Dbs().size()>0){
			java.util.Set cos0003Dbs = new ListOrderedSet();
			for(Object dtlObj : obj.getCos4003Dbs()){
				Cos4003Db cos4003Db = (Cos4003Db)dtlObj;
				Cos0003Db cos0003Db = new Cos0003Db();
				org.springframework.beans.BeanUtils.copyProperties(cos4003Db, cos0003Db, ignoreFields);
				cos0003Db.setCos0001Db(master);
				cos0003Db.setCreator(userId);
				cos0003Db.setCreateDate(yyymmdd);
				cos0003Db.setCreateTime(hhmmss);
				cos0003Dbs.add(cos0003Db);
			}
			master.setCos0003Dbs(cos0003Dbs);
		}

		update(master);
	}
		
	@Override
	public void insertByCOSEX0102F(COSEX0102F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[COSEX0102F]-[新增]-[無法辨別登入的使用者]");
		}
		
		Cos4001Db obj = new Cos4001Db();
		obj.setStatus("00");
		obj.setNotifierName(c.getUserName());
		obj.setNotifierTitle(c.getJobTitle());
		
		if(c.getCommonDepartment() != null){
			obj.setNotifierType(c.getCommonDepartment().getShortCode());
		}else{
			obj.setNotifierType("");
		}
		obj.setNotifierDeptId(c.getUserJob());
		obj.setNotifierDept(View.getNotifierDeptName(Common.get(obj.getNotifierDeptId()), Common.get(obj.getNotifierType())));
		
		obj.setNotifierTelArea(c.getUserTelArea());
		obj.setNotifierTel(c.getUserTel());	
		obj.setNotifierTelExt(c.getUserTelExt());
		obj.setNotifierEamil(c.getUserEmail());
		obj.setNotifierArea(c.getUserCounty());
		obj.setNotifierZipCode(c.getUserZipCode());
		obj.setAddress(c.getUserAddr());
		if(null != obj.getNotifierType() && "" != obj.getNotifierType()){
			if("01".equals(obj.getNotifierType())){
				obj.setNotifierSource("06");
			}else if("02".equals(obj.getNotifierType())){
				obj.setNotifierSource("05");
			}else if("03".equals(obj.getNotifierType())){
				obj.setNotifierSource("02");
			}else if("04".equals(obj.getNotifierType())){
				obj.setNotifierSource("03");
			}
		}
		
		
		obj.setCosType(ref.getCosType());
		obj.setCaseOwner(c.getUserId());
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		obj.setModifier(c.getUserId());
		obj.setModifyDate(Datetime.getYYYMMDD());
		obj.setModifyTime(Datetime.getHHMMSS());
		
		java.util.Set dtl42Set = new ListOrderedSet();
		if("2".equals(Common.get(obj.getCosType())) || "3".equals(Common.get(obj.getCosType()))){
			Cos4002Db cos42 = new Cos4002Db();
			cos42.setCos4001Db(obj);
			cos42.setCreator(c.getUserId());
			cos42.setCreateDate(Datetime.getYYYMMDD());
			cos42.setCreateTime(Datetime.getHHMMSS());
			cos42.setModifier(c.getUserId());
			cos42.setModifyDate(Datetime.getYYYMMDD());
			cos42.setModifyTime(Datetime.getHHMMSS());
			
			java.util.Set dtlSet = new ListOrderedSet();
			Cos4004Db cos44 = new Cos4004Db();
			cos44.setCos4002Db(cos42);
			cos44.setCreator(c.getUserId());
			cos44.setCreateDate(Datetime.getYYYMMDD());
			cos44.setCreateTime(Datetime.getHHMMSS());
			cos44.setModifier(c.getUserId());
			cos44.setModifyDate(Datetime.getYYYMMDD());
			cos44.setModifyTime(Datetime.getHHMMSS());
			dtlSet.add(cos44);
			cos42.setCos4004Dbs(dtlSet);
			
			dtlSet = new ListOrderedSet();
			Cos4005Db cos45 = new Cos4005Db();
			cos45.setCos4002Db(cos42);
			cos45.setCreator(c.getUserId());
			cos45.setCreateDate(Datetime.getYYYMMDD());
			cos45.setCreateTime(Datetime.getHHMMSS());
			cos45.setModifier(c.getUserId());
			cos45.setModifyDate(Datetime.getYYYMMDD());
			cos45.setModifyTime(Datetime.getHHMMSS());
			dtlSet.add(cos45);
			cos42.setCos4005Dbs(dtlSet);
			dtl42Set.add(cos42);
		}
		obj.setCos4002Dbs(dtl42Set);
		save(obj);

		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", obj.getId(), "", "00", "新增一筆登錄資料", c.getUserId());
		ref.setId(Common.get(obj.getId()));
	}

	@Override
	public void updateByCOSEX0102F(COSEX0102F ref, boolean isAutoSave) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		String logName = "";
		String msg = "";
		if("pauseSave".equals(ref.getState())){
			logName = "化妝品通報登入作業-外部-暫存";
			msg = "暫存完成";
		}else if("stayedUpload".equals(ref.getState())){
			logName = "化妝品通報登入作業-外部-待上傳";
			msg = "待上傳完成";
		}else if("doSend".equals(ref.getState())){
			logName = "化妝品通報登入作業-外部-送出";
			msg = "送出完成";
		}
		if(isAutoSave){
			logName = "化妝品通報登入作業-外部-自動儲存";
		}
		
		Cos4001Db obj = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Cos4001Db();
			obj.setCaseOwner(ref.getUserID());
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			save(obj);
			logger.info("[TCBW]-[CosDaoImpl]-[" + logName + "]-[查無COS0001_DB，新建一筆]");
		}
		
		// 案件狀態處理
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("stayedUpload".equals(ref.getState())){
			obj.setStatus("01");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
		}
		if(isAutoSave){
			obj.setStatus("00");
		}
		
		// 不良事件類別處理 - 注意自動儲存時，當原案件為不良品時，得隨著畫面[不良品是否有損害使用者身體或健康之事實]挑選的做變化
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
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierSource(ref.getNotifierSource());
		obj.setNotifierSourceOther(ref.getNotifierSourceOther());
		obj.setNotifierName(ref.getNotifierName());
		obj.setNotifierTelArea(ref.getNotifierTelArea());
		obj.setNotifierTel(ref.getNotifierTel());
		obj.setNotifierTelExt(ref.getNotifierTelExt());
		obj.setNotifierEamil(ref.getNotifierEamil());
		obj.setNotifierArea(ref.getNotifierArea());
		obj.setNotifierZipCode(ref.getNotifierZipCode());
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
		obj.setTradePlace(ref.getTradePlace());
		obj.setTradePlaceZipCode(ref.getTradePlaceZipCode());
		obj.setTradePlaceAddr(ref.getTradePlaceAddr());
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
			
			if("1".equals(obj.getCosType())){
				if(obj.getCos4002Dbs()!=null && obj.getCos4002Dbs().size()>0){
					java.util.List<Cos4002Db> dList = new java.util.ArrayList<Cos4002Db>(); 
					for(Object dtlObj : obj.getCos4002Dbs()){
						Cos4002Db dtl = (Cos4002Db)dtlObj;
						dList.add(dtl);
						
						java.util.List<Cos4004Db> cos04List = load(" from Cos4004Db where cos4002Db.id = " + Common.getLong(dtl.getId()));
						java.util.List<Cos4005Db> cos05List = load(" from Cos4005Db where cos4002Db.id = " + Common.getLong(dtl.getId()));
						java.util.List<Con0001Db> fileList = load(" from Con0001Db where fileKind in ('COSSD','COSDP','COSID') and dbName = 'COS4002DB' and upLoadId = " + Common.getLong(dtl.getId()));
						if(fileList!=null && fileList.size()>0){
							for(Con0001Db con0001Db : fileList){
								String fileID = Common.isoToBig5(Common.get(con0001Db.getFileRoute()));
								File dir = new File("." + File.separator + "upload" + File.separator + "COS" + File.separator + fileID);
								try{
									Common.RemoveDirectory(dir);
									delete(dtl);
								}catch(Exception e){
									e.printStackTrace();
								}
							}
							deleteBatch(fileList);
							fileList.clear();
						}
						deleteBatch(cos04List);
						deleteBatch(cos05List);
					}
					if(dList.size() > 0){
						for(Cos4002Db dtl : dList){
							dtl.getCos4001Db().getCos4002Dbs().remove(dtl);
							dtl.setCos4001Db(null);
						}
						deleteBatch(dList);
						dList.clear();
					}
				}
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
				updateCos4004Db(cos4002Db, ref.getHttpRequest(), ref.getCOS4004DbRow(), ref.arrCOS4004DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosexDaoImpl]-[" + logName + "]-[更新COS4004_DB發生異常，COS4002_DB.ID : " + cos4002Db.getId() + "]");
				e.printStackTrace();
			}
			
			// 相關附件(相關檢查及檢驗數據)
			try{
				updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSID", "COS", ref.getCOSIDFileRow(), "COS4002DB", ref.arrCOSIDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(醫師診斷證明)
			try{
				updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSDP", "COS", ref.getCOSDPFileRow(), "COS4002DB", ref.arrCOSDPFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 相關附件(就醫紀錄)
			try{
				updateCon0001Db(ref.getHttpRequest(), Common.getLong(cos4002Db.getId()), "COSSD", "COS", ref.getCOSSDFileRow(), "COS4002DB", ref.arrCOSSDFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			// 併用的其他化妝品
			try{
				updateCos4005Db(cos4002Db, ref.getHttpRequest(), ref.getCOS4005DbRow(), ref.arrCOS4005DbFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				logger.info("[TCBW]-[CosexDaoImpl]-[" + logName + "]-[更新COS4005_DB發生異常，COS4002_DB.ID : " + cos4002Db.getId() + "]");
				e.printStackTrace();
			}
		}
		saveOrUpdate(obj);
		
		// 案件本身相關資料處理
		updateCon0001Db(ref.getHttpRequest(), Common.getLong(obj.getId()), "C", "COS", ref.getCFileRow(), "COS4001DB", ref.arrCFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		
		if("10".equals(obj.getStatus())){
			updateDoCopyCos4001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
		}
		
		// 寫入流程
		if(!isAutoSave){
			if(!"pauseSave".equals(ref.getState())){
				if("stayedUpload".equals(ref.getState())){
					logName = "待上傳";
				}else if("doSend".equals(ref.getState())){
					logName = "送出";
				}
				
				if("10".equals(obj.getStatus())){
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
				}else{
					ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), logName, ref.getUserID());
				}
			}
		}
		
		ref.setId(Common.get(ref.getId()));
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}

	public void updateCos4004Db(Cos4002Db cos4002Db, javax.servlet.ServletRequest httpRequest, String[] COS4004DbRow, String[] arrCOS4004DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos4004Db> oCos04Map = new java.util.HashMap<Long, Cos4004Db>();  
		java.util.List<Cos4004Db> cos04List = load(" from Cos4004Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
		if(cos04List!=null && cos04List.size()>0){
			for(Cos4004Db dtl : cos04List){
				oCos04Map.put(dtl.getId(), dtl);
			}
			cos04List.clear();
		}
		java.util.List<Cos4004Db> dCos04List = new java.util.ArrayList<Cos4004Db>();
		if(httpRequest!=null && COS4004DbRow!=null && COS4004DbRow.length>0){
			if(oCos04Map.size() > 0){
				for(Map.Entry<Long, Cos4004Db> dtl : oCos04Map.entrySet()){
					boolean flag = true;
					for(String rid : COS4004DbRow){
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
			for(String rid : COS4004DbRow){
				boolean isUpdate = true;
				Cos4004Db cos4004Db = oCos04Map.get(Common.getLong(rid));
				if(cos4004Db == null){
					cos4004Db = new Cos4004Db();
					cos4004Db.setCos4002Db(cos4002Db);
					cos4004Db.setCreator(userId);
					cos4004Db.setCreateDate(yyymmdd);
					cos4004Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos4004Db.setTestDate(httpRequest.getParameter(arrCOS4004DbFieldName[0] + rid));
				cos4004Db.setTestItems(httpRequest.getParameter(arrCOS4004DbFieldName[1] + rid));
				cos4004Db.setTestNum(httpRequest.getParameter(arrCOS4004DbFieldName[2] + rid));
				cos4004Db.setModifier(userId);
				cos4004Db.setModifyDate(yyymmdd);
				cos4004Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos4004Db);
				}else{
					save(cos4004Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos4004Db> dtl : oCos04Map.entrySet()){
				dCos04List.add(dtl.getValue());
			}
		}
		if(dCos04List.size() > 0){
			deleteBatch(dCos04List);
			dCos04List.clear();
		}
		oCos04Map.clear();
	}
	
	public void updateCos4005Db(Cos4002Db cos4002Db, javax.servlet.ServletRequest httpRequest, String[] COS4005DbRow, String[] arrCOS4005DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception{
		java.util.Map<Long, Cos4005Db> oCos05Map = new java.util.HashMap<Long, Cos4005Db>();  
		java.util.List<Cos4005Db> cos05List = load(" from Cos4005Db where cos4002Db.id = " + Common.getLong(cos4002Db.getId()));
		if(cos05List!=null && cos05List.size()>0){
			for(Cos4005Db dtl : cos05List){
				oCos05Map.put(dtl.getId(), dtl);
			}
			cos05List.clear();
		}
		java.util.List<Cos4005Db> dCos05List = new java.util.ArrayList<Cos4005Db>();
		if(httpRequest!=null && COS4005DbRow!=null && COS4005DbRow.length>0){
			if(oCos05Map.size() > 0){
				for(Map.Entry<Long, Cos4005Db> dtl : oCos05Map.entrySet()){
					boolean flag = true;
					for(String rid : COS4005DbRow){
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
			for(String rid : COS4005DbRow){
				boolean isUpdate = true;
				Cos4005Db cos4005Db = oCos05Map.get(Common.getLong(rid));
				if(cos4005Db == null){
					cos4005Db = new Cos4005Db();
					cos4005Db.setCos4002Db(cos4002Db);
					cos4005Db.setCreator(userId);
					cos4005Db.setCreateDate(yyymmdd);
					cos4005Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				cos4005Db.setcName(httpRequest.getParameter(arrCOS4005DbFieldName[0] + rid));
				cos4005Db.setManufactorName(httpRequest.getParameter(arrCOS4005DbFieldName[1] + rid));
				cos4005Db.setUseDateS(httpRequest.getParameter(arrCOS4005DbFieldName[2] + rid));
				cos4005Db.setUseDateE(httpRequest.getParameter(arrCOS4005DbFieldName[3] + rid));
				cos4005Db.setUseRate(httpRequest.getParameter(arrCOS4005DbFieldName[4] + rid));
				cos4005Db.setUseMethod(httpRequest.getParameter(arrCOS4005DbFieldName[5] + rid));
				cos4005Db.setManufactorNo(httpRequest.getParameter(arrCOS4005DbFieldName[6] + rid));
				cos4005Db.setExpirationDate(httpRequest.getParameter(arrCOS4005DbFieldName[7] + rid));
				cos4005Db.setTradeDate(httpRequest.getParameter(arrCOS4005DbFieldName[8] + rid));
				cos4005Db.setModifier(userId);
				cos4005Db.setModifyDate(yyymmdd);
				cos4005Db.setModifyTime(hhmmss);
				if(isUpdate){
					update(cos4005Db);
				}else{
					save(cos4005Db);
				}
			}
		}else{
			for(Map.Entry<Long, Cos4005Db> dtl : oCos05Map.entrySet()){
				dCos05List.add(dtl.getValue());
			}
		}
		if(dCos05List.size() > 0){
			deleteBatch(dCos05List);
			dCos05List.clear();
		}
		oCos05Map.clear();
	}
	
	public void updateCon0001Db(javax.servlet.ServletRequest httpRequest, Long uploadId, String fileType, String fileStoreType, String[] fileRow, String dbName, String[] fileField, String userId, String yyymmdd, String hhmmss)throws Exception{
		java.util.List<Con0001Db> fileList = load(" from Con0001Db where fileKind = " + Common.sqlChar(fileType) + " and dbName = " + Common.sqlChar(dbName) + " and upLoadId = " + Common.getLong(uploadId));
		if(fileList!=null && fileList.size()>0){
		//	for(Con0001Db dtl : fileList){
			//	String fileID = Common.isoToBig5(Common.get(dtl.getFileRoute()));
			//	File dir = new File("." + File.separator + "upload" + File.separator + fileStoreType + File.separator + fileID);
			//	try{
			//		Common.RemoveDirectory(dir);
			//		delete(dtl);
			//	}catch(Exception e){
			//		e.printStackTrace();
			//	}
		//	}
			deleteBatch(fileList);
			fileList.clear();
		}
		
		if(httpRequest!=null && fileRow!=null && fileRow.length>0){
			java.util.List<Con0001Db> sList = new java.util.ArrayList<Con0001Db>(); 
			for(String rid : fileRow){
				Con0001Db dtl = new Con0001Db();
				dtl.setFileKind(fileType);
				dtl.setUpLoadId(uploadId);
				dtl.setDbName(dbName);
				dtl.setFileName(httpRequest.getParameter(fileField[0] + rid));
				dtl.setFileRoute(httpRequest.getParameter(fileField[1] + rid));
				dtl.setFileExplan(httpRequest.getParameter(fileField[2] + rid));
				dtl.setCreator(userId);
				dtl.setCreateDate(yyymmdd);
				dtl.setCreateTime(hhmmss);
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				sList.add(dtl);
			}
			if(sList.size() > 0){
				save(sList);
				sList.clear();
			}
		}
	}

	@Override
	public void deleteByCOSEX0102F(COSEX0102F ref) throws Exception {
		Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(ref.getId()));
		if(cos4001Db != null){
			if(cos4001Db.getCos4002Dbs()!=null && cos4001Db.getCos4002Dbs().size()>0){
				for(Object dtlObj : cos4001Db.getCos4002Dbs()){
					Cos4002Db cos4002Db = (Cos4002Db)dtlObj;
					
					java.util.List<Con0001Db> con0001DbsList = load(" from Con0001Db where fileKind in ('COSSD','COSDP','COSID') and dbName = 'COS4002DB' and upLoadId = " + Common.getLong(cos4002Db.getId()));
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
	public void updateByAutoCOSEX0102F(COSEX0102F ref) throws Exception {
		updateByCOSEX0102F(ref, true);
	}
	
	@Override
	public void updateCaseReply(COSEX0104F ref) throws Exception {
		Cos4001Db cos4001Db = (Cos4001Db)View.getObject(" from Cos4001Db where id = " + Common.getLong(ref.getCos4001DbId()));
		if(cos4001Db != null){
			Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where cos4001DbId = " + Common.getLong(cos4001Db.getId()));
			if(cos0001Db != null){
				String yyymmdd = Datetime.getYYYMMDD();
				String hhmmss = Datetime.getHHMMSS();
				
				// 更新回覆檔
				Con0004Db con0004Db = (Con0004Db)View.getObject(" from Con0004Db where id = " + Common.getLong(ref.getCon0004DbId()));
				if(con0004Db != null){
					con0004Db.setReplyDate(ref.getReplyDate());
					con0004Db.setReplyBody(ref.getReplyBody());
					con0004Db.setModifier(ref.getEditID());
					con0004Db.setModifyDate(yyymmdd);
					con0004Db.setModifyTime(hhmmss);
				//	update(con0004Db);
				}
				
				CommonUser man = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(cos0001Db.getChargeMan()));
				if(man != null){
					if(!"".equals(Common.get(man.getUserEmail())) && Validate.checkEmail(Common.get(man.getUserEmail()))){
						java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
						mailList.add(new javax.mail.internet.InternetAddress(Common.get(man.getUserEmail())));
						
						java.util.List<String> attFile = new java.util.ArrayList<String>();
						java.util.List<Con0001Db> sList = new java.util.ArrayList<Con0001Db>();
						if(ref.getHttpRequest()!=null && ref.getATTFileRow()!=null && ref.getATTFileRow().length>0){
							for(String rid : ref.getATTFileRow()){
								Con0001Db dtl = new Con0001Db();
								dtl.setFileKind("COSOT");
								dtl.setUpLoadId(con0004Db.getId());
								dtl.setFileName(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[0] + rid));
								dtl.setFileRoute(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[1] + rid));
								dtl.setDbName("Con0004Db");
								dtl.setCreator(ref.getEditID());
								dtl.setCreateDate(yyymmdd);
								dtl.setCreateTime(hhmmss);
								dtl.setModifier(ref.getEditID());
								dtl.setModifyDate(yyymmdd);
								dtl.setModifyTime(hhmmss);
								sList.add(dtl);
								
								//再增加一筆內部附件資料
								Con0001Db dtlC = new Con0001Db();
								dtlC.setFileKind("C");
								dtlC.setUpLoadId(cos0001Db.getId());
								dtlC.setFileName(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[0] + rid));
								dtlC.setFileRoute(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[1] + rid));
								dtlC.setDbName("COS0001DB");
								dtlC.setFileExplan(Common.formatYYYMMDD(ref.getReplyDate(),4)+"補件回覆");
								dtlC.setCreator(ref.getEditID());
								dtlC.setCreateDate(yyymmdd);
								dtlC.setCreateTime(hhmmss);
								dtlC.setModifier(ref.getEditID());
								dtlC.setModifyDate(yyymmdd);
								dtlC.setModifyTime(hhmmss);
								sList.add(dtlC);
								
								String f = "./upload/COS" + File.separator + 
										   Common.get(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[1] + rid)) + File.separator + 
										   Common.get(ref.getHttpRequest().getParameter(ref.arrAttFileFieldName[0] + rid));
								attFile.add(f);
							}
						}
						
						try{
							ServiceGetter.getInstance().getTcbwService().sendEmail("【補件回覆】", ref.getReplyBody(), mailList, attFile, true, null, null, null);
							mailList.clear();
							attFile.clear();
							if(sList.size() > 0){
								saveBatch(sList);
								sList.clear();
							}
							if("30".equals(Common.get(cos4001Db.getStatus()))){
								cos4001Db.setStatus("20");
								cos4001Db.setModifier(ref.getEditID());
								cos4001Db.setModifyDate(yyymmdd);
								cos4001Db.setModifyTime(hhmmss);
							//	update(cos4001Db);
								
							//	ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", cos4001Db.getId(), Common.get(cos4001Db.getApplNo()), cos4001Db.getStatus(), "補件回覆完成", ref.getEditID());
							}
							if("30".equals(Common.get(cos0001Db.getStatus()))){
								cos0001Db.setStatus("20");
								cos0001Db.setModifier(ref.getEditID());
								cos0001Db.setModifyDate(yyymmdd);
								cos0001Db.setModifyTime(hhmmss);
							//	update(cos0001Db);
							//	ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", cos0001Db.getId(), Common.get(cos0001Db.getApplNo()), cos0001Db.getStatus(), "補件回覆完成", ref.getEditID());
							}
							ref.setActionMsg("replySuccess");
						}catch(Exception e){
							ref.setActionMsg("replyError");
							ref.setErrorMsg("發送信件時發生未預期錯誤，無法回覆");
							logger.info("[TCBW]-[CosexDaoImpl]-[外部補件回覆]-[發送信箱發生未預期錯誤，案號 : " + cos0001Db.getApplNo() + " ]");
							e.printStackTrace();
						}
					}else{
						ref.setActionMsg("replyError");
						ref.setErrorMsg("案件作業人員信箱異常，無法回覆");
						logger.info("[TCBW]-[CosexDaoImpl]-[外部補件回覆]-[不發信件，案件作業人員信箱異常，案號 : " + cos0001Db.getApplNo() + " ]");
					}
				}else{
					ref.setActionMsg("replyError");
					ref.setErrorMsg("無案件作業人員資料，無法回覆");
					logger.info("[TCBW]-[CosexDaoImpl]-[外部補件回覆]-[不發信件，案件作業人員資料空值，案號 : " + cos0001Db.getApplNo() + " ]");
				}
				
				
				
			}else{
				ref.setActionMsg("replyError");
				ref.setErrorMsg("查無該筆關聯案件資料，無法回覆");
			}
		}else{
			ref.setActionMsg("replyError");
			ref.setErrorMsg("查無該筆資料，無法回覆");
		}
	}

	@Override
	public void updateSendByCOSEX0201F(COSEX0201F ref) throws Exception {
		if(ref.getFds()!=null && ref.getFds().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getFds()){
				if(!"".equals(Common.get(rid))){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(Common.getLong(rid));
				}
			}
			if(sb.toString().length() > 0){
				java.util.List objList = load(" from Cos4001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0){
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					String userId = ref.getUserID();
					
					for(Object dtlObj : objList){
						Cos4001Db dtl = (Cos4001Db)dtlObj;
						if("01".equals(Common.get(dtl.getStatus()))){
							dtl.setStatus("10");
							dtl.setNotifierRepDate(yyymmdd);
							dtl.setModifier(userId);
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							try{
								updateDoCopyCos4001Db(dtl, userId, yyymmdd, hhmmss);
								update(dtl);
								
								// 流程檔處理
								long cos0001ID = Common.getLong(View.getLookupField(" select id from Cos0001Db where cos4001DbId = " + dtl.getId()));
								java.util.List<Con2001Db> con2001DbList = load(" from Con2001Db where systemType = 'COS01' and formID = " + dtl.getId());
								if(con2001DbList!=null && con2001DbList.size()>0){
									for(Con2001Db con2001Db : con2001DbList){
										con2001Db.setFormID(cos0001ID);
									}
									updateBatch(con2001DbList);
									con2001DbList.clear();
								}
								ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS01", cos0001ID, "", dtl.getStatus(), "送出", ref.getUserID());
							}catch(Exception e){
								logger.info("[TCBW]-[CosexDaoImpl]-[化妝品非預期反應-外部-待上傳作業]-[案件送出複製資料時發生錯誤，不更新資料，COS4001_DB.ID : " + dtl.getId() + "]");
								e.printStackTrace();
							}
						}else{
							logger.info("[TCBW]-[CosexDaoImpl]-[化妝品非預期反應-外部-待上傳作業]-[案件狀態不為待上傳，不更新資料，COS4001_DB.ID : " + dtl.getId() + "]");
						}
					}
					objList.clear();
				}
			}else{
				logger.info("[TCBW]-[CosexDaoImpl]-[化妝品非預期反應-外部-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
			}
		}else{
			logger.info("[TCBW]-[CosexDaoImpl]-[化妝品非預期反應-外部-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
