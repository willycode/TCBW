package com.kangdainfo.common.model.bo;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class CommonMsgGroup implements Serializable {

    /** identifier field */
    private Long id;

    
    /** persistent field */
    private int groupId;
    private int userId;
    private int deptId; 

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonMsg commonMsg;

    /** full constructor */
    public CommonMsgGroup(int groupId, int userId, int deptId, com.kangdainfo.common.model.bo.CommonMsg commonMsg) {
        this.groupId = groupId;
        this.userId = userId;
        this.deptId = deptId;
        this.commonMsg = commonMsg;
    }

    /** default constructor */
    public CommonMsgGroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public com.kangdainfo.common.model.bo.CommonMsg getCommonMsg() {
        return this.commonMsg;
    }

    public void setCommonMsg(com.kangdainfo.common.model.bo.CommonMsg commonMsg) {
        this.commonMsg = commonMsg;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonMsgGroup [id=");
		builder.append(id);
		builder.append(", commonMsg=");
		builder.append(commonMsg);
		builder.append(", deptId=");
		builder.append(deptId);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
