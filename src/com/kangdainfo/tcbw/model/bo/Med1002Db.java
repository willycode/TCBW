package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Med1002Db  implements Serializable 
{
	/** identifier field */
    private Long id;

    private String matter;//事項
	private int isYes;//是
    private String isYesType;//是-型態
	private int isNo;//否
    private String isNoType;//否-型態
	private int isUnknown;//不知
    private String isUnknownType;//不知-型態
    private int seq;//排序
    private String isStop;//是否停用
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
	
	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public int getIsYes() {
		return isYes;
	}

	public void setIsYes(int isYes) {
		this.isYes = isYes;
	}

	public String getIsYesType() {
		return isYesType;
	}

	public void setIsYesType(String isYesType) {
		this.isYesType = isYesType;
	}

	public int getIsNo() {
		return isNo;
	}

	public void setIsNo(int isNo) {
		this.isNo = isNo;
	}

	public String getIsNoType() {
		return isNoType;
	}

	public void setIsNoType(String isNoType) {
		this.isNoType = isNoType;
	}

	public int getIsUnknown() {
		return isUnknown;
	}

	public void setIsUnknown(int isUnknown) {
		this.isUnknown = isUnknown;
	}

	public String getIsUnknownType() {
		return isUnknownType;
	}

	public void setIsUnknownType(String isUnknownType) {
		this.isUnknownType = isUnknownType;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
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
    public Med1002Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med1002Db [id=");
		builder.append(id);
		builder.append(", matter=");
		builder.append(matter);
		builder.append(", isYes=");
		builder.append(isYes);
		builder.append(", isYesType=");
		builder.append(isYesType);
		builder.append(", isNo=");
		builder.append(isNo);
		builder.append(", isNoType=");
		builder.append(isNoType);
		builder.append(", isUnknown=");
		builder.append(isUnknown);
		builder.append(", isUnknownType=");
		builder.append(isUnknownType);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", isStop=");
		builder.append(isStop);
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
