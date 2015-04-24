package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Drg8002Db implements Serializable{
	
	private Long id;
	private String lotNo;
	private Long reservesNum;
	private String reservesUnit;
	private Long sellnum;
	private String sellunit;
	private String receiveSystem;
	private String receiveDate;
	private String receiveTime;
	private String creator;
	private String createDate;
	private String createTime;
	private String modifier;
	private String modifyDate;
	private String modifyTime;
	private com.kangdainfo.tcbw.model.bo.Drg8001Db Drg8001Db;
	
	public Drg8002Db() {		
	    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Long getReservesNum() {
		return reservesNum;
	}

	public void setReservesNum(Long reservesNum) {
		this.reservesNum = reservesNum;
	}

	public String getReservesUnit() {
		return reservesUnit;
	}

	public void setReservesUnit(String reservesUnit) {
		this.reservesUnit = reservesUnit;
	}

	public Long getSellnum() {
		return sellnum;
	}

	public void setSellnum(Long sellnum) {
		this.sellnum = sellnum;
	}

	public String getSellunit() {
		return sellunit;
	}

	public void setSellunit(String sellunit) {
		this.sellunit = sellunit;
	}

	public com.kangdainfo.tcbw.model.bo.Drg8001Db getDrg8001Db() {
		return Drg8001Db;
	}

	public void setDrg8001Db(com.kangdainfo.tcbw.model.bo.Drg8001Db drg8001Db) {
		Drg8001Db = drg8001Db;
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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
}
