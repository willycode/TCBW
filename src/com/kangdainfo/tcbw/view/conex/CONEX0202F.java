package com.kangdainfo.tcbw.view.conex;

import javax.mail.internet.AddressException;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonLog;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONEX0202F extends CONEX0201F{
	
	private String department;
	private String userId;
	private String userName;
	private String personalId;
	private String userSex;
	private String userBirthday;
	private String education;
	private String userTel;
	private String userTelArea;
	private String userTelExt;
    private String userMobile;
    private String userFax;
    private String userFaxArea;
    private String userEmail;
    private String userPwd;
	private String userJob;
	private String userJobName;
	private String userJobType;
	private String userCounty;
	private String userZipCode;
	private String userAddr;
	private String jobTitle;
	private String medicineSN;
	private String userIp;	
	private String creator;
	private String applyDate;	
	private String mailMsg;
	private String isStop;
	
	public String getCreator() {
		return checkGet(creator);
	}
	public void setCreator(String creator) {
		this.creator = checkSet(creator);
	}
	public String getApplyDate() {
		return checkGet(applyDate);
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = checkSet(applyDate);
	}
	public String getMailMsg() {
		return checkGet(mailMsg);
	}
	public void setMailMsg(String mailMsg) {
		this.mailMsg = checkSet(mailMsg);
	}
	
	public String getIsStop(){ return checkGet(isStop); }
	public void setIsStop(String s){ isStop=checkSet(s); }
	
	public String getUserIp() {return checkGet(userIp);}
	public void setUserIp(String userIp) {this.userIp = checkSet(userIp);}
	
	public String getDepartment() {		return checkGet(department);	}
	public void setDepartment(String department) {		this.department = checkSet(department);	}
	public String getUserId() {		return checkGet(userId);	}
	public void setUserId(String userId) {		this.userId = checkSet(userId);	}
	public String getUserName() {		return checkGet(userName);	}
	public void setUserName(String userName) {		this.userName = checkSet(userName);	}
	public String getPersonalId() {		return checkGet(personalId);}
	public void setPersonalId(String personalId) {		this.personalId = checkSet(personalId);	}
	public String getUserSex() {		return checkGet(userSex);	}
	public void setUserSex(String userSex) {		this.userSex = checkSet(userSex);	}
	public String getUserBirthday() {		return checkGet(userBirthday);	}
	public void setUserBirthday(String userBirthday) {		this.userBirthday = checkSet(userBirthday);	}
	public String getEducation() {		return checkGet(education);	}
	public void setEducation(String education) {		this.education = checkSet(education);	}
	public String getUserTel() {		return checkGet(userTel);	}
	public void setUserTel(String userTel) {		this.userTel = checkSet(userTel);	}
	public String getUserAddr() {		return checkGet(userAddr);	}
	public void setUserAddr(String userAddr) {		this.userAddr = checkSet(userAddr);	}
	public String getUserMobile() {		return checkGet(userMobile);	}
	public void setUserMobile(String userMobile) {		this.userMobile = checkSet(userMobile);	}
	public String getUserFax() {		return checkGet(userFax);	}
	public void setUserFax(String userFax) {		this.userFax = checkSet(userFax);	}
	public String getUserEmail() {		return checkGet(userEmail);	}
	public void setUserEmail(String userEmail) {		this.userEmail = checkSet(userEmail);	}
	public String getUserPwd() {		return checkGet(userPwd);	}
	public void setUserPwd(String userPwd) {		this.userPwd = checkSet(userPwd);}
	public String getUserJob() {		return checkGet(userJob);	}
	public void setUserJob(String userJob) {		this.userJob = checkSet(userJob);	}
	public String getUserJobName() {		return checkGet(userJobName);	}
	public void setUserJobName(String userJobName) {		this.userJobName = checkSet(userJobName);	}
	public String getUserJobType() {		return checkGet(userJobType);	}
	public void setUserJobType(String userJobType) {		this.userJobType = checkSet(userJobType);	}
	public String getJobTitle() {		return checkGet(jobTitle);	}
	public void setJobTitle(String jobTitle) {		this.jobTitle = checkSet(jobTitle);	}
	public String getMedicineSN() {		return checkGet(medicineSN);	}
	public void setMedicineSN(String medicineSN) {		this.medicineSN = checkSet(medicineSN);	}	
	public String getUserTelArea() {		return checkGet(userTelArea);	}
	public void setUserTelArea(String userTelArea) {		this.userTelArea = checkSet(userTelArea);	}
	public String getUserTelExt() {		return checkGet(userTelExt);	}
	public void setUserTelExt(String userTelExt) {		this.userTelExt = checkSet(userTelExt);	}
	public String getUserFaxArea() {		return checkGet(userFaxArea);	}
	public void setUserFaxArea(String userFaxArea) {		this.userFaxArea = checkSet(userFaxArea);	}
	public String getUserCounty() {		return checkGet(userCounty);	}
	public void setUserCounty(String userCounty) {		this.userCounty = checkSet(userCounty);	}
	public String getUserZipCode() {		return checkGet(userZipCode);	}
	public void setUserZipCode(String userZipCode) {		this.userZipCode = checkSet(userZipCode);	}

	private String oldUserJob;
	public String getOldUserJob() {		return checkGet(oldUserJob);	}
	public void setOldUserJob(String oldUserJob) {		this.oldUserJob = checkSet(oldUserJob);	}
	
	private String createYN;
	public String getCreateYN() {		return checkGet(createYN);	}
	public void setCreateYN(String createYN) {		this.createYN = checkSet(createYN);	}
	
	private String reLogin;
	public String getReLogin() {		return checkGet(reLogin);	}
	public void setReLogin(String reLogin) {		this.reLogin = checkSet(reLogin);	}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		CONEX0202F obj = this;

		CommonUser c = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setDepartment(c.getCommonDepartment()!=null?c.getCommonDepartment().getShortCode():"");
			obj.setUserId(c.getUserId());
			obj.setUserName(c.getUserName());
			
			if(!"".equals(Common.get(c.getPersonalId()))){
				obj.setPersonalId(TCBWCommon.getDecodeString(c.getPersonalId()));
			}else{
				obj.setPersonalId("");
			}
			obj.setUserSex(c.getUserSex());
			obj.setUserBirthday(c.getUserBirthday());
			obj.setEducation(c.getEducation());
			obj.setUserTel(c.getUserTel());
			obj.setUserTelArea(c.getUserTelArea());
			obj.setUserTelExt(c.getUserTelExt());
			obj.setUserMobile(c.getUserMobile());
			obj.setUserFax(c.getUserFax());
			obj.setUserFaxArea(c.getUserFaxArea());
			obj.setUserEmail(c.getUserEmail());
			obj.setUserCounty(c.getUserCounty());
			obj.setUserZipCode(c.getUserZipCode());
			obj.setUserAddr(c.getUserAddr());
			obj.setUserPwd("NOT_CHANGE123");				// 不顯示密碼
			obj.setJobTitle(c.getJobTitle());
			obj.setIsStop(c.getIsStop());
	        String jobName = "";
			if ("02".equals(Common.get(getDepartment()))){	
				jobName = View.getLookupField("select name from Con1005Db where id="+Common.getLong(c.getUserJob()));		
			}		
			else if ("03".equals(Common.get(getDepartment()))){			
				jobName = View.getLookupField("select medAgencyName from Con1009Db where id="+Common.getLong(c.getUserJob()));		
			}		
			else if ("04".equals(Common.get(getDepartment()))){			
				jobName = View.getLookupField("select unionName from Con1003Db where id="+Common.getLong(c.getUserJob()));	
			}
			obj.setUserJobName(jobName);
			obj.setUserJob(c.getUserJob());
			obj.setOldUserJob(c.getUserJob());
			obj.setMedicineSN(c.getMedicineSN());
			obj.setUserJobType(c.getUserJobType());
			obj.setCreator(c.getCreator());
	        obj.setApplyDate(c.getApplyDate());
	        obj.setEditID(c.getEditId());
	        obj.setEditDate(c.getEditDate());
		}
		return obj;
	}

	@Override
	public void doUpdate() throws Exception {
		CommonUser obj = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getId()));
		if(obj != null){
			
//			int c = ServiceGetter.getInstance().getTcbwService().loadCount(" from CommonUser where userEmail="+Common.sqlChar(getUserEmail())+" and id !="+Common.getLong(getId()));
//			if(c>0){
//            	throw new Exception("此信箱已使用，請重新輸入!");
//            }else{
            	obj.setUserName(getUserName());
    			if(!"".equals(getPersonalId())){
    				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));
    			}else{
    				obj.setPersonalId(null);
    			}			
    			obj.setUserSex(getUserSex());
    			obj.setUserBirthday(getUserBirthday());
    			obj.setEducation(getEducation());
    			obj.setUserTel(getUserTel());
    			obj.setUserTelArea(getUserTelArea());
    			obj.setUserTelExt(getUserTelExt());
    			obj.setUserMobile(getUserMobile());
    			obj.setUserFax(getUserFax());
    			obj.setUserFaxArea(getUserFaxArea());
    			obj.setUserEmail(getUserEmail());
    			obj.setUserAddr(getUserAddr());
    			obj.setUserCounty(getUserCounty());
    			obj.setUserZipCode(getUserZipCode());
    			obj.setJobTitle(getJobTitle());
    			obj.setIsStop(getIsStop());
    			obj.setUserJob(getUserJob());
    			if("03".equals(getDepartment()) || "04".equals(getDepartment())){
    				if(!obj.getUserJob().equals(getOldUserJob())){
    					obj.setUserJobModDate(Datetime.getYYYMMDD());
    				}
    			}			
    			obj.setUserJobType(getUserJobType());
    			obj.setMedicineSN(getMedicineSN());
    			obj.setEditId(getUserID());
    			obj.setEditDate(getEditDate());
    			obj.setEditTime(getEditTime());
    			
    			if(!"NOT_CHANGE123".equals(getUserPwd())){
    				if("Y".equals(Common.get(obj.getIsGetBackPW()))){
    					obj.setIsGetBackPW("N");
    				}
    				obj.setUserPwd(Common.getDigestString(getUserPwd(), "SHA-1"));
    				obj.setUserPwdModDt(Datetime.getYYYMMDD());
    			}
    			if("Y".equals(Common.get(obj.getIsGetNewID()))){
					obj.setIsGetNewID("N");
				}
    			
    			if(!"N".equals(getCreateYN()))
    			{
    				CommonLog commonLog = new CommonLog();
    				
    				commonLog.setCode("CONIN0202F");//通報表代碼
    				commonLog.setOpenDate(Datetime.getYYYMMDD());//開啟日期
    				commonLog.setOpenTime(Datetime.getHHMMSS());//開啟時間
    				commonLog.setUserId(getUserID());//開啟人員
    				commonLog.setIp(getUserIp());//開啟人員IP
    				commonLog.setMethodName("UPDATE");//方法
    				commonLog.setDb("CommonUser");//資料表
    				commonLog.setDbId(getId());//資料表IP
    				commonLog.setApplNo("");//
    				commonLog.setOpenUserId(getUserId());//被開啟USerID
    				commonLog.setOpenUserName(getUserName());

    				ServiceGetter.getInstance().getTcbwService().save(commonLog);
    			}    			
    			ServiceGetter.getInstance().getTcbwService().update(obj);
            }			
