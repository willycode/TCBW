package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0004Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Cos0002Db cos0002Db;	//化妝品不良反應資料檔
    private String testDate; //檢驗日期
    private String testItems;  //檢驗項目
    private String testNum;  //檢驗數據
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間

	/** default constructor */
    public Cos0004Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cos0002Db getCos0002Db() {
		return cos0002Db;
	}

	public void setCos0002Db(Cos0002Db cos0002Db) {
		this.cos0002Db = cos0002Db;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getTestItems() {
		return testItems;
	}

	public void setTestItems(String testItems) {
		this.testItems = testItems;
	}

	public String getTestNum() {
		return testNum;
	}

	public void setTestNum(String testNum) {
		this.testNum = testNum;
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

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0004Db [id=");
		builder.append(id);
		builder.append(", cos0002Db=");
		builder.append(cos0002Db);
		builder.append(", testDate=");
		builder.append(testDate);
		builder.append(", testItems=");
		builder.append(testItems);
		builder.append(", testNum=");
		builder.append(testNum);
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
