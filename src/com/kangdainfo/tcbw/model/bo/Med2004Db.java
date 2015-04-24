package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med2004Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Med2001Db med2001Db;
   
    private String cName;//品名
    private String permit;//許可證字號
    private String permitNumber;//許可證字號
    private String permitFirm;//許可證申請商
    private String model;//型號
    private String mainCategoryCode;//器材主類別
    private String mainCategory;//器材主類別
    private String useDate;//使用日期
    private String useReason;//使用原因

	/** persistent field */
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
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Med2001Db getMed2001Db() {
		return med2001Db;
	}

	public void setMed2001Db(Med2001Db med2001Db) {
		this.med2001Db = med2001Db;
	}
	

	
	public String getMainCategoryCode() {
		return mainCategoryCode;
	}

	public void setMainCategoryCode(String mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	
	
	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	public String getPermitFirm() {
		return permitFirm;
	}

	public void setPermitFirm(String permitFirm) {
		this.permitFirm = permitFirm;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getUseReason() {
		return useReason;
	}

	public void setUseReason(String useReason) {
		this.useReason = useReason;
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
    public Med2004Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med2004Db [id=");
		builder.append(id);
		builder.append(", med2001Db=");
		builder.append(med2001Db);
		builder.append(", cName=");
		builder.append(cName);		
		builder.append(", permitNumber=");
	    builder.append(permitNumber);	
		builder.append(", permitFirm=");
		builder.append(permitFirm);	
		builder.append(", model=");
		builder.append(model);	
		builder.append(", mainCategory=");
		builder.append(mainCategory);	
		builder.append(", useDate=");
		builder.append(useDate);	
		builder.append(", useReason=");
		builder.append(useReason);	
		
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