//		}
		else
		{
			throw new Exception("查無該使用者資料，無法更新");
		}
	}
	
	@Override
	public void doCreate() throws Exception {
		
		CommonUser obj = (CommonUser)View.getObject(" from CommonUser where userId = " + Common.sqlChar(getUserId()));
		if(obj == null){
			int c = ServiceGetter.getInstance().getTcbwService().loadCount(" from CommonUser where userEmail="+Common.sqlChar(getUserEmail()));
//            if(c>0){
//            	throw new Exception("此信箱已使用，請重新輸入!");
//            }else{
            	obj = new CommonUser();		
    			obj.setUserId(getUserId());		
    			obj.setUserName(getUserName());		
    			if(!"".equals(getPersonalId())){			
    				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));		
    			}		
    			obj.setUserSex(getUserSex());		
    			obj.setUserBirthday(getUserBirthday());		
    			obj.setEducation(getEducation());
    			obj.setUserFax(getUserFax());
    			obj.setUserFaxArea(getUserFaxArea());
    			obj.setUserTel(getUserTel());
    			obj.setUserTelArea(getUserTelArea());
    			obj.setUserTelExt(getUserTelExt());
    			obj.setUserMobile(getUserMobile());		
    			obj.setUserEmail(getUserEmail());		
    			obj.setUserJobType(getUserJobType());		
    			obj.setUserJob(getUserJob());		
    			obj.setUserAddr(getUserAddr());
    			obj.setUserCounty(getUserCounty());
    			obj.setUserZipCode(getUserZipCode());
    			obj.setJobTitle(getJobTitle());		
    			obj.setMedicineSN(getMedicineSN());		
    			obj.setRoleId(1);		
    			obj.setInORout("out");		
    			obj.setIsStop("N");		
    			obj.setCreator(getEditID());
    			obj.setApplyDate(getApplyDate());
    			obj.setCreateTime(getEditTime());
    			obj.setEditId(getUserID());		
    			obj.setEditDate(Datetime.getYYYMMDD());		
    			obj.setEditTime(Datetime.getHHMMSS());		
    		
    			if(!"".equals(getUserPwd())){			
    				obj.setUserPwd(Common.getDigestString(getUserPwd(), "SHA-1"));		
    			}

    			// 所屬單位		
    			CommonDepartment dept = (CommonDepartment)View.getObject(" from CommonDepartment where shortCode ="+Common.sqlChar(getDepartment()));		
    			obj.setCommonDepartment(dept);
    		
    			ServiceGetter.getInstance().getTcbwService().save(obj);		
    			
    			String msg = sendEmail(obj, getUserPwd());
    			setMailMsg(msg);
    		
    			//身分別權限(預設一般民眾)		
    			CommonGroup group = null;		
    			if("01".equals(Common.get(getDepartment()))){		
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='一般民眾登錄'");			
    			}		
    			else if ("02".equals(Common.get(getDepartment()))){			
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='廠商登錄'");		
    			}		
    			else if ("03".equals(Common.get(getDepartment()))){			
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='醫事機構登錄'");		
    			}		
    			else if ("04".equals(Common.get(getDepartment()))){			
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='衛生單位登錄'");			
    			}
    		
    			if(group != null){			
    				CommonUserGroup userGroup = new CommonUserGroup();			
    				userGroup.setCommonGroup(group);			
    				userGroup.setCommonUser(obj);			
    				userGroup.setEditId("SYS");			
    				userGroup.setEditDate(Datetime.getYYYMMDD());			
    				userGroup.setEditTime(Datetime.getHHMMSS());			
    				ServiceGetter.getInstance().getTcbwService().save(userGroup);		
    			}
    			setId(Common.get(obj.getId()));
