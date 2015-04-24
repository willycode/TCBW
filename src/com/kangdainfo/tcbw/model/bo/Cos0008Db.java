package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0008Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;	//案件號碼
    private String preResult;
	private String leftCaseReason;
    private String isCompleteYn;	//通報資料完整性
    private String isContactYn;	//是否聯絡通報者取得完整資料
    private String reactionLev;	//不良反應嚴重程度
    private String timingLev;	//時序性
    private String previousNotify;	//過往通報紀錄
    private String isComplaintYn;	//是否連絡廠商進行客訴
    private String isOtherDeptYn;	//是否送交相關單位處理
    private String disDate;
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
    public Cos0008Db() {
    }

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
	
	public String getPreResult() {
		return preResult;
	}

	public void setPreResult(String preResult) {
		this.preResult = preResult;
	}

	public String getLeftCaseReason() {
		return leftCaseReason;
	}

	public void setLeftCaseReason(String leftCaseReason) {
		this.leftCaseReason = leftCaseReason;
	}

	public String getIsCompleteYn() {
		return isCompleteYn;
	}

	public void setIsCompleteYn(String isCompleteYn) {
		this.isCompleteYn = isCompleteYn;
	}

	public String getIsContactYn() {
		return isContactYn;
	}

	public void setIsContactYn(String isContactYn) {
		this.isContactYn = isContactYn;
	}

	public String getReactionLev() {
		return reactionLev;
	}

	public void setReactionLev(String reactionLev) {
		this.reactionLev = reactionLev;
	}

	public String getTimingLev() {
		return timingLev;
	}

	public void setTimingLev(String timingLev) {
		this.timingLev = timingLev;
	}

	public String getPreviousNotify() {
		return previousNotify;
	}

	public void setPreviousNotify(String previousNotify) {
		this.previousNotify = previousNotify;
	}

	public String getIsComplaintYn() {
		return isComplaintYn;
	}

	public void setIsComplaintYn(String isComplaintYn) {
		this.isComplaintYn = isComplaintYn;
	}

	public String getIsOtherDeptYn() {
		return isOtherDeptYn;
	}

	public void setIsOtherDeptYn(String isOtherDeptYn) {
		this.isOtherDeptYn = isOtherDeptYn;
	}
	
	public String getDisDate() {
		return disDate;
	}

	public void setDisDate(String disDate) {
		this.disDate = disDate;
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
		builder.append("Cos0008Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", preResult=");
		builder.append(preResult);
		builder.append(", leftCaseReason=");
		builder.append(leftCaseReason);
		builder.append(", isCompleteYn=");
		builder.append(isCompleteYn);
		builder.append(", isContactYn=");
		builder.append(isContactYn);
		builder.append(", reactionLev=");
		builder.append(reactionLev);
		builder.append(", timingLev=");
		builder.append(timingLev);
		builder.append(", previousNotify=");
		builder.append(previousNotify);
		builder.append(", isComplaintYn=");
		builder.append(isComplaintYn);
		builder.append(", isOtherDeptYn=");
		builder.append(isOtherDeptYn);
		builder.append(", disDate=");
		builder.append(disDate);
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
