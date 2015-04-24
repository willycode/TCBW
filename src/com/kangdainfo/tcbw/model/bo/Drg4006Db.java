package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg4006Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    private String manufactorID;	//	製造商統編
    private String permitKey2;		//	懷疑療效不等藥品-許可證字
    private String permitNo2;		//	懷疑療效不等藥品-許可證號
    private String productName2;	//	懷疑療效不等藥品-商品名
    private String replyDate;		//	回覆日期
    private String review1;			//	製程管制與最終產品之結果檢討
    private String review2;			//	原料(含來源)/包材或配方變更之檢討
    private String review3;			//	製程/規格或分析方法變更之檢討
    private String review4;			//	安定性監測計畫的結果與任何不良趨勢之檢討
    private String review5;			//	一切顯著偏離或不相符(包括品質相關之客訴/回收…等)之調查及實施CAPA後成果之檢討
    private String review6;			//	任何其他先前產品製程/設備或環境改正行動之適當性檢討
    private String isClose;			//	是否完成廠商回覆  
    private Set drg4010Dbs;
    
    private String receiveSystem;	//介接異動系統
    private String receiveDate;		//介接異動日期
    private String receiveTime;		//介接異動時間
    private String creator;			//建檔者
    private String createDate;		//建檔日期
    private String createTime;		//建檔時間
    private String modifier;		//最後異動者
    private String modifyDate;		//最後異動日期
    private String modifyTime;		//最後異動時間

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufactorID() {
		return manufactorID;
	}

	public void setManufactorID(String manufactorID) {
		this.manufactorID = manufactorID;
	}

	public String getPermitKey2() {
		return permitKey2;
	}

	public void setPermitKey2(String permitKey2) {
		this.permitKey2 = permitKey2;
	}

	public String getPermitNo2() {
		return permitNo2;
	}

	public void setPermitNo2(String permitNo2) {
		this.permitNo2 = permitNo2;
	}

	public String getProductName2() {
		return productName2;
	}

	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getReview1() {
		return review1;
	}

	public void setReview1(String review1) {
		this.review1 = review1;
	}

	public String getReview2() {
		return review2;
	}

	public void setReview2(String review2) {
		this.review2 = review2;
	}

	public String getReview3() {
		return review3;
	}

	public void setReview3(String review3) {
		this.review3 = review3;
	}

	public String getReview4() {
		return review4;
	}

	public void setReview4(String review4) {
		this.review4 = review4;
	}

	public String getReview5() {
		return review5;
	}

	public void setReview5(String review5) {
		this.review5 = review5;
	}

	public String getReview6() {
		return review6;
	}

	public void setReview6(String review6) {
		this.review6 = review6;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public Set getDrg4010Dbs() {
		return drg4010Dbs;
	}

	public void setDrg4010Dbs(Set drg4010Dbs) {
		this.drg4010Dbs = drg4010Dbs;
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
    public Drg4006Db() {
    }	
}
