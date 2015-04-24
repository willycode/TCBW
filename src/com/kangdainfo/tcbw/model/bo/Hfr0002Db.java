package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0002Db implements java.io.Serializable {

	private Long id;
	private Hfr0001Db hfr0001Db;
	private String permitKey;			
	private String permitNo;			
	private String chProduct; 
	private String enProduct; 
	private String ingredient;
	private String content;
	private String doseDay;
	private String doseNum;
//	private String frequencyNum;
//	private String frequencyMl;
	private String edibleDateS;
	private String edibleDateE;
	private String buySource;
	private String brand;
	private String type;
	private String medModel;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    private String frequency;
    private String frequencyUnit;
    
	public Hfr0002Db(){
		
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
	
	public String getEnProduct() {
		return enProduct;
	}
	
	public void setEnProduct(String enProduct) {
		this.enProduct = enProduct;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getDoseDay() {
		return doseDay;
	}
	
	public void setDoseDay(String doseDay) {
		this.doseDay = doseDay;
	}
	
	public String getDoseNum() {
		return doseNum;
	}
	
	public void setDoseNum(String doseNum) {
		this.doseNum = doseNum;
	}
	
	public String getEdibleDateS() {
		return edibleDateS;
	}
	
	public void setEdibleDateS(String edibleDateS) {
		this.edibleDateS = edibleDateS;
	}
	
	public String getEdibleDateE() {
		return edibleDateE;
	}
	
	public void setEdibleDateE(String edibleDateE) {
		this.edibleDateE = edibleDateE;
	}
	
	public String getBuySource() {
		return buySource;
	}

	public void setBuySource(String buySource) {
		this.buySource = buySource;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
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
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyUnit() {
		return frequencyUnit;
	}

	public void setFrequencyUnit(String frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}

}
