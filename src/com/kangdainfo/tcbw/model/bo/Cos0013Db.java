package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0013Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Cos0011Db cos0011Db;	//化妝品不良品追蹤資料檔    
    private String applNo;	//案件號碼
    private String receiptDate;	//收文日期
    private String postDate;	//發文日期
    private String postDept;	//公文發文單位
    private String postNo;	//公文發文文號
    private String postMemo;	//內容摘要
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
    public Cos0013Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cos0011Db getCos0011Db() {
		return cos0011Db;
	}

	public void setCos0011Db(Cos0011Db cos0011Db) {
		this.cos0011Db = cos0011Db;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostDept() {
		return postDept;
	}

	public void setPostDept(String postDept) {
		this.postDept = postDept;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getPostMemo() {
		return postMemo;
	}

	public void setPostMemo(String postMemo) {
		this.postMemo = postMemo;
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
		builder.append("Cos0013Db [id=");
		builder.append(id);
		builder.append(", cos0011Db=");
		builder.append(cos0011Db);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", receiptDate=");
		builder.append(receiptDate);
		builder.append(", postDate=");
		builder.append(postDate);
		builder.append(", postDept=");
		builder.append(postDept);
		builder.append(", postNo=");
		builder.append(postNo);
		builder.append(", postMemo=");
		builder.append(postMemo);
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
