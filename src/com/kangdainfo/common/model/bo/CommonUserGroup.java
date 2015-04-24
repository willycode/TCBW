package com.kangdainfo.common.model.bo;

/**
 * CommonUserGroup entity. @author MyEclipse Persistence Tools
 */

public class CommonUserGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private CommonGroup commonGroup;
	private CommonUser commonUser;
	private String editId;
	private String editDate;
	private String editTime;

	// Constructors

	/** default constructor */
	public CommonUserGroup() {
	}

	/** minimal constructor */
	public CommonUserGroup(Integer id, CommonGroup commonGroup,
			CommonUser commonUser) {
		this.id = id;
		this.commonGroup = commonGroup;
		this.commonUser = commonUser;
	}

	/** full constructor */
	public CommonUserGroup(Integer id, CommonGroup commonGroup,
			CommonUser commonUser, String editId, String editDate,
			String editTime) {
		this.id = id;
		this.commonGroup = commonGroup;
		this.commonUser = commonUser;
		this.editId = editId;
		this.editDate = editDate;
		this.editTime = editTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommonGroup getCommonGroup() {
		return this.commonGroup;
	}

	public void setCommonGroup(CommonGroup commonGroup) {
		this.commonGroup = commonGroup;
	}

	public CommonUser getCommonUser() {
		return this.commonUser;
	}

	public void setCommonUser(CommonUser commonUser) {
		this.commonUser = commonUser;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonUserGroup [id=");
		builder.append(id);
		builder.append(", commonGroup=");
		builder.append(commonGroup);
		builder.append(", commonUser=");
		builder.append(commonUser);
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