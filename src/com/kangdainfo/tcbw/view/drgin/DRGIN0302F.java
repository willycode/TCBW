package com.kangdainfo.tcbw.view.drgin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4005Db;
import com.kangdainfo.tcbw.model.bo.Drg4006Db;
import com.kangdainfo.tcbw.model.bo.Drg4007Db;
import com.kangdainfo.tcbw.model.bo.Drg4008Db;
import com.kangdainfo.tcbw.model.bo.Drg4009Db;
import com.kangdainfo.tcbw.model.bo.Drg4010Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.model.bo.Drg6002Db;
import com.kangdainfo.tcbw.model.bo.Drg6003Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0302F extends DRGIN0301F {

	javax.servlet.ServletRequest httpRequest;

	public javax.servlet.ServletRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(javax.servlet.ServletRequest r) {
		this.httpRequest = r;
	}

	private String applNo; // 案件號碼 VARCHAR(11)
	private String status; // 案件狀態 VARCHAR(2)
	private String chargeMan; // 作業人員 VARCHAR(50)
	private String enrolledDate; // 收案日期 VARCHAR(7)
	private String inOrOutcreator; // 案件擁有者 VARCHAR(50)

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
	
	private String isCloseUserInfo;		//是否遮蔽個資

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

	// 歷史通報查詢條件
	private String q_notifierRepDate41S, q_notifierRepDate41E;
	private String q_conSequence41;
	private String q_notifierDept41;
	private String q_gradeDate;
	String[] id41s;

	public String[] getId41s() {return id41s;}
	public void setId41s(String[] id41s) {this.id41s = id41s;}
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

	public String getQ_notifierRepDate41S() {return checkGet(q_notifierRepDate41S);}
	public void setQ_notifierRepDate41S(String s) {this.q_notifierRepDate41S = checkSet(s);}
	public String getQ_notifierRepDate41E() {return checkGet(q_notifierRepDate41E);}
	public void setQ_notifierRepDate41E(String s) {this.q_notifierRepDate41E = checkSet(s);}
	public String getQ_conSequence41() {return checkGet(q_conSequence41);}
	public void setQ_conSequence41(String s) {this.q_conSequence41 = checkSet(s);}
	public String getQ_notifierDept41() {return checkGet(q_notifierDept41);}
	public void setQ_notifierDept41(String s) {this.q_notifierDept41 = checkSet(s);}
	public String getQ_gradeDate() {return checkGet(q_gradeDate);}
	public void setQ_gradeDate(String s) {this.q_gradeDate = checkSet(s);}
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}

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

	public DefaultTableModel getDefaultTableModel(List<Drg4001Db> drg41List) throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		String[] cols = new String[] { "id", "applNo", "status", "chargeMan", //0~3
				"occurDate", "notifierRevDate", "notifierRepDate",	//4~6
				"notifierSource", "notifierName", "notifierDept",	//7~9
				"notifierTel", "address", "notifierEamil", "notifierFax",	//10~13
				"notifierType", "notifierTitle", "patientId", "patientSex",	//14~17
				"patientBDate", "patientAge", "patientHeight", "patientWeight",	//18~21
				"conSequence", "beforeDesc", "changeDesc", "occurDesc",	//22~25
				"afterDesc", "otherDesc","permitKey1",  //26~28
				"scientificName1", "productName1", "applicationName1",	//29~31
				"manufactorName1", "manufactorNo1", "content1", "medModel1",	//32~35
				"medPath1", "dosage1", "frequency1", "startDare1", "endDate1",	//36~40
				"indication1", "permitKey2","scientificName2", //41~43
				"productName2", "applicationName2", "manufactorName2",	//44~46
				"manufactorNo2", "content2", "medModel2", "medPath2",	//47~50
				"dosage2", "frequency2", "startDare2", "endDate2",	//51~54
				"indication2","obj1","detail1","detail2","isprint1","isprint2",	//55~60
				"permitKey3","scientificName3","productName3", "applicationName3",//61~64
				"manufactorName3","manufactorNo3", "content3", "medModel3", "medPath3",	//65~69
				"dosage3", "frequency3", "startDare3", "endDate3",	//70~73
				"indication3","dealWith","isImproveYn","dressingAttitude","obedienceLev"//74~78

		};
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		java.util.List list = null;
		if(null == drg41List || drg41List.isEmpty()){
			String hql = "from Drg4001Db where 1=1 ";

			if (!"".equals(getId()))
				hql += "and id = " + Common.getLong(getId());
			if (!"".equals(getApplNo()))
				hql += "and applNo = " + Common.sqlChar(getApplNo());
			System.out.println("HQL: " + hql);
			list = ServiceGetter.getInstance().getCommonService().load(hql);
		}else{
			list = drg41List;
		}
		
		int dt1 = 0;
		int dt2 = 0;
		
		if (list != null && list.size() > 0) {
			java.util.Map<String, String> medModelMap = TCBWCommon.getCommonCodeMap("DRGFLN");

			for (int i = 0; i < list.size(); i++) {
				Drg4001Db obj = (Drg4001Db) list.get(i);
				Object rowArray[] = new Object[cols.length];

				rowArray[0] = Common.get(obj.getId());
				rowArray[1] = Common.get(obj.getApplNo());
				rowArray[2] = Common.get(obj.getStatus());
				rowArray[3] = Common.get(obj.getChargeMan());
				rowArray[4] = Common.formatYYYMMDD(obj.getOccurDate(), 2);
				rowArray[5] = Common.formatYYYMMDD(obj.getNotifierRevDate(), 2);
				rowArray[6] = Common.formatYYYMMDD(obj.getNotifierRepDate(), 2);
				rowArray[7] = getNotifierSource(Common.get(obj.getNotifierSource()));
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierName())))) {
					rowArray[8]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getNotifierName());
				} else {
					rowArray[8]=Common.get(obj.getNotifierName());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierDept())))) {
					rowArray[9]="●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getNotifierDept());
				} else {
					rowArray[9]=Common.get(obj.getNotifierDept());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierTel())))) {
					rowArray[10]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[10]=Common.get(obj.getNotifierTel());
				}

				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAddress())))) {
					rowArray[11]="●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[11]=Common.get(obj.getNotifierAddress());
				} else {
					rowArray[11]=Common.get(obj.getNotifierAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierEmail())))) {
					rowArray[12]="●●●●●●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[12]=Common.get(obj.getNotifierEmail());
				} else {
					rowArray[12]=Common.get(obj.getNotifierEmail());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierFax())))) {
					rowArray[13]="●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[13]=Common.get(obj.getNotifierFax());
				} else {
					rowArray[13]=Common.get(obj.getNotifierFax());
				}

				
				rowArray[14] = Common.get(obj.getNotifierType());
				rowArray[15] = getNotifierTitle(Common.get(obj.getNotifierTitle()));
				rowArray[16] = Common.get(obj.getPatientId());
				if ("M".equals(Common.get(obj.getPatientSex()))) {
					rowArray[17] = "■男　□女";
				} else if ("F".equals(Common.get(obj.getPatientSex()))) {
					rowArray[17] = "□男　■女";
				} else {
					rowArray[17] = "□男　□女";
				}

				rowArray[18] = Common.formatYYYMMDD(obj.getPatientBirth());
				rowArray[19] = Common.get(obj.getPatientAge());
				rowArray[20] = Common.get(obj.getPatientHeight());
				rowArray[21] = Common.get(obj.getPatientWeight());				

				if ("1".equals(Common.get(obj.getConSequence()))) {
					String effectChangeDesc = "";
					if ("1".equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "■增強　□減弱";
					} else if ("2"
							.equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "□增強　■減弱";
					} else {
						effectChangeDesc += "□增強　□減弱";
					}
					rowArray[22] = "■藥效改變 -- "
							+ effectChangeDesc
							+ "\n□不良反應發生、強度增加或頻率增加(不良反應：_____)\n--□嚴重不良反應　□非嚴重不良反應\n--□可預期不良反應　□非預期不良反應";
				} else if ("2".equals(Common.get(obj.getConSequence()))) {
					String effectChangeDesc = "□增強　□減弱";
					String badReactionLev = "";
					badReactionLev = getBadReactionLev(badReactionLev,
							Common.get(obj.getBadReactionLev()));
					String badReactionDesc = "";
					if (!"".equals(Common.get(obj.getBadReactionDesc()))) {
						badReactionDesc += "(不良反應："
								+ Common.get(obj.getBadReactionDesc()) + ")";
					} else {
						badReactionDesc += "(不良反應：______)";
					}
					rowArray[22] = "□藥效改變 -- " + effectChangeDesc
							+ "\n■不良反應發生、強度增加或頻率增加　" + badReactionDesc + "\n"
							+ badReactionLev;
				} else if(obj.getConSequence().length() > 1) {  //通報事件之後果：藥效改變與不良反應發生同時被勾選
					String effectChangeDesc = "■藥效改變 -- ";
					if ("1".equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "■增強　□減弱";
					} else if ("2"
							.equals(Common.get(obj.getEffectChangeDesc()))) {
						effectChangeDesc += "□增強　■減弱";
					} else {
						effectChangeDesc += "□增強　□減弱";
					}
					effectChangeDesc += "\n■不良反應發生、強度增加或頻率增加";
					String badReactionDesc = "";
					if (!"".equals(Common.get(obj.getBadReactionDesc()))) {
						badReactionDesc += "(不良反應："
								+ Common.get(obj.getBadReactionDesc()) + ")";
					} else {
						badReactionDesc += "(不良反應：______)";
					}
					String badReactionLev = "";
					badReactionLev = getBadReactionLev(badReactionLev,
							Common.get(obj.getBadReactionLev()));
					effectChangeDesc += badReactionDesc + "\n" + badReactionLev;
					
					rowArray[22] = effectChangeDesc;
				}
				else {
					rowArray[22] = "□　藥效改變 -- □增強　□減弱\n□　不良反應發生、強度增加或頻率增加　(不良反應：________)\n" +
					"--□嚴重不良反應　□非嚴重不良反應\n--□可預期不良反應　□非預期不良反應";
				}

				rowArray[23] = Common.get(obj.getBeforeDesc());
				rowArray[24] = Common.get(obj.getChangeDesc());
				rowArray[25] = Common.get(obj.getOccurDesc());
				rowArray[26] = Common.get(obj.getAfterDesc());
				rowArray[27] = Common.get(obj.getOtherDesc());
