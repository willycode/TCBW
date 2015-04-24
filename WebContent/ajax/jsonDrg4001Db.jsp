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
	String hql = " from Drg4001Db where 1=1 ";
	if(!"".equals(Common.get(q))) hql += " and id="+q;
	if(!"".equals(Common.get(v))) hql += " and applNo="+Common.sqlChar(v);

	Drg4001Db dt = (Drg4001Db) View.getObject(hql);
	if (dt!=null) 
	{
		JSONObject item=new JSONObject();
		item.put("id",dt.getId());
		item.put("notifierName",Common.get(dt.getNotifierName()));//姓名
		item.put("notifierTelArea",Common.get(dt.getNotifierTelArea()));//電話
		item.put("notifierTel",Common.get(dt.getNotifierTel()));//電話
		item.put("notifierTelExt",Common.get(dt.getNotifierTelExt()));//電話
		item.put("notifierEmail",Common.get(dt.getNotifierEmail()));//信箱
		//item.put("notifierCounty",dt.getNotifierCounty());//地址
		//item.put("notifierZipCode",dt.getNotifierZipCode());//地址
		item.put("notifierAddress",Common.get(dt.getNotifierAddress()));//地址
		item.put("notifierDept",Common.get(dt.getNotifierDept()));//服務機構
		item.put("notifierDeptID",Common.get(dt.getNotifierDeptID()));//服務機構ID
		item.put("notifierPhone",Common.get(dt.getNotifierPhone()));//手機
		item.put("notifierFaxArea",Common.get(dt.getNotifierFaxArea()));//傳真
		item.put("notifierFax",Common.get(dt.getNotifierFax()));//傳真	

		out.write(item.toString());
	}
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
