package com.kangdainfo.common.model.bo;

import java.util.HashSet;
import java.util.Set;

/**
 * EmpMaster entity. @author MyEclipse Persistence Tools
 */

public class EmpMaster implements java.io.Serializable {

	// Fields

	private Long id;
	private CommonDepartment commonDepartment;
	private CommonCode commonCode;
	private String empName;
	private String empAddr;
	private String empDob;
	private String userId;
	private String modDate;
	private String modTime;
	private Set empDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public EmpMaster() {
	}

	/** full constructor */
	public EmpMaster(CommonDepartment commonDepartment, CommonCode commonCode,
			String empName, String empAddr, String empDob, String userId,
			String modDate, String modTime, Set empDetails) {
		this.commonDepartment = commonDepartment;
		this.commonCode = commonCode;
		this.empName = empName;
		this.empAddr = empAddr;
		this.empDob = empDob;
		this.userId = userId;
		this.modDate = modDate;
		this.modTime = modTime;
		this.empDetails = empDetails;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommonDepartment getCommonDepartment() {
		return this.commonDepartment;
	}

	public void setCommonDepartment(CommonDepartment commonDepartment) {
		this.commonDepartment = commonDepartment;
	}

	public CommonCode getCommonCode() {
		return this.commonCode;
	}

	public void setCommonCode(CommonCode commonCode) {
		this.commonCode = commonCode;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddr() {
		return this.empAddr;
	}

	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}

	public String getEmpDob() {
		return this.empDob;
	}

	public void setEmpDob(String empDob) {
		this.empDob = empDob;
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

	public Set getEmpDetails() {
		return this.empDetails;
	}

	public void setEmpDetails(Set empDetails) {
		this.empDetails = empDetails;
	}

}