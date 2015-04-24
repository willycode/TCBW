<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.*" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String u = Common.get(request.getParameter("u"));
String v = Common.get(request.getParameter("v"));
//response.addDateHeader("Expires", 1);
 
try{
	JSONObject item = new JSONObject();	
	StringBuilder sb = new StringBuilder(1024);
	sb.append(" from CommonUser where 1=1");
	if(!"".equals(Common.get(u))) 
		sb.append(" and userId="+Common.sqlChar(u));
	if(!"".equals(Common.get(v))) 
		sb.append(" and userEmail="+Common.sqlChar(v));
	int c = ServiceGetter.getInstance().getCommonService().loadCount(sb.toString());
	if(c > 0){
		item.put("obj0", "Y");	
	}
	out.write(Common.get(item.toString()));
}catch(Exception e){
	e.printStackTrace();
}
%>
