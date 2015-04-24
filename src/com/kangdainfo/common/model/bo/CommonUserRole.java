package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CommonUserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9070577865425372326L;

	public interface FieldNames {
		String id = "id";
		String roleId = "roleId";
		String editId = "editId";
		String editDate = "editDate";
		String editTime = "editTime";
		String commonUser = "commonUser";
		String commonGroup = "commonGroup";
	}



    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer roleId;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** nullable persistent field */
    private com.kangdainfo.common.model.bo.CommonUser commonUser;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonGroup commonGroup;

    /** full constructor */
    public CommonUserRole(Integer id, Integer roleId, String editId, String editDate, String editTime, com.kangdainfo.common.model.bo.CommonUser commonUser, com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.id = id;
        this.roleId = roleId;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonUser = commonUser;
        this.commonGroup = commonGroup;
    }

    /** default constructor */
    public CommonUserRole() {
    }

    /** minimal constructor */
    public CommonUserRole(Integer id, Integer roleId, com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.id = id;
        this.roleId = roleId;
        this.commonGroup = commonGroup;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public com.kangdainfo.common.model.bo.CommonUser getCommonUser() {
        return this.commonUser;
    }

    public void setCommonUser(com.kangdainfo.common.model.bo.CommonUser commonUser) {
        this.commonUser = commonUser;
    }

    public com.kangdainfo.common.model.bo.CommonGroup getCommonGroup() {
        return this.commonGroup;
    }

    public void setCommonGroup(com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.commonGroup = commonGroup;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("editId", getEditId())
            .append("editDate", getEditDate())
            .append("editTime", getEditTime())
            .append("commonGroup", getCommonGroup())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CommonUserRole) ) return false;
        CommonUserRole castOther = (CommonUserRole) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }	

	private int[] roleIdArray = new int[] {1,2,3};
	private String[] roleName = new String[] {"一般使用者","業務管理者","系統管理者"};
	
	public String getRoleName() {
		for (int i=0; i<roleIdArray.length; i++) {
			if (i==getRoleId()) return roleName[i];
		}
		return "";
	}
	
	public String getRoleOption() {
        StringBuilder sb = new StringBuilder().append("<option value=''></option>");		
		for (int i=0; i<roleIdArray.length; i++) {
            sb.append("<option value='").append(roleIdArray[i]).append("' ");
            if (getRoleId() == roleIdArray[i]) sb.append(" selected ");
            sb.append(">").append(roleName[i]).append("</option>\n");    			
		}
		return sb.toString();
	}
	
	/**
	 * 若不是管理者，一律回傳field_RO
	 * <br>否則回傳原始的傳入的adminCssClass
	 * @param adminCssClass
	 * @return
	 */
	public String getCssClass(String adminCssClass) {
		if (getRoleId()>2) {
			return adminCssClass;
		} else {
			if (adminCssClass.indexOf("field_Q")!=-1) return "field_QRO";
			else return "field_RO";
		}
	}
	
	public boolean isUserLevel() {
		if (getRoleId()>1) return false;
		else return true;
	}
		
}
