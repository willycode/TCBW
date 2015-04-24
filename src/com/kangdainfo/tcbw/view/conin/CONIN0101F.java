package com.kangdainfo.tcbw.view.conin;

import javax.mail.internet.AddressException;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonLog;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONIN0101F extends SuperBean{
	
	private String id;
	private String userId;
	private String userSSN;
	private String userPWD;
	private String userName;
	private String groupId;
	private String deptCode;
	private String jobTitle;
	private String userTel;
	private String userTelArea;
	private String userTelExt;
	private String userMobile;
	private String userFax;
	private String userFaxArea;
	private String userEmail;
	private String userAddr;
	private String userCounty;
	private String userZipCode;
	private String isStop;
	private String roleId;
	private String memo;
	private String applyDate;
	private String personalId;	
	private String creator;

	private String q_userId;
	private String q_userName;
	private String q_deptCode;
	private String q_userEmail;
	private String q_isStop;
	private String q_roleId;
	private String q_applyDate;
	
	private String userIP;
	private String mailMsg;
	
	public String getMailMsg() {
		return checkGet(mailMsg);
	}
	public void setMailMsg(String mailMsg) {
		this.mailMsg = checkSet(mailMsg);
	}
	public String getId(){ return checkGet(id); }
	public void setId(String s){ id=checkSet(s); }
	public String getUserId(){ return checkGet(userId); }
	public void setUserId(String s){ userId=checkSet(s); }
	public String getUserSSN(){ return checkGet(userSSN); }
	public void setUserSSN(String s){ userSSN=checkSet(s); }
	public String getUserPWD(){ return get(userPWD); }
	public void setUserPWD(String s){ userPWD=get(s); }
	public String getUserName(){ return checkGet(userName); }
	public void setUserName(String s){ userName=checkSet(s); }
	public String getGroupId(){ return checkGet(groupId); }
	public void setGroupId(String s){ groupId=checkSet(s); }
	public String getDeptCode(){ return checkGet(deptCode); }
	public void setDeptCode(String s){ deptCode=checkSet(s); }
	public String getJobTitle(){ return checkGet(jobTitle); }
	public void setJobTitle(String s){ jobTitle=checkSet(s); }
	public String getUserTel(){ return checkGet(userTel); }
	public void setUserTel(String s){ userTel=checkSet(s); }
	public String getUserMobile(){ return checkGet(userMobile); }
	public void setUserMobile(String s){ userMobile=checkSet(s); }
	public String getUserFax(){ return checkGet(userFax); }
	public void setUserFax(String s){ userFax=checkSet(s); }
	public String getUserEmail(){ return checkGet(userEmail); }
	public void setUserEmail(String s){ userEmail=checkSet(s); }
	public String getUserAddr(){ return checkGet(userAddr); }
	public void setUserAddr(String s){ userAddr=checkSet(s); }
	public String getIsStop(){ return checkGet(isStop); }
	public void setIsStop(String s){ isStop=checkSet(s); }
	public String getRoleId(){ return checkGet(roleId); }
	public void setRoleId(String s){ roleId=checkSet(s); }
	public String getMemo(){ return checkGet(memo); }
	public void setMemo(String s){ memo=checkSet(s); }
	public String getApplyDate() {		return checkGet(applyDate);	}
	public void setApplyDate(String applyDate) {		this.applyDate = checkSet(applyDate);	}
	public String getPersonalId() {		return checkGet(personalId);	}
	public void setPersonalId(String personalId) {		this.personalId = checkSet(personalId);	}
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
	
	public String getQ_userId(){ return checkGet(q_userId); }
	public void setQ_userId(String s){ q_userId=checkSet(s); }
	public String getQ_userName(){ return checkGet(q_userName); }
	public void setQ_userName(String s){ q_userName=checkSet(s); }
	public String getQ_deptCode(){ return checkGet(q_deptCode); }
	public void setQ_deptCode(String s){ q_deptCode=checkSet(s); }
	public String getQ_userEmail(){ return checkGet(q_userEmail); }
	public void setQ_userEmail(String s){ q_userEmail=checkSet(s); }
	public String getQ_isStop(){ return checkGet(q_isStop); }
	public void setQ_isStop(String s){ q_isStop=checkSet(s); }
	public String getQ_roleId(){ return checkGet(q_roleId); }
	public void setQ_roleId(String s){ q_roleId=checkSet(s); }
	public String getQ_applyDate() {		return checkGet(q_applyDate);	}
	public void setQ_applyDate(String q_applyDate) {		this.q_applyDate = checkSet(q_applyDate);	}

	String isMgr, q_isMgr;
	public String getIsMgr() {return checkGet(isMgr);}
	public void setIsMgr(String s) {this.isMgr = checkSet(s);}
	public String getQ_isMgr() {return checkGet(q_isMgr);}
	public void setQ_isMgr(String s) {q_isMgr = checkSet(s);}
	
	private String[] subSystem;
	public String[] getSubSystem() {		return subSystem;	}
	public void setSubSystem(String[] subSystem) {		this.subSystem = subSystem;	}
	

	public String getUserIP() {return checkGet(userIP);}
	public void setUserIP(String userIP) {this.userIP = checkSet(userIP);}
	
	public String getCreator() {
		return checkGet(creator);
	}
	public void setCreator(String creator) {
		this.creator = checkSet(creator);
	}
	
	@Override
	public void doCreate() throws Exception {
		CommonUser obj = (CommonUser) View.getObject(" from CommonUser where userId = " + Common.sqlChar(getUserId()));
		if (obj != null) {
			throw new Exception("新增失敗，帳號重覆");
		} else {
//			int c = ServiceGetter.getInstance().getTcbwService().loadCount(" from CommonUser where userEmail="+Common.sqlChar(getUserEmail()));
//			if(c>0){        	
//				throw new Exception("此信箱已使用，請重新輸入!");
//            }else{
            	obj = new CommonUser();
    			obj.setUserId(getUserId());
    			if (!"".equals(getUserPWD())){
    				obj.setUserPwd(Common.getDigestString(getUserPWD(), "SHA-1"));
    			}
    			obj.setUserName(getUserName());
    			if(!"".equals(getPersonalId())){
    				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));
    			}    			
    			obj.setJobTitle(getJobTitle());
    			obj.setUserTel(getUserTel());
    			obj.setUserTelArea(getUserTelArea());
    			obj.setUserTelExt(getUserTelExt());
    			obj.setUserMobile(getUserMobile());
    			obj.setUserFax(getUserFax());
    			obj.setUserFaxArea(getUserFaxArea());
    			obj.setUserEmail(getUserEmail());
    			obj.setUserCounty(getUserCounty());
    			obj.setUserZipCode(getUserZipCode());
    			obj.setUserAddr(getUserAddr());
    			obj.setIsStop(getIsStop());
    			obj.setCreator(getEditID());
    			obj.setApplyDate(getApplyDate());
    			obj.setCreateTime(getEditTime());
    			obj.setMemo(getMemo());
    			obj.setRoleId(1);
    			obj.setInORout("in");
    			obj.setEditId(getEditID());
    			obj.setEditDate(getEditDate());
    			obj.setEditTime(getEditTime());
    	
    			// 所屬單位		
    			CommonDepartment dept = (CommonDepartment)View.getObject(" from CommonDepartment where shortCode ="+Common.sqlChar(getDeptCode()));		
    			obj.setCommonDepartment(dept);
    	
    			ServiceGetter.getInstance().getTcbwService().save(obj);
    			
    			String msg = sendEmail(obj, getUserPWD());
    			setMailMsg(msg);
//            }
		}	
	}
	
	@Override
	public void doUpdate() throws Exception {
		CommonUser obj = (CommonUser) View.getObject("from CommonUser where id = " + Common.getInt(getId()));	
		if(obj != null){			
//			int c = ServiceGetter.getInstance().getTcbwService().loadCount(" from CommonUser where userEmail="+Common.sqlChar(getUserEmail())+" and id !="+Common.getLong(getId()));
//			if(c>0){
//            	throw new Exception("此信箱已使用，請重新輸入!");
//            }else{
            	if(!"NOT_CHANGE123".equals(getUserPWD())){
    				if("Y".equals(Common.get(obj.getIsGetBackPW()))){
    					obj.setIsGetBackPW("N");
    				}
    				obj.setUserPwd(Common.getDigestString(getUserPWD(), "SHA-1"));
    				obj.setUserPwdModDt(Datetime.getYYYMMDD());
    			}
    			if(!"".equals(getPersonalId())){
    				obj.setPersonalId(TCBWCommon.getEncodeString(getPersonalId()));
    			}else{
    				obj.setPersonalId("");
    			}    			
    			obj.setJobTitle(getJobTitle());
    			obj.setUserTel(getUserTel());
    			obj.setUserTelArea(getUserTelArea());
    			obj.setUserTelExt(getUserTelExt());
    			obj.setUserMobile(getUserMobile());
    			obj.setUserFax(getUserFax());
    			obj.setUserFaxArea(getUserFaxArea());
    			obj.setUserEmail(getUserEmail());
    			obj.setUserCounty(getUserCounty());
    			obj.setUserZipCode(getUserZipCode());
    			obj.setUserAddr(getUserAddr());			

    			obj.setIsStop(getIsStop());
    			obj.setMemo(getMemo());
    			obj.setEditId(getEditID());
    			obj.setEditDate(getEditDate());
    			obj.setEditTime(getEditTime());			
    			obj.setRoleId(1);
    			obj.setInORout("in");			
    			
    			// 所屬單位		
    			CommonDepartment dept = (CommonDepartment)View.getObject(" from CommonDepartment where shortCode ="+Common.sqlChar(getDeptCode()));		
    			obj.setCommonDepartment(dept);		
    	
    			ServiceGetter.getInstance().getTcbwService().update(obj);
    			
    			CommonLog commonLog = new CommonLog();
    			
    			commonLog.setCode("CONIN0101F");//通報表代碼
    			commonLog.setOpenDate(Datetime.getYYYMMDD());//開啟日期
    			commonLog.setOpenTime(Datetime.getHHMMSS());//開啟時間
    			commonLog.setUserId(getUserID());//開啟人員
    			commonLog.setIp(getUserIP());//開啟人員IP
    			commonLog.setMethodName("UPDATE");//方法
    			commonLog.setDb("CommonUser");//資料表
    			commonLog.setDbId(getId());//資料表IP
    			commonLog.setApplNo("");//
    			commonLog.setOpenUserId(getUserId());//被開啟USerID
    			commonLog.setOpenUserName(getUserName());

    			ServiceGetter.getInstance().getTcbwService().save(commonLog);
//            }			
		} 
		else 
		{
			throw new Exception("查無資料，無法更新");
		}
		
	}
	
	@Override
	public void doDelete() throws Exception {
		ServiceGetter.getInstance().getCommonService().getCommonUserDao().deleteCommonUser(Common.getInt(getId()));	
	}
	
	
	/**
	 * <br>
	 * <br>目的：依主鍵查詢單一資料
	 * <br>參數：無
	 * <br>傳回：傳回本物件
	*/
	
	public CONIN0101F  doQueryOne() throws Exception {
		CONIN0101F obj = this;
		CommonUser c = (CommonUser)ServiceGetter.getInstance().getCommonService().getCommonUserDao().load(CommonUser.class, Integer.parseInt(getId()));
		if (c != null) {
	        obj.setId(Common.get(c.getId()));
	        obj.setUserId(c.getUserId());
//			obj.setUserPWD(c.getUserPwd());
	        obj.setUserPWD("NOT_CHANGE123");
	        obj.setUserName(c.getUserName());
	        if(!"".equals(Common.get(c.getPersonalId()))){
	        	obj.setPersonalId(TCBWCommon.getDecodeString(c.getPersonalId()));
	        }else{
	        	obj.setPersonalId("");
	        }
	        obj.setJobTitle(c.getJobTitle());
	        obj.setUserTel(c.getUserTel());
	        obj.setUserTelArea(c.getUserTelArea());
			obj.setUserTelExt(c.getUserTelExt());
	        obj.setUserMobile(c.getUserMobile());
	        obj.setUserFax(c.getUserFax());
	        obj.setUserFaxArea(c.getUserFaxArea());
	        obj.setUserEmail(c.getUserEmail());
	        obj.setUserAddr(c.getUserAddr());
			obj.setUserCounty(c.getUserCounty());
			obj.setUserZipCode(c.getUserZipCode());
	        obj.setIsStop(c.getIsStop());
			obj.setCreator(c.getCreator());
	        obj.setApplyDate(c.getApplyDate());
	        obj.setMemo(c.getMemo());
	        obj.setEditID(c.getEditId());
	        obj.setEditDate(c.getEditDate());
	        obj.setEditTime(c.getEditTime());   

	        
	        // 所屬單位
	        obj.setDeptCode(c.getCommonDepartment()!=null?c.getCommonDepartment().getShortCode():"");	        


		} else {
			throw new Exception("查無該筆資料！");
		}
		return obj;
	}


	/**
	 * <br>
	 * <br>目的：依查詢欄位查詢多筆資料
	 * <br>參數：無
	 * <br>傳回：傳回ArrayList
	*/

	public java.util.ArrayList<String[]> doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		StringBuffer sb = new StringBuffer();
		sb.append(" from CommonUser where 1=1 and inORout='in' ");
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		//追加條件：除了系統管理者以外，擁有子系統的user也可以排除在以下條件外
		if ("1".equals(Common.get(c.getRoleId())) && "".equals(Common.get(c.getSubSystem()))) {
			sb.append(" and userId = ").append(Common.sqlChar(c.getUserId()));  //非系統管理者只能查詢自己
		}
		c = null;
		if (!"".equals(getQ_userId()))
			sb.append(" and userId like ").append(Common.sqlChar(getQ_userId()));
		if (!"".equals(getQ_userName()))
			sb.append(" and userName like ").append(Common.sqlChar(getQ_userName()));
		if (!"".equals(getQ_deptCode()))
			sb.append(" and commonDepartment.shortCode = ").append(Common.sqlChar(getQ_deptCode()));
		if (!"".equals(getQ_userEmail()))
			sb.append(" and userEmail like ").append(Common.sqlChar(getQ_userEmail()));
		if (!"".equals(getQ_isStop()))
			sb.append(" and isStop = ").append(Common.sqlChar(getQ_isStop()));	
		if (!"".equals(getQ_applyDate())) 
			sb.append(" and applyDate = ").append(Common.sqlChar(getQ_applyDate()));

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonUserDao().loadCount(sb.toString()));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonUserDao().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonUserDao().load(sb.toString(), this.getRecordStart(), this.getPageSize(), "userId", true);
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");
				while (it.hasNext()) {
					CommonUser o = (CommonUser) it.next();					
					
					String rowArray[] = new String[6];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getUserId());
					rowArray[2] = Common.get(o.getUserName());
					rowArray[3] = Common.get(o.getJobTitle()!=null?titleMap.get(o.getJobTitle()):"");				
					rowArray[4] = Common.get(o.getCommonDepartment()!=null?o.getCommonDepartment().getDepartment():"");
					rowArray[5] = Common.get(o.getUserTel());
					arrList.add(rowArray);					
				}
				if(titleMap!=null) titleMap.clear();
			}
		}
		return arrList;
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
