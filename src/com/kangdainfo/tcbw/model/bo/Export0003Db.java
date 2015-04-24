package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Export0003Db implements Serializable {
	/** identifier field */
	private Long id;
	
	private String code; //代號
	private String name; //名稱
	
	private String receiveSystem; //介接異動系統
	private String receiveDate;	  //介接異動日期
	private String receiveTime;   //介接異動時間
	private String creator;       //建檔者
	private String createDate;    //建檔日期
	private String createTime;    //建檔時間
	private String modifier;      //最後異動者
	private String modifyDate;    //最後異動日期
	private String modifyTime;    //最後異動時間
	
	/** default constructor */
	public Export0003Db(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Export0003Db [id=");
		sb.append(id);
		sb.append(", code=");
		sb.append(code);
		sb.append(", name=");
		sb.append(name);
		sb.append(", receiveSystem=");
		sb.append(receiveSystem);
		sb.append(", receiveDate=");
		sb.append(receiveDate);
		sb.append(", receiveTime=");
		sb.append(receiveTime);
		sb.append(", creator=");
		sb.append(creator);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", modifier=");
		sb.append(modifier);
		sb.append(", modifyDate=");
		sb.append(modifyDate);
		sb.append(", modifyTime=");
		sb.append(modifyTime);
		sb.append("]");
		return sb.toString();
	}
}
