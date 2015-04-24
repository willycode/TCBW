package com.kangdainfo.common.model.bo;

import java.io.Serializable;
import java.util.Arrays;


/** @author Hibernate CodeGenerator */
public class CommonUser implements Serializable {

    private Integer id;
    private String userId;
    private String userSsn;
    private String userPwd;
    private String userPwdModDt;
    private String userName;
    private String personalId;
    private Integer roleId;    
    private String userTelArea;
    private String userTel;    
    private String userTelExt;
    private String userMobile;
    private String userFax;
    private String userFaxArea;
    private String userEmail;
    private String userCounty;
    private String userZipCode;
    private String userAddr;
    private String takeJobDate;
    private String isStop;
    private String isDeptManager;
    private String isAdministrator;
    private String isGetBackPW;
    private String subSystem;
    private String memo;
    private String creator;
    private String applyDate;
    private String createTime;
    
    private String userBirthday;							// 生日
    private String userSex;									// 性別
    private String userJob;									// 服務機構
    private String userJobType;								// 服務機構類別
    private String medicineSN;								// 醫事人員證書號碼
    private String education;								// 最高學歷
    private String jobTitle;								// 職稱
    private String userJobModDate;						    // 單位屬性異動日期
    private String inORout;						            // 內網或外網人員
    
    private String trans;                // 轉檔註記
    private String isGetNewID;           // 是否需變更帳號
    
    private String editId;
    private String editDate;
    private String editTime;
    private String userIP, lastLoginDate, lastLoginTime, lastLoginIP; //不存在於資料庫,只是為了使用者登入時暫存用的
    private String sessionId, loginDate, loginTime; //不存在於資料庫,只是為了使用者登入時暫存用的            
    private java.util.Map<String, Object[]> permissionMap; //不存在於資料庫,只是為了使用者登入時暫存用的   
    
    
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 */       
    public java.util.Map<String, Object[]> getPermissionMap() {return permissionMap;}
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 */     
	public void setPermissionMap(java.util.Map<String, Object[]> permissionMap) {this.permissionMap = permissionMap;}	

	private CommonAuth[] commonAuth; //不存在於資料庫,只是為了使用者登入時暫存用的   
	public CommonAuth[] getCommonAuth() {
		return commonAuth;
	}
	public void setCommonAuth(CommonAuth[] commonAuth) {
		this.commonAuth = commonAuth;
	}

	private com.kangdainfo.common.model.bo.CommonGroup commonGroup;
    private com.kangdainfo.common.model.bo.CommonDepartment commonDepartment;
    private com.kangdainfo.common.model.bo.CommonCode commonCode;

    /** full constructor */
    public CommonUser( String userId, String userSsn, String userPwd, String userName, String userTelExt, String userTel, String userTelArea, String userMobile, String userFax, String userFaxArea, String userEmail, String userCounty, String userZipCode, String userAddr, String takeJobDate, String isStop, String isDeptManager, 
    				   String isAdministrator, String subSystem, String memo, String personalId, String isGetBackPW, String applyDate, String editId, String editDate, String editTime,com.kangdainfo.common.model.bo.CommonGroup commonGroup, com.kangdainfo.common.model.bo.CommonDepartment commonDepartment, com.kangdainfo.common.model.bo.CommonCode commonCode,
    				   String userBirthday, String userSex, String userJob, String userJobType, String medicineSN, String education, String jobTitle, String userJobModDate, String creator, String createTime, String trans, String isGetNewID) {
        this.userId = userId;
        this.userSsn = userSsn;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userTelExt = userTelExt;
        this.userTelArea = userTelArea;
        this.userTel = userTel;
        this.userMobile = userMobile;
        this.userFax = userFax;
        this.userFaxArea = userFaxArea;
        this.userEmail = userEmail;
        this.userCounty = userCounty;
        this.userZipCode = userZipCode;
        this.userAddr = userAddr;
        this.takeJobDate = takeJobDate;
        this.isStop = isStop;
        this.isDeptManager = isDeptManager;
        this.isAdministrator = isAdministrator;
        this.subSystem = subSystem;
        this.personalId = personalId;
        this.isGetBackPW = isGetBackPW;
        this.creator = creator;
        this.applyDate = applyDate;
        this.createTime = createTime;
        this.memo = memo;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userJob = userJob;
        this.userJobType = userJobType;
        this.medicineSN = medicineSN;
        this.education = education;
        this.jobTitle = jobTitle;
        this.userJobModDate = userJobModDate;
        this.trans = trans;
        this.isGetNewID = isGetNewID;
        this.editId = editId;
        this.editDate = editDate;
        this.editTime = editTime;
        this.commonGroup = commonGroup;        
        this.commonDepartment = commonDepartment;
        this.commonCode = commonCode;
    }

