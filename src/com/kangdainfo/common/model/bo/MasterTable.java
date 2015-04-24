package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MasterTable implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String mdate;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String modDate;

    /** nullable persistent field */
    private String modTime;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonDepartment commonDepartment;

    /** persistent field */
    private com.kangdainfo.common.model.bo.CommonCode commonCode;

    /** persistent field */
    private Set detailTables;

    /** full constructor */
    public MasterTable(String mdate, String userId, String modDate, String modTime, com.kangdainfo.common.model.bo.CommonDepartment commonDepartment, com.kangdainfo.common.model.bo.CommonCode commonCode, Set detailTables) {
        this.mdate = mdate;
        this.userId = userId;
        this.modDate = modDate;
        this.modTime = modTime;
        this.commonDepartment = commonDepartment;
        this.commonCode = commonCode;
        this.detailTables = detailTables;
    }

    /** default constructor */
    public MasterTable() {
    }

    /** minimal constructor */
    public MasterTable(com.kangdainfo.common.model.bo.CommonDepartment commonDepartment, com.kangdainfo.common.model.bo.CommonCode commonCode, Set detailTables) {
        this.commonDepartment = commonDepartment;
        this.commonCode = commonCode;
        this.detailTables = detailTables;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMdate() {
        return this.mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getModDate() {
        return this.modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getModTime() {
        return this.modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

    public com.kangdainfo.common.model.bo.CommonDepartment getCommonDepartment() {
        return this.commonDepartment;
    }

    public void setCommonDepartment(com.kangdainfo.common.model.bo.CommonDepartment commonDepartment) {
        this.commonDepartment = commonDepartment;
    }

    public com.kangdainfo.common.model.bo.CommonCode getCommonCode() {
        return this.commonCode;
    }

    public void setCommonCode(com.kangdainfo.common.model.bo.CommonCode commonCode) {
        this.commonCode = commonCode;
    }

    public Set getDetailTables() {
        return this.detailTables;
    }

    public void setDetailTables(Set detailTables) {
        this.detailTables = detailTables;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
