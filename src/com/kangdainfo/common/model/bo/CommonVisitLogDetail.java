package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CommonVisitLogDetail implements Serializable {

    /** identifier field */
    private Long id;

    private String logDate;
    
    /** nullable persistent field */
    private String logTime;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String userName;

    /** nullable persistent field */
    private String organId;

    /** nullable persistent field */
    private String organName;    
    private String userIP;
    private String logStatus;
    private String logAction;
    private String logMsg;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogDate() {
		return logDate;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganId() {
        return this.organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return this.organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getLogMsg() {
		return logMsg;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
