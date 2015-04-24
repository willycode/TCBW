package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Con3001Db  implements Serializable 
{
	/** identifier field */
    private Long id;    

    private String projType;//專案類別
	private String projName;//專案名稱
	private String permitKey;//許可證字
	private String permitNo;//許可證號
	private String chProduct;//商品名稱中文
	private String applicationId;//許可證持有商ID
	private String applicationName;//許可證持有商
	private String medSecCategory;//醫材次類別
	private String isclose;//是否結案
	private String maintainman;//維護人員
    
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
    public Con3001Db() {             

    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	
	
	

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getPermitKey() {
		return permitKey;
	}

	public void setPermitKey(String permitKey) {
		this.permitKey = permitKey;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getChProduct() {
		return chProduct;
	}

	public void setChProduct(String chProduct) {
		this.chProduct = chProduct;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getMedSecCategory() {
		return medSecCategory;
	}

	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = medSecCategory;
	}

	public String getIsclose() {
		return isclose;
	}

	public void setIsclose(String isclose) {
		this.isclose = isclose;
	}

	public String getMaintainman() {
		return maintainman;
	}

	public void setMaintainman(String maintainman) {
		this.maintainman = maintainman;
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
		builder.append("Con3001Db [id=");
		builder.append(id);			
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
