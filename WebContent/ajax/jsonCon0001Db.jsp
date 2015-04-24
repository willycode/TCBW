<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ include file="../home/head.jsp" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String id = Common.get(request.getParameter("id"));
String ft = Common.get(request.getParameter("fileType"));
String dbName = Common.get(request.getParameter("dbName"));

try{
	if (!"".equals(id)) {
		JSONArray dsField = View.getJsonCon0001Db(id, ft, dbName);
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
