<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
try {
	JSONObject json = new JSONObject();
	json.put("YYYMMDD",Datetime.getYYYMMDD());	
	json.put("HHMMSS",Datetime.getHHMMSS());
	out.write(json.toString());
} catch (Exception e) {
	e.printStackTrace();
}
%>