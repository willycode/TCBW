package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Med7001Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;
    private String status;
    private String publDept;
    private String situation;
    private String medMainCategory;
    private String chProduct ;
    private String recycleclass;
    private String dataRevDate;
    private String publDate;
    private String manufactorName;
    private String contextsummary;
    private String lotNo;
    private String applicationaction;
    private String effectarea;
    private String effectnum;
    private String datasourWebSite;
    private String isrecycle;
    private String istranslate;
    private String translateman;
    private String medModel;
    private String productlotNo;
    private String incountrysituation;
    private String fdareceiveDate;
    private String fdareceiveNo;
    private String receivesummary;
    private String postdate;
    private String posesummary;
    private String completerecycledate;
    private String completereceiveno;
    private String completesummary;
    private String restartreason;
    
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
    private Set med7002Dbs;
	private Set med7003Dbs;
	private Set med7004Dbs;
	private Set med7005Dbs;
	
	/** default constructor */
    public Med7001Db() {
    }
  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getPublDept() {
		return publDept;
	}


	public void setPublDept(String publDept) {
		this.publDept = publDept;
	}


	public String getSituation() {
		return situation;
	}


	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getMedMainCategory() {
		return medMainCategory;
	}


	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = medMainCategory;
	}


	public String getChProduct() {
		return chProduct;
	}


	public void setChProduct(String chProduct) {
		this.chProduct = chProduct;
	}


	public String getRecycleclass() {
		return recycleclass;
	}


	public void setRecycleclass(String recycleclass) {
		this.recycleclass = recycleclass;
	}


	public String getDataRevDate() {
		return dataRevDate;
	}


	public void setDataRevDate(String dataRevDate) {
		this.dataRevDate = dataRevDate;
	}


	public String getPublDate() {
		return publDate;
	}


	public void setPublDate(String publDate) {
		this.publDate = publDate;
	}


	public String getManufactorName() {
		return manufactorName;
	}


	public void setManufactorName(String manufactorName) {
		this.manufactorName = manufactorName;
	}


	public String getContextsummary() {
		return contextsummary;
	}


	public void setContextsummary(String contextsummary) {
		this.contextsummary = contextsummary;
	}


	public String getLotNo() {
		return lotNo;
	}


	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}


	public String getApplicationaction() {
		return applicationaction;
	}


	public void setApplicationaction(String applicationaction) {
		this.applicationaction = applicationaction;
	}


	public String getEffectarea() {
		return effectarea;
	}


	public void setEffectarea(String effectarea) {
		this.effectarea = effectarea;
	}


	public String getEffectnum() {
		return effectnum;
	}


	public void setEffectnum(String effectnum) {
		this.effectnum = effectnum;
	}


	public String getDatasourWebSite() {
		return datasourWebSite;
	}


	public void setDatasourWebSite(String datasourWebSite) {
		this.datasourWebSite = datasourWebSite;
	}


	public String getIsrecycle() {
		return isrecycle;
	}


	public void setIsrecycle(String isrecycle) {
		this.isrecycle = isrecycle;
	}


	public String getIstranslate() {
		return istranslate;
	}


	public void setIstranslate(String istranslate) {
		this.istranslate = istranslate;
	}


	public String getTranslateman() {
		return translateman;
	}


	public void setTranslateman(String translateman) {
		this.translateman = translateman;
	}


	public String getMedModel() {
		return medModel;
	}


	public void setMedModel(String medModel) {
		this.medModel = medModel;
	}


	public String getProductlotNo() {
		return productlotNo;
	}


	public void setProductlotNo(String productlotNo) {
		this.productlotNo = productlotNo;
	}


	public String getIncountrysituation() {
		return incountrysituation;
	}


	public void setIncountrysituation(String incountrysituation) {
		this.incountrysituation = incountrysituation;
	}


	public String getFdareceiveDate() {
		return fdareceiveDate;
	}


	public void setFdareceiveDate(String fdareceiveDate) {
		this.fdareceiveDate = fdareceiveDate;
	}


	public String getFdareceiveNo() {
		return fdareceiveNo;
	}


	public void setFdareceiveNo(String fdareceiveNo) {
		this.fdareceiveNo = fdareceiveNo;
	}


	public String getReceivesummary() {
		return receivesummary;
	}


	public void setReceivesummary(String receivesummary) {
		this.receivesummary = receivesummary;
	}


	public String getPostdate() {
		return postdate;
	}


	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}


	public String getPosesummary() {
		return posesummary;
	}


	public void setPosesummary(String posesummary) {
		this.posesummary = posesummary;
	}


	public String getCompleterecycledate() {
		return completerecycledate;
	}


	public void setCompleterecycledate(String completerecycledate) {
		this.completerecycledate = completerecycledate;
	}


	public String getCompletereceiveno() {
		return completereceiveno;
	}


	public void setCompletereceiveno(String completereceiveno) {
		this.completereceiveno = completereceiveno;
	}


	public String getCompletesummary() {
		return completesummary;
	}


	public void setCompletesummary(String completesummary) {
		this.completesummary = completesummary;
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

	public Set getMed7002Dbs() {
		return med7002Dbs;
	}

	public void setMed7002Dbs(Set med7002Dbs) {
		this.med7002Dbs = med7002Dbs;
	}

	public Set getMed7003Dbs() {
		return med7003Dbs;
	}

	public void setMed7003Dbs(Set med7003Dbs) {
		this.med7003Dbs = med7003Dbs;
	}

	public Set getMed7004Dbs() {
		return med7004Dbs;
	}

	public void setMed7004Dbs(Set med7004Dbs) {
		this.med7004Dbs = med7004Dbs;
	}

	public Set getMed7005Dbs() {
		return med7005Dbs;
	}

	public void setMed7005Dbs(Set med7005Dbs) {
		this.med7005Dbs = med7005Dbs;
	}

	public String getRestartreason() {
		return restartreason;
	}

	public void setRestartreason(String restartreason) {
		this.restartreason = restartreason;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}
	
	
}
