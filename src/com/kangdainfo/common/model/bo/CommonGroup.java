package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Set;


/** @author Hibernate CodeGenerator */
public class CommonGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -739829669454869340L;

	public interface FieldNames {
		String id = "id";
		String groupName = "groupName";
		String groupDesc = "groupDesc";
		String subSystem = "subSystem";
		String editId = "editId";
		String editDate = "editDate";
		String editTime = "editTime";
		String commonUsers = "commonUsers";
		String commonAuths = "commonAuths";
	}	

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String groupName;

    /** nullable persistent field */
    private String groupDesc;
    
    private String subSystem;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** persistent field */
    private Set commonAuths;

    /** persistent field */
    private Set commonUsers;

    /** full constructor */
    public CommonGroup(String groupName, String groupDesc, String subSystem, String editId, String editDate, String editTime, Set commonAuths, Set commonUsers) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonAuths = commonAuths;
        this.commonUsers = commonUsers;
        this.subSystem = subSystem;
    }

    /** default constructor */
    public CommonGroup() {
    }

    /** minimal constructor */
    public CommonGroup(String groupName, Set commonAuths, Set commonUsers) {
        this.groupName = groupName;
        this.commonAuths = commonAuths;
        this.commonUsers = commonUsers;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return this.groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
    
    public String getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
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

    public Set getCommonAuths() {
        return this.commonAuths;
    }

    public void setCommonAuths(Set commonAuths) {
        this.commonAuths = commonAuths;
    }

    public Set getCommonUsers() {
        return this.commonUsers;
    }

    public void setCommonUsers(Set commonUsers) {
        this.commonUsers = commonUsers;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonGroup [id=");
		builder.append(id);
		builder.append(", groupDesc=");
		builder.append(groupDesc);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", subSystem=");
		builder.append(subSystem);
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
