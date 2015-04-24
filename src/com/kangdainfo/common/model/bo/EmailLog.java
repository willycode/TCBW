package com.kangdainfo.common.model.bo;

import java.io.Serializable;

public class EmailLog  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String systemType;//系統別
    private String title;//主旨
    private String mailBody;//內容
    private String state;//發送狀況
	private String applNo;
	private String failReasons;//失敗原因
	private String mail;//發送信箱
	private String bccmail;//發送信箱
	private String ccmail;//發送信箱
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間

	/** default constructor */
    public EmailLog() {
    }

	public String toString() 
	{
		
		StringBuilder builder = new StringBuilder();
		builder.append("EmailLog [id=");
		builder.append(id);
		builder.append(", systemType=");
		builder.append(systemType);
		builder.append(", title=");
		builder.append(title);
		builder.append(", mailBody=");
		builder.append(mailBody);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", state=");
		builder.append(state);
		builder.append(", failReasons=");
		builder.append(failReasons);
		builder.append(", mail=");
		builder.append(mail);
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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}


	public String getFailReasons() {
		return failReasons;
	}

	public void setFailReasons(String failReasons) {
		this.failReasons = failReasons;
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

	public String getBccmail() {
		return bccmail;
	}

	public void setBccmail(String bccmail) {
		this.bccmail = bccmail;
	}

	public String getCcmail() {
		return ccmail;
	}

	public void setCcmail(String ccmail) {
		this.ccmail = ccmail;
	}
	
	
}
