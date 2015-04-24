package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;



public class CommonNewsLog implements Serializable {

	private static final long serialVersionUID = 3387034920753244272L;
	private Long id;
    private com.kangdainfo.common.model.bo.CommonNews commonNews;
    private String userId;
    private String logDate, logTime;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public com.kangdainfo.common.model.bo.CommonNews getCommonNews() {
		return commonNews;
	}

	public void setCommonNews(com.kangdainfo.common.model.bo.CommonNews commonNews) {
		this.commonNews = commonNews;
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

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
