package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;


public class Med7004Db  implements Serializable 
{
    /** identifier field */
    private Long id;   
    private String applicationID;
    private String applicationName;
    private String noticetranslatedate;
    private String checktranslatedate;
    private String checkcontextdesc;
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
    private Set med7002Dbs;    
    
    /** default constructor */
    

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med7004Db [id=");
		builder.append(id);
		builder.append(", med7001Db=");
		builder.append(med7001Db);
		builder.append(", noticetranslatedate=");
		builder.append(noticetranslatedate);
		builder.append(", checktranslatedate=");
	    builder.append(checktranslatedate);	
		builder.append(", checkcontextdesc=");
		builder.append(checkcontextdesc);
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
    
	public String getNoticetranslatedate() {
		return noticetranslatedate;
	}

	public void setNoticetranslatedate(String noticetranslatedate) {
		this.noticetranslatedate = noticetranslatedate;
	}

	public String getChecktranslatedate() {
		return checktranslatedate;
	}

	public void setChecktranslatedate(String checktranslatedate) {
		this.checktranslatedate = checktranslatedate;
	}

	public String getCheckcontextdesc() {
		return checkcontextdesc;
	}

	public void setCheckcontextdesc(String checkcontextdesc) {
		this.checkcontextdesc = checkcontextdesc;
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

	public Set getMed7002Dbs() {
		return med7002Dbs;
	}

	public void setMed7002Dbs(Set med7002Dbs) {
		this.med7002Dbs = med7002Dbs;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	
		
}
