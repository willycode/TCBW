<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ include file="../home/head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String id = Common.get(request.getParameter("id"));
try{
	if (!"".equals(id)) {
		JSONArray dsField = View.getJsonCos0003Db(id);
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
