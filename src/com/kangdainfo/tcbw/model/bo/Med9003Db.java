package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med9003Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    private String noticereupdatedate;
    private String noticereupdateNo;
    private String noticereupdatesummary;
    private String reupdatedate;
    private String reupdateNo;
    private String reupdatesummary;
	private String receiveSystem;
    private String receiveDate;
    private String receiveTime;
    private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    private Med9002Db med9002Db;
    
	/** default constructor */
    public Med9003Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med9003Db [id=");
		builder.append(id);
		builder.append(", med9002Db=");
		builder.append(med9002Db);
		builder.append(", noticereupdatedate=");
		builder.append(noticereupdatedate);
	    builder.append(", noticereupdateNo=");
		builder.append(noticereupdateNo);
	    builder.append(", noticereupdatesummary=");
		builder.append(noticereupdatesummary);
	    builder.append(", reupdatedate=");
		builder.append(reupdatedate);
	    builder.append(", reupdateNo=");
		builder.append(reupdateNo);
	    builder.append(", reupdatesummary=");
		builder.append(reupdatesummary);
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
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getNoticereupdatedate() {
		return noticereupdatedate;
	}

	public void setNoticereupdatedate(String noticereupdatedate) {
		this.noticereupdatedate = noticereupdatedate;
	}

	public String getNoticereupdateNo() {
		return noticereupdateNo;
	}

	public void setNoticereupdateNo(String noticereupdateNo) {
		this.noticereupdateNo = noticereupdateNo;
	}

	public String getNoticereupdatesummary() {
		return noticereupdatesummary;
	}

	public void setNoticereupdatesummary(String noticereupdatesummary) {
		this.noticereupdatesummary = noticereupdatesummary;
	}

	public String getReupdatedate() {
		return reupdatedate;
	}

	public void setReupdatedate(String reupdatedate) {
		this.reupdatedate = reupdatedate;
	}

	public String getReupdateNo() {
		return reupdateNo;
	}

	public void setReupdateNo(String reupdateNo) {
		this.reupdateNo = reupdateNo;
	}

	public String getReupdatesummary() {
		return reupdatesummary;
	}

	public void setReupdatesummary(String reupdatesummary) {
		this.reupdatesummary = reupdatesummary;
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

	public Med9002Db getMed9002Db() {
		return med9002Db;
	}

	public void setMed9002Db(Med9002Db med9002Db) {
		this.med9002Db = med9002Db;
	}
	
}
