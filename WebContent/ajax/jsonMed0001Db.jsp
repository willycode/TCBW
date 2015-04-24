<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.*"%>
<%@ page import="com.kangdainfo.common.util.*" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.*" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

String q = Common.get(request.getParameter("q"));

try 
{	
	String hql = " from Med0001Db where id=" + q;
	Med0001Db dt = (Med0001Db) View.getObject(hql);
	if (dt!=null) 
	{
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		item.put("notifierName",dt.getNotifierName());//姓名
		item.put("notifierAreaCode",dt.getNotifierAreaCode());//電話
		item.put("notifierTel",dt.getNotifierTel());//電話
		item.put("notifierTelExt",dt.getNotifierTelExt());//電話
		item.put("notifierEamil",dt.getNotifierEamil());//信箱
		item.put("notifierCounty",dt.getNotifierCounty());//地址
		item.put("notifierZipCode",dt.getNotifierZipCode());//地址
		item.put("notifierAddress",dt.getNotifierAddress());//地址
		item.put("notifierDeptID",dt.getNotifierDeptID());//服務機構代碼
		item.put("notifierDept",dt.getNotifierDept());//服務機構
		out.write(item.toString());
	}
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
