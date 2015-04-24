package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0004Db implements java.io.Serializable{

	private Long id;
	private Hfr0001Db hfr0001Db;
	private String doctorDate;
	private String bloodIndex;
	private String liverIndex;
	private String renalIndex;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    
    public Hfr0004Db(){
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hfr0001Db getHfr0001Db() {
		return hfr0001Db;
	}

	public void setHfr0001Db(Hfr0001Db hfr0001Db) {
		this.hfr0001Db = hfr0001Db;
	}

	public String getDoctorDate() {
		return doctorDate;
	}

	public void setDoctorDate(String doctorDate) {
		this.doctorDate = doctorDate;
	}

	public String getBloodIndex() {
		return bloodIndex;
	}

	public void setBloodIndex(String bloodIndex) {
		this.bloodIndex = bloodIndex;
	}

	public String getLiverIndex() {
		return liverIndex;
	}

	public void setLiverIndex(String liverIndex) {
		this.liverIndex = liverIndex;
	}

	public String getRenalIndex() {
		return renalIndex;
	}

	public void setRenalIndex(String renalIndex) {
		this.renalIndex = renalIndex;
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
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
	
}
