package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Hfr1005Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    private Hfr1003Db hfr1003Db;
    private String caseno;	//案件編號
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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaseno() {
		return caseno;
	}

	public void setCaseno(String caseno) {
		this.caseno = caseno;
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
	
	public Hfr1003Db getHfr1003Db() {
		return hfr1003Db;
	}

	public void setHfr1003Db(Hfr1003Db hfr1003Db) {
		this.hfr1003Db = hfr1003Db;
	}

	/** default constructor */
    public Hfr1005Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Hfr1005Db [id=");
		builder.append(id);
		builder.append(", hfr1003Db=");
		builder.append(hfr1003Db);
		builder.append(", caseno=");
		builder.append(caseno);
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
