package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Con2002Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    private Long con1009Db;
    /** persistent field */
    private String bnhi;
    private String medAgencyCode;
	private String medAgencyName;
    private String agencyAddress;
    private String areaTel;
    private String tel;
    private String engageKind;
    private String medAgencyKind;   
    private String endDate;
	private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Long getCon1009Db() {
		return con1009Db;
	}

	public void setCon1009Db(Long con1009Db) {
		this.con1009Db = con1009Db;
	}
    
    public String getBnhi() {
		return bnhi;
	}

	public void setBnhi(String bnhi) {
		this.bnhi = bnhi;
	}

	public String getMedAgencyCode() {
		return medAgencyCode;
	}

	public void setMedAgencyCode(String medAgencyCode) {
		this.medAgencyCode = medAgencyCode;
	}

	public String getMedAgencyName() {
		return medAgencyName;
	}

	public void setMedAgencyName(String medAgencyName) {
		this.medAgencyName = medAgencyName;
	}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAreaTel() {
		return areaTel;
	}

	public void setAreaTel(String areaTel) {
		this.areaTel = areaTel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEngageKind() {
		return engageKind;
	}

	public void setEngageKind(String engageKind) {
		this.engageKind = engageKind;
	}

	public String getMedAgencyKind() {
		return medAgencyKind;
	}

	public void setMedAgencyKind(String medAgencyKind) {
		this.medAgencyKind = medAgencyKind;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
    
    /** default constructor */
    public Con2002Db() {
    }
    
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Con2002Db [id=");
		builder.append(id);
		builder.append(", con1009Db=");
		builder.append(con1009Db);
		builder.append(", bnhi=");
		builder.append(bnhi);
		builder.append(", medAgencyCode=");
		builder.append(medAgencyCode);
		builder.append(", medAgencyName=");
		builder.append(medAgencyName);
		builder.append(", areaTel=");
		builder.append(areaTel);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", engageKind=");
		builder.append(engageKind);
		builder.append(", medAgencyKind=");
		builder.append(medAgencyKind);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", receiveSystem=");
		builder.append(receiveSystem);
		builder.append(", receiveDate=");
		builder.append(receiveDate);
		builder.append(", receiveTime=");
		builder.append(receiveTime);	
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", modifyDate=");
		builder.append(modifyDate);
		builder.append(", modifyTime=");
		builder.append(modifyTime);
		builder.append("]");
		return builder.toString();
	}    
    

	
}
