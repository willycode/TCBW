<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%@ page import="java.sql.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String upLoadId = Common.get(request.getParameter("upLoadId"));
String dbName = Common.get(request.getParameter("dbName"));

try {	
	String hql = " from " + Con0001Db.class.getCanonicalName() + " where upLoadId=" + Common.getLong(upLoadId) +" and dbName="+Common.sqlChar(dbName);	
	Con0001Db dt = (Con0001Db) View.getObject(hql);
	if (dt!=null) {
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		out.write(item.toString());
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>