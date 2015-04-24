package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CommonAuth implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private int auth;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonDtree commonDtree;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonGroup commonGroup;

    /** full constructor */
    public CommonAuth(int auth, String editId, String editDate, String editTime, com.kangdainfo.common.model.bo.CommonDtree commonDtree, com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.auth = auth;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonDtree = commonDtree;
        this.commonGroup = commonGroup;
    }

    /** default constructor */
    public CommonAuth() {
    }

    /** minimal constructor */
    public CommonAuth(int auth, com.kangdainfo.common.model.bo.CommonDtree commonDtree, com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.auth = auth;
        this.commonDtree = commonDtree;
        this.commonGroup = commonGroup;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAuth() {
        return this.auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
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

    public com.kangdainfo.common.model.bo.CommonDtree getCommonDtree() {
        return this.commonDtree;
    }

    public void setCommonDtree(com.kangdainfo.common.model.bo.CommonDtree commonDtree) {
        this.commonDtree = commonDtree;
    }

    public com.kangdainfo.common.model.bo.CommonGroup getCommonGroup() {
        return this.commonGroup;
    }

    public void setCommonGroup(com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.commonGroup = commonGroup;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonAuth [id=");
		builder.append(id);
		builder.append(", auth=");
		builder.append(auth);
		builder.append(", commonDtree=");
		builder.append(commonDtree);
		builder.append(", commonGroup=");
		builder.append(commonGroup);
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
