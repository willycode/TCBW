<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
response.addDateHeader("Expires", 1);

int q = Integer.parseInt(Common.get(request.getParameter("q")));

try {
	CommonDepartment parent = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getCommonDepartment(q);
	if (parent!=null && !"".equals(parent.getDepartment())) out.write(parent.getDepartment());
	else out.write("最上層機關"); 	
} catch (Exception e) {}
 
%>
