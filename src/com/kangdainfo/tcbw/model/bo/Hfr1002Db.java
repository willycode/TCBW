package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Hfr1002Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String termSdate;//任期區間(起)
    private String termEdate;//任期區間(迄)
    private String postLev;//職等
    private String unionID;//任職單位
    private String address;//公函地址
    private String email;//信箱
    private String telArea;//區域號碼
    private String tel;//電話
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
	
	private Hfr1001Db hfr1001Db;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTermSdate() {
		return termSdate;
	}

	public void setTermSdate(String termSdate) {
		this.termSdate = termSdate;
	}

	public String getTermEdate() {
		return termEdate;
	}

	public void setTermEdate(String termEdate) {
		this.termEdate = termEdate;
	}

	public String getPostLev() {
		return postLev;
	}

	public void setPostLev(String postLev) {
		this.postLev = postLev;
	}

	public String getUnionID() {
		return unionID;
	}

	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelArea() {
		return telArea;
	}

	public void setTelArea(String telArea) {
		this.telArea = telArea;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFaxArea() {
		return faxArea;
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = faxArea;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public Hfr1001Db getHfr1001Db() {
		return hfr1001Db;
	}

	public void setHfr1001Db(Hfr1001Db hfr1001Db) {
		this.hfr1001Db = hfr1001Db;
	}

	/** default constructor */
    public Hfr1002Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Hfr1002Db [id=");
		builder.append(id);
		builder.append(", termSdate=");
		builder.append(termSdate);
		builder.append(", termEdate=");
		builder.append(termEdate);
		builder.append(", postLev=");
		builder.append(postLev);
		builder.append(", unionID=");
		builder.append(unionID);
		builder.append(", address=");
		builder.append(address);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telArea=");
		builder.append(telArea);
		builder.append(", tel=");
		builder.append(tel);
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
		builder.append(",hfr1001Db=");
		builder.append(hfr1001Db);
		builder.append("]");
		return builder.toString();
	}    
    

	
}