//				rowArray[28] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
//				if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "■是　□否　□不知";
//				} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "□是　■否　□不知";
//				} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
//					rowArray[29] = "□是　□否　■不知";
//				} else {
//					rowArray[29] = "□是　□否　□不知";
//				}
//				
//				rowArray[30] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
//				rowArray[31] = getObedienceLev(Common.get(obj.getObedienceLev()));
				
				if(obj.getDrg4003Dbs()!=null && obj.getDrg4003Dbs().size()>0) {
					java.util.Iterator it2 = obj.getDrg4003Dbs().iterator();
					while(it2.hasNext())
					{
						Drg4003Db drg4003Db = (Drg4003Db)it2.next();
						if("01".equals(Common.get(drg4003Db.getMedType()))) {
							if(getPermitKey(Common.get(drg4003Db.getPermitKey())).length() > 0 ) {
								rowArray[28]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字";
								if(Common.get(drg4003Db.getPermitNo()).length() > 0) {
									rowArray[28]=rowArray[28]+"第"+Common.get(drg4003Db.getPermitNo())+"號";
								}
							} else {
								rowArray[28]="";
							}
//							rowArray[28]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字"+"第"+Common.get(drg4003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[29]=Common.get(drg4003Db.getScientificName());
							rowArray[30]=Common.get(drg4003Db.getProductName());
							rowArray[31]=Common.get(drg4003Db.getApplicationName());
							rowArray[32]=Common.get(drg4003Db.getManufactorName());
							rowArray[33]=Common.get(drg4003Db.getManufactorNo());
							rowArray[34]=Common.get(drg4003Db.getContent());
							rowArray[35]=Common.get(medModelMap.get(drg4003Db.getMedModel()));
							rowArray[36]=Common.get(drg4003Db.getMedPath());
							rowArray[37]=Common.get(drg4003Db.getDosage());
							rowArray[38]=Common.get(drg4003Db.getFrequency());
							rowArray[39]=Common.get(drg4003Db.getStartDare());
							rowArray[40]=Common.get(drg4003Db.getEndDate());
							rowArray[41]=Common.get(drg4003Db.getIndication());
						}
						if("02".equals(Common.get(drg4003Db.getMedType()))) {
							String permitKey1=getPermitKey(Common.get(drg4003Db.getPermitKey()));
							rowArray[42]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字第"+ Common.get(drg4003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[43]=Common.get(drg4003Db.getScientificName());
							rowArray[44]=Common.get(drg4003Db.getProductName());
							rowArray[45]=Common.get(drg4003Db.getApplicationName());
							rowArray[46]=Common.get(drg4003Db.getManufactorName());
							rowArray[47]=Common.get(drg4003Db.getManufactorNo());
							rowArray[48]=Common.get(drg4003Db.getContent());
							rowArray[49]=Common.get(medModelMap.get(drg4003Db.getMedModel()));
							rowArray[50]=Common.get(drg4003Db.getMedPath());
							rowArray[51]=Common.get(drg4003Db.getDosage());
							rowArray[52]=Common.get(drg4003Db.getFrequency());
							rowArray[53]=Common.get(drg4003Db.getStartDare());
							rowArray[54]=Common.get(drg4003Db.getEndDate());
							rowArray[55]=Common.get(drg4003Db.getIndication());
						}
						if("03".equals(Common.get(drg4003Db.getMedType()))) {
							String permitKey1=getPermitKey(Common.get(drg4003Db.getPermitKey()));
							rowArray[61]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字第"+ Common.get(drg4003Db.getPermitNo())+"號";//許可證字+許可證號
							rowArray[62]=Common.get(drg4003Db.getScientificName());
							rowArray[63]=Common.get(drg4003Db.getProductName());
							rowArray[64]=Common.get(drg4003Db.getApplicationName());
							rowArray[65]=Common.get(drg4003Db.getManufactorName());
							rowArray[66]=Common.get(drg4003Db.getManufactorNo());
							rowArray[67]=Common.get(drg4003Db.getContent());
							rowArray[68]=Common.get(medModelMap.get(drg4003Db.getMedModel()));
							rowArray[69]=Common.get(drg4003Db.getMedPath());
							rowArray[70]=Common.get(drg4003Db.getDosage());
							rowArray[71]=Common.get(drg4003Db.getFrequency());
							rowArray[72]=Common.get(drg4003Db.getStartDare());
							rowArray[73]=Common.get(drg4003Db.getEndDate());
							rowArray[74]=Common.get(drg4003Db.getIndication());
						}
						else {
							rowArray[61]="";//許可證字+許可證號
							rowArray[62]="";
							rowArray[63]="";
							rowArray[64]="";
							rowArray[65]="";
							rowArray[66]="";
							rowArray[67]="";
							rowArray[68]="";
							rowArray[69]="";
							rowArray[70]="";
							rowArray[71]="";
							rowArray[72]="";
							rowArray[73]="";
							rowArray[74]="";
						}
						rowArray[75] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
						if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "■是　□否　□不知";
						} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "□是　■否　□不知";
						} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
							rowArray[76] = "□是　□否　■不知";
						} else {
							rowArray[76] = "□是　□否　□不知";
						}
						
						rowArray[77] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
						rowArray[78] = getObedienceLev(Common.get(obj.getObedienceLev()));
						
						//16.其他併用藥品