//            }			
		}else{
			throw new Exception("已有此使用者帳號，無法新增");
		}
		
	}
	
	public String sendEmail(CommonUser obj, String pwd) throws AddressException{
		String msg = "";
		if(obj != null){
			if(!"".equals(Common.get(obj.getUserEmail()))){
				if (!"".equals(Common.get(obj.getUserEmail())) && com.kangdainfo.common.util.Validate.checkEmail(Common.get(obj.getUserEmail()))) {
					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
					mailList.add(new javax.mail.internet.InternetAddress(Common.get(obj.getUserEmail())));
					try{
						ServiceGetter.getInstance().getCommonService().sendEmail(obj.getUserName()+"，您的帳號已建立完成", "帳號：" + obj.getUserId() + "<br>密碼：" + pwd, mailList, null, true);
					}catch(Exception e){
						e.printStackTrace();
						msg = "發送EMAIL時，發生錯誤 ，請連絡管理者!";
					}
					if("".equals(msg)){
						obj.setIsGetBackPW("Y");
						ServiceGetter.getInstance().getCommonService().update(obj);
						msg = "已將新帳號密碼發送至使用者的信箱!";
					}
				} else {
					msg = "EMAIL資料有誤，無法發送!";
				}
			}else{
				msg = "無信箱網址，無法發送!";
			}
		}else{
			msg = "查無該使用者，請重新輸入!";
		}
	
		return msg;
	}

}
