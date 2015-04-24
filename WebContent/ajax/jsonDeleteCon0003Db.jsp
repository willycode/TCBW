<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="net.sf.json.*"%>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String sysTemType = Common.get(request.getParameter("sysTemType"));
String formCode = Common.get(request.getParameter("formCode"));
String id = Common.get(request.getParameter("id"));

try{
	JSONObject item = new JSONObject();
	String result = "Y";
	
	String hql = "from Con0003Db where systemType = " + Common.sqlChar(sysTemType);
	hql += " and formCode = " + Common.sqlChar(formCode);
	hql += " and dbID = " + Common.sqlChar(id);
	hql += " and userID = " + Common.sqlChar(User.getUserId());
	
	Con0003Db dt = (Con0003Db) View.getObject(hql);
	if(dt != null){
		ServiceGetter.getInstance().getTcbwService().delete(dt);
	}else{
		result = "N";
	}
	item.put("r", result);
	out.write(item.toString());
}catch (Exception e){
	e.printStackTrace();
}
%>
