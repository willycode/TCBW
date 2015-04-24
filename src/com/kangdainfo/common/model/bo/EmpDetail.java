package com.kangdainfo.common.model.bo;

/**
 * EmpDetail entity. @author MyEclipse Persistence Tools
 */

public class EmpDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private EmpMaster empMaster;
	private CommonCode commonCode;
	private String relSsn;
	private String relName;
	private String userId;
	private String modDate;
	private String modTime;

	// Constructors

	/** default constructor */
	public EmpDetail() {
	}

	/** minimal constructor */
	public EmpDetail(EmpMaster empMaster) {
		this.empMaster = empMaster;
	}

	/** full constructor */
	public EmpDetail(EmpMaster empMaster, CommonCode commonCode, String relSsn,
			String relName, String userId, String modDate, String modTime) {
		this.empMaster = empMaster;
		this.commonCode = commonCode;
		this.relSsn = relSsn;
		this.relName = relName;
		this.userId = userId;
		this.modDate = modDate;
		this.modTime = modTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmpMaster getEmpMaster() {
		return this.empMaster;
	}

	public void setEmpMaster(EmpMaster empMaster) {
		this.empMaster = empMaster;
	}

	public CommonCode getCommonCode() {
		return this.commonCode;
	}

	public void setCommonCode(CommonCode commonCode) {
		this.commonCode = commonCode;
	}

	public String getRelSsn() {
		return this.relSsn;
	}

	public void setRelSsn(String relSsn) {
		this.relSsn = relSsn;
	}

	public String getRelName() {
		return this.relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModDate() {
		return this.modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getModTime() {
		return this.modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

}