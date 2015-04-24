package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos4005Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Cos4002Db cos4002Db;	//化妝品不良反應資料檔    
    private String useDateS;  //使用期間起
    private String useDateE;  //使用期間迄
    private String tradeDate;	//購買日期
    private String cName;	//品名
    private String manufactorName;	//製造廠/進口代理商
    private String useRate;	//使用頻率
    private String useMethod;	//使用方法
    private String manufactorNo;	//製造批號/製造日期
    private String expirationDate;	//保存期限
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
    public Cos4005Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cos4002Db getCos4002Db() {
		return cos4002Db;
	}

	public void setCos4002Db(Cos4002Db cos4002Db) {
		this.cos4002Db = cos4002Db;
	}

    public String getUseDateS() {
		return useDateS;
	}

	public void setUseDateS(String useDateS) {
		this.useDateS = useDateS;
	}

	public String getUseDateE() {
		return useDateE;
	}

	public void setUseDateE(String useDateE) {
		this.useDateE = useDateE;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getManufactorName() {
		return manufactorName;
	}

	public void setManufactorName(String manufactorName) {
		this.manufactorName = manufactorName;
	}

	public String getUseRate() {
		return useRate;
	}

	public void setUseRate(String useRate) {
		this.useRate = useRate;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getManufactorNo() {
		return manufactorNo;
	}

	public void setManufactorNo(String manufactorNo) {
		this.manufactorNo = manufactorNo;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
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

	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos4005Db [id=");
		builder.append(id);
		builder.append(", cos4002Db=");
		builder.append(cos4002Db);
		builder.append(", useDateS=");
		builder.append(useDateS);
		builder.append(", useDateE=");
		builder.append(useDateE);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", cName=");
		builder.append(cName);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", useRate=");
		builder.append(useRate);
		builder.append(", useMethod=");
		builder.append(useMethod);
		builder.append(", manufactorNo=");
		builder.append(manufactorNo);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
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