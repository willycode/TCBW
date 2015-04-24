package com.kangdainfo.common.model.bo;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class CommonDepartment implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer parentId;

    /** nullable persistent field */
    private Integer level;

    /** persistent field */
    private String departmentCode;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String shortCode, shortName;

    /** nullable persistent field */
    private String fullName;

    /** nullable persistent field */
    private String fullCode;

    /** nullable persistent field */
    private String preFullCode;    

    /** nullable persistent field */
    private String phone;

    /** nullable persistent field */
    private String fax;

    /** nullable persistent field */
    private String zip;

    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String homepage;

    /** nullable persistent field */
    private String chiefTitle;

    /** nullable persistent field */
    private String chiefName;

    /** nullable persistent field */
    private String isSchool;

    /** nullable persistent field */
    private String schoolType;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** full constructor */
    public CommonDepartment(Integer parentId, Integer level, String departmentCode, String department, String shortName, String fullName, String fullCode, String preFullCode, String phone, String fax, String zip, String address, String email, String description, String homepage, String chiefTitle, String chiefName, String isSchool, String schoolType, String editId, String editDate, String editTime) {
        this.parentId = parentId;
        this.level = level;
        this.departmentCode = departmentCode;
        this.department = department;
        this.shortName = shortName;
        this.fullName = fullName;
        this.fullCode = fullCode;
        this.preFullCode = preFullCode;
        this.phone = phone;
        this.fax = fax;
        this.zip = zip;
        this.address = address;
        this.email = email;
        this.description = description;
        this.homepage = homepage;
        this.chiefTitle = chiefTitle;
        this.chiefName = chiefName;
        this.isSchool = isSchool;
        this.schoolType = schoolType;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
    }

    /** default constructor */
    public CommonDepartment() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDepartmentCode() {
        return this.departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullCode() {
        return this.fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    public String getPreFullCode() {
        return this.preFullCode;
    }

    public void setPreFullCode(String preFullCode) {
        this.preFullCode = preFullCode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getChiefTitle() {
        return this.chiefTitle;
    }

    public void setChiefTitle(String chiefTitle) {
        this.chiefTitle = chiefTitle;
    }

    public String getChiefName() {
        return this.chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getIsSchool() {
        return this.isSchool;
    }

    public void setIsSchool(String isSchool) {
        this.isSchool = isSchool;
    }

    public String getSchoolType() {
        return this.schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getEditId() {
        return this.editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public String getEditDate() {
        return this.editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditTime() {
        return this.editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
    
    private CommonDepartment issueDepartment;
    /**
     * 沒有實際存在資料庫，只是為了方便
     * @return
     */
	public CommonDepartment getIssueDepartment() {
		return issueDepartment;
	}
	/**
	 * 沒有實際存在資料庫，只是為了方便
	 * @param issueDepartment
	 */
	public void setIssueDepartment(CommonDepartment issueDepartment) {
		this.issueDepartment = issueDepartment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonDepartment [id=");
		builder.append(id);
		builder.append(", address=");
		builder.append(address);
		builder.append(", chiefName=");
		builder.append(chiefName);
		builder.append(", chiefTitle=");
		builder.append(chiefTitle);
		builder.append(", department=");
		builder.append(department);
		builder.append(", departmentCode=");
		builder.append(departmentCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", email=");
		builder.append(email);
		builder.append(", fax=");
		builder.append(fax);
		builder.append(", fullCode=");
		builder.append(fullCode);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", level=");
		builder.append(level);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", shortCode=");
		builder.append(shortCode);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", editId=");
		builder.append(editId);
		builder.append(", editDate=");
		builder.append(editDate);
		builder.append(", editTime=");
		builder.append(editTime);
		builder.append("]");
		return builder.toString();
	}
    

}
