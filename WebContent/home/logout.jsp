<%@page import="com.kangdainfo.ServiceGetter"%>
<%
	String isInOrOut = "O";
	if (ServiceGetter.getInstance().getAuthenticationService().getCurrentUser()!=null) {
		if(ServiceGetter.getInstance().getAuthenticationService().getCurrentUser().getCommonDepartment()!=null && ServiceGetter.getInstance().getAuthenticationService().getCurrentUser().getCommonDepartment().getShortCode()!=null){
			if("05,06,07".indexOf(ServiceGetter.getInstance().getAuthenticationService().getCurrentUser().getCommonDepartment().getShortCode()) != -1){
				isInOrOut = "I";
			}
		}
		ServiceGetter.getInstance().getCommonService().saveCommonVisitLog(ServiceGetter.getInstance().getAuthenticationService().getCurrentUser(), request.getRemoteAddr(), "2", "Y", null);	
	}
	ServiceGetter.getInstance().getAuthenticationService().invalidateSession(request);
	//session.invalidate();
	if("O".equals(isInOrOut)){
		response.sendRedirect("../index.jsp?logout=Y");
	}else{
		response.sendRedirect("../login.jsp?logout=Y");
	}
		
%>
