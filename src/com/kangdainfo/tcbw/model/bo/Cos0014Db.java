package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0014Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;	//案件號碼
    private String isLetterYn;	//是否再次發函詢問廠商
    private String dealWith;	//後續處置
    private String replyMemo;
    private String dealWithOther;	//後續處置(其他)
    private String assess;	//針對廠商回覆之評估
    private String summary;	//調查摘要
    private String resolution;	//會議決議簡述
    private String result;	//審查結果簡述
    private String precaution;	//預防矯正措施
    private String recycling;	//產品回收報告
    private String otherDisposal;	//其他處置紀錄
    private String remark;	//處理備註
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
    public Cos0014Db() {
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

	public String getIsLetterYn() {
		return isLetterYn;
	}

	public void setIsLetterYn(String isLetterYn) {
		this.isLetterYn = isLetterYn;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getDealWithOther() {
		return dealWithOther;
	}

	public void setDealWithOther(String dealWithOther) {
		this.dealWithOther = dealWithOther;
	}

	public String getAssess() {
		return assess;
	}

	public void setAssess(String assess) {
		this.assess = assess;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPrecaution() {
		return precaution;
	}

	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}

	public String getRecycling() {
		return recycling;
	}

	public void setRecycling(String recycling) {
		this.recycling = recycling;
	}

	public String getOtherDisposal() {
		return otherDisposal;
	}

	public void setOtherDisposal(String otherDisposal) {
		this.otherDisposal = otherDisposal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getReplyMemo() {
		return replyMemo;
	}

	public void setReplyMemo(String replyMemo) {
		this.replyMemo = replyMemo;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0014Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", isLetterYn=");
		builder.append(isLetterYn);
		builder.append(", dealWith=");
		builder.append(dealWith);
		builder.append(", replyMemo=");
		builder.append(replyMemo);
		builder.append(", dealWithOther=");
		builder.append(dealWithOther);
		builder.append(", assess=");
		builder.append(assess);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", resolution=");
		builder.append(resolution);
		builder.append(", result=");
		builder.append(result);
		builder.append(", precaution=");
		builder.append(precaution);
		builder.append(", recycling=");
		builder.append(recycling);
		builder.append(", otherDisposal=");
		builder.append(otherDisposal);
		builder.append(", remark=");
		builder.append(remark);
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
