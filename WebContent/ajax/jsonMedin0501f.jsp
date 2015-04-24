<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.json.simple.*"%>
<%@ include file="../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.model.bo.Con1007Db" %>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String str = Common.get(request.getParameter("str"));

JSONArray dsField = new JSONArray();

try 
{
	if(str.length()>0)
	{
		str=str.substring(0,str.length()-1);
	}
	
	String hql = "from Med0001Db where id in(" + str+")";
	
	java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
	
	String x="N";
	String temp="";
	
	int i=0;
	
	if (objList != null) 
	{
		JSONObject item = new JSONObject();	
	
		for(Object dtlObj : objList) 
		{				
			Med0001Db dtl = (Med0001Db)dtlObj;
			
			if(i>0 && !temp.equals(dtl.getMedPermitFirmCode()))
			{
				x="Y";
			}	
			
			temp=dtl.getMedPermitFirmCode();
			
			i++;
		}
		
		item.put("result",x);
		
		out.write(Common.get(item.toString()));
		
	}
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
