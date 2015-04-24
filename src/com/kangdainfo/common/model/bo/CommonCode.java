package com.kangdainfo.common.model.bo;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class CommonCode implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2061895433934968024L;

    private Integer id;
    private String codeId;
    private String codeName, codeEngName, codeShortName;
    private String issueDate, endDate;
    private String codeCon1;
    private String codeCon2;
    private String codeCon3;
    private String codeMemo;
    private String isDrg;
    private String isMed;
    private String isHfr;
    private String isCos;
    private String isStop;
    private String editId;
    private String editDate;
    private String editTime;
    private com.kangdainfo.common.model.bo.CommonCodeKind commonCodeKind;

    /** full constructor */
    public CommonCode(String codeId, String codeName, 
    		String codeCon1, String codeCon2, String codeCon3, 
    		String codeMemo, String editId, String editDate, 
    		String editTime, com.kangdainfo.common.model.bo.CommonCodeKind commonCodeKind,
    		String isDrg, String isMed, String isHfr,String isCos,String isStop
    ) 
    {
        this.codeId = codeId;
        this.codeName = codeName;
        this.codeCon1 = codeCon1;
        this.codeCon2 = codeCon2;
        this.codeCon3 = codeCon3;
        this.codeMemo = codeMemo;
        this.isDrg = isDrg;
        this.isMed = isMed;
        this.isHfr = isHfr;
        this.isCos = isCos;
        this.isStop = isStop;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonCodeKind = commonCodeKind;
    }

    /** default constructor */
    public CommonCode() {
    }

    /** minimal constructor */
    public CommonCode(String codeId, String codeName, com.kangdainfo.common.model.bo.CommonCodeKind commonCodeKind) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.commonCodeKind = commonCodeKind;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeShortName() {
		return codeShortName;
	}

	public void setCodeShortName(String codeShortName) {
		this.codeShortName = codeShortName;
	}

	public String getCodeEngName() {
		return codeEngName;
	}

	public void setCodeEngName(String codeEngName) {
		this.codeEngName = codeEngName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCodeCon1() {
        return this.codeCon1;
    }

    public void setCodeCon1(String codeCon1) {
        this.codeCon1 = codeCon1;
    }

    public String getCodeCon2() {
        return this.codeCon2;
    }

    public void setCodeCon2(String codeCon2) {
        this.codeCon2 = codeCon2;
    }

    public String getCodeCon3() {
        return this.codeCon3;
    }

    public void setCodeCon3(String codeCon3) {
        this.codeCon3 = codeCon3;
    }

    public String getCodeMemo() {
        return this.codeMemo;
    }

    public void setCodeMemo(String codeMemo) {
        this.codeMemo = codeMemo;
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

    public com.kangdainfo.common.model.bo.CommonCodeKind getCommonCodeKind() {
        return this.commonCodeKind;
    }

    public void setCommonCodeKind(com.kangdainfo.common.model.bo.CommonCodeKind commonCodeKind) {
        this.commonCodeKind = commonCodeKind;
    }
    
    

	public String getIsDrg() {
		return isDrg;
	}

	public void setIsDrg(String isDrg) {
		this.isDrg = isDrg;
	}

	public String getIsMed() {
		return isMed;
	}

	public void setIsMed(String isMed) {
		this.isMed = isMed;
	}

	public String getIsHfr() {
		return isHfr;
	}

	public void setIsHfr(String isHfr) {
		this.isHfr = isHfr;
	}

	public String getIsCos() {
		return isCos;
	}

	public void setIsCos(String isCos) {
		this.isCos = isCos;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonCode [id=");
		builder.append(id);
		builder.append(", codeCon1=");
		builder.append(codeCon1);
		builder.append(", codeCon2=");
		builder.append(codeCon2);
		builder.append(", codeCon3=");
		builder.append(codeCon3);
		builder.append(", codeEngName=");
		builder.append(codeEngName);
		builder.append(", codeId=");
		builder.append(codeId);
		builder.append(", codeMemo=");
		builder.append(codeMemo);
		builder.append(", codeName=");
		builder.append(codeName);
		builder.append(", codeShortName=");
		builder.append(codeShortName);
		builder.append(", commonCodeKind=");
		builder.append(commonCodeKind);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", issueDate=");
		builder.append(issueDate);	
		builder.append(", isStop=");
		builder.append(isStop);
		builder.append(", isCos=");
		builder.append(isCos);
		builder.append(", isHfr=");
		builder.append(isHfr);
		builder.append(", isMed=");
		builder.append(isMed);
		builder.append(", isDrg=");
		builder.append(isDrg);
		builder.append(", editId=");
		builder.append(editId);
		builder.append(", editDate=");
		builder.append(editDate);
		builder.append(", editTime=");
		builder.append(editTime);
		builder.append("]");
		return builder.toString();
	}

}
