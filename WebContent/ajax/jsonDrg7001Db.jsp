<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String v = Common.get(request.getParameter("v"));
String q = Common.get(request.getParameter("q"));

try 
{	
	String hql = " from Drg7001Db where 1=1 ";
	if(!"".equals(Common.get(q))) hql += " and id="+q;
	if(!"".equals(Common.get(v))) hql += " and applNo="+Common.sqlChar(v);

	Drg7001Db dt = (Drg7001Db) View.getObject(hql);
	if (dt!=null) 
	{
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		out.write(item.toString());
	}
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