    /** default constructor */
    public CommonUser() {
    }

    /** minimal constructor */
    public CommonUser(String userId, com.kangdainfo.common.model.bo.CommonGroup commonGroup, com.kangdainfo.common.model.bo.CommonDepartment commonDepartment, com.kangdainfo.common.model.bo.CommonCode commonCode) {
        this.userId = userId;
        this.commonGroup = commonGroup;        
        this.commonDepartment = commonDepartment;
        this.commonCode = commonCode;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSsn() {
        return this.userSsn;
    }

    public void setUserSsn(String userSsn) {
        this.userSsn = userSsn;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwdModDt() {
		return userPwdModDt;
	}
	public void setUserPwdModDt(String userPwdModDt) {
		this.userPwdModDt = userPwdModDt;
	}
	
	public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserTel() {
        return this.userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserTelArea() {
		return userTelArea;
	}
    
	public void setUserTelArea(String userTelArea) {
		this.userTelArea = userTelArea;
	}
	
	public String getUserTelExt() {
		return userTelExt;
	}
	
	public void setUserTelExt(String userTelExt) {
		this.userTelExt = userTelExt;
	}
	
	public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserFax() {
        return this.userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax;
    }

    public String getUserFaxArea() {
		return userFaxArea;
	}
    
	public void setUserFaxArea(String userFaxArea) {
		this.userFaxArea = userFaxArea;
	}
	
	public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCounty() {
		return userCounty;
	}
    
	public void setUserCounty(String userCounty) {
		this.userCounty = userCounty;
	}
	
	public String getUserZipCode() {
		return userZipCode;
	}
	
	public void setUserZipCode(String userZipCode) {
		this.userZipCode = userZipCode;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	
	public String getTakeJobDate() {
        return this.takeJobDate;
    }

    public void setTakeJobDate(String takeJobDate) {
        this.takeJobDate = takeJobDate;
    }

    public String getIsStop() {
        return this.isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public String getIsDeptManager() {
        return this.isDeptManager;
    }

    public void setIsDeptManager(String isDeptManager) {
        this.isDeptManager = isDeptManager;
    }

    public String getIsAdministrator() {
        return this.isAdministrator;
    }

    public void setIsAdministrator(String isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public String getSubSystem() {
		return subSystem;
	}
    
	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}
	
	public String getIsGetBackPW() {
		return isGetBackPW;
	}
	
	public void setIsGetBackPW(String isGetBackPW) {
		this.isGetBackPW = isGetBackPW;
	}
	
	public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getUserBirthday() {
		return userBirthday;
	}
    
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	public String getUserSex() {
		return userSex;
	}
	
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	public String getUserJob() {
		return userJob;
	}
	
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	
	public String getUserJobType() {
		return userJobType;
	}
	
	public void setUserJobType(String userJobType) {
		this.userJobType = userJobType;
	}
	
	public String getMedicineSN() {
		return medicineSN;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setMedicineSN(String medicineSN) {
		this.medicineSN = medicineSN;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getUserJobModDate() {
		return userJobModDate;
	}
	
	public void setUserJobModDate(String userJobModDate) {
		this.userJobModDate = userJobModDate;
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

    public String getPersonalId() {
		return personalId;
	}
    
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	
	public String getApplyDate() {
		return applyDate;
	}
	
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	
	/**
     * 不存在於資料庫,只是為了使用者登入時暫存一下使用者的IP
     * @return
     */
	public String getUserIP() {
		return userIP;
	}

	/**
	 * 不存在於資料庫,只是為了使用者登入時暫存一下使用者的IP
	 * @param userIP
	 */
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	/**
	 * 不存在於資料庫,只是為了使用者登入時暫存一下的使用者前次登入日期
	 * @return
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * 不存在於資料庫,只是為了使用者登入時暫存一下的使用者前次登入時間
	 * @return
	 */
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 不存在於資料庫,只是為了使用者登入時暫存一下的使用者前次登入IP
	 * @return
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public com.kangdainfo.common.model.bo.CommonGroup getCommonGroup() {
        return this.commonGroup;
    }

    public void setCommonGroup(com.kangdainfo.common.model.bo.CommonGroup commonGroup) {
        this.commonGroup = commonGroup;
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

	public String getInORout() {
		return inORout;
	}
	public void setInORout(String inORout) {
		this.inORout = inORout;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}	
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getIsGetNewID() {
		return isGetNewID;
	}
	public void setIsGetNewID(String isGetNewID) {
		this.isGetNewID = isGetNewID;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonUser [id=");
		builder.append(id);
		builder.append(", commonCode=");
		builder.append(commonCode);
		builder.append(", commonDepartment=");
		builder.append(commonDepartment);
		builder.append(", isAdministrator=");
		builder.append(isAdministrator);
		builder.append(", isDeptManager=");
		builder.append(isDeptManager);
		builder.append(", subSystem=");
		builder.append(subSystem);
		builder.append(", isStop=");
		builder.append(isStop);
		builder.append(", lastLoginDate=");
		builder.append(lastLoginDate);
		builder.append(", lastLoginIP=");
		builder.append(lastLoginIP);
		builder.append(", lastLoginTime=");
		builder.append(lastLoginTime);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", takeJobDate=");
		builder.append(takeJobDate);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", userAddr=");
		builder.append(userAddr);
		builder.append(", userCounty=");
		builder.append(userCounty);
		builder.append(", userZipCode=");
		builder.append(userZipCode);
		builder.append(", userFax=");
		builder.append(userFax);
		builder.append(", userFaxArea=");
		builder.append(userFaxArea);
		builder.append(", userIP=");
		builder.append(userIP);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userMobile=");
		builder.append(userMobile);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userPwd=");
		builder.append(userPwd);
		builder.append(", userPwdModDt=");
		builder.append(userPwdModDt);		
		builder.append(", userSsn=");
		builder.append(userSsn);
		builder.append(", userTel=");
		builder.append(userTel);
		builder.append(", userTelArea=");
		builder.append(userTelArea);
		builder.append(", userTelExt=");
		builder.append(userTelExt);
		builder.append(", isGetBackPW=");
		builder.append(isGetBackPW);
		builder.append(", userBirthday=");
		builder.append(userBirthday);
		builder.append(", userSex=");
		builder.append(userSex);
		builder.append(", userJob=");
		builder.append(userJob);
		builder.append(", userJobType=");
		builder.append(userJobType);
		builder.append(", medicineSN=");
		builder.append(medicineSN);
		builder.append(", education=");
		builder.append(education);
		builder.append(", jobTitle=");
		builder.append(jobTitle);
		builder.append(", userJobModDate=");
		builder.append(userJobModDate);
		builder.append(", inORout=");
		builder.append(inORout);
		builder.append(", trans=");
		builder.append(trans);
		builder.append(", isGetNewID=");
		builder.append(isGetNewID);
		builder.append(", loginDate=");
		builder.append(loginDate);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", editDate=");
		builder.append(editDate);
		builder.append(", editId=");
		builder.append(editId);
		builder.append(", editTime=");
		builder.append(editTime);		
		builder.append("]");
		return builder.toString();
	}

}
