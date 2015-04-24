<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String q = Common.get(request.getParameter("q"));

try {	
	String hql = " from " + Con1005Db.class.getCanonicalName() + " where id=" + Common.sqlChar(q);
	Con1005Db dt = (Con1005Db) View.getObject(hql);
	if (dt!=null) {
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		item.put("name",dt.getName());
		out.write(item.toString());
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>
