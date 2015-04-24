package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg4007Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    private String permitKey2;		//	懷疑療效不等藥品-許可證字
    private String permitNo2;		//	懷疑療效不等藥品-許可證號
    private String productName2;	//	懷疑療效不等藥品-商品名
    private String assessDate;		//	評估日期
    private String assessMan;		//	評估人員
    private String assessDesc;		//	評估意見
    private String isClose;			//	是否完成品質調查
    private Set drg4011Dbs;
    
    private String receiveSystem;	//介接異動系統
    private String receiveDate;		//介接異動日期
    private String receiveTime;		//介接異動時間
    private String creator;			//建檔者
    private String createDate;		//建檔日期
    private String createTime;		//建檔時間
    private String modifier;		//最後異動者
    private String modifyDate;		//最後異動日期
    private String modifyTime;		//最後異動時間

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

	public String getAssessDesc() {
		return assessDesc;
	}

	public void setAssessDesc(String assessDesc) {
		this.assessDesc = assessDesc;
	}

	public String getIsClose() {
		return isClose;
	}

	public Set getDrg4011Dbs() {
		return drg4011Dbs;
	}

	public void setDrg4011Dbs(Set drg4011Dbs) {
		this.drg4011Dbs = drg4011Dbs;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
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
    public Drg4007Db() {
    }	
}
