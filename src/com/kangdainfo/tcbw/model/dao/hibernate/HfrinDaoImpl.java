package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletRequest;
import org.apache.commons.collections.set.ListOrderedSet;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.Validate;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.report.TableModelReportEnvironment;
import com.kangdainfo.common.util.report.TableModelReportGenerator;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.model.bo.Hfr0005Db;
import com.kangdainfo.tcbw.model.bo.Hfr0006Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.model.dao.HfrinDao;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.hfrex.HFREX0104F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0402F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0403F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0502F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0503F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0602F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0603F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0702F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0802F;

public class HfrinDaoImpl extends BaseDaoImpl implements HfrinDao {
	
	@Override
	public void insertByHFRIN0402F(HFRIN0402F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[HfrinDaoImpl]-[內部-簡表-新增]-[無法辨別登入的使用者]");
		}
		
		Hfr4001Db obj = new Hfr4001Db();
		obj.setHfrType("1");
		obj.setStatus("00");
		obj.setNotifierType("01");
	/*	
		obj.setNotifierName(c.getUserName());
		obj.setNotifierType("01");								// 屬性
		obj.setNotifierTitle(c.getJobTitle());					// 職稱		
		obj.setNotifierDept(c.getUserJob());					// 服務機構
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
	*/	
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		save(obj);
		ref.setId(Common.get(obj.getId()));
	}

	@Override
	public void updateByHFRIN0402F(HFRIN0402F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部簡表)-暫存";
			msg = "暫存完成";
		}else if("stayedUpload".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部簡表)-待上傳";
			msg = "待上傳完成";
		}else if("doSend".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部簡表)-送出";
			msg = "送出完成";
		}
		
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Hfr4001Db();
			obj.setHfrType("1");
			obj.setNotifierType(ref.getNotifierType());
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[查無HFR4001_DB，新建一筆]");
		}
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("stayedUpload".equals(ref.getState())){
			obj.setStatus("01");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
			obj.setNotifierRepDate(yyymmdd);								
		}
		
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setCaseOwner(ref.getCaseOwner());
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4002Db(obj, hfr4002Dbs, dList, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4002Db(obj, hfr4002Dbs, dList, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR4001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if("doSend".equals(ref.getState())){
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateDoCopyHfr0001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
			
			// 寫入流程
			String id = View.getLookupField("select id from Hfr0001Db where hfr4001DbId = " + Common.getLong(obj.getId()));
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", Common.getLong(id), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
		}
		ref.setId(Common.get(obj.getId()));
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}
	
	@Override
	public void deleteByHFRIN0402F(HFRIN0402F ref) throws Exception {
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
	public void insertByHFRIN0403F(HFRIN0403F ref) throws Exception {
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(ref.getUserID()));
		if(c == null){
			c = new CommonUser();
			System.out.println("[TCBW]-[HfrinDaoImpl]-[內部-一般表-新增]-[無法辨別登入的使用者]");
		}
		
		Hfr4001Db obj = new Hfr4001Db();
		obj.setStatus("00");
		obj.setHfrType("2");
		/*
		obj.setNotifierName(c.getUserName());
		obj.setNotifierTel(c.getUserTel());
		obj.setNotifierEamil(c.getUserEmail());
		obj.setNotifierIdNum(Common.get(c.getPersonalId()).equals("")?"":TCBWCommon.getDecodeString(Common.get(c.getPersonalId())));
		obj.setNotifierType(c.getCommonDepartment()!=null?c.getCommonDepartment().getShortCode():"");
		obj.setAddress(c.getUserAddr());
		obj.setNotifierDept(c.getUserJob());
		obj.setNotifierTitle(c.getJobTitle());
		obj.setCaseOwner(Common.get(c.getUserId()));
		*/
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		save(obj);
		ref.setId(Common.get(obj.getId()));
	}

	@Override
	public void updateByHFRIN0403F(HFRIN0403F ref) throws Exception {
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		String msg = "";
		String logName = "";
		if("pauseSave".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部一般表)-暫存";
			msg = "暫存完成";
		}else if("stayedUpload".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部一般表)-待上傳";
			msg = "待上傳完成";
		}else if("doSend".equals(ref.getState())){
			logName = "食品非預期反應通報登錄作業(內部一般表)-送出";
			msg = "送出完成";
		}
		
		Hfr4001Db obj = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(ref.getId()));
		if(obj == null){
			obj = new Hfr4001Db();
			obj.setHfrType("2");
			obj.setNotifierType(ref.getNotifierType());
			obj.setCreator(ref.getUserID());
			obj.setCreateDate(yyymmdd);
			obj.setCreateTime(hhmmss);
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[查無HFR4001_DB，新建一筆]");
		}
		if("pauseSave".equals(ref.getState())){
			obj.setStatus("00");
		}else if("stayedUpload".equals(ref.getState())){
			obj.setStatus("01");
		}else if("doSend".equals(ref.getState())){
			obj.setStatus("10");
			obj.setNotifierRepDate(yyymmdd);								
		}
		
		obj.setOccurDate(ref.getOccurDate());
		obj.setNotifierRevDate(ref.getNotifierRevDate());
		obj.setCaseOwner(ref.getCaseOwner());
		obj.setNotifierName(ref.getNotifierName());
		obj.setNotifierTelArea(ref.getNotifierTelArea());
		obj.setNotifierTel(ref.getNotifierTel());
		obj.setNotifierTelExt(ref.getNotifierTelExt());
		obj.setNotifierIdNum(ref.getNotifierIdNum());
		obj.setNotifierDeptId(ref.getNotifierDeptID());
		obj.setNotifierDept(ref.getNotifierDept());
		obj.setNotifierArea(ref.getNotifierArea());
		obj.setNotifierZipCode(ref.getNotifierZipCode());
		obj.setAddress(ref.getAddress());
		obj.setNotifierEamil(ref.getNotifierEamil());
		obj.setNotifierType(ref.getNotifierType());
		obj.setNotifierTitle(ref.getNotifierTitle());
		
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4003Db(obj, hfr4003Dbs, d3List, ref.getHttpRequest(), ref.getDiscriptRow(), ref.discriptFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4004Db(obj, hfr4004Dbs, d4List, ref.getHttpRequest(), ref.getDoctorRow(), ref.doctorFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4002Db(obj, hfr4002Dbs, d2List, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateHfr4002Db(obj, hfr4002Dbs, d2List, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateDrugHfr4002Db(obj, hfr4002Dbs, d2List, "D", ref.getHttpRequest(), ref.getDrRow(), ref.arrDrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用藥品更新異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		try{
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateDrugHfr4002Db(obj, hfr4002Dbs, d2List, "O", ref.getHttpRequest(), ref.getOdrRow(), ref.arrODrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用其它藥品，HFR4001_DB.ID : " + obj.getId() + "]");
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
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR4001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR4001_DB.ID : " + obj.getId() + "]");
		}
		
		if("doSend".equals(ref.getState())){
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateDoCopyHfr0001Db(obj, ref.getUserID(), yyymmdd, hhmmss);
			
			// 寫入流程
			String id = View.getLookupField("select id from Hfr0001Db where hfr4001DbId = " + Common.getLong(obj.getId()));
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", Common.getLong(id), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
		}
		ref.setId(Common.get(obj.getId()));
		ref.setErrorMsg(msg);
		ref.setIsNeedBackQuery("Y");
	}
	
	@Override
	public void deleteByHFRIN0403F(HFRIN0403F ref) throws Exception {
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
	public void updateByHFRIN0502F(HFRIN0502F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			Hfr4001Db hfr4001 = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(obj.getHfr4001DbId()));
			
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			int updateCase = 0;
			String msg = "暫存完成";
			String logName = "食品審核作業-簡表-暫存動作";
			if("doAccept".equals(ref.getState())){
				logName = "食品審核作業-簡表-案件受理";
				msg = "受理成功";
				updateCase = 1;
			}else if("doUnAccept".equals(ref.getState())){
				logName = "食品審核作業-簡表-案件撤案";
				msg = "撤案成功";
				updateCase = 2;
			}else if("doAddition".equals(ref.getState())){
				logName = "食品審核作業-簡表-案件補件";
				msg = "案件異動為補件完成";
				updateCase = 3;
			}else if("doAdditionComplete".equals(ref.getState())){
				logName = "食品審核作業-簡表-補件完成";
				msg = "補件完成";
				updateCase = 4;
			}
			
			obj.setNotifierRevDate(ref.getNotifierRevDate());
	//		obj.setNotifierName(ref.getNotifierName());					基本資料不能異動	
	//		if("Y".equals(ref.getIsSameEaters())){
	//			obj.setNotifierName(ref.getEatersName());
	//		}
	//		obj.setNotifierTel(ref.getNotifierTel());
	//		obj.setNotifierEamil(ref.getNotifierEamil());
	//		obj.setEatersName(ref.getEatersName());
	//		obj.setEatersIdNum(ref.getEatersIdNum());
	//		obj.setEatersHight(ref.getEatersHight());
	//		obj.setEatersWeight(ref.getEatersWeight());
	//		obj.setEatersSex(ref.getEatersSex());
	//		obj.setEatersAge(ref.getEatersAge());
	//		obj.setEatersTel(ref.getEatersTel());
	//		obj.setAddress(ref.getAddress());
			
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
			
			java.util.Set hfr0002Dbs = obj.getHfr0002Dbs();
			if(hfr0002Dbs == null){
				hfr0002Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0002Db> dList = new java.util.ArrayList<Hfr0002Db>();
			try{
				updateHfr0002Db(obj, hfr0002Dbs, dList, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateHfr0002Db(obj, hfr0002Dbs, dList, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(dList.size() > 0){
				for(Hfr0002Db dtl : dList){
					dtl.getHfr0001Db().getHfr0002Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(dList);
				dList.clear();
			}
			obj.setHfr0002Dbs(hfr0002Dbs);
			
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			
			boolean isNeedSendMail = false;
			switch(updateCase){
			case 0:
			//	obj.setStatus("10");
				obj.setAssignMan(ref.getUserID());
				break;
			case 1:
				obj.setStatus("20");
				obj.setAssignMan("");
				obj.setEnrolledDate(yyymmdd);
				obj.setApplNo(TCBWCommon.getApplNo("HFR01", checkIsF01OrF02(obj), Datetime.getYYY()));
				isNeedSendMail = true;
				break;
			case 2:
				obj.setStatus("02");
				obj.setAssignMan("");
			//	isNeedSendMail = true;			-- 103.06.20 取消後端發送信件，改由前端
				break;
			case 3:
				obj.setStatus("03");
				obj.setAssignMan(ref.getUserID());
				obj.setAdditionalDate(yyymmdd);
				break;
			case 4:
				obj.setStatus("10");
				obj.setAssignMan(ref.getUserID());
				obj.setAdditionalFinshDate(yyymmdd);
				break;
			}
			update(obj);
			
			//撤案時產生一PDF附件置於相關附件頁籤-簡表
			if("02".equals(obj.getStatus())) {
				closedPrint(Common.get(hfr4001.getId()),Common.get(obj.getId()),ref.getUserID(),obj.getHfrType(),obj.getStatus());
			}
			// 寫入流程
			if(!"pauseSave".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
			}
			
			// 發送信件
			if(isNeedSendMail){
				generateHFRCaseSendEmail(obj, logName);
			}
			
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該案件資料，無法作動，請重新操作");
			ref.setIsNeedBackQuery("Y");
		}
	}
	
	public String checkIsF01OrF02(Hfr0001Db obj){
		String r = "F02";
		if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
			for(Object dtlObj : obj.getHfr0002Dbs()){
				Hfr0002Db dtl = (Hfr0002Db)dtlObj;
				if("G".equals(Common.get(dtl.getType()))){
					r = "F01";
				}
				if("F01".equals(r)){
					break;
				}
			}
		}
		return r;
	}

	@Override
	public void updateHfr0002Db( Hfr0001Db obj, Set hfr0002Dbs, List<Hfr0002Db> dList, String type, ServletRequest httpRequest,
								 String[] HFR0002DbRow, String[] arrHFR0002DbFieldName,
								 String userId, String yyymmdd, String hhmmss) throws Exception {
		
		java.util.Map<String, Hfr0002Db> oHfr0002DbMap = new java.util.HashMap<String, Hfr0002Db>();
		if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
			for(Object dtlObj : obj.getHfr0002Dbs()){
				Hfr0002Db dtl = (Hfr0002Db)dtlObj;
				oHfr0002DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		
		if(httpRequest!=null && HFR0002DbRow!=null && HFR0002DbRow.length>0){
			if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0002Dbs()){
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						boolean flag = false;
						for(int i=0; i<HFR0002DbRow.length; i++){
							if(Common.get(dtl.getId()).equals(HFR0002DbRow[i])){
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
			
			for(int i=0; i<HFR0002DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR0002DbRow[i]);
				Hfr0002Db dtl = oHfr0002DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr0002Db();
					dtl.setHfr0001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setPermitKey(httpRequest.getParameter(arrHFR0002DbFieldName[0] + rid));
				dtl.setPermitNo(httpRequest.getParameter(arrHFR0002DbFieldName[1] + rid));
				dtl.setChProduct(httpRequest.getParameter(arrHFR0002DbFieldName[2] + rid));
				dtl.setEnProduct(httpRequest.getParameter(arrHFR0002DbFieldName[3] + rid));
				dtl.setBuySource(httpRequest.getParameter(arrHFR0002DbFieldName[4] + rid));
				dtl.setIngredient(httpRequest.getParameter(arrHFR0002DbFieldName[5] + rid));
				dtl.setDoseDay(httpRequest.getParameter(arrHFR0002DbFieldName[6] + rid));
				dtl.setDoseNum(httpRequest.getParameter(arrHFR0002DbFieldName[7] + rid));
				dtl.setFrequency(httpRequest.getParameter(arrHFR0002DbFieldName[8] + rid));
				dtl.setFrequencyUnit(httpRequest.getParameter(arrHFR0002DbFieldName[9] + rid));
				dtl.setEdibleDateS(httpRequest.getParameter(arrHFR0002DbFieldName[10] + rid));
				dtl.setEdibleDateE(httpRequest.getParameter(arrHFR0002DbFieldName[11] + rid));
				dtl.setType(type);
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				hfr0002Dbs.add(dtl);
				
				if (isUpdate) update(dtl);
				else save(dtl);
			}
		}else{
			if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0002Dbs()){
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						dList.add(dtl);
					}
				}
			}
		}

		oHfr0002DbMap.clear();		
	}
	
	public void generateHFRCaseSendEmail(Hfr0001Db obj, String logName) throws Exception {
		if(!"".equals(Common.get(obj.getNotifierEamil())) && Validate.checkEmail(Common.get(obj.getNotifierEamil()))){
			java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
			mailList.add(new javax.mail.internet.InternetAddress(Common.get(obj.getNotifierEamil())));
			
			boolean isSend = false;
			String mailTitle = "";
			StringBuffer mailBody = new StringBuffer();
			if("20".equals(obj.getStatus())){
				isSend = true;
				String[] mailRef =  TCBWCommon.getCon1001DbMail("HFR", "HFR01", "HFR010001");
				if(mailRef!=null && mailRef.length==2){
					mailTitle = Common.get(mailRef[0]);
					mailBody.append(Common.get(mailRef[1]));
				}else{
					mailTitle = "案件通報受理通知";					
					mailBody.append(obj.getApplNo()).append("已受理");
				}
			}else if("02".equals(obj.getStatus())){
			//	-- 103.06.20 取消後端發送信件，改由前端
			//	isSend = true;
			//	String[] mailRef =  TCBWCommon.getCon1001DbMail("HFR", "HFR01", "HFR010002");
			//	if(mailRef!=null && mailRef.length==2){
			//		mailTitle = Common.get(mailRef[0]);
			//		mailBody.append(Common.get(mailRef[1]));
			//	}else{
			//		mailTitle = "案件通報退件通知";					
			//		mailBody.append(obj.getApplNo()).append("已退件");
			//	}
			}
			if(isSend){
				try{
					ServiceGetter.getInstance().getTcbwService().sendEmail( mailTitle, mailBody.toString(), mailList, null, true, null, null, null, 
																		    "HFR", Common.get(obj.getApplNo()).equals("")?Common.get(obj.getId()):Common.get(obj.getApplNo()));
				}catch(Exception e){
					e.printStackTrace();
					logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[發送EMAIL :" + obj.getNotifierEamil() + "，發送失敗]");
				}
			}else{
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[案件狀態不為可發信的狀態 :" + obj.getStatus() + "，HFR0001_DB.ID : " + obj.getId() + "，不發送]");
			}
		}else{
			logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[發送EMAIL :" + obj.getNotifierEamil() + "，發送失敗，EMAIL不正確]");
		}	
	}
	
	public void updateHFR4001DbStatus(Hfr0001Db obj) throws Exception {
		Hfr4001Db hfr4001Db = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(obj.getHfr4001DbId()));
		if(hfr4001Db != null){
			hfr4001Db.setApplNo(obj.getApplNo());
			hfr4001Db.setStatus(obj.getStatus());
			hfr4001Db.setModifier(obj.getModifier());
			hfr4001Db.setModifyDate(obj.getModifyDate());
			hfr4001Db.setModifyTime(obj.getModifyTime());
			update(hfr4001Db);
		}
	}
	

	@Override
	public void updateByHFRIN0503F(HFRIN0503F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			Hfr4001Db hfr4001 = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(obj.getHfr4001DbId()));
			
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			int updateCase = 0;
			String msg = "暫存完成";
			String logName = "食品審核作業-一般表-暫存動作";
			if("doAccept".equals(ref.getState())){
				logName = "食品審核作業-一般表-案件受理";
				msg = "受理成功";
				updateCase = 1;
			}else if("doUnAccept".equals(ref.getState())){
				logName = "食品審核作業-一般表-案件撤案";
				msg = "撤案成功";
				updateCase = 2;
			}else if("doAddition".equals(ref.getState())){
				logName = "食品審核作業-一般表-案件補件";
				msg = "案件異動為補件完成";
				updateCase = 3;
				
			}else if("doAdditionComplete".equals(ref.getState())){
				logName = "食品審核作業-一般表-補件完成";
				msg = "補件完成";
				updateCase = 4;
			}
			
			obj.setOccurDate(ref.getOccurDate());
			obj.setNotifierRevDate(ref.getNotifierRevDate());
		/*	
		 * 相關通報者資料，不能異動
			obj.setNotifierName(ref.getNotifierName());
			obj.setNotifierTel(ref.getNotifierTel());
			obj.setNotifierIdNum(ref.getNotifierIdNum());
			obj.setNotifierDept(ref.getNotifierDept());
			obj.setAddress(ref.getAddress());
			obj.setNotifierEamil(ref.getNotifierEamil());
			obj.setNotifierType(ref.getNotifierType());
			obj.setNotifierTitle(ref.getNotifierTitle());
			
			obj.setEatersId(ref.getEatersId());
			if("Y".equals(ref.getIsSameNotifier())){
				obj.setEatersName(ref.getNotifierName());
				obj.setEatersIdNum(ref.getNotifierIdNum());
				obj.setEatersTel(ref.getNotifierTel());
			}else{
				obj.setEatersName(ref.getEatersName());
				obj.setEatersIdNum(ref.getEatersIdNum());
				obj.setEatersTel(ref.getEatersTel());
			}
			obj.setEatersSex(ref.getEatersSex());
			obj.setEatersBirthYear(ref.getEatersBirthYear());
			obj.setEatersHight(ref.getEatersHight());
			obj.setEatersWeight(ref.getEatersWeight());
			obj.setAddress(ref.getAddress());
		*/	
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
			java.util.Set hfr0003Dbs = obj.getHfr0003Dbs();
			if(hfr0003Dbs == null){
				hfr0003Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0003Db> d3List = new java.util.ArrayList<Hfr0003Db>();
			try{
				updateHfr0003Db(obj, hfr0003Dbs, d3List, ref.getHttpRequest(), ref.getDiscriptRow(), ref.discriptFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(d3List.size() > 0){
				for(Hfr0003Db dtl : d3List){
					dtl.getHfr0001Db().getHfr0003Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(d3List);
				d3List.clear();
			}
			
			// 醫師診斷及相關檢查及檢驗數據
			java.util.Set hfr0004Dbs = obj.getHfr0004Dbs();
			if(hfr0004Dbs == null){
				hfr0004Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0004Db> d4List = new java.util.ArrayList<Hfr0004Db>();
			try{
				updateHfr0004Db(obj, hfr0004Dbs, d4List, ref.getHttpRequest(), ref.getDoctorRow(), ref.doctorFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(d4List.size() > 0){
				for(Hfr0004Db dtl : d4List){
					dtl.getHfr0001Db().getHfr0004Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
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
			
			java.util.Set hfr0002Dbs = obj.getHfr0002Dbs();
			if(hfr0002Dbs == null){
				hfr0002Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0002Db> d2List = new java.util.ArrayList<Hfr0002Db>();
			
			try{
				updateHfr0002Db(obj, hfr0002Dbs, d2List, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateHfr0002Db(obj, hfr0002Dbs, d2List, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "D", ref.getHttpRequest(), ref.getDrRow(), ref.arrDrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用藥品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "O", ref.getHttpRequest(), ref.getOdrRow(), ref.arrODrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用其它藥品，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			
			if(d2List.size() > 0){
				for(Hfr0002Db dtl : d2List){
					dtl.getHfr0001Db().getHfr0002Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(d2List);
				d2List.clear();
			}
			
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			
			boolean isNeedSendMail = false;
			switch(updateCase){
			case 0:
			//	obj.setStatus("10");
				obj.setAssignMan(ref.getUserID());
				break;
			case 1:
				obj.setStatus("20");
				obj.setAssignMan("");
				obj.setEnrolledDate(yyymmdd);
				obj.setApplNo(TCBWCommon.getApplNo("HFR01", checkIsF01OrF02(obj), Datetime.getYYY()));
				isNeedSendMail = true;
				break;
			case 2:
				obj.setStatus("02");
				obj.setAssignMan("");
			//	isNeedSendMail = true;			-- 103.06.20 取消後端發送信件，改由前端
				break;
			case 3:
				obj.setStatus("03");
				obj.setAssignMan(ref.getUserID());
				obj.setAdditionalDate(yyymmdd);
				break;
			case 4:
				obj.setStatus("10");
				obj.setAssignMan(ref.getUserID());
				obj.setAdditionalFinshDate(yyymmdd);
				break;
			}
			obj.setHfr0002Dbs(hfr0002Dbs);
			obj.setHfr0003Dbs(hfr0003Dbs);
			obj.setHfr0004Dbs(hfr0004Dbs);
			update(obj);
			
			//撤案時產生一PDF附件置於相關附件頁籤-一般表
			if("02".equals(obj.getStatus())) {
				closedPrint(Common.get(hfr4001.getId()),Common.get(obj.getId()),ref.getUserID(),obj.getHfrType(),obj.getStatus());
			}
			
			// 寫入流程
			if(!"pauseSave".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), msg, ref.getUserID());
			}
			
			// 發送信件
			if(isNeedSendMail){
				generateHFRCaseSendEmail(obj, logName);
			}
			
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該案件，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
	}
	
	@Override
	public void updateDrugHfr0002Db( Hfr0001Db obj, Set hfr0002Dbs, List<Hfr0002Db> dList, String type, ServletRequest httpRequest,
									 String[] HFR0002DbRow, String[] arrHFR0002DbFieldName,
									 String userId, String yyymmdd, String hhmmss) throws Exception {
		
		java.util.Map<String, Hfr0002Db> oHfr0002DbMap = new java.util.HashMap<String, Hfr0002Db>();
		if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
			for(Object dtlObj : obj.getHfr0002Dbs()){
				Hfr0002Db dtl = (Hfr0002Db)dtlObj;
				oHfr0002DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		
		if(httpRequest!=null && HFR0002DbRow!=null && HFR0002DbRow.length>0){
			if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0002Dbs()){
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						boolean flag = false;
						for(int i=0; i<HFR0002DbRow.length; i++){
							if(Common.get(dtl.getId()).equals(HFR0002DbRow[i])){
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
			
			for(int i=0; i<HFR0002DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR0002DbRow[i]);
				Hfr0002Db dtl = oHfr0002DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr0002Db();
					dtl.setHfr0001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setChProduct(httpRequest.getParameter(arrHFR0002DbFieldName[0] + rid));
				dtl.setEnProduct(httpRequest.getParameter(arrHFR0002DbFieldName[1] + rid));
				dtl.setContent(httpRequest.getParameter(arrHFR0002DbFieldName[2] + rid));
				dtl.setMedModel(httpRequest.getParameter(arrHFR0002DbFieldName[3] + rid));
				dtl.setDoseNum(httpRequest.getParameter(arrHFR0002DbFieldName[4] + rid));
				dtl.setDoseDay(httpRequest.getParameter(arrHFR0002DbFieldName[5] + rid));
				dtl.setFrequency(httpRequest.getParameter(arrHFR0002DbFieldName[6] + rid));
				dtl.setFrequencyUnit(httpRequest.getParameter(arrHFR0002DbFieldName[7] + rid));
				dtl.setEdibleDateS(httpRequest.getParameter(arrHFR0002DbFieldName[8] + rid));
				dtl.setEdibleDateE(httpRequest.getParameter(arrHFR0002DbFieldName[9] + rid));
				dtl.setBrand(httpRequest.getParameter(arrHFR0002DbFieldName[10] + rid));
				dtl.setPermitNo(httpRequest.getParameter(arrHFR0002DbFieldName[11] + rid));
				dtl.setType(type);
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				hfr0002Dbs.add(dtl);
				
				if (isUpdate) update(dtl);
				else save(dtl);
			}
		}else{
			if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0002Dbs()){
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					if(Common.get(type).equals(Common.get(dtl.getType()))){
						dList.add(dtl);
					}
				}
			}
		}
		
		oHfr0002DbMap.clear();
		
	}
	
	@Override
	public void updateHfr0003Db( Hfr0001Db obj, Set hfr0003Dbs, List<Hfr0003Db> dList, ServletRequest httpRequest,
								 String[] HFR0003DbRow, String[] arrHFR0003DbFieldName,
								 String userId, String yyymmdd, String hhmmss) throws Exception {
		
		java.util.Map<String, Hfr0003Db> oHfr0003DbMap = new java.util.HashMap<String, Hfr0003Db>();
		if(obj.getHfr0003Dbs()!=null && obj.getHfr0003Dbs().size()>0){
			for(Object dtlObj : obj.getHfr0003Dbs()){
				Hfr0003Db dtl = (Hfr0003Db)dtlObj;
				oHfr0003DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		if(httpRequest!=null && HFR0003DbRow!=null && HFR0003DbRow.length>0){
			if(obj.getHfr0003Dbs()!=null && obj.getHfr0003Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0003Dbs()){
					Hfr0003Db dtl = (Hfr0003Db)dtlObj;
					boolean flag = false;
					for(int i=0; i<HFR0003DbRow.length; i++){
						if(Common.get(dtl.getId()).equals(HFR0003DbRow[i])){
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
			for(int i=0; i<HFR0003DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR0003DbRow[i]);
				Hfr0003Db dtl = oHfr0003DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr0003Db();
					dtl.setHfr0001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setDiscriptDate(httpRequest.getParameter(arrHFR0003DbFieldName[0]+ rid));
				dtl.setPosition(httpRequest.getParameter(arrHFR0003DbFieldName[1]+ rid));
				dtl.setSymptom(httpRequest.getParameter(arrHFR0003DbFieldName[2]+ rid));
				dtl.setSeverity(httpRequest.getParameter(arrHFR0003DbFieldName[3]+ rid));
				dtl.setDoResponse(httpRequest.getParameter(arrHFR0003DbFieldName[4]+ rid));
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				hfr0003Dbs.add(dtl);
				
				if (isUpdate) update(dtl);
				else save(dtl);
			}
		}else{
			if(obj.getHfr0003Dbs()!=null && obj.getHfr0003Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0003Dbs()){
					Hfr0003Db dtl = (Hfr0003Db)dtlObj;
					dList.add(dtl);
				}
			}
		}
		oHfr0003DbMap.clear();
		
	}

	@Override
	public void updateHfr0004Db( Hfr0001Db obj, Set hfr0004Dbs, List<Hfr0004Db> dList, ServletRequest httpRequest,
								 String[] HFR0004DbRow, String[] arrHFR0004DbFieldName,
								 String userId, String yyymmdd, String hhmmss) throws Exception {
		java.util.Map<String, Hfr0004Db> oHfr0004DbMap = new java.util.HashMap<String, Hfr0004Db>();
		if(obj.getHfr0004Dbs()!=null && obj.getHfr0004Dbs().size()>0){
			for(Object dtlObj : obj.getHfr0004Dbs()){
				Hfr0004Db dtl = (Hfr0004Db)dtlObj;
				oHfr0004DbMap.put(Common.get(dtl.getId()), dtl);
			}
		}
		if(httpRequest!=null && HFR0004DbRow!=null && HFR0004DbRow.length>0){
			if(obj.getHfr0004Dbs()!=null && obj.getHfr0004Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0004Dbs()){
					Hfr0004Db dtl = (Hfr0004Db)dtlObj;
					boolean flag = false;
					for(int i=0; i<HFR0004DbRow.length; i++){
						if(Common.get(dtl.getId()).equals(HFR0004DbRow[i])){
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
			for(int i=0; i<HFR0004DbRow.length; i++){
				boolean isUpdate = true;
				String rid = Common.get(HFR0004DbRow[i]);
				Hfr0004Db dtl = oHfr0004DbMap.get(rid);
				if(dtl == null){
					dtl = new Hfr0004Db();
					dtl.setHfr0001Db(obj);
					dtl.setCreator(userId);
					dtl.setCreateDate(yyymmdd);
					dtl.setCreateTime(hhmmss);
					isUpdate = false;
				}
				dtl.setDoctorDate(httpRequest.getParameter(arrHFR0004DbFieldName[0]+ rid));
				dtl.setBloodIndex(httpRequest.getParameter(arrHFR0004DbFieldName[1]+ rid));
				dtl.setLiverIndex(httpRequest.getParameter(arrHFR0004DbFieldName[2]+ rid));
				dtl.setRenalIndex(httpRequest.getParameter(arrHFR0004DbFieldName[3]+ rid));
				dtl.setModifier(userId);
				dtl.setModifyDate(yyymmdd);
				dtl.setModifyTime(hhmmss);
				hfr0004Dbs.add(dtl);
				
				if (isUpdate) update(dtl);
				else save(dtl);
			}
		}else{
			if(obj.getHfr0004Dbs()!=null && obj.getHfr0004Dbs().size()>0){
				for(Object dtlObj : obj.getHfr0004Dbs()){
					Hfr0004Db dtl = (Hfr0004Db)dtlObj;
					dList.add(dtl);
				}
			}
		}
		oHfr0004DbMap.clear();
		
	}

	@Override
	public void updateByHFRIN0602F(HFRIN0602F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
		
			String msg = "暫存完成";
			String logName = "初評作業-暫存";
			if("pauseSave".equals(ref.getState())){
				
			}else if("doComplete".equals(ref.getState())){
				msg = "評估完成";
				logName = "初評作業-評估完成";
			}
			
			obj.setNotifierRevDate(ref.getNotifierRevDate());
			//		obj.setNotifierName(ref.getNotifierName());					基本資料不能異動	
			//		if("Y".equals(ref.getIsSameEaters())){
			//			obj.setNotifierName(ref.getEatersName());
			//		}
			//		obj.setNotifierTel(ref.getNotifierTel());
			//		obj.setNotifierEamil(ref.getNotifierEamil());
			//		obj.setEatersName(ref.getEatersName());
			//		obj.setEatersIdNum(ref.getEatersIdNum());
			//		obj.setEatersHight(ref.getEatersHight());
			//		obj.setEatersWeight(ref.getEatersWeight());
			//		obj.setEatersSex(ref.getEatersSex());
			//		obj.setEatersAge(ref.getEatersAge());
			//		obj.setEatersTel(ref.getEatersTel());
			//		obj.setAddress(ref.getAddress());
			
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
			
			java.util.Set hfr0002Dbs = obj.getHfr0002Dbs();
			if(hfr0002Dbs == null){
				hfr0002Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0002Db> dList = new java.util.ArrayList<Hfr0002Db>();
			try{
				updateHfr0002Db(obj, hfr0002Dbs, dList, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateHfr0002Db(obj, hfr0002Dbs, dList, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(dList.size() > 0){
				for(Hfr0002Db dtl : dList){
					dtl.getHfr0001Db().getHfr0002Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(dList);
				dList.clear();
			}
			obj.setHfr0002Dbs(hfr0002Dbs);
					
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}		
			
			// 檔案處理- FOR 調查頁籤-病歷資訊、檢驗資料
			obj.setMedicalPostingDate(ref.getMedicalPostingDate());
			obj.setMedicalCompleteDate(ref.getMedicalCompleteDate());
			obj.setInspectPostingDate(ref.getInspectPostingDate());
			obj.setInspectCompleteDate(ref.getInspectCompleteDate());
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFM", "HFR", ref.getHFRFMFileRow(), ref.arrHFRFMFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFI", "HFR", ref.getHFRFIFileRow(), ref.arrHFRFIFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			
			// HFR0005_DB處理
			java.util.Set hfr0005Dbs = obj.getHfr0005Dbs();
			if(hfr0005Dbs == null){
				hfr0005Dbs = new ListOrderedSet();
				obj.setHfr0005Dbs(hfr0005Dbs);
			}
			java.util.Map<String, Hfr0005Db> oldHfr0005DbMap = new java.util.HashMap<String, Hfr0005Db>(); 
			for(Object dtlObj : hfr0005Dbs){
				Hfr0005Db dtl = (Hfr0005Db)dtlObj;
				oldHfr0005DbMap.put(Common.get(dtl.getId()), dtl);
			}
			
			boolean isUpdate = true;
			Hfr0005Db hfr0005Db = oldHfr0005DbMap.get(ref.getHfr0005Id());
			if(hfr0005Db == null){
				hfr0005Db = new Hfr0005Db();
				hfr0005Db.setHfr0001Db(obj);
				hfr0005Db.setCreateDate(ref.getUserID());
				hfr0005Db.setCreateDate(yyymmdd);
				hfr0005Db.setCreateTime(hhmmss);
				
				hfr0005Dbs.add(hfr0005Db);
				isUpdate = false;
			}
			hfr0005Db.setPreCompleteDate(ref.getPreCompleteDate());
			hfr0005Db.setAssessmentSendDate(ref.getAssessmentSendDate());
			hfr0005Db.setAssessmentCompleteDate(ref.getAssessmentCompleteDate());
			hfr0005Db.setCaseType(ref.getCaseType());
			hfr0005Db.setUnExpectedClassify(ref.getUnExpectedClassify());
			if(ref.getUnExpectedReason()!=null && ref.getUnExpectedReason().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : ref.getUnExpectedReason()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				hfr0005Db.setUnExpectedReason(sb.toString());
			}else{
				hfr0005Db.setUnExpectedReason("");
			}
			
			hfr0005Db.setEvidentiary(ref.getEvidentiary());
			hfr0005Db.setPreSeverity(ref.getPreSeverity());
			hfr0005Db.setAdministrativeLevel(ref.getAdministrativeLevel());
			hfr0005Db.setPreOpinion(ref.getPreOpinion());
			hfr0005Db.setPreRemark(ref.getPreRemark());
			hfr0005Db.setPreExplain(ref.getPreExplain());
			hfr0005Db.setUnPreExplain(ref.getUnPreExplain());
			hfr0005Db.setModifier(ref.getUserID());
			hfr0005Db.setModifyDate(yyymmdd);
			hfr0005Db.setModifyTime(hhmmss);
			if("doComplete".equals(ref.getState())){
				hfr0005Db.setIsClosed("Y");
			}
			if(isUpdate){
				update(hfr0005Db);
			}else{
				save(hfr0005Db);
			}
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), hfr0005Db.getId(), "HFR0005DB", "HFRFO", "HFR", ref.getHFRFOFileRow(), ref.arrHFRFOFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			oldHfr0005DbMap.clear();
			
			// 更新HFR0001_DB
			if("pauseSave".equals(ref.getState())){
				obj.setAssignMan(ref.getUserID());
				obj.setStatus("20");
			}else if("doComplete".equals(ref.getState())){
				obj.setAssignMan("");
				
				if("01".equals(Common.get(hfr0005Db.getCaseType()))){					// 結案
					obj.setStatus("90");
				}else if("02".equals(Common.get(hfr0005Db.getCaseType()))){				// 報告案
					obj.setStatus("40");
				}else{																	// 評估案
					obj.setStatus("30");
				}
			}
			obj.setUnExpectedClassify(hfr0005Db.getUnExpectedClassify());
			obj.setUnExpectedReason(hfr0005Db.getUnExpectedReason());
			obj.setEvidentiary(hfr0005Db.getEvidentiary());
			obj.setRecentlySeverity(hfr0005Db.getPreSeverity());
			obj.setAdministrativeLevel(hfr0005Db.getAdministrativeLevel());
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			//結案後產生檔案
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getHfrType(),obj.getStatus());
			}
			
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			// 寫入流程
			if("doComplete".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "初評評估完成", ref.getUserID());
			}
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
	}
	
	@Override
	public void updateByHFRIN0603F(HFRIN0603F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
		
			String msg = "暫存完成";
			String logName = "初評作業-暫存";
			if("pauseSave".equals(ref.getState())){
				
			}else if("doComplete".equals(ref.getState())){
				msg = "評估完成";
				logName = "初評作業-評估完成";
			}
			
			obj.setOccurDate(ref.getOccurDate());
			obj.setNotifierRevDate(ref.getNotifierRevDate());
		/*	
		 * 相關通報者資料，不能異動
			obj.setNotifierName(ref.getNotifierName());
			obj.setNotifierTel(ref.getNotifierTel());
			obj.setNotifierIdNum(ref.getNotifierIdNum());
			obj.setNotifierDept(ref.getNotifierDept());
			obj.setAddress(ref.getAddress());
			obj.setNotifierEamil(ref.getNotifierEamil());
			obj.setNotifierType(ref.getNotifierType());
			obj.setNotifierTitle(ref.getNotifierTitle());
			
			obj.setEatersId(ref.getEatersId());
			if("Y".equals(ref.getIsSameNotifier())){
				obj.setEatersName(ref.getNotifierName());
				obj.setEatersIdNum(ref.getNotifierIdNum());
				obj.setEatersTel(ref.getNotifierTel());
			}else{
				obj.setEatersName(ref.getEatersName());
				obj.setEatersIdNum(ref.getEatersIdNum());
				obj.setEatersTel(ref.getEatersTel());
			}
			obj.setEatersSex(ref.getEatersSex());
			obj.setEatersBirthYear(ref.getEatersBirthYear());
			obj.setEatersHight(ref.getEatersHight());
			obj.setEatersWeight(ref.getEatersWeight());
			obj.setAddress(ref.getAddress());
		*/	
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
			java.util.Set hfr0003Dbs = obj.getHfr0003Dbs();
			if(hfr0003Dbs == null){
				hfr0003Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0003Db> d3List = new java.util.ArrayList<Hfr0003Db>();
			try{
				updateHfr0003Db(obj, hfr0003Dbs, d3List, ref.getHttpRequest(), ref.getDiscriptRow(), ref.discriptFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(d3List.size() > 0){
				for(Hfr0003Db dtl : d3List){
					dtl.getHfr0001Db().getHfr0003Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(d3List);
				d3List.clear();
			}
			
			// 醫師診斷及相關檢查及檢驗數據
			java.util.Set hfr0004Dbs = obj.getHfr0004Dbs();
			if(hfr0004Dbs == null){
				hfr0004Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0004Db> d4List = new java.util.ArrayList<Hfr0004Db>();
			try{
				updateHfr0004Db(obj, hfr0004Dbs, d4List, ref.getHttpRequest(), ref.getDoctorRow(), ref.doctorFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[描述資料檔更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			if(d4List.size() > 0){
				for(Hfr0004Db dtl : d4List){
					dtl.getHfr0001Db().getHfr0004Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
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
			
			java.util.Set hfr0002Dbs = obj.getHfr0002Dbs();
			if(hfr0002Dbs == null){
				hfr0002Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0002Db> d2List = new java.util.ArrayList<Hfr0002Db>();
			
			try{
				updateHfr0002Db(obj, hfr0002Dbs, d2List, "G", ref.getHttpRequest(), ref.getGFoodRow(), ref.arrGFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[健康食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateHfr0002Db(obj, hfr0002Dbs, d2List, "N", ref.getHttpRequest(), ref.getNFoodRow(), ref.arrNFoodFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[一般食品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "D", ref.getHttpRequest(), ref.getDrRow(), ref.arrDrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用藥品更新異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			try{
				updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "O", ref.getHttpRequest(), ref.getOdrRow(), ref.arrODrugFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[併用其它藥品，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			
			if(d2List.size() > 0){
				for(Hfr0002Db dtl : d2List){
					dtl.getHfr0001Db().getHfr0002Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				deleteBatch(d2List);
				d2List.clear();
			}
			
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "H", "HFR", ref.getFoodFileRow(), ref.arrHFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
				logger.info("[TCBW]-[HfrinDaoImpl]-[" + logName + "]-[更新檔案異常，HFR0001_DB.ID : " + obj.getId() + "]");
			}
			obj.setHfr0002Dbs(hfr0002Dbs);
			obj.setHfr0003Dbs(hfr0003Dbs);
			obj.setHfr0004Dbs(hfr0004Dbs);
			
			// 檔案處理- FOR 調查頁籤-病歷資訊、檢驗資料
			obj.setMedicalPostingDate(ref.getMedicalPostingDate());
			obj.setMedicalCompleteDate(ref.getMedicalCompleteDate());
			obj.setInspectPostingDate(ref.getInspectPostingDate());
			obj.setInspectCompleteDate(ref.getInspectCompleteDate());
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFM", "HFR", ref.getHFRFMFileRow(), ref.arrHFRFMFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFI", "HFR", ref.getHFRFIFileRow(), ref.arrHFRFIFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			
			// HFR0005_DB處理
			java.util.Set hfr0005Dbs = obj.getHfr0005Dbs();
			if(hfr0005Dbs == null){
				hfr0005Dbs = new ListOrderedSet();
				obj.setHfr0005Dbs(hfr0005Dbs);
			}
			java.util.Map<String, Hfr0005Db> oldHfr0005DbMap = new java.util.HashMap<String, Hfr0005Db>(); 
			for(Object dtlObj : hfr0005Dbs){
				Hfr0005Db dtl = (Hfr0005Db)dtlObj;
				oldHfr0005DbMap.put(Common.get(dtl.getId()), dtl);
			}
			
			boolean isUpdate = true;
			Hfr0005Db hfr0005Db = oldHfr0005DbMap.get(ref.getHfr0005Id());
			if(hfr0005Db == null){
				hfr0005Db = new Hfr0005Db();
				hfr0005Db.setHfr0001Db(obj);
				hfr0005Db.setCreateDate(ref.getUserID());
				hfr0005Db.setCreateDate(yyymmdd);
				hfr0005Db.setCreateTime(hhmmss);
				
				hfr0005Dbs.add(hfr0005Db);
				isUpdate = false;
			}
			hfr0005Db.setPreCompleteDate(ref.getPreCompleteDate());
			hfr0005Db.setAssessmentSendDate(ref.getAssessmentSendDate());
			hfr0005Db.setAssessmentCompleteDate(ref.getAssessmentCompleteDate());
			hfr0005Db.setCaseType(ref.getCaseType());
			hfr0005Db.setUnExpectedClassify(ref.getUnExpectedClassify());
			if(ref.getUnExpectedReason()!=null && ref.getUnExpectedReason().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : ref.getUnExpectedReason()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				hfr0005Db.setUnExpectedReason(sb.toString());
			}else{
				hfr0005Db.setUnExpectedReason("");
			}
			hfr0005Db.setEvidentiary(ref.getEvidentiary());
			hfr0005Db.setPreSeverity(ref.getPreSeverity());
			hfr0005Db.setAdministrativeLevel(ref.getAdministrativeLevel());
			hfr0005Db.setPreOpinion(ref.getPreOpinion());
			hfr0005Db.setPreRemark(ref.getPreRemark());
			hfr0005Db.setPreExplain(ref.getPreExplain());
			hfr0005Db.setUnPreExplain(ref.getUnPreExplain());
			hfr0005Db.setModifier(ref.getUserID());
			hfr0005Db.setModifyDate(yyymmdd);
			hfr0005Db.setModifyTime(hhmmss);
			if("doComplete".equals(ref.getState())){
				hfr0005Db.setIsClosed("Y");
			}
			if(isUpdate){
				update(hfr0005Db);
			}else{
				save(hfr0005Db);
			}
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), hfr0005Db.getId(), "HFR0005DB", "HFRFO", "HFR", ref.getHFRFOFileRow(), ref.arrHFRFOFileFieldName, ref.getUserID(), yyymmdd, hhmmss);
			oldHfr0005DbMap.clear();
			
			// 更新HFR0001_DB
			if("pauseSave".equals(ref.getState())){
				obj.setAssignMan(ref.getUserID());
				obj.setStatus("20");
			}else if("doComplete".equals(ref.getState())){
				obj.setAssignMan("");
				
				if("01".equals(Common.get(hfr0005Db.getCaseType()))){					// 結案
					obj.setStatus("90");
				}else if("02".equals(Common.get(hfr0005Db.getCaseType()))){				// 報告案
					obj.setStatus("40");
				}else{																	// 評估案
					obj.setStatus("30");
				}
			}
			obj.setUnExpectedClassify(hfr0005Db.getUnExpectedClassify());
			obj.setUnExpectedReason(hfr0005Db.getUnExpectedReason());
			obj.setEvidentiary(hfr0005Db.getEvidentiary());
			obj.setRecentlySeverity(hfr0005Db.getPreSeverity());
			obj.setAdministrativeLevel(hfr0005Db.getAdministrativeLevel());
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			//結案後產生檔案
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getHfrType(),obj.getStatus());
			}
			
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			// 寫入流程
			if("doComplete".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "初評評估完成", ref.getUserID());
			}
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}	
		
	}

	@Override
	public void updateByHFRIN0702F(HFRIN0702F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			String msg = "暫存完成";
			if("pauseSave".equals(ref.getState())){
				
			}else if("doComplete".equals(ref.getState())){
				msg = "評估完成";
			}
			
			// HFR0006_DB處理
			if(!"".equals(ref.getHfr0006Id())){
				java.util.Set hfr0006Dbs = obj.getHfr0006Dbs();
				if(hfr0006Dbs == null){
					hfr0006Dbs = new ListOrderedSet();
					obj.setHfr0006Dbs(hfr0006Dbs);
				}
				java.util.Map<String, Hfr0006Db> oldHfr0006DbMap = new java.util.HashMap<String, Hfr0006Db>(); 
				for(Object dtlObj : hfr0006Dbs){
					Hfr0006Db dtl = (Hfr0006Db)dtlObj;
					oldHfr0006DbMap.put(Common.get(dtl.getId()), dtl);
				}
				boolean isUpdate = true;
				Hfr0006Db hfr0006Db = oldHfr0006DbMap.get(ref.getHfr0006Id());
				if(hfr0006Db == null){
					hfr0006Db = new Hfr0006Db();
					hfr0006Db.setHfr0001Db(obj);
					hfr0006Db.setCreateDate(ref.getUserID());
					hfr0006Db.setCreateDate(yyymmdd);
					hfr0006Db.setCreateTime(hhmmss);
					
					hfr0006Dbs.add(hfr0006Db);
					isUpdate = false;
				}
				
				hfr0006Db.setEvaluateCommittee(Common.getLong(ref.getEvaluateCommittee()));
				hfr0006Db.setUnExpectedClassify(ref.getSecUnExpectedClassify());
				if(ref.getSecUnExpectedReason()!=null && ref.getSecUnExpectedReason().length>0){
					StringBuffer sb = new StringBuffer();
					for(String rid : ref.getSecUnExpectedReason()){
						if(sb.toString().length() > 0){
							sb.append(",");
						}
						sb.append(rid);
					}
					hfr0006Db.setUnExpectedReason(sb.toString());
				}else{
					hfr0006Db.setUnExpectedReason("");
				}
				hfr0006Db.setEvidentiary(ref.getSecEvidentiary());
				hfr0006Db.setSecSeverity(ref.getSecSeverity());
				hfr0006Db.setAdministrativeLevel(ref.getSecAdministrativeLevel());
				hfr0006Db.setAssessments(ref.getAssessments());
				hfr0006Db.setEvaluateResult(ref.getEvaluateResult());
				hfr0006Db.setSecExplain(ref.getSecExplain());
				hfr0006Db.setUnSecExplain(ref.getUnSecExplain());
				hfr0006Db.setModifier(ref.getUserID());
				hfr0006Db.setModifyDate(yyymmdd);
				hfr0006Db.setModifyTime(hhmmss);
				if("doComplete".equals(ref.getState())){
					
				}else{
					hfr0006Db.setIsClosed("N");
				}
				if(isUpdate){
					update(hfr0006Db);
				}else{
					save(hfr0006Db);
				}
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), hfr0006Db.getId(), "HFR0006DB", "HFRAS", "HFR", ref.getHFRASFileRow(), ref.arrHFRASFieldName, ref.getUserID(), yyymmdd, hhmmss);
				oldHfr0006DbMap.clear();
				
				obj.setUnExpectedClassify(hfr0006Db.getUnExpectedClassify());
				obj.setUnExpectedReason(hfr0006Db.getUnExpectedReason());
				obj.setEvidentiary(hfr0006Db.getEvidentiary());
				obj.setRecentlySeverity(hfr0006Db.getSecSeverity());
				obj.setAdministrativeLevel(hfr0006Db.getAdministrativeLevel());
			}
			
			if("doComplete".equals(ref.getState())){
				for(Object dtlObj : obj.getHfr0006Dbs()){
					Hfr0006Db dtl = (Hfr0006Db)dtlObj;
					dtl.setEvaluateDate(yyymmdd);
					dtl.setIsClosed("Y");
				}
			}
			
			// 更新HFR0001_DB
			if("pauseSave".equals(ref.getState())){
				obj.setAssignMan(ref.getUserID());
				obj.setStatus("30");
			}else if("doComplete".equals(ref.getState())){
				obj.setAssignMan("");
				obj.setStatus("40");
			}
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			// 寫入流程
			if("doComplete".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "預評評估完成", ref.getUserID());
			}
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
		
	}

	@Override
	public void updateByHFRIN0802F(HFRIN0802F ref) throws Exception {
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(ref.getId()));
		if(obj != null){
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			String msg = "暫存完成";
			if("pauseSave".equals(ref.getState())){
				
			}else if("doComplete".equals(ref.getState())){
				msg = "評估完成";
			}
			
			// HFR0007_DB處理
			java.util.Set hfr0007Dbs = obj.getHfr0007Dbs();
			if(hfr0007Dbs == null){
				hfr0007Dbs = new ListOrderedSet();
				obj.setHfr0007Dbs(hfr0007Dbs);
			}
			java.util.Map<String, Hfr0007Db> oldHfr0007DbMap = new java.util.HashMap<String, Hfr0007Db>(); 
			for(Object dtlObj : hfr0007Dbs){
				Hfr0007Db dtl = (Hfr0007Db)dtlObj;
				oldHfr0007DbMap.put(Common.get(dtl.getId()), dtl);
			}
			
			boolean isUpdate = true;
			Hfr0007Db hfr0007Db = oldHfr0007DbMap.get(ref.getHfr0007Id());
			if(hfr0007Db == null){
				hfr0007Db = new Hfr0007Db();
				hfr0007Db.setHfr0001Db(obj);
				hfr0007Db.setCreateDate(ref.getUserID());
				hfr0007Db.setCreateDate(yyymmdd);
				hfr0007Db.setCreateTime(hhmmss);
				
				hfr0007Dbs.add(hfr0007Db);
				isUpdate = false;
			}
			hfr0007Db.setCommitteeMeeting(ref.getCommitteeMeeting());
			hfr0007Db.setCommitteeDate(ref.getCommitteeDate());
			hfr0007Db.setRecordDate(ref.getRecordDate());
			hfr0007Db.setCaseBackDate(ref.getCaseBackDate());
			hfr0007Db.setUnExpectedClassify(ref.getThiUnExpectedClassify());
			if(ref.getThiUnExpectedReason()!=null && ref.getThiUnExpectedReason().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : ref.getThiUnExpectedReason()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				hfr0007Db.setUnExpectedReason(sb.toString());
			}else{
				hfr0007Db.setUnExpectedReason("");
			}
			
			hfr0007Db.setEvidentiary(ref.getThiEvidentiary());
			hfr0007Db.setThiSeverity(ref.getThiSeverity());
			hfr0007Db.setAdministrativeLevel(ref.getThiAdministrativeLevel());
			hfr0007Db.setReEvaluateResult(ref.getReEvaluateResult());
			hfr0007Db.setThiExplain(ref.getThiExplain());
			hfr0007Db.setUnThiExplain(ref.getUnThiExplain());
			
			if("doComplete".equals(ref.getState())){
				hfr0007Db.setIsClosed("Y");
			}else{
				hfr0007Db.setIsClosed("N");
			}
			if(isUpdate){
				update(hfr0007Db);
			}else{
				save(hfr0007Db);
			}
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(ref.getHttpRequest(), hfr0007Db.getId(), "HFR0007DB", "HFRRE", "HFR", ref.getHFRREFileRow(), ref.arrHFRREFieldName, ref.getUserID(), yyymmdd, hhmmss);
			oldHfr0007DbMap.clear();
			
			// 更新HFR0001_DB
			if("pauseSave".equals(ref.getState())){
				obj.setAssignMan(ref.getUserID());
				obj.setStatus("40");
			}else if("doComplete".equals(ref.getState())){
				obj.setAssignMan("");
				if("1".equals(ref.getReEvaluateResult())){
					obj.setStatus("90");
				}else{
					obj.setStatus("20");
				}
			}
			obj.setUnExpectedClassify(hfr0007Db.getUnExpectedClassify());
			obj.setUnExpectedReason(hfr0007Db.getUnExpectedReason());
			obj.setEvidentiary(hfr0007Db.getEvidentiary());
			obj.setRecentlySeverity(hfr0007Db.getThiSeverity());
			obj.setAdministrativeLevel(hfr0007Db.getAdministrativeLevel());
			obj.setModifier(ref.getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			update(obj);
			
			//結案後產生檔案
			if("90".equals(obj.getStatus())) {
				closedPrint(obj.getApplNo(),Common.get(obj.getId()),ref.getUserID(),obj.getHfrType(),obj.getStatus());
			}
			// 同案外部案件狀態
			updateHFR4001DbStatus(obj);
			
			// 寫入流程
			if("doComplete".equals(ref.getState())){
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("HFR01", obj.getId(), Common.get(obj.getApplNo()), obj.getStatus(), "複評評估完成", ref.getUserID());
			}
			ref.setId(Common.get(obj.getId()));
			ref.setErrorMsg(msg);
			ref.setIsNeedBackQuery("Y");
		}else{
			ref.setErrorMsg("查無該筆資料，請重新操作 !");
			ref.setIsNeedBackQuery("Y");
		}
		
	}
	
	//產生備份PDF
	public void closedPrint(String applNo,String id,String userID,String hfrType,String status) throws Exception 
	{
		HFREX0104F hfrex0104f=new HFREX0104F();
		
		if(!"".equals(applNo)) {
			if("90".equals(status)) {
				hfrex0104f.setApplNo(applNo);
			} else if("02".equals(status)) {
				hfrex0104f.setId(applNo);
			}
		}
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
		
		javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
	    
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		
		if("1".equals(hfrType)) {
			hfrex0104f.setSR_Parameter(parms);
			model = hfrex0104f.getSimpleDefaultTableModel();
			env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr.jasper"));
		} else {
			hfrex0104f.setGR_Parameter(parms);
			model = hfrex0104f.getGeneralDefaultTableModel();
			env.setJasperFile(ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr.jasper"));
		}
		

		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());

		env.setFormat("PDF");	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	    
	    String hfr = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("HFR");
	    
	    String fileName="";//檔案名稱
	    String fileDir="HFR000001Backup";//存放資料夾
	    
	    if(!"".equals(applNo))fileName=applNo;
	   
	    File meddir = new File(hfr);
	    //判斷資料夾是否存在，若不存在則建立
	    if(!meddir.isDirectory())
	    {
	    	meddir.mkdir();
	    }	
	    
	    
	    
	    File dir = new File(hfr+"\\"+fileDir+"\\");
	    //判斷資料夾是否存在，若不存在則建立
	    if(!dir.isDirectory())
	    {
	    	dir.mkdir();
	    }	
	  
	    File output = new File(hfr+"\\"+fileDir+"\\"+fileName+".pdf");
	    //產生檔案存放
	    generator.reportToFile(output, parms);
	    
	    Con0001Db o = new Con0001Db();
	    
	    o.setFileKind("H");
	    o.setUpLoadId(Common.getLong(id));
	    o.setFileRoute(fileDir);
	    o.setFileName(fileName+".pdf");
	    o.setFileExplan("健康食品及膠囊錠狀食品非預期反應通報備查PDF");
	    o.setIsInsert("N");
	    o.setIsDelete("N");
	    o.setSystemType("HFR000001");
	    o.setDbName("Hfr0001Db");
	    o.setCreator(userID);
	    o.setCreateDate(Datetime.getYYYMMDD());
	    o.setCreateTime(Datetime.getHHMMSS());

	    save(o);

	    env.clear();

	}
	

}
