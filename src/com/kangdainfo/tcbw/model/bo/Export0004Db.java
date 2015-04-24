package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Export0004Db implements Serializable {
	/** identifier field */
	private Long id;
	
	private Integer seq; 	  //排序
	private String field; 	  //欄位名稱
	private String fieldname; //欄位名中文名稱
	private String tablename; //資料表名稱
	private String codename;  //代碼名稱
	private String isStop; 	  //是否停用
	private String isMany; 	  //是否為多筆
	private String manyName;  //多筆名稱
	
	private String receiveSystem; //介接異動系統
	private String receiveDate;	  //介接異動日期
	private String receiveTime;   //介接異動時間
	private String creator;       //建檔者
	private String createDate;    //建檔日期
	private String createTime;    //建檔時間
	private String modifier;      //最後異動者
	private String modifyDate;    //最後異動日期
	private String modifyTime;    //最後異動時間
	
	/** default constructor */
	public Export0004Db(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getIsMany() {
		return isMany;
	}

	public void setIsMany(String isMany) {
		this.isMany = isMany;
	}

	public String getManyName() {
		return manyName;
	}

	public void setManyName(String manyName) {
		this.manyName = manyName;
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
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Export0004Db [id=");
		sb.append(id);
		sb.append(", seq");
		sb.append(seq);
		sb.append(", field");
		sb.append(field);
		sb.append(", fieldname");
		sb.append(fieldname);
		sb.append(", tablename");
		sb.append(tablename);
		sb.append(", codename");
		sb.append(codename);
		sb.append(", isStop");
		sb.append(isStop);
		sb.append(", isMany");
		sb.append(isMany);
		sb.append(", manyName");
		sb.append(manyName);
		sb.append(", receiveSystem=");
		sb.append(receiveSystem);
		sb.append(", receiveDate=");
		sb.append(receiveDate);
		sb.append(", receiveTime=");
		sb.append(receiveTime);
		sb.append(", creator=");
		sb.append(creator);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", createTime=");
		sb.append(createTime);
		sb.append(", modifier=");
		sb.append(modifier);
		sb.append(", modifyDate=");
		sb.append(modifyDate);
		sb.append(", modifyTime=");
		sb.append(modifyTime);
		sb.append("]");
		
		return sb.toString();
	}
}
