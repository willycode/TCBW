package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg4009Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String ingredient;		//成分
    private String notifyDate;      //發信日期
    private String replyDate;		//回覆日期
    private String medAgencyName;	//醫事機構名稱
    private String isEffectChange;	//有療效不等狀況
    private String isBrandChange;	//有更換廠牌無療效不等狀況
    private String noBrandChange;	//未更換廠牌
    private String beforeBrand;		//轉換前廠牌
    private String afterBrand;		//轉換後廠牌
    private String comment;			//補充說明

    private Drg4005Db drg4005Db;
    private Con1009Db con1009Db;
    
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

	public String getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getMedAgencyName() {
		return medAgencyName;
	}

	public void setMedAgencyName(String medAgencyName) {
		this.medAgencyName = medAgencyName;
	}

	public String getIsEffectChange() {
		return isEffectChange;
	}

	public void setIsEffectChange(String isEffectChange) {
		this.isEffectChange = isEffectChange;
	}

	public String getIsBrandChange() {
		return isBrandChange;
	}

	public void setIsBrandChange(String isBrandChange) {
		this.isBrandChange = isBrandChange;
	}

	public String getNoBrandChange() {
		return noBrandChange;
	}

	public void setNoBrandChange(String noBrandChange) {
		this.noBrandChange = noBrandChange;
	}

	public String getBeforeBrand() {
		return beforeBrand;
	}

	public void setBeforeBrand(String beforeBrand) {
		this.beforeBrand = beforeBrand;
	}

	public String getAfterBrand() {
		return afterBrand;
	}

	public void setAfterBrand(String afterBrand) {
		this.afterBrand = afterBrand;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Drg4005Db getDrg4005Db() {
		return drg4005Db;
	}

	public void setDrg4005Db(Drg4005Db drg4005Db) {
		this.drg4005Db = drg4005Db;
	}

	public Con1009Db getCon1009Db() {
		return con1009Db;
	}

	public void setCon1009Db(Con1009Db con1009Db) {
		this.con1009Db = con1009Db;
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
    public Drg4009Db() {
    }	
}
