package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DetailTable implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String dtlField1;

    /** nullable persistent field */
    private String dtlField2;

    /** nullable persistent field */
    private String userId;

    /** nullable persistent field */
    private String modDate;

    /** nullable persistent field */
    private String modTime;

    /** persistent field */
    private com.kangdainfo.common.model.bo.MasterTable masterTable;

    /** full constructor */
    public DetailTable(String dtlField1, String dtlField2, String userId, String modDate, String modTime, com.kangdainfo.common.model.bo.MasterTable masterTable) {
        this.dtlField1 = dtlField1;
        this.dtlField2 = dtlField2;
        this.userId = userId;
        this.modDate = modDate;
        this.modTime = modTime;
        this.masterTable = masterTable;
    }

    /** default constructor */
    public DetailTable() {
    }

    /** minimal constructor */
    public DetailTable(com.kangdainfo.common.model.bo.MasterTable masterTable) {
        this.masterTable = masterTable;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtlField1() {
        return this.dtlField1;
    }

    public void setDtlField1(String dtlField1) {
        this.dtlField1 = dtlField1;
    }

    public String getDtlField2() {
        return this.dtlField2;
    }

    public void setDtlField2(String dtlField2) {
        this.dtlField2 = dtlField2;
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

    public com.kangdainfo.common.model.bo.MasterTable getMasterTable() {
        return this.masterTable;
    }

    public void setMasterTable(com.kangdainfo.common.model.bo.MasterTable masterTable) {
        this.masterTable = masterTable;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
