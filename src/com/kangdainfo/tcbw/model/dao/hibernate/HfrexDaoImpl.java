package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletRequest;
import org.apache.commons.collections.set.ListOrderedSet;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.model.dao.HfrexDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.hfrex.HFREX0102F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0104F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0201F;

public class HfrexDaoImpl extends BaseDaoImpl implements HfrexDao{
	
	public void updateDoCopyHfr0001Db(Hfr4001Db obj, String userId, String yyymmdd, String hhmmss) throws Exception{
		if(obj != null){
			String[] ignoreFields = new String[]{"id"};
			
			Hfr0001Db master = new Hfr0001Db(); 
			org.springframework.beans.BeanUtils.copyProperties(obj, master, ignoreFields);
			master.setCreator(userId);
			master.setCreateDate(yyymmdd);
			master.setCreateTime(hhmmss);
			master.setHfr4001DbId(obj.getId());
			
			if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
				java.util.Set hfr0002Dbs = new ListOrderedSet();
				for(Object dtlObj : obj.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					
					Hfr0002Db tmpDtl = new Hfr0002Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, ignoreFields);
					tmpDtl.setHfr0001Db(master);
					tmpDtl.setCreator(userId);
					tmpDtl.setCreateDate(yyymmdd);
					tmpDtl.setCreateTime(hhmmss);
					hfr0002Dbs.add(tmpDtl);
				}
				master.setHfr0002Dbs(hfr0002Dbs);
			}
					
			if(obj.getHfr4003Dbs()!=null && obj.getHfr4003Dbs().size()>0){
				java.util.Set hfr0003Dbs = new ListOrderedSet();
				for(Object dtlObj : obj.getHfr4003Dbs()){
					Hfr4003Db dtl = (Hfr4003Db)dtlObj;
					
					Hfr0003Db tmpDtl = new Hfr0003Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, ignoreFields);
					tmpDtl.setHfr0001Db(master);
					tmpDtl.setCreator(userId);
					tmpDtl.setCreateDate(yyymmdd);
					tmpDtl.setCreateTime(hhmmss);
					hfr0003Dbs.add(tmpDtl);
				}
				master.setHfr0003Dbs(hfr0003Dbs);
			}
			
			if(obj.getHfr4004Dbs()!=null && obj.getHfr4004Dbs().size()>0){
				java.util.Set hfr0004Dbs = new ListOrderedSet();
				for(Object dtlObj : obj.getHfr4004Dbs()){
					Hfr4004Db dtl = (Hfr4004Db)dtlObj;
					
					Hfr0004Db tmpDtl = new Hfr0004Db(); 
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, ignoreFields);
					tmpDtl.setHfr0001Db(master);
					tmpDtl.setCreator(userId);
					tmpDtl.setCreateDate(yyymmdd);
					tmpDtl.setCreateTime(hhmmss);
					hfr0004Dbs.add(tmpDtl);
				}
				master.setHfr0004Dbs(hfr0004Dbs);
			}
			save(master);
			
			// 複製檔案
			java.util.List<Con0001Db> fileList = load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR4001DB' " +  
					  								  " and upLoadId = " + Common.getLong(obj.getId()));
			if(fileList!=null && fileList.size()>0){
				java.util.List<Con0001Db> sList = new java.util.ArrayList<Con0001Db>(); 
				for(Con0001Db dtl : fileList){
					Con0001Db tmpDtl = new Con0001Db();
					org.springframework.beans.BeanUtils.copyProperties(dtl, tmpDtl, ignoreFields);
					tmpDtl.setUpLoadId(master.getId());
					tmpDtl.setDbName("HFR0001DB");
					tmpDtl.setCreator(userId);
					tmpDtl.setCreateDate(yyymmdd);
					tmpDtl.setCreateTime(hhmmss);
					sList.add(tmpDtl);
				}
				if(sList.size() > 0){
					saveBatch(sList);
				}
				sList.clear();
				fileList.clear();
			}
		}
		
	}
	
	@Override
	public void insertByHFREX0102F(HFREX0102F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[HfrexDaoImpl]-[外部簡表-新增]-[無法辨別登入的使用者]");
		}
		
		Hfr4001Db obj = new Hfr4001Db();
		obj.setHfrType("1");
		obj.setStatus("00");
		obj.setNotifierName(c.getUserName());
		obj.setNotifierType("01");								// 屬性
		obj.setNotifierTitle(c.getJobTitle());					// 職稱
		obj.setNotifierDeptId(c.getUserJob());					// 服務機構
		obj.setNotifierTelArea(c.getUserTelArea());
		obj.setNotifierTel(c.getUserTel());	
		obj.setNotifierTelExt(c.getUserTelExt());
		obj.setNotifierEamil(c.getUserEmail());
		obj.setEatersName(c.getUserName());
		obj.setEatersIdNum(Common.get(c.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(c.getPersonalId())));
		obj.setEatersTelArea(c.getUserTelArea());
		obj.setEatersTel(c.getUserTel());
		obj.setEatersTelExt(c.getUserTelExt());
		obj.setCaseOwner(c.getUserId());
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		save(obj);
		ref.setId(Common.get(obj.getId()));
		
