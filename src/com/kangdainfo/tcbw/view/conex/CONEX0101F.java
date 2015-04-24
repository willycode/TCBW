package com.kangdainfo.tcbw.view.conex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONEX0101F extends SuperBean{

	private String commonDepartment;
	private String userId;
	private String userName;
	private String personalId;
	private String userSex;
	private String userBirthday;
	private String education;
	private String userTel;
	private String userTelExt;
	private String userTelArea;
    private String userMobile;
    private String userFax;
    private String userFaxArea;
    private String userEmail;
    private String userPwd;
	private String userJob;
	private String userJobName;
	private String userJobType;
	private String userAddr;
	private String userCounty;
	private String userZipCode;
	private String jobTitle;
	private String medicineSN;
	
	public String getCommonDepartment() {		return checkGet(commonDepartment);	}
	public void setCommonDepartment(String commonDepartment) {		this.commonDepartment = checkSet(commonDepartment);	}
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
	public String getUserMobile() {		return checkGet(userMobile);	}
	public void setUserMobile(String userMobile) {		this.userMobile = checkSet(userMobile);	}
	public String getUserFax() {		return checkGet(userFax);	}
	public void setUserFax(String userFax) {		this.userFax = checkSet(userFax);	}
	public String getUserEmail() {		return checkGet(userEmail);	}
	public void setUserEmail(String userEmail) {		this.userEmail = checkSet(userEmail);	}
	public String getUserAddr() {		return checkGet(userAddr);	}
	public void setUserAddr(String userAddr) {		this.userAddr = checkSet(userAddr);	}
	public String getUserPwd() {		return checkGet(userPwd);	}
	public void setUserPwd(String userPwd) {		this.userPwd = checkSet(userPwd);}
	public String getUserJob() {		return checkGet(userJob);	}
	public void setUserJob(String userJob) {		this.userJob = checkSet(userJob);	}
	public String getUserJobName() {return checkGet(userJobName);}
	public void setUserJobName(String userJobName) {this.userJobName = checkSet(userJobName);}
	public String getUserJobType() {		return checkGet(userJobType);	}
	public void setUserJobType(String userJobType) {		this.userJobType = checkSet(userJobType);	}
	public String getJobTitle() {		return checkGet(jobTitle);	}
	public void setJobTitle(String jobTitle) {		this.jobTitle = checkSet(jobTitle);	}
	public String getMedicineSN() {		return checkGet(medicineSN);	}
	public void setMedicineSN(String medicineSN) {		this.medicineSN = checkSet(medicineSN);	}
	public String getUserTelExt() {		return checkGet(userTelExt);}
	public void setUserTelExt(String userTelExt) {		this.userTelExt = checkSet(userTelExt);}
	public String getUserTelArea() {		return checkGet(userTelArea);}
	public void setUserTelArea(String userTelArea) {		this.userTelArea = checkSet(userTelArea);}
	public String getUserFaxArea() {		return checkGet(userFaxArea);}
	public void setUserFaxArea(String userFaxArea) {		this.userFaxArea = checkSet(userFaxArea);}
	public String getUserCounty() {		return checkGet(userCounty);}
	public void setUserCounty(String userCounty) {		this.userCounty = checkSet(userCounty);}
	public String getUserZipCode() {		return checkGet(userZipCode);}
	public void setUserZipCode(String userZipCode) {		this.userZipCode = checkSet(userZipCode);}



	private String URL;
	public String getURL() {		return checkGet(URL);	}
	public void setURL(String uRL) {		URL = checkSet(uRL);	}
	
	public String doSaveCreate()throws Exception{
		String r = "";
		CommonUser obj = (CommonUser) View.getObject(" from CommonUser where userId = " + Common.sqlChar(getUserId()));
		if(obj != null){
			r = "註冊失敗，帳號重覆";
		}else{
			
//			int c = ServiceGetter.getInstance().getTcbwService().loadCount(" from CommonUser where userEmail="+Common.sqlChar(getUserEmail()));
//			if(c > 0){
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
    			obj.setUserTel(getUserTel());
    			obj.setUserFax(getUserFax());
    			obj.setUserFaxArea(getUserFaxArea());
    			obj.setUserTelExt(getUserTelExt());
    			obj.setUserTelArea(getUserTelArea());
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
    			obj.setIsStop("Y");
    			obj.setEditId("SYS");
    			obj.setEditDate(Datetime.getYYYMMDD());
    			obj.setEditTime(Datetime.getHHMMSS());
    			
    			if(!"".equals(getUserPwd())){
    				obj.setUserPwd(Common.getDigestString(getUserPwd(), "SHA-1"));
    			}
    	
    			// 所屬單位
    			CommonDepartment dept = (CommonDepartment)View.getObject(" from CommonDepartment where shortCode ="+Common.sqlChar(getCommonDepartment()));
    			obj.setCommonDepartment(dept);		
    		
    			ServiceGetter.getInstance().getTcbwService().save(obj);
    			
    			//身分別權限(預設一般民眾)
    			CommonGroup group = null;
    			if("01".equals(Common.get(getCommonDepartment()))){			
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='一般民眾登錄'");	
    			}
    			else if ("03".equals(Common.get(getCommonDepartment()))){
    				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='醫事機構登錄'");	
    			}
    			else if ("04".equals(Common.get(getCommonDepartment()))){
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
    			

    			
    			r = "註冊完成。請至您信箱收信，進行帳號啟用!!";			
    			
    			// 發送信件
    			if(!"".equals(Common.get(obj.getUserEmail()))){
    				if(!"".equals(Common.get(obj.getUserEmail())) && com.kangdainfo.common.util.Validate.checkEmail(Common.get(obj.getUserEmail()))){
    					java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
    					mailList.add(new javax.mail.internet.InternetAddress(Common.get(obj.getUserEmail())));
    					
    					StringBuffer sb = new StringBuffer();
    					sb.append(getURL().replace("preLogin.jsp", "")).append("startLogin.jsp?")
    					  .append("v=").append(TCBWCommon.getEncodeString(obj.getUserId()));
    					try{
    						ServiceGetter.getInstance().getCommonService().sendEmail(obj.getUserName() + "， 這是您的啟用確認信件", sb.toString(), mailList, null, true);
    						System.out.println("[TCBW]-[CONEX0101F]-[外部使用者註冊發送信件，發送完成]");
    					}catch(Exception e){
    						e.printStackTrace();
    						System.out.println("[TCBW]-[CONEX0101F]-[外部使用者註冊發送信件，發生錯誤]");
    					}
    				}else{
    					System.out.println("[TCBW]-[CONEX0101F]-[外部使用者註冊發送信件，EMAIL錯誤，無法發送啟用信件]");
    				}
    			}else{
    				System.out.println("[TCBW]-[CONEX0101F]-[外部使用者註冊發送信件，EMAIL空値，無法發送啟用信件]");
    			}
//            }			
		}
		return r;
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
