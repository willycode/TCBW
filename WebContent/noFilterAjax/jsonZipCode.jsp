<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="org.owasp.esapi.ESAPI" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
//response.addDateHeader("Expires", 1);

String city =ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("city")));

try {
	if (!"".equals(city)) {
		JSONArray dsField = View.getJsonZipCode(city);
		System.out.println(dsField.toString());
		out.write(dsField.toString());
	}		
} catch (Exception e) {
	e.printStackTrace();
}
%>
