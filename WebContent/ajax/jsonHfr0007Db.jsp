<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="net.sf.json.util.*"%>
<%@ page import="com.kangdainfo.tcbw.model.bo.Hfr0007Db" %>
<%
String q = Common.get(request.getParameter("id"));
if (!"".equals(q)) {
	Hfr0007Db obj = (Hfr0007Db) View.getObject("from Hfr0007Db where id = " + Common.sqlChar(q));
	if (obj != null) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"hfr0001Db"});
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);		
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		out.write(json.toString());		
	}
}
%>