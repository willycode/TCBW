package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med0006Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Med0001Db med0001Db;
   
    private String bulletinKind;//通報方式
    private String remark;//備註
    private String transDate;//傳送日期
    private String assignmentKind;//分派方式
    private String worker;//作業人員
    private String transType;//轉送類別(1:不良反應-轉送評估中 ,2:不良品-轉送評估中(初評),3:不良品-轉送評估中(複評))

    
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
    
	public Med0001Db getMed0001Db() {
		return med0001Db;
	}

	public void setMed0001Db(Med0001Db med0001Db) {
		this.med0001Db = med0001Db;
	}



	public String getBulletinKind() {
		return bulletinKind;
	}

	public void setBulletinKind(String bulletinKind) {
		this.bulletinKind = bulletinKind;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getAssignmentKind() {
		return assignmentKind;
	}

	public void setAssignmentKind(String assignmentKind) {
		this.assignmentKind = assignmentKind;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}	
	
	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
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
    public Med0006Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med0006Db [id=");
		builder.append(id);
		builder.append(", med0001Db=");
		builder.append(med0001Db);
		builder.append(", bulletinKind=");
		builder.append(bulletinKind);		
		builder.append(", remark=");
	    builder.append(remark);	
		builder.append(", transDate=");
		builder.append(transDate);	
		builder.append(", assignmentKind=");
		builder.append(assignmentKind);	
		builder.append(", worker=");
		builder.append(worker);
		builder.append(", transType=");
		builder.append(transType);	
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
