package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos7001Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;
    private String prodtype;
    private String chProduct;
    private String dataRevDate;
    private String publDept;
    private String publCountry;
    private String manufactorCountry;
    private String ingredient;
    private String brand;
    private String situation;    
    private String publDate;
    private String subjecttype;
    private String subject;
    private String contextsummary;
    private String lotNo;
    private String datasourWebSite;
    private String estimateDate;
    private String isImport;
    private String ispublnews;
    private String ispublconsumer;
    private String publconsumerDate;
    private String lamp;
    private String searchdept;
    private String remark;
    private String aftereffect;
    private String status;
    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}		
	public String getApplNo() {return applNo;}
	public void setApplNo(String applNo) {this.applNo = applNo;}
	public String getProdtype() {return prodtype;}
	public void setProdtype(String prodtype) {this.prodtype = prodtype;}
	public String getChProduct() {return chProduct;}
	public void setChProduct(String chProduct) {this.chProduct = chProduct;}
	public String getDataRevDate() {return dataRevDate;}
	public void setDataRevDate(String dataRevDate) {this.dataRevDate = dataRevDate;}
	public String getPublDept() {return publDept;}
	public void setPublDept(String publDept) {this.publDept = publDept;}
	public String getPublCountry() {return publCountry;}
	public void setPublCountry(String publCountry) {this.publCountry = publCountry;}
	public String getManufactorCountry() {return manufactorCountry;}
	public void setManufactorCountry(String manufactorCountry) {this.manufactorCountry = manufactorCountry;}
	public String getIngredient() {return ingredient;}
	public void setIngredient(String ingredient) {this.ingredient = ingredient;}
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	public String getSituation() {return situation;}
	public void setSituation(String situation) {this.situation = situation;}
	public String getPublDate() {return publDate;}
	public void setPublDate(String publDate) {this.publDate = publDate;}
	public String getSubjecttype() {return subjecttype;}
	public void setSubjecttype(String subjecttype) {this.subjecttype = subjecttype;}
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	public String getContextsummary() {return contextsummary;}
	public void setContextsummary(String contextsummary) {this.contextsummary = contextsummary;}
	public String getLotNo() {return lotNo;}
	public void setLotNo(String lotNo) {this.lotNo = lotNo;}
	public String getDatasourWebSite() {return datasourWebSite;}
	public void setDatasourWebSite(String datasourWebSite) {this.datasourWebSite = datasourWebSite;}
	public String getEstimateDate() {return estimateDate;}
	public void setEstimateDate(String estimateDate) {this.estimateDate = estimateDate;}
	public String getIsImport() {return isImport;}
	public void setIsImport(String isImport) {this.isImport = isImport;}
	public String getIspublnews() {return ispublnews;}
	public void setIspublnews(String ispublnews) {this.ispublnews = ispublnews;}
	public String getIspublconsumer() {return ispublconsumer;}
	public void setIspublconsumer(String ispublconsumer) {this.ispublconsumer = ispublconsumer;}
	public String getPublconsumerDate() {return publconsumerDate;}
	public void setPublconsumerDate(String publconsumerDate) {this.publconsumerDate = publconsumerDate;}
	public String getLamp() {return lamp;}
	public void setLamp(String lamp) {this.lamp = lamp;}
	public String getSearchdept() {return searchdept;}
	public void setSearchdept(String searchdept) {this.searchdept = searchdept;}
	public String getRemark() {return remark;}
	public void setRemark(String remark) {this.remark = remark;}
	public String getAftereffect() {return aftereffect;}
	public void setAftereffect(String aftereffect) {this.aftereffect = aftereffect;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	public String getReceiveSystem() {return receiveSystem;}
	public void setReceiveSystem(String receiveSystem) {this.receiveSystem = receiveSystem;}
	public String getReceiveDate() {return receiveDate;}
	public void setReceiveDate(String receiveDate) {this.receiveDate = receiveDate;}
	public String getReceiveTime() {return receiveTime;}
	public void setReceiveTime(String receiveTime) {this.receiveTime = receiveTime;}
	public String getCreator() {return creator;}
	public void setCreator(String creator) {this.creator = creator;}
	public String getCreateDate() {return createDate;}
	public void setCreateDate(String createDate) {this.createDate = createDate;}
	public String getCreateTime() {return createTime;}
	public void setCreateTime(String createTime) {this.createTime = createTime;}
	public String getModifier() {return modifier;}
	public void setModifier(String modifier) {this.modifier = modifier;}
	public String getModifyDate() {return modifyDate;}
	public void setModifyDate(String modifyDate) {this.modifyDate = modifyDate;}
	public String getModifyTime() {return modifyTime;}
	public void setModifyTime(String modifyTime) {this.modifyTime = modifyTime;}
	
	
	/** default constructor */
    public Cos7001Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos7001Db [id=");
		builder.append(id);		
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", prodtype=");
		builder.append(prodtype);
		builder.append(", chProduct=");
		builder.append(chProduct);
		builder.append(", dataRevDate=");
		builder.append(dataRevDate);
		builder.append(", publDept=");
		builder.append(publDept);
		builder.append(", publCountry=");
		builder.append(publCountry);
		builder.append(", manufactorCountry=");
		builder.append(manufactorCountry);
		builder.append(", ingredient=");
		builder.append(ingredient);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", situation=");
		builder.append(situation);
		builder.append(", publDate=");
		builder.append(publDate);
		builder.append(", subjecttype=");
		builder.append(subjecttype);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", contextsummary=");
		builder.append(contextsummary);
		builder.append(", lotNo=");
		builder.append(lotNo);
		builder.append(", datasourWebSite=");
		builder.append(datasourWebSite);
		builder.append(", estimateDate=");
		builder.append(estimateDate);
		builder.append(", isImport=");
		builder.append(isImport);
		builder.append(", ispublnews=");
		builder.append(ispublnews);
		builder.append(", ispublconsumer=");
		builder.append(ispublconsumer);
		builder.append(", publconsumerDate=");
		builder.append(publconsumerDate);
		builder.append(", lamp=");
		builder.append(lamp);
		builder.append(", searchdept=");
		builder.append(searchdept);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", aftereffect=");
		builder.append(aftereffect);
		builder.append(", status=");
		builder.append(status);		
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