//						if("03".equals(Common.get(drg4003Db.getMedType()))) {
//							java.util.Iterator it3 = obj.getDrg4003Dbs().iterator();
//							rowArray[61] = new JRTableModelDataSource(getSubModel02(it3));
//						}
					}
				}
				
				if(obj.getDrg4002Dbs()!=null && obj.getDrg4002Dbs().size()>0) {
					java.util.Iterator it2 = obj.getDrg4002Dbs().iterator();
					rowArray[56] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[56]=null;
				}
//				rowArray[57] = new JRTableModelDataSource(getDetail1Model(obj));
//				rowArray[58] = new JRTableModelDataSource(getDetail2Model(obj));
//				rowArray[59] = "tmp" + dt1;
//				rowArray[60] = "tmp" + dt2;
				rowArray[57] = "";
				rowArray[58] = "";
				rowArray[59] = "";
				rowArray[60] = "";
				arrList.add(rowArray);
			}
		}
		if (arrList != null && arrList.size() > 0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][]) arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
	}
	//子報表路徑設定
	public void setParameter(java.util.HashMap<String, Object> params)
	{
		//傳給報表的查詢條件參數,在此設定
		String detail1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_detail1.jasper");
		String detail2FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_detail2.jasper");
		String subreport0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_subreport0.jasper");
		String subreport1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/drg/DRGIN0302tir_subreport1.jasper");
		
		params.put("detail1", detail1FilePath);
		params.put("detail2", detail2FilePath);
		params.put("subreport0", subreport0FilePath);			
		params.put("subreport1", subreport1FilePath);
	}
	
	//16.其他併用藥品
	public DefaultTableModel getDetail1Model(Drg4001Db obj) throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{"obj2"};

		Object[] rowArray = new Object[cols.length];
		
		if(obj.getDrg4003Dbs()!=null && obj.getDrg4003Dbs().size()>0) {
			java.util.Iterator it3 = obj.getDrg4003Dbs().iterator();
			rowArray[0] = new JRTableModelDataSource(getSubModel02(it3));
//			java.util.Iterator it2 = obj.getDrg4003Dbs().iterator();
//			while(it2.hasNext())
//			{
//				Drg4003Db drg4003Db = (Drg4003Db)it2.next();
//				if("03".equals(Common.get(drg4003Db.getMedType()))) {
//					
//					
//				}
//			}
			
		} else {
			rowArray[0] = null;
		}
		arrList.add(rowArray);
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, cols);
		}
		System.out.println("其他併用藥品： " + model.getDataVector());
		return model;
	}
	//Ⅳ.其他相關資訊
	public DefaultTableModel getDetail2Model(Drg4001Db obj) throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{"dealWith","isImproveYn","dressingAttitude","obedienceLev"};

		Object[] rowArray = new Object[cols.length];
		
		rowArray[0] = getDealWith(Common.get(obj.getDealWith()),Common.get(obj.getDealWithOther()));
		if ("Y".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "■是　□否　□不知";
		} else if ("N".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "□是　■否　□不知";
		} else if ("0".equals(Common.get(obj.getIsImproveYn()))) {
			rowArray[1] = "□是　□否　■不知";
		} else {
			rowArray[1] = "□是　□否　□不知";
		}
		
		rowArray[2] = getDressingAttitude(Common.get(obj.getDressingAttitude()));
		rowArray[3] = getObedienceLev(Common.get(obj.getObedienceLev()));
		arrList.add(rowArray);
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, cols);
		}
