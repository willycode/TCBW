package com.kangdainfo.tcbw.view.comple;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgin.DRGIN0302F;


public class COMPLE0201F extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)	
	private String tabId ;
	private String toUpdate;  //檔案上傳後維持在修改模式
	
	private String applNo; // 案件號碼 VARCHAR(11)
	private String status; // 案件狀態 VARCHAR(2)
	private String chargeMan; // 作業人員 VARCHAR(50)
	private String enrolledDate; // 收案日期 VARCHAR(7)
	private String inOrOutcreator; // 案件擁有者 VARCHAR(50)
	private String isInOrOutPerson;

	// 基本資料------------------------------------------------------------------
	private String occurDate; // 發生日期 VARCHAR(7)
	private String notifierRevDate; // 通報者接獲日期 VARCHAR(7)
	private String notifierRepDate; // 通報中心接獲通報日期 VARCHAR(7)
	private String notifierSource; // 通報來源 VARCHAR(2)
	private String notifierUserID; // 通報者ID
	private String notifierName; // 通報者姓名 NVARCHAR(20)
	private String notifierDeptID; //通報者服務機構ID NVARCHAR
	private String notifierDept; // 通報者服務機構 NVARCHAR(50)
	private String notifierTelArea;
	private String notifierTel; // 通報者電話 VARCHAR(10)
	private String notifierTelExt;
	private String notifierPhone; // 通報者手機 VARCHAR(10)
	private String notifierCounty;
	private String notifierAddress; // 通報者地址 NVARCHAR(100)
	private String notifierZipCode;
	private String notifierEmail; // 通報者電子信箱 VARCHAR(50)
	private String notifierFaxArea;
	private String notifierFax; // 通報者傳真 VARCHAR(10)
	private String notifierType; // 通報者屬性 VARCHAR(2)
	private String notifierTitle; // 通報者職稱 VARCHAR(2)
	private String patientId; // 病人識別代號
	private String patientSex; // 病人性別
	private String patientBirth; // 病人出生日期
	private String patientAge; // 病人年齡
	private String patientHeight; // 病人身高
	private String patientWeight; // 病人體重
	
	// 療效不等反應------------------------------------------------------------------
	private String[] conSequence; // 通報事件後果
	private String effectChangeDesc; // 通報事件後果-藥效改變狀況
	private String badReactionLev; // 通報事件後果-不良反應等級
	private String badReactionDesc; // 通報事件後果-不良反應狀況
	private String beforeDesc; // 事件前描述
	private String changeDesc; // 藥品轉換描述
	private String occurDesc; // 發生事件描述
	private String afterDesc; // 事件後描述
	private String otherDesc; // 其他相關資料
	private String dealWith; // 發生事件後之處置
	private String dealWithOther; // 發生事件後之處置其他
	private String isImproveYn; // 病人恢復原藥或轉換同成分藥品其症狀是否改善
	private String isContactYn; // 提供聯絡資訊供廠商後續調查評估
	private String dressingAttitude; // 醫師對換藥的態度
	private String obedienceLev; // 病人服藥順從性

	// 案件初評表------------------------------------------------------------------
	private String drg04Update; //是否新增修改drg4004
	private String drg04id; //初評資料檔ID
	private String medNti; // 藥品成分-NTI Drugs
	private String medAtcCode; // 藥品成分-藥理治療分類(ATC code)
	private String[] conSequence4; // 通報事件後果
	private String effectChangeDesc4;// 通報事件後果-藥效改變狀況
	private String badReactionLev4; // 通報事件後果-不良反應等級
	private String badReactionDesc4; // 通報事件後果-不良反應狀況
	private String badReactionDra; // 通報事件後果-不良反應MedDRA代碼
	private String assessEC1; // 相關性評估-藥效改變-時序姓
	private String assessEC2; // 相關性評估-藥效改變-併用藥物
	private String assessEC3; // 相關性評估-藥效改變-維持藥效
	private String assessBR1; // 相關性評估-不良反應-時序姓
	private String assessBR2; // 相關性評估-不良反應-併用藥物
	private String assessBR3; // 相關性評估-不良反應-減輕或消失
	private String assessResult; // 療效不等評估結果
	private String notifierAging; // 通報時效
	private String notifierQuality; // 通報品質
	private String assessRemark; // 評估註記
	private String intervalDays; // 間隔天數
	private String remark; // 備註
	private String isCouncilYn; // 是否提報諮議會
	private String caseHCount; // 歷年本藥品之通報件數
	private String caseHPCount; // 歷年本藥品Possible以上之通報件數
	private String caseHYCount; // 一年內本藥品之通報件數
	private String caseHPYCount; // 一年內本藥品Possible以上之通報件數
	
	private String q_applNo;
	private String q_permitKey;
	private String q_permitNo;
	private String q_occurDateS, q_occurDateE;
	private String q_notifierRepDateS, q_notifierRepDateE;
	private String q_enrolledDateS, q_enrolledDateE;
	private String q_notifierSource;
	private String q_notifierDept;
	private String q_productName;
	private String q_scientificName;
	private String q_applicationName;
	private String q_manufactorName;
	private String[] q_conSequence;
	private String q_medNti;
	private String q_medAtcCode;
	private String q_assessResult;
	private String q_status;
	
	// ========== 其他相關檢查及檢驗數據資訊 =================
	String[] drg42Row;

	public String[] getDrg42Row() {
		return drg42Row;
	}

	public void setDrg42Row(String[] drg42Row) {
		this.drg42Row = drg42Row;
	}

	public final String[] arrDrg42FieldName = { "testDate", "testItems",
			"testNum" };
	String drg42JSBuilder;

	public String getDrg42JSBuilder() {
		if (drg42JSBuilder != null)
			return drg42JSBuilder;
		else
			return "";
	}

	public void setDrg42JSBuilder(String drg42JSBuilder) {
		this.drg42JSBuilder = drg42JSBuilder;
	}

	public String genDrg4002DbSet(java.util.Set dtlSet) throws Exception {
		if (dtlSet != null && dtlSet.size() > 0) {
			StringBuilder sb = new StringBuilder(1024);
			int j = 0;
			for (Object dtlObj : dtlSet) {
				Drg4002Db dtl = (Drg4002Db) dtlObj;
				sb.append("addDrg42Row('drg42Table'");
				for (j = 0; j < arrDrg42FieldName.length; j++) {
					sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg42FieldName[j])))));
				}
				sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
				sb.append(");\n");
			}
			this.setDrg42JSBuilder(sb.toString());
			return sb.toString();
		} else if (httpRequest != null && this.getDrg42Row() != null&& this.getDrg42Row().length > 0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i = 0; i < getDrg42Row().length; i++) {
				String rid = getDrg42Row()[i];
				sb.append("addDrg42Row('drg42Table'");
				for (int j = 0; j < arrDrg42FieldName.length; j++) {
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg42FieldName[j] + rid)), true);
					sb.append(",").append(Common.sqlChar(v));
				}
				sb.append(",'").append(checkGet(httpRequest.getParameter("drg42Id"+ rid))).append("'");
				sb.append(");\n");

			}
			this.setDrg42JSBuilder(sb.toString());
			return sb.toString();
		}
		return "";
	}

	// ========== 使用藥品 =================
	String[] drg43Row;
	public String[] getDrg43Row() {return drg43Row;}
	public void setDrg43Row(String[] drg43Row) {this.drg43Row = drg43Row;}
	public final String[] arrDrg43FieldName = { "medType", "permitKey","permitNo", "scientificName", "productName", "applicationName","manufactorName", "manufactorNo", "content", "medModel", "medPath","dosage", "frequency", "startDare", "endDate", "indication", "manufactorID","medModelOther","medPathOther","frequencyOther"};
	String drg43JSBuilder;
	public String getDrg43JSBuilder() {
		if (drg43JSBuilder != null)	return drg43JSBuilder;
		else return "";
	}
	public void setDrg43JSBuilder(String drg43JSBuilder) {this.drg43JSBuilder = drg43JSBuilder;}
	public String genDrg4003DbSet(java.util.Set dtlSet) throws Exception {
		if (dtlSet != null && dtlSet.size() > 0) {
			StringBuilder sb = new StringBuilder(1024);
			int j = 0;
			for (Object dtlObj : dtlSet) {
				Drg4003Db dtl = (Drg4003Db) dtlObj;
				sb.append("addDrg43Row").append(dtl.getMedType()).append("('drg43Table").append(dtl.getMedType()).append("'");
				for (j = 0; j < arrDrg43FieldName.length; j++) {
					sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg43FieldName[j])))));
				}
				sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
				sb.append(");\n");
			}
			this.setDrg43JSBuilder(sb.toString());
			return sb.toString();
		} else if (httpRequest != null && this.getDrg43Row() != null && this.getDrg43Row().length > 0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i = 0; i < getDrg43Row().length; i++) {
				String rid = getDrg43Row()[i];
				sb.append("addDrg43Row('drg43Table'");
				for (int j = 0; j < arrDrg43FieldName.length; j++) {
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg43FieldName[j] + rid)), true);
					sb.append(",").append(Common.sqlChar(v));
				}
				sb.append(",'").append(checkGet(httpRequest.getParameter("drg63Id"+ rid))).append("'");
				sb.append(");\n");

			}
			this.setDrg43JSBuilder(sb.toString());
			return sb.toString();
		}
		return "";
	}

	// ========== 附件上傳 =================
	String[] conRow;
	public String[] getConRow() {
		return conRow;
	}
	public void setConRow(String[] conRow) {
		this.conRow = conRow;
	}
	public final String[] arrConFieldName = { "fileName", "fileExplan","fileRoute" };
	String conJSBuilder;

	public String getConJSBuilder() {
		if (conJSBuilder != null) return conJSBuilder;
		else return "";
	}
	public void setConJSBuilder(String conJSBuilder) {
		this.conJSBuilder = conJSBuilder;
	}
	public String genCon0001DbSet(Long drg4001Id) throws Exception {
		String hql = " from Con0001Db where fileKind='DRG' and systemType = 'DRG020001' and dbName='Drg4001Db' and upLoadId="+ drg4001Id;
		List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(hql + " order by id asc");
		if (null != conList && !conList.isEmpty()) {
			StringBuilder sb = new StringBuilder(1024);
			for (Con0001Db dtl : conList) {
				sb.append("addConRow('conTable'");
				for (int j = 0; j < arrConFieldName.length; j++) {
					if (j == 2) {
						String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
						sb.append(",").append(Common.sqlChar(attFile));
					} else {
						sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConFieldName[j])), true)));
					}
				}
				sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
				sb.append(");\n");
			}
			this.setConJSBuilder(sb.toString());
			return sb.toString();
		}
		return "";
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		COMPLE0201F obj = this;
		
		Drg4001Db c = (Drg4001Db) View.getObject(" from Drg4001Db where id="+ Common.getInt(obj.getId()));

		if (c != null) {
			obj.setApplNo(c.getApplNo());
			obj.setChargeMan(c.getChargeMan());
			// 通報訊息
			obj.setOccurDate(c.getOccurDate()); // 通報日期
			obj.setNotifierRepDate(c.getNotifierRepDate()); // 通報者接獲日期
			obj.setNotifierRevDate(c.getNotifierRevDate()); // 通報中心接獲日期
			obj.setEnrolledDate(c.getEnrolledDate()); // 收案日期			
			obj.setNotifierSource(c.getNotifierSource()); // 原始藥物不良事件獲知來源
			obj.setStatus(c.getStatus());

			// 通報者資訊
			obj.setNotifierUserID(c.getNotifierUserID());
			obj.setNotifierName(c.getNotifierName()); // 姓名
			obj.setNotifierDeptID(c.getNotifierDeptID()); // 服務機構
			obj.setNotifierDept(c.getNotifierDept()); // 服務機構
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel()); // 電話
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierEmail(c.getNotifierEmail()); // E-Mail
			obj.setNotifierPhone(c.getNotifierPhone()); // 手機
			obj.setNotifierFaxArea(c.getNotifierFaxArea());
			obj.setNotifierFax(c.getNotifierFax()); // 傳真
			obj.setNotifierCounty(c.getNotifierCounty());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setNotifierAddress(c.getNotifierAddress()); // 地址
			obj.setNotifierTitle(c.getNotifierTitle()); // 職稱
			obj.setNotifierType(c.getNotifierType()); // 屬性

			// 病人相關資料
			obj.setPatientId(c.getPatientId()); // 識別代碼
			obj.setPatientSex(c.getPatientSex()); // 性別
			obj.setPatientBirth(c.getPatientBirth()); // 出生日期
			obj.setPatientAge(c.getPatientAge()); // 年齡
			obj.setPatientHeight(c.getPatientHeight()); // 身高
			obj.setPatientWeight(c.getPatientWeight()); // 體重

			// 療效不等反應
			obj.setConSequence(Common.get(c.getConSequence()).split(",")); // 通報事件後果
			obj.setEffectChangeDesc(c.getEffectChangeDesc()); // 通報事件後果-藥效改變狀況
			obj.setBadReactionLev(c.getBadReactionLev()); // 通報事件後果-不良反應等級
			obj.setBadReactionDesc(c.getBadReactionDesc()); // 通報事件後果-不良反應狀況
			obj.setBeforeDesc(c.getBeforeDesc()); // 事件前描述
			obj.setChangeDesc(c.getChangeDesc()); // 藥品轉換描述
			obj.setOccurDesc(c.getOccurDesc()); // 發生事件描述
			obj.setAfterDesc(c.getAfterDesc()); // 事件後描述
			obj.setOtherDesc(c.getOtherDesc()); // 其他相關資料
			obj.setDealWith(c.getDealWith()); // 發生事件後之處置
			obj.setDealWithOther(c.getDealWithOther()); // 發生事件後之處置其他
			obj.setIsImproveYn(c.getIsImproveYn()); // 病人恢復原藥或轉是同成分藥品其症狀是否改善
			obj.setIsContactYn(c.getIsContactYn()); // 提供聯絡資訊供廠商後續調查評估
			obj.setDressingAttitude(c.getDressingAttitude()); // 醫師對換藥的態度
			obj.setObedienceLev(c.getObedienceLev()); // 病人服藥順從性
			obj.setDrg42JSBuilder(this.genDrg4002DbSet(c.getDrg4002Dbs()));
			obj.setDrg43JSBuilder(this.genDrg4003DbSet(c.getDrg4003Dbs()));
			obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));

			// 藥品療效不等初評表
			Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(c.getApplNo()));
			if (null != drg04) {
				obj.setDrg04id(Common.get(drg04.getId()));
				obj.setMedNti(drg04.getMedNti());
				obj.setMedAtcCode(drg04.getMedAtcCode());
				obj.setConSequence4(Common.get(drg04.getConSequence()).split(","));
				obj.setEffectChangeDesc4(drg04.getEffectChangeDesc());
				obj.setBadReactionLev4(drg04.getBadReactionLev());
				obj.setBadReactionDesc4(drg04.getBadReactionDesc());
				obj.setBadReactionDra(drg04.getBadReactionDra());
				obj.setAssessEC1(drg04.getAssessEC1());
				obj.setAssessEC2(drg04.getAssessEC2());
				obj.setAssessEC3(drg04.getAssessEC3());
				obj.setAssessBR1(drg04.getAssessBR1());
				obj.setAssessBR2(drg04.getAssessBR2());
				obj.setAssessBR3(drg04.getAssessBR3());
				obj.setAssessResult(drg04.getAssessResult());
				obj.setNotifierAging(drg04.getNotifierAging());
				obj.setNotifierQuality(drg04.getNotifierQuality());
				obj.setIsCouncilYn(drg04.getIsCouncilYn());
				obj.setAssessRemark(drg04.getAssessRemark());
				obj.setIntervalDays(drg04.getIntervalDays());
				obj.setRemark(drg04.getRemark());

				// 取得療效不等的藥證號碼
				String permitKey = "";
				String permitNo = "";
				if(null != c.getDrg4003Dbs() && !c.getDrg4003Dbs().isEmpty()){
					for(Object dtlObj:c.getDrg4003Dbs()){
						Drg4003Db drg43 = (Drg4003Db)dtlObj;
						permitKey = Common.get(drg43.getPermitKey());
						permitNo = Common.get(drg43.getPermitNo());
					}
				}
				
				obj.setCaseHCount("0");
				obj.setCaseHPCount("0");
				obj.setCaseHYCount("0");
				obj.setCaseHPYCount("");
				if(!"".equals(permitKey) && !"".equals(permitNo)){
					// 歷年本藥品之通報件數
					String caseHhql = " from Drg4001Db where applNo is not null and applNo <> '' and status in ('30','40','50','90') "
									+ " and id in (select drg4001Db.id from Drg4003Db where medType='02' and permitKey+permitNo = " + Common.sqlChar(permitKey+permitNo)+")" 
									+ " and id <> " + c.getId();
					obj.setCaseHCount(Common.get(ServiceGetter.getInstance().getCommonService().loadCount(caseHhql)));

					// 一年內本藥品之通報件數
					
					caseHhql += " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
					obj.setCaseHYCount(Common.get(ServiceGetter.getInstance().getCommonService().loadCount(caseHhql)));
					
					
					// 歷年本藥品Possible以上之通報件數
					String caseHPhql = " from Drg4001Db where applNo is not null and applNo <> '' and status in ('30','40','50','90') "
							+ " and id in (select drg4001Db.id from Drg4003Db where medType='02' and permitKey+permitNo = " + Common.sqlChar(permitKey+permitNo)+")" 
							+ " and applNo in (select applNo from Drg4004Db where assessResult in ('01','02','03')) and id <> " + c.getId();
					obj.setCaseHPCount(Common.get(ServiceGetter.getInstance().getCommonService().loadCount(caseHPhql)));

					// 一年內本藥品Possible以上之通報件數
					
					String caseHPYhql = " from Drg4001Db where applNo is not null and applNo <> '' and status in ('30','40','50','90') "
							+ " and id in (select drg4001Db.id from Drg4003Db where medType='02' and permitKey+permitNo = " + Common.sqlChar(permitKey+permitNo)+")" 
							+ " and applNo in (select applNo from Drg4004Db where assessResult in ('01','02','03')) and id <> " + c.getId()
							+ " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
						
					List<Drg4001Db> drg41List = ServiceGetter.getInstance().getCommonService().load(caseHPYhql);
					if(null != drg41List && !drg41List.isEmpty()){
						Map<String, Integer> drg41Map = new HashMap<String, Integer>();
						for(Drg4001Db drg41:drg41List){
							if(null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
								for(Object dtlObj:drg41.getDrg4003Dbs()){
									Drg4003Db drg43 = (Drg4003Db) dtlObj;
									if("02".equals(drg43.getMedType())){
										Integer drg43Count = Common.getInt(drg41Map.get(drg43.getManufactorNo()));
										drg43Count++;
										drg41Map.put(drg43.getManufactorNo(), drg43Count);
									}
								}
							}
						}
						if (null != drg41Map && !drg41Map.isEmpty()) {
							String caseHPYCountStr = "";
							for(String manufactorNo:drg41Map.keySet()){
								if (caseHPYCountStr.length() > 0)
									caseHPYCountStr += "、";
								caseHPYCountStr += "[" + manufactorNo + "]有" + drg41Map.get(manufactorNo) + "件";
							}
							
							obj.setCaseHPYCount(caseHPYCountStr);
						}
					}					
				}
			}
		}
		this.setState("queryOneSuccess");
		return obj;
	}

	@Override
	public void doUpdate() throws Exception {		
		List delList = new ArrayList();
		List updList = new ArrayList();
		Drg4001Db obj = (Drg4001Db)View.getObject(" from Drg4001Db where id = " + Common.getLong(getId()));
        
		if(obj != null)
		{
        	// 通報訊息
			obj.setOccurDate(getOccurDate()); // 通報日期
			obj.setNotifierRepDate(getNotifierRepDate()); // 通報者接獲日期
			obj.setNotifierRevDate(getNotifierRevDate()); // 通報中心接獲日期
			obj.setNotifierSource(getNotifierSource()); // 原始藥物不良事件獲知來源
			obj.setEnrolledDate(getEnrolledDate()); //收案日期

			// 通報者資訊
			obj.setNotifierName(getNotifierName()); // 姓名
			obj.setNotifierDept(getNotifierDept()); // 服務機構
			obj.setNotifierTel(getNotifierTel()); // 電話
			obj.setNotifierTelExt(getNotifierTelExt()); // 電話
			obj.setNotifierTelArea(getNotifierTelArea()); // 電話
			obj.setNotifierEmail(getNotifierEmail()); // E-Mail
			obj.setNotifierPhone(getNotifierPhone()); // 手機
			obj.setNotifierFax(getNotifierFax()); // 傳真
			obj.setNotifierFaxArea(getNotifierFaxArea()); // 傳真
			obj.setNotifierAddress(getNotifierAddress()); // 地址
			obj.setNotifierTitle(getNotifierTitle()); // 職稱
			obj.setNotifierType(getNotifierType()); // 屬性

			// 病人相關資料
			obj.setPatientId(getPatientId()); // 識別代碼
			obj.setPatientSex(getPatientSex()); // 性別
			obj.setPatientBirth(getPatientBirth()); // 出生日期
			obj.setPatientAge(getPatientAge()); // 年齡
			obj.setPatientHeight(getPatientHeight()); // 身高
			obj.setPatientWeight(getPatientWeight()); // 體重

			// 療效不等反應
			String conSequences = "";
			if(null != getConSequence() && getConSequence().length > 0){
				for(String conSeq : getConSequence()){
					if(conSequences.length() > 0)	conSequences += ",";
					conSequences += conSeq;
				}
			}
			obj.setConSequence(conSequences);	
			obj.setEffectChangeDesc(getEffectChangeDesc());				//通報事件後果-藥效改變狀況
			obj.setBadReactionLev(getBadReactionLev());					//通報事件後果-不良反應等級
			obj.setBadReactionDesc(getBadReactionDesc());				//通報事件後果-不良反應狀況
			//通報事件後果
			if(!StringUtils.contains(obj.getConSequence(), "1")) {
				obj.setEffectChangeDesc(null);
			}
			if(!StringUtils.contains(obj.getConSequence(), "2")) {
				obj.setBadReactionLev(null);
				obj.setBadReactionDesc(null);
			}

			obj.setBeforeDesc(getBeforeDesc()); // 事件前描述
			obj.setChangeDesc(getChangeDesc()); // 藥品轉換描述
			obj.setOccurDesc(getOccurDesc()); // 發生事件描述
			obj.setAfterDesc(getAfterDesc()); // 事件後描述
			obj.setOtherDesc(getOtherDesc()); // 其他相關資料
			obj.setDealWith(getDealWith()); // 發生事件後之處置
			if ("ZZ".equals(getDealWith())) {
				obj.setDealWithOther(getDealWithOther()); // 發生事件後之處置其他
			} else {
				obj.setDealWithOther(null);
			}

			obj.setIsImproveYn(getIsImproveYn()); // 病人恢復原藥或轉是同成分藥品其症狀是否改善
			obj.setIsContactYn(getIsContactYn()); // 提供聯絡資訊供廠商後續調查評估
			obj.setDressingAttitude(getDressingAttitude()); // 醫師對換藥的態度
			obj.setObedienceLev(getObedienceLev()); // 病人服藥順從性

			// 其他相關檢查及檢驗數據資訊
			if (true) {
				java.util.Set dtlSet = new ListOrderedSet();
				if (getHttpRequest() != null && getDrg42Row() != null && getDrg42Row().length > 0) {
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getDrg42Row().length; i++) {
						String rid = getDrg42Row()[i];
						Drg4002Db dtl = (Drg4002Db) View.getObject("from Drg4002Db where id="+ Common.getLong(getHttpRequest().getParameter("drg42Id" + rid)));
						if (dtl == null) {
							dtl = new Drg4002Db();
							dtl.setDrg4001Db(obj);
						}
						for (int j = 0; j < arrDrg42FieldName.length; j++) {
							BeanUtils.setProperty(dtl,arrDrg42FieldName[j],getHttpRequest().getParameter(arrDrg42FieldName[j] + rid));
						}
						dtl.setModifier(getUserID());
						dtl.setModifyDate(Datetime.getYYYMMDD());
						dtl.setModifyTime(Datetime.getHHMMSS());
						dtlSet.add(dtl);

						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6002Db where drg6001Db.id="+ Common.getLong(obj.getId())+ " and id not in ("+ dtlKey.toString() + ")"));
				} else {
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg6002Db where drg6001Db.id="+ Common.getLong(obj.getId())));
				}
				obj.setDrg4002Dbs(dtlSet);
			}

			// 使用藥品
			if (true) {
				java.util.Set dtlSet = new ListOrderedSet();
				if (getHttpRequest() != null && getDrg43Row() != null && getDrg43Row().length > 0) {
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getDrg43Row().length; i++) {
						String rid = getDrg43Row()[i];
						Drg4003Db dtl = (Drg4003Db) View.getObject("from Drg4003Db where id="+ Common.getLong(getHttpRequest().getParameter("drg43Id" + rid)));
						if (dtl == null) {
							dtl = new Drg4003Db();
							dtl.setDrg4001Db(obj);
						}
						for (int j = 0; j < arrDrg43FieldName.length; j++) {
							BeanUtils.setProperty(dtl,arrDrg43FieldName[j],getHttpRequest().getParameter(arrDrg43FieldName[j] + rid));
						}
						dtl.setModifier(getUserID());
						dtl.setModifyDate(Datetime.getYYYMMDD());
						dtl.setModifyTime(Datetime.getHHMMSS());
						dtlSet.add(dtl);

						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg4003Db where drg4001Db.id="+ Common.getLong(obj.getId())+ " and id not in ("+ dtlKey.toString() + ")"));
				} else {
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg4003Db where drg4001Db.id="+ Common.getLong(obj.getId())));
				}
				obj.setDrg4003Dbs(dtlSet);
			}

			obj.setModifier(getUserID());
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMMSS());

			// 附件上傳
			if (true) {
				java.util.Set dtlSet = new ListOrderedSet();
				if (getHttpRequest() != null && getConRow() != null && getConRow().length > 0) {
					StringBuilder dtlKey = new StringBuilder("-2,-1");
					for (int i = 0; i < getConRow().length; i++) {
						String rid = getConRow()[i];
						Con0001Db dtl = (Con0001Db) View.getObject("from Con0001Db where id="+ Common.getLong(getHttpRequest().getParameter("conId" + rid)));
						dtlKey.append(",").append(dtl.getId());
					}
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Drg4001Db' and upLoadId= "+ Common.getLong(getId())+ " and id not in ("+ dtlKey.toString() + ")"));
				} else {
					delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Con0001Db where systemType ='DRG020001' and dbName ='Drg4001Db' and upLoadId= "+ Common.getLong(getId())));
				}
			}

			// 藥品療效不等初評表
			if("Y".equals(Common.get(getDrg04Update()))){
				Drg4004Db drg44 = null;
				if(!"".equals(Common.get(getDrg04id()))){
					drg44 = (Drg4004Db)View.getObject(" from Drg4004Db where id = " + Common.get(getDrg04id()));
				}else{
					drg44 = new Drg4004Db();    
				}
				drg44.setApplNo(Common.get(obj.getApplNo()));
				drg44.setMedNti(getMedNti());
				drg44.setMedAtcCode(getMedAtcCode());
				String conSequences4 = "";
				if(null != getConSequence4() && getConSequence4().length > 0){
					for(String conSeq : getConSequence4()){
						if(conSequences4.length() > 0)	conSequences4 += ",";
						conSequences4 += conSeq;
					}
				}
				drg44.setConSequence(conSequences4);	
				drg44.setEffectChangeDesc(getEffectChangeDesc4());				//通報事件後果-藥效改變狀況
				drg44.setBadReactionLev(getBadReactionLev4());					//通報事件後果-不良反應等級
				drg44.setBadReactionDesc(getBadReactionDesc4());				//通報事件後果-不良反應狀況
				drg44.setBadReactionDra(this.getBadReactionDra());
				//通報事件後果
				if(!StringUtils.contains(drg44.getConSequence(), "1")) {
					drg44.setEffectChangeDesc(null);
				}
				if(!StringUtils.contains(drg44.getConSequence(), "2")) {
					drg44.setBadReactionLev(null);
					drg44.setBadReactionDesc(null);
					drg44.setBadReactionDra(null);
				}
				drg44.setAssessEC1(getAssessEC1());
				drg44.setAssessEC2(getAssessEC2());
				drg44.setAssessEC3(getAssessEC3());
				drg44.setAssessBR1(getAssessBR1());
				drg44.setAssessBR2(getAssessBR2());
				drg44.setAssessBR3(getAssessBR3());
				drg44.setAssessResult(getAssessResult());
				drg44.setNotifierAging(getNotifierAging());
				drg44.setNotifierQuality(getNotifierQuality());
				drg44.setIsCouncilYn(getIsCouncilYn());
				drg44.setAssessRemark(getAssessRemark());
				drg44.setIntervalDays(getIntervalDays());
				drg44.setRemark(getRemark());
				drg44.setModifier(getUserID());
				drg44.setModifyDate(Datetime.getYYYMMDD());
				drg44.setModifyTime(Datetime.getHHMMSS());
				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(drg44);
			}
			
			ServiceGetter.getInstance().getTcbwService().update(obj);
			ServiceGetter.getInstance().getTcbwService().updateBatch(updList);
			ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
		}
	}
	
	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg4001Db where (id in ( select max(a.id) from Drg4001Db a where (a.applNo is not null or a.applNo <> '')  group by a.applNo )"+
		     	"  or id in ( select b.id from Drg4001Db b where (b.applNo is null or b.applNo = ''))) and trans = 'Y'";
		
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_occurDateS()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		if(!"".equals(getQ_enrolledDateS()))
			hql += " and enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS());
		if(!"".equals(getQ_enrolledDateE()))
			hql += " and enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE());
		if(!"".equals(getQ_notifierSource()))
			hql += " and notifierSource = " + Common.sqlChar(getQ_notifierSource());
		if(!"".equals(getQ_notifierDept()))
			hql += " and notifierDept = " + Common.sqlChar(getQ_notifierDept());
		if(!"".equals(getQ_permitKey())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and permitKey = " + Common.sqlChar(getQ_permitKey())+")";
		}
		if(!"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}
		if(!"".equals(getQ_productName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and productName like " + Common.likeSqlChar(getQ_productName())+")";
		}
		if(!"".equals(getQ_scientificName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and scientificName like " + Common.likeSqlChar(getQ_scientificName())+")";
		}
		if(!"".equals(getQ_applicationName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and applicationName like " + Common.likeSqlChar(getQ_applicationName())+")";
		}
		if(!"".equals(getQ_manufactorName())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType = '02' and manufactorName like " + Common.likeSqlChar(getQ_manufactorName())+")";
		}
		if(null != getQ_conSequence() && !"".equals(getQ_conSequence())){
			hql += " and ( ";
			for(int i=0;i<getQ_conSequence().length;i++){
				if(i > 0) hql += " or ";
				hql += "  conSequence  like " + Common.likeSqlChar(getQ_conSequence()[i]);
			}
			hql += ")";
		}
		if(!"".equals(getQ_medNti())){
			hql += " and applNo in (select applNo from Drg4004Db where medNti = 'Y')";
		}
		if(!"".equals(getQ_medAtcCode())){
			hql += " and applNo in (select applNo from Drg4004Db where medAtcCode like "+Common.likeSqlChar(getQ_medAtcCode(),false)+")";
		}
		if(!"".equals(getQ_assessResult())){
			hql += " and applNo in (select applNo from Drg4004Db where assessResult = " + Common.sqlChar(getQ_assessResult())+")";
		}
		if(!"".equals(getQ_status())){
			hql += " and status = " + Common.sqlChar(getQ_status());
		}
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRG0310");
				for(Object dtlObj : objList)
				{
					Drg4001Db dtl = (Drg4001Db)dtlObj;
					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = "";
					rowArray[4] = "";
					rowArray[5] = "";
					rowArray[6] = "";
					rowArray[7] = "";
					rowArray[8] = "";
					if(null != dtl.getDrg4003Dbs() && !dtl.getDrg4003Dbs().isEmpty()){
						for(Object dtl43:dtl.getDrg4003Dbs()){
							Drg4003Db drg43 = (Drg4003Db)dtl43;
							if("02".equals(drg43.getMedType())){
								if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
									rowArray[3] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
								}
								rowArray[4] =  Common.get(drg43.getScientificName());
								rowArray[5] =  Common.get(drg43.getProductName());
								rowArray[6] =  Common.get(drg43.getApplicationName());
								rowArray[7] =  Common.get(drg43.getManufactorName());
								rowArray[8] =  Common.get(drg43.getManufactorNo());
							}
						}
					}
					rowArray[9] = "";
					if(StringUtils.contains(dtl.getConSequence(), "1")) {
						rowArray[9] += "藥效改變";
					}
					if(StringUtils.contains(dtl.getConSequence(), "2")) {
						rowArray[9] += "不良反應發生、強度增強或頻率增加";
					}
					rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));					
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}	
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getToUpdate() {return checkGet(toUpdate);}
	public void setToUpdate(String toUpdate) {this.toUpdate = checkSet(toUpdate);}
	
	
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getStatus() {return checkGet(status);}
	public void setStatus(String status) {this.status = checkSet(status);}
	public String getChargeMan() {return checkGet(chargeMan);}
	public void setChargeMan(String chargeMan) {this.chargeMan = checkSet(chargeMan);}
	public String getEnrolledDate() {return checkGet(enrolledDate);}
	public void setEnrolledDate(String enrolledDate) {this.enrolledDate = checkSet(enrolledDate);}
	public String getInOrOutcreator() {return checkGet(inOrOutcreator);}
	public void setInOrOutcreator(String inOrOutcreator) {this.inOrOutcreator = checkSet(inOrOutcreator);}
	public String getIsInOrOutPerson() {return checkGet(isInOrOutPerson);	}
	public void setIsInOrOutPerson(String isInOrOutPerson) {		this.isInOrOutPerson = checkSet(isInOrOutPerson);	}
	
	public String getOccurDate() {return checkGet(occurDate);}
	public void setOccurDate(String occurDate) {this.occurDate = checkSet(occurDate);}
	public String getNotifierRevDate() {return checkGet(notifierRevDate);}
	public void setNotifierRevDate(String notifierRevDate) {this.notifierRevDate = checkSet(notifierRevDate);}
	public String getNotifierRepDate() {return checkGet(notifierRepDate);}
	public void setNotifierRepDate(String notifierRepDate) {this.notifierRepDate = checkSet(notifierRepDate);}
	public String getNotifierSource() {return checkGet(notifierSource);}
	public void setNotifierSource(String notifierSource) {this.notifierSource = checkSet(notifierSource);}
	public String getNotifierUserID() {return checkGet(notifierUserID);}
	public void setNotifierUserID(String s) {this.notifierUserID = checkSet(s);}
	public String getNotifierName() {return checkGet(notifierName);}
	public void setNotifierName(String notifierName) {this.notifierName = checkSet(notifierName);}
	public String getNotifierDeptID() {	return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {	this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierDept() {return checkGet(notifierDept);}
	public void setNotifierDept(String notifierDept) {this.notifierDept = checkSet(notifierDept);}
	public String getNotifierTelArea() {return checkGet(notifierTelArea);}
	public void setNotifierTelArea(String notifierTelArea) {this.notifierTelArea = checkSet(notifierTelArea);}
	public String getNotifierTel() {return checkGet(notifierTel);}
	public void setNotifierTel(String notifierTel) {this.notifierTel = checkSet(notifierTel);}
	public String getNotifierTelExt() {return checkGet(notifierTelExt);}
	public void setNotifierTelExt(String notifierTelExt) {this.notifierTelExt = checkSet(notifierTelExt);}
	public String getNotifierPhone() {return checkGet(notifierPhone);}
	public void setNotifierPhone(String notifierPhone) {this.notifierPhone = checkSet(notifierPhone);}
	public String getNotifierCounty() {return checkGet(notifierCounty);}
	public void setNotifierCounty(String notifierCounty) {this.notifierCounty = checkSet(notifierCounty);}
	public String getNotifierZipCode() {return checkGet(notifierZipCode);}
	public void setNotifierZipCode(String notifierZipCode) {this.notifierZipCode = checkSet(notifierZipCode);}
	public String getNotifierAddress() {return checkGet(notifierAddress);}
	public void setNotifierAddress(String notifierAddress) {this.notifierAddress = checkSet(notifierAddress);}
	public String getNotifierEmail() {return checkGet(notifierEmail);}
	public void setNotifierEmail(String notifierEmail) {this.notifierEmail = checkSet(notifierEmail);}
	public String getNotifierFaxArea() {return checkGet(notifierFaxArea);}
	public void setNotifierFaxArea(String notifierFaxArea) {this.notifierFaxArea = checkSet(notifierFaxArea);}
	public String getNotifierFax() {return checkGet(notifierFax);}
	public void setNotifierFax(String notifierFax) {this.notifierFax = checkSet(notifierFax);}
	public String getNotifierType() {return checkGet(notifierType);}
	public void setNotifierType(String notifierType) {this.notifierType = checkSet(notifierType);}
	public String getNotifierTitle() {return checkGet(notifierTitle);}
	public void setNotifierTitle(String notifierTitle) {this.notifierTitle = checkSet(notifierTitle);}
	public String getPatientId() {return checkGet(patientId);}
	public void setPatientId(String patientId) {this.patientId = checkSet(patientId);}
	public String getPatientSex() {return checkGet(patientSex);}
	public void setPatientSex(String patientSex) {this.patientSex = checkSet(patientSex);}
	public String getPatientBirth() {return checkGet(patientBirth);}
	public void setPatientBirth(String patientBirth) {this.patientBirth = checkSet(patientBirth);}
	public String getPatientAge() {return checkGet(patientAge);}
	public void setPatientAge(String patientAge) {this.patientAge = checkSet(patientAge);}
	public String getPatientHeight() {return checkGet(patientHeight);}
	public void setPatientHeight(String patientHeight) {this.patientHeight = checkSet(patientHeight);}
	public String getPatientWeight() {return checkGet(patientWeight);}
	public void setPatientWeight(String patientWeight) {this.patientWeight = checkSet(patientWeight);}
	public String[] getConSequence() {return conSequence;}
	public void setConSequence(String[] conSequence) {this.conSequence = conSequence;}
	public String getEffectChangeDesc() {return checkGet(effectChangeDesc);}
	public void setEffectChangeDesc(String effectChangeDesc) {this.effectChangeDesc = checkSet(effectChangeDesc);}
	public String getBadReactionLev() {return checkGet(badReactionLev);}
	public void setBadReactionLev(String badReactionLev) {this.badReactionLev = checkSet(badReactionLev);}
	public String getBadReactionDesc() {return checkGet(badReactionDesc);}
	public void setBadReactionDesc(String badReactionDesc) {this.badReactionDesc = checkSet(badReactionDesc);}
	public String getBeforeDesc() {return checkGet(beforeDesc);}
	public void setBeforeDesc(String beforeDesc) {this.beforeDesc = checkSet(beforeDesc);}
	public String getChangeDesc() {return checkGet(changeDesc);}
	public void setChangeDesc(String changeDesc) {this.changeDesc = checkSet(changeDesc);}
	public String getOccurDesc() {return checkGet(occurDesc);}
	public void setOccurDesc(String occurDesc) {this.occurDesc = checkSet(occurDesc);}
	public String getAfterDesc() {return checkGet(afterDesc);}
	public void setAfterDesc(String afterDesc) {this.afterDesc = checkSet(afterDesc);}
	public String getOtherDesc() {return checkGet(otherDesc);}
	public void setOtherDesc(String otherDesc) {this.otherDesc = checkSet(otherDesc);}
	public String getDealWith() {return checkGet(dealWith);}
	public void setDealWith(String dealWith) {this.dealWith = checkSet(dealWith);}
	public String getDealWithOther() {return checkGet(dealWithOther);}
	public void setDealWithOther(String dealWithOther) {this.dealWithOther = checkSet(dealWithOther);}
	public String getIsImproveYn() {return checkGet(isImproveYn);}
	public void setIsImproveYn(String isImproveYn) {this.isImproveYn = checkSet(isImproveYn);}
	public String getIsContactYn() {return checkGet(isContactYn);}
	public void setIsContactYn(String s) {this.isContactYn = checkSet(s);}
	public String getDressingAttitude() {return checkGet(dressingAttitude);}
	public void setDressingAttitude(String dressingAttitude) {this.dressingAttitude = checkSet(dressingAttitude);}
	public String getObedienceLev() {return checkGet(obedienceLev);}
	public void setObedienceLev(String obedienceLev) {this.obedienceLev = checkSet(obedienceLev);}
	public String getMedNti() {return checkGet(medNti);}
	public void setMedNti(String s) {this.medNti = checkSet(s);}
	public String getMedAtcCode() {return checkGet(medAtcCode);}
	public void setMedAtcCode(String s) {this.medAtcCode = checkSet(s);}
	public String[] getConSequence4() {return conSequence4;}
	public void setConSequence4(String[] conSequence4) {this.conSequence4 = conSequence4;}
	public String getEffectChangeDesc4() {return checkGet(effectChangeDesc4);}
	public void setEffectChangeDesc4(String s) {this.effectChangeDesc4 = checkSet(s);}
	public String getBadReactionLev4() {return checkGet(badReactionLev4);}
	public void setBadReactionLev4(String s) {this.badReactionLev4 = checkSet(s);}
	public String getBadReactionDesc4() {return checkGet(badReactionDesc4);}
	public void setBadReactionDesc4(String s) {this.badReactionDesc4 = checkSet(s);}
	public String getBadReactionDra() {return checkGet(badReactionDra);}
	public void setBadReactionDra(String s) {this.badReactionDra = checkSet(s);}
	public String getAssessEC1() {return checkGet(assessEC1);}
	public void setAssessEC1(String s) {this.assessEC1 = checkSet(s);}
	public String getAssessEC2() {return checkGet(assessEC2);}
	public void setAssessEC2(String s) {this.assessEC2 = checkSet(s);}
	public String getAssessEC3() {return checkGet(assessEC3);}
	public void setAssessEC3(String s) {this.assessEC3 = checkSet(s);}
	public String getAssessBR1() {return checkGet(assessBR1);}
	public void setAssessBR1(String s) {this.assessBR1 = checkSet(s);}
	public String getAssessBR2() {return checkGet(assessBR2);}
	public void setAssessBR2(String s) {this.assessBR2 = checkSet(s);}
	public String getAssessBR3() {return checkGet(assessBR3);}
	public void setAssessBR3(String s) {this.assessBR3 = checkSet(s);}
	public String getAssessResult() {return checkGet(assessResult);}
	public void setAssessResult(String s) {this.assessResult = checkSet(s);}
	public String getNotifierAging() {return checkGet(notifierAging);}
	public void setNotifierAging(String s) {this.notifierAging = checkSet(s);}
	public String getNotifierQuality() {return checkGet(notifierQuality);}
	public void setNotifierQuality(String s) {this.notifierQuality = checkSet(s);}
	public String getAssessRemark() {return checkGet(assessRemark);}
	public void setAssessRemark(String s) {this.assessRemark = checkSet(s);}
	public String getIntervalDays() {return checkGet(intervalDays);}
	public void setIntervalDays(String s) {this.intervalDays = checkSet(s);}
	public String getRemark() {return checkGet(remark);}
	public void setRemark(String s) {this.remark = checkSet(s);}
	public String getIsCouncilYn() {return checkGet(isCouncilYn);}
	public void setIsCouncilYn(String s) {this.isCouncilYn = checkSet(s);}
	public String getCaseHCount() {return checkGet(caseHCount);}
	public void setCaseHCount(String s) {this.caseHCount = checkSet(s);}
	public String getCaseHPCount() {return checkGet(caseHPCount);}
	public void setCaseHPCount(String s) {this.caseHPCount = checkSet(s);}
	public String getCaseHYCount() {return checkGet(caseHYCount);}
	public void setCaseHYCount(String s) {this.caseHYCount = checkSet(s);}
	public String getCaseHPYCount() {return checkGet(caseHPYCount);}
	public void setCaseHPYCount(String s) {this.caseHPYCount = checkSet(s);}
	public String getDrg04Update() {return checkGet(drg04Update);	}
	public void setDrg04Update(String drg04Update) {this.drg04Update = checkSet(drg04Update);	}
	public String getDrg04id() {return checkGet(drg04id);	}
	public void setDrg04id(String drg04id) {this.drg04id = checkSet(drg04id);	}
	
	public String getQ_applNo() {return checkGet(q_applNo);}
	public void setQ_applNo(String s) {this.q_applNo = checkSet(s);}
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}
	public String getQ_notifierRepDateS() {return checkGet(q_notifierRepDateS);}
	public void setQ_notifierRepDateS(String s) {this.q_notifierRepDateS = checkSet(s);}
	public String getQ_notifierRepDateE() {return checkGet(q_notifierRepDateE);}
	public void setQ_notifierRepDateE(String s) {this.q_notifierRepDateE = checkSet(s);}
	public String getQ_productName() {return checkGet(q_productName);}
	public void setQ_productName(String s) {this.q_productName = checkSet(s);}
	public String getQ_occurDateS() {return checkGet(q_occurDateS);}
	public void setQ_occurDateS(String s) {this.q_occurDateS = checkSet(s);}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);}
	public void setQ_occurDateE(String s) {this.q_occurDateE = checkSet(s);}
	public String getQ_enrolledDateS() {return checkGet(q_enrolledDateS);}
	public void setQ_enrolledDateS(String s) {this.q_enrolledDateS = checkSet(s);}
	public String getQ_enrolledDateE() {return checkGet(q_enrolledDateE);}
	public void setQ_enrolledDateE(String s) {this.q_enrolledDateE = checkSet(s);}
	public String getQ_notifierSource() {return checkGet(q_notifierSource);}
	public void setQ_notifierSource(String s) {this.q_notifierSource = checkSet(s);}
	public String getQ_notifierDept() {return checkGet(q_notifierDept);}
	public void setQ_notifierDept(String s) {this.q_notifierDept = checkSet(s);}
	public String getQ_scientificName() {return checkGet(q_scientificName);}
	public void setQ_scientificName(String s) {this.q_scientificName = checkSet(s);}
	public String getQ_applicationName() {return checkGet(q_applicationName);}
	public void setQ_applicationName(String s) {this.q_applicationName = checkSet(s);}
	public String getQ_manufactorName() {return checkGet(q_manufactorName);}
	public void setQ_manufactorName(String s) {this.q_manufactorName = checkSet(s);}
	public String[] getQ_conSequence() {return q_conSequence;}
	public void setQ_conSequence(String[] s) {this.q_conSequence = s;}
	public String getQ_medNti() {return checkGet(q_medNti);}
	public void setQ_medNti(String s) {this.q_medNti = checkSet(s);}
	public String getQ_medAtcCode() {return checkGet(q_medAtcCode);}
	public void setQ_medAtcCode(String s) {this.q_medAtcCode = checkSet(s);}
	public String getQ_assessResult() {return checkGet(q_assessResult);}
	public void setQ_assessResult(String s) {this.q_assessResult = checkSet(s);}
	public String getQ_status() {return checkGet(q_status);}
	public void setQ_status(String s) {this.q_status = checkSet(s);}

	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}
}
