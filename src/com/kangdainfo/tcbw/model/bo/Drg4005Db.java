package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg4005Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String permitKey2;		//懷疑療效不等藥品-許可證字
    private String permitNo2;		//懷疑療效不等藥品-許可證號
    private String productName2;	//懷疑療效不等藥品-商品名
    private String assessDate;		//初評日期
    private String assessMan;		//初評人員
    private String assessResult;	//評估結果
    private String assessDesc;		//備註
    private String isClose;			//是否完成諮議會評估
    private String isMedSend;		//是否已作過醫院調查

    private Set drg4008Dbs;
    private Set drg4009Dbs;
    
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
	
	public String getPermitKey2() {
		return permitKey2;
	}

	public void setPermitKey2(String permitKey2) {
		this.permitKey2 = permitKey2;
	}

	public String getPermitNo2() {
		return permitNo2;
	}

	public void setPermitNo2(String permitNo2) {
		this.permitNo2 = permitNo2;
	}

	public String getProductName2() {
		return productName2;
	}

	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}

	public String getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(String assessDate) {
		this.assessDate = assessDate;
	}

	public String getAssessMan() {
		return assessMan;
	}

	public void setAssessMan(String assessMan) {
		this.assessMan = assessMan;
	}

	public String getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
	}

	public String getAssessDesc() {
		return assessDesc;
	}

	public void setAssessDesc(String assessDesc) {
		this.assessDesc = assessDesc;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getIsMedSend() {
		return isMedSend;
	}

	public void setIsMedSend(String isMedSend) {
		this.isMedSend = isMedSend;
	}

	public Set getDrg4008Dbs() {
		return drg4008Dbs;
	}

	public void setDrg4008Dbs(Set drg4008Dbs) {
		this.drg4008Dbs = drg4008Dbs;
	}

	public Set getDrg4009Dbs() {
		return drg4009Dbs;
	}

	public void setDrg4009Dbs(Set drg4009Dbs) {
		this.drg4009Dbs = drg4009Dbs;
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
    public Drg4005Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg0003Db [id=");
		builder.append(id);
		builder.append(", assessDate=");
		builder.append(assessDate);
		builder.append(", assessMan=");
		builder.append(assessMan);
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
