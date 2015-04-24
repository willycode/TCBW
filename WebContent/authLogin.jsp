<!--
(內)
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.ServiceGetter"%>
<%@ page import="com.kangdainfo.common.model.bo.*"%>
<%@ page import="com.kangdainfo.common.util.*"%>
<%@ page import="com.kangdainfo.web.util.*"%>
<%
String j_username = Common.get(request.getParameter("j_username"));
String j_domain = Common.get(request.getParameter("j_domain"));
String j_logout = Common.get(request.getParameter("j_logout"));

String account = Common.get(request.getParameter("userID"));
String pwd = Common.get(request.getParameter("userPWD"));
String btnSubmit = Common.get(request.getParameter("state"));
String script = "";

int retryCount = 3;
int retryTimeout = 10;	
CommonUser user = null;
if ((!"".equals(btnSubmit) && btnSubmit.equals("submit") && !"".equals(account) && !"".equals(pwd) && Validate.checkSpecialChar(account)==false && Validate.checkSpecialChar(pwd)==false)
		|| ("fda.gov.tw".equals(j_domain) && !"Y".equals(j_logout) && !"".equals(j_username))){	
	// check user is retrying.
	boolean retrying = LoginUtil.isUserRetrying(account, request.getRemoteAddr(),retryCount);
	if(!retrying){
		if("fda.gov.tw".equals(j_domain) && !"Y".equals(j_logout) && !"".equals(j_username))
	    {
	      user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().saveOrGetVerifiedUser(j_username);	
	    }	
	    else
	    {	
		  user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().saveOrGetVerifiedUser(account, pwd);	 
	    }
	}
	if (user!=null) {
		if ("".equals(Common.get(user.getCommonDepartment().getFullCode()))) {
			//script = "alert('所屬單位資訊設定錯誤，請洽系統管理者或相關承辦人員!');";				
			ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(user, request.getRemoteAddr(), "1", "N", "單位屬性設定錯誤，請洽系統管理者或相關承辦人員");
			out.write("單位屬性設定錯誤，請洽系統管理者或相關承辦人員!");
		} if("05,06,07".indexOf(Common.get(user.getCommonDepartment().getShortCode())) == -1){
			// 判斷內外部使用者-此驗證程式為外部
			ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(user, request.getRemoteAddr(), "1", "N", "單位屬性不為內部使用者，請由外部登入作業登入");
			out.write("單位屬性不為內部使用者，請由外部登入作業登入!");
		} else {
			CommonVisitLogDetail objLog = (CommonVisitLogDetail) View.getObject("from CommonVisitLogDetail where userId="+Common.sqlChar(user.getUserId()) + " order by id desc");
			if (objLog!=null) {
				user.setLastLoginDate(Common.get(objLog.getLogDate()));
				user.setLastLoginTime(Common.get(objLog.getLogTime()));
				user.setLastLoginIP(Common.get(objLog.getUserIP()));			
			} else {
				user.setLastLoginDate("");
				user.setLastLoginTime("");
				user.setLastLoginIP("");
			}
			
			CommonUserRole userRole = new CommonUserRole();
			userRole.setId(user.getId());
			userRole.setRoleId(user.getRoleId());
			userRole.setCommonGroup(user.getCommonGroup());
			if (userRole != null) {
				CommonDepartment objDept = user.getCommonDepartment();
				
				/* 暫時不需要
				String hql = "from CommonDepartment where fullCode='" + Common.formatRearZero(objDept.getFullCode().substring(0,4),10) + "'";
				CommonDepartment issuDept = (CommonDepartment) View.getObject(hql);
				if (issuDept!=null) objDept.setIssueDepartment(issuDept);
				else {
					//out.write("發證單位層級找不到!!");
					//return;
				}			
				*/
				
				// 權限資料
				CommonAuth[] commonAuth = ServiceGetter.getInstance().getCommonService().getCommonAuthDao().getPermission("-11",userRole);				
				java.util.Map<String, Object[]> permissionMap = ServiceGetter.getInstance().getCommonService().getCommonAuthDao().getPermissionMap(commonAuth);				
								
				// 取得使用者權限 //session.getId()
				user.setCommonAuth(commonAuth);
				user.setPermissionMap(permissionMap);
				user.setSessionId(session.getId());
				user.setLoginDate(Datetime.getYYYMMDD());
				user.setLoginTime(Datetime.getHHMMSS());
				user.setUserIP(request.getRemoteAddr());							
				session.setAttribute("UserRole", userRole);
				session.setAttribute(WebConstants.SESSION_CURRENT_USER, user);			
				session.setAttribute(WebConstants.SESSION_CURRENT_USER_DEPARTMENT, objDept);
				session.setAttribute(WebConstants.SESSION_CURRENT_SYSTEM_ID, "-11");
				
				String isGetBackPw = "N";
				if("Y".equals(user.getIsGetBackPW())){
					isGetBackPw = "Y";
				}
				session.setAttribute("isGetBackPw", isGetBackPw);
				session.setAttribute("isInOrOut", "I");
				response.sendRedirect("home/frame.jsp");	
			} 
		//	else {
		//		//script = "alert('對不起，權限不足!');";			
		//		out.write("對不起，權限不足!");
		//	}		
		}
	} else if(retrying){
	    //script = "alert('因為重覆輸入錯誤密碼,這個帳號已被停用"+retryTimeout+"分鐘!');";
	    user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(account);
	    if (user==null) {
	    	user = new CommonUser();
	    	user.setUserId(account);	    	
	    }
	    ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(user, request.getRemoteAddr(), "1", "N", "因為重覆輸入錯誤密碼,帳號被停用"+retryTimeout+"分鐘");
	    out.write("因為重覆輸入錯誤密碼,這個帳號已被停用"+retryTimeout+"分鐘!");	    
	} else {
	    LoginUtil.markUserRetrying(account,request.getRemoteAddr(),retryCount,retryTimeout);	    	    
	    user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(account);	    
	    if (user==null) {
	    	user = new CommonUser();
	    	user.setUserId(account);
	    }
	    ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(user, request.getRemoteAddr(), "1", "N", "帳號或密碼輸入錯誤");
	    out.write("帳號或密碼錯誤, <a href=\"login.jsp\">請重新登入!</a><br>(注意：如果超過3次輸入錯誤，帳號將會暫時被停用！)");
	}
} else {
	session.invalidate();
}	
%>