//		ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "新增食品簡表案件", c.getUserId());
	}
	
	@Override
	public void updateByHFREX0102F(HFREX0102F ref, boolean isAutoSave) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		String logType = "HFR01";
		if("pauseSave".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部簡表)-暫存";
			msg = "暫存完成";
		}else if("stayedUpload".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部簡表)-待上傳";
			msg = "待上傳完成";
		}else if("doSend".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部簡表)-送出";
			msg = "送出完成";
		}
		if(isAutoSave){
			logName = "食品非預期反應通報登錄作業(外部簡表)-自動儲存";
			msg = "自動儲存完成";
		}
		
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Hfr4001Db();
			obj.setHfrType("1");
			obj.setNotifierType(ref.getNotifierType());
			obj.setCaseOwner(ref.getUserID());
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[查無HFR4001_DB，新建一筆]");
		}
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("stayedUpload".equals(ref.getState())){
			obj.setStatus("01");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
			obj.setNotifierRepDate(yyymmdd);								
		}
		if(isAutoSave){
			obj.setStatus("00");
		}
		
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setNotifierName(ref.getNotifierName());
		obj.setNotifierTelArea(ref.getNotifierTelArea());
		obj.setNotifierTel(ref.getNotifierTel());
		obj.setNotifierTelExt(ref.getNotifierTelExt());
		obj.setNotifierEamil(ref.getNotifierEamil());
		
		if("Y".equals(ref.getIsSameNotifier())){
			obj.setEatersName(ref.getNotifierName());
			obj.setEatersTelArea(ref.getNotifierTelArea());
			obj.setEatersTel(ref.getNotifierTel());
			obj.setEatersTelExt(ref.getNotifierTelExt());
		}else{
			obj.setEatersName(ref.getEatersName());
			obj.setEatersTelArea(ref.getEatersTelArea());
			obj.setEatersTel(ref.getEatersTel());
			obj.setEatersTelExt(ref.getEatersTelExt());
		}
		obj.setEatersIdNum(ref.getEatersIdNum());
		obj.setEatersHight(ref.getEatersHight());
		obj.setEatersWeight(ref.getEatersWeight());
		obj.setEatersSex(ref.getEatersSex());
		obj.setEatersAge(ref.getEatersAge());
		obj.setNotifierArea(ref.getNotifierArea());
		obj.setNotifierZipCode(ref.getNotifierZipCode());
		obj.setAddress(ref.getAddress());
		obj.setDrugAllergies(ref.getDrugAllergies());
		obj.setDrugOther(ref.getDrugOther());
		obj.setFoodAllergies(ref.getFoodAllergies());
		obj.setFoodOther(ref.getFoodOther());
		obj.setIsReactionResult(ref.getIsReactionResult());
		obj.setInformedSources(ref.getInformedSources());
		obj.setModifier(ref.getUserID());
		obj.setModifyDate(yyymmdd);
		obj.setModifyTime(hhmmss);
		if(ref.getDiseaseHistory()!=null && ref.getDiseaseHistory().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getDiseaseHistory()){
				if(sb.toString().length() > 0){
					sb.append(",");
				}
				sb.append(rid);
			}
			obj.setDiseaseHistory(sb.toString());
		}else{
			obj.setDiseaseHistory("");
		}
		obj.setDiseaseOther(ref.getDiseaseOther());
		
		if(ref.getLifeHistory()!=null && ref.getLifeHistory().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getLifeHistory()){
				if(sb.toString().length() > 0){
					sb.append(",");
				}
				sb.append(rid);
			}
			obj.setLifeHistory(sb.toString());
		}else{
			obj.setLifeHistory("");
		}
		obj.setLifeOther(ref.getLifeOther());
		
		obj.setUnHealthIsYes(ref.getUnHealthIsYes());
		obj.setUnHealthBrief(ref.getUnHealthBrief());
		obj.setOccurDate(ref.getOccurDate());
		obj.setOccurredAfter(ref.getOccurredAfter());
		obj.setSymptom(ref.getSymptom());
		obj.setSeverity(ref.getSeverity());
		obj.setSymptomDuration(ref.getSymptomDuration());
		obj.setStopEatingReaction(ref.getStopEatingReaction());
		obj.setAgainEatingReaction(ref.getAgainEatingReaction());
		obj.setIsMedical(ref.getIsMedical());
		obj.setMedicalDate(ref.getMedicalDate());
		obj.setHospital(ref.getHospital());
		obj.setIsWhileMedicine(ref.getIsWhileMedicine());
		obj.setWestDrugName(ref.getWestDrugName());
		obj.setIsWhileCMedicine(ref.getIsWhileCMedicine());
		obj.setEastDrugName(ref.getEastDrugName());
		obj.setIsWhileOther(ref.getIsWhileOther());
		obj.setProductName(ref.getProductName());
		obj.setOtherExplainMemo(ref.getOtherExplainMemo());
		
		java.util.Set hfr4002Dbs = obj.getHfr4002Dbs();
		if(hfr4002Dbs == null){
			hfr4002Dbs = new ListOrderedSet();
		}
		java.util.List<Hfr4002Db> dList = new java.util.ArrayList<Hfr4002Db>();
		try{
			updateHfr4002Db(obj, hfr4002Dbs, dList, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			updateHfr4002Db(obj, hfr4002Dbs, dList, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if(dList.size() > 0){
			for(Hfr4002Db dtl : dList){
				dtl.getHfr4001Db().getHfr4002Dbs().remove(dtl);
				dtl.setHfr4001Db(null);
			}
			deleteBatch(dList);
			dList.clear();
		}
		obj.setHfr4002Dbs(hfr4002Dbs);
		saveOrUpdate(obj);
		
		try{
			updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR4001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[更新檔案異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if("doSend".equals(ref.getState())){
			updateDoCopyHfr0001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
		}
		
		// 寫入流程
		if(!isAutoSave){
			if("doSend".equals(ref.getState())){
				String id = View.getLookupField("select id from Hfr0001Db where hfr4001DbId = " + Common.getLong(obj.getId()));
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, Common.getLong(id), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
			}
		}
		
		ref.setId(Common.get(obj.getId()));
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}
	
	public void updateHfr4002Db(Hfr4001Db obj, java.util.Set hfr4002Dbs, java.util.List<Hfr4002Db> dList, String type, javax.servlet.ServletRequest httpRequest, 
								String[] HFR4002DbRow, String[] arrHFR4002DbFieldName, 
								String userId, String yyymmdd, String hhmmss) throws Exception{
		
		java.util.Map<String, Hfr4002Db> oHfr4002DbMap = new java.util.HashMap<String, Hfr4002Db>();
		if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
			for(Object dtlObj : obj.getHfr4002Dbs()){
				Hfr4002Db dtl = (Hfr4002Db)dtlObj;
				oHfr4002DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		
		if(httpRequest!=null && HFR4002DbRow!=null && HFR4002DbRow.length>0){
			if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						boolean flag = false;
						for(int i=0; i<HFR4002DbRow.length; i++){
							if(Common.get(dtl.getId()).equals(HFR4002DbRow[i])){
								flag = true;
							}
							if(flag){
								break;
							}
						}
						if(!flag){
							dList.add(dtl);
						}
					}
				}
			}
			
			for(int i=0; i<HFR4002DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR4002DbRow[i]);
				Hfr4002Db dtl = oHfr4002DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr4002Db();
					dtl.setHfr4001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setPermitKey(httpRequest.getParameter(arrHFR4002DbFieldName[0] + rid));
				dtl.setPermitNo(httpRequest.getParameter(arrHFR4002DbFieldName[1] + rid));
				dtl.setChProduct(httpRequest.getParameter(arrHFR4002DbFieldName[2] + rid));
				dtl.setEnProduct(httpRequest.getParameter(arrHFR4002DbFieldName[3] + rid));
				dtl.setBuySource(httpRequest.getParameter(arrHFR4002DbFieldName[4] + rid));
				dtl.setIngredient(httpRequest.getParameter(arrHFR4002DbFieldName[5] + rid));
				dtl.setDoseDay(httpRequest.getParameter(arrHFR4002DbFieldName[6] + rid));
				dtl.setDoseNum(httpRequest.getParameter(arrHFR4002DbFieldName[7] + rid));
				dtl.setFrequency(httpRequest.getParameter(arrHFR4002DbFieldName[8] + rid));
				dtl.setFrequencyUnit(httpRequest.getParameter(arrHFR4002DbFieldName[9] + rid));
				dtl.setEdibleDateS(httpRequest.getParameter(arrHFR4002DbFieldName[10] + rid));
				dtl.setEdibleDateE(httpRequest.getParameter(arrHFR4002DbFieldName[11] + rid));
				dtl.setType(type);
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				
				boolean r = false;
				if(isUpdate){
					hfr4002Dbs.add(dtl);
					update(dtl);
				}else{
					if(TCBWCommon.validateHFR4002Db(dtl)){
						hfr4002Dbs.add(dtl);
						save(dtl);
					}
				}
			}
		}else{
			if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						dList.add(dtl);
					}
				}
			}
		}
		
		oHfr4002DbMap.clear();
	}
	
	@Override
	public void deleteByHFREX0102F(HFREX0102F ref) throws Exception {
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			java.util.List<Con0001Db> con0001DbsList = load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR4001DB' and upLoadId = " + Common.getLong(obj.getId()));
			if(con0001DbsList!=null && con0001DbsList.size()>0){
				for(Con0001Db dtl : con0001DbsList){
					String fileID = Common.isoToBig5(Common.get(dtl.getFileRoute()));
					File dir = new File("./upload/HFR" + File.separator + fileID);
					try{
						Common.RemoveDirectory(dir);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				deleteBatch(con0001DbsList);
			}
			delete(obj);
			ref.setId("");
		}
	}

	@Override
	public void insertByHFREX0104F(HFREX0104F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[HfrexDaoImpl]-[外部-一般表-新增]-[無法辨別登入的使用者]");
		}
		
		Hfr4001Db obj = new Hfr4001Db();
		obj.setStatus("00");
		obj.setHfrType("2");
		obj.setNotifierName(c.getUserName());
		obj.setNotifierIdNum(Common.get(c.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(c.getPersonalId())));
		obj.setNotifierTelArea(c.getUserTelArea());
		obj.setNotifierTel(c.getUserTel());
		obj.setNotifierTelExt(c.getUserTelExt());
		obj.setNotifierEamil(c.getUserEmail());
		obj.setNotifierType(c.getCommonDepartment()!=null?c.getCommonDepartment().getShortCode():"");
		obj.setNotifierDeptId(c.getUserJob());
		obj.setNotifierDept(View.getNotifierDeptName(obj.getNotifierDeptId(), obj.getNotifierType()));
		obj.setNotifierTitle(c.getJobTitle());
		obj.setNotifierArea(c.getUserCounty());
		obj.setNotifierZipCode(c.getUserZipCode());
		obj.setAddress(c.getUserAddr());
		obj.setEatersName(c.getUserName());
		obj.setEatersTelArea(c.getUserTelArea());
		obj.setEatersTel(c.getUserTel());
		obj.setEatersTelExt(c.getUserTelExt());
		obj.setEatersIdNum(Common.get(c.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(c.getPersonalId())));
		obj.setCaseOwner(c.getUserId());
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		save(obj);
		ref.setId(Common.get(obj.getId()));
		
	//	ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "新增", c.getUserId());
	}

	@Override
	public void updateByHFREX0104F(HFREX0104F ref, boolean isAutoSave) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		String logType = "HFR01";
		if("pauseSave".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部一般表)-暫存";
			msg = "暫存完成";
		}else if("stayedUpload".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部一般表)-待上傳";
			msg = "待上傳完成";
		}else if("doSend".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(外部一般表)-送出";
			msg = "送出完成";
		}
		if(isAutoSave){
			logName = "食品非預期反應通報登錄作業(外部一般表)-自動儲存";
			msg = "自動儲存完成";
		}
		
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Hfr4001Db();
			obj.setNotifierType(ref.getNotifierType());
			obj.setHfrType("2");
			obj.setCaseOwner(ref.getUserID());
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[查無HFR4001_DB，新建一筆]");
		}
		
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("stayedUpload".equals(ref.getState())){
			obj.setStatus("01");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
			obj.setNotifierRepDate(yyymmdd);								
		}
		if(isAutoSave){
			obj.setStatus("00");
		}
		
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setNotifierName(ref.getNotifierName());
		obj.setNotifierTelArea(ref.getNotifierTelArea());
		obj.setNotifierTel(ref.getNotifierTel());
		obj.setNotifierTelExt(ref.getNotifierTelExt());
		obj.setNotifierIdNum(ref.getNotifierIdNum());
		obj.setNotifierType(ref.getNotifierType());
		obj.setNotifierDeptId(ref.getNotifierDeptID());
		obj.setNotifierDept(View.getNotifierDeptName(obj.getNotifierDeptId(), obj.getNotifierType()));
		obj.setNotifierEamil(ref.getNotifierEamil());
		obj.setNotifierTitle(ref.getNotifierTitle());
		obj.setNotifierArea(ref.getNotifierArea());
		obj.setNotifierZipCode(ref.getNotifierZipCode());
		obj.setAddress(ref.getAddress());
		
		obj.setEatersId(ref.getEatersId());
		if("Y".equals(ref.getIsSameNotifier())){
			obj.setEatersName(ref.getNotifierName());
			obj.setEatersIdNum(ref.getNotifierIdNum());
			obj.setEatersTelArea(ref.getNotifierTelArea());
			obj.setEatersTel(ref.getNotifierTel());
			obj.setEatersTelExt(ref.getNotifierTelExt());
		}else{
			obj.setEatersName(ref.getEatersName());
			obj.setEatersIdNum(ref.getEatersIdNum());
			obj.setEatersTelArea(ref.getEatersTelArea());
			obj.setEatersTel(ref.getEatersTel());
			obj.setEatersTelExt(ref.getEatersTelExt());
		}
		obj.setEatersSex(ref.getEatersSex());
		obj.setEatersBirthYear(ref.getEatersBirthYear());
		obj.setEatersHight(ref.getEatersHight());
		obj.setEatersWeight(ref.getEatersWeight());
		obj.setDrugAllergies(ref.getDrugAllergies());
		obj.setDrugOther(ref.getDrugOther());
		obj.setFoodAllergies(ref.getFoodAllergies());
		obj.setFoodOther(ref.getFoodOther());
		obj.setIsReactionResult(ref.getIsReactionResult());
		obj.setInformedSources(ref.getInformedSources());
		if(ref.getDiseaseHistory()!=null && ref.getDiseaseHistory().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getDiseaseHistory()){
				if(sb.toString().length() > 0){
					sb.append(",");
				}
				sb.append(rid);
			}
			obj.setDiseaseHistory(sb.toString());
		}else{
			obj.setDiseaseHistory("");
		}
		obj.setDiseaseOther(ref.getDiseaseOther());
		if(ref.getLifeHistory()!=null && ref.getLifeHistory().length>0){
			StringBuffer sb = new StringBuffer();
			for(String rid : ref.getLifeHistory()){
				if(sb.toString().length() > 0){
					sb.append(",");
				}
				sb.append(rid);
			}
			obj.setLifeHistory(sb.toString());
		}else{
			obj.setLifeHistory("");
		}
		obj.setLifeOther(ref.getLifeOther());
		
		obj.setUnHealthIsYes(ref.getUnHealthIsYes());
		obj.setUnHealthBrief(ref.getUnHealthBrief());
		obj.setUnReactionResults(ref.getUnReactionResults());
		obj.setDeathDate(ref.getDeathDate());
		obj.setDeathResult(ref.getDeathResult());
		obj.setEndangerLife(ref.getEndangerLife());
		obj.setOtherNonResponse(ref.getOtherNonResponse());
		if(ref.getOtherrElevantInformation()!=null && ref.getOtherrElevantInformation().length>0){
			StringBuffer s = new StringBuffer();
			for(String codeId : ref.getOtherrElevantInformation()){
				if(s.toString().length()>0)	s.append(",");
				s.append(codeId);
			}
			obj.setOtherrElevantInformation(s.toString());
		}else{
			obj.setOtherrElevantInformation("");
		}
		obj.setAllergicDescription(ref.getAllergicDescription());
		obj.setOthersDescribeHistory(ref.getOthersDescribeHistory());
		obj.setModifier(ref.getUserID());
		obj.setModifyDate(yyymmdd);
		obj.setModifyTime(hhmmss);
		
		// 通報事件描述
		java.util.Set hfr4003Dbs = obj.getHfr4003Dbs();
		if(hfr4003Dbs == null){
			hfr4003Dbs = new ListOrderedSet();
		}
		java.util.List<Hfr4003Db> d3List = new java.util.ArrayList<Hfr4003Db>();
		try{
			updateHfr4003Db(obj, hfr4003Dbs, d3List, ref.getHttpRequest(), ref.getDiscriptRow(), ref.discriptFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		if(d3List.size() > 0){
			for(Hfr4003Db dtl : d3List){
				dtl.getHfr4001Db().getHfr4003Dbs().remove(dtl);
				dtl.setHfr4001Db(null);
			}
			deleteBatch(d3List);
			d3List.clear();
		}
		
		// 醫師診斷及相關檢查及檢驗數據
		java.util.Set hfr4004Dbs = obj.getHfr4004Dbs();
		if(hfr4004Dbs == null){
			hfr4004Dbs = new ListOrderedSet();
		}
		java.util.List<Hfr4004Db> d4List = new java.util.ArrayList<Hfr4004Db>();
		try{
			updateHfr4004Db(obj, hfr4004Dbs, d4List, ref.getHttpRequest(), ref.getDoctorRow(), ref.doctorFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[醫師診斷及相關檢查及檢驗數據更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		if(d4List.size() > 0){
			for(Hfr4004Db dtl : d4List){
				dtl.getHfr4001Db().getHfr4004Dbs().remove(dtl);
				dtl.setHfr4001Db(null);
			}
			deleteBatch(d4List);
			d4List.clear();
		}
		
		// 食藥品資料
		obj.setAgainEatingHealthFood(ref.getAgainEatingHealthFood());
		obj.setHealthFoodName(ref.getHealthFoodName());
		obj.setAgainOtherNonResponse(ref.getAgainOtherNonResponse());
		obj.setStopEatingReaction(ref.getStopEatingReaction());
		obj.setAgainEatingReaction(ref.getAgainEatingReaction());
		
		java.util.Set hfr4002Dbs = obj.getHfr4002Dbs();
		if(hfr4002Dbs == null){
			hfr4002Dbs = new ListOrderedSet();
		}
		java.util.List<Hfr4002Db> d2List = new java.util.ArrayList<Hfr4002Db>();
		
		try{
			updateHfr4002Db(obj, hfr4002Dbs, d2List, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			updateHfr4002Db(obj, hfr4002Dbs, d2List, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			updateDrugHfr4002Db(obj, hfr4002Dbs, d2List, "D", ref.getHttpRequest(), ref.getDrRow(), ref.arrDrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[併用藥品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			updateDrugHfr4002Db(obj, hfr4002Dbs, d2List, "O", ref.getHttpRequest(), ref.getOdrRow(), ref.arrODrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[併用其它藥品，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if(d2List.size() > 0){
			for(Hfr4002Db dtl : d2List){
				dtl.getHfr4001Db().getHfr4002Dbs().remove(dtl);
				dtl.setHfr4001Db(null);
			}
			deleteBatch(d2List);
			d2List.clear();
		}
		obj.setHfr4002Dbs(hfr4002Dbs);
		obj.setHfr4003Dbs(hfr4003Dbs);
		obj.setHfr4004Dbs(hfr4004Dbs);
		saveOrUpdate(obj);
		
		try{
			updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR4001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrexDaoImpl]-[" + logName + "]-[更新檔案異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if("doSend".equals(ref.getState())){
			updateDoCopyHfr0001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
		}
		
		// 寫入流程
		if(!isAutoSave){
			if("doSend".equals(ref.getState())){
				String id = View.getLookupField("select id from Hfr0001Db where hfr4001DbId = " + Common.getLong(obj.getId()));
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db(logType, Common.getLong(id), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
			}
		}
		
		ref.setId(Common.get(obj.getId()));
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}
	
	@Override
	public void updateDrugHfr4002Db( Hfr4001Db obj, Set hfr4002Dbs,	List<Hfr4002Db> dList, String type, ServletRequest httpRequest,
									 String[] HFR4002DbRow, String[] arrHFR4002DbFieldName,
									 String userId, String yyymmdd, String hhmmss ) throws Exception {
		
		java.util.Map<String, Hfr4002Db> oHfr4002DbMap = new java.util.HashMap<String, Hfr4002Db>();
		if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
			for(Object dtlObj : obj.getHfr4002Dbs()){
				Hfr4002Db dtl = (Hfr4002Db)dtlObj;
				oHfr4002DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		
		if(httpRequest!=null && HFR4002DbRow!=null && HFR4002DbRow.length>0){
			if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						boolean flag = false;
						for(int i=0; i<HFR4002DbRow.length; i++){
							if(Common.get(dtl.getId()).equals(HFR4002DbRow[i])){
								flag = true;
							}
							if(flag){
								break;
							}
						}
						if(!flag){
							dList.add(dtl);
						}
					}
				}
			}
			
			for(int i=0; i<HFR4002DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR4002DbRow[i]);
				Hfr4002Db dtl = oHfr4002DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr4002Db();
					dtl.setHfr4001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setChProduct(httpRequest.getParameter(arrHFR4002DbFieldName[0] + rid));
				dtl.setEnProduct(httpRequest.getParameter(arrHFR4002DbFieldName[1] + rid));
				dtl.setContent(httpRequest.getParameter(arrHFR4002DbFieldName[2] + rid));
				dtl.setMedModel(httpRequest.getParameter(arrHFR4002DbFieldName[3] + rid));
				dtl.setDoseNum(httpRequest.getParameter(arrHFR4002DbFieldName[4] + rid));
				dtl.setDoseDay(httpRequest.getParameter(arrHFR4002DbFieldName[5] + rid));
				dtl.setFrequency(httpRequest.getParameter(arrHFR4002DbFieldName[6] + rid));
				dtl.setFrequencyUnit(httpRequest.getParameter(arrHFR4002DbFieldName[7] + rid));
				dtl.setEdibleDateS(httpRequest.getParameter(arrHFR4002DbFieldName[8] + rid));
				dtl.setEdibleDateE(httpRequest.getParameter(arrHFR4002DbFieldName[9] + rid));
				dtl.setBrand(httpRequest.getParameter(arrHFR4002DbFieldName[10] + rid));
				dtl.setPermitNo(httpRequest.getParameter(arrHFR4002DbFieldName[11] + rid));
				dtl.setType(type);
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				
				
				if(isUpdate){
					hfr4002Dbs.add(dtl);
					update(dtl);
				}else{
					if(TCBWCommon.validateDrugHFR4002Db(dtl)){
						hfr4002Dbs.add(dtl);
						save(dtl);
					}
				}
			}
		}else{
			if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						dList.add(dtl);
					}
				}
			}
		}
		
		oHfr4002DbMap.clear();
	}

	@Override
	public void updateHfr4003Db( Hfr4001Db obj, Set hfr4003Dbs, List<Hfr4003Db> dList, ServletRequest httpRequest, 
								 String[] HFR4003DbRow, String[] arrHFR4003DbFieldName,
								 String userId, String yyymmdd, String hhmmss ) throws Exception {
		
		java.util.Map<String, Hfr4003Db> oHfr4003DbMap = new java.util.HashMap<String, Hfr4003Db>();
		if(obj.getHfr4003Dbs()!=null && obj.getHfr4003Dbs().size()>0){
			for(Object dtlObj : obj.getHfr4003Dbs()){
				Hfr4003Db dtl = (Hfr4003Db)dtlObj;
				oHfr4003DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		if(httpRequest!=null && HFR4003DbRow!=null && HFR4003DbRow.length>0){
			if(obj.getHfr4003Dbs()!=null && obj.getHfr4003Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4003Dbs()){
					Hfr4003Db dtl = (Hfr4003Db)dtlObj;
					boolean flag = false;
					for(int i=0; i<HFR4003DbRow.length; i++){
						if(Common.get(dtl.getId()).equals(HFR4003DbRow[i])){
							flag = true;
						}
						if(flag){
							break;
						}
					}
					if(!flag){
						dList.add(dtl);
					}
				}
			}
			for(int i=0; i<HFR4003DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR4003DbRow[i]);
				Hfr4003Db dtl = oHfr4003DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr4003Db();
					dtl.setHfr4001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setDiscriptDate(httpRequest.getParameter(arrHFR4003DbFieldName[0]+ rid));
				dtl.setPosition(httpRequest.getParameter(arrHFR4003DbFieldName[1]+ rid));
				dtl.setSymptom(httpRequest.getParameter(arrHFR4003DbFieldName[2]+ rid));
				dtl.setSeverity(httpRequest.getParameter(arrHFR4003DbFieldName[3]+ rid));
				dtl.setDoResponse(httpRequest.getParameter(arrHFR4003DbFieldName[4]+ rid));
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				hfr4003Dbs.add(dtl);
				
				if (isUpdate) update(dtl);
				else save(dtl);
			}
		}else{
			if(obj.getHfr4003Dbs()!=null && obj.getHfr4003Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4003Dbs()){
					Hfr4003Db dtl = (Hfr4003Db)dtlObj;
					dList.add(dtl);
				}
			}
		}
		oHfr4003DbMap.clear();
	}
	
	@Override
	public void updateHfr4004Db( Hfr4001Db obj, Set hfr4004Dbs,	List<Hfr4004Db> dList, ServletRequest httpRequest,
								 String[] HFR4004DbRow, String[] arrHFR4004DbFieldName,
								 String userId, String yyymmdd, String hhmmss ) throws Exception {
		
		java.util.Map<String, Hfr4004Db> oHfr4004DbMap = new java.util.HashMap<String, Hfr4004Db>();
		if(obj.getHfr4004Dbs()!=null && obj.getHfr4004Dbs().size()>0){
			for(Object dtlObj : obj.getHfr4004Dbs()){
				Hfr4004Db dtl = (Hfr4004Db)dtlObj;
				oHfr4004DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		if(httpRequest!=null && HFR4004DbRow!=null && HFR4004DbRow.length>0){
			if(obj.getHfr4004Dbs()!=null && obj.getHfr4004Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4004Dbs()){
					Hfr4004Db dtl = (Hfr4004Db)dtlObj;
					boolean flag = false;
					for(int i=0; i<HFR4004DbRow.length; i++){
						if(Common.get(dtl.getId()).equals(HFR4004DbRow[i])){
							flag = true;
						}
						if(flag){
							break;
						}
					}
					if(!flag){
						dList.add(dtl);
					}
				}
			}
			for(int i=0; i<HFR4004DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR4004DbRow[i]);
				Hfr4004Db dtl = oHfr4004DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr4004Db();
					dtl.setHfr4001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setDoctorDate(httpRequest.getParameter(arrHFR4004DbFieldName[0]+ rid));
				dtl.setBloodIndex(httpRequest.getParameter(arrHFR4004DbFieldName[1]+ rid));
				dtl.setLiverIndex(httpRequest.getParameter(arrHFR4004DbFieldName[2]+ rid));
			//	dtl.setRenalIndex(httpRequest.getParameter(arrHFR4004DbFieldName[3]+ rid));
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				
				if(isUpdate){
					update(dtl);
				}else{
					if(TCBWCommon.validateHFR4004DB(dtl)){
						save(dtl);
					}
				}
			}
		}else{
			if(obj.getHfr4004Dbs()!=null && obj.getHfr4004Dbs().size()>0){
				for(Object dtlObj : obj.getHfr4004Dbs()){
					Hfr4004Db dtl = (Hfr4004Db)dtlObj;
					dList.add(dtl);
				}
			}
		}
		oHfr4004DbMap.clear();
	}
	
	@Override
	public void deleteByHFREX0104F(HFREX0104F ref) throws Exception {
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			java.util.List<Con0001Db> con0001DbsList = load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR4001DB' and upLoadId = " + Common.getLong(obj.getId()));
			if(con0001DbsList!=null && con0001DbsList.size()>0){
				for(Con0001Db dtl : con0001DbsList){
					String fileID = Common.isoToBig5(Common.get(dtl.getFileRoute()));
					File dir = new File("./upload/HFR" + File.separator + fileID);
					try{
						Common.RemoveDirectory(dir);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				deleteBatch(con0001DbsList);
			}
			delete(obj);			
			ref.setId("");
		}
	}

	public void updateCon0001Db(javax.servlet.ServletRequest httpRequest, Long uploadId, String dbName, String fileType, String fileStoreType, String[] fileRow, String[] fileField, String userId, String yyymmdd, String hhmmss)throws Exception{
		java.util.Map<String, Con0001Db> oldCon0001DbMap = new java.util.HashMap<String, Con0001Db>(); 
		java.util.List<Con0001Db> fileList = load(" from Con0001Db where fileKind = " + Common.sqlChar(fileType) +
												  " and dbName = " + Common.sqlChar(dbName) + 
												  " and upLoadId = " + Common.getLong(uploadId));
		if(fileList!=null && fileList.size()>0){
			for(Con0001Db dtl : fileList){
				oldCon0001DbMap.put(Common.get(dtl.getId()), dtl);
			}
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
		//	deleteBatch(fileList);
			fileList.clear();
		}
		
		java.util.List<Con0001Db> dList = new java.util.ArrayList<Con0001Db>();
		if(httpRequest!=null && fileRow!=null && fileRow.length>0){
			for(Map.Entry<String, Con0001Db> dtl : oldCon0001DbMap.entrySet()){
				boolean flag = false;
				for(String rid : fileRow){
					if(dtl.getKey().equals(rid)){
						flag = true;
					}
					if(flag){
						break;
					}
				}
				if(!flag){
					dList.add(dtl.getValue());
				}
			}
			for(String rid : fileRow){
				boolean isUpdate = true;
				Con0001Db con0001Db = oldCon0001DbMap.get(rid);
				if(con0001Db == null){
					con0001Db = new Con0001Db();
					con0001Db.setFileKind(fileType);
					con0001Db.setUpLoadId(uploadId);
					con0001Db.setDbName(dbName);
					con0001Db.setCreator(userId);
					con0001Db.setCreateDate(yyymmdd);
					con0001Db.setCreateTime(hhmmss);
					isUpdate = false;
				}
				con0001Db.setFileName(httpRequest.getParameter(fileField[0] + rid));
				con0001Db.setFileRoute(httpRequest.getParameter(fileField[1] + rid));
				con0001Db.setFileExplan(httpRequest.getParameter(fileField[2] + rid));
				con0001Db.setModifier(userId);
				con0001Db.setModifyDate(yyymmdd);
				con0001Db.setModifyTime(hhmmss);
				
				if(isUpdate){
					update(con0001Db);
				}else{
					if(TCBWCommon.validateCON0001DB(con0001Db)){
						save(con0001Db);
					}
				}
			}
		}else{
			for(Map.Entry<String, Con0001Db> dtl : oldCon0001DbMap.entrySet()){
				dList.add(dtl.getValue());
			}
		}
		
		deleteBatch(dList);
		oldCon0001DbMap.clear();
	}
	
	
	@Override
	public void updateSendApplNoByHFREX0201F(HFREX0201F ref) throws Exception {
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
				java.util.List objList = load(" from Hfr4001Db where id in (" + sb.toString() + ")");
				if(objList!=null && objList.size()>0){
					String yyymmdd = Datetime.getYYYMMDD();
					String hhmmss = Datetime.getHHMMSS();
					
					for(Object dtlObj : objList){
						Hfr4001Db dtl = (Hfr4001Db)dtlObj;
						if("01".equals(Common.get(dtl.getStatus()))){
							dtl.setNotifierRepDate(yyymmdd);
							dtl.setStatus("10");
							dtl.setModifier(ref.getUserID());
							dtl.setModifyDate(yyymmdd);
							dtl.setModifyTime(hhmmss);
							update(dtl);
							try{
								updateDoCopyHfr0001Db(dtl, ref.getUserID(), yyymmdd, hhmmss);
								
								String id = View.getLookupField("select id from Hfr0001Db where hfr4001DbId = " + Common.getLong(dtl.getId()));
								ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", Common.getLong(id), Common.get(dtl.getApplNo()), dtl.getStatus(), "案件送出", ref.getUserID());
							}catch(Exception e){
								logger.info("[TCBW]-[CosexDaoImpl]-[食品非預期反應-外部-待上傳作業]-[案件送出複製資料時發生錯誤，不更新資料，COS4001_DB.ID : " + dtl.getId() + "]");
								e.printStackTrace();
							}
						}else{
							logger.info("[TCBW]-[HfrexDaoImpl]-[食品非預期反應-外部-待上傳作業]-[案件狀態不為待上傳，不更新資料，HFR4001_DB.ID : " + dtl.getId() + "]");
						}
					}
				}else{
					logger.info("[TCBW]-[HfrexDaoImpl]-[食品非預期反應-外部-待上傳作業]-[查無勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
				}
			}
		}else{
			logger.info("[TCBW]-[HfrexDaoImpl]-[食品非預期反應-外部-待上傳作業]-[未有勾選案件，不更新資料]-[" + Datetime.getYYYMMDD() + " : " + Datetime.getHHMMSS() + "]");
		}
	}
	
	
	
	
	
}
