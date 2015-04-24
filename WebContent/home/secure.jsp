<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="org.apache.commons.beanutils.BeanUtils"%>
<%@ include file="head.jsp" %>
<%
if (UserRole!=null && UserRole.getRoleId()<3 && User!=null && User.getPermissionMap()!=null && User.getPermissionMap().size()>0) {
	boolean isAuth = false;
	String progCode = Common.get(request.getParameter(WebConstants.PROGRAM_CODE));
	String hackerState = Common.get(request.getParameter("state"));
	if (!"".equals(progCode)) {				
		Object[] o = User.getPermissionMap().get(progCode);
		if (o!=null) {
			isAuth = true;
			int intAuth = Common.getInt(o[0]);
			CommonDtree dt = (CommonDtree) o[1];			
			if (UserRole.isUserLevel()) {
				session.setAttribute("PAU",intAuth);
				if (intAuth==1 && (hackerState.startsWith("insert") || hackerState.startsWith("save") || hackerState.startsWith("update") || hackerState.startsWith("delete") || hackerState.startsWith("import") || hackerState.startsWith("copy"))) {
					isAuth=false;
					try {
						CommonConfig ref = (CommonConfig) View.getObject("from CommonConfig where id='8'");
						if (ref!=null) {
							String[] arrFields = {"field1","field2","field3","field4","field5","field6","field7","field8","field9","field10","field11","field12"};
							java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
							for (int i=0; i<arrFields.length; i++) {
								String email = Common.get(BeanUtils.getProperty(ref,arrFields[i]));
								if (!"".equals(email) && Validate.checkEmail(email)) {
									mailList.add(new javax.mail.internet.InternetAddress(email));	
								}
							}
							if (mailList!=null && mailList.size()>0) {
								String subject = Common.formatDateTime(Datetime.getYYYMMDD()+Datetime.getHHMMSS(),4)+"-Hacker行為偵測警示";
								StringBuilder sb = new StringBuilder();
								sb.append("Hacker電腦資訊：").append(request.getRemoteAddr()).append("-").append(request.getRemoteHost()).append("-").append(request.getRemoteUser()).append("<br>");						
								sb.append("Hacker使用帳號：").append(User.getUserId()).append(" : ").append(User.getUserName()).append(" ").append(Dept.getFullName()).append("<br>");
								sb.append("Hacker執行程式：").append(dt.getName()).append("<br>");
								sb.append("Hacker執行動作：").append(hackerState).append("　動作沒有被授權<br>");			
								ServiceGetter.getInstance().getCommonService().sendEmail(subject, sb.toString(), mailList, null, true);	
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}
			ServiceGetter.getInstance().getAuthenticationService().setCurrentDtree(dt);	
		}
		if (isAuth==false) {
			out.flush(); 
			out.close(); 
			return; 
		}			
	}
}
/**	
if (User.getCommonAuth()!=null && User.getCommonAuth().length>0) {
	String progCode = Common.get(request.getParameter(WebConstants.PROGRAM_CODE));		
	Long pid = Common.getLong(request.getParameter("progID"));
	for (int i=0; i<User.getCommonAuth().length; i++) {
		CommonAuth authObj = User.getCommonAuth()[i];			
		if (!"".equals(progCode)) {
			if (Common.get(authObj.getCommonDtree().getBtnRead()).equals(progCode)) {
				session.setAttribute("PAU",authObj.getAuth());
				isAuth = true;
				break;
			}
		} else if (pid!=null && pid>0) {
			if (authObj.getCommonDtree().getId().longValue()==pid.longValue()) {
				session.setAttribute("PAU",authObj.getAuth());
				isAuth = true;
				break;
			}				
		}
	}				
}	
if (isAuth==false) {
	out.flush(); 
	out.close(); 
	return; 
}	
**/
%>