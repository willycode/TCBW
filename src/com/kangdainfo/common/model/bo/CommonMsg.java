package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Set;


/** @author Hibernate CodeGenerator */
public class CommonMsg implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String startDate;

    /** nullable persistent field */
    private String startTime;

    /** nullable persistent field */
    private String endDate;

    /** nullable persistent field */
    private String endTime;

    /** nullable persistent field */
    private String isOpen;

    /** nullable persistent field */
    private String deptCode;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String msgBody;

    /** nullable persistent field */
    private String msgSender;

    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;

    /** persistent field */
    private Set commonMsgGroups;

    /** full constructor */
    public CommonMsg(String startDate, String startTime, String endDate, String endTime, String isOpen, String deptCode, String userId, String msgBody, String msgSender, String editId, String editDate, String editTime, Set commonMsgGroups) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.isOpen = isOpen;
        this.deptCode = deptCode;
        this.userId = userId;
        this.msgBody = msgBody;
        this.msgSender = msgSender;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonMsgGroups = commonMsgGroups;
    }

    /** default constructor */
    public CommonMsg() {
    }

    /** minimal constructor */
    public CommonMsg(Set commonMsgGroups) {
        this.commonMsgGroups = commonMsgGroups;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getMsgSender() {
        return this.msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
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

    public Set getCommonMsgGroups() {
        return this.commonMsgGroups;
    }

    public void setCommonMsgGroups(Set commonMsgGroups) {
        this.commonMsgGroups = commonMsgGroups;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonMsg [id=");
		builder.append(id);
		builder.append(", deptCode=");
		builder.append(deptCode);
		builder.append(", editDate=");
		builder.append(editDate);
		builder.append(", isOpen=");
		builder.append(isOpen);
		builder.append(", msgBody=");
		builder.append(msgBody);
		builder.append(", msgSender=");
		builder.append(msgSender);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", editTime=");
		builder.append(editTime);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", editId=");
		builder.append(editId);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append("]");
		return builder.toString();
	}

}
