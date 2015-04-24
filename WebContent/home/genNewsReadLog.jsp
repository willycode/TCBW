<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%
String ids = Common.get(request.getParameter("q"));
if (ids!=null && ids.length()>0) {
	String[] arrIds = ids.split(",");	
	if (arrIds!=null && arrIds.length>0) {
		for (String id : arrIds) {
			if (Validate.checkInt(id) && Common.getInt(id)>0) {
				ServiceGetter.getInstance().getCommonService().saveCommonNewsReadLog(User,Common.getInt(id));
			}
		}
	}
}
%>