//		System.out.println("其他相關資訊： " + model.getDataVector());
		return model;
	}
	//子報表-相關檢查,檢驗數據及其他資料
	public DefaultTableModel getSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"testDate","testItems","testNum"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Drg4002Db drg4002Db = (Drg4002Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(drg4002Db.getTestDate(),2);
			rowArray[1]=Common.get(drg4002Db.getTestItems());
			rowArray[2]=Common.get(drg4002Db.getTestNum());
			
			arrList.add(rowArray);
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//子報表-其他併用藥品
	public DefaultTableModel getSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{ "permitKey","scientificName",
				"productName", "applicationName", "manufactorName",
				"manufactorNo", "content", "medModel", "medPath",
				"dosage", "frequency", "startDare", "endDate",
				"indication"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Drg4003Db drg4003Db = (Drg4003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=getPermitKey(Common.get(drg4003Db.getPermitKey()))+"字第"+ Common.get(drg4003Db.getPermitNo())+"號";//許可證字+許可證號
			rowArray[1]=Common.get(drg4003Db.getScientificName());
			rowArray[2]=Common.get(drg4003Db.getProductName());
			rowArray[3]=Common.get(drg4003Db.getApplicationName());
			rowArray[4]=Common.get(drg4003Db.getManufactorName());
			rowArray[5]=Common.get(drg4003Db.getManufactorNo());
			rowArray[6]=Common.get(drg4003Db.getContent());
			rowArray[7]=Common.get(drg4003Db.getMedModel());
			rowArray[8]=Common.get(drg4003Db.getMedPath());
			rowArray[9]=Common.get(drg4003Db.getDosage());
			rowArray[10]=Common.get(drg4003Db.getFrequency());
			rowArray[11]=Common.get(drg4003Db.getStartDare());
			rowArray[12]=Common.get(drg4003Db.getEndDate());
			rowArray[13]=Common.get(drg4003Db.getIndication());
			
			if("03".equals(drg4003Db.getMedType())){
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
	        System.out.println("其他併用藥品： " + model.getDataVector());
		}
        return model;
	}	

	// Common_CodeKind查詢條件
	public String getCommonCodeKindHQL(String codeKindId) {
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId
				+ "'";
		return HQL;
	}

	// 通報來源
	public String getNotifierSource( String NotifierSource) {
		String hql = getCommonCodeKindHQL("26");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(NotifierSource)) {
					checkbox = "■" + obj.getCodeName();

				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				if ("03".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";

				}

			}
		}
		return rowArray2;
	}

	// 通報者職稱
	public String getNotifierTitle(String NotifierTitle) {
		String hql = getCommonCodeKindHQL("2");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(NotifierTitle)) {
					checkbox = "■" + obj.getCodeName();

				} else {
					checkbox = checkbox + obj.getCodeName();
				}

				if ("09".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";
				}

			}
		}
		return rowArray2;
	}

	// 不良反應等級
	public String getBadReactionLev(String rowArray2, String BadReactionLev) {
		String hql = getCommonCodeKindHQL("101");
		rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (i == 0 || i == 2) {
					checkbox = "--" + checkbox;
				}

				if (obj.getCodeId().equals(BadReactionLev)) {
					checkbox = "■" + obj.getCodeName();
					if (i == 0 || i == 2) {
						checkbox = "--" + checkbox;
					}
					if (i == 1 || i == 3) {
						checkbox += "\n";
					}
				} else {
					checkbox = checkbox + obj.getCodeName();

					if (i == 1 || i == 3) {
						checkbox += "\n";
					}
				}

				if ("02".equals(obj.getCodeId())) {
					rowArray2 += checkbox;
				} else {
					rowArray2 += checkbox + "　";
				}
			}
		}
		return rowArray2;
	}

	// 發生事件後之處置
	public String getDealWith(String DealWith,String DealwithOther) {
		String hql = getCommonCodeKindHQL("35");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService()
				.load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(DealWith)) {
					checkbox = "■" + obj.getCodeName();
					if ("ZZ".equals(obj.getCodeId())
							&& !"".equals(DealwithOther)) {
						checkbox += DealwithOther;
					} else if ("ZZ".equals(obj.getCodeId())) {
						checkbox += "_____";
					}
				} else {
					checkbox = checkbox + obj.getCodeName();
					if ("ZZ".equals(obj.getCodeId())) {
						checkbox += "_____";
					}
				}

				if ("05".equals(obj.getCodeId())) {
					rowArray2 += checkbox + "\n";
				} else {
					rowArray2 += checkbox + "　";
				}
			}
		}
		return rowArray2;
	}

	// 醫師對換藥的態度
	public String getDressingAttitude(String DressingAttitude) {
		String hql = getCommonCodeKindHQL("36");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(DressingAttitude)) {
					checkbox = "■" + obj.getCodeName();
				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox + "　";
			}
		}
		return rowArray2;
	}

	// 病人服藥順從性
	public String getObedienceLev(String ObedienceLev) {
		String hql = getCommonCodeKindHQL("37");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(ObedienceLev)) {
					checkbox = "■" + obj.getCodeName();
				} else {
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox + "　";
			}
		}
		return rowArray2;
	}
	
	//許可證字
	public String getPermitKey(String Permitkey) {
		String hql = getCommonCodeKindHQL("25");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";
				if (obj.getCodeId().equals(Permitkey)) {
					checkbox = obj.getCodeName();
				}
				rowArray2 += checkbox;
			}
		}
