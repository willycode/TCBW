package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CommonVisitLogWeek implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String logYyy;

    /** persistent field */
    private int logWeek;

    /** nullable persistent field */
    private Long logCount;

    /** full constructor */
    public CommonVisitLogWeek(String logYyy, int logWeek, Long logCount) {
        this.logYyy = logYyy;
        this.logWeek = logWeek;
        this.logCount = logCount;
    }

    /** default constructor */
    public CommonVisitLogWeek() {
    }

    /** minimal constructor */
    public CommonVisitLogWeek(String logYyy, int logWeek) {
        this.logYyy = logYyy;
        this.logWeek = logWeek;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogYyy() {
        return this.logYyy;
    }

    public void setLogYyy(String logYyy) {
        this.logYyy = logYyy;
    }

    public int getLogWeek() {
        return this.logWeek;
    }

    public void setLogWeek(int logWeek) {
        this.logWeek = logWeek;
    }

    public Long getLogCount() {
        return this.logCount;
    }

    public void setLogCount(Long logCount) {
        this.logCount = logCount;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
