<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="net.sf.json.util.*"%>
<%
String q = Common.get(request.getParameter("q"));
if (!"".equals(q)) {
	CommonCodeKind obj = (CommonCodeKind) View.getObject("from CommonCodeKind where codeKindId=" + Common.sqlChar(q));
	if (obj!=null) {
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"commonCodeKind"});
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);		
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		out.write(json.toString());		
	}
}
%>