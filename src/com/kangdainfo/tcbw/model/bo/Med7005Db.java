package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med7005Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    private String translatedate;
    private String translatecontext;
    private String recheckdate;
    private String completecheckdate;
    private String publdate;
    private String effectivedate;
    private String subject;
    private String context;
    private String remark;
    private String datasource;
    private String ispublnews;
    private String ispublconsumer;
    private String publconsumerDate;
    private String isfdaweb;
    private String lamp;   
    private String adrweb;//公告網址
    private String trans;//是否透過轉檔轉入
	private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間
    private Med7001Db med7001Db;
    
	/** default constructor */
    public Med7005Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med7005Db [id=");
		builder.append(id);
		builder.append(", med7001Db=");
		builder.append(med7001Db);
		builder.append(", translatedate=");
		builder.append(translatedate);
	    builder.append(", translatecontext=");
		builder.append(translatecontext);
	    builder.append(", recheckdate=");
		builder.append(recheckdate);
	    builder.append(", completecheckdate=");
		builder.append(completecheckdate);
	    builder.append(", publdate=");
		builder.append(publdate);
	    builder.append(", effectivedate=");
		builder.append(effectivedate);
	    builder.append(", subject=");
		builder.append(subject);
	    builder.append(", context=");
		builder.append(context);
	    builder.append(", remark=");
		builder.append(remark);
	    builder.append(", datasource=");
		builder.append(datasource);
		builder.append(", ispublnews=");
		builder.append(ispublnews);
		builder.append(", ispublconsumer=");
		builder.append(ispublconsumer);
		builder.append(", isfdaweb=");
		builder.append(isfdaweb);
		builder.append(", lamp=");
		builder.append(lamp);
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
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
	
	public String getTranslatedate() {
		return translatedate;
	}

	public void setTranslatedate(String translatedate) {
		this.translatedate = translatedate;
	}

	public String getTranslatecontext() {
		return translatecontext;
	}

	public void setTranslatecontext(String translatecontext) {
		this.translatecontext = translatecontext;
	}

	public String getRecheckdate() {
		return recheckdate;
	}

	public void setRecheckdate(String recheckdate) {
		this.recheckdate = recheckdate;
	}

	public String getCompletecheckdate() {
		return completecheckdate;
	}

	public void setCompletecheckdate(String completecheckdate) {
		this.completecheckdate = completecheckdate;
	}

	public String getPubldate() {
		return publdate;
	}

	public void setPubldate(String publdate) {
		this.publdate = publdate;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getIspublnews() {
		return ispublnews;
	}

	public void setIspublnews(String ispublnews) {
		this.ispublnews = ispublnews;
	}

	public String getIspublconsumer() {
		return ispublconsumer;
	}

	public void setIspublconsumer(String ispublconsumer) {
		this.ispublconsumer = ispublconsumer;
	}

	public String getIsfdaweb() {
		return isfdaweb;
	}

	public void setIsfdaweb(String isfdaweb) {
		this.isfdaweb = isfdaweb;
	}

	public String getLamp() {
		return lamp;
	}

	public void setLamp(String lamp) {
		this.lamp = lamp;
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

	public Med7001Db getMed7001Db() {
		return med7001Db;
	}

	public void setMed7001Db(Med7001Db med7001Db) {
		this.med7001Db = med7001Db;
	}

	public String getPublconsumerDate() {
		return publconsumerDate;
	}

	public void setPublconsumerDate(String publconsumerDate) {
		this.publconsumerDate = publconsumerDate;
	}    
	
	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public String getAdrweb() {
		return adrweb;
	}

	public void setAdrweb(String adrweb) {
		this.adrweb = adrweb;
	}
	
	
}
