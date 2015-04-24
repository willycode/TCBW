package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg6001Db  implements Serializable 
{
	/** identifier field */
    private Long id;

    private Long drg4001Id; //內部主檔ID
    private String applNo;	//案件號碼
    private String status;	//案件狀態
    private String chargeMan;	//作業人員
    private String occurDate;	//發生日期
    private String enrolledDate;  //收案日期
    private String inOrOutcreator;  //案件擁有者
    private String notifierRevDate;	//通報者接獲日期
    private String notifierRepDate;	//通報中心接獲通報日期
    private String notifierSource;	//通報來源
    private String notifierUserID;	//通報者ID
    private String notifierName;	//通報者姓名
    private String notifierDeptID;	//通報者服務機構ID
    private String notifierDept;	//通報者服務機構
    private String notifierTel;	//通報者電話
    private String notifierTelArea;	//通報者電話區碼
    private String notifierTelExt;	//通報者電話分機
    private String notifierPhone;	//通報者手機
    private String notifierCounty;	//通報者地址縣市
    private String notifierZipCode;	//通報者地址鄉鎮區
    private String notifierAddress;	//通報者地址
    private String notifierEmail;	//通報者電子信箱
    private String notifierFaxArea;	//通報者地址鄉鎮區
    private String notifierFax;	//通報者傳真
    private String notifierType;	//通報者屬性
    private String notifierTitle;	//通報者職稱
    private String patientId;	//病人識別代號
    private String patientSex;	//病人性別
    private String patientBirth;  //病人出生日期
    private String patientAge;	//病人年齡
    private String patientHeight;	//病人身高
    private String patientWeight;	//病人體重
    private String conSequence;	//通報事件後果
    private String effectChangeDesc;	//通報事件後果-藥效改變狀況    
    private String badReactionLev;	//通報事件後果-不良反應等級
    private String badReactionDesc;	//通報事件後果-不良反應狀況
    private String beforeDesc;	//事件前描述
    private String changeDesc;	//藥品轉換描述
    private String occurDesc;	//發生事件描述
    private String afterDesc;	//事件後描述
    private String otherDesc;	//其他相關資料
    private String dealWith;	//發生事件後之處置
    private String dealWithOther;	//發生事件後之處置其他
    private String isImproveYn;	//病人恢復原藥或轉換同成分藥品其症狀是否改善
    private String dressingAttitude;	//醫師對換藥的態度
    private String obedienceLev;	//病人服藥順從性
    private String isContactYn;	//是否提供聯絡資訊
    private String revision;	//版次

    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間
    
    private String trans;	          //是否為轉檔資料
    private String old_CaseNoE;	      //案件號碼(Excel)
    private String old_CaseNoD;	      //案件號碼(Db)
    private String old_notifierDept;  //通報機構
    private String old_dataSource;    //資料來源

	private Set drg6002Dbs;
	private Set drg6003Dbs;
    
	/** default constructor */
    public Drg6001Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDrg4001Id() {
		return drg4001Id;
	}

	public void setDrg4001Id(Long drg4001Id) {
		this.drg4001Id = drg4001Id;
	}

	public String getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getInOrOutcreator() {
		return inOrOutcreator;
	}

	public void setInOrOutcreator(String inOrOutcreator) {
		this.inOrOutcreator = inOrOutcreator;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChargeMan() {
		return chargeMan;
	}

	public void setChargeMan(String chargeMan) {
		this.chargeMan = chargeMan;
	}

	public String getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}

	public String getNotifierRevDate() {
		return notifierRevDate;
	}

	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = notifierRevDate;
	}

	public String getNotifierRepDate() {
		return notifierRepDate;
	}

	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = notifierRepDate;
	}

	public String getNotifierSource() {
		return notifierSource;
	}

	public void setNotifierSource(String notifierSource) {
		this.notifierSource = notifierSource;
	}

	public String getNotifierUserID() {
		return notifierUserID;
	}

	public void setNotifierUserID(String notifierUserID) {
		this.notifierUserID = notifierUserID;
	}

	public String getNotifierName() {
		return notifierName;
	}

	public void setNotifierName(String notifierName) {
		this.notifierName = notifierName;
	}

	public String getNotifierDeptID() {
		return notifierDeptID;
	}

	public void setNotifierDeptID(String notifierDeptID) {
		this.notifierDeptID = notifierDeptID;
	}

	public String getNotifierDept() {
		return notifierDept;
	}

	public void setNotifierDept(String notifierDept) {
		this.notifierDept = notifierDept;
	}

	public String getNotifierTel() {
		return notifierTel;
	}

	public void setNotifierTel(String notifierTel) {
		this.notifierTel = notifierTel;
	}

	public String getNotifierPhone() {
		return notifierPhone;
	}

	public void setNotifierPhone(String notifierPhone) {
		this.notifierPhone = notifierPhone;
	}

	public String getNotifierAddress() {
		return notifierAddress;
	}

	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = notifierAddress;
	}

	public String getNotifierEmail() {
		return notifierEmail;
	}

	public void setNotifierEmail(String notifierEmail) {
		this.notifierEmail = notifierEmail;
	}

	public String getNotifierFax() {
		return notifierFax;
	}

	public void setNotifierFax(String notifierFax) {
		this.notifierFax = notifierFax;
	}

	public String getNotifierType() {
		return notifierType;
	}

	public void setNotifierType(String notifierType) {
		this.notifierType = notifierType;
	}

	public String getNotifierTitle() {
		return notifierTitle;
	}

	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = notifierTitle;
	}
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientBirth() {
		return patientBirth;
	}

	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientHeight() {
		return patientHeight;
	}

	public void setPatientHeight(String patientHeight) {
		this.patientHeight = patientHeight;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getConSequence() {
		return conSequence;
	}

	public void setConSequence(String conSequence) {
		this.conSequence = conSequence;
	}

	public String getEffectChangeDesc() {
		return effectChangeDesc;
	}

	public void setEffectChangeDesc(String effectChangeDesc) {
		this.effectChangeDesc = effectChangeDesc;
	}
	
	public String getBadReactionLev() {
		return badReactionLev;
	}

	public void setBadReactionLev(String badReactionLev) {
		this.badReactionLev = badReactionLev;
	}

	public String getBadReactionDesc() {
		return badReactionDesc;
	}

	public void setBadReactionDesc(String badReactionDesc) {
		this.badReactionDesc = badReactionDesc;
	}

	public String getBeforeDesc() {
		return beforeDesc;
	}

	public void setBeforeDesc(String beforeDesc) {
		this.beforeDesc = beforeDesc;
	}

	public String getChangeDesc() {
		return changeDesc;
	}

	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}

	public String getOccurDesc() {
		return occurDesc;
	}

	public void setOccurDesc(String occurDesc) {
		this.occurDesc = occurDesc;
	}

	public String getAfterDesc() {
		return afterDesc;
	}

	public void setAfterDesc(String afterDesc) {
		this.afterDesc = afterDesc;
	}

	public String getOtherDesc() {
		return otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getDealWithOther() {
		return dealWithOther;
	}

	public void setDealWithOther(String dealWithOther) {
		this.dealWithOther = dealWithOther;
	}

	public String getIsImproveYn() {
		return isImproveYn;
	}

	public void setIsImproveYn(String isImproveYn) {
		this.isImproveYn = isImproveYn;
	}

	public String getDressingAttitude() {
		return dressingAttitude;
	}

	public void setDressingAttitude(String dressingAttitude) {
		this.dressingAttitude = dressingAttitude;
	}

	public String getObedienceLev() {
		return obedienceLev;
	}

	public void setObedienceLev(String obedienceLev) {
		this.obedienceLev = obedienceLev;
	}

	public String getIsContactYn() {
		return isContactYn;
	}

	public void setIsContactYn(String isContactYn) {
		this.isContactYn = isContactYn;
	}

	public String getNotifierTelArea() {
		return notifierTelArea;
	}

	public void setNotifierTelArea(String notifierTelArea) {
		this.notifierTelArea = notifierTelArea;
	}

	public String getNotifierTelExt() {
		return notifierTelExt;
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = notifierTelExt;
	}

	public String getNotifierCounty() {
		return notifierCounty;
	}

	public void setNotifierCounty(String notifierCounty) {
		this.notifierCounty = notifierCounty;
	}

	public String getNotifierZipCode() {
		return notifierZipCode;
	}

	public void setNotifierZipCode(String notifierZipCode) {
		this.notifierZipCode = notifierZipCode;
	}

	public String getNotifierFaxArea() {
		return notifierFaxArea;
	}

	public void setNotifierFaxArea(String notifierFaxArea) {
		this.notifierFaxArea = notifierFaxArea;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getReceiveSystem() {
		return receiveSystem;
	}

	public void setReceiveSystem(String receiveSystem) {
		this.receiveSystem = receiveSystem;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public Set getDrg6002Dbs() {
		return drg6002Dbs;
	}

	public void setDrg6002Dbs(Set drg6002Dbs) {
		this.drg6002Dbs = drg6002Dbs;
	}

	public Set getDrg6003Dbs() {
		return drg6003Dbs;
	}

	public void setDrg6003Dbs(Set drg6003Dbs) {
		this.drg6003Dbs = drg6003Dbs;
	}
	
	
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getOld_CaseNoE() {
		return old_CaseNoE;
	}
	public void setOld_CaseNoE(String old_CaseNoE) {
		this.old_CaseNoE = old_CaseNoE;
	}
	public String getOld_CaseNoD() {
		return old_CaseNoD;
	}
	public void setOld_CaseNoD(String old_CaseNoD) {
		this.old_CaseNoD = old_CaseNoD;
	}
	public String getOld_notifierDept() {
		return old_notifierDept;
	}
	public void setOld_notifierDept(String old_notifierDept) {
		this.old_notifierDept = old_notifierDept;
	}
	public String getOld_dataSource() {
		return old_dataSource;
	}
	public void setOld_dataSource(String old_dataSource) {
		this.old_dataSource = old_dataSource;
	}

	@Override
	public String toString() {
		return "Drg6001Db [id=" + id + ", drg4001Id=" + drg4001Id + ", applNo="
				+ applNo + ", status=" + status + ", chargeMan=" + chargeMan
				+ ", occurDate=" + occurDate + ", enrolledDate=" + enrolledDate
				+ ", inOrOutcreator=" + inOrOutcreator + ", notifierRevDate="
				+ notifierRevDate + ", notifierRepDate=" + notifierRepDate
				+ ", notifierSource=" + notifierSource + ", notifierName="
				+ notifierName + ", notifierDept=" + notifierDept
				+ ", notifierDeptID=" + notifierDeptID
				+ ", notifierTel=" + notifierTel + ", notifierPhone="
				+ notifierPhone + ", notifierAddress=" + notifierAddress
				+ ", notifierEmail=" + notifierEmail + ", notifierFax="
				+ notifierFax + ", notifierType=" + notifierType
				+ ", notifierTitle=" + notifierTitle + ", patientId="
				+ patientId + ", patientSex=" + patientSex + ", patientBirth="
				+ patientBirth + ", patientAge=" + patientAge
				+ ", patientHeight=" + patientHeight + ", patientWeight="
				+ patientWeight + ", conSequence=" + conSequence
				+ ", effectChangeDesc=" + effectChangeDesc
				+ ", badReactionLev=" + badReactionLev + ", badReactionDesc="
				+ badReactionDesc + ", beforeDesc=" + beforeDesc
				+ ", changeDesc=" + changeDesc + ", occurDesc=" + occurDesc
				+ ", afterDesc=" + afterDesc + ", otherDesc=" + otherDesc
				+ ", dealWith=" + dealWith + ", dealWithOther=" + dealWithOther
				+ ", isImproveYn=" + isImproveYn + ", dressingAttitude="
				+ dressingAttitude + ", obedienceLev=" + obedienceLev
				+ ", isContactYn=" + isContactYn + ", revision=" + revision
				+ ", receiveSystem=" + receiveSystem + ", receiveDate="
				+ receiveDate + ", receiveTime=" + receiveTime + ", creator="
				+ creator + ", createDate=" + createDate + ", createTime="
				+ createTime + ", modifier=" + modifier + ", modifyDate="
				+ modifyDate + ", modifyTime=" + modifyTime + ", drg6002Dbs="
				+ drg6002Dbs + ", drg6003Dbs=" + drg6003Dbs  
				+ ", trans=" + trans + ", old_CaseNoE=" + old_CaseNoE
				+ ", old_CaseNoD=" + old_CaseNoD + ", old_notifierDept=" + old_notifierDept
				+ ", old_dataSource=" + old_dataSource
				+"]";
	}

	
}
