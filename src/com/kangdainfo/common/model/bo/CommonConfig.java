package com.kangdainfo.common.model.bo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 系統參數設定檔<br>
 * 1 = 系統參數檔<br>
 * 2 = GateWay FTP參數 : 電子閘門放行資料匯入參數設定<br>
 * 3 = LOAGTV資料庫參數設定<br>
 * 4 = HRM201V資料庫參數設定<br>
 * 5 = SMS 連線資訊
 * 6 = SMTP Email Server 參數設定
 * 7 = 報驗案件的inspDueDate及actDueDate的計算天
 * 8 = 系統管理者Email設定
 * 9 = 列印次數
 * @author shark
 */
public class CommonConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9215225345170702091L;
	
	/** identifier field */
    private String id;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String field11;
    private String field12;
	private String userId;
	private String modDate;
	private String modTime;    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}

	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}

	public String getField11() {
		return field11;
	}

	public void setField11(String field11) {
		this.field11 = field11;
	}

	public String getField12() {
		return field12;
	}

	public void setField12(String field12) {
		this.field12 = field12;
	}
	
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getModTime() {
		return modTime;
	}
	
	public void setModTime(String modTime) {
		this.modTime = modTime;
	}	

	/** default constructor */
    public CommonConfig() {
    }

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sys0001Db [id=");
		builder.append(id);
		builder.append(", field1=");
		builder.append(field1);
		builder.append(", field10=");
		builder.append(field10);
		builder.append(", field11=");
		builder.append(field11);
		builder.append(", field12=");
		builder.append(field12);
		builder.append(", field2=");
		builder.append(field2);
		builder.append(", field3=");
		builder.append(field3);
		builder.append(", field4=");
		builder.append(field4);
		builder.append(", field5=");
		builder.append(field5);
		builder.append(", field6=");
		builder.append(field6);
		builder.append(", field7=");
		builder.append(field7);
		builder.append(", field8=");
		builder.append(field8);
		builder.append(", field9=");
		builder.append(field9);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", modDate=");
		builder.append(modDate);
		builder.append(", modTime=");
		builder.append(modTime);
		builder.append("]");
		return builder.toString();
	}    
    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CommonConfig) ) return false;
        CommonConfig castOther = (CommonConfig) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }    

}
