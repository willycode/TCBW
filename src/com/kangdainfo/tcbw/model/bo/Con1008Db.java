package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Con1008Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Con1007Db con1007Db;

	/** persistent field */
    
    private String tabId;//頁籤名稱
    private String name;//欄位名稱
    private String chName;//欄位中文名稱
    private String fieldType;//欄位型態
    private String isRequired;//是否必填   
    private String isDate; //日期格式  
    private String isNum;//數字格式    
    private String isLength;//長度限制    
    private String isComplete;//是否完整性
    private String isMlms;//是否檢核藥證欄位
   
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

    public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Con1007Db getCon1007Db() {
		return con1007Db;
	}

	public void setCon1007Db(Con1007Db con1007Db) {
		this.con1007Db = con1007Db;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getIsDate() {
		return isDate;
	}

	public void setIsDate(String isDate) {
		this.isDate = isDate;
	}

	public String getIsNum() {
		return isNum;
	}

	public void setIsNum(String isNum) {
		this.isNum = isNum;
	}

	public String getIsLength() {
		return isLength;
	}

	public void setIsLength(String isLength) {
		this.isLength = isLength;
	}
	
	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public String getIsMlms() {
		return isMlms;
	}

	public void setIsMlms(String isMlms) {
		this.isMlms = isMlms;
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
    

    public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	

	/** default constructor */
    public Con1008Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Con1008Db [id=");
		builder.append(id);
		builder.append(", con1007Db=");
		builder.append(con1007Db);
		builder.append(", name=");
		builder.append(chName);
		builder.append(", isRequired=");
		builder.append(isRequired);		
		builder.append(", isDate=");
		builder.append(isDate);
		builder.append(", isNum=");
		builder.append(isNum);
		builder.append(", isLength=");
		builder.append(isLength);	
		builder.append(", isComplete=");
		builder.append(isComplete);	
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
