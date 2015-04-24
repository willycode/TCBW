package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Set;


/** @author Hibernate CodeGenerator */
public class CommonCodeKind implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String codeKindId;

    /** persistent field */
    private String codeKindName;
    
    private String codeLabelCon1, codeLabelCon2, codeLabelCon3, memoLabel;
    private Integer codeMaxLen;

    private String systemType;
    
    /** persistent field */
    private Set commonCodes;
    

    /** full constructor */
    public CommonCodeKind(String codeKindId, String codeKindName, Set commonCodes) {
        this.codeKindId = codeKindId;
        this.codeKindName = codeKindName;
        this.commonCodes = commonCodes;
    }

    /** default constructor */
    public CommonCodeKind() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodeKindId() {
        return this.codeKindId;
    }

    public void setCodeKindId(String codeKindId) {
        this.codeKindId = codeKindId;
    }

    public String getCodeKindName() {
        return this.codeKindName;
    }

    public void setCodeKindName(String codeKindName) {
        this.codeKindName = codeKindName;
    }

    public String getCodeLabelCon1() {
		return codeLabelCon1;
	}

	public void setCodeLabelCon1(String codeLabelCon1) {
		this.codeLabelCon1 = codeLabelCon1;
	}

	public String getCodeLabelCon2() {
		return codeLabelCon2;
	}

	public void setCodeLabelCon2(String codeLabelCon2) {
		this.codeLabelCon2 = codeLabelCon2;
	}

	public String getCodeLabelCon3() {
		return codeLabelCon3;
	}

	public void setCodeLabelCon3(String codeLabelCon3) {
		this.codeLabelCon3 = codeLabelCon3;
	}

	public String getMemoLabel() {
		return memoLabel;
	}

	public void setMemoLabel(String memoLabel) {
		this.memoLabel = memoLabel;
	}

	public Integer getCodeMaxLen() {
		return codeMaxLen;
	}

	public void setCodeMaxLen(Integer codeMaxLen) {
		this.codeMaxLen = codeMaxLen;
	}

	public Set getCommonCodes() {
        return this.commonCodes;
    }

    public void setCommonCodes(Set commonCodes) {
        this.commonCodes = commonCodes;
    }
    
	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonCodeKind [id=");
		builder.append(id);
		builder.append(", codeKindId=");
		builder.append(codeKindId);
		builder.append(", codeKindName=");
		builder.append(codeKindName);
		builder.append(", codeLabelCon1=");
		builder.append(codeLabelCon1);
		builder.append(", codeLabelCon2=");
		builder.append(codeLabelCon2);
		builder.append(", codeLabelCon3=");
		builder.append(codeLabelCon3);
		builder.append(", codeMaxLen=");
		builder.append(codeMaxLen);
		builder.append(", memoLabel=");
		builder.append(memoLabel);
		builder.append(", systemType=");
		builder.append(systemType);
		builder.append("]");
		return builder.toString();
	}

}
