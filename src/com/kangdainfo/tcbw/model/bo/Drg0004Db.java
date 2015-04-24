package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Drg0004Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String applNo;	//案件號碼
    private String gradeMan;	//分級確認人員
    private String gradeDate;	//分級確認日期
    private String drgLev;	//風險等級
    private String postDate;	//發文日期
    private String postNo;	//發文文號
    
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

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getGradeMan() {
		return gradeMan;
	}

	public void setGradeMan(String gradeMan) {
		this.gradeMan = gradeMan;
	}

	public String getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(String gradeDate) {
		this.gradeDate = gradeDate;
	}

	public String getDrgLev() {
		return drgLev;
	}

	public void setDrgLev(String drgLev) {
		this.drgLev = drgLev;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
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
    public Drg0004Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg0004Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", gradeMan=");
		builder.append(gradeMan);
		builder.append(", gradeDate=");
		builder.append(gradeDate);
		builder.append(", drgLev=");
		builder.append(drgLev);
		builder.append(", postDate=");
		builder.append(postDate);
		builder.append(", postNo=");
		builder.append(postNo);
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
