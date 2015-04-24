package com.kangdainfo.common.model.bo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CommonMsgLog implements Serializable {

    /** identifier field */
    private Long id;
    /** nullable persistent field */
    private String userId;
    private String logDate;
    private String logTime;
    private String isSlide;
    private String isRead;
 
    private CommonMsg commonMsg;

    /** default constructor */
    public CommonMsgLog() {
    }

    /** minimal constructor */
    public CommonMsgLog(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLogDate() {
        return this.logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogTime() {
        return this.logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getIsSlide() {
		return isSlide;
	}

	public void setIsSlide(String isSlide) {
		this.isSlide = isSlide;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public CommonMsg getCommonMsg() {
		return commonMsg;
	}

	public void setCommonMsg(CommonMsg commonMsg) {
		this.commonMsg = commonMsg;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