//		System.out.println("PK: " + rowArray2);
		return rowArray2;
	}

	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0302F obj = this;
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
				obj.setQ_gradeDate(drg04.getGradeDate());
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
		return obj;
	}
	
	public void doDelete() throws Exception {
	}

	// 檢查是否有分派權限
	public String competenceDrg0102() throws Exception {

		String hql = "  from Con1015Db ";
		hql += " where con1014Db.code = '01' "; // 審核
		hql += " and   con1014Db.con1007Db.formCode= 'DRG02'";
		hql += " and   competence like '%1%'";
		hql += " and   commonUser.userId="
				+ Common.sqlChar(TCBWCommon.getCurrentUserId());

		Con1015Db c = (Con1015Db) View.getObject(hql);

		if (c != null) {
			return "Y";
		} else {
			return null;
		}
	}

	public Object[] setUpdateData(Drg4001Db obj){
		Object[] actionObj = new Object[3];
		List delList = new ArrayList();
		List updList = new ArrayList();
		if (obj != null) {
			try {
				obj.setChargeMan(TCBWCommon.getCurrentUserId());
				// 通報訊息
				//obj.setOccurDate(getOccurDate()); // 通報日期
				//obj.setNotifierRepDate(getNotifierRepDate()); // 通報者接獲日期
				//obj.setNotifierRevDate(getNotifierRevDate()); // 通報中心接獲日期
				//obj.setNotifierSource(getNotifierSource()); // 原始藥物不良事件獲知來源

				// 通報者資訊
				//obj.setNotifierName(getNotifierName()); // 姓名
				//obj.setNotifierDept(getNotifierDept()); // 服務機構
				//obj.setNotifierTel(getNotifierTel()); // 電話
				//obj.setNotifierEmail(getNotifierEmail()); // E-Mail
				//obj.setNotifierPhone(getNotifierPhone()); // 手機
				//obj.setNotifierFax(getNotifierFax()); // 傳真
				//obj.setNotifierAddress(getNotifierAddress()); // 地址
				//obj.setNotifierTitle(getNotifierTitle()); // 職稱
				//obj.setNotifierType(getNotifierType()); // 屬性

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
				if(null != obj.getApplNo() && !"".equals(obj.getApplNo())){
					Drg4004Db drg44 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo="+ Common.sqlChar(obj.getApplNo()));
					if (null != drg44) {
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
						updList.add(drg44);
					}
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		actionObj[0] = obj;
		actionObj[1] = updList;
		actionObj[2] = delList;
		return actionObj;
	}
	public void doUpdateCase(){
		try {
			Drg4001Db obj = (Drg4001Db) View.getObject(" from Drg4001Db where id = " + Common.getLong(getId()));
			if (obj != null) {
				Object[] actionObj = setUpdateData(obj);
				if (null != actionObj[0]) {
					ServiceGetter.getInstance().getTcbwService().update(actionObj[0]);
				}
				if (null != actionObj[1]) {
					ServiceGetter.getInstance().getTcbwService().updateBatch((List)actionObj[1]);
				}
				if (null != actionObj[2]) {
					ServiceGetter.getInstance().getCommonService().deleteBatch((List)actionObj[2]);
				}

				setId(Common.get(obj.getId()));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public java.util.ArrayList<String[]> doQueryAllDrg48(String drg4001Id)throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from " + Drg4005Db.class.getCanonicalName()	+ " where 1=1 ";
		if (null != drg4001Id && !"".equals(drg4001Id)) {
			hql += " and id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = " + Common.sqlChar(drg4001Id) + ")";
		}
		System.out.println("[TCBW]-[DRGEX0302F]-[objList48-QUERYALL] : " + hql);
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
			if (objList != null && objList.size() > 0) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Drg4005Db o = (Drg4005Db) it.next();
					String rowArray[] = new String[5];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getAssessDate());
					rowArray[2] = "";
					if (null != o.getAssessResult()	&& !"".equals(o.getAssessResult())) {
						StringBuffer s = new StringBuffer();
						for (String result : o.getAssessResult().split(",")) {
							if (s.toString().length() > 0)
								s.append(",");
							s.append(View.getCommonCodeName("DRG0309", result));
						}
						if (s.toString().length() > 0) {
							rowArray[2] = s.toString();
						}
					}
					rowArray[3] = Common.get(o.getAssessDesc());
					rowArray[4] = "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"popDRG4008View('"+ o.getId()+ "','1');\"> "
							+ "<input type='button' class='toolbar_default' name='btn_Data' value='醫 院 調 查' onClick=\"popDRG4008View('"+ o.getId() + "','2');\"> ";
					if (null != o.getDrg4009Dbs()&& !o.getDrg4009Dbs().isEmpty()	&& !"Y".equals(o.getIsMedSend())) {
						rowArray[4] += "<input type='button' class='toolbar_default' name='btn_Data' value='發 送 詢 問 函' onClick=\"sendDRG45('" + o.getId() + "');\"> ";
					}
					if (!"Y".equals(o.getIsClose())) {
						rowArray[4] += "<input type='button' class='toolbar_default' name='btn_Data' value='評 估 完 成' onClick=\"popDRG4008View('" + o.getId() + "','1');\"> ";
					}
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	public java.util.ArrayList<String[]> doQueryAllDrg49(String drg4001Id) throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = "select ingredient, drg4005Db.id , count(id) from Drg4009Db where 1=1 ";
		if (null != drg4001Id && !"".equals(drg4001Id)) {
			hql += " and drg4005Db.id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = "+ Common.sqlChar(drg4001Id) + ")";
		}
		hql += " group by ingredient,drg4005Db.id ";
		hql += " order by ingredient,drg4005Db.id ";
		System.out.println("[TCBW]-[DRGEX0302F]-[objList49-QUERYALL] : " + hql);
		List objList = ServiceGetter.getInstance().getTcbwService().load(hql);

		this.setPageSize(objList.size());
		this.processCurrentPageAttribute(objList.size());
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			if (objList != null && objList.size() > 0) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Object[] o = (Object[]) it.next();
					String rowArray[] = new String[6];
					rowArray[0] = Common.get(o[0]);
					rowArray[1] = "<a href='#' class='sLink2' onClick=\"popShowDrg49('"+ o[0]+ "','"+ o[1] + "')\">" + Common.get(o[0]) + "</a>";
					// 統計有療效不等狀況
					hql = " from Drg4009Db where isEffectChange = 'Y'  and (replyDate is not null or replyDate <> '') and ingredient = "+ Common.sqlChar(Common.get(o[0]));
					if (null != drg4001Id && !"".equals(drg4001Id)) {
						hql += " and drg4005Db.id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = "+ Common.sqlChar(drg4001Id) + ")";
					}
					rowArray[2] = Common.get(ServiceGetter.getInstance().getTcbwService().loadCount(hql))+ "件";

					// 統計有更換廠牌，無療效不等狀況
					hql = " from Drg4009Db where isBrandChange = 'Y'  and (replyDate is not null or replyDate <> '') and ingredient = " + Common.sqlChar(Common.get(o[0]));
					if (null != drg4001Id && !"".equals(drg4001Id)) {
						hql += " and drg4005Db.id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = " + Common.sqlChar(drg4001Id) + ")";
					}
					rowArray[3] = Common.get(ServiceGetter.getInstance().getTcbwService().loadCount(hql)) + "件";

					// 統計未更換廠牌
					hql = " from Drg4009Db where noBrandChange = 'Y'  and (replyDate is not null or replyDate <> '') and ingredient = "	+ Common.sqlChar(Common.get(o[0]));
					if (null != drg4001Id && !"".equals(drg4001Id)) {
						hql += " and drg4005Db.id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = "	+ Common.sqlChar(drg4001Id) + ")";
					}
					rowArray[4] = Common.get(ServiceGetter.getInstance().getTcbwService().loadCount(hql)) + "件";

					// 統計廠商家數
					rowArray[5] = "";
					hql = " select count(DISTINCT con1009Db.id) from Drg4009Db where 1 = 1 and (replyDate is not null or replyDate <> '')";
					if (null != drg4001Id && !"".equals(drg4001Id)) {
						hql += " and drg4005Db.id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = "	+ Common.sqlChar(drg4001Id) + ")";
					}
					rowArray[5] = "總共" + Common.get(View.getLookupField(hql))	+ "家";
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	public java.util.ArrayList<String[]> doQueryAllDrg46(String drg4001Id)throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from " + Drg4006Db.class.getCanonicalName()+ " where 1=1 ";
		if (null != drg4001Id && !"".equals(drg4001Id)) {
			hql += " and id in (select drg4006Db.id from Drg4010Db where drg4001Db.id = "+ Common.sqlChar(drg4001Id) + ")";
		}
		System.out.println("[TCBW]-[DRGEX0302F]-[objList46-QUERYALL] : " + hql);
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
			if (objList != null && objList.size() > 0) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Drg4006Db o = (Drg4006Db) it.next();
					String rowArray[] = new String[8];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getReplyDate());
					rowArray[2] = Common.get(o.getReview1());
					rowArray[3] = Common.get(o.getReview2());
					rowArray[4] = "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"popDRG4006View('"+ o.getId() + "');\"> ";
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	public java.util.ArrayList<String[]> doQueryAllDrg47(String drg4001Id)
			throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from " + Drg4007Db.class.getCanonicalName()+ " where 1=1 ";
		if (null != drg4001Id && !"".equals(drg4001Id)) {
			hql += " and id in (select drg4007Db.id from Drg4011Db where drg4001Db.id  = "+ Common.sqlChar(drg4001Id) + ")";
		}
		System.out.println("[TCBW]-[DRGEX0302F]-[objList47-QUERYALL] : " + hql);
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(),this.getPageSize());
			if (objList != null && objList.size() > 0) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Drg4007Db o = (Drg4007Db) it.next();
					String rowArray[] = new String[5];
					rowArray[0] = Common.get(o.getId());
					if(null != o.getPermitKey2() && !"".equals(o.getPermitKey2()) && null != o.getPermitNo2() && !"".equals(o.getPermitNo2())){
						rowArray[1] =  Common.get(View.getCommonCodeName("DRGPKID", o.getPermitKey2()))+"第"+Common.get(o.getPermitNo2())+"號";
					}
					rowArray[2] = Common.get(o.getAssessDate());
					rowArray[3] = Common.get(o.getAssessDesc());
					rowArray[4] = "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"popDRG4007View('"
							+ o.getId() + "');\"> ";
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	public java.util.ArrayList<String[]> doQueryAllDrg41(String drg4001Id,boolean isLink) throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from " + Drg4001Db.class.getCanonicalName()
		+ " where 1=1 and applNo is not null and applNo <> '' and id <> " + drg4001Id
		+ " and status in ('30','40','50','90')";
		
		if (null != getQ_gradeDate() && !"".equals(getQ_gradeDate())){
			hql += " and applNo in (select applNo from Drg4004Db where gradeDate <= " + Common.sqlChar(getQ_gradeDate())+")";
		}
		if (null != drg4001Id && !"".equals(drg4001Id)) {
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType ='02' and permitKey+permitNo in (select permitKey+permitNo from Drg4003Db where medType ='02' and drg4001Db.id = "+ drg4001Id + "))";
		}
		if (!"".equals(getQ_notifierRepDate41S())) {
			hql += " and notifierRepDate >= "+ Common.sqlChar(getQ_notifierRepDate41S());
		}
		if (!"".equals(getQ_notifierRepDate41E())) {
			hql += " and notifierRepDate <= "+ Common.sqlChar(getQ_notifierRepDate41E());
		}
		if (!"".equals(getQ_conSequence41())) {
			hql += " and conSequence = " + Common.sqlChar(getQ_conSequence41());
		}
		if (!"".equals(getQ_notifierDept41())) {
			hql += " and notifierDept = "+ Common.sqlChar(getQ_notifierDept41());
		}
		if (null != getId41s() && !"".equals(getId41s())) {
			String ids = "";
			for (String id : getId41s()) {
				if (ids.length() > 0)	ids += ",";
				ids += id;
			}
			hql += " and id in (" + ids + ")";
		}
		System.out.println("[TCBW]-[DRGEX0302F]-[objList41-QUERYALL] : " + hql);
		int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by applNo", this.getRecordStart(),this.getPageSize());
			if (objList != null && objList.size() > 0) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Drg4001Db o = (Drg4001Db) it.next();
					String rowArray[] = new String[15];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getNotifierRepDate());
					rowArray[2] = Common.get(o.getApplNo());
					if (isLink) {
						rowArray[2] = "<a href='#' class='sLink2' onClick=\"listContainerRowClick('"+ o.getId()	+ "');popShowOne('"	+ o.getId()	+ "','')\">" + Common.get(o.getApplNo()) + "</a>";
					}
					rowArray[3] = "";
					rowArray[4] = "";
					rowArray[5] = "";
					rowArray[6] = "";
					rowArray[7] = "";
					rowArray[8] = "";
					rowArray[9] = "";
					rowArray[10] = "";
					rowArray[12] = "";
					if (null != o.getDrg4003Dbs()
							&& !o.getDrg4003Dbs().isEmpty()) {
						for (Object dtlObj : o.getDrg4003Dbs()) {
							Drg4003Db drg43 = (Drg4003Db) dtlObj;
							if ("02".equals(drg43.getMedType())) {
								if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
									rowArray[3] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
								}
								rowArray[4] = Common.get(drg43.getProductName());
								rowArray[5] = Common.get(drg43.getManufactorName());
								rowArray[6] = Common.get(drg43.getManufactorNo());
							}
							if ("01".equals(drg43.getMedType())) {
								if(null != drg43.getPermitKey() && !"".equals(drg43.getPermitKey()) && null != drg43.getPermitNo() && !"".equals(drg43.getPermitNo())){
									rowArray[7] =  Common.get(View.getCommonCodeName("DRGPKID", drg43.getPermitKey()))+"第"+Common.get(drg43.getPermitNo())+"號";
								}
								rowArray[8] = Common.get(drg43.getProductName());
								rowArray[9] = Common.get(drg43.getManufactorName());
							}
						}
					}
					Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo="+ Common.sqlChar(o.getApplNo()));
					if (null != drg04) {
						if ("1".equals(drg04.getConSequence())) {
							rowArray[10] = "藥效改變";
						} else if ("2".equals(drg04.getConSequence())) {
							rowArray[10] = "不良反應發生、強度增強或頻率增加";
						}
		
						rowArray[12] = View.getCommonCodeName("DRG2RKL",drg04.getAssessResult());
					}
					rowArray[11] = View.getCommonCodeName("DRG0301",o.getDealWith());
					rowArray[13] = Common.get(o.getNotifierDept());
					rowArray[14] = "";
					if (isLink) {
						rowArray[14] = "<a href='#' class='sLink2' onClick=\"listContainerRowClick('"+ o.getId()+ "');popShowOne('"	+ o.getId()	+ "','F')\">附件</a>";
					}
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	public DefaultTableModel getTableModel() throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		String[] columns = new String[] { "Id", "NotifierRepDate", "ApplNo",
				"PermitKeyNo1", "ProductName1", "ManufactorName1",
				"ManufactorNo1", "PermitKeyNo2", "ProductName2",
				"ManufactorName2", "ConSequence", "DealWith", "AssessResult",
				"NotifierDept" };

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		arrList = doQueryAllDrg41(null, false);
		if (null != arrList && arrList.size() > 0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][]) arrList.toArray(rs);
			model.setDataVector(rs, columns);

		} else {
			model = null;// 查詢無資料
		}
		return model;
	}
	
	public void doUpdate() throws Exception {
		String procDesc = "";
		List updList = new ArrayList();
		List saveList = new ArrayList();
		List delList = new ArrayList();
		StringBuilder delSql = new StringBuilder("");	
		java.util.Map<String, List<Drg4001Db>> sendMailMap = new HashMap<String, List<Drg4001Db>>();
		java.util.List<Drg4009Db> updateDrg4009Db = new java.util.ArrayList<Drg4009Db>();
		java.util.Map<Long, Drg4001Db> saveLogMap = new HashMap<Long, Drg4001Db>();
		if(null == ids || ids.length == 0 && (null != getId() && !"".equals(getId()))){
			ids = new String[1];
			ids[0] = getId();
		}
		try {
			if(null != ids && ids.length > 0){
				Drg4006Db drg46 = null;
				for(int i=0;i<ids.length;i++){
					//1.查詢案件
					Drg4001Db drg41 = (Drg4001Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4001Db where id = " + ids[i]);
					if(null != drg41){
						//批次受理
						if((ids.length == 1 && !"1".equals(getActionType())) || !"1".equals(getActionType())){
							Object[] actionObj = setUpdateData(drg41);
							drg41 = (Drg4001Db) actionObj[0];
							
							if (null != actionObj[1]) {
								updList.addAll((List)actionObj[1]);
							}
							if (null != actionObj[2]) {
								delList.addAll((List)actionObj[2]);
							}
						}
						if("1".equals(getActionType())){		//受理
							//2.1系統給號
							String newApplNo = TCBWCommon.getApplNo("DRG02","A02",Datetime.getYYY());
							if(null != newApplNo && !"".equals(newApplNo)){
								drg41.setApplNo(newApplNo);
								drg41.setStatus("20");//案件分級中(一般)		
								drg41.setEnrolledDate(Datetime.getYYYMMDD());//收案日期=系統日期		
								drg41.setChargeMan(TCBWCommon.getUserID("DRG01", "02", "Drg4001Db"));//依據案件分級自動分派原則設定人員
								updList.add(drg41);
								
								//新增案件分級資料檔
								Drg4004Db drg44 = new Drg4004Db();
								drg44.setApplNo(drg41.getApplNo()); 
								
								String dayNum = Datetime.getDateDiff("d", drg41.getOccurDate(), drg41.getNotifierRevDate());
								drg44.setIntervalDays(dayNum); //間隔天數
								if(Common.getInt(dayNum) <= 14)  drg44.setNotifierAging("1"); //時效佳
								else drg44.setNotifierAging("1"); //時效待加強
								
								boolean chk42 = false;
								boolean chk43 = false;
								if(null != drg41.getDrg4002Dbs() && !drg41.getDrg4002Dbs().isEmpty()){
									chk42 = true;
								}
								if(null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
									for(Object dtlObj:drg41.getDrg4003Dbs()){
										Drg4003Db drg43 = (Drg4003Db)dtlObj;
										if("02".equals(drg43.getMedType()) && null != drg43.getScientificName() && !"".equals(drg43.getScientificName())){
											chk43 = true;
											break;
										}
									}
								}
								
								drg44.setNotifierQuality("3");		//通報品質Fair
								if(chk42 && chk43){
									drg44.setNotifierQuality("1");	//通報品質Excellent
								}else if(chk43){
									drg44.setNotifierQuality("2");	//通報品質Good
								}
								
								
								drg44.setCreator(getUserID());
								drg44.setCreateDate(Datetime.getYYYMMDD());        
								drg44.setCreateTime(Datetime.getHHMMSS());

								saveList.add(drg44);
								if(null != drg41.getNotifierName() && !"".equals(drg41.getNotifierName())){
									List<Drg4001Db> drg41List = sendMailMap.get(drg41.getNotifierName());
									if(null == drg41List || drg41List.isEmpty()){
										drg41List = new ArrayList<Drg4001Db>();
									}
									drg41List.add(drg41);
									sendMailMap.put(drg41.getNotifierName(), drg41List);
								}
								procDesc = "審核作業 - 案件受理";
							}else throw new Exception("給號發生錯誤，受理失敗！");
							
						}else if("2".equals(getActionType())){	//退件
							drg41.setStatus("02");//退件
							drg41.setChargeMan(getUserID());
							updList.add(drg41);
							
							procDesc = "審核作業 - 退件";
						}else if("3".equals(getActionType())){	//撤案
							drg41.setStatus("03");//撤案
							drg41.setChargeMan("");//清空作業人員
							//歷程紀錄
							procDesc = "審核作業 - 撤案";
							updList.add(drg41);
						}else if("4".equals(getActionType())){	//案件改派
							drg41.setChargeMan(getChargeMan());
							updList.add(drg41);
						}else  if("8".equals(getActionType())){	//初評完成
							//查詢初評資料
							Drg4004Db drg44 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(drg41.getApplNo()));
							drg44.setGradeDate(Datetime.getYYYMMDD());
							//1.先判斷使用者是否有勾選招開諮議會，如果沒有則需背景判斷是否應該招開。
							if(!"Y".equals(drg44.getIsCouncilYn())){
								//判斷是否有達到開諮議會的標準
								drg44.setIsCouncilYn(ServiceGetter.getInstance().getTcbwService().getDrginDao().chkIsCouncilYnByDrg44(drg41, drg44, "PHY"));
							}
							updList.add(drg44);
							drg41.setChargeMan(null);		//清空作業人員
							if("Y".equals(drg44.getIsCouncilYn())){
								drg41.setStatus("30");			//案件評估中
							}else{
								drg41.setStatus("90");			//結案
							}
							updList.add(drg41);
							//歷程紀錄
							procDesc = "分級作業 - 初評完成"+("90".equals(drg41.getStatus())?"結案":"");
						}else  if("9".equals(getActionType())){	//退件補件
							drg41.setStatus("21");
							updList.add(drg41);
							
							//複製一筆案件到外網
							Drg6001Db drg61Old = (Drg6001Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where drg4001Id = " + drg41.getId()+" order by revision desc ");
							if(null != drg61Old){
								Drg6001Db drg61New = new Drg6001Db();
								org.springframework.beans.BeanUtils.copyProperties(drg61Old, drg61New, new String[]{"id","drg6002Dbs","drg6003Dbs"});
								drg61New.setRevision(ServiceGetter.getInstance().getTcbwService().getDrgexDao().getMaxRevisionByDrg6001Db(drg41.getId()));
								if(null != drg61Old.getDrg6002Dbs() && !drg61Old.getDrg6002Dbs().isEmpty()){
									Set dtlSet = new ListOrderedSet();
									for(Object dtlObj:drg61Old.getDrg6002Dbs()){
										Drg6002Db drg62New = new Drg6002Db();
										org.springframework.beans.BeanUtils.copyProperties((Drg6002Db)dtlObj, drg62New, new String[]{"id","drg6001Db"});
										drg62New.setDrg6001Db(drg61New);
										dtlSet.add(drg62New);
									}
									drg61New.setDrg6002Dbs(dtlSet);
								}
								
								if(null != drg61Old.getDrg6003Dbs() && !drg61Old.getDrg6003Dbs().isEmpty()){
									Set dtlSet = new ListOrderedSet();
									for(Object dtlObj:drg61Old.getDrg6003Dbs()){
										Drg6003Db drg63New = new Drg6003Db();
										org.springframework.beans.BeanUtils.copyProperties((Drg6003Db)dtlObj, drg63New, new String[]{"id","drg6001Db"});
										drg63New.setDrg6001Db(drg61New);
										dtlSet.add(drg63New);
									}
									drg61New.setDrg6003Dbs(dtlSet);
								}
								ServiceGetter.getInstance().getCommonService().save(drg61New);
							}
							procDesc = "分級作業 - 退件補件";
						}else  if("10".equals(getActionType())){	//補件通知
							drg41.setStatus("22");
							updList.add(drg41);
							procDesc = "分級作業 - 通知補件";
						}else if("13".equals(getActionType())){	//取消提報
							//1. 刪除諮議會資料
							List drg45List = ServiceGetter.getInstance().getTcbwService().load(" from Drg4005Db where (isClose is null or isClose ='' or isClose <> 'Y') and id in (select drg4005Db.id from Drg4008Db where drg4001Db.id = "+drg41.getId()+")");
							for(int j=0; i < drg45List.size(); i++){
								Drg4005Db drg45 = (Drg4005Db) drg45List.get(j);
								for(Object dtlObj : drg45.getDrg4008Dbs()){
									Drg4008Db drg48 = (Drg4008Db)dtlObj;
									if(Common.get(drg48.getDrg4001Db().getId()).equals(Common.get(drg41.getId()))){
										if(delSql.length() > 0) delSql.append(";");
										delSql.append(" delete from Drg4008Db where id =" + drg48.getId());
									}
								}
								if(drg45.getDrg4008Dbs().size() == 1){
									if(delSql.length() > 0) delSql.append(";");
									delSql.append(" delete from Drg4009Db where drg4005Db.id =" + drg45.getId());
									if(delSql.length() > 0) delSql.append(";");
									delSql.append(" delete from Drg4005Db where id =" + drg45.getId());
								}
							}
							drg41.setStatus("90");
							drg41.setChargeMan(null);
							updList.add(drg41);
							procDesc = "初評作業 - 結案";
						}else if("15".equals(getActionType())){
							if(null != getDrg45Id() && !"".equals(getDrg45Id())){
								Drg4005Db drg45 = (Drg4005Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4005Db where id =" + getDrg45Id());
								if(null != drg45){
									drg45.setIsMedSend("Y");
									drg45.setModifier(getUserID());
									drg45.setModifyDate(Datetime.getYYYMMDD());
									drg45.setModifyTime(Datetime.getHHMMSS());
									updList.add(drg45);
								}
								List drg49List = ServiceGetter.getInstance().getTcbwService().load(" from Drg4009Db where drg4005Db.id = "+getDrg45Id());
								for(int j=0; i < drg49List.size(); i++){
									Drg4009Db drg49 = (Drg4009Db) drg49List.get(j);
									drg49.setNotifyDate(Datetime.getYYYMMDD());
									drg49.setModifier(getUserID());
									drg49.setModifyDate(Datetime.getYYYMMDD());
									drg49.setModifyTime(Datetime.getHHMMSS());
									updateDrg4009Db.add(drg49);
								}
								if(drg49List != null) drg49List.clear();
								
							}
						}else  if("18".equals(getActionType())){	//廠商補件
							Drg4003Db drg43 = null;
							if(null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
								for(Object dtlObj:drg41.getDrg4003Dbs()){
									Drg4003Db drg43Tmp = (Drg4003Db)dtlObj;
									if("02".equals(drg43Tmp.getMedType())){
										drg43 = drg43Tmp;
									}
								}
							}
							
							//1.使用藥證資料查詢是否有相同廠商回覆資料
							if(null != drg43){
								if(null == drg46){
									drg46 = (Drg4006Db) View.getObject(" from Drg4006Db where (isClose is null or isClose ='' or isClose ='N') and permitKey2 = "+Common.sqlChar(drg43.getPermitKey())+" and permitNo2 = "+Common.sqlChar(drg43.getPermitNo()));
									if(null == drg46){
										drg46 = new Drg4006Db();
										drg46.setManufactorID(drg43.getManufactorID());
										drg46.setPermitKey2(drg43.getPermitKey());
										drg46.setPermitNo2(drg43.getPermitNo());
										drg46.setProductName2(drg43.getProductName());
										drg46.setCreator(getUserID());
										drg46.setCreateDate(Datetime.getYYYMMDD());        
										drg46.setCreateTime(Datetime.getHHMMSS());
										drg46.setModifier(getUserID());
										drg46.setModifyDate(Datetime.getYYYMMDD());
										drg46.setModifyTime(Datetime.getHHMMSS());
									}
								}
								
								java.util.Set dtl10Set = new ListOrderedSet();
								Drg4010Db drg410 = new Drg4010Db();
								drg410.setApplNo(drg41.getApplNo());
								drg410.setDrg4001Db(drg41);
								drg410.setDrg4006Db(drg46);
								drg410.setCreator(getUserID());
								drg410.setCreateDate(Datetime.getYYYMMDD());        
								drg410.setCreateTime(Datetime.getHHMMSS());
								drg410.setModifier(getUserID());
								drg410.setModifyDate(Datetime.getYYYMMDD());
								drg410.setModifyTime(Datetime.getHHMMSS());
								dtl10Set.add(drg410);								
								drg46.setDrg4010Dbs(dtl10Set);
								drg41.getDrg4010Dbs().add(drg410);
							}
							drg41.setStatus("40");
							updList.add(drg41);
							procDesc = "複評作業 - 廠商補件";
						}
						
						//外詢外部資料，並更新案件資料
						Drg6001Db drg61 = (Drg6001Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where drg4001Id = " + drg41.getId()+" order by revision desc ");
						if(null != drg61){
							drg61.setApplNo(drg41.getApplNo());
							drg61.setStatus(drg41.getStatus());
							updList.add(drg61);
						}
					}
					saveLogMap.put(drg41.getId(), drg41);
				}
				if(null != drg46 && (null == drg46.getId() || drg46.getId() == 0)){
					saveList.add(drg46);
				}
			}
			
			if(null != saveList && !saveList.isEmpty()){
				ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
			}
			if(null != updList && !updList.isEmpty()){
				ServiceGetter.getInstance().getTcbwService().updateBatch(updList);
			}
			if(null != delList && !delList.isEmpty()){
				ServiceGetter.getInstance().getTcbwService().deleteBatch(delList);
			}
			if(delSql.length() > 0){
				for(String sql:delSql.toString().split(";")){
					ServiceGetter.getInstance().getCommonService().bulkUpdate(sql);
				}
			}
			if(null != updateDrg4009Db && !updateDrg4009Db.isEmpty()){
				ServiceGetter.getInstance().getTcbwService().updateBatch(updateDrg4009Db);
			}
			if(null != procDesc && !"".equals(procDesc)){
				if(null != saveLogMap && !saveLogMap.isEmpty()){
					for(Long drg41Id:saveLogMap.keySet()){
						Drg4001Db drg41 = saveLogMap.get(drg41Id);
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg41.getId(), drg41.getApplNo(), drg41.getStatus(), procDesc, TCBWCommon.getCurrentUserId());
						//撤案、結案後案件轉為pdf檔置於相關附件頁籤中
						if("90".equals(drg41.getStatus()) || "03".equals(drg41.getStatus())){
							ServiceGetter.getInstance().getTcbwService().getDrginDao().closedCaseToPDFByDrg(drg41);
						}
					}
				}
			}
			
			if(null != sendMailMap && !sendMailMap.isEmpty()){
				java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
				//先查詢郵件範本
				Con1001Db con11 = (Con1001Db)View.getObject("from Con1001Db where mailID='DRG020001'");	
				if(null != con11 && null != con11.getMailBody() && !"".equals(con11.getMailBody())){
					for(String notifierName:sendMailMap.keySet()){
						String mailBody = con11.getMailBody();
						String userMail = View.getLookupField("select userEmail from CommonUser where userName = " + Common.sqlChar(notifierName));
						if(null != userMail && !"".equals(userMail)){
							List<Drg4001Db> drg41List = sendMailMap.get(notifierName);
							StringBuilder drg41Content = new StringBuilder();
							if(null != drg41List && !drg41List.isEmpty()){
								drg41Content.append("<html>").append("\n");
								drg41Content.append("<table  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n");
								drg41Content.append("<tr>");							
								drg41Content.append("	<td>案件編號</td>");
								drg41Content.append("	<td>通報日期</td>");
								drg41Content.append("	<td>成份</td>");
								drg41Content.append("	<td>病人識別代碼</td>");
								drg41Content.append("</tr>\n");
								for(Drg4001Db drg41 : drg41List){
									drg41Content.append("<tr>");
									drg41Content.append("	<td>").append(Common.get(drg41.getApplNo())).append("</td>");
									drg41Content.append("	<td>").append(Common.get(drg41.getOccurDate())).append("").append("</td>");
									String scientificName = "";
									if(null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
										for(Object dtlObj : drg41.getDrg4003Dbs()){
											Drg4003Db drg43 = (Drg4003Db)dtlObj;
											if("02".equals(drg43.getMedType())){
												scientificName = Common.get(drg43.getScientificName());
											}
										}
									}
									drg41Content.append("	<td>").append(scientificName).append("").append("</td>");
									drg41Content.append("	<td>").append(Common.get(drg41.getPatientId())).append("</td>");
									drg41Content.append("</tr>\n");
								}
								drg41Content.append("</table>");
								drg41Content.append("</html>").append("\n");
								
								mailBody = mailBody.replace("[F_通報內容]", drg41Content.toString());
								
								javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
		    				    p.setAddress(userMail);
		    				    mailList.add(p);
		    				    
								ServiceGetter.getInstance().getTcbwService().sendEmail(con11.getTitle(), mailBody, mailList, null, true,null, null, null);
								for(Drg4001Db drg41 : drg41List){
									TCBWCommon.setMailbackup("DRG2",Common.get(drg41.getId()),con11.getTitle(), mailBody, drg41.getApplNo(),"", TCBWCommon.getCurrentUserId(),"");	
								}
							}
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setStateUpdateError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,修改失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}
}
