<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires", 1);
long q = Common.getLong(request.getParameter("q"));
try {
	CommonDtree parent = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getNode(q);
	if (parent!=null && !"".equals(parent.getName())) out.write(parent.getName());
	else out.write("功能選單"); 	
} catch (Exception e) {}
 
%>
