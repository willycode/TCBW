<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<%@ page import="org.json.simple.*"%>
<%
//如果沒有設定驗證filter，請自行加權限驗證，否則請勿使用本程式..

response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");
String q = Common.get(request.getParameter("q"));
try 
{
	if (!"".equals(q)) 
	{
		java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(q);		

		if (list!=null && list.size()>0) 
		{
			Object o = list.get(0);		
			JSONObject json = new JSONObject();
			if (o.getClass().isArray()) 
			{
				Object[] x= (Object[]) list.get(0);				
				for (int i=0; i<x.length; i++) 
				{
					json.put("obj"+i,Common.get(x[i]));		
				}
			} 
			else 
			{
				json.put("obj0",o);
			}
			out.write(json.toString());
		}		
	}
} catch (Exception e) 
{
	e.printStackTrace();
	//out.write(e.getMessage());
}
%>