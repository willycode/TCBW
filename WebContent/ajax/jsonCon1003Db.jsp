<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String q = Common.get(request.getParameter("q"));

try {	
	String hql = " from " + Con1003Db.class.getCanonicalName() + " where id=" + Common.sqlChar(q);
	Con1003Db dt = (Con1003Db) View.getObject(hql);
	if (dt!=null) {
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		item.put("unionName",dt.getUnionName());
		out.write(item.toString());
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>
