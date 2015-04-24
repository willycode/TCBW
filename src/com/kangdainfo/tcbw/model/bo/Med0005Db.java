package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med0005Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Med0001Db med0001Db;
   
    private String cName;//學名/商品名
    private String content;//含量
    private String formulation;//劑型
    private String drgApproach;//給藥途徑
    private String model;//型號
    private String dose;//劑量
    private String frequency;//頻率
    private String sDate;//起日期
    private String eDate;//迄日期
    private String medCauses;//用藥原因


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
	
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getDrgApproach() {
		return drgApproach;
	}

	public void setDrgApproach(String drgApproach) {
		this.drgApproach = drgApproach;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getMedCauses() {
		return medCauses;
	}

	public void setMedCauses(String medCauses) {
		this.medCauses = medCauses;
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
    public Med0005Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med0005Db [id=");
		builder.append(id);
		builder.append(", med0001Db=");
		builder.append(med0001Db);
		builder.append(", cName=");
		builder.append(cName);
	    builder.append(", content=");
		builder.append(content);
	    builder.append(", formulation=");
		builder.append(formulation);
	    builder.append(", drgApproach=");
		builder.append(drgApproach);
	    builder.append(", model=");
		builder.append(model);
	    builder.append(", dose=");
		builder.append(dose);
	    builder.append(", frequency=");
		builder.append(frequency);
	    builder.append(", sDate=");
		builder.append(sDate);
	    builder.append(", eDate=");
		builder.append(eDate);
	    builder.append(", medCauses=");
		builder.append(medCauses);

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
