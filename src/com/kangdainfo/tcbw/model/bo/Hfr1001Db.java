package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Hfr1001Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    private String name;//姓名
    private String sex;//性別
    private String communAddress;//通訊地址
    private String houseAddress;//戶籍地址
    private String idNum;//身分證號
    private String email;//信箱
    private String telArea;//區域號碼
    private String tel;//電話
    private String mobile;//手機
    private String faxArea;//fax區域號碼
    private String fax;//FAX
    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間 
    private String committeeNum;	

	public String getCommitteeNum() {
		return committeeNum;
	}

	public void setCommitteeNum(String committeeNum) {
		this.committeeNum = committeeNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCommunAddress() {
		return communAddress;
	}

	public void setCommunAddress(String communAddress) {
		this.communAddress = communAddress;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelArea() {
		return telArea;
	}

	public void setTelArea(String telArea) {
		this.telArea = telArea;
	}

	public String getFaxArea() {
		return faxArea;
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = faxArea;
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
    public Hfr1001Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Hfr1001Db [id=");
		builder.append(id);
		builder.append(", committeeNum=");
		builder.append(committeeNum);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", communAddress=");
		builder.append(communAddress);
		builder.append(", houseAddress=");
		builder.append(houseAddress);
		builder.append(", idNum=");
		builder.append(idNum);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telArea=");
		builder.append(telArea);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", faxArea=");
		builder.append(faxArea);
		builder.append(", fax=");
		builder.append(fax);
